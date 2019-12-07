# encoding: UTF-8
# 
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
#require_relative "titulo_propiedad.rb"
#require_relative "mazo_sorpresas.rb"
#require_relative "sorpresa.rb"
#require_relative "tiposorpresa.rb"
require_relative "vista_textual.rb"
require_relative "diario.rb"
require_relative "dado.rb"
require_relative "civitas.rb"
require_relative "controlador"
require_relative "operaciones_juego.rb"
require_relative "jugador.rb"
#require "byebug"

module Civitas
  class Prueba
    
    attr_reader :vista, :juego
    
    def initialize
      jugadores = Array.new
      jugadores << "Elvira"
      jugadores << "Ale"
      #jugadores << "Jolu"
      @vista = Vista_textual.new
      @juego =  Civitas.new(jugadores)
    end
   
    def self.main
      Dado.instance.setDebug(true)
      p1 = Prueba.new
      #byebug
      control =  Controlador.new(p1.juego, p1.vista)
      control.juega
    end
    
  end
  Prueba.main
end
