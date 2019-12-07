# encoding: UTF-8

#require "byebug"
require_relative "jugador.rb"
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  
class Sorpresa
  #los necesito para poder acceder al objeto y asignar valores
  attr_accessor :tipo, :tablero, :valor, :mazo, :texto

  
    def initialize (tablero =nil, valor=nil , texto = nil, mazo=nil)
      init
    end
    
    def init
        @valor = -1
        @mazo = nil
        @tablero = nil
      
    end

    
#    /**
#     *@brief comprueba si el jugador actual esta en la lista de jugadores
#     * @param actual es el jugador que tiene el turno
#     * @param todos lista de jugadores
#     * @return correcto true si es correcto el jugador
#     */
    def jugadorCorrecto(actual, todos)
        correcto = false;
        if (actual < todos.size)
            correcto = true
        end
        return correcto
    end


#    /**
#     *@brief Informa que se esta aplicando una sorpresa a un jugador
#     * @param actual es el jugador que tiene el turno
#     * @param todos lista de jugadores
#     */
    def informe(actual,todos)
      Diario.instance.ocurre_evento(" Al jugador: " + todos.at(actual).nombre + 
          " se le aplica la sorpresa: "+ @texto)
    end

    
#     /**
#     * @brief didce el nombre de la sorpresa.
#     */
      def toString()
          return @texto;
      end  
 
      
      
      private :init
      private_class_method :new

end
end