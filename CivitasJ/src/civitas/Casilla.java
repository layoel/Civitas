/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

/**
 *
 * @author ecastillo
 * 
 * @Bief Clase casilla temporal
 */
public class Casilla {
    private String nombre;
    
/**
 * @brief constructor de la clase casilla
 * @param n nombre de la casilla 
 */   
    Casilla(String n){
        this.nombre = n;
    }
    
    
/**
 * @brief Consultor de nombre
 * @return devuelve el nombre
 */
    String getNombre(){
        return nombre;
    }
}
