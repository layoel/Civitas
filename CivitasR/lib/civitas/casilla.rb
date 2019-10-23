# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative "tipocasilla.rb"
require_relative "titulo_propiedad.rb"
require_relative "mazo_sorpresas.rb"
require_relative "sorpresa.rb"
require_relative "tiposorpresa.rb"
require_relative "diario.rb"
module Civitas
 # /**
 #*
 #* @author ecastillo
 #* 
 #* @Bief Clase casilla 
 #*/
  class Casilla

    #/**
    #* @brief Consultor de nombre
    #* @return devuelve el nombre
    #*/
      attr_reader :nombre
    

#    /**
#     * @brief consultor del titulo de la propiedad
#     * return el titulo de la propiedad
#     */   
      attr_accessor :sorpresa, :tituloPropiedad, :importe, :tipo,:mazo
      
    
 #/**
   #* @brief constructor de casilla descanso
   #* @param nombre nombre de la casilla 
   #*/ 
    def initialize(nombre)
      init
      @nombre = nombre
      @tipo = TipoCasilla::DESCANSO;
    end
    
    
#  /**
#   * @brief constructor de casilla calle
#   * @param titulo de la propiedad 
#   */ 
    def self.new_casillaTitulo(titulo)
      c=new(titulo.nombre)
      c.tituloPropiedad = titulo
      c.tipo = TipoCasilla::CALLE
      return c
    end
    
    
#  /**
#   * @brief constructor de casilla impuesto
#   * @param cantidad 
#   * @param nombre de la casilla 
#   */
    def self.new_casillaImpuesto(cantidad, nombre)
      c = new(nombre)
      c.importe = cantidad;
      c.tipo = TipoCasilla::IMPUESTO;
    return c
    end
    
    
#  /** 
#   * @brief constructor de casilla juez
#   * @param numcarcel numero de la casilla carcel
#   * @param nombre el nombre de la casilla
#   */ 
    def self.new_casillaJuez(numCarcel, nombre)
      c= new(nombre)
      @@carcel = numCarcel
      c.tipo = TipoCasilla::JUEZ
      return c
    end
    
#    /**
#     * @brief constructor de casilla sorpresa
#     * @param mazo de cartas sorpresa
#     * @param nombre
#     */ 
      def self.new_casillaSorpresa(mazo, nombre)
        c= new(nombre)
        c.mazo = mazo
        c.tipo = TipoCasilla::SORPRESA
        return c
      end
    
#        /**
#     * @brief inicia todos los atributos por defecto
#     * @param carcel
#     * @param importe
#     * @param nombre
#     * @param tipo
#     * @param tituloPropiedad
#     * @param sorpresa
#     * @param mazo
#     */
    def init
        @importe = 0
        @nombre = "Calle"
        @tipo = TipoCasilla::DESCANSO
        @tituloPropiedad = nil
        @sorpresa = nil
        @mazo = nil
    end   



#    /**
#     *@brief informa la diario acerca del jugador que ha caido en la casilla 
#     * @param actual
#     * @param todos
#     */

      def informe (actual , todos)
        Diario.instance.ocurre_evento("El jugador "+ todos.at(actual).nombre+ " ha vaido en la casilla " + self.toString)
      end
    
      
#    /**
#     * @brief El jugador paga un impuesto por el valor que indica la casilla
#     * @param actual
#     * @param todos
#     */
    def recibeJugador_impuesto( actual, todos)
        if(jugadorCorrecto(actual,todos))
            informe(actual,todos)
            todos.at(actual).pagaImpuesto(@importe)
        end   
    end
    
#     /**
#     * @brief Se encarcela al jugador actual
#     * @param actual
#     * @param todos
#     */
    def recibeJugador_juez( actual, todos)
        if(jugadorCorrecto(actual, todos))
            informe(actual,todos)
            todos.at(actual).encarcelar(@@carcel)
        end
    end  
    
    
    
