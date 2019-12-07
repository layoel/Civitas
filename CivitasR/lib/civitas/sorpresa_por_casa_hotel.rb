# encoding: UTF-8

# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "sorpresa.rb"
require_relative "jugador.rb"
#require "byebug"
module Civitas

  class SorpresaPorCasaHotel < Sorpresa
    def initialize ( valor, texto)
      super(nil, valor, texto,nil)
      @valor = valor
      @texto = texto
    end
    
    
        
#    /**
#     *@brief si el jugador es correcto,
#     * se utiliza el método informe
#     * y se modifica el saldo del jugador actual(método modificarSaldo) 
#     * con el valor de la sorpresa multiplicado por el número de casas 
#     * y hoteles del jugador.
#     * @param actual indice del jugador que tiene el turno
#     * @param todos lista de jugadores 
#     */
    def aplicarAJugador( actual, todos)
        if (jugadorCorrecto(actual, todos))
            informe(actual, todos)
            todos.at(actual).modificarSaldo(@valor*todos.at(actual).cantidadCasasHoteles)
        end
    end
    
    
     public_class_method :new
    
    
  end
  
end