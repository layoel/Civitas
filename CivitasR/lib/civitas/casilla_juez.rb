# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative "casilla.rb"
#require "byebug"
module Civitas
  class CasillaJuez < Casilla
    
    
#  /** 
#   * @brief constructor de casilla juez
#   * @param numcarcel numero de la casilla carcel
#   * @param nombre el nombre de la casilla
#   */ 
    def initialize (numCarcel, nombre)
      super(nombre)
      @@carcel = numCarcel
    end
    
    
#     /**
#     * @brief Se encarcela al jugador actual
#     * @param actual
#     * @param todos
#     */
    def recibeJugador( actual, todos)
        if(jugadorCorrecto(actual, todos))
            informe(actual,todos)
            todos.at(actual).encarcelar(@@carcel)
        end
    end 
    
    #    /**
#     * @brief represente con detalle la información acerca de la casilla
#     */
    def toString()
      text = " \n nombre: " + @nombre.to_s
      text =text+    " \n El tipo :   Juez" +
                    " \n carcel: "+ @@carcel.to_s
        return text;
    end   
    
    
    
  end
end