/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

 */
package civitas;

import juegoTexto.OperacionesJuego;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author ecastillo
 */
public class Civitas {

    private int indiceJugadorActual;
    private ArrayList<Jugador> jugadores;
    private EstadosJuego estado;
    private GestorEstados gestorEstados;
    private MazoSorpresas mazo;
    private Tablero tablero;
    
    
    /**
     * @brief Constructor
     *      • Inicializar el atributo jugadores creando y añadiendo un jugador 
     *          por cada nombre suministrado como parámetro.
     *      • Crear el gestor de estados y fijar el estado actual como el estado
     *          inicial (método estadoInicial()) indicado por este gestor.
     *      • Inicializar el índice del jugador actual (que será quien tenga el 
     *          primer turno). Para obtener ese valor se utilizará el método 
     *          adecuado del dado.
     *      • Crear el mazo de sorpresas
     *      • Llamar al método de inicialización del tablero. 
     *      • Llamar al método de inicialización del mazo de sorpresas. 
     */
     Civitas(ArrayList<String> nombres){ // cambiado a public en vez de package porque prueba esta en el paquete juegoTexto
        
        jugadores = new ArrayList<>(nombres.size());
        for (String n : nombres){
            jugadores.add(new Jugador(n));
        }
        
        gestorEstados = new GestorEstados();
        estado = gestorEstados.estadoInicial();
        
        Dado da = Dado.getInstance();
        indiceJugadorActual = da.quienEmpieza(jugadores.size());

        mazo = new MazoSorpresas(da.getDebug()); //hay que añadir el getDebug para que el mazo sepa si esta en modo debug para que se baraje o no
        
        inicializarTablero(mazo);
        
        inicializarMazoSorpresas(tablero); 

    }

     /* *************AÑDIDO PARA USO DEL JUEGO EN LA P3****************************
     Creo este método para poder consultarlo en la pausa del controlador y saber 
     si inicia turno el jugador o esta en otro estado
     */
    public EstadosJuego getEstado() {
        return estado;
    }
     
    
    /**
     * @brief igual que el metodo cancelar hipoteca de jugador
     * @param ip el identificador de la propiedad
     * @return true si la ha cancelado
     */
    public Boolean cancelarHipoteca(int ip){
        Boolean ok =jugadores.get(indiceJugadorActual).cancelarHipoteca(ip);
         if(ok)
            System.out.println("Has cancelado la hipoteca de la propiedad" + tablero.getCasilla(ip).getNombre());
        return ok;
    }
    
    
    
    /**
     * @brief igual que el metodo construir casa de jugador
     * @param ip el identificador de la propiedad
     * @return true si la ha construido
     */
    public Boolean construirCasa(int ip){
        Boolean ok = jugadores.get(indiceJugadorActual).construirCasa(ip);
        if(ok)
            System.out.println("Has construido UNA CASA en la propiedad " + jugadores.get(indiceJugadorActual).getPropiedades().get(ip).getNombre());
        return ok;
    }


    
    /**
     * @brief igual que el metodo construir hotel de jugador
     * @param ip el identificador de la propiedad
     * @return true si lo ha construido
     */    
    public Boolean construirHotel(int ip){
        Boolean ok = jugadores.get(indiceJugadorActual).construirHotel(ip);
        if(ok)
            System.out.println("Has construido UN HOTEL en la propiedad" + jugadores.get(indiceJugadorActual).getPropiedades().get(ip).getNombre());
        return ok;
    }   
    
    
    
    
    /**
     * @brief Cuenta cuantas vecs pasa por la salida.
     */
    private void contabilizarPasosPorSalida(Jugador jugadorActual){
        while(tablero.getPorSalida()>0)
            jugadorActual.pasaPorSalida();
    }
    
    
    /**
     * @brief Se acaba el juego si algun jugador cae en banca rota
     * @return fin true si se acaba el juego
     */
    public Boolean finalDelJuego(){
        Boolean fin = false;
    
        for(Jugador j : jugadores)
            if(j.enBancarrota()){
                fin=true;
            }
        if (fin){
            System.out.println("***********FIN DEL JUEGO**********");
            System.out.println("**** "+jugadores.get(indiceJugadorActual).getNombre()+ " TE HAS ARRUINADO ****");
        }
        return fin;
    }
    
    
    
