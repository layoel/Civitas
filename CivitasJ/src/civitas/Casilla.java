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
 * @Bief Clase casilla temporal
 */
public class Casilla {
    static private int carcel;
    private float importe;
    private String nombre;
    TipoCasilla tipo;
    TituloPropiedad tiutloPropiedad;
    TipoSorpresa sorpresa;
    MazoSorpresas mazo;
    
/**
 * @brief constructor de la clase casilla
 * @param n nombre de la casilla 
 */   
    Casilla(String n){
        this.nombre = n;
    }
    
    
    Casilla (TituloPropiedad titulo){
    
    }
    
    Casilla (float cantidad, String nombre){
    
    }
    
    Casilla (int numCarcel, String nombre){
    
    }
    
    Casilla (MazoSorpresas mazo, String nombre){
    
    }
    
    
/**
 * @brief Consultor de nombre
 * @return devuelve el nombre
 */
    public String getNombre(){
        return nombre;
    }
    
    TituloPropiedad getTituloPropiedad(){
        
    }
    
    private void informe(int actual, ArrayList<Jugador> todos){
    
    }
    
    private void init(){
    
    }
    
    public Boolean jugadorCorrecto(int actual, ArrayList<Jugador> todos){
    
    }
    
    void recibeJugador(int actual, ArrayList<Jugador>todos){
        
    }
    
    void recibeJugador_calle(int actual, ArrayList<Jugador>todos){
        
    }
    void recibeJugador_impuesto(int actual, ArrayList<Jugador>todos){
        
    }
    void recibeJugador_juez(int actual, ArrayList<Jugador>todos){
        
    }
    void recibeJugador_sorpresa(int actual, ArrayList<Jugador>todos){
        
    }
    public String toString(){
    
    }
    
    
    
}
