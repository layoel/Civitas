# encoding: UTF-8

#require "byebug"
    # /**
    #*@brief Representa el tablero del juego imponiendo restricciones 
    #* sobre el mismo en las reglas del juego
    #* 
    #* @param numCasillaCarcel el numero de la casilla donde se encuentrala carcel
    #* @param casillas contenedor de las casillas del juego
    #* @param porSalida numero de veces que se ha pasado por la salida en un turno
    #* @param tieneJuez si el tablero tiene o no casilla de ese tipo
    #*/
    
require_relative "casilla_juez.rb"

module Civitas
  
  class Tablero
    
    #attr_reader :casillas         #solo para hacer las pruebas de test_p1
    
    #/**
    #* @brief Consultor de numero de casilla carcel
    #* @return numCasillaCarcel
    #*/
    attr_reader :numCasillaCarcel, :casillas
    
    #attr_reader 
    #attr_writer
    #attr_accessor

    
    
    
    # /**
    # * @brief Constructor
    # * @param casilla_carcel indice de la casilla de la carcel
    # */
    
    def initialize(casilla_carcel)
      
      if (casilla_carcel>=1)
        @numCasillaCarcel = casilla_carcel
      else
        @numCasillaCarcel = 1
      end
           
      @casillas = Array.new
      @casillas << Casilla.new("Salida")
      
      @porSalida = 0
      @tieneJuez = false
    end
    
    
    
    
     #/**
     #* @brief Comprueba si el tablero es correcto para jugar
     #* de la casilla de la carcel y que se dispone de una casilla tipo juez
     #* @return true si el tablero es correcto y puede usarse para jugar
     #*/
    
    def correctoC
      if((@casillas.size()> @numCasillaCarcel) and @tieneJuez)
        ok = true
      else
        ok = false
      end
      return ok
    end
    
    
    
    #/**
    #* @brief comprueba una casilla está en el tablero 
    #* @param numCasilla indice válido para acceder a elementos de casillas
    #* @return true si se puede jugar y el indice es valido
    #*/
    
    def correcto(numCasilla)
      
      if (correctoC and (numCasilla < casillas.size()))
        ok = true
      else
        ok = false
      end
      return ok
    end
    
    
    
    # /**
    # * @brief numero de veces que se pasa por salida
    # * @return porSalida
    # */
    
    def getPorSalida
      por_salida_anterior = @porSalida
      
      if @porSalida > 0
        @porSalida = @porSalida -1
      end
      
      return por_salida_anterior
    end
    
    
    
    # /**
    # * @brief añade a casillas la casilla c
    # * @param cas casilla que se va a añadir
    # */
    
    def aniadeCasilla(cas)
      if (@casillas.size == @numCasillaCarcel)
        @casillas << Casilla.new("||||||A LA CARCEL!!|||||| Como que no 
                 conces al menos a 5 mujeres 
                 cientificas importantes de la historia? ")
      end
      @casillas << cas
    end
    
    
    
    #/**
    # * @brief añade la casilla juez si no esta aun
    # */
    
    def aniadeJuez
      unless(@tieneJuez)
        @casillas << CasillaJuez.new(@numCasillaCarcel,"Juez, yo voy a juzgar tus 
                      conocimientos sobre mujeres importantes en la ciencia")
        @tieneJuez = true
      end
    end
    
    
    
    #/**
    # * @brief Comprueba si la casilla esta en el tablero
    # * @return casilla si es correcto o null si no lo es
    # */
    
    def getCasilla(numCasilla)
      if (numCasilla < @casillas.size())
        cas = @casillas.at(numCasilla)
      else
        cas = nil
      end
      return cas
    end
    
    
    
    #/**
    # * @brief calcula la posicion en el tablero a partir de la actual y avanza 
    # * una tirada de unidades
    # * @param actual
    # * @param tirada
    # * @return -1 si el tablero no es correcto, posicion 
    # */
    
    def nuevaPosicion (actual, tirada)
      
      if (correctoC)
        pos = actual + tirada
        if(pos > @casillas.size)
          pos = pos % @casillas.size
          @porSalida = @porSalida + 1
        end
      else
        pos = -1
      end
      return pos
    end
    
    
    
    # /**
    # * @brief calcula el numero de casillas que avanza 
    # * @param origen casilla donde estoy
    # * @param destino casilla a la que llego
    # * @return tirada de dado que se habria tenido que obtener para ir desde la 
    # * casilla origen a la casilla destino 
    # */
    
    def calcularTirada(origen, destino)
       tirada = destino - origen
      if (tirada < 0)
        tirada = tirada + @casillas.size
      end
      return tirada
    end
    
    
    
#    #solo para pruebas
#    def getCasillas
#      return @casillas
#    end
    
    #metodos privados de la clase
    private :correcto, :correctoC
    
  end
end
