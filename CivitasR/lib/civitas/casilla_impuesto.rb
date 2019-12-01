# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative "casilla.rb"
module Civitas
  class CasillaImpuesto < Casilla

    
#  /**
#   * @brief constructor de casilla impuesto
#   * @param cantidad 
#   * @param nombre de la casilla 
#   */
    def initialize (cantidad, nombre)
      super(nombre)
      @importe = cantidad
    end
  
  
    
#    /**
#     * @brief El jugador paga un impuesto por el valor que indica la casilla
#     * @param actual
#     * @param todos
#     */
    def recibeJugador( actual, todos)
        if(jugadorCorrecto(actual,todos))
            informe(actual,todos)
            todos.at(actual).pagaImpuesto(@importe)
        end   
    end
    
    
    #    /**
#     * @brief represente con detalle la información acerca de la casilla
#     */
    def toString()
      text = " \n nombre: " + @nombre.to_s
      text =text+    " \n El tipo :  impuesto"
      text = text + " \n el importe a pagar: " + @importe.to_s 
      return text;
    end   
    
    
    
  end
end