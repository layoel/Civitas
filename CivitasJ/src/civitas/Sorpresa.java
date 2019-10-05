/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.ArrayList;

/**
 *
 * @author ELVIRA
 */
public class Sorpresa {
    private String texto;
    private int valor;
    private MazoSorpresas mazo;
    private TipoSorpresa tipo;
    private Tablero tablero;
    
    /**
     * @brief Constructor de la sorpresa que envia a la carcel
     * @param tipo 
     * @param tablero
     */
    Sorpresa(TipoSorpresa tipo, Tablero tablero){
       
        if (tipo == TipoSorpresa.IRCARCEL ){
            init();
            this.tipo = tipo;
            this.tablero = tablero;
            texto = "ve a la carcel";
            //mazo= null; //en init
            //valor = -1;//en init
        }
        
    }
    
    
    /**
     * @brief Constructor sirpresa que lleva a otra casilla
     * @param tipo
     * @param tablero
     * @param valor
     * @param texto
     */
    Sorpresa(TipoSorpresa tipo, Tablero tablero, int valor, String texto){
       init();
       this.tipo = tipo;
       this.tablero = tablero;
       this.valor = valor;
       this.texto = texto;
    }
    
    
    /**
     * @brief Constructor de todas las sorpresas restantes
     * @param tipo
     * @param valor
     * @param texto
     */
    Sorpresa(TipoSorpresa tipo, int valor, String texto){
        init();
        this.tipo = tipo;
        this.valor = valor;
        this.texto = texto;
    }
    
    
    /**
     *@brief Constructor para la sorpresa evitar la carcel
     * @param tipo
     * @param mazo
     */
    Sorpresa(TipoSorpresa tipo, MazoSorpresas mazo){
        init();
        this.tipo = tipo;
        this.mazo = mazo;
        
    }
    
    
    
    /**
     * @brief Inicializa los  valores valor mazo y tablero
     */
    private void init(){
        valor = -1;
        mazo = null;
        tablero = null;
    }    
  
    
    
    /**
     * @brief comprueba si el jugador actual esta en la lista de jugadores
     * @param actual es el jugador que tiene el turno
     * @param todos lista de jugadores
     * @return correcto true si es correcto el jugador
     */
    public Boolean jugadorCorrecto(int actual, ArrayList<Jugador> todos){
        Boolean correcto = false;
        if (actual < todos.size())
            correcto = true;
        return correcto;
    }
    
    
    
    /**
     * @brief Informa que se esta aplicando una sorpresa a un jugador
     * @param actual es el jugador que tiene el turno
     * @param todos lista de jugadores
     */
    private void informe(int actual, ArrayList<Jugador> todos){
        Diario di = Diario.getInstance();
        di.ocurreEvento("se esta aplicando la sorpresa "+ texto+" al jugador: "+ todos.get(actual).getNombre());
    }
    
    
    
