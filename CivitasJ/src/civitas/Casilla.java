/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.ArrayList;

/**
 *
 * @author ecastillo
 * 
 * @Bief Clase casilla
 */
public class Casilla {
    static private int carcel;
    private float importe; 
    private String nombre;
    private TipoCasilla tipo;
    private TituloPropiedad tituloPropiedad;
    private Sorpresa sorpresa;
    private MazoSorpresas mazo;
    
    /**
     * @brief constructor de casilla descanso
     * @param nombre nombre de la casilla 
     */   
    Casilla(String nombre){
        init();
        this.nombre = nombre;
        tipo = TipoCasilla.DESCANSO;
    }
    
    
    
    /**
     * @brief constructor de casilla calle
     * @param titulo de la propiedad 
     */ 
    Casilla (TituloPropiedad titulo){
        init();
        tituloPropiedad = titulo;
        tipo = TipoCasilla.CALLE;
        nombre = tituloPropiedad.getNombre();
    }
    
    
    
    /**
     * @brief constructor de casilla impuesto
     * @param cantidad 
     * @param nombre de la casilla 
     */ 
    Casilla (float cantidad, String nombre){
        init();
        importe = cantidad;
        this.nombre = nombre;
        tipo = TipoCasilla.IMPUESTO;
    }
    
    

    /** 
     * @brief constructor de casilla juez
     * @param numcarcel numero de la casilla carcel
     * @param nombre el nombre de la casilla
     */ 
    Casilla (int numCarcel, String nombre){
        init();
        carcel = numCarcel;
        this.nombre = nombre;
        tipo = TipoCasilla.JUEZ;
    }
    
    
    
    /**
     * @brief constructor de casilla sorpresa
     * @param mazo de cartas sorpresa
     * @param nombre
     */ 
    Casilla (MazoSorpresas mazo, String nombre){
        init();
        this.mazo = mazo;
        this.nombre = nombre;
        tipo = TipoCasilla.SORPRESA;
    }
    
    
        /**
     * @brief inicia todos los atributos por defecto
     * @param carcel
     * @param importe
     * @param nombre
     * @param tipo
     * @param tituloPropiedad
     * @param sorpresa
     * @param mazo
     */
    private void init(){
        carcel= 5;
        importe = 0;
        nombre = "Calle";
        tipo = TipoCasilla.DESCANSO;
        tituloPropiedad = null;
        sorpresa = null;
        mazo = null;
    }
    
    
    /**
     *@brief informa la diario acerca del jugador que ha caido en la casilla 
     * @param actual
     * @param todos
     */
    private void informe(int actual, ArrayList<Jugador> todos){
        Diario di =Diario.getInstance();
        di.ocurreEvento("el jugador "+ todos.get(actual).getNombre() + 
                "ha caido en la casilla " + this.toString());
    }
    
    
    
    /**
     * @brief El jugador paga un impuesto por el valor que indica la casilla
     * @param actual
     * @param todos
     */
    void recibeJugador_impuesto(int actual, ArrayList<Jugador>todos){
        if(jugadorCorrecto(actual,todos)){
            informe(actual,todos);
            todos.get(actual).pagaImpuesto(importe);
        }
            
    }  
    
    
    
    /**
     * @brief Se encarcela al jugador actual
     * @param actual
     * @param todos
     */
    void recibeJugador_juez(int actual, ArrayList<Jugador>todos){
        if(jugadorCorrecto(actual, todos)){
            informe(actual,todos);
            todos.get(actual).encarcelar(carcel);
        }
    }
        

        
    /**
     * @brief represente con detalle la información acerca de la casilla
     */
    public String toString(){
        String text;
        
        text = "\n nombre: "+ nombre +
                "\n El tipo : " +tipo.toString();
        if (carcel > 0)
            text = text +"\n carcel: "+ Integer.toString(carcel);
        if (importe > 0)
            text = text + "\n el importe: "+ Float.toString(importe);
        if(tituloPropiedad != null)
            text = text + "\n tutiloPropiedad" + tituloPropiedad.toString();
        if(sorpresa != null)
            text = text + "\n sorpresa: " + sorpresa.toString();
        if(mazo != null)
            text = text + "\n mazo: " + mazo.toString();       
        return text;
    }   
    
    
    
