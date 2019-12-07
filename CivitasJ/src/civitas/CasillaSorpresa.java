/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.ArrayList;

/**
 *
 * @author ELVIRA
 */
public class CasillaSorpresa extends Casilla{
       
    
    /**
     * @brief constructor de casilla sorpresa
     * @param mazo de cartas sorpresa
     * @param nombre
     */ 
    CasillaSorpresa (MazoSorpresas mazo, String nombre){
        super(nombre);
        super.setMazo (mazo);
    }
    
    
    
    @Override
    void recibeJugador(int actual, ArrayList<Jugador>todos){
        if(jugadorCorrecto(actual, todos)){
            System.out.println("Has caido en una casilla sorpresa!!");
            setSorpresa (getMazo().siguiente());
            informe(actual, todos);
            getSorpresa().aplicarAJugador(actual,todos);
        }
    }
    
 /**
     * @brief represente con detalle la información acerca de la casilla
     */
    @Override
    public String toString(){
        String text = super.toString() +
        "\n LA SORPRESA CONTIENE: " + getSorpresa().toString();
        //if(mazo != null) //mazo no tiene tostring
        //    text = text + "\n mazo: " + mazo.toString();       
        return text;
    }       
    
}
