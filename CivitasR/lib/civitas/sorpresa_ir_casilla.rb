# encoding: UTF-8

# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative "sorpresa.rb"
require_relative "jugador.rb"
#require "byebug"
module Civitas
  class SorpresaIrCasilla < Sorpresa
    
    def initialize (tablero, valor, texto)
      super(tablero, valor, texto,nil)
      @tablero = tablero
      @valor = valor
      @texto = texto
    end
    
    
       
#   /**
#     *@brief si el jugador es correcto, 
#     * ◦ se utiliza el método informe y obtiene la casilla actual del jugador
#     * ◦ se calcula la tirada utilizando el método calcularTirada(casillaActual, valor) del tablero.
#     * ◦ se obtiene la nueva posición del jugador con el método nuevaPosicion(casillaActual,tirada) del tablero.
#     * ◦ se mueve al jugador a esa nueva posición (método moverACasilla)
#     * ◦ se indica a la casilla que está en la posición del valor de la sorpresa que reciba al jugador (método recibeJugador)
#     * @param actual indice del jugador que tiene el turno
#     * @param todos lista de jugadores 
#     */
    def aplicarAJugador(actual, todos)
        if (jugadorCorrecto(actual, todos))
            informe(actual, todos)
            casillaActual = todos.at(actual).numCasillaActual
            tirada = @tablero.calcularTirada(casillaActual, @valor)
            nuevaPos = @tablero.nuevaPosicion(casillaActual, tirada)
            todos.at(actual).moverACasilla(nuevaPos)
            @tablero.getCasilla(nuevaPos).recibeJugador(actual, todos)
        end
    end
    
    
    
     public_class_method :new
    
  end
end