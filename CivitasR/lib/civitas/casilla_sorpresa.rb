# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative "casilla.rb"
require_relative "sorpresa_ir_carcel.rb"
require_relative "sorpresa_ir_casilla.rb"
require_relative "sorpresa_pagar_cobrar.rb"
require_relative "sorpresa_por_casa_hotel.rb"
require_relative "sorpresa_por_jugador.rb"
require_relative "sorpresa_salir_carcel.rb"
require_relative "sorpresa.rb"
require_relative "jugador.rb"
#require "byebug"
module Civitas
  class CasillaSorpresa < Casilla
    
    
#    /**
#     * @brief constructor de casilla sorpresa
#     * @param mazo de cartas sorpresa
#     * @param nombre
#     */ 
    def initialize (mazo, nombre)
      super(nombre)
      @mazo = mazo
    end
    
    
    def recibeJugador( actual, todos)
      #byebug
        if(jugadorCorrecto(actual, todos))
            sorpresa = @mazo.siguiente
            informe(actual, todos)
            sorpresa.aplicarAJugador(actual,todos)
        end
    end
    
    #    /**
#     * @brief represente con detalle la información acerca de la casilla
#     */
    def toString()
      text = super
      if @sorpresa !=nil
       text = text + "\nLa Sorpresa Contiene: " + @sorpresa.toString
      end
      return text;
    end   
    
  end
end