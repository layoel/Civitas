# encoding: UTF-8

#require "byebug"
require_relative 'operaciones_juego.rb'
require 'io/console'
require_relative "diario.rb"
require_relative "dado.rb"
require_relative "respuestas.rb"
require_relative "salidas_carcel.rb"

module Civitas

  class Vista_textual
    @@separador = "==============================================================="
    
    def initialize
      @iGestion = -1
      @iPropiedad = -1
      @juegoModel = nil
    end

    def mostrar_estado(estado)
      puts estado
    end

    
    def pausa
      if @juegoModel.estado == Estados_juego::INICIO_TURNO
        print "\nPulsa una tecla para tirar el dado\n"
      else
        print "\n Pulsa una tecla"
      end
      STDIN.getch
      print "\n"
    end

    def lee_entero(max,msg1,msg2)
      ok = false
      begin
        print msg1
        cadena = gets.chomp
        begin
          if (cadena =~ /\A\d+\Z/)
            numero = cadena.to_i
            ok = true
          else
            raise IOError
          end
        rescue IOError
          puts msg2
        end
        if (ok)
          if (numero >= max)
            ok = false
          end
        end
      end while (!ok)

      return numero
    end



    def menu(titulo,lista)
      tab = "  "
      puts titulo
      index = 0
      lista.each { |l|
        puts tab+index.to_s+"-"+l
        index += 1
      }

      opcion = lee_entero(lista.length,
                          "\n"+tab+"Elige una opción: ",
                          tab+"Valor erróneo")
      return opcion
    end

    
#     /*
#  * @brief muestra menu preguntando la forma de salir de la carcel
#  */
  def salirCarcel
      lista_SalidasCarcel = [Salidas_carcel::PAGANDO, Salidas_carcel::TIRANDO]
     # byebug
    opcion = menu("\Elige la forma para intentar salir de la carcel ", 
      ["PAGANDO", "TIRANDO"])
    return lista_SalidasCarcel[opcion].to_s
  end 
    
    
    def comprar
      lista_Respuestas= [Respuestas::SI, Respuestas::NO]
      opcion = menu("¿Quieres comprar la calle?", ["si", "no"]) 
      return lista_Respuestas[opcion].to_s
    end
    
    def gestionar
      # //pregunta el numero de gestion inmobiliaria
      lista = Array.new
      lista << "0 VENDER \n"
      lista << "1 HIPOTECAR\n"
      lista << "2 CANCELAR_HIPOTECA\n"
      lista << "3 CONSTRUIR_CASA\n"
      lista << "4 CONSTRUIR_HOTEL\n"
      lista << "5 TERMINAR\n"
      #byebug
      opcion1 = menu("\n Ahora puedes gestionar tus propiedades, ¿Que quieres hacer?:", lista)
     
      #//pregunta la propiedad a la que aplicar esa gestion inmobiliaria
      prop = Array.new
      i = 0
      for propiedad in @juegoModel.getJugadorActual.propiedades
        prop << i.to_s + propiedad.nombre
        i = i + 1
      end
      
      @iGestion= opcion1;
      
      if opcion1 < 5 
        opcion = menu("Elige el numero de propiedad para realizar la gestion:", prop)
        @iPropiedad = opcion;
      end
      
      
    end
#############################33
    
    
    def getGestion ## TAMBIEN SE PUEDE HACER CON ATTR READER :IGESTION
      return @iGestion
    end

    def getPropiedad ## TAMBIEN SE PUEDE HACER CON ATTRREADER :IPROPIEDAD
      return @iPropiedad
    end

    def mostrarSiguienteOperacion(operacion)
      puts @@separador
      puts "Ahora puedes: "
      puts operacion.to_s
    end

    def mostrarEventos
      while(Diario.instance.eventos_pendientes)
        puts Diario.instance.leer_evento
      end
    end

    
    def setCivitas(civitas)
      puts "\ninicializando juego \n"
         @juegoModel = civitas
         self.actualizarVista
    end
    
    
# /*
#  * @brief muestra informacion del jugador actual, sus propiedades y la casilla actual
#  */
    def actualizarVista
      puts @@separador
      puts "****** LA INFORMACION DEL JUGADOR ACTUAL****** \n"
      puts @juegoModel.getJugadorActual.toString()
      puts "**************TUS PROPIEDADES SON:**********\n"
      if @juegoModel.getJugadorActual().propiedades.size> 0
        for  propiedad in @juegoModel.getJugadorActual.propiedades
          puts propiedad.toString
        end
      else
        puts "AUN NO TIENES PROPIEDADES"
      end
      puts "**************ESTAS EN LA CASILLA:**************"
      puts @juegoModel.getCasillaActual.toString
      puts @@separador
    end

    
  end

end
