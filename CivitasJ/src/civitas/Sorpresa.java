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
public abstract class Sorpresa {
    private String texto;
    private int valor;
    private MazoSorpresas mazo;
    //private TipoSorpresa tipo; //DESAPARECE EL TIPO PORQUE AHORA ES ABSTRACTA Y ADEMÁS TIENE CLASES HIJAS QUE HEREDAN DE ELLA
    private Tablero tablero;
    

    /** 
     * @brief Constructor de sorpresa*
     *
     */
      Sorpresa(){
          init();
      }
    
      
    
    public String getTexto() {
        return texto;
    }

    public int getValor() {
        return valor;
    }

    public MazoSorpresas getMazo() {
        return mazo;
    }

    public Tablero getTablero() {
        return tablero;
    }
     
    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void setMazo(MazoSorpresas mazo) {
        this.mazo = mazo;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
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
     void informe(int actual, ArrayList<Jugador> todos){
        Diario di = Diario.getInstance();
        di.ocurreEvento("se esta aplicando la sorpresa: "+ texto+" al jugador: "+ todos.get(actual).getNombre());
    }
    
    
    
    /**
     * @brief aplica al jugador el tipo de sorpresa
     * @param actual el indice del jugador que tiene el turno
     * @param todos la lista de jugadores
     */
    abstract void aplicarAJugador(int actual, ArrayList<Jugador> todos);

    /**
     * @brief didce el nombre de la sorpresa.
     */
    public String toString(){
        return texto;
    }    
    
//    /**
//     * @brief si el jugador es correcto, 
//     * ◦ se utiliza el método informe y obtiene la casilla actual del jugador
//     * ◦ se calcula la tirada utilizando el método calcularTirada(casillaActual, valor) del tablero.
//     * ◦ se obtiene la nueva posición del jugador con el método nuevaPosicion(casillaActual,tirada) del tablero.
//     * ◦ se mueve al jugador a esa nueva posición (método moverACasilla)
//     * ◦ se indica a la casilla que está en la posición del valor de la sorpresa que reciba al jugador (método recibeJugador)
//     * @param actual indice del jugador que tiene el turno
//     * @param todos lista de jugadores 
//     */
//    private void aplicarAJugador_irACasilla(int actual, ArrayList<Jugador> todos){
//        if (jugadorCorrecto(actual, todos)){
//            informe(actual, todos);
//            int casillaActual = todos.get(actual).getNumCasillaActual();
//            int tirada = tablero.calcularTirada(casillaActual, valor);
//            int nuevaPos = tablero.nuevaPosicion(casillaActual, tirada);
//            todos.get(actual).moverACasilla(nuevaPos);
//            tablero.getCasilla(nuevaPos).recibeJugador(actual, todos);
//        }
//    }
    
//    /**
//     * @brief Si el jugador es correcto, 
//     * se utiliza el método informe y 
//     * se encarcela al jugador (método encarcelar) indicado
//     * @param actual indice del jugador que tiene el turno
//     * @param todos lista de jugadores 
//     */
//    private void aplicarAJugador_irCarcel(int actual, ArrayList<Jugador> todos){
//        if (jugadorCorrecto(actual, todos)){
//            informe(actual, todos);
//            todos.get(actual).encarcelar(tablero.getCarcel());
//        }
//    }
//    
    
//    /**
//     * @brief si el jugador es correcto, 
//     * se utiliza el método informe 
//     * y se modifica el saldo del jugador actual(método modificarSaldo) con el valor de la sorpresa
//     * @param actual indice del jugador que tiene el turno
//     * @param todos lista de jugadores 
//     */
//    private void aplicarAJugador_pagarCobrar(int actual, ArrayList<Jugador> todos){
//        if (jugadorCorrecto(actual, todos)){
//            informe(actual, todos);
//            todos.get(actual).modificarSaldo(valor);
//        }
//    }
    
    
//    /**
//     * @brief si el jugador es correcto,
//     * se utiliza el método informe
//     * y se modifica el saldo del jugador actual(método modificarSaldo) 
//     * con el valor de la sorpresa multiplicado por el número de casas 
//     * y hoteles del jugador.
//     * @param actual indice del jugador que tiene el turno
//     * @param todos lista de jugadores 
//     */
//    private void aplicarAJugador_porCasaHotel(int actual, ArrayList<Jugador> todos){
//        if (jugadorCorrecto(actual, todos)){
//            informe(actual, todos);
//            todos.get(actual).modificarSaldo(valor*todos.get(actual).cantidadCasasHoteles());
//        }
//    }
    
    
//    /**
//     * @brief El jugador da dinero al jugador actual
//     * si el jugador es actual es correcto, 
//     * se utiliza el método informe
//     * ◦ se crea una sorpresa de tipo PAGARCOBRAR con el valor de la sorpresa multiplicado por -1 
//     * ◦ y se aplica a todos los jugadores menos el actual
//     * ◦ se crea una sorpresa de tipo PAGARCOBRAR con el valor de la sorpresa multiplicado 
//     * por el número de jugadores excluyendo al actual y se aplica solo al jugador actual.
//     * @param actual indice del jugador que tiene el turno
//     * @param todos lista de jugadores 
//     */
//    private void aplicarAJugador_porJugador(int actual, ArrayList<Jugador> todos){
//        if (jugadorCorrecto(actual, todos)){
//            informe(actual, todos);
//            Sorpresa s;
//            if (valor<0)
//                s = new Sorpresa(TipoSorpresa.PAGARCOBRAR, valor*-1, "Jugador actual hace una donación ");
//            else
//                s = new Sorpresa(TipoSorpresa.PAGARCOBRAR, valor*-1, "Jugador actual recibe una donación gracias ");
//            for (int i=0; i<todos.size(); i++){
//                if(i != actual)
//                    s.aplicarAJugador(i,todos);
//            }
//            Sorpresa s1;
//            if(valor<0)
//                s1 = new Sorpresa(TipoSorpresa.PAGARCOBRAR, valor*(todos.size()-1), "Has hecho una donación.");
//            else
//                s1= new Sorpresa(TipoSorpresa.PAGARCOBRAR, valor*(todos.size()-1), "Has recibido una donación."); 
//            s1.aplicarAJugador(actual,todos);
//            
//        }
//    }
    
    
    
//    /**
//     * @brief si el jugador es correcto, 
//     * se utiliza el método informe 
//     * y se pregunta a todos los jugadores si alguien tiene la sorpresa para evitar la cárcel (método tieneSalvoconducto).
//     * Si nadie la tiene, la obtiene el jugador actual (método obtenerSalvoconducto) 
//     * y se llama al método salirDelMazo.
//     */
//    private void aplicarAJugador_salirCarcel(int actual, ArrayList<Jugador> todos){
//        if (jugadorCorrecto(actual, todos)){
//            informe(actual, todos);
//            Boolean latienen = false;
//            for (int i=0; i< todos.size() && !latienen ;i++ ){
//                if(todos.get(i).tieneSalvoconducto())
//                    latienen = true;
//            }
//            if(!latienen){
//                Sorpresa s = new Sorpresa(TipoSorpresa.SALIRCARCEL, mazo);
//                todos.get(actual).obtenerSalvoconducto(s); 
//                s.salirDelMazo();
//            }
//        }
//    }
   
    
//    /**
//     * @brief si el tipo de la sorpresa es la que evita la cárcel, 
//     * inhabilita la carta especial en el mazo de sorpresas.
//     */
//    void salirDelMazo(){
//        if(tipo == TipoSorpresa.SALIRCARCEL)
//            mazo.inhabilitarCartaEspecial(this);
//    }
    
    

    
    
//    /**
//     * @brief si el tipo de la sorpresa es la que evita la cárcel, 
//     * habilita la carta especial en el mazo de sorpresas.
//     */
//    void usada(){
//         if(tipo == TipoSorpresa.SALIRCARCEL)
//            mazo.habilitarCartaEspecial(this);
//    }
    
    
    
    
    
    
    
//    /**
//     * @brief Constructor de la sorpresa que envia a la carcel
//     * @param tipo 
//     * @param tablero
//     */
//    Sorpresa(TipoSorpresa tipo, Tablero tablero){
//       
//        if (tipo == TipoSorpresa.IRCARCEL ){
//            init();
//            this.tipo = tipo;
//            this.tablero = tablero;
//            texto = "Vas a la carcel";
//        }
//        
//    }
    
    
//    /**
//     * @brief Constructor sorpresa que lleva a otra casilla
//     * @param tipo
//     * @param tablero
//     * @param valor
//     * @param texto
//     */
//    Sorpresa(TipoSorpresa tipo, Tablero tablero, int valor, String texto){
//       init();
//       this.tipo = tipo;
//       this.tablero = tablero;
//       this.valor = valor;
//       this.texto = texto;
//    }
    
    
//    /**
//     * @brief Constructor de todas las sorpresas restantes
//     * @param tipo
//     * @param valor
//     * @param texto
//     */
//    Sorpresa(TipoSorpresa tipo, int valor, String texto){
//        init();
//        this.tipo = tipo;
//        this.valor = valor;
//        this.texto = texto;
//    }
    
    
//    /**
//     *@brief Constructor para la sorpresa evitar la carcel
//     * @param tipo
//     * @param mazo
//     */
//    Sorpresa(TipoSorpresa tipo, MazoSorpresas mazo){
//        init();
//        texto = "Tienes un salvoconducto";
//        this.tipo = tipo;
//        this.mazo = mazo;
//        
//    }


//    /*******************____MAIN PRUEBA___*************************************/
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        // TODO code application logic here
//        System.out.println(TipoCasilla.CALLE);
//    }

    
}
