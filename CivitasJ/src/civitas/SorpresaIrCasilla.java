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
public class SorpresaIrCasilla extends Sorpresa{
    
    
    
    /**
     * @brief Constructor sorpresa que lleva a otra casilla
     * @param tablero
     * @param valor
     * @param texto
     */
    SorpresaIrCasilla(Tablero tablero, int valor, String texto){
       super();
       setTablero(tablero);
       setValor(valor);
       setTexto(texto);
    }

    
    /**
     * @brief si el jugador es correcto, 
     * ◦ se utiliza el método informe y obtiene la casilla actual del jugador
     * ◦ se calcula la tirada utilizando el método calcularTirada(casillaActual, valor) del tablero.
     * ◦ se obtiene la nueva posición del jugador con el método nuevaPosicion(casillaActual,tirada) del tablero.
     * ◦ se mueve al jugador a esa nueva posición (método moverACasilla)
     * ◦ se indica a la casilla que está en la posición del valor de la sorpresa que reciba al jugador (método recibeJugador)
     * @param actual indice del jugador que tiene el turno
     * @param todos lista de jugadores 
     */
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){

        if (jugadorCorrecto(actual, todos)){
            informe(actual, todos);
            int casillaActual = todos.get(actual).getNumCasillaActual();
            int tirada = getTablero().calcularTirada(casillaActual, getValor());
            int nuevaPos = getTablero().nuevaPosicion(casillaActual, tirada);
            todos.get(actual).moverACasilla(nuevaPos);
            getTablero().getCasilla(nuevaPos).recibeJugador(actual, todos);
        }
    }
}

