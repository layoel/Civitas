/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitasj;

import java.util.ArrayList;

/**
 *
 * @author ecastillo
 */
public class Tablero {
    /*los atributos son pivados*/
    private int numCasillaCarcel;
    private  ArrayList<Casilla> casillas;
    private int porSalida;
    private Boolean tieneJuez;
    
    private static String nombre;
    /**
     * Constructor
     * @param c indice de la casilla de la carcel
    */
    Tablero(int c){
        
        if (c >=1 )
            numCasillaCarcel = c;
        else
            numCasillaCarcel = 1;
        
        casillas = new ArrayList<Casilla>();
        casillas.add(Salida);
        porSalida = 0;
        tieneJuez = false; 
    }
    
    Boolean correcto(){
    
    }
    
    Boolean correcto(int numCasilla){
    
    }
    
    int getCarcel(){
        return numCasillaCarcel;
    }
    
    int getPorSalida(){
    
    }
    
    void aniadeCasilla(){
    
    }
    
    void aniadeJuez(){
    
    }
    
    Casilla getCasilla(int numCasilla){
       
    }
    
    int nuevaPosicion(int actual, int tirada){
    
    }
    
    int calcularTirada(int origen, int destino){
    
    }
    
    
            
    
    
}
