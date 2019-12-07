/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.ArrayList;

/**
 *
 * @author ELVIRA
 */
public abstract class Sorpresa {
    private String texto;
    private int valor;
    private MazoSorpresas mazo;
    //private TipoSorpresa tipo; //DESAPARECE EL TIPO PORQUE AHORA ES ABSTRACTA Y ADEMÁS TIENE CLASES HIJAS QUE HEREDAN DE ELLA
    private Tablero tablero;
    

    /** 
     * @brief Constructor de sorpresa*
     *
     */
      Sorpresa(){
          init();
      }
    
      
    
    public String getTexto() {
        return texto;
    }

    public int getValor() {
        return valor;
    }

    public MazoSorpresas getMazo() {
        return mazo;
    }

    public Tablero getTablero() {
        return tablero;
    }
     
    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void setMazo(MazoSorpresas mazo) {
        this.mazo = mazo;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }
    
    
    
    /**
     * @brief Inicializa los  valores valor mazo y tablero
     */
    private void init(){
        valor = -1;
        mazo = null;
        tablero = null;
    }    
  
    
    
    /**
     * @brief comprueba si el jugador actual esta en la lista de jugadores
     * @param actual es el jugador que tiene el turno
     * @param todos lista de jugadores
     * @return correcto true si es correcto el jugador
     */
    public Boolean jugadorCorrecto(int actual, ArrayList<Jugador> todos){
        Boolean correcto = false;
        if (actual < todos.size())
            correcto = true;
        return correcto;
    }
    
    
    
    /**
     * @brief Informa que se esta aplicando una sorpresa a un jugador
     * @param actual es el jugador que tiene el turno
     * @param todos lista de jugadores
     */
     void informe(int actual, ArrayList<Jugador> todos){
        Diario di = Diario.getInstance();
        di.ocurreEvento("se esta aplicando la sorpresa: "+ texto+" al jugador: "+ todos.get(actual).getNombre());
    }
    
    
    
    /**
     * @brief aplica al jugador el tipo de sorpresa
     * @param actual el indice del jugador que tiene el turno
     * @param todos la lista de jugadores
     */
    abstract void aplicarAJugador(int actual, ArrayList<Jugador> todos);

    /**
     * @brief didce el nombre de la sorpresa.
     */
    public String toString(){
        return texto;
    }    
    


//    /*******************____MAIN PRUEBA___*************************************/
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        // TODO code application logic here
//        System.out.println(TipoCasilla.CALLE);
//    }

    
}
