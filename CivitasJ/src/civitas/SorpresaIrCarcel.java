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
public class SorpresaIrCarcel extends Sorpresa{
    
    
    
    /**
     * @brief Constructor de la sorpresa que envia a la carcel
     * @param tablero
     */
    SorpresaIrCarcel(Tablero tablero){
       super();
       setTablero(tablero);
       setTexto("Vas a la carcel");
    }
    

    /**
     * @brief Si el jugador es correcto, 
     * se utiliza el método informe y 
     * se encarcela al jugador (método encarcelar) indicado
     * @param actual indice del jugador que tiene el turno
     * @param todos lista de jugadores 
     */
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){

        if (jugadorCorrecto(actual, todos)){
            informe(actual, todos);
            todos.get(actual).encarcelar(getTablero().getCarcel());
        }
    }
    
}
