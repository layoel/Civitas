# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Civitas
  
end
class Sorpresa
  def initialize
    
  end
  
    # /**
    #  *@brief Inicializa los  valores valor mazo y tablero
    # */
    def init
        @valor = -1
        @mazo = nil
        @tablero = nil
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
        return correct
    end
    
#    /**
#     *@brief Informa que se esta aplicando una sorpresa a un jugador
#     * @param actual es el jugador que tiene el turno
#     * @param todos lista de jugadores
#     */
    def informe(actual,todos)
        di = Diario.instance()
        di.ocurreEvento("se esta aplicando la sorpresa "+ @texto+" al jugador: " + 
            todos.at(actual).nombre)
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
            informe(actual, todos);
            micasilla = Casilla.new(Integer.toString(todos.at(actual).numCasillaActual));
            int nuevaPos = tablero.calcularTirada(actual, valor);
            todos.get(actual).moverACasilla(nuevaPos);
            tablero.getCasilla(nuevaPos).recibeJugador_sorpresa(actual, todos);
        end
    end
   
    
end
