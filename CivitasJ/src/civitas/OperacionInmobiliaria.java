/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

/**
 *
 * @author ELVIRA
 */
public class OperacionInmobiliaria {
    private int numPropiedad;
    private GestionesInnmobiliarias gestion;

    public OperacionInmobiliaria(GestionesInnmobiliarias gestion, int numPropiedad) {
        this.numPropiedad = numPropiedad;
        this.gestion = gestion;
    }

    public int getNumPropiedad() {
        return numPropiedad;
    }

    public GestionesInnmobiliarias getGestion() {
        return gestion;
    }
    
    
}
