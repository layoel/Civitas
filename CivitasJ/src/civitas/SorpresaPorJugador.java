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
public class SorpresaPorJugador extends Sorpresa{
    
    
        /**
     * @brief Constructor de todas las sorpresas restantes
     * @param tipo
     * @param valor
     * @param texto
     */
    SorpresaPorJugador(int valor, String texto){
        super();
        setValor(valor);
        setTexto(texto);
    }


    
    /**
     * @brief El jugador da dinero al jugador actual
     * si el jugador es actual es correcto, 
     * se utiliza el método informe
     * ◦ se crea una sorpresa de tipo PAGARCOBRAR con el valor de la sorpresa multiplicado por -1 
     * ◦ y se aplica a todos los jugadores menos el actual
     * ◦ se crea una sorpresa de tipo PAGARCOBRAR con el valor de la sorpresa multiplicado 
     * por el número de jugadores excluyendo al actual y se aplica solo al jugador actual.
     * @param actual indice del jugador que tiene el turno
     * @param todos lista de jugadores 
     */
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){

        if (jugadorCorrecto(actual, todos)){
            informe(actual, todos);
            Sorpresa s;
            if (getValor()<0)
                s = new SorpresaPagarCobrar(getValor()*-1, "Jugador actual hace una donación ");
            else
                s = new SorpresaPagarCobrar(getValor()*-1, "Jugador actual recibe una donación gracias ");
            for (int i=0; i<todos.size(); i++){
                if(i != actual)
                    s.aplicarAJugador(i,todos);
            }
            Sorpresa s1;
            if(getValor()<0)
                s1 = new SorpresaPagarCobrar(getValor()*(todos.size()-1), "Has hecho una donación.");
            else
                s1= new SorpresaPagarCobrar(getValor()*(todos.size()-1), "Has recibido una donación."); 
            s1.aplicarAJugador(actual,todos);
            
        }
    }

}

