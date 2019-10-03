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
    public Boolean jugadorCorrecto(int actual, ArrayList<Jugador> todos){
        Boolean correcto = false;
        if (actual < todos.size())
            correcto = true;
        return correcto;
    }
    
end
