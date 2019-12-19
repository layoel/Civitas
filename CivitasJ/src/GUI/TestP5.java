/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import civitas.Civitas;
import java.util.ArrayList;

/**
 *
 * @author ELVIRA
 */
public class TestP5 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CivitasView civi= new CivitasView();
        Dado.createInstance(civi); //crear la instancia
        Dado da = Dado.getInstance();
        da.setDebug(true);
        
        CapturaNombres cn = new CapturaNombres(civi, true);
        
        ArrayList<String> nombres = new ArrayList<>();
        nombres = cn.getNombres();
        
        Civitas juego = new Civitas(nombres);
        
        Controlador controlador = new Controlador(juego,civi);
        civi.setJuego(juego);
        controlador.juega();
        //civi.actualizarVista();
        
    }
    
    
}