#    /**
#     * @brief represente con detalle la información acerca de la casilla
#     */
    def toString()
      text = " \n nombre: " + @nombre.to_s
      if (@tipo == TipoCasilla::CALLE )
          text =text+   " \n El tipo :  Calle"
      end
      if (@tipo == TipoCasilla::SORPRESA )
          text =text+    " \n El tipo :  sorpresa"
      end
      if (@tipo == TipoCasilla::JUEZ )
          text =text+    " \n El tipo :   Juez" +
                        " \n carcel: "+ @@carcel.to_s
      end
      if (@tipo == TipoCasilla::IMPUESTO )
          text =text+    " \n El tipo :  impuesto"
      end
      if (@tipo == TipoCasilla::DESCANSO )
          text =text+    " \n El tipo :  descanso"
      end
     
      if @importe > 0
        text = text + " \n el importe: " + @importe.to_s
      end
      if @tituloPropiedad != nil
        text = text + " \n tutiloPropiedad" + @tituloPropiedad.toString
      end
      if@sorpresa != nil
            text = text + "\n sorpresa: " + @sorpresa.toString()
      end
      #mazo no tiene toString
#      if @mazo != nil
#          text = text + "\n mazo: " + @mazo.toString()         
#      end    
        return text;
    end   
    
    
    
#    /**
#     * @brief comprueba si el indice  es valido para acceder a jugador
#     * @param actual jugador que tiene el turno
#     * @param todos todos los jugadores
#     * @return ok true si es correcto
#     */
    def jugadorCorrecto( actual, todos)
      ok = false
         if (actual < todos.size())
             ok = true
         end
      return ok
    end
    
    
    
# se implementan en proximas practicas.
    def recibeJugador( actual, todos)
        if (@tipo == TipoCasilla::CALLE)
            recibeJugador_calle(actual, todos);
        elsif (@tipo == TipoCasilla::IMPUESTO)
            recibeJugador_impuesto(actual, todos);
        elsif (@tipo == TipoCasilla::JUEZ)
            recibeJugador_juez(actual, todos);
        elsif (@tipo == TipoCasilla::SORPRESA)
            recibeJugador_sorpresa(actual, todos);
        else
            informe(actual, todos)
        end
    end
    
    
    def recibeJugador_calle( actual, todos)
      if(jugadorCorrecto(actual, todos))
         informe(actual, todos)
         jugador = self.new_copiaJugador(todos.at(actual))
         if(!@tituloPropiedad.tienePropietario)
             jugador.puedeComprarCasilla   
         else
             @tituloPropiedad.tramitarAlquiler(jugador)
         end
      end
    end
    
    
    
    
    
    def recibeJugador_sorpresa( actual, todos)
        if(jugadorCorrecto(actual, todos))
            sorpresa = @mazo.siguiente
            informe(actual, todos)
            sorpresa.aplicarAJugador(actual,todos)
        end
    end
    
    
    
    
    
    def self.main
      tit = TituloPropiedad.new("mititulo", 100, 100, 100, 111, 111)
      cas = Casilla.new_casillaTitulo(tit)
      puts cas.inspect
      cas2 = Casilla.new_casillaImpuesto(100,"hola" )
      puts cas2.inspect
      
      puts "creando casilla de salida"
      salida = Casilla.new("Salida") 
      puts salida.toString

      puts "\ncreando casilla calle" 
      titulo = TituloPropiedad.new("calle pollito" ,1.0, 2.0, 3.0, 4.0, 5.0)
      calle1 = Casilla.new_casillaTitulo(titulo)
      puts calle1.inspect

      puts "\ncreando casilla impuesto:" 
      impuesto = Casilla.new_casillaImpuesto(  200.36, "impuesto de sucesiones") 
      puts impuesto.inspect

      puts "\ncreando casilla Juez:" 
      juez = Casilla.new_casillaJuez(5, "Soy el Juez") 
      puts juez.inspect

      puts "\ncreando casilla Sorpresa:" 
      m = MazoSorpresas.new
      s = Sorpresa.new_todasSorpresas(TipoSorpresa::PAGARCOBRAR,100, "has conseguido 100 monedas!" )
      m.alMazo(s)
      sor = Casilla.new_casillaSorpresa(m, "Toma sorpresa!" )
      puts "hola"
      puts sor.inspect


       j1 = Jugador.new(" Elvira "  )
       j2 = Jugador.new(" Ale "  )
       j3 = Jugador.new(" Jolu "  )
       j4 = Jugador.new(" Iballa "  )

      todos = Array.new

      todos<<j1
      todos<<j2
      todos<<j3
      todos<<j4


      puts sor.jugadorCorrecto(1,todos)
      sor.informe(2, todos) #metodo privado
      puts Diario.instance.leer_evento

      puts "no existen tantos jugadores" 
      
    end
       
     
    private :init, :recibeJugador_calle, 
      :recibeJugador_impuesto, :recibeJugador_juez, 
      :recibeJugador_sorpresa 
    #protected :informe
    
  end
  
  Casilla.main
  
end