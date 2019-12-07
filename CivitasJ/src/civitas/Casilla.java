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
    private float importe; //PASA A CASILLA IMPUESTO 
    private String nombre;
    //private TipoCasilla tipo; YA NO EXISTE POR USAR HERENCIA
    private TituloPropiedad tituloPropiedad; //PASA A LA CLASE CASILLA CALLE
    private Sorpresa sorpresa; //PASA A CASILLA SORPRESA necesito visibilidad de paquete para poder usarla en casillaSorpresa
    private MazoSorpresas mazo; //PASA A CASILLA SORPRESA
    
    
    /**
     * @brief constructor de casilla descanso
     * @param nombre nombre de la casilla 
     */   
    Casilla(String nombre){
        init();
        this.nombre = nombre;
    }

    static void setCarcel(int carcel) {
        Casilla.carcel = carcel;
    }

    static int getCarcel() {
        return carcel;
    }
    
    void setMazo(MazoSorpresas mazo) {
        this.mazo = mazo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public void setTituloPropiedad(TituloPropiedad tituloPropiedad) {
        this.tituloPropiedad = tituloPropiedad;
    }

    public void setSorpresa(Sorpresa sorpresa) {
        this.sorpresa = sorpresa;
    }

    public float getImporte() {
        return importe;
    }

    public Sorpresa getSorpresa() {
        return sorpresa;
    }

    public MazoSorpresas getMazo() {
        return mazo;
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
        tituloPropiedad = null;
        sorpresa = null;
        mazo = null;
    }
    
    
     /**
     *@brief informa la diario acerca del jugador que ha caido en la casilla 
     * @param actual
     * @param todos
     */
    void informe(int actual, ArrayList<Jugador> todos){ //cambio visibilidad a paquete para poder usarlo en las clases hijas
        Diario di = Diario.getInstance();
        di.ocurreEvento("El jugador "+ todos.get(actual).getNombre() + 
                " avanza hasta la casilla " + this.toString());
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
    
    


    
    //se implementan en proximas practicas.
    void recibeJugador(int actual, ArrayList<Jugador>todos){
            informe(actual, todos);
    }
    
   
    
         
    /**
     * @brief represente con detalle la información acerca de la casilla
     */
    public String toString(){
        String text;
        
        text = "\n nombre: "+ nombre;
        return text;
    }   
    
    
    /*******************____MAIN PRUEBA___*************************************/
    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        System.out.println("creando casilla de salida");
//        Casilla salida = new Casilla("Salida");
//        System.out.println(salida.toString());
//        
//        System.out.println("\ncreando casilla calle");
//        TituloPropiedad titulo = new TituloPropiedad("calle pollito" ,(float)1.0,(float)2.0,(float)3.0,(float)4.0,(float)5.0);
//        Casilla calle1 = new Casilla(titulo);
//        System.out.println(calle1.toString());
//        
//        System.out.println("\ncreando casilla impuesto:");
//        Casilla impuesto = new Casilla ((float) 200.36, "impuesto de sucesiones");
//        System.out.println(impuesto.toString());
//        
//        System.out.println("\ncreando casilla Juez:");
//        Casilla juez = new Casilla(5, "Soy el Juez");
//        System.out.println(juez.toString());
//        
//        System.out.println("\ncreando casilla Sorpresa:");
//        MazoSorpresas m = new MazoSorpresas();
//        Sorpresa s = new Sorpresa (TipoSorpresa.PAGARCOBRAR,100, "has conseguido 100 monedas!");
//        m.alMazo(s);
//        Casilla sor = new Casilla(m, "Toma sorpresa!");
//        System.out.println(sor.toString());
//        
//        
//        Jugador j1 = new Jugador(" Elvira ");
//        Jugador j2 = new Jugador(" Ale ");
//        Jugador j3 = new Jugador(" Jolu ");
//        Jugador j4 = new Jugador(" Iballa ");
//        
//        ArrayList<Jugador> todos = new ArrayList<>();
//        
//        todos.add(j1);
//        todos.add(j2);
//        todos.add(j3);
//        todos.add(j4);
//        
//        
//        salida.informe(0, todos);
//        Diario di = Diario.getInstance();
//        System.out.println(di.eventosPendientes());
//        System.out.println(di.leerEvento());
//        calle1.informe(1, todos);
//        System.out.println(di.leerEvento());
//        impuesto.informe(2, todos);
//        System.out.println(di.leerEvento());
//        juez.informe(3, todos);
//        System.out.println(di.leerEvento());
//        if(sor.jugadorCorrecto(2,todos)){
//            sor.informe(2, todos);
//            System.out.println(di.leerEvento());
//        }else
//            System.out.println("no existen tantos jugadores");
//    }


    
}
