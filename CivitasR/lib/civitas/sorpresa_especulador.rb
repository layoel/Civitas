# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
#require "byebug"
require_relative "jugador_especulador.rb"
module Civitas

  class SorpresaEspeculador < Sorpresa
    
    def initialize valor, texto
      super(nil,valor,texto,nil)
      @texto = texto
      @valor = valor
    end
    
    
#     /**
#     * @brief Si el jugador es correcto, 
#     * se utiliza el método informe y 
#     * se convierte al juegador en especulador
#     * @param actual indice del jugador que tiene el turno
#     * @param todos lista de jugadores 
#     */
  def aplicarAJugador(actual, todos)

        if (jugadorCorrecto(actual, todos))
            informe(actual, todos);
            especulador = JugadorEspeculador.nuevoEspeculador(todos.at(actual), @valor)
            #byebug
            todos[actual] = especulador 
        end
  end 
    public_class_method :new
  end
end