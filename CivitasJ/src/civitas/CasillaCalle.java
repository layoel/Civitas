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
public class CasillaCalle extends Casilla{
    
    
    /**
     * @brief constructor de casilla calle
     * @param titulo de la propiedad 
     */ 
    CasillaCalle (TituloPropiedad titulo, String nombre){
        super(nombre);
        setTituloPropiedad (titulo);
        setNombre(getTituloPropiedad().getNombre());
    }


    @Override
     void recibeJugador(int actual, ArrayList<Jugador>todos){
        if(jugadorCorrecto(actual, todos)){
            informe(actual, todos);
            
            if(!getTituloPropiedad().tienePropietario()){
                todos.get(actual).puedeComprarCasilla();   
            }else{
                getTituloPropiedad().tramitarAlquiler(todos.get(actual));
            }
        }    
    }

    @Override
    public String toString(){
        String text = super.toString() +  "\n El tipo : CALLE"; 
        text = text + "\n tutiloPropiedad" + getTituloPropiedad().toString();
        return text;
    }    
}