    /**
     *#brief obtinee la casilla actual
     * @return casilla en la que esta el jugador actualmente
     */
    public Casilla getCasillaActual(){
        return tablero.getCasilla(jugadores.get(indiceJugadorActual).getNumCasillaActual());
    }
    
    
    
    /**
     * @brief consultor del jugador activo
     */
    public Jugador getJugadorActual(){
        return jugadores.get(indiceJugadorActual);
    }
    
    
    
    /**
     * @brief igual que el metodo hipotecar de jugador
     * @param ip el identificador de la propiedad
     * @return true si la ha hipotecado
     */
    public Boolean hipotecar (int ip){
        Boolean ok = jugadores.get(indiceJugadorActual).hipotecar(ip);
         if(ok)
            System.out.println("Has hipotecado la propiedad" + Integer.toString(ip));
        return ok;
    }
    
    
    /**
     * @brief información del jugador actual
     * @return toda la información del jugador que tiene el turno
     */
    public String infoJugadorTexto(){
        return jugadores.get(indiceJugadorActual).toString();
    }
    
    
    /**
     * @brief crea las sorpresas y las añade al mazo
     */
    private void inicializarMazoSorpresas( Tablero tablero ){
       
        mazo.alMazo(new SorpresaEspeculador(100, "Te conviertes en un jugador especulador ¡Ahora tienes privilegios!"));        
        mazo.alMazo(new SorpresaPorJugador(100, "Recibes una donación de 100 monedas de cada jugador"));
        mazo.alMazo(new SorpresaPorJugador( -1000, "Dona a los demas jugadores 1000 monedas."));
        mazo.alMazo(new SorpresaIrCasilla(tablero, 15, "Ve a la casilla 15"));
        mazo.alMazo(new SorpresaPorCasaHotel(1000, "Toma regalo!! ganas 1000 monedas por casaHotel"));
        mazo.alMazo(new SorpresaIrCasilla(tablero, 8, "Ve a la casilla 8"));
        mazo.alMazo(new SorpresaSalirCarcel(mazo));
        mazo.alMazo(new SorpresaIrCarcel( tablero));
        mazo.alMazo(new SorpresaPagarCobrar(-1000, "Dona a 1000 monedas"));
        mazo.alMazo(new SorpresaPagarCobrar(500, "Recibes una donación de 500 monedas mas!!"));
        mazo.alMazo(new SorpresaIrCasilla(tablero, 4, "Ve a la casilla 4"));
        mazo.alMazo(new SorpresaPorCasaHotel(-500, "Has pensado donar a la ciencia 500 monedas por casaHotel"));
        
    }
    
    
    /**
     *@brief crea el tablero indica la pos de la carcel y añade las casillas 
     *          del tablero
     * 12 casillas tipo calle
     * 3 casillas sorpresa
     * 1 casilla juez
     * 1 casilla carcel
     * 1 casilla impuesto
     * 1 casilla parking
     */
    private void inicializarTablero( MazoSorpresas mazo){
        Casilla.setCarcel(8);
        tablero = new Tablero(Casilla.getCarcel()); //la posicion de la carcel es la q yo he dicho por defecto crea la casilla de salida
        
        tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle Hipatia", 100, 100, 50, 50, 50), "Calle Hipatia"));//string nom, float ab, float fr, float hb, float pc, float pe////dice que se añadan las casillas que se van creando...
        tablero.añadeCasilla(new CasillaSorpresa(mazo, "SORPRESA!! ¿sabes quien fue Mary Winston Jackson? \n"
                + "fue una matemática e ingeniera aeroespacial estadounidense, \n"
                + "que trabajó para el Comité Consejero Nacional para la \n"
                + "Aeronáutica (NACA), que más tarde se transformaría en la NASA.\n"
                + " Trabajó en el Centro de Investigación de Langley la mayor parte\n"
                + " de su vida, empezando como calculista en la división de Cálculo \n"
                + "del Área Oeste, y más tarde llegaría a ser la primera ingeniera \n"
                + "de color de la NASA. Tras 34 años en la NASA, Jackson alcanzó\n"
                + " el puesto más alto posible para ingenieros. COGE UNA CARTA SORPRESA"));
        tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle  Mary Jackson", 1000, 1000, 500, 500, 500), "Calle  Mary Jackson"));       
        tablero.añadeJuez();
        tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle  Ada Lovelace", 100, 100, 50, 50, 50),"Calle  Ada Lovelace"));
        tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle  Hertha Ayrton", 100, 100, 50, 50, 50), "Calle  Hertha Ayrton"));
        tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle  Hedy Lamarr", 100, 100, 50, 50, 50), "Calle  Hedy Lamarr"));
        tablero.añadeCasilla(new CasillaSorpresa(mazo, "SORPRESA!! ¿sabes quien fue \n"
                + "Katherine Coleman Goble Johnson una física, científica \n"
                + "espacial y matemática estadounidense que contribuyó a la \n"
                + "aeronáutica de los Estados Unidos y sus programas espaciales \n"
                + "con la aplicación temprana de las computadoras electrónicas \n"
                + "digitales en la NASA. Conocida por su precisión en la \n"
                + "navegación astronómica, calculó la trayectoria para el \n"
                + "Proyecto Mercury y el vuelo del Apolo 11 a la Luna en 1969. COGE UNA CARTA SORPRESA"));
        tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle  Rosalind Franklin", 100, 100, 50, 50, 50), "Calle  Rosalind Franklin"));
        tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle  Annie Easley", 100, 100, 50, 50, 50), "Calle  Annie Easley"));
        tablero.añadeCasilla(new CasillaSorpresa(mazo, "SORPRESA!! ¿sabes quien fue Hedwig \n"
               + "Eva Maria Kiesler? conocida como Hedy Lamarr,  fue una actriz \n"
               + "de cine e inventora austriaca naturalizada estadounidense, \n"
               + "inventora de la primera versión del espectro ensanchado que \n"
               + "permitiría las comunicaciones inalámbricas de larga distancia.COGE UNA CARTA SORPRESA"));
        tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle  Anita Borg", 100, 100, 50, 50, 50), "Calle  Anita Borg"));
        tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle  Katherine Johnson ", 1000, 1000, 500, 500, 500), "Calle  Katherine Johnson "));
        tablero.añadeCasilla(new CasillaImpuesto((float)1000.0, "Maria Goeppert-Mayer fue Premio \n"
               + "Nobel de Física por sus descubrimientos sobre la estructura \n"
               + "de capas nuclear. AHORA QUE LA CONOCES, PAGA TUS IMPUESTOS!"));
        tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle  Valentina Tereshkova", 1000, 1000, 500, 500, 500), "Calle  Valentina Tereshkova"));
        tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle  Dorothy Vaughan", 1000, 1000, 500, 500, 500), "Calle  Dorothy Vaughan"));
        tablero.añadeCasilla(new Casilla("Karen Uhlenbeck es una matemática \n"
               + "estadounidense especialista en ecuaciones en derivadas \n"
               + "parciales. En marzo de 2019 recibió el Premio Abel or sus \n"
               + "investigaciones con ecuaciones en derivadas parciales de \n"
               + "las formas del espacio en varias dimensiones.PUEDES ACCEDER \n"
               + "AL PARKING"));
        tablero.añadeCasilla(new CasillaCalle(new TituloPropiedad("Calle  Jocelyn Bell Burnell", 1000, 1000, 500, 500, 500), "Calle  Jocelyn Bell Burnell"));
  
    }    
    
    
    
