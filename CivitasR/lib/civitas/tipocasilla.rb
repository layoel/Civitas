# encoding: UTF-8

#require "byebug"
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
module Civitas
  #creamos un enumerado y las palabras :palabra son palabras reservadas
  #/**
  #* @author ecastillo
  #* Representa todos los tipos de casillas del juego.
  #* Las casillas descanso al llegar el jugador no ocurre nada,
  #* casilla salida, carcel y zonas de aparcamiento forman parte
  #* de este conjunto de casillas
  #*/
  module TipoCasilla
    CALLE = :calle
    CARCEL = :carcel
    SORPRESA = :sorpresa
    JUEZ = :juez
    IMPUESTO = :impuesto
    DESCANSO = :descanso
  end
end