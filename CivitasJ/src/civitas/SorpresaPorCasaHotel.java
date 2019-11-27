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
public class SorpresaPorCasaHotel extends Sorpresa {
    
    
    /**
     * @brief Constructor de todas las sorpresas restantes
     * @param valor
     * @param texto
     */
    SorpresaPorCasaHotel(int valor, String texto){
        super();
        setValor(valor);
        setTexto(texto);
    }
    
    
/**
     * @brief si el jugador es correcto,
     * se utiliza el método informe
     * y se modifica el saldo del jugador actual(método modificarSaldo) 
     * con el valor de la sorpresa multiplicado por el número de casas 
     * y hoteles del jugador.
     * @param actual indice del jugador que tiene el turno
     * @param todos lista de jugadores 
     */
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        
        if (jugadorCorrecto(actual, todos)){
            informe(actual, todos);
            todos.get(actual).modificarSaldo(getValor()*todos.get(actual).cantidadCasasHoteles());
        }
    }
}
