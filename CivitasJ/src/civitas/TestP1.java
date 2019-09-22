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
public class TestP1 {
    public static void main (String args[]){
    
        Dado d = Dado.getInstance(); //creo el dado
        
        /*llama 100 veces al método  quienEmpieza() de Dado considerando que 
        hay 4 jugadores, y calcula cuantas veces se obtiene cada uno de los 
        valores posibles. Comprueba si se cumplen a nivel práctico las 
        probabilidades de cada valor
        */
         
        ArrayList<Integer> jugador = new ArrayList<Integer>();
        
        for (int i=0; i<100; i++){
            jugador.add(d.quienEmpieza(4));
        }
        int j0 = 0;
        int j1 = 0;
        int j2 = 0;
        int j3 = 0;
        
        for(int i: jugador){
            if(i == 0)
                j0 = j0 + 1;
            if(i == 1)
                j1 = j1 + 1;
            if(i == 2)
                j2 = j2 + 1;
            if(i == 3)
                j3 = j3 + 1;
        }
        
        System.out.println("el jugador0 empieza "+j0+" veces");
        System.out.println("el jugador1 empieza "+j1+" veces");
        System.out.println("el jugador2 empieza "+j2+" veces");
        System.out.println("el jugador3 empieza "+j3+" veces");
        
        int media = (j0+j1+j2+j3)/4;
        
        System.out.println("la media es"+media);

    } 
}
