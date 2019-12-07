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
public class SorpresaSalirCarcel extends Sorpresa{
    
        /**
     *@brief Constructor para la sorpresa evitar la carcel
     * @param tipo
     * @param mazo
     */
    SorpresaSalirCarcel( MazoSorpresas mazo){
        super();
        setTexto("Tienes un salvoconducto");
        setMazo(mazo);
        
    }
    
    

    
    
     /**
     * @brief si el tipo de la sorpresa es la que evita la cárcel, 
     * inhabilita la carta especial en el mazo de sorpresas.
     */
    void salirDelMazo(){
        getMazo().inhabilitarCartaEspecial(this);
    }
    
    
    /**
     * @brief si el tipo de la sorpresa es la que evita la cárcel, 
     * habilita la carta especial en el mazo de sorpresas.
     */
    void usada(){
            getMazo().habilitarCartaEspecial(this);
    }

    /**
     * @brief si el jugador es correcto, 
     * se utiliza el método informe 
     * y se pregunta a todos los jugadores si alguien tiene la sorpresa para evitar la cárcel (método tieneSalvoconducto).
     * Si nadie la tiene, la obtiene el jugador actual (método obtenerSalvoconducto) 
     * y se llama al método salirDelMazo.
     */
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){

        if (jugadorCorrecto(actual, todos)){
            informe(actual, todos);
            Boolean latienen = false;
            for (int i=0; i< todos.size() && !latienen ;i++ ){
                if(todos.get(i).tieneSalvoconducto())
                    latienen = true;
            }
            if(!latienen){
                Sorpresa s;
                s = new SorpresaSalirCarcel(getMazo());
                todos.get(actual).obtenerSalvoconducto(s); 
                ((SorpresaSalirCarcel)s).salirDelMazo();
            }
        }
    }



}

