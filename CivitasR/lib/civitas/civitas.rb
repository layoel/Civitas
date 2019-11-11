# encoding: UTF-8

# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
#
require "byebug"
require_relative "casilla"
require_relative "dado.rb"
require_relative "diario.rb"
require_relative "estados_juego.rb"
require_relative "gestor_estados.rb"
require_relative "jugador.rb"
require_relative "mazo_sorpresas.rb"
require_relative "sorpresa.rb"
require_relative "tablero.rb"
require_relative "tipocasilla.rb"
require_relative "tiposorpresa.rb"
require_relative "titulo_propiedad.rb"
require_relative "gestor_estados.rb"

module Civitas
    class Civitas
      
#      /* *************AÑDIDO PARA USO DEL JUEGO EN LA P3****************************
#     Creo este método para poder consultarlo en la pausa del controlador y saber 
#     si inicia turno el jugador o esta en otro estado
#     */
      attr_reader :estado
      
  #    /**
  #     * @brief Constructor
  #     *      • Inicializar el atributo jugadores creando y aniadiendo un jugador 
  #     *          por cada nombre suministrado como parametro.
  #     *      • Crear el gestor de estados y fijar el estado actual como el estado
  #     *          inicial (metodo estadoInicial()) indicado por este gestor.
  #     *      • Inicializar el indice del jugador actual (que sera quien tenga el 
  #     *          primer turno). Para obtener ese valor se utilizara el metodo 
  #     *          adecuado del dado.
  #     *      • Crear el mazo de sorpresas
  #     *      • Llamar al metodo de inicializacion del tablero. 
  #     *      • Llamar al metodo de inicializacion del mazo de sorpresas. 
  #     */
      def initialize(nombres)
          @jugadores = Array.new
         # for i in 0..nombres.size do
         #   @jugadores.push(Jugador.new(nombres.at(i)))
          #end

          nombres.each do |nombre|
            @jugadores << Jugador.new(nombre)
          end
        
          @gestorEstados = Gestor_estados.new
          @estado = @gestorEstados.estado_inicial

          @indiceJugadorActual = Dado.instance.quienEmpieza(@jugadores.size) 
          
          @mazo = MazoSorpresas.new(Dado.instance.debug)
 
          inicializarTablero(@mazo)
          
          inicializarMazoSorpresas(@tablero)
      end

      
      
#    /** Metodo eliminado en este juego dicho por la profe en clase
#     * @brief muestra la info del jugador actual, si banca rota imprime ranking  
#     */
#    def actualizarInfo
#        puts @jugadores.at(@indiceJugadorActual).nombre
#        @jugadores.each do |j|
#            if(j.enBancarrota)
#                ranking();
#            end
#        end
#    end 
      
    
    
