# encoding: UTF-8

#require "byebug"
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  
  class Jugador

  #    /**
  #     * @brief Consultor del nombre del jugador
  #     * @return nombre del jugador
  #     */  
      attr_accessor :nombre
  #    /**
  #     * @brief Consultor de la casilla actual
  #     * @return numCasillaActual
  #     */
      attr_accessor :numCasillaActual
  #    /**
  #     * @brief Consultor de propiedades
  #     * @return propiedades del jugador actual
  #     */    
      attr_accessor :propiedades, :salvoconducto
  #    /**
  #     * @brief Consultor de puede comprar
  #     * @return puedeComprar
  #     */
      attr_accessor :puedeComprar
  #    /**
  #     * @brief Consultor de saldo
  #     * @return saldo
  #     */
      attr_accessor :saldo
  #    /**
  #     * @brief Consultor de encarcelado
  #     * @encarcelado true si lo está
  #     */    
      attr_accessor :encarcelado#, 
        #:PrecioLibertad, :PasoPorSalida

      @@CasasMax = 4
      @@CasasPorHotel = 4
      @@HotelesMax = 4
      @@PasoPorSalida = 1000
      @@PrecioLibertad = 200
      @@SaldoInicial= 7500



  #  /**
  #   * @brief constructor de jugador
  #   * @param n nombre del jugador
  #   */
      def initialize (nombre)
        @nombre = nombre
        @encarcelado = false
        @numCasillaActual= 0 
        @puedeComprar = false
        @saldo = @@SaldoInicial
        @propiedades = Array.new
        @salvoconducto = nil
      end


  #  /**
  #   * @brief constructor de copia de jugador
  #   * @param otro jugador al que copia
  #   */    
      def self.new_copiaJugador(otro)
        j = new(otro.nombre)
        j.encarcelado = otro.encarcelado
        j.numCasillaActual = otro.numCasillaActual
        j.puedeComprar = otro.puedeComprar
        j.saldo = otro.saldo
        j.propiedades = otro.propiedades
        j.salvoconducto = otro.salvoconducto

        return j
      end

     def new_copiaJugador(otro)
        @nombre= otro.nombre
        @encarcelado = otro.encarcelado
        @numCasillaActual = otro.numCasillaActual
        @puedeComprar = otro.puedeComprar
        @saldo = otro.saldo
        @propiedades = otro.propiedades
        @salvoconducto = otro.salvoconducto
      end

  #    /**
  #     * @brief encarcela al jugador si no tiene salvoconducto para evitar la carcel
  #     * @return ok true si debe ser encarcelado
  #     */
      def debeSerEncarcelado
        ok = false #si ya está encarcelado o bien tiene salvoconducto
        if (!@encarcelado)
          if(!tieneSalvoconducto)
            ok = true 
          else 
            perderSalvoConducto()
            Diario.instance.ocurre_evento("El jugador "+ @nombre +
              " se libra de la carcel.\n")
          end
        end
        return ok
      end     



  #    /**
  #     * @brief manda a la casilla de la carcel
  #     * @return encarcelado true si lo ha mandado false si no.
  #     */
      def encarcelar( numCasillaCarcel) 
        if (debeSerEncarcelado)
          moverACasilla(numCasillaCarcel)
          @encarcelado = true
          Diario.instance.ocurre_evento("El jugador "+ @nombre +
              " ha sido encarcelado.\n")
        end
        return @encarcelado
      end



  #    /**
  #     * @brief si no esta encarcelado se guarda la sorpresa en salvoconducto.
  #     * @return ok true si lo ha obtenido
  #     */
      def obtenerSalvoconducto(sorpresa)
        ok = false; 
        if(!@encarcelado)
          @salvoconducto = sorpresa
          ok = true
        end
        return ok
      end



  #    /**
  #     * @brief se marca cmo usada la sorpresa y se hace nula.
  #     */
      def perderSalvoConducto
        @salvoconducto.usada
        @salvoconducto = nil
      end



  #    /**
  #     * @brief si salvoconducto no es null es que lo tiene
  #     * return ok true si lo tiene
  #     */
      def tieneSalvoconducto
        ok = false 
        if (@salvoconducto != nil)
          ok = true
        end

        return ok
      end    



  #    /**
  #     * @brief Solo puede comprar si no esta encarcelado
  #     * @return puedeComprar true si puede comprar
  #     */
      def puedeComprarCasilla
        if (!@encarcelado)
          @puedeComprar = true
        else
          @puedeComprar = false
        end
        return @puedeComprar
      end    



  #    /**
  #     * @brief modifica el saldo del jugador
  #     * @param cantidad cuanto ha de pagar el jugador
  #     * @return ok true si ha pagado correctamente.
  #     */
      def paga(cantidad)
        ok = modificarSaldo(cantidad*-1)
        return ok
      end    



  #    /**
  #     * @brief El jugador paga impuesto
  #     * @return ok true si lo ha pagado.
  #     */
      def pagaImpuesto(cantidad)
        ok = false 
        if (!@encarcelado)
          ok = paga(cantidad)
        end
        return ok
      end 



  #    /**
  #     * @brief El jugador paga Alquiler
  #     * @cantidad cuanto ha de pagar
  #     * @return ok true si lo ha pagado.
  #     */
      def pagaAlquiler(cantidad)
        ok = pagaImpuesto(cantidad)
        return ok
      end    



  #    /**
  #     * @brief  se llama al método modificarSaldo con el
  #     * mismo parámetro y se devuelve lo que devuelve este
  #     * último método.
  #     * @param cantidad cuanto recibe
  #     * @return ok true si recibe alguna cantidad
  #     */
      def recibe(cantidad)
        ok = false 
        if(!@encarcelado)
          ok = modificarSaldo(cantidad)
        end
        return ok
      end   



  #    /**
  #     * @brief Modificador de saldo
  #     * @param cantidad saldo que se incrementa o decrementa al actual
  #     * @return ok true si lo ha podido modificar
  #     */
      def modificarSaldo(cantidad)
        ok = true 
        @saldo = @saldo + cantidad
        Diario.instance.ocurre_evento("El jugador " + @nombre +
            " ha modificado su saldo a: "+@saldo.to_s)
        return ok
      end   



  #    /**
  #     * @brief Modificador de numCasillaActual
  #     * @param numCasilla casilla a la que se mueve
  #     * @return ok true si se ha podido mover a esa casilla
  #     */
      def moverACasilla(numCasilla)
        ok = false 
       
        if (!@encarcelado)
          @numCasillaActual = numCasilla
          @puedeComprar = false
          Diario.instance.ocurre_evento("El jugador " +
              @nombre+ " se mueve a la casilla "+ numCasilla.to_s)
          ok = true
        end
        return ok
      end    



  #    /**
  #     * @brief comprueba el saldo del jugador
  #     * @return ok si el saldo es el que necesita para comprar
  #     */
      def puedoGastar(precio)
        ok = false 
        if (!@encarcelado && (@saldo >= precio))
          ok = true
        end
        return ok
      end    



  #    /**
  #     * @brief Si el jugador es propietario vende la propiedad 
  #     * @param ip indice de la propiedad
  #     * @return ok true si ha podido venderla.
  #     */
      def vender(ip)
        ok = false 
        if(!@encarcelado)
          if(existeLaPropiedad(ip))
            ok = @propiedades.at(ip).vender(self) #la propiedad es vendida por el jugador
            if (ok)
              Diario.instance.ocurre_evento("el jugador "+ 
                @nombre+ " ha vendido la propiedad "+
                @propiedades.at(ip).nombre)
                @propiedades.delete_at(ip)
            end
          end
        end
        return ok
      end



  #    /**
  #     * @brief Si el jugador tiene propiedades
  #     * @return true si las tiene
  #     */
      def tieneAlgoQueGestionar
        ok = false 
        if(@propiedades.size > 0)
          ok = true
        end
        return ok
      end



  #    /**
  #     * @brief Si tiene saldo puede pagar y salir
  #     * @return true si lo tiene
  #     */
      def puedeSalirCarcelPagando
        ok = false 
        if (@saldo >= @@PrecioLibertad )
            ok =  true
        end
        return ok
      end



  #    /**
  #     * @brief consulta si tiene dinero para pagar su salida de la carcel
  #     * @return ok true si puede salir pagando 
  #     */
      def salirCarcelPagando
        #byebug
        ok = false 
        if(@encarcelado && puedeSalirCarcelPagando)
          paga(@@PrecioLibertad)
          @encarcelado = false
          ok = true
          Diario.instance.ocurre_evento("El jugador "+ 
            @nombre+ "sale de la carcel pagando "+ 
            @@PrecioLibertad.to_s)
        end
          return ok;
      end



  #    /**
  #     * @brief El dado es el encargado de decir si sale de la carcel el jugador
  #     * @return salir true si puede salir
  #     */
      def salirCarcelTirando 
        salir=false
          if(Dado.instance.salgoDeLaCarcel)
            @encarcelado = false
            salir = true
            Diario.instance.ocurre_evento("El jugador ha sacado "+ 
              Dado.instance.getUltimoResultado+ 
              " tirando el dado para salir de la carcel")
          end
        return salir
      end



  #    /**
  #     * @brief cada vez que pasa por salida incrementa su saldo
  #     * @return true siempre
  #     */
      def pasaPorSalida
        ok = true 
        modificarSaldo(@@PasoPorSalida)
        Diario.instance.ocurre_evento("El jugador "+
          @nombre + "ha pasado por salida, incrementa su saldo en "+
          @@PasoPorSalida.to_s)
        return ok
      end



  #    /**
  #     * @brief compara el saldo de ambos jugadores
  #     * @return 0 si son iguales
  #     *        -1 si jugador < otro
  #     *        +1 si jugador > otro
  #     *        nil si jugador no se puede comparar con otro
  #     */
      def compareTo( otro)
        return @saldo <=> otro.saldo
      end




  #    /**
  #     * @brief
  #     */
      def cancelarHipoteca( ip)
          result = false 
          if(!@encarcelado)
            if (existeLaPropiedad(ip))
                propiedad = @propiedades.at(ip)
                cantidad = propiedad.getImporteCancelarHipoteca()
                puedoGastar = puedoGastar(cantidad)
                if(puedoGastar)
                    result = @propiedades.at(ip).cancelarHipoteca(self)
                    if(result)
                      Diario.instance().ocurre_evento("El jugador " + @nombre + " cancela la hipoteca de la propiedad " + ip.to_s)
                    end
                end
            end
          end
          return result
      end


  #    /**
  #     * @brief
  #     */
      def comprar(titulo)
          result = false 
          if(!@encarcelado)
              if(@puedeComprar)
                  precio = titulo.precioCompra
                  if(puedoGastar(precio))
                      result= titulo.comprar(self)
                      if(result)
                          @propiedades.push(titulo)
                          Diario.instance.ocurre_evento("El jugador "+ @nombre+ " compra la propiedad " + titulo.nombre)
                      end
                  end
              end
          end
          return result
      end



  #    /**
  #     * @brief
  #     */
      def construirCasa(ip)
        result = false; 
        if(!@encarcelado)
            existe = existeLaPropiedad(ip)
            if(existe)
                propiedad = @propiedades.at(ip)
                puedoEdificarCasa = puedoEdificarCasa(propiedad)
                if(puedoEdificarCasa)
                    result = propiedad.construirCasa(self)
                    if(result)
                    Diario.instance.ocurre_evento("El jugador "+ @nombre+ " construye casa en la propiedad "+ @propiedades.at(ip).nombre)
                    end
                end
            end
        end
        
          return result
      end



  #    /**
  #     * @brief
  #     * @param
  #     */
      def construirHotel( ip)
        result = false 
        if(!@encarcelado)
            if(existeLaPropiedad(ip))
                propiedad = @propiedades.at(ip)
                puedoEdificarHotel = puedoEdificarHotel(propiedad)
                precio = propiedad.precioEdificar
                if(puedoGastar(precio))
                    if(propiedad.numHoteles < @@HotelesMax)
                        if(propiedad.numCasas >= @@CasasPorHotel)
                            puedoEdificarHotel = true
                        end
                    end
                end
                if(puedoEdificarHotel)
                    result = propiedad.construirHotel(self)
                    #casasPorHotel = @@CasasPorHotel
                    propiedad.derruirCasas(@@CasasPorHotel, self)
                end
                Diario.instance.ocurre_evento("El jugador "+ @nombre+ " construye hotel en la propiedad "+ ip.to_s+ @propiedades.at(ip).nombre)
            end
        end
        return result
      end




  #    /**
  #     * @brief
  #     */
      def puedoEdificarCasa( propiedad)
          ok = false 
          if(puedoGastar(propiedad.precioEdificar))
              if(propiedad.numCasas < @@CasasMax)
                  ok = true
              end
          end
          return ok
      end 



  #    /**
  #     * @brief
  #     */
      def puedoEdificarHotel(propiedad)
          ok = false 
          precio = propiedad.precioEdificar
          if(puedoGastar(precio))
              if(propiedad.numHoteles < @@HotelesMax)
                if(propiedad.numCasas >= @@CasasPorHotel)
                  ok = true
                end
              end
          else
            puts "No puedes construir un nuevo Hotel"
          end
          return ok
      end    