    /**
     * @brief comprueba si el indice  es valido para acceder a jugador
     * @param actual jugador que tiene el turno
     * @param todos todos los jugadores
     * @return ok true si es correcto
     */
    public Boolean jugadorCorrecto(int actual, ArrayList<Jugador> todos){
        Boolean ok = false;
         if (actual < todos.size())
             ok = true;
         return ok;
    }
    
    
    
    
    /**
     * @brief Consultor de nombre
     * @return devuelve el nombre
     */
    public String getNombre(){
        return nombre;
    }
        
    /**
     * @brief consultor del titulo de la propiedad
     * return el titulo de la propiedad
     */
    TituloPropiedad getTituloPropiedad(){
        return tituloPropiedad;
    }
    
    
    //se implementan en proximas practicas.
    void recibeJugador(int actual, ArrayList<Jugador>todos){
        if (tipo == TipoCasilla.CALLE)
            recibeJugador_calle(actual, todos);
        else if (tipo == TipoCasilla.IMPUESTO)
            recibeJugador_impuesto(actual, todos);
        else if (tipo == TipoCasilla.JUEZ)
            recibeJugador_juez(actual, todos);
        else if (tipo == TipoCasilla.SORPRESA)
            recibeJugador_sorpresa(actual, todos);
        else
            informe(actual, todos);
    }
    
    void recibeJugador_calle(int actual, ArrayList<Jugador>todos){
        if(jugadorCorrecto(actual, todos)){
            informe(actual, todos);
            Jugador jugador = new Jugador(todos.get(actual));
            if(!tituloPropiedad.tienePropietario()){
                jugador.puedeComprarCasilla();   
            }else{
                tituloPropiedad.tramitarAlquiler(jugador);
            }
        }    
    }
    
    void recibeJugador_sorpresa(int actual, ArrayList<Jugador>todos){
        if(jugadorCorrecto(actual, todos)){
            Sorpresa sorpresa= mazo.siguiente();
            informe(actual, todos);
            sorpresa.aplicarAJugador(actual,todos);
        }
    }
    
    /*******************____MAIN PRUEBA___*************************************/
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("creando casilla de salida");
        Casilla salida = new Casilla("Salida");
        System.out.println(salida.toString());
        
        System.out.println("\ncreando casilla calle");
        TituloPropiedad titulo = new TituloPropiedad("calle pollito" ,(float)1.0,(float)2.0,(float)3.0,(float)4.0,(float)5.0);
        Casilla calle1 = new Casilla(titulo);
        System.out.println(calle1.toString());
        
        System.out.println("\ncreando casilla impuesto:");
        Casilla impuesto = new Casilla ((float) 200.36, "impuesto de sucesiones");
        System.out.println(impuesto.toString());
        
        System.out.println("\ncreando casilla Juez:");
        Casilla juez = new Casilla(5, "Soy el Juez");
        System.out.println(juez.toString());
        
        System.out.println("\ncreando casilla Sorpresa:");
        MazoSorpresas m = new MazoSorpresas();
        Sorpresa s = new Sorpresa (TipoSorpresa.PAGARCOBRAR,100, "has conseguido 100 monedas!");
        m.alMazo(s);
        Casilla sor = new Casilla(m, "Toma sorpresa!");
        System.out.println(sor.toString());
        
        
        Jugador j1 = new Jugador(" Elvira ");
        Jugador j2 = new Jugador(" Ale ");
        Jugador j3 = new Jugador(" Jolu ");
        Jugador j4 = new Jugador(" Iballa ");
        
        ArrayList<Jugador> todos = new ArrayList<>();
        
        todos.add(j1);
        todos.add(j2);
        todos.add(j3);
        todos.add(j4);
        
        
        salida.informe(0, todos);
        Diario di = Diario.getInstance();
        System.out.println(di.eventosPendientes());
        System.out.println(di.leerEvento());
        calle1.informe(1, todos);
        System.out.println(di.leerEvento());
        impuesto.informe(2, todos);
        System.out.println(di.leerEvento());
        juez.informe(3, todos);
        System.out.println(di.leerEvento());
        if(sor.jugadorCorrecto(2,todos)){
            sor.informe(2, todos);
            System.out.println(di.leerEvento());
        }else
            System.out.println("no existen tantos jugadores");
    }
    
    
}
