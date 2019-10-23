/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.ArrayList;
import java.util.Arrays;
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
    Civitas(ArrayList<String> nombres){
        
        jugadores = new ArrayList<>(nombres.size());
        for (String n : nombres){
            jugadores.add(new Jugador(n));
        }
        
        gestorEstados = new GestorEstados();
        estado = gestorEstados.estadoInicial();
        
        Dado da = Dado.getInstance();
        indiceJugadorActual = da.quienEmpieza(jugadores.size());

        mazo = new MazoSorpresas();
        
        inicializarTablero(mazo);
        
        
        inicializarMazoSorpresas(tablero);  
    }
     
    
     /**
     * @brief muestra la info del jugador actual, si banca rota imprime ranking  
     */
//    public void actualizarInfo(){
//        System.out.println(jugadores.get(indiceJugadorActual).toString());
//        for(Jugador j : jugadores)
//            if(j.enBancarrota())
//                ranking();
//    }
    
    
    
    
    /**
     * @brief igual que el metodo candelar hipoteca de jugador
     * @param ip el identificador de la propiedad
     * @return true si la ha cancelado
     */
    public Boolean cancelarHipoteca(int ip){
        return jugadores.get(indiceJugadorActual).cancelarHipoteca(ip);
    }
    
    
    
    /**
     * @brief igual que el metodo construir casa de jugador
     * @param ip el identificador de la propiedad
     * @return true si la ha construido
     */
    public Boolean construirCasa(int ip){
        return jugadores.get(indiceJugadorActual).construirCasa(ip);
    }


    
    /**
     * @brief igual que el metodo construir hotel de jugador
     * @param ip el identificador de la propiedad
     * @return true si lo ha construido
     */    
    public Boolean construirHotel(int ip){
        return jugadores.get(indiceJugadorActual).construirHotel(ip);
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
            if(j.enBancarrota())
                return true;
        
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
        return jugadores.get(indiceJugadorActual).hipotecar(ip);
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

        mazo.alMazo(new Sorpresa(TipoSorpresa.IRCARCEL, tablero));
        mazo.alMazo(new Sorpresa(TipoSorpresa.SALIRCARCEL, mazo));
        mazo.alMazo(new Sorpresa(TipoSorpresa.IRCASILLA, tablero, 5, "ve a la carcel"));
        mazo.alMazo(new Sorpresa(TipoSorpresa.IRCASILLA, tablero, 4, "ve a la casilla 4"));
        mazo.alMazo(new Sorpresa(TipoSorpresa.IRCASILLA, tablero, 8, "ve a la casilla 8"));
        mazo.alMazo(new Sorpresa(TipoSorpresa.PAGARCOBRAR, -100, "Paga a 100 monedas"));
        mazo.alMazo(new Sorpresa(TipoSorpresa.PAGARCOBRAR, 100, "Toma regalo!! 100 monedas mas!!"));
        mazo.alMazo(new Sorpresa(TipoSorpresa.PORCASAHOTEL, -1000, "Paga  1000 monedas por casaHotel"));
        mazo.alMazo(new Sorpresa(TipoSorpresa.PORCASAHOTEL, 1000, "Toma regalo!! 300 monedas por casaHotel"));
        mazo.alMazo(new Sorpresa(TipoSorpresa.PORJUGADOR, 100, "cada uno de tus compis te regalan 100 monedas"));
        mazo.alMazo(new Sorpresa(TipoSorpresa.PORJUGADOR, -100, "debes pagar a tu compi 100 monedas"));
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
        tablero = new Tablero(5); //la posicion de la carcel es la q yo he dicho??????????????????
        
        tablero.añadeCasilla(new Casilla("SALIDA"));
        
        tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle Hipatia", 100, 100, 50, 50, 50)));//string nom, float ab, float fr, float hb, float pc, float pe////dice que se añadan las casillas que se van creando...
        tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle  Ada Lovelace", 100, 100, 50, 50, 50)));
        tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle  Hertha Ayrton", 100, 100, 50, 50, 50)));
        tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle  Hedy Lamarr", 100, 100, 50, 50, 50)));
        tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle  Rosalind Franklin", 100, 100, 50, 50, 50)));
        tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle  Annie Easley", 100, 100, 50, 50, 50)));
        tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle  Anita Borg", 100, 100, 50, 50, 50)));
        tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle  Valentina Tereshkova", 100, 100, 50, 50, 50)));
        tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle  Jocelyn Bell Burnell", 100, 100, 50, 50, 50)));
        tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle  Katherine Johnson ", 100, 100, 50, 50, 50)));
        tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle  Dorothy Vaughan", 100, 100, 50, 50, 50)));
        tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle  Mary Jackson", 100, 100, 50, 50, 50)));
        
        tablero.añadeCasilla(new Casilla(mazo, "SORPRESA!! ¿sabes quien fue Mary Winston Jackson? "
                + "fue una matemática e ingeniera aeroespacial estadounidense, "
                + "que trabajó para el Comité Consejero Nacional para la "
                + "Aeronáutica (NACA), que más tarde se transformaría en la NASA."
                + " Trabajó en el Centro de Investigación de Langley la mayor parte"
                + " de su vida, empezando como calculista en la división de Cálculo "
                + "del Área Oeste, y más tarde llegaría a ser la primera ingeniera "
                + "de color de la NASA. Tras 34 años en la NASA, Jackson alcanzó"
                + " el puesto más alto posible para ingenieros"));
        tablero.añadeCasilla(new Casilla(mazo, "SORPRESA!! ¿sabes quien fue "
                + "Katherine Coleman Goble Johnson una física, científica "
                + "espacial y matemática estadounidense que contribuyó a la "
                + "aeronáutica de los Estados Unidos y sus programas espaciales "
                + "con la aplicación temprana de las computadoras electrónicas "
                + "digitales en la NASA. Conocida por su precisión en la "
                + "navegación astronómica, calculó la trayectoria para el "
                + "Proyecto Mercury y el vuelo del Apolo 11 a la Luna en 1969."));
        tablero.añadeCasilla(new Casilla(mazo, "SORPRESA!! ¿sabes quien fue Hedwig "
                + "Eva Maria Kiesler? conocida como Hedy Lamarr,  fue una actriz "
                + "de cine e inventora austriaca naturalizada estadounidense, "
                + "inventora de la primera versión del espectro ensanchado que "
                + "permitiría las comunicaciones inalámbricas de larga distancia."));
        
        tablero.añadeJuez();
        
        
        tablero.añadeCasilla(new Casilla(100, "Maria Goeppert-Mayer fue Premio "
                + "Nobel de Física por sus descubrimientos sobre la estructura "
                + "de capas nuclear. AHORA QUE LA CONOCES, PAGA TUS IMPUESTOS!"));
        
        tablero.añadeCasilla(new Casilla("Karen Uhlenbeck es una matemática "
                + "estadounidense especialista en ecuaciones en derivadas "
                + "parciales. En marzo de 2019 recibió el Premio Abel or sus "
                + "investigaciones con ecuaciones en derivadas parciales de "
                + "las formas del espacio en varias dimensiones.PUEDES ACCEDER "
                + "AL PARKING"));
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
    private ArrayList<Jugador> ranking(){
   
        ArrayList<Jugador> rankingJugadores = new ArrayList<>();
        
        rankingJugadores = jugadores;
        Collections.sort(rankingJugadores);
        
         return rankingJugadores;
    }
    
    
    
    /**
     * @brief igual que el metodo salir de la carcel pagando de jugador
     * @param ip el identificador de la propiedad
     * @return true si sale
     */ 
    public Boolean salirCarcelPagando(){
        return jugadores.get(indiceJugadorActual).salirCarcelPagando();
    }
    
    
    /**
     * @brief igual que el metodo salir de la carcel tirando de jugador
     * @param ip el identificador de la propiedad
     * @return true si sale
     */ 
    public Boolean salirCarcelTirando(){
        return jugadores.get(indiceJugadorActual).salirCarcelTirando();
    }    
    
    
    
    /**
     *  @brief actualiza el estado del juego obteniendo el siguiente 
     *          estado del gestor de estados
     */
    public void siguientePasoCompletado( OperacionesJuego operacion){
        gestorEstados.siguienteEstado(jugadores.get(indiceJugadorActual), estado, operacion);
    }
    
    
    /**
     * @brief igual que el metodo vender de jugador
     * @param ip el identificador de la propiedad
     * @return true si lo ha vendido
     */    
    public Boolean vender(int ip){
        return jugadores.get(indiceJugadorActual).vender(ip);
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

