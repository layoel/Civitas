package GUI;

import civitas.Civitas;
import civitas.Diario;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import civitas.Casilla;
import civitas.EstadosJuego;
import civitas.Jugador;
import civitas.TituloPropiedad;
import GUI.OperacionesJuego;
import GUI.Respuestas;
import GUI.SalidasCarcel;

public class VistaTextual { //cambio de paquete a public para poder usarla desde prueba que esta en paquete civitas
  
  Civitas juegoModel; 
  int iGestion=-1;
  int iPropiedad=-1;
  private static String separador = "===============================================================";
  
  private Scanner in;
  
  public VistaTextual () { //cambio de paquete a public para poder usarla desde prueba que esta en paquete civitas
    in = new Scanner (System.in);
  }
  
  void mostrarEstado(String estado) {
    System.out.println (estado);
  }
              
  void pausa() {
      if(juegoModel.getEstado()== EstadosJuego.INICIO_TURNO) /*************AÑDIDO PARA USO DEL JUEGO EN LA P3****************************/
        System.out.print ("Pulsa una tecla para tirar el dado"); /*************AÑDIDO PARA USO DEL JUEGO EN LA P3****************************/
      else
        System.out.print ("Pulsa una tecla");
    in.nextLine();
  }

  int leeEntero (int max, String msg1, String msg2) {
    Boolean ok;
    String cadena;
    int numero = -1;
    do {
      System.out.print (msg1);
      cadena = in.nextLine();
      try {  
        numero = Integer.parseInt(cadena);
        ok = true;
      } catch (NumberFormatException e) { // No se ha introducido un entero
        System.out.println (msg2);
        ok = false;  
      }
      if (ok && (numero < 0 || numero >= max)) {
        System.out.println (msg2);
        ok = false;
      }
    } while (!ok);

    return numero;
  }

  int menu (String titulo, ArrayList<String> lista) {
    String tab = "  ";
    int opcion;
    System.out.println (titulo);
    for (int i = 0; i < lista.size(); i++) {
      System.out.println (tab+i+"-"+lista.get(i));
    }

    opcion = leeEntero(lista.size(),
                          "\n"+tab+"Elige una opción: ",
                          tab+"Valor erróneo");
    return opcion;
  }

  /*
  * @brief muestra menu preguntando la forma de salir de la carcel
  */
  SalidasCarcel salirCarcel() {
    int opcion = menu ("Elige la forma para intentar salir de la carcel",
      new ArrayList<> (Arrays.asList("0 Pagando","1 Tirando el dado")));
    return (SalidasCarcel.values()[opcion]);
  }

  
  /*
  * @brief muestra el menu preguntando si quieres comprar la calle a la que has llegado
  */
  Respuestas comprar() {
      int opcion = menu ("¿Quieres comprar la calle? ",
      new ArrayList<> (Arrays.asList("SI","NO")));
      return (Respuestas.values()[opcion]);
  }

  /*
  * @brief muestra el menu preguntando por el numero de gestion inmobiliaria elegida
  */
  void gestionar () {
      
      //pregunta el numero de gestion inmobiliaria
      int opcion1 = menu ("\n Ahora puedes gestionar tus propiedades, ¿Que quieres hacer?:",
      new ArrayList<> (Arrays.asList("0 VENDER \n","1 HIPOTECAR\n","2 CANCELAR_HIPOTECA\n","3 CONSTRUIR_CASA\n","4 CONSTRUIR_HOTEL\n","5 TERMINAR\n")));
     
      //pregunta la propiedad a la que aplicar esa gestion inmobiliaria
      ArrayList<String> prop= new ArrayList<>();
      for (TituloPropiedad propiedad : juegoModel.getJugadorActual().getPropiedades() )
        prop.add(propiedad.getNombre());
    //if else añadido por mi, no aparece en los guiones es 
    //para solucionar el error de que pida tambien numero de propiedad cuando eliges terminar.  
      iGestion= opcion1;
    if(opcion1 != 5)  { 
      int opcion = menu ("Que propiedad quieres: "+ opcion1+ " ?", prop);
      iPropiedad = opcion;
    }else
      iPropiedad = -1;

      

  
  }
  
  public int getGestion(){
    return iGestion;
  }
  
  public int getPropiedad(){
      return iPropiedad;
  }
    

  void mostrarSiguienteOperacion(OperacionesJuego operacion) {
      System.out.println(separador);
      System.out.println("Ahora puedes: ");
      System.out.println(operacion);
  }


  void mostrarEventos() {
      if(Diario.getInstance().eventosPendientes())
        System.out.println("Los eventos pendientes son:");
      
      while(Diario.getInstance().eventosPendientes()){
          System.out.println(Diario.getInstance().leerEvento());
      }
  }
  
  
  /*
  * @brief da valor al atributo civitas, el modelo para que lo conozca la vista 
  * y pueda consultarlo directamente
  */
  public void setCivitas(Civitas civitas){ 
      System.out.println("----------------INICIANDO JUEGO--------------");
        juegoModel = civitas;
        this.actualizarVista(); //si no quieres que se repita el mensaje cuando inicia el juego comentar esta linea

    }
  
  /*
  * @brief muestra informacion del jugador actual, sus propiedades y la casilla actual
  */
  public void actualizarVista(){
      System.out.println(separador);
      System.out.println("****** LA INFORMACION DEL JUGADOR ACTUAL****** \n");
      System.out.println(juegoModel.getJugadorActual().toString());
      System.out.println("**************TUS PROPIEDADES SON:**********\n");
      if ( juegoModel.getJugadorActual().getPropiedades().size()> 0)
        for (TituloPropiedad propiedad : juegoModel.getJugadorActual().getPropiedades())
            System.out.println(propiedad.toString());
      else
          System.out.println("AUN NO TIENES PROPIEDADES");
      System.out.println("**************ESTAS EN LA CASILLA:**************");
      System.out.println(juegoModel.getCasillaActual().toString());
      System.out.println(separador);
  } 
}
