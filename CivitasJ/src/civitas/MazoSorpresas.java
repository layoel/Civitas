/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author ELVIRA
 * 
 * @brief Representa el mazo de cartas sorpresa, almacena las cartas
 * las instancias velan porque el mazo se mantenga consistente a lo largo del
 * juego y que se produzcan las operaciones de barajado cuando se han usado
 * todas las cartas.
 * 
 * @param sorpresas almacenar las cartas Sorpresa
 * @param barajada  indicar si ha sido barajado o no.
 * @param usadas contar el número de cartas del mazo que han sido ya usadas
 * @param debug activar/desactivar el modo depuración. Cuando está activo 
 * este atributo, el mazo no se baraja, permitiendo ir obteniendo las sorpresas
 * siempre en el mismo orden en el que se añaden
 * @param cartasEspeciales  Este atributo almacenará la carta sorpresa del 
 * tipo SALIRCARCEL mientras se considere retirada del mazo (y por tanto en 
 * posesión de un jugador)
 * @param ultimaSorpresa guardar la última sorpresa que ha salido. 
 * 
 */
public class MazoSorpresas {
    
    private ArrayList<Sorpresa> sorpresas;
    private Boolean barajada;
    private int usadas;
    private Boolean debug;
    private ArrayList<Sorpresa> cartasEspeciales;
    private Sorpresa ultimaSorpresa;
    
    /**
     * @brief inicia sorpresas y cartas especiales barajada y usadas 
     */
    private void init(){
        
        sorpresas = new ArrayList<>();
        cartasEspeciales = new ArrayList<>();
        barajada = false;
        usadas = 0;
        
    }
    
    /**
     * @brief constructor
     */
    MazoSorpresas(){
        
        init();
        debug = false;
            
    }
    
    /**
     * @brief constructor
     */
    MazoSorpresas(Boolean d){
        debug = d;
        init();
        if (debug){
        //informo a traves de diario
        }
    
    }
    
    /**
     *@brief añade la sorpresa si el mazo no ha sido barajado
     * @param s sorpresa que se añade
     */
    void alMazo(Sorpresa s){
    
        if (!barajada && !debug)
            sorpresas.add(s);
        
    }
    
    /**
     * @brief coger una carta sorpresa
     */
    Sorpresa siguiente(){
    
        Sorpresa sp = new Sorpresa();
        
        if (!debug)
            if (!barajada || (usadas == sorpresas.size())){
                Collections.shuffle(sorpresas); //baraja las cartas
                usadas = 0;
                barajada = true;
            }
        usadas = usadas+1;
        
        sp = sorpresas.get(0);
        sorpresas.remove(0);
        sorpresas.add(sp);
        ultimaSorpresa = sp;
        
        return sp;
    }
    
    /**
     * @brief comprueba si una carta especial esta en el mazo 
     * @param sorpresa carta especial
     */
    void inhabilitarCartaEspecial(Sorpresa sorpresa){
    
        for(int i=0; i<sorpresas.size(); i=i+1)
            if(sorpresas.get(i)== sorpresa){
                Sorpresa sp = new Sorpresa();
                sp = sorpresas.get(i);
                sorpresas.remove(i);
                cartasEspeciales.add(sp);
                Diario di = Diario.getInstance();//deja constancia en el diario
                di.ocurreEvento("inhabilitada carta especial "+sp);
            }
    }
    
    /**
     * @brief comprueba si una carta es especial
     * @param sorpresa carta sorpresa
     */
    void habilitarCartaEspecial(Sorpresa sorpresa){
        
        for(int i=0; i<cartasEspeciales.size(); i=i+1)
            if(cartasEspeciales.get(i)== sorpresa){
                Sorpresa sp = new Sorpresa();
                sp = cartasEspeciales.get(i);
                cartasEspeciales.remove(i);
                sorpresas.add(sp);
                Diario di = Diario.getInstance();//deja constancia en el diario
                di.ocurreEvento("habilitada carta especial "+sp);
            }
    }

    
    
}
