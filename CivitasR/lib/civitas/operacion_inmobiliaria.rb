# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

class OperacionInmobiliaria
  
  attr_reader :numPropiedad, :gestion
  
  
  def initialize (gestion, numPropiedad)
    @numPropiedad = numPropiedad
    @gestion = gestion
  end
  
  
end
