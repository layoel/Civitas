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
    private TipoSorpresa sorpresa;
    private MazoSorpresas mazo;
    
    /**
     * @brief constructor de casilla descanso
     * @param n nombre de la casilla 
     */   
    Casilla(String n){
        init();
        nombre = n;
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
    
    
    
    /** ¿seguro que es casilla carcel y no casilla juez?? cosas raras del guion?
     * @brief constructor de casilla juez
     * @param nuncarcel numero de la casilla carcel
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
        carcel= 0;
        importe = 0;
        nombre = "Casilla Descanso";
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
        
        text = "carcel: "+ Integer.toString(carcel)+ "\nel importe: "+ 
                Float.toString(importe)+"\n nombre: "+ nombre +
                "\n El tipo : " +tipo.toString() + "\n tutiloPropiedad" +
                tituloPropiedad.toString() + "\n sorpresa: " + sorpresa.toString() 
                + "\n mazo: " + mazo.toString();       
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
//    void recibeJugador(int actual, ArrayList<Jugador>todos){
//        
//    }
//    
//    void recibeJugador_calle(int actual, ArrayList<Jugador>todos){
//        
//    }
//    
//    void recibeJugador_sorpresa(int actual, ArrayList<Jugador>todos){
//        
//    }
    
    
    
    
}
