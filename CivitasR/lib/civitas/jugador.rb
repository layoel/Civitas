# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  
  class Jugador

  #     /**
  #     * @brief consultor de CasasMax
  #     * @return numero maximo de casas
  #     */
      attr_reader :CasasMax
  #    /**
  #     * @brief Consultor de CasasPorHotel
  #     * @return numero de casas que se cambian por un hotel
  #     */
      attr_reader :CasasPorHotel
  #    /**
  #     * @brief Consultor de Hoteles max
  #     * @return numero maximo de hoteles
  #     */
      attr_reader :HotelesMax
  #    /**
  #     * @brief Consultor del nombre del jugador
  #     * @return nombre del jugador
  #     */  
      attr_reader :nombre
  #    /**
  #     * @brief Consultor de la casilla actual
  #     * @return numCasillaActual
  #     */
      attr_reader :numCasillaActual
  #    /**
  #     * @brief Consultor de propiedades
  #     * @return propiedades del jugador actual
  #     */    
      attr_reader :propiedades
  #    /**
  #     * @brief Consultor de puede comprar
  #     * @return puedeComprar
  #     */
      attr_reader :puedeComprar
  #    /**
  #     * @brief Consultor de saldo
  #     * @return saldo
  #     */
      attr_reader :saldo
  #    /**
  #     * @brief Consultor de encarcelado
  #     * @encarcelado true si lo está
  #     */    
      attr_reader :encarcelado, 
        :PrecioLibertad, :PasoPorSalida

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
            Diario.instance.ocurreEvento("El jugador "+ @nombre +
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
          Diario.instance.ocurreEvento("El jugador "+ @nombre +
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
        Diario.instance.ocurreEvento("El jugador " + @nombre +
            " ha modificado su saldo a: "+@saldo)
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
          Diario.instance.ocurreEvento("El jugador " +
              @nombre+ " se mueve a la casilla "+@numCasilla)
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
              Diario.instance.ocurreEvento("el jugador "+ 
                @nombre+ " ha vendido la propiedad "+
                @propiedades.at(ip).nombre)
              @propiedades.remove(ip) 
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
        ok = false 
        if(@encarcelado && puedeSalirCarcelPagando)
          paga(@@PrecioLibertad)
          @encarcelado = false
          ok = true
          Diario.instance.ocurreEvento("El jugador "+ 
            @nombre+ "sale de la carcel pagando "+ 
            @@PrecioLibertad)
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
            Diario.instance.ocurreEvento("El jugador ha sacado "+ 
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
        Diario.instance.ocurreEvento("El jugador "+
          @nombre + "ha pasado por salida, incrementa su saldo en "+
          @@PasoPorSalida)
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
          ok = false 

          return ok
      end


  #    /**
  #     * @brief
  #     */
      def comprar(titulo)
          Boolean ok = false 

          return ok
      end



  #    /**
  #     * @brief
  #     */
      def construirCasa(ip)
          ok = false 

          return ok
      end



  #    /**
  #     * @brief
  #     * @param
  #     */
      def construirHotel( ip)
          ok = false 

          return ok
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
          if(puedoGastar(propiedad.precioEdificar))
              if(propiedad.numHoteles < @@HotelesMax)
                  ok = true
              end
          end
          return ok
      end    



      private :existeLaPropiedad, :perderSalvoConducto, 
        :puedeSalirCarcelPagando, :CasasMax, :HotelesMax,
        :PrecioLibertad, :PasoPorSalida, :puedoEdificarCasa,
        :puedoEdificarHotel, :puedoGastar
  end
end