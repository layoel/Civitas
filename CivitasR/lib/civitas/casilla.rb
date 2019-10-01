# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
 # /**
 #*
 #* @author ecastillo
 #* 
 #* @Bief Clase casilla temporal
 #*/
  class Casilla
    
    #/**
    #* @brief Consultor de nombre
    #* @return devuelve el nombre
    #*/
    attr_reader :nombre
    
    #/**
    #* @brief constructor de la clase casilla
    #* @param n nombre de la casilla 
    #*/ 
    def initialize(n)
      @nombre = n
    end
  end
end