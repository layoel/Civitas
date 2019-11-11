/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import civitas.Civitas;
import java.util.ArrayList;
import juegoTexto.Controlador;
import juegoTexto.VistaTextual;

/**
 *
 * @author ELVIRA
 */
public class Prueba {
    private VistaTextual vista;
    private Civitas juego;
    
    Prueba(){
        ArrayList<String> jugadores= new ArrayList<>();
        jugadores.add("Elvira");
        jugadores.add("Ale");
        //jugadores.add("Jolu");
        juego = new Civitas(jugadores);
        vista = new VistaTextual();
    } 

    public VistaTextual getVista() {
        return vista;
    }

    public Civitas getJuego() {
        return juego;
    }
    
    
    
    public static void main(String args[]){
        Dado d= Dado.getInstance();
        d.setDebug(Boolean.TRUE); //comentar para probar el juego completo
        //d.setDebug(Boolean.FALSE);// descomentar para probar el juego completo
        Prueba p1= new Prueba();
       
        Controlador control = new Controlador(p1.getJuego(), p1.getVista());
        control.juega();
    }
}


