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
        
        /*
        Llama 100 veces al método  quienEmpieza() de Dado considerando que 
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
        
        for(int i: jugador){ //es un for each i toma el valor de cada elemento de jugador en cada iteracion
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

        /*
        Asegúrate de que funciona el modo debug del dado activando y 
        desactivando ese modo, y realizando varias tiradas en cada modo.        
        */
        d.setDebug(Boolean.TRUE);
        System.out.println("tirando dado: " + d.tirar());
        d.setDebug(Boolean.FALSE);
        System.out.println("tirando dado: " + d.tirar());
        
        /*
        Prueba al menos una vez los métodos getUltimoResultado() y 
        salgoDeLaCarcel() de Dado.
        */
        System.out.println("el ultimo resultado tras tirar el dado es: " + d.getUltimoResultado());
        System.out.println("¿Salgo de la carcel?"+d.salgoDeLaCarcel());
        
        /*
        Muestra al menos un valor de cada tipo enumerado
        */
        System.out.println(EstadosJuego.INICIO_TURNO );
        System.out.println(EstadosJuego.DESPUES_CARCEL );
        System.out.println(EstadosJuego.DESPUES_AVANZAR );
        System.out.println(EstadosJuego.DESPUES_COMPRAR );
        System.out.println(EstadosJuego.DESPUES_GESTIONAR );
        
        System.out.println(TipoCasilla.CALLE );
        System.out.println(TipoCasilla.SORPRESA );
        System.out.println(TipoCasilla.JUEZ );
        System.out.println(TipoCasilla.IMPUESTO );
        System.out.println(TipoCasilla.DESCANSO );
        
        System.out.println(TipoSorpresa.IRCARCEL );
        System.out.println(TipoSorpresa.IRCASILLA );
        System.out.println(TipoSorpresa.PAGARCOBRAR );
        System.out.println(TipoSorpresa.PORCASAHOTEL );
        System.out.println(TipoSorpresa.PORJUGADOR );
        System.out.println(TipoSorpresa.SALIRCARCEL );


        /*
        Crea un objeto MazoSorpresas y haz las siguientes pruebas: 
        añade dos sorpresas al mazo,
        obtén la siguiente sorpresa en juego,
        inhabilita y
        habilita la segunda carta añadida. 
        Dado que MazoSorpresas
        */
        Sorpresa s = new Sorpresa();
        Sorpresa s2 = new Sorpresa();
        Sorpresa s3 = new Sorpresa();
        
        MazoSorpresas m = new MazoSorpresas();
        
        m.alMazo(s);
        m.alMazo(s2);
        
        s3 = m.siguiente();
        
        m.inhabilitarCartaEspecial(s2);
        m.habilitarCartaEspecial(s2);
        
        /*
        usa la clase Diario, aprovecha y prueba todos los métodos de Diario.
        */
        Diario di = Diario.getInstance();
            //ocurreEvento
        di.ocurreEvento("evento1");
        di.ocurreEvento("evento2");
            //eventosPendientes
        for(int i=0; i<4; i++){
            if(di.eventosPendientes()){
                System.out.println("si hay eventos");
                System.out.println(di.leerEvento()); //leerEventos
            }else
                System.out.println("no hay eventos");
        }
            
        /*
        Crea un tablero, añádele varias casillas y comprueba con el depurador 
        que efectivamente la estructura del mismo es la que esperabas. Intenta 
        provocar las situaciones erróneas controladas en la clase Tablero (por 
        ejemplo, que la posición de la cárcel sea mayor que el tamaño del 
        tablero) y comprueba que la gestión de las mismas es la correcta. 
        Finalmente,realiza distintas tiradas con el dado y asegúrate de que se
        calcula correctamente la posición de destino en el tablero.
        */
    } 
}
