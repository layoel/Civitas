# encoding: UTF-8


# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "sorpresa.rb"
require_relative "jugador.rb"
#require "byebug"
module Civitas

  class SorpresaIrCarcel < Sorpresa
    
    def initialize (tablero)
      super(tablero, nil, nil, nil)
      @tablero = tablero
      @texto = "vas a la carcel"
    end
    
    
       
#    /**
#     * @brief Si el jugador es correcto, 
#     * se utiliza el método informe y 
#     * se encarcela al jugador (método encarcelar) indicado
#     * @param actual indice del jugador que tiene el turno
#     * @param todos lista de jugadores 
#     */
    def aplicarAJugador(actual, todos)
        if (jugadorCorrecto(actual, todos))
            informe(actual, todos)
            todos.at(actual).encarcelar(@tablero.numCasillaCarcel);
        end
    end
  
    
    public_class_method :new
    
  end
end