    /**
     * @brief aplica al jugador el tipo de sorpresa
     * @param actual el indice del jugador que tiene el turno
     * @param todos la lista de jugadores
     */
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        
        if (tipo == TipoSorpresa.IRCARCEL)
            aplicarAJugador_irCarcel(actual, todos);
        if (tipo == TipoSorpresa.IRCASILLA)
            aplicarAJugador_irACasilla(actual, todos);
        if (tipo == TipoSorpresa.PAGARCOBRAR)
            aplicarAJugador_pagarCobrar(actual, todos);
        if (tipo == TipoSorpresa.PORCASAHOTEL)
            aplicarAJugador_porCasaHotel(actual, todos);
        if (tipo == TipoSorpresa.PORJUGADOR)
            aplicarAJugador_porJugador(actual, todos);
        if (tipo == TipoSorpresa.SALIRCARCEL)
            aplicarAJugador_salirCarcel(actual, todos);
    }
    
    
    /**
     * @brief si el jugador es correcto, 
     * ◦ se utiliza el método informe y obtiene la casilla actual del jugador
     * ◦ se calcula la tirada utilizando el método calcularTirada(casillaActual, valor) del tablero.
     * ◦ se obtiene la nueva posición del jugador con el método nuevaPosicion(casillaActual,tirada) del tablero.
     * ◦ se mueve al jugador a esa nueva posición (método moverACasilla)
     * ◦ se indica a la casilla que está en la posición del valor de la sorpresa que reciba al jugador (método recibeJugador)
     * @param actual indice del jugador que tiene el turno
     * @param todos lista de jugadores 
     */
    private void aplicarAJugador_irACasilla(int actual, ArrayList<Jugador> todos){
        if (jugadorCorrecto(actual, todos)){
            informe(actual, todos);
            Casilla micasilla = new Casilla(Integer.toString(todos.get(actual).getNumCasillaActual()));
            int nuevaPos = tablero.calcularTirada(actual, valor);
            todos.get(actual).moverACasilla(nuevaPos);
            tablero.getCasilla(nuevaPos).recibeJugador_sorpresa(actual, todos);
        }
    }
    
    /**
     * @brief Si el jugador es correcto, 
     * se utiliza el método informe y 
     * se encarcela al jugador (método encarcelar) indicado
     * @param actual indice del jugador que tiene el turno
     * @param todos lista de jugadores 
     */
    private void aplicarAJugador_irCarcel(int actual, ArrayList<Jugador> todos){
        if (jugadorCorrecto(actual, todos)){
            informe(actual, todos);
            todos.get(actual).encarcelar(tablero.getCarcel());
        }
    }
    
    
    /**
     * @brief si el jugador es correcto, 
     * se utiliza el método informe 
     * y se modifica el saldo del jugador actual(método modificarSaldo) con el valor de la sorpresa
     * @param actual indice del jugador que tiene el turno
     * @param todos lista de jugadores 
     */
    private void aplicarAJugador_pagarCobrar(int actual, ArrayList<Jugador> todos){
        if (jugadorCorrecto(actual, todos)){
            informe(actual, todos);
            todos.get(actual).modificarSaldo(valor);
        }
    }
    
    
    /**
     * @brief si el jugador es correcto,
     * se utiliza el método informe
     * y se modifica el saldo del jugador actual(método modificarSaldo) 
     * con el valor de la sorpresa multiplicado por el número de casas 
     * y hoteles del jugador.
     * @param actual indice del jugador que tiene el turno
     * @param todos lista de jugadores 
     */
    private void aplicarAJugador_porCasaHotel(int actual, ArrayList<Jugador> todos){
        if (jugadorCorrecto(actual, todos)){
            informe(actual, todos);
            todos.get(actual).modificarSaldo(valor*todos.get(actual).cantidadCasasHoteles());
        }
    }
    
    
    /**
     * @brief El jugador da dinero al jugador actual
     * si el jugador es actual es correcto, 
     * se utiliza el método informe
     * ◦ se crea una sorpresa de tipo PAGARCOBRAR con el valor de la sorpresa multiplicado por -1 
     * ◦ y se aplica a todos los jugadores menos el actual
     * ◦ se crea una sorpresa de tipo PAGARCOBRAR con el valor de la sorpresa multiplicado 
     * por el número de jugadores excluyendo al actual y se aplica solo al jugador actual.
     * @param actual indice del jugador que tiene el turno
     * @param todos lista de jugadores 
     */
    private void aplicarAJugador_porJugador(int actual, ArrayList<Jugador> todos){
        if (jugadorCorrecto(actual, todos)){
            informe(actual, todos);
            Sorpresa s = new Sorpresa(TipoSorpresa.PAGARCOBRAR, valor*-1, "devuelve la pasta!");
            for (int i=0; i<todos.size(); i++){
                if(i != actual)
                    s.aplicarAJugador(i,todos);
            }
            Sorpresa s1 = new Sorpresa(TipoSorpresa.PAGARCOBRAR, valor*(todos.size()-1), "tus compis te regalan pasta!");
            s1.aplicarAJugador(actual,todos);
            
        }
    }
    
    
    
    /**
     * @brief si el jugador es correcto, 
     * se utiliza el método informe 
     * y se pregunta a todos los jugadores si alguien tiene la sorpresa para evitar la cárcel (método tieneSalvoconducto).
     * Si nadie la tiene, la obtiene el jugador actual (método obtenerSalvoconducto) 
     * y se llama al método salirDelMazo.
     */
    private void aplicarAJugador_salirCarcel(int actual, ArrayList<Jugador> todos){
        if (jugadorCorrecto(actual, todos)){
            informe(actual, todos);
            Boolean latienen = false;
            for (int i=0; i< todos.size() && !latienen ;i++ ){
                if(todos.get(i).tieneSalvoconducto())
                    latienen = true;
            }
            if(!latienen){
                Sorpresa s = new Sorpresa(TipoSorpresa.SALIRCARCEL, mazo);
                todos.get(actual).obtenerSalvoconducto(s); 
            }
        }
    }
   
    
    /**
     * @brief si el tipo de la sorpresa es la que evita la cárcel, 
     * inhabilita la carta especial en el mazo de sorpresas.
     */
    void salirDelMazo(){
        if(tipo == TipoSorpresa.SALIRCARCEL)
            mazo.inhabilitarCartaEspecial(this);
    }
    
    
    /**
     * @brief didce el nombre de la sorpresa.
     */
    public String toString(){
        return texto;
    }
    
    
    /**
     * @brief si el tipo de la sorpresa es la que evita la cárcel, 
     * habilita la carta especial en el mazo de sorpresas.
     */
    void usada(){
         if(tipo == TipoSorpresa.SALIRCARCEL)
            mazo.habilitarCartaEspecial(this);
    }
    
    /*******************____MAIN PRUEBA___*************************************/
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println(TipoCasilla.CALLE);
    }

    
}
