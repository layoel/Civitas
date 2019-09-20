/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

/**
 *
 * @author ecastillo
 * Representa todos los tipos de casillas del juego.
 * Las casillas descanso al llegar el jugador no ocurre nada,
 * casilla salida, carcel y zonas de aparcamiento forman parte
 * de este conjunto de casillas
 */
public enum TipoCasilla {   
    CALLE,
    SORPRESA,
    JUEZ,
    IMPUESTO,
    DESCANSO
}