#    /**
#     *@brief crea el tablero indica la pos de la carcel y aniade las casillas 
#     *          del tablero
#     * 12 casillas tipo calle
#     * 3 casillas sorpresa
#     * 1 casilla juez
#     * 1 casilla carcel
#     * 1 casilla impuesto
#     * 1 casilla parking
#     */
    def inicializarTablero( mazo)
        @tablero = Tablero.new(5) #SALIDA se añade cuando se crea el tablero
         
        @tablero.aniadeCasilla(Casilla.new_casillaTitulo( TituloPropiedad.new("Calle Hipatia", 100, 100, 50, 50, 50))) #//string nom, float ab, float fr, float hb, float pc, float pe////dice que se aniadan las casillas que se van creando...
        
        @tablero.aniadeCasilla( Casilla.new_casillaSorpresa(mazo, "SORPRESA!! \n 
                 sabes quien fue Mary Winston Jackson? fue una matematica e \n
                 ingeniera aeroespacial. Trabajo para la NASA.Empezo como \n
                 calculista en la division de Calculo del Area Oeste, y mas \n
                 tarde llegaria a ser la primera ingeniera de color de la NASA."))
        @tablero.aniadeCasilla(Casilla.new_casillaTitulo( TituloPropiedad.new("Calle  Ada Lovelace", 100, 100, 50, 50, 50)))
        @tablero.aniadeCasilla(Casilla.new_casillaTitulo( TituloPropiedad.new("Calle  Hertha Ayrton", 100, 100, 50, 50, 50)))
        @tablero.aniadeCasilla(Casilla.new_casillaTitulo( TituloPropiedad.new("Calle  Hedy Lamarr", 100, 100, 50, 50, 50)))
        @tablero.aniadeCasilla( Casilla.new_casillaSorpresa(mazo, "SORPRESA!!
                 sabes quien fue Katherine Coleman Goble Johnson? \nuna fisica, cientifica 
                 espacial y matematica estadounidense que contribuyo\n a la aeronautica 
                 de los Estados Unidos y sus programas espaciales con\n la aplicacion 
                 temprana de las computadoras electronicas digitales en\n la NASA. 
                 Conocida por su precision en la navegacion astronomica,\n calculo 
                 la trayectoria para el Proyecto Mercury y el vuelo del\n Apolo 11 
                 a la Luna en 1969."))
        @tablero.aniadeCasilla(Casilla.new_casillaTitulo( TituloPropiedad.new("Calle  Rosalind Franklin", 100, 100, 50, 50, 50)))
        @tablero.aniadeCasilla(Casilla.new_casillaTitulo( TituloPropiedad.new("Calle  Annie Easley", 100, 100, 50, 50, 50)))
       
        @tablero.aniadeCasilla( Casilla.new_casillaSorpresa(mazo, "SORPRESA!! sabes quien fue Hedwig 
                 Eva Maria Kiesler\n conocida como Hedy Lamarr,  fue una actriz 
                 de cine e inventora\n austriaca naturalizada estadounidense, 
                 inventora de la \nprimera version del espectro ensanchado que 
                 permitiria las \ncomunicaciones inalambricas de larga distancia."))
        @tablero.aniadeCasilla(Casilla.new_casillaTitulo( TituloPropiedad.new("Calle  Anita Borg", 100, 100, 50, 50, 50)))
        @tablero.aniadeCasilla(Casilla.new_casillaTitulo( TituloPropiedad.new("Calle  Valentina Tereshkova", 100, 100, 50, 50, 50)))
        @tablero.aniadeCasilla(Casilla.new_casillaTitulo( TituloPropiedad.new("Calle  Jocelyn Bell Burnell", 100, 100, 50, 50, 50)))
        @tablero.aniadeCasilla(Casilla.new_casillaTitulo( TituloPropiedad.new("Calle  Katherine Johnson ", 100, 100, 50, 50, 50)))
        @tablero.aniadeCasilla( Casilla.new_casillaImpuesto(100, "Maria Goeppert-Mayer fue Premio 
                 Nobel de Fisica\n por sus descubrimientos sobre la estructura 
                 de capas nuclear.\n AHORA QUE LA CONOCES, PAGA TUS IMPUESTOS!"));
        @tablero.aniadeCasilla(Casilla.new_casillaTitulo( TituloPropiedad.new("Calle  Dorothy Vaughan", 100, 100, 50, 50, 50)))
        @tablero.aniadeCasilla(Casilla.new_casillaTitulo( TituloPropiedad.new("Calle  Mary Jackson", 100, 100, 50, 50, 50)))
        @tablero.aniadeCasilla(Casilla.new("Karen Uhlenbeck es una matematica 
                 estadounidense especialista en ecuaciones en derivadas 
                 parciales. \nEn marzo de 2019 recibio el Premio Abel or sus 
                 investigaciones\n con ecuaciones en derivadas parciales de 
                 las formas del \nespacio en varias dimensiones.PUEDES ACCEDER 
                 AL PARKING"));
      @tablero.aniadeJuez()
    end    
    
    
    

#    /**
#     * @brief igual que el metodo construir casa de jugador
#     * @param ip el identificador de la propiedad
#     * @return true si la ha construido
#     */
    def construirCasa( ip)
      ok = @jugadores.at(@indiceJugadorActual).construirCasa(ip)
      if ok
        puts "Has construido UNA CASA en la propiedad " + @tablero.getCasilla(@jugadores.at(@indiceJugadorActual).numCasillaActual).nombre
      end
        return ok
    end    
    
    
    
#    /**
#     * @brief igual que el metodo construir hotel de jugador
#     * @param ip el identificador de la propiedad
#     * @return true si lo ha construido
#     */    
    def construirHotel(ip)
      ok = @jugadores.at(@indiceJugadorActual).construirHotel(ip)
      if ok
        puts "Has construido UN HOTEL en la propiedad " + @tablero.getCasilla(@jugadores.at(@indiceJugadorActual).numCasillaActual).nombre
      end
        return ok
    end       
    
    

#    /**
#     * @brief Cuenta cuantas vecs pasa por la salida.
#     */
    def contabilizarPasosPorSalida( jugadorActual)
        while(@tablero.getPorSalida()>0)
            jugadorActual.pasaPorSalida();
        end
    end
    
    
    
#    /**
#     * @brief Se acaba el juego si algun jugador cae en banca rota
#     * @return fin true si se acaba el juego
#     */
    def finalDelJuego
        fin = false
    
        @jugadores.each do |j|
            if(j.enBancarrota)
                return true
            end
        end
        if fin
          puts "***********FIN DEL JUEGO**********"
          puts "**** "+ @jugadores.at(@indiceJugadorActual).nombre + " TE HAS ARRUINADO ****"
        end
      
        return fin
    end    



#    /**
#     *#brief obtinee la casilla actual
#     * @return casilla en la que esta el jugador actualmente
#     */
    def getCasillaActual
        return @tablero.getCasilla(@jugadores.at(@indiceJugadorActual).numCasillaActual)
    end
    
    
    
#    /**
#     * @brief consultor del jugador activo
#     */
    def getJugadorActual
        return @jugadores.at(@indiceJugadorActual)
    end
    
    
    
#    /**
#     * @brief igual que el metodo hipotecar de jugador
#     * @param ip el identificador de la propiedad
#     * @return true si la ha hipotecado
#     */
    def hipotecar ( ip)
      ok =  @jugadores.at(@indiceJugadorActual).hipotecar(ip)
      if ok
        puts "Has hipotecado la propiedad" + ip.to_s
      end
        return ok
    end
    
    
#    /**
#     * @brief informacion del jugador actual
#     * @return toda la informacion del jugador que tiene el turno
#     */
    def infoJugadorTexto
        return @jugadores.at(@indiceJugadorActual).toString
    end



#    /**
#     * @brief crea las sorpresas y las aniade al mazo
#     */
    def inicializarMazoSorpresas( tablero )

      @mazo.alMazo( Sorpresa.new_sorpresaEvitaCarcel(TipoSorpresa::SALIRCARCEL, @mazo));
      @mazo.alMazo( Sorpresa.new_todasSorpresas(TipoSorpresa::PORJUGADOR, 100, 'Recibes una donación de 100 monedas de cada jugador'));  
      @mazo.alMazo( Sorpresa.new_todasSorpresas(TipoSorpresa::PORJUGADOR, -1000, 'Dona a los demas jugadores 1000 monedas.'));
      @mazo.alMazo( Sorpresa.new_SorpresaIrOtraCasilla(TipoSorpresa::IRCASILLA, tablero, 15, 'Ve a la casilla 15'));
      @mazo.alMazo( Sorpresa.new_todasSorpresas(TipoSorpresa::PORCASAHOTEL, 1000, 'Toma regalo ganas 1000 monedas por casaHotel'));
      @mazo.alMazo( Sorpresa.new_SorpresaIrOtraCasilla(TipoSorpresa::IRCASILLA, tablero, 8, 'Ve a la casilla 8'));
      @mazo.alMazo( Sorpresa.new(TipoSorpresa::IRCARCEL, tablero));  
      @mazo.alMazo( Sorpresa.new_todasSorpresas(TipoSorpresa::PAGARCOBRAR, -1000, 'Dona a 1000 monedas'));
      @mazo.alMazo( Sorpresa.new_todasSorpresas(TipoSorpresa::PAGARCOBRAR, 500, 'Recibes una donación de 500 monedas mas'));
      @mazo.alMazo( Sorpresa.new_SorpresaIrOtraCasilla(TipoSorpresa::IRCASILLA, tablero, 4, 've a la casilla 4'));
      @mazo.alMazo( Sorpresa.new_todasSorpresas(TipoSorpresa::PORCASAHOTEL, -500, 'Has pensado donar a la ciencia 500 monedas por casaHotel'));      
    
    end
    
    
    
#    /**
#     * @brief actualiza el indice de jugador al cambiar de turno
#     */
    def pasarTurno
        if (@indiceJugadorActual < @jugadores.size)
            @indiceJugadorActual = @indiceJugadorActual + 1
        end
        if (@indiceJugadorActual == @jugadores.size)
            @indiceJugadorActual = 0
        end
    end
    
    
    
#    /**
#     * @brief realiza un ranking en funcion del saldo de los jugadores
#     */
    def ranking
      
        rankingJugadores = Array.new
        rankingJugadores = @jugadores;
        rankingJugadores.sort
        
      puts "\n****** El ranking queda asi ********\n"
      
      for j in rankingJugadores
        puts j.nombre +" "+ j.saldo.to_s
      end
      
        return rankingJugadores
    end
    
    
    
#    /**
#     * @brief igual que el metodo salir de la carcel pagando de jugador
#     * @param ip el identificador de la propiedad
#     * @return true si sale
#     */ 
    def salirCarcelPagando
        ok = @jugadores.at(@indiceJugadorActual).salirCarcelPagando
        if(ok)
            puts "\nSales de la carcel pagando\n"
        else
            puts "\nNO puedes salir de la carcel pagando\n" 
        end
        return ok
    end
    
    
#    /**
#     * @brief igual que el metodo salir de la carcel tirando de jugador
#     * @param ip el identificador de la propiedad
#     * @return true si sale
#     */ 
    def salirCarcelTirando
      ok = @jugadores.at(@indiceJugadorActual).salirCarcelTirando
        if(ok)
            puts "\n Has sacado 5!! Sales de la carcel\n"
        else
            puts "\nNO has sacado mas de 5, NO puedes salir de la carcel\n" 
        end
        return ok
    end    
    
    
    
#    /**
#     *  @brief actualiza el estado del juego obteniendo el siguiente 
#     *          estado del gestor de estados
#     */
    def siguientePasoCompletado( operacion)
        @estado = @gestorEstados.siguienteEstado(@jugadores.at(@indiceJugadorActual), @estado, operacion)
    end
    
    
#    /**
#     * @brief igual que el metodo vender de jugador
#     * @param ip el identificador de la propiedad
#     * @return true si lo ha vendido
#     */    
    def vender( ip)
      ok = @jugadores.at(@indiceJugadorActual).vender(ip)
      if ok
        puts "\n Has vendido la propiedad" + @tablero.getCasilla(ip).nombre+"\n"
      end
      
      return ok
    end    



#    //////////////////////////////////// implementacion en p3 /////////////
#    /** P3
#     * @brief igual que el metodo candelar hipoteca de jugador
#     * @param ip el identificador de la propiedad
#     * @return true si la ha cancelado
#     */
    def cancelarHipoteca( ip)
      ok = @jugadores.at(@indiceJugadorActual).cancelarHipoteca(ip)
      if ok
        puts "Has cancelado la hipoteca de la propiedad" + @tablero.casillas.at(ip).nombre
      end
        return ok
    end    
    
    
    
#    /**
#     *@brief  
#     */
    def avanzaJugador
      jugadorActual = @jugadores.at(@indiceJugadorActual)
      posicionActual = jugadorActual.numCasillaActual
      tirada = Dado.instance.tirar()
      posicionNueva = @tablero.nuevaPosicion(posicionActual, tirada);
      casilla = @tablero.getCasilla(posicionNueva)
      contabilizarPasosPorSalida(jugadorActual)
      jugadorActual.moverACasilla(posicionNueva)
      casilla.recibeJugador(@indiceJugadorActual, @jugadores);
      contabilizarPasosPorSalida(jugadorActual)
      puts "----- Puedes ir a la casilla: " + jugadorActual.numCasillaActual.to_s + " "+ casilla.nombre + " -------\n"
    end
    

#    /**
#     *@brief  
#     */    
    def siguientePaso
        jugadorActual = @jugadores.at(@indiceJugadorActual)
        operacion = @gestorEstados.operaciones_permitidas(jugadorActual,@estado )
        if(operacion == Operaciones_juego::PASAR_TURNO)
            pasarTurno
            siguientePasoCompletado(operacion);
        elsif(operacion == Operaciones_juego::AVANZAR)
            avanzaJugador
            siguientePasoCompletado(operacion)
        end
        return operacion
    end
    
    
    
#    /**
#     *@brief  
#     */    
    def comprar
        res = false
        
        jugadorActual = @jugadores.at(@indiceJugadorActual)
        numCasillaActual = jugadorActual.numCasillaActual
        casilla = @tablero.getCasilla(numCasillaActual)
        titulo = casilla.tituloPropiedad
        res =jugadorActual.comprar(titulo)
        if (!res)
          puts "\n No puedes comprar :( \n"
        else 
          puts "\n Propiedad comprada! :) \n" 
        end

        return res
    end
    

#    /*******************____MAIN PRUEBA___*************************************/
#    /**
#     * @param args the command line arguments
#     */
    def main
        
        puts getCasillaActual.toString #funciona
        puts getJugadorActual.nombre #funciona
        puts construirCasa(0) #sin implementar
        puts construirHotel(0) #sin implementar
        puts getJugadorActual.nombre #funciona
        puts contabilizarPasosPorSalida(getJugadorActual) #no devueve nada
        puts "final del juego: " + finalDelJuego.to_s #funciona
        puts getJugadorActual.saldo #funciona
        #for i in 0..3
          puts @tablero.getCasilla(1).toString
        #end
        
    end
        
        
    private :avanzaJugador, :contabilizarPasosPorSalida,
      :inicializarMazoSorpresas, :inicializarTablero, :pasarTurno
      :ranking
    
    end
    
  todos = Array.new
        
        todos << " Elvira "
        todos << " Ale "
        todos << " Jolu "
        todos << " Iballa "
        
        m = Civitas.new(todos)
        m.main

end
