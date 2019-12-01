# encoding: UTF-8
#
#require "byebug"
require_relative "sorpresa.rb"
#/**
# *
# * @author ELVIRA
# * 
# * @brief Representa el mazo de cartas sorpresa, almacena las cartas
# * las instancias velan porque el mazo se mantenga consistente a lo largo del
# * juego y que se produzcan las operaciones de barajado cuando se han usado
# * todas las cartas.
# * 
# * @param sorpresas almacenar las cartas Sorpresa
# * @param barajada  indicar si ha sido barajado o no.
# * @param usadas contar el número de cartas del mazo que han sido ya usadas
# * @param debug activar/desactivar el modo depuración. Cuando está activo 
# * este atributo, el mazo no se baraja, permitiendo ir obteniendo las sorpresas
# * siempre en el mismo orden en el que se añaden
# * @param cartasEspeciales  Este atributo almacenará la carta sorpresa del 
# * tipo SALIRCARCEL mientras se considere retirada del mazo (y por tanto en 
# * posesión de un jugador)
# * @param ultimaSorpresa guardar la última sorpresa que ha salido. 
# * 
# */
module Civitas
  class MazoSorpresas
    #attr_accessor :sorpresas         #/////solo para probaar test_p1
    
    # /**
    # * @brief inicia sorpresas y cartas especiales barajada y usadas 
    # */
    attr_reader :ultimaSorpresa, :sorpresas
    
    def init
       @sorpresas = Array.new
       @cartasEspeciales = Array.new
       @barajada = false
       @usadas = 0
     end
     
    
    #  /**
    # * @brief constructor con parametro debug y por defecto
    # */
    
    def initialize( debu = false ) 
      @debug = debu
      @ultimaSorpresa = nil
      init
      if (debu)
        Diario.instance.ocurre_evento("iniciado modo debug")
      end
    end
  
    
    # /**
    # *@brief añade la sorpresa si el mazo no ha sido barajado
    # * @param s sorpresa que se añade
    # */
    
    def alMazo (s)
      unless @barajada
        @sorpresas << s
      end
    end
    
    
    
    #/**
    # * @brief coger una carta sorpresa
    # */
    
    def siguiente
      if !@debug
        if !@barajado or @usadas == @sorpresas.size
          @sorpresas.shuffle #barajo el mazo
          @usadas = 0
          @barajada = true
        end
      end
      @usadas = @usadas+1
      @ultimaSorpresa = @sorpresas.at(0) #guardo la sorpresa en s
      @sorpresas.delete_at(0) #borro la sorpresa del inicio del mazo
      @sorpresas << @ultimaSorpresa #añado la sorpresa al final del mazo
    
      return @ultimaSorpresa
    end
  
    
    # /**
    # * @brief comprueba si una carta especial esta en el mazo 
    # * @param sorpresa carta especial
    # */
    
    def inhabilitarCartaEspecial (sorpresa)
      #byebug
      s = Sorpresa.new(nil, nil)
      unless ((s = @sorpresas.find(sorpresa)) == nil)
        @sorpresas.delete(s)
        @cartasEspeciales << sorpresa
        Diario.instance.ocurre_evento("se ha inhabilitado una carta especial "+ sorpresa.toString)
      end
    end
    
    
    # /**
    # * @brief comprueba si una carta es especial
    # * @param sorpresa carta sorpresa
    # */
    
    def habilitarCartaEspecial (sorpresa)
      
      unless ((s = @cartasEspeciales.find(sorpresa)) == nil)
        @cartasEspeciales.delete(s)
        @sorpresas << sorpresa
        Diario.instance.ocurre_evento("se ha habilitado una carta sorpresa "+ sorpresa.toString)
      end
    end
    
    private :init
    
  end
end