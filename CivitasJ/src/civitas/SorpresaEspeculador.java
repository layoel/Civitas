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
public class SorpresaEspeculador extends Sorpresa {
    
        
    /**
     * @brief Constructor de la sorpresa que envia a la carcel
     * @param tablero
     */
    SorpresaEspeculador(int valor, String texto){
       super();
       setTexto(texto);
       setValor(valor);
    }  
       
       
     /**
     * @brief Si el jugador es correcto, 
     * se utiliza el método informe y 
     * se convierte al juegador en especulador
     * @param actual indice del jugador que tiene el turno
     * @param todos lista de jugadores 
     */
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){

        if (jugadorCorrecto(actual, todos)){
            informe(actual, todos);
            Jugador especulador = new JugadorEspeculador(todos.get(actual), getValor());
            todos.set(actual, especulador);
        }
    }  
    
    
    
      
    
}

