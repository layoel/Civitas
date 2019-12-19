/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import GUI.CapturaNombres;
import GUI.CivitasView;
import GUI.Controlador;
import GUI.Dado;
import java.util.ArrayList;

/**
 *
 * @author ecast
 */
public class TestP5 {
    public static void main (String args[]){
        CivitasView civi = new CivitasView();
        Dado.createInstance(civi);
        Dado da = Dado.getInstance();
        da.setDebug(true);
        
        CapturaNombres jugadores = new CapturaNombres(civi, true);
        
        ArrayList<String> nombres = new ArrayList<>();
        nombres = jugadores.getNombres();
        
        Civitas juego = new Civitas(nombres);
        
        Controlador controlador = new Controlador(juego, civi);
        
        civi.setCivitas(juego);
        
        controlador.juega();
    }
    
}
