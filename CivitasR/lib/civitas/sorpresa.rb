# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  
end
class Sorpresa
  
  attr_accessor :tipo, :tablero, :valor, :mazo, :texto,
    # /**
    #  *@brief Inicializa los  valores valor mazo y tablero
    # */
    def init
        @valor = -1
        @mazo = nil
        @tablero = nil
    end

#     /**
#     * @brief Constructor sorpresa que lleva a otra casilla
#     * @param tipo
#     * @param tablero
#     * @param valor
#     * @param texto
#     */
    def self.new_SorpresaIrOtraCasilla(tipo, tablero, valor, texto)
      s= new(tipo, nil)
      s.tablero = tablero
      s.valor = valor
      s.texto = texto
      return s
    end
  
  
#   /**
#     * @brief Constructor de todas las sorpresas restantes
#     * @param tipo
#     * @param valor
#     * @param texto
#     */
    def self.new_todasSorpresas(tipo, valor,texto)
        s= new(tipo, nil)
        s.valor = valor
        s.texto = texto
    end
    
    
#  /**
#     *@brief Constructor para la sorpresa evitar la carcel
#     * @param tipo
#     * @param mazo
#     */
    def self.new_sorpresaEvitaCarcel(tipo, mazo)
        s=new(tipo, nil)
        s.mazo = mazo
        s.texto = "Salvoconducto"
    end
    
#      /**
#     * @brief Constructor de la sorpresa que envia a la carcel
#     * @param tipo 
#     * @param tablero
#     */
    def initialize(tipo,tablero)
      init()
      @tipo = tipo
      @tablero = tablero
      @texto = "ve a la carcel"
    end
    
    
    
#    /**
#     *@brief comprueba si el jugador actual esta en la lista de jugadores
#     * @param actual es el jugador que tiene el turno
#     * @param todos lista de jugadores
#     * @return correcto true si es correcto el jugador
#     */
    def jugadorCorrecto(actual, todos)
        correcto = false;
        if (actual < todos.size())
            correcto = true
        end
        return correcto
    end


#    /**
#     *@brief Informa que se esta aplicando una sorpresa a un jugador
#     * @param actual es el jugador que tiene el turno
#     * @param todos lista de jugadores
#     */
    def informe(actual,todos)
      Diario.instance.ocurre_evento("se esta aplicando la sorpresa "+ @texto+
            " al jugador: " + todos.at(actual).nombre)
    end
    
    
#    /**
#     @brief aplica al jugador el tipo de sorpresa
#     * @param actual el indice del jugador que tiene el turno
#     * @param todos la lista de jugadores
#     */
    def aplicarAJugador(actual, todos)

         if (@tipo == TipoSorpresa::IRCARCEL)
             aplicarAJugador_irCarcel(actual, todos)
         end
         if (@tipo == TipoSorpresa::IRCASILLA)
             aplicarAJugador_irACasilla(actual, todos)
         end
         if (@tipo == TipoSorpresa::PAGARCOBRAR)
             aplicarAJugador_pagarCobrar(actual, todos)
         end
         if (@tipo == TipoSorpresa::PORCASAHOTEL)
             aplicarAJugador_porCasaHotel(actual, todos)
         end
         if (@tipo == TipoSorpresa::PORJUGADOR)
             aplicarAJugador_porJugador(actual, todos)
         end
         if (@tipo == TipoSorpresa::SALIRCARCEL)
             aplicarAJugador_salirCarcel(actual, todos)
         end
    end
   
   
#   /**
#     *@brief si el jugador es correcto, 
#     * ◦ se utiliza el método informe y obtiene la casilla actual del jugador
#     * ◦ se calcula la tirada utilizando el método calcularTirada(casillaActual, valor) del tablero.
#     * ◦ se obtiene la nueva posición del jugador con el método nuevaPosicion(casillaActual,tirada) del tablero.
#     * ◦ se mueve al jugador a esa nueva posición (método moverACasilla)
#     * ◦ se indica a la casilla que está en la posición del valor de la sorpresa que reciba al jugador (método recibeJugador)
#     * @param actual indice del jugador que tiene el turno
#     * @param todos lista de jugadores 
#     */
    def aplicarAJugador_irACasilla(actual, todos)
        if (jugadorCorrecto(actual, todos))
            informe(actual, todos)
            casillaActual = todos.at(actual).numCasillaActual
            tirada = @tablero.calcularTirada(casillaActual, @valor)
            nuevaPos = @tablero.nuevaPosicion(casillaActual, tirada)
            todos.at(actual).moverACasilla(nuevaPos)
            @tablero.getCasilla(nuevaPos).recibeJugador_sorpresa(actual, todos)
        end
    end
   
#    /**
#     * @brief Si el jugador es correcto, 
#     * se utiliza el método informe y 
#     * se encarcela al jugador (método encarcelar) indicado
#     * @param actual indice del jugador que tiene el turno
#     * @param todos lista de jugadores 
#     */
    def aplicarAJugador_irCarcel(actual, todos)
        if (jugadorCorrecto(actual, todos))
            informe(actual, todos)
            todos.at(actual).encarcelar(@tablero.numCasillaCarcel);
        end
    end
    
