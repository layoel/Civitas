# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "sorpresa.rb"
require_relative "jugador.rb"
#require "byebug"
module Civitas

  class SorpresaPorJugador < Sorpresa
    def initialize (valor, texto)
      super(nil,valor, texto, nil)
      @valor = valor
      @texto = texto
    end
    
    
#    /**
#     *@brief El jugador da dinero al jugador actual
#     * si el jugador es actual es correcto, 
#     * se utiliza el método informe
#     * ◦ se crea una sorpresa de tipo PAGARCOBRAR con el valor de la sorpresa multiplicado por -1 
#     * ◦ y se aplica a todos los jugadores menos el actual
#     * ◦ se crea una sorpresa de tipo PAGARCOBRAR con el valor de la sorpresa multiplicado 
#     * por el número de jugadores excluyendo al actual y se aplica solo al jugador actual.
#     * @param actual indice del jugador que tiene el turno
#     * @param todos lista de jugadores 
#     */
    def aplicarAJugador(actual, todos)
        if (jugadorCorrecto(actual, todos))
            informe(actual, todos)
            s = SorpresaPagarCobrar.new(@valor*-1, "donacion")
            i=0;    
            while (i<todos.size)
                if(i != actual)
                  s.aplicarAJugador(i,todos)
                end
                i=i+1
            end
            
            s1 =  SorpresaPagarCobrar.new(@valor*(todos.size-1), "donacion")
            s1.aplicarAJugador(actual,todos);

        end
    end
    
    
     public_class_method :new
    
  end
end