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
 */

/**
 *@brief Representa el tablero del juego imponiendo restricciones 
 * sobre el mismo en las reglas del juego
 * 
 * \param numCasillaCarcel el numero de la casilla donde se encuentrala carcel
 * \param casillas contenedor de las casillas del juego
 * \param porSalida numero de veces que se ha pasado por la salida en un turno
 * \param tieneJuez si el tablero tiene o no casilla de ese tipo
 */

public class Tablero {
    /*los atributos son pivados*/
    private int numCasillaCarcel;
    private ArrayList<Casilla> casillas;
    private int porSalida;
    private Boolean tieneJuez;
    
    private static String nombre;
   
    /**
     * @brief Constructor
     * \param c indice de la casilla de la carcel
    */
    
    Tablero(int c){
        
        if (c >=1 )
            numCasillaCarcel = c;
        else
            numCasillaCarcel = 1;
        
        //creo lista de casillas
        casillas = new ArrayList<Casilla>();
        //creo una casilla llamada Salida
        Casilla Salida;
        Salida = new Casilla("Salida");
  
        //añádo salida a casillas
        casillas.add(Salida);
       
        porSalida = 0;
        tieneJuez = false; 
    }
    
    
    /**
     * @brief Consulta si el numero de elementos en casillas es mayor que el indice
     * de la casilla de la carcel y que se dispone de una casilla tipo juez
     * @return true si el tablero es correcto y puede usarse para jugar
     */
    Boolean correcto(){
        
        Boolean juegoOK = false;
        
        if ((numCasillaCarcel < casillas.size()) && tieneJuez)
            juegoOK = true;
        
        return juegoOK;
    }
    
    
    /**
     * @brief comprueba si el tablero es correcto para jugar 
     * @param numCasilla indice válido para acceder a elementos de casillas
     * @return true si se puede jugar y el indice es valido
     */
    Boolean correcto(int numCasilla){
        
        Boolean ok = false;
        
        if (correcto() && numCasilla < casillas.size())
            ok = true;
        
        return ok;
            
    }
    
    
    /**
     * @brief Consultor de numero de casilla carcel
     * @return numCasillaCarcel
     */
    int getCarcel(){
        
        return numCasillaCarcel;
    
    }
    
    
    
    /**
     * @brief numero de veces que se pasa por salida
     * @return porSalida
     */
    int getPorSalida(){
        
        if (porSalida > 0)
            porSalida = porSalida-1;
        
        return porSalida;
        
    }
    
    
    /**
     * @brief añade a casillas la casilla c
     * @param c casilla que se va a añadir
     */
    void añadeCasilla( Casilla c){
        
        if (casillas.size()== numCasillaCarcel){
            Casilla carcel = new Casilla("Cárcel");
            casillas.add(carcel);
        }
        
        casillas.add(c);

    }
    
    /**
     * @brief añade la casilla juez si no esta aun
     */
    void añadeJuez(){
        
        if(!tieneJuez){
            Casilla juez = new Casilla("Juez");
            casillas.add(juez);
            tieneJuez = !tieneJuez;
        }
        
    }
   
     /**
     * @brief Comprueba si la casilla esta en el tablero
     * @return casilla si es correcto o null si no lo es
     */
    Casilla getCasilla(int numCasilla){
      
        if(correcto(numCasilla))
            return casillas.get(numCasilla);
        else
            return null; 
    }
    
    
    /**
     * @brief calcula la posicion en el tablero a partir de la actual y avanza 
     * una tirada de unidades
     * @param actual
     * @param tirada
     * @return -1 si el tablero no es correcto, posicion 
     */
    int nuevaPosicion(int actual, int tirada){
        
        int pos = -1;
        
        if(correcto()){
            if (actual+tirada > casillas.size())
                porSalida = porSalida+1;
            
            pos = (actual + tirada) % casillas.size();
        }
              
        return pos;
            
    }
    
    
    /**
     * @brief calcula el numero de casillas que avanza 
     * @param origen casilla donde estoy
     * @param destino casilla a la que llego
     * @return tirada de dado que se habria tenido que obtener para ir desde la 
     * casilla origen a la casilla destino 
     */
    int calcularTirada(int origen, int destino){
        
        int tirada = destino - origen;
        
        if(tirada < 0)
            tirada = tirada + casillas.size();
        
        return tirada;
    }            
    
    
}
