/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import civitas.Civitas;
import civitas.EstadosJuego;

/**
 *
 * @author ELVIRA
 */
public class Controlador {
    private Civitas juego;
    private CivitasView vista;
    
    public Controlador(Civitas juego, CivitasView vista){ //cambio de paquete a public para poder usarlo en prueba que esta en civitas
        this.juego = juego;
        this.vista = vista;
    }
    
    /*
    * @brief se encarga de todo el desarrollo de una partida
    */
    public void juega(){ //cambio de paquete a public para poder usarlo en prueba que esta en civitas
        vista.setCivitas(juego); //Indicar a la vista que muestre el estado del juego actualizado 
        while(!juego.finalDelJuego()){  //Mientras no se haya producido el final del juego
            System.out.println("\n \n ****** ACTUALIZANDO INFORMACIÓN DEL JUGADOR ACTUAL ******\n \n");
            vista.actualizarVista();    //muestre el estado del juego en cada momento
            //vista.pausa();              // hara que el juego espere la interaccion del usuario entre turno y turno.
            OperacionesJuego operacion = juego.siguientePaso();
            vista.mostrarSiguienteOperacion(operacion); //muestre la siguiente operación que se va a realizar
            if (operacion != OperacionesJuego.PASAR_TURNO)
                vista.mostrarEventos(); //mostrar los eventos pendientes del diario.
            if(!juego.finalDelJuego()){ //si no he llegado al final del juego
                
                if (operacion == OperacionesJuego.COMPRAR){ //Si la operación es comprar
                    Respuestas respuesta = vista.comprar(); 
                    if(respuesta == Respuestas.SI)         //compra
                        juego.comprar();
                    juego.siguientePasoCompletado(operacion); // se ha completado el siguiente paso
                    
                        
                }   
                
                if(operacion == OperacionesJuego.GESTIONAR){ //Si la operación es gestionar
                    vista.gestionar(); //indicar a la vista que ejecute el método gestionar
                    GestionesInmobiliarias gestion = GestionesInmobiliarias.values()[vista.getGestion()]; //se consultan los indices de gestion
                    int propiedad = vista.getPropiedad(); //se consultan los índices de propiedad
                    OperacionInmobiliaria opInmobiliaria = new OperacionInmobiliaria(gestion,propiedad); //se crea un objeto OperacionInmobiliaria
                    switch(opInmobiliaria.getGestion()){ //se llama al método del modelo correspondiente a la gestion elegida pasando el indice de propiedad
                        case VENDER:
                            juego.vender(propiedad);
                        break;
                        case HIPOTECAR:
                            juego.hipotecar(propiedad);
                            break;
                        case CANCELAR_HIPOTECA:
                            juego.cancelarHipoteca(propiedad);
                            break;
                        case CONSTRUIR_CASA:
                            juego.construirCasa(propiedad);
                            break;
                        case CONSTRUIR_HOTEL:
                            juego.construirHotel(propiedad);
                            break;
                        default: // si es TERMINAR se llama a siguiente paso completado
                            juego.siguientePasoCompletado(operacion);
                    }
                }
                
                if(operacion == OperacionesJuego.SALIR_CARCEL){ //Si la operación es intentar salir de la carcel
                   SalidasCarcel salida =  vista.salirCarcel(); //ejecute el metod asociado a la eleccion de metodo de salida
                   if (salida == SalidasCarcel.PAGANDO)
                       juego.salirCarcelPagando();
                   if(salida == SalidasCarcel.TIRANDO)
                       juego.salirCarcelTirando();
                   juego.siguientePasoCompletado(operacion);    //Indicar al modelo que se ha completado el siguiente paso          
                }
            } 
        }
        juego.ranking(); //final del juego se mostrara un ranking de los jugadores
    }
    
}
