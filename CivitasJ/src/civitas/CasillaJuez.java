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
public class CasillaJuez extends Casilla{
    
    
    /** 
     * @brief constructor de casilla juez
     * @param numcarcel numero de la casilla carcel
     * @param nombre el nombre de la casilla
     */ 
    CasillaJuez (int numCarcel, String nombre){
        super(nombre);
        setCarcel(numCarcel);
        //tipo = TipoCasilla.JUEZ;
    }
    
    /**
     * @brief Se encarcela al jugador actual
     * @param actual
     * @param todos
     */
    @Override
    void recibeJugador(int actual, ArrayList<Jugador>todos){
        if(jugadorCorrecto(actual, todos)){
            System.out.println("El Juez te manda a la carcel");
            informe(actual,todos);
            todos.get(actual).encarcelar(Casilla.getCarcel());  
        }
    }
            
    
    /**
     * @brief represente con detalle la información acerca de la casilla
     */
    
    @Override
    public String toString(){
        String text = super.toString();
        
        text = text +
               "\n El tipo : JUEZ";
        text = text +"\n carcel: "+ Integer.toString(getCarcel());
              
        return text;
    }   
    
}
