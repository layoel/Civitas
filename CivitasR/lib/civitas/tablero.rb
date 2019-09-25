# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.


# encoding:utf-8
module Civitas
  class Tablero
    #attr_reader 
    #attr_writer
    #attr_accessor
    attr_accessor :numCasillaCarcel, :casillas, :porSalida, :tieneJuez 
    
    def initialize(casilla_carcel)
      
      
      if (casilla_carcel>=1)
        @numCasillaCarcel = casilla_carcel
      else
        @numCasillaCarcel = 1
      end
           
      @casillas = Array.new
      @casillas.push(Casilla.new("Salida"))
      
      @porSalida = 0
      @tieneJuez = false
    end
  
    private
    def correcto
      if(@casillas.size()> @numCasillaCarcel and @tieneJuez)
        ok = true
      else
        ok = false
      end
      return ok
    end
    
    def correcto(numCasilla)
      if (correcto and numCasilla < casillas.size())
        ok = true
      else
        ok = false
      end
      return ok
    end
    
    def getPorSalida
      if @porSalida > 0
        por_salida_anterior = @porSalida
        @porSalida = @porSalida -1
      else
        por_salida_anterior = @porSalida 
      end
      return por_salida_anterior
    end
    
    def añadeCasilla(cas)
      if (@casillas.size == @numCasillaCarcel)
        @casillas.push(Casilla.new("Carcel"))
      end
      @casillas.push(cas)
    end
    
    def añadeJuez
      unless(@tieneJuez)
        @casillas.push(Casilla.new("Juez"))
        @tieneJuez = true
      end
    end
    
    def getCasilla(numCasilla)
      if (numCasilla < @casillas.size())
        cas = casillas[numCasilla]
      else
        cas = null
      end
      return cas
    end
    
    def nuevaPosicion (actual, tirada)
      pos = actual + tirada
      if (correcto)
        if(pos > @casillas.size)
          pos = pos % @casillas.size
          @porSalida = @porSalida + 1
        end
      else
        pos = -1
      end
      return pos
    end
    
    def calcularTirada(origen, destino)
       tirada = destino - origen
      if (tirada < 0)
        tirada = tirada + @casillas.size
      end
      return tirada
    end
    
    
  end
end
