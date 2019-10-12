# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

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
      attr_reader :TituloPropiedad
    
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
      new(titulo.nombre)
      @tituloPropiedad = titulo;
      @tipo = TipoCasilla.CALLE;
    end
    
    
#  /**
#   * @brief constructor de casilla impuesto
#   * @param cantidad 
#   * @param nombre de la casilla 
#   */
    def self.new_casillaImpuesto(cantidad, nombre)
      new(nombre)
      @importe = cantidad;
      @tipo = TipoCasilla::IMPUESTO;
    end
    
    
#  /** 
#   * @brief constructor de casilla juez
#   * @param numcarcel numero de la casilla carcel
#   * @param nombre el nombre de la casilla
#   */ 
    def self.new_casillaJuez(numCarcel, nombre)
      new(nombre)
      @carcel = numCarcel
      @tipo = TipoCasilla::JUEZ
    end
    
#    /**
#     * @brief constructor de casilla sorpresa
#     * @param mazo de cartas sorpresa
#     * @param nombre
#     */ 
      def self.new_casillaSorpresa(mazo, nombre)
        new(nombre)
        @mazo = mazo
        @tipo = TipoCasilla::SORPRESA
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
        @carcel= 0
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
        Diario.instance.ocurreEvento("El jugador "+ todos.at(actual).nombre)+ " ha vaido en la casilla " + self.toString
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
            todos.at(actual).encarcelar(@carcel)
        end
    end  
    
    
    
#    /**
#     * @brief represente con detalle la información acerca de la casilla
#     */
    def toString()
      text = " \n nombre: " + @nombre + 
             " \n El tipo : " + @tipo.to_s
      if @carcel > 0
        text = text +" \n carcel: "+ @carcel.to_s
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
      if @mazo != nil
          text = text + "\n mazo: " + @mazo.toString()         
      end    
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
        
    end
    
    def recibeJugador_calle( actual, todos)
        
    end
    
    def recibeJugador_sorpresa( actual, todos)
        
    end
    
    
    private :informe, :init, :recibeJugador_calle, 
      :recibeJugador_impuesto, :recibeJugador_juez, 
      :recibeJugador_sorpresa 
    
    private_class_method :new
    
  end
end