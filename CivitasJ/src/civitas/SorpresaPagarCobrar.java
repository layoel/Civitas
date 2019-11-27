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
public class SorpresaPagarCobrar extends Sorpresa {
    
    
    /**
     * @brief Constructor de todas las sorpresas restantes
     * @param tipo
     * @param valor
     * @param texto
     */
    SorpresaPagarCobrar(int valor, String texto){
        super();
        setValor(valor);
        setTexto(texto);
    }
    
/**
     * @brief si el jugador es correcto, 
     * se utiliza el método informe 
     * y se modifica el saldo del jugador actual(método modificarSaldo) con el valor de la sorpresa
     * @param actual indice del jugador que tiene el turno
     * @param todos lista de jugadores 
     */
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){

        if (jugadorCorrecto(actual, todos)){
            informe(actual, todos);
            todos.get(actual).modificarSaldo(getValor());
        }
    }
}
