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
public class CasillaImpuesto extends Casilla{
    
        
        /**
     * @brief constructor de casilla impuesto
     * @param cantidad 
     * @param nombre de la casilla 
     */ 
    CasillaImpuesto (float cantidad, String nombre){
        super(nombre);
        setImporte (cantidad);
    }
    
    
     /**
     * @brief El jugador paga un impuesto por el valor que indica la casilla
     * @param actual
     * @param todos
     */
    @Override
    void recibeJugador(int actual, ArrayList<Jugador>todos){
        if(jugadorCorrecto(actual,todos)){
            informe(actual,todos);
            todos.get(actual).pagaImpuesto(getImporte());  
        } 
    }  

    
    @Override
    public String toString(){
          String text = super.toString()+  "\n El tipo : IMPUESTO";
          text = text + "\n el importe a pagar: "+ Float.toString(getImporte());
          return text;
    }


}