    /**
     * @brief actualiza el indice de jugador al cambiar de turno
     */
    private void pasarTurno(){
        if (indiceJugadorActual < jugadores.size())
            indiceJugadorActual = indiceJugadorActual + 1;
        if (indiceJugadorActual == jugadores.size())
            indiceJugadorActual = 0;
    }
    
    
    
    /**
     * @brief realiza un ranking en funcion del saldo de los jugadores
     */
    public ArrayList<Jugador> ranking(){
   
        ArrayList<Jugador> rankingJugadores = new ArrayList<>();
        
        rankingJugadores = jugadores;
        Collections.sort(rankingJugadores, Collections.reverseOrder());
        
        System.out.println("\n****** El ranking queda asi ********");
        int i = 1;
        for(Jugador j : rankingJugadores){
            System.out.println(Integer.toString(i)+ " "+j.getNombre()+" "+ Float.toString(j.getSaldo()));
            i++;
        }
         return rankingJugadores;
    }
    
    
    
    /**
     * @brief igual que el metodo salir de la carcel pagando de jugador
     * @param ip el identificador de la propiedad
     * @return true si sale
     */ 
    public Boolean salirCarcelPagando(){
        Boolean ok = jugadores.get(indiceJugadorActual).salirCarcelPagando();
        if(ok)
            System.out.println("\nSales de la carcel pagando\n");
        else
            System.out.println("\nNO puedes salir de la carcel pagando\n"); 
        return ok;
    }
    
    
    /**
     * @brief igual que el metodo salir de la carcel tirando de jugador
     * @param ip el identificador de la propiedad
     * @return true si sale
     */ 
    public Boolean salirCarcelTirando(){
        Boolean ok = jugadores.get(indiceJugadorActual).salirCarcelTirando();
        if(ok)
            System.out.println("\n Has sacado 5!! Sales de la carcel\n");
         else
            System.out.println("\nNO has sacado mas de 5, NO puedes salir de la carcel\n"); 
        return ok;
    }    
    
    
    
