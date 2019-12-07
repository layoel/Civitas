# encoding: UTF-8

# # To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative "sorpresa.rb"
require_relative "jugador.rb"
#require "byebug"

module Civitas
  class SorpresaSalirCarcel <Sorpresa
    def initialize(mazo)
       super(nil,nil,nil, mazo)
      @texto = "tienes un salvoconducto"
      @mazo = mazo
    end
    
    #     /**
#     *@brief si el tipo de la sorpresa es la que evita la cárcel, 
#     * inhabilita la carta especial en el mazo de sorpresas.
#     */
      def salirDelMazo
        @mazo.inhabilitarCartaEspecial(self)
      end
    
    
    
#     /**
#     *@brief si el tipo de la sorpresa es la que evita la cárcel, 
#     * habilita la carta especial en el mazo de sorpresas.
#     */
      def usada
        @mazo.habilitarCartaEspecial(self)
      end
    
#       /**
#     * @brief si el jugador es correcto, 
#     * se utiliza el método informe 
#     * y se pregunta a todos los jugadores si alguien tiene la sorpresa para evitar la cárcel (método tieneSalvoconducto).
#     * Si nadie la tiene, la obtiene el jugador actual (método obtenerSalvoconducto) 
#     * y se llama al método salirDelMazo.
#     */
    def aplicarAJugador(actual, todos)
      ##byebug
      if (jugadorCorrecto(actual, todos))
            informe(actual, todos)
            latienen = false
            for z in todos
              if z.tieneSalvoconducto
                latienen = true
              end
            end
            if(!latienen)
                s = SorpresaSalirCarcel.new(@mazo)
                todos.at(actual).obtenerSalvoconducto(s)
                s.salirDelMazo
            end
        end
      end
     public_class_method :new
  end
end