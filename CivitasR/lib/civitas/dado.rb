# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require 'singleton'
require 'securerandom'

module Civitas
  class Dado
    
    #  //singleton
    include Singleton
    
    #atributo de clase privado
    @@salidaCarcel = 5
    
    #/**
    #* @brief consultor de ultimoResultado
    #*/
    attr_reader :ultimoResultado
    
    private
    #/**
    #* @brief constructor de la clase dado
    #*/
    def initialize
      @random = Random.new
      @ultimoResultado = 0
      @debug = false
    end
    
    
    public
    # /**
    # * @brief genera un numero aleatorio entre 1 y 6 si el modo debub esta desactivado
    # * @return ultimoResultado devuelve 1 en modo debug, sino el numero generado
    # */
    def tirar
      @ultimoResultado = 1
      
      unless(@debug)
        @ultimoResultado = @random.rand(1..6)
      end
      
      return @ultimoResultado
    end
    
    
    
    #/**
    #* @brief se tira el dado y sale de la carcel si saca 5 o mas
    #* @return salgo true si sale, false no sale.
    #*/
    def salgoDeLaCarcel
      tirar
      salgo = false
      if(@ultimoResultado == 5)
        salgo = true
      end
      return salgo
    end
    
    
    
    # /**
    # * @brief decide que jugador empieza el juego
    # * @param n numero de juegadores
    # * @return num jugador que empieza
    # */
    def quienEmpieza(n)
      return @random.rand(0..n-1)
    end
    
    
    # /**
    # * @brief modificador del atributo debug, además deja constancia en el 
    # * diario del modo en el que se ha puesto del dado 
    # * @param d      
    # */
    def setDebug(d)
      @debug = d
      if (@debug)
        Diario.instance.ocurre_evento("debug activado")
      else
        Diario.instance.ocurre_evento("debug desactivado")
      end
    end
    
    
  end
end
