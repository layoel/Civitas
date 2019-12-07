# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative "casilla.rb"
#require "byebug"
module Civitas
  class CasillaCalle < Casilla

  #  /**
  #   * @brief constructor de casilla calle
  #   * @param titulo de la propiedad 
  #   */ 
    def initialize (titulo)
      super(titulo.nombre)
        @tituloPropiedad = titulo
    end
    
    
    
    def recibeJugador( actual, todos)
      if(jugadorCorrecto(actual, todos))
         informe(actual, todos)

         if(!@tituloPropiedad.tienePropietario)
             todos.at(actual).puedeComprarCasilla   
         else
             @tituloPropiedad.tramitarAlquiler(todos.at(actual))
         end
      end
    end
    
    
    #    /**
#     * @brief represente con detalle la información acerca de la casilla
#     */
    def toString()
      text = " \n nombre: " + @nombre.to_s
      text = text + " \n tutiloPropiedad" + @tituloPropiedad.toString
      return text;
    end   
    
    
    
  end
end