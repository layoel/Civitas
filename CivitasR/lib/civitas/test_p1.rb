# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require "civitas/casilla.rb"
require "civitas/dado.rb"
require "civitas/diario.rb"
require "civitas/estados_juego.rb"
#require "civitas/main.rb"
require "civitas/mazo_sorpresas.rb"
require "civitas/sorpresa.rb"
require "civitas/tablero.rb"
require "civitas/tipocasilla.rb"
require "civitas/tiposorpresa.rb"
require "civitas/civitas.rb"

module Civitas

  class TestP1
    def mainn 

      #Llama 100 veces al método  quienEmpieza() de Dado considerando que 
      #hay 4 jugadores, y calcula cuantas veces se obtiene cada uno de los 
      #valores posibles. Comprueba si se cumplen a nivel práctico las 
      #probabilidades de cada valor
        dado = Dado.instance
        diario = Diario.instance

        jugador = Array.new

        100.times do
           jugador << dado.quienEmpieza(4)
        end

        j1=0
        j2=0
        j3=0
        j4=0

        jugador.each do |i|
          if i == 0
            j1 = j1+1
          end
          if i == 1
            j2 = j2+1
          end
          if i == 2
            j3 = j3 +1
          end
          if i == 3
            j4 = j4 + 1
          end
        end
      puts "el jugador 1:"
      puts j1
      puts "el jugador 2:"
      puts j2
      puts "el jugador 3:"
      puts j3
      puts "el jugador 4:"
      puts j4

  

    # Asegúrate de que funciona el modo debug del dado activando y 
    # desactivando ese modo, y realizando varias tiradas en cada modo.        
      num = dado.tirar

      #activo modo debug
      dado.setDebug(true)
      puts dado.tirar
      puts dado.tirar
      puts dado.tirar
      puts dado.tirar
      #desactuvo modo debug
      dado.setDebug(false)
      puts dado.tirar
      puts dado.tirar
      puts dado.tirar

    # Prueba al menos una vez los métodos getUltimoResultado() y 
    # salgoDeLaCarcel() de Dado.
      puts dado.tirar
      puts dado.ultimoResultado
      puts dado.tirar
      puts dado.salgoDeLaCarcel
      

    # Muestra al menos un valor de cada tipo enumerado
      puts TipoCasilla::CALLE
      puts TipoSorpresa::IRCARCEL
      puts Estados_juego::INICIO_TURNO
      
    # Crea un objeto MazoSorpresas y haz las siguientes pruebas: 
    
    mz = MazoSorpresas.new
    # añade dos sorpresas al mazo,
    s1 = Sorpresa.new
    s2 = Sorpresa.new
    puts "aniade sorpresas"
    puts mz.alMazo(s1)
    puts mz.alMazo(s2)
    ##puts mz.sorpresas.inspect
    
    # obtén la siguiente sorpresa en juego,
    puts "obten la siguiente sorpresa"
    puts mz.siguiente
    # inhabilita y
    # habilita la segunda carta añadida. 
    puts "inhabilita la segunda carta aniadida"
    puts mz.inhabilitarCartaEspecial(s2)
    puts "habilita la segunda carta aniadida"
    puts mz.habilitarCartaEspecial(s2)
    
    
     
    # usa la clase Diario, aprovecha y prueba todos los métodos de Diario.
    puts 'prueba'
    puts Diario.instance.ocurre_evento("ha ocurrido el evento hola")
    puts Diario.instance.leer_evento
    puts Diario.instance.ocurre_evento("evento 2")
    puts Diario.instance.ocurre_evento("evento 3")
    puts Diario.instance.eventos_pendientes
    
    # Crea un tablero,   
    t = Tablero.new(2) #el argumento es la posicion de la casilla de la carcel
    #añádele varias casillas
    cas = Casilla.new("calle 1")
    cas1 = Casilla.new("calle 2")
    cas2 = Casilla.new("calle 3")
    
    t.aniadeCasilla(cas)
    t.aniadeCasilla(cas1)
    t.aniadeCasilla(cas2)
    # Intenta provocar las situaciones erróneas controladas en la clase Tablero (por 
    # ejemplo, que la posición de la cárcel sea mayor que el tamaño del 
    # tablero) y comprueba que la gestión de las mismas es la correcta.
    
      ##puts t.getCasilla(7)
    
    # Finalmente,realiza distintas tiradas con el dado y asegúrate de que se
    # calcula correctamente la posición de destino en el tablero.
    
    dado.setDebug(false)
    t.aniadeJuez
    ##miscasillas = t.casillas
    
    
    ##puts miscasillas.inspect
    ##puts "tamanio" + miscasillas.size.to_s
      
    actual = 0
    tirada = dado.tirar
    puts "dado" + tirada.to_s
    actual = t.nuevaPosicion(actual, tirada)
    puts "posicion nueva" + actual.to_s
    
    tirada = dado.tirar
    puts "dado" + tirada.to_s
    actual = t.nuevaPosicion(actual, tirada)
    puts "posicion nueva" + actual.to_s
      
    tirada = dado.tirar
    puts "dado" + tirada.to_s
    actual = t.nuevaPosicion(actual, tirada)
    puts "posicion nueva" + actual.to_s  
    end
      
       
  end
  
  t = TestP1.new
  
  t.mainn
    
end

