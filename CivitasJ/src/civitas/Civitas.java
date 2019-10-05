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
    public void actualizarInfo(){
        jugadores.get(indiceJugadorActual).toString();
        for(Jugador j : jugadores)
            if(j.enBancarrota())
                ranking();
    }
    
    
    
    
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
        mazo.alMazo(new Sorpresa(TipoSorpresa.PAGARCOBRAR, -100, "Paga a la banca 100 monedas"));
        mazo.alMazo(new Sorpresa(TipoSorpresa.PAGARCOBRAR, 100, "La banca te regala 100 monedas"));
        mazo.alMazo(new Sorpresa(TipoSorpresa.PORCASAHOTEL, -100, "Paga a la banca 100monedas por casaHotel"));
        mazo.alMazo(new Sorpresa(TipoSorpresa.PORCASAHOTEL, 100, "La banca te regala 100monedas por casaHotel"));
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
        
        tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle1", 100, 100, 50, 50, 50)));//string nom, float ab, float fr, float hb, float pc, float pe////dice que se añadan las casillas que se van creando...
        tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle2", 100, 100, 50, 50, 50)));
        tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle3", 100, 100, 50, 50, 50)));
        tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle4", 100, 100, 50, 50, 50)));
        tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle5", 100, 100, 50, 50, 50)));
        tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle6", 100, 100, 50, 50, 50)));
        tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle7", 100, 100, 50, 50, 50)));
        tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle8", 100, 100, 50, 50, 50)));
        tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle9", 100, 100, 50, 50, 50)));
        tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle10", 100, 100, 50, 50, 50)));
        tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle11", 100, 100, 50, 50, 50)));
        tablero.añadeCasilla(new Casilla(new TituloPropiedad("Calle12", 100, 100, 50, 50, 50)));
        
        tablero.añadeCasilla(new Casilla(mazo, "¡¡¡sorpresa1!!!"));
        tablero.añadeCasilla(new Casilla(mazo, "¡¡¡sorpresa2!!!"));
        tablero.añadeCasilla(new Casilla(mazo, "¡¡¡sorpresa3!!!"));
        
        tablero.añadeJuez();
        
        tablero.añadeCasilla(new Casilla(5, "||||||CARCEL||||||"));
        
        tablero.añadeCasilla(new Casilla(100, "PAGA TUS IMPUESTOS"));
        
        tablero.añadeCasilla(new Casilla("PARKING"));
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
        //Arrays.sort(jugadores, c);
   
        ArrayList<Jugador> rankingJugadores = new ArrayList<>();
//        ArrayList<Float> saldos = new ArrayList<>();
//            
//        //guardo los saldos en un array
//            for(Jugador j : jugadores){
//                saldos.add(j.getSaldo());
//            }
//        //ordeno el array de saldos
//            Collections.sort(saldos);
//        //si el jugador que estoy mirando su saldo coincide con el saldo 
//        //en el ranking lo coloco en el ranking
//            int i=0;
//            while( i< saldos.size()){
//                for(Jugador j : jugadores){
//                    if(j.getSaldo() == saldos.get(i))
//                        rankingJugadores.add(j);
//                }
//                i=i+1;
//            }
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
    
    }
    
    
    public OperacionesJuego siguientePaso(){
        return null;
    }
    
    
    
    public Boolean comprar(){
        Boolean ok=false;
        
        return ok;
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

