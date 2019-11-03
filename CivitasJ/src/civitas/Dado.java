/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;
import java.util.Random;
        
/**
 *
 * @author ELVIRA
 */


public class Dado {
   private Random rand;
   private int ultimoResultado;
   private Boolean debug;
   static private int SalidaCarcel;
    
  //singleton
    static final private Dado instance = new Dado();
    
    //visibilidad de paquete
    static Dado getInstance(){
    return instance;
    }
  //end singleton
    
    /**
     * @brief constructor de la clase dado
     */
    private Dado(){
        rand = new Random();
        ultimoResultado = 0;
        debug = false;
        SalidaCarcel =5;
    }

    /*
        Añado este método para poder inicializar el mazo de sorpresas en modo debug o no
    */
    public Boolean getDebug() {
        return debug;
    }
    
    
    
    /**
     * @brief genera un numero aleatorio entre 1 y 6 si el modo debub esta desactivado
     * @return ultimoResultado devuelve 1 en modo debug, sino el numero generado
     */
    int tirar(){
        ultimoResultado = 1;
        
        if (!debug){
            int res = rand.nextInt(6)+1;  //genera numero aleatorio entre 1 y 6
            ultimoResultado = res;
        }
        System.out.println("*-*-*-*-*-*-* Tirando el dado sacas: "+ultimoResultado+" *-*-*-*-*-*-*\n");  //para probar si funciona el método
        return ultimoResultado;
    }
    
    /**
     * @brief se tira el dado y sale de la carcel si saca 5 o mas
     * @return salgo true si sale, false no sale.
     */
    Boolean salgoDeLaCarcel(){
        
        Boolean salgo = false;
        int num = tirar();
        
        if (num >=5)
            salgo = true;
        
        return salgo;
    }
    
    /**
     * @brief decide que jugador empieza el juego
     * @param n numero de juegadores
     * @return num jugador que empieza
     */
    int quienEmpieza(int n){
        
        int jugador = rand.nextInt(n); //genera un aleatorio entre 0 y n-1
        
        return jugador;
    }
    
    /**
     * @brief modificador del atributo debug, además deja constancia en el 
     * diario del modo en el que se ha puesto del dado 
     * @param d      
     */
   void setDebug(Boolean d){
       Diario di= Diario.getInstance();
       debug = d;
       if (debug)
            di.ocurreEvento("dado debug activado");
       else
           di.ocurreEvento("dado debug desactivado");
   } 
   
   /**
    * @brief consultor de ultimo resultado
    * @return ultimoResultado
    */
   int getUltimoResultado(){
       
       return ultimoResultado;
   
   }
}
