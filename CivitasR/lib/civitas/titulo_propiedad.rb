# encoding: UTF-8

#require "byebug"
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative "jugador.rb"
module Civitas
  
  class TituloPropiedad

    # /**
    #  *@brief consulta si esta hipotecado
    #  */
    attr_reader :hipotecado

    # /**
    # *@brief consulta el nombre del titulo de propiedad
    # */
    attr_reader :nombre
    #/**
    # *@brief cuantas casas tengo
    # */
    attr_reader :numCasas
    #/**
    # *@brief cuantos hoteles tengo
    # */
    attr_reader :numHoteles
    #/**
    # *@brief Cuanto cuesta edificar
    # */
    attr_reader :precioEdificar
    #/**
    # *@brief Cuanto cuesta Comprar
    # */
    attr_reader :precioCompra
    # /**
    # *@brief Quien es el propietario
    #  */
    attr_reader :propietario
    
    
    @@factorInteresesHipoteca = 1.1
    #/**
    #   * @brief Constructor
    #   * @param nom nombre
    #   * @param ab precioBaseAlquiler
    #   * @param fr factorRevalorizacion
    #   * @param hb precioBaseHipoteca
    #   * @param pc precioCompra
    #   * @param pe precioEdificar
    #   * @param propietario
    #   * @param numCasas
    #   * @param numHoteles
    #   * @param hipotecado
    #   * @pre titulo propiedad comienza sin propietario ni casas ni hoteles y
    #   # sin hipotecar 
    
    def initialize(nom, ab, fr, hb, pc, pe)
         @nombre = nom
         @alquilerBase = ab
         @factorRevalorizacion = fr
         @hipotecaBase = hb
         @precioCompra = pc
         @precioEdificar = pe
         @propietario = nil
         @numCasas = 0
         @numHoteles = 0
         @hipotecado = false
    end

    #   /**
    #   *@brief actualizar propietario
    #   */
    def actualizaPropietarioPorConversion(jugador)
        @propietario = jugador;
    end

    #/**
    # *@brief se puede cancelar la hipoteca?
    # * @param jugador
    # * @return ok true si se ha cancelado
    # */
    def cancelarHipoteca(jugador)
        result = false;
        if (@hipotecado)
          if (esEsteElPropietario(jugador))
            result = jugador.paga(getImporteCancelarHipoteca)
            @hipotecado = false;
          end
        end
        
        return result;    
    end
    
    
    #/**
    # *@brief total de propiedades.
    # * return total suma de casas y hoteles
    # */
    def cantidadCasasHoteles
        total = @numCasas + @numHoteles;
        return  total;
    end
    
    
    #/**
    # * @brief ¿puedo comprar esta propiedad?
    # * @return comprado true si no tenia propietario y he pagado por ella
    # */
    def comprar(jugador)
       comprado = false;
       if(!tienePropietario)
           @propietario = jugador;
           comprado = @propietario.paga(@precioCompra);
       end
       return comprado;
    end

    
    #/**
    # * @brief ¿puedo construir una casa?
    # * @param jugador el que quiere construir
    # * @return construida true si ha podido construir la casa
    # */
    def construirCasa(jugador)
        construida = false;
        
        if(esEsteElPropietario(jugador))
            construida = jugador.paga(@precioCompra);
            @numCasas = @numCasas + 1
        end
        
        return construida;
    end
    
    
    
    # /**
    # * @brief ¿puedo construir un hotel?
    # * @param jugador el que quiere construir
    # * @return result true si ha podido construir el hotel
    # */
    def construirHotel(jugador)
        result = false
        
        if(esEsteElPropietario(jugador))
            jugador.paga(@precioCompra)
            @numHoteles = @numHoteles + 1
            result = true
        end
        
        return result
    end
    
    # /**
    # *@brief el jugador quiere derruir algunas casas
    # * @param n numero de casas que quiere derruir
    # * @param jugador el que quiere derruir las casas
    # * @return derruido true si ha conseguido derruir algunas casas
    # */
    def derruirCasas(n, jugador)
        derruido = false
        
        if(@propietario == jugador && (@numCasas >= n))
            @numCasas = @numCasas - n;
            derruido = true;
        end
        
        return derruido;
    end
    
    #/**
    # *@brief consulta si un jugador es propietario
    # * @param jugador
    # * @return loes true si es el propietario
    # */
    def esEsteElPropietario(jugador)
        loes = false;
        if(@propietario == jugador)
            loes = true;
        end
        return loes;
    end
    
    
    # getHipotecado usa el attreader para obtener hipotecado
    
    #/**
    # * @brief calcula el importe para cancelar la hipoteca
    # * @return importe  hipotecaBase * factorInteresesHipoteca;
    # */
    def getImporteCancelarHipoteca
        return @hipotecaBase * @@factorInteresesHipoteca
    end
    
    
    #/**
    # *@brief Cuanto es la hipoteca de este titulo de propiedad
    # */
    def getImporteHipoteca
      return @hipotecaBase
    end     
    # getNombre usa el attreader
    
    # getNumCasas usa el attreader
    
    # getNumHoteles usa el attreader
    
    #/**
    # *@Brief calcula el presio del alquiler segun la formula
    # * PrecioAlquiler=AlquilerBase*(1+(NumCasas*0.5)+(NumHoteles*2.5)) excepto 
    # * que esté hipotecado o el propietario encarcelado
    # * @return alq el precio que debe pagar
    # */
    def getPrecioAlquiler
      if(!@hipotecado && !propietarioEncarcelado)
            alq = @alquilerBase * ( 1 + ( @numCasas * 0.5)+(@numHoteles * 2.5))
      end
      return alq
    end
    
    # getPrecioEdificar usa el attreader
    
    
    #/**
    # *@brief calcula el precio de venta segun la formula: 
    # * numCasas * (precioCompra + precioEdificar) * numHoteles * factorRevalorizacion;
    # * @return precio el precio calculado
    # */
    def getPrecioVenta
      precio = (cantidadCasasHoteles) *(@precioCompra +@precioEdificar) *@factorRevalorizacion 
      return precio
    end
    
    #getPropietario
    
    #/**
    # *@brief consulta si un jugador puede  hipotecar una propiedad o no
    # * @return realizada true si ha podido hipotecarla
    # */
    def hipotecar(jugador)
      salida = false
        
      if(!@hipotecado && esEsteElPropietario(jugador))
        jugador.recibe(getImporteHipoteca)
        @hipotecado = true
        salida = true
      end
        
        return salida
    end
    
    
    # /**
    # * @brief Quiero saber si el propietario está encarcelado
    # * @return carcel true si lo esta
    # */
    def propietarioEncarcelado
      carcel = false;
        if(@propietario.encarcelado)
            carcel = true;
        end
        return carcel;
    end
    
    #/**
    # *@brief consulta si tiene propietario una propiedad
    # * @return tiene true si lo tiene false en otro caso.
    # */
    def tienePropietario
        tiene = false;
        if (@propietario != nil)
            tiene = true;
        end
        return tiene;
    end
    
    #/**
    # *@brief proporciona una representación en forma de cadera del estado del objeto
    # * @return mensaje el estado del objeto
    # */
    def toString
      
      mensaje = " \n nombre" + @nombre + 
                " \n alquilerBase: " + @alquilerBase.to_s + 
                " \n factorInteresesHipoteca: " + @factorInteresesHipoteca.to_s+ 
                " \n factorRevalorizacion: " + @factorRevalorizacion.to_s+ 
                " \n hipotecaBase: " + @hipotecaBase.to_s +
                " \n hipotecado: " + @hipotecado.to_s + 
                " \n numCasas" + @numCasas.to_s+ 
                " \n numHoteles: " + @numHoteles.to_s + 
                " \n precioCompra:" + @precioCompra.to_s+ 
                " \n precioEdificar: " + @precioEdificar.to_s
              if @propietario != nil
                mensaje = mensaje + " \n propietario: " + @propietario.nombre
              else
                mensaje = mensaje + " \n Aun no tiene propietario\n"
              end
        return mensaje;
    end
    
    
    #/**
    # *@brief Transaccion de pago y recibo de alquiler por parte del inquilino 
    # * y el propietario
    # */
    def tramitarAlquiler(jugador)
        
      if tienePropietario 
          unless esEsteElPropietario(jugador)
            precio = getPrecioAlquiler
            jugador.pagaAlquiler(precio)
            @propietario.recibe(precio)
          end
      end
    end
  
    # /**
    # *@brief si el jugador pasado como parámetro es el propietario
    #del título, y éste no está hipotecado, entonces se da al propietario (método recibe de
    #Jugador) el precio de venta , se desvincula el propietario de la propiedad, y se eliminan las
    #casas y hoteles, devolviendo true.
    # */
    def vender(jugador)
        vendido = false
        
        if(esEsteElPropietario(jugador) && !@hipotecado)
            jugador.recibe(getPrecioVenta);
            @propietario = nil;
            @numCasas = 0;
            @numHoteles = 0;
            vendido = true;
        end
        return vendido;
    end    
      
    
    
    def self.main
      j1 = Jugador.new("alicia")

      tit = TituloPropiedad.new("mititulo", 100, 100, 100, 111, 111)
#      puts j1.propiedades.inspect
#      j1.propiedades<<tit
 #     tit.propietario = j1
 #     puts j1.propiedades.inspect

      puts tit.toString
      puts tit.getImporteCancelarHipoteca
      if tit.tienePropietario
        puts "si tiene propietario"
      else 
        puts "no tiene propietario"
      end
    end
    
    private :esEsteElPropietario, :getImporteHipoteca, :propietarioEncarcelado,
            :getPrecioAlquiler, :getPrecioVenta
    
    #TituloPropiedad.main
  end
end