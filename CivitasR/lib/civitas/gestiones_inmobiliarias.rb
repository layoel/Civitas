# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  module GestionesInmobiliarias
    VENDER = :vender
    HIPOTECAR = :hipotecar
    CANCELAR_HIPOTECA = :cancelar_hipoteca
    CONSTRUIR_CASA = :construir_casa
    CONSTRUIR_HOTEL = :construir_hotel
    TERMINAR = :terminar
  end
  Lista_Operaciones = [OperacionesInmobiliarias::VENDER,
    OperacionesInmobiliarias::HIPOTECAR,
    OperacionesInmobiliarias::CANCELAR_HIPOTECA,
    OperacionesInmobiliarias::CONSTRUIR_CASA,
    OperacionesInmobiliarias::CONSTRUIR_HOTEL,
    OperacionesInmobiliarias::TERMINAR]
end