    /**
     *  @brief actualiza el estado del juego obteniendo el siguiente 
     *          estado del gestor de estados
     */
    public void siguientePasoCompletado( OperacionesJuego operacion){
        estado = gestorEstados.siguienteEstado(jugadores.get(indiceJugadorActual), estado, operacion);
    }
    
    
    /**
     * @brief igual que el metodo vender de jugador
     * @param ip el identificador de la propiedad
     * @return true si lo ha vendido
     */    
    public Boolean vender(int ip){
        Boolean ok = jugadores.get(indiceJugadorActual).vender(ip);
        if(ok)
            System.out.println("\n Has vendido la propiedad" + tablero.getCasilla(ip).getNombre()+"\n");
        return ok;
    }
    
    
    //////////////////////////////////// implementacion en p3 /////////////
    /**
     *@brief  
     */
    private void avanzaJugador(){
        Jugador jugadorActual = jugadores.get(indiceJugadorActual);
        int posicionActual = jugadorActual.getNumCasillaActual();
        int tirada = Dado.getInstance().tirar();
        int posicionNueva = tablero.nuevaPosicion(posicionActual, tirada);
        Casilla casilla = tablero.getCasilla(posicionNueva);
        this.contabilizarPasosPorSalida(jugadorActual);
        jugadorActual.moverACasilla(posicionNueva);
        casilla.recibeJugador(indiceJugadorActual, jugadores);
        this.contabilizarPasosPorSalida(jugadorActual);
        System.out.println("-------Puedes ir a la casilla: "+ jugadorActual.getNumCasillaActual()+ " " +casilla.getNombre() +" --------\n");
    }
    
    
    public OperacionesJuego siguientePaso(){
        Jugador jugadorActual = jugadores.get(indiceJugadorActual);
        OperacionesJuego operacion = gestorEstados.operacionesPermitidas(jugadorActual,estado );
        if(operacion == OperacionesJuego.PASAR_TURNO){
            pasarTurno();
            siguientePasoCompletado(operacion);
        }else if(operacion == OperacionesJuego.AVANZAR){
            avanzaJugador();
            siguientePasoCompletado(operacion);
        }
        return operacion;
    }
    
    
    
    public Boolean comprar(){
        Boolean res=false;
        
        Jugador jugadorActual = jugadores.get(indiceJugadorActual);
        int numCasillaActual = jugadorActual.getNumCasillaActual();
        Casilla casilla = tablero.getCasilla(numCasillaActual);
        TituloPropiedad titulo = casilla.getTituloPropiedad();
        res =jugadorActual.comprar(titulo);
        if(!res)
            System.out.println("\n No puedes comprar :( \n");
        else
            System.out.println("\n Propiedad comprada! :) \n");
        return res;
    }
    
    /*******************____MAIN PRUEBA___*************************************/
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ArrayList<String> todos = new ArrayList<>();
        
        todos.add(" Elvira ");
        todos.add(" Ale ");
        todos.add(" Jolu ");
        todos.add(" Iballa ");
        
        Civitas civi= new Civitas (todos);
        
        System.out.println(civi.infoJugadorTexto());
        System.out.println(civi.finalDelJuego());
        System.out.println(civi.indiceJugadorActual);
        if(civi.tablero.correcto())
            System.out.println("es correcto");
        System.out.println(civi.getCasillaActual());
    }
    
}