#     /**
#     * @brief si el jugador es correcto, 
#     * se utiliza el método informe 
#     * y se modifica el saldo del jugador actual(método modificarSaldo) con el valor de la sorpresa
#     * @param actual indice del jugador que tiene el turno
#     * @param todos lista de jugadores 
#     */
    def aplicarAJugador_pagarCobrar(actual, todos)
        if (jugadorCorrecto(actual, todos))
            informe(actual, todos)
            todos.at(actual).modificarSaldo(@valor)
        end
    end
    
    
#    /**
#     *@brief si el jugador es correcto,
#     * se utiliza el método informe
#     * y se modifica el saldo del jugador actual(método modificarSaldo) 
#     * con el valor de la sorpresa multiplicado por el número de casas 
#     * y hoteles del jugador.
#     * @param actual indice del jugador que tiene el turno
#     * @param todos lista de jugadores 
#     */
    def aplicarAJugador_porCasaHotel( actual, todos)
        if (jugadorCorrecto(actual, todos))
            informe(actual, todos)
            todos.at(actual).modificarSaldo(@valor*todos.at(actual).cantidadCasasHoteles)
        end
    end
    
#    /**
#     *@brief El jugador da dinero al jugador actual
#     * si el jugador es actual es correcto, 
#     * se utiliza el método informe
#     * ◦ se crea una sorpresa de tipo PAGARCOBRAR con el valor de la sorpresa multiplicado por -1 
#     * ◦ y se aplica a todos los jugadores menos el actual
#     * ◦ se crea una sorpresa de tipo PAGARCOBRAR con el valor de la sorpresa multiplicado 
#     * por el número de jugadores excluyendo al actual y se aplica solo al jugador actual.
#     * @param actual indice del jugador que tiene el turno
#     * @param todos lista de jugadores 
#     */
    def aplicarAJugador_porJugador(actual, todos)
        if (jugadorCorrecto(actual, todos))
            informe(actual, todos)
            s = Sorpresa.new_todasSorpresas(TipoSorpresa::PAGARCOBRAR, @valor*-1, "devuelve la pasta!")
            i=0;    
            while (i<todos.size)
                if(i != actual)
                  s.aplicarAJugador(i,todos)
                end
                i=i+1
            end
            Sorpresa s1 = Sorpresa.new_todasSorpresas(TipoSorpresa::PAGARCOBRAR, @valor*(todos.size-1), "tus compis te regalan pasta!")
            s1.aplicarAJugador(actual,todos);

        end
    end
    
      
      
#       /**
#     * @brief si el jugador es correcto, 
#     * se utiliza el método informe 
#     * y se pregunta a todos los jugadores si alguien tiene la sorpresa para evitar la cárcel (método tieneSalvoconducto).
#     * Si nadie la tiene, la obtiene el jugador actual (método obtenerSalvoconducto) 
#     * y se llama al método salirDelMazo.
#     */
    def aplicarAJugador_salirCarcel(actual, todos)
        if (jugadorCorrecto(actual, todos))
            informe(actual, todos)
            latienen = false
            i=0    
            if(todos.at(i).tieneSalvoconducto())
                    latienen = true;
            end
            while (i< todos.size() && !latienen)
                i=i+1
                if(todos.at(i).tieneSalvoconducto())
                    latienen = true;
                end
            end
            if(!latienen)
                Sorpresa s = Sorpresa.new_sorpresaEvitaCarcel(TipoSorpresa::SALIRCARCEL, @mazo)
                todos.at(actual).obtenerSalvoconducto(s)
                s.salirDelMazo
            end
        end
      end
    
    
    
#     /**
#     *@brief si el tipo de la sorpresa es la que evita la cárcel, 
#     * inhabilita la carta especial en el mazo de sorpresas.
#     */
      def salirDelMazo
        if(@tipo == TipoSorpresa::SALIRCARCEL)
            @mazo.inhabilitarCartaEspecial(self)
        end
      end
    
    
      
#     /**
#     * @brief didce el nombre de la sorpresa.
#     */
      def toString()
          return @texto;
      end
      
      
      
#     /**
#     *@brief si el tipo de la sorpresa es la que evita la cárcel, 
#     * habilita la carta especial en el mazo de sorpresas.
#     */
      def usada
        if(@tipo == TipoSorpresa::SALIRCARCEL)
            @mazo.habilitarCartaEspecial(self)
        end
      end
      
      private :aplicarAJugador_irACasilla, :aplicarAJugador_irCarcel, 
        :aplicarAJugador_pagarCobrar, :aplicarAJugador_porCasaHotel, 
        :aplicarAJugador_porJugador, :aplicarAJugador_salirCarcel, 
        :informe, :init

end
