# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

class Controlador
    
    def initialize (juego, vista)
        @juego = juego 
        @vista = vista 
    end
    
#    /*
#    * @brief se encarga de todo el desarrollo de una partida
#    */
    def juega
        @vista.setCivitas(@juego)  #//Indicar a la @vista que muestre el estado del @juego actualizado 
        while(!@juego.finalDelJuego)  #//Mientras no se haya producido el final del juego
           puts "\n \n ****** ACTUALIZANDO INFORMACIÓN DEL JUGADOR ACTUAL ******\n \n"
           @vista.actualizarVista    #//muestre el estado del juego en cada momento
           @vista.pausa               #// hara que el juego espere la interaccion del usuario entre turno y turno.
           operacion = @juego.siguientePaso 
           @vista.mostrarSiguienteOperacion(operacion)  #//muestre la siguiente operación que se va a realizar
            if (operacion != OperacionesJuego::PASAR_TURNO)
               @vista.mostrarEventos  #//mostrar los eventos pendientes del diario.
            end
            if(!@juego.finalDelJuego) #//si no he llegado al final del@juego
                
                if (operacion == OperacionesJuego::COMPRAR) #//Si la operación es comprar
                    respuesta = @vista.comprar()  
                    if(respuesta == Respuestas::SI)         #//compra
                       @juego.comprar() 
                    end
                    @juego.siguientePasoCompletado(operacion)  #// se ha completado el siguiente paso
                end
                
                if(operacion == OperacionesJuego::GESTIONAR) #//Si la operación es gestionar
                    @vista.gestionar()  #//indicar a la@vista que ejecute el método gestionar
                    lista_GestionesInmobiliarias =[GestionesInmobiliarias::VENDER, GestionesInmobiliarias::HIPOTECAR, GestionesInmobiliarias::CANCELAR_HIPOTECA, GestionesInmobiliarias::CONSTRUIR_CASA, GestionesInmobiliarias::CONSTRUIR_HOTEL , GestionesInmobiliarias::TERMINAR]
                    gestion = GestionesInmobiliarias[@vista.getGestion()]  #//se consultan los indices de gestion
                    propiedad = @vista.getPropiedad()  #//se consultan los índices de propiedad
                    opInmobiliaria = OperacionInmobiliaria.new(gestion,propiedad)  #//se crea un objeto OperacionInmobiliaria
                    case (opInmobiliaria.getGestion) #//se llama al método del modelo correspondiente a la gestion elegida pasando el indice de propiedad
                      when VENDER then
                           @juego.vender(propiedad) 
                      when HIPOTECAR then
                           @juego.hipotecar(propiedad) 
                      when CANCELAR_HIPOTECA then
                           @juego.cancelarHipoteca(propiedad) 
                      when CONSTRUIR_CASA then
                           @juego.construirCasa(propiedad) 
                      when CONSTRUIR_HOTEL then
                           @juego.construirHotel(propiedad) 
                      else #// si es TERMINAR se llama a siguiente paso completado
                           @juego.siguientePasoCompletado(operacion) 
                    end
                end
                
                if(operacion == OperacionesJuego::SALIR_CARCEL) #//Si la operación es intentar salir de la carcel
                   salida = @vista.salirCarcel()  #//ejecute el metod asociado a la eleccion de metodo de salida
                   if (salida == SalidasCarcel::PAGANDO)
                      @juego.salirCarcelPagando() 
                   end
                   if(salida == SalidasCarcel::TIRANDO)
                      @juego.salirCarcelTirando() 
                   end
                  @juego.siguientePasoCompletado(operacion)     #//Indicar al modelo que se ha completado el siguiente paso          
                end
            end
        end
       @juego.ranking()  #//final del@juego se mostrara un ranking de los jugadores
    end
    

end
