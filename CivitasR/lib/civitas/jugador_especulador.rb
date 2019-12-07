# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "jugador.rb"
require_relative "titulo_propiedad.rb"
#require "byebug"

module Civitas

  class JugadorEspeculador < Jugador
    
    @@FactorEspeculador = 2
    
    
    def initialize (fianza)
      @fianza = fianza
    end
    
    def self.nuevoEspeculador(jugadorActual, fianza)
      #byebug
      j = new(fianza)
      j.new_copiaJugador(jugadorActual)
      if j.propiedades !=nil
        for p in j.propiedades
          p.actualizaPropietarioPorConversion(j)# recorrer propiedades y actualizaPropietarioPorConversion
        end
      end
      return j
    end
    
    
     #    /**
  #     * @brief manda a la casilla de la carcel
  #     * @return encarcelado true si lo ha mandado false si no.
  #     */
      def encarcelar( numCasillaCarcel) 
        if (debeSerEncarcelado)
          if (@saldo>@fianza)
            puts "No vas a la carcel porque pagas la fianza"
            paga(@fianza)
            @encarcelado = false
            Diario.instance.ocurre_evento("El jugador "+ @nombre +
              " paga la fianza para librarse de la carcel.\n")
          end
        else
          super.encarcelar(numCasillaCarcel)
        end
        return @encarcelado
      end    
      
     
    
  #    /**
  #     * @brief Modificador de saldo
  #     * @param cantidad saldo que se incrementa o decrementa al actual
  #     * @return ok true si lo ha podido modificar
  #     */
      def modificarSaldo(cantidad)
        ok = false 
        if( (@saldo + cantidad*-1) >0)
          @saldo = @saldo + cantidad
          ok = true
          Diario.instance.ocurre_evento("El jugador Especulador" + @nombre +
            " ha modificado su saldo a: "+@saldo.to_s)
        end
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
  #     * @brief
  #     */
      def puedoEdificarCasa( propiedad)
          ok = false 
          if(puedoGastar(propiedad.precioEdificar))
              if(propiedad.numCasas < @@CasasMax*@@FactorEspeculador)
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
              if(propiedad.numHoteles < @@HotelesMax*@@FactorEspeculador)
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
#     * @brief convierte a string los atributos de jugador.
#     */
    def toString
      
        text =         "\nEl jugador Especulador "+ @nombre
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
      
      
  end
  
end