#    /**
#     * @brief El jugador esta en bancarrota si tieen saldo negativo
#     * @return ok true si lo está 
#     */
    def enBancarrota
      ok = false; 
        if (@saldo <= 0.0)
            ok = true
        end
      return ok;
    end
     
    
    
#    /**
#     * @brief consulta sobre si existe una propiedad en concreto.
#     * @param ip identificador de la propiedad
#     * @return ok true si existe
#     */
    def existeLaPropiedad(ip)
        ok = false 
        if (ip < @propiedades.size)
            ok = true
        end
        return ok
    end
    

    
    
#    /**
#     * @brief La cantidad de casas y hoteles que tiene el jugador
#     * @return numero de propiedades
#     */
    def cantidadCasasHoteles
      return  @propiedades.size
    end
    
#    /**
#     * @brief
#     */
    def hipotecar(ip)
        result = false 
        if(!@encarcelado)
            if(existeLaPropiedad(ip))
                propiedad = @propiedades.at(ip);
                result = propiedad.hipotecar(self);
            end
            if(result)
                Diario.instance.ocurre_evento("El jugador "+ @nombre+ "hipoteca la propiedad " + ip.to_s);
            end
        end
        return result 
    end
    
    
#    /**
#     * @brief convierte a string los atributos de jugador.
#     */
    def toString
      
        text =         "\nEl jugador "+ @nombre
        text = text +  "\n Esta encarcelado? "+ @encarcelado.to_s
        text = text +  "\n Esta en la casilla " + @numCasillaActual.to_s 
        text = text +  "\n Puede comprar? "+ @puedeComprar.to_s
        text = text +  "\n Su saldo es " + @saldo.to_s
        if (@salvoconducto != nil)
          text = text + "\n Tiene salvoconducto?: si, tiene la sorpresa de tipo " + @salvoconducto.tipo.to_s
        end
        if (@propiedades.size>0)
          text = text+ "\n Tienes :"+ @propiedades.size.to_s + " propiedades"
        end
        return text
      end
    
    
    
    
    def self.main
      j1= Jugador.new("elvira")
#      puts TipoCasilla::CALLE 
        j1 = Jugador.new("Elvira") 
        j2 = j1
        
        if(j1.compareTo(j2)== 0)
            puts "tienen el mismo saldo" 
        else if (j1.compareTo(j2)== 1)
              puts "j1 tiene mas pasta" 
            else
              puts "j2 tiene mas pasta" 
            end
        end
         #quitar saldo de  protected para ejecutar
#        puts j1.saldo
#        puts j2.saldo 
#        puts j2.nombre 
#        puts j2.HotelesMax 
#        puts j2.puedeComprar() 
#        Diario di = Diario.getInstance();
#        j2.modificarSaldo(70000) 
#        puts j2.getSaldo 
#        j2.paga(70000) 
#        puts j2.getSaldo 
#        j2.pagaAlquiler(500) 
#        puts j2.saldo 
#        j2.pagaImpuesto(100) 
#        puts j2.saldo 
#        j2.recibe(100) 
#        puts j2.saldo 
#        puts j2.tieneSalvoconducto() 
        puts j2.toString
      
      
    end
      
      private :existeLaPropiedad, :perderSalvoConducto, 
        :puedeSalirCarcelPagando,  :puedoEdificarCasa,
        :puedoEdificarHotel, :puedoGastar
      protected :debeSerEncarcelado
     
    #Jugador.main
  end
end