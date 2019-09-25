# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require 'singleton'
module Civitas
  class Dado
    include Singleton
    attr_accesor :random, :ulimoResultado, :debug, :salidaCarcel
    
    private
    def initialize
      @random = Rand.new
      @ultimoResultado = 0
      @debug = false
      @salidaCarcel = 5
    end
    
    public
    def tirar
      @ultimoResultado = 1
      
      unless(@debug)
        @ultimoResultado = @random.rand(1..6)
      end
      
      return @ultimoResultado
    end
    
    def salgoDeLaCarcel
      tirar
      if(@ultimoResultado == 5)
        @salidaarcel = true
      end
    end
    
    def quienEmpieza(n)
      return @random.rand(0..n-1)
    end
    
    def setDebug(d)
      @debug = d
      if (@debug)
        ocurreEvento("debug activado")
      else
        ocurreEvento("debug desactivado")
      end
    end
    
    
  end
end
