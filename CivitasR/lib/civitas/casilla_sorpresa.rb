# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative "casilla.rb"

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
      text = " \n nombre: " + @nombre.to_s
      text =text+    " \n El tipo :  sorpresa"
      if@sorpresa != nil
            text = text + "\nLa Sorpresa Contiene: " + @sorpresa.texto
      end  
      return text;
    end   
    
  end
end