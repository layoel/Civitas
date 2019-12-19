/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

/**
 *
 * @author ELVIRA
 */
public class TituloPropiedad {
    
    private float alquilerBase;
    static private float factorInteresesHipoteca = (float) 1.1;
    private float factorRevalorizacion;
    private float hipotecaBase;
    private Boolean hipotecado;
    private String nombre;
    private int numCasas;
    private int numHoteles;
    private float precioCompra;
    private float precioEdificar;
    private Jugador propietario;
    
    /**
     * @brief Constructor
     * @param nom nombre
     * @param ab precioBaseAlquiler
     * @param fr factorRevalorizacion
     * @param hb precioBaseHipoteca
     * @param pc precioCompra
     * @param pe precioEdificar
     * @param propietario
     * @param numCasas
     * @param numHoteles
     * @param hipotecado
     * @pre titulo propiedad comienza sin propietario ni casas ni hoteles y sin hipotecar
     */
    TituloPropiedad(String nom, float ab, float fr, float hb, float pc, float pe){
       nombre = nom;
       alquilerBase = ab;
       factorRevalorizacion = fr;
       hipotecaBase = hb;
       precioCompra = pc;
       precioEdificar = pe;
       propietario = null;
       numCasas = 0;
       numHoteles = 0;
       hipotecado = false;
    }
    
    /**
     *@brief actualizar propietario
     */
    void actualizaPropietarioPorConversion (Jugador jugador){
        propietario = jugador;
    }
    
    
    /**
     *@brief se puede cancelar la hipoteca?
     * @param jugador
     * @return result true si se ha cancelado
     */
    Boolean cancelarHipoteca(Jugador jugador){
        Boolean result = false;
        
        if (hipotecado)
            if(esEsteElPropietario(jugador)){
                result = jugador.paga(getImporteCancelarHipoteca());
                hipotecado = false;
            }
        
        return result;    
    }
    
    
    /**
     *@brief total de propiedades.
     * return total suma de casas y hoteles
     */
    int cantidadCasasHoteles(){
        int total=0;
        total = numCasas + numHoteles;
        return  total;
    }
    
    /**
     * @brief ¿puedo comprar esta propiedad?
     * @return comprado true si no tenia propietario y he pagado por ella
     */
    Boolean comprar(Jugador jugador){
       Boolean comprado = false;
       if(!tienePropietario()){
           propietario = jugador;
           comprado = propietario.paga(precioCompra);
       }
       return comprado;
    }
   
    
    /**
     * @brief ¿puedo construir una casa?
     * @param jugador el que quiere construir
     * @return construida true si ha podido construir la casa
     */
    Boolean construirCasa(Jugador jugador){
        Boolean construida = false;
        
        if(esEsteElPropietario(jugador)){
            construida = jugador.paga(precioCompra);
            numCasas++;
        }
        
        return construida;
    }
    
    
    /**
     * @brief ¿puedo construir un hotel?
     * @param jugador el que quiere construir
     * @return result true si ha podido construir el hotel
     */
    Boolean construirHotel(Jugador jugador){
        Boolean result = false;
        
        if(esEsteElPropietario(jugador)){
            jugador.paga(precioEdificar);
            numHoteles++;
            result = true;
        }
        
        return result;
    }
    
    
    /**
     *@brief el jugador quiere derruir algunas casas
     * @param n numero de casas que quiere derruir
     * @param jugador el que quiere derruir las casas
     * @return derruido true si ha conseguido derruir algunas casas
     */
    Boolean derruirCasas(int n, Jugador jugador){
        Boolean derruido = false;
        
        if(propietario == jugador && (numCasas >= n)){
            numCasas = numCasas - n;
            derruido = true;
        }
        
        return derruido;
    }
    
    /**
     *@brief consulta si un jugador es propietario
     * @param jugador
     * @return loes true si es el propietario
     */
    private Boolean esEsteElPropietario(Jugador jugador){
        Boolean loes = false;
        if(propietario == jugador)
            loes = true;
        return loes;
    }
    
    /**
     *@brief consulta si esta hipotecado
     */
    public Boolean getHipotecado(){
    return hipotecado;
    }
    
    
    /**
     * @brief calcula el importe para cancelar la hipoteca
     * @return importe  hipotecaBase * factorInteresesHipoteca;
     */
    float getImporteCancelarHipoteca(){
        return hipotecaBase * factorInteresesHipoteca; 
    }
    
    /**
     *@brief Cuanto es la hipoteca de este titulo de propiedad
     */
    private float getImporteHipoteca(){
        return hipotecaBase;
    }
    
    /**
     *@brief consulta el nombre del titulo de propiedad
     */
    public String getNombre(){
        return nombre;
    }
    
    /**
     *@brief cuantas casas tengo
     */
    public int getNumCasas(){
        return numCasas;
    }
    
    /**
     *@brief cuantos hoteles tengo
     */
    public int getNumHoteles(){
        return numHoteles;
    }
    
    /**
     *@Brief calcula el presio del alquiler segun la formula
     * PrecioAlquiler=AlquilerBase*(1+(NumCasas*0.5)+(NumHoteles*2.5)) excepto 
     * que esté hipotecado o el propietario encarcelado
     * @return alq el precio que debe pagar
     */
    private float getPrecioAlquiler(){
        float alq = (float) 0.0;
        
        if(!hipotecado && !propietarioEncarcelado())
            alq = alquilerBase*(1+(numCasas*(float) 0.5)+(numHoteles*(float) 2.5));
        
        return alq;
    }
    
    /**
     *@brief Cual es el precio de compra de la propiedad
     */
    float getPrecioCompra(){
        return precioCompra;
    }
    
    
    /**
     *@brief Cuanto cuesta edificar
     */
    float getPrecioEdificar(){
        return precioEdificar;
    }
    
    /**
     *@brief calcula el precio de venta segun la formula: 
     * numCasas * (precioCompra + precioEdificar) * numHoteles * factorRevalorizacion;
     * @return precio el precio calculado
     */
    private float getPrecioVenta(){
        float precio= (float)0.0;
        
        precio = (cantidadCasasHoteles())* (precioCompra + precioEdificar) * factorRevalorizacion;
                
        return precio;
        
    }
    
    /**
     *@brief Quien es el propietario
     */
    Jugador getPropietario(){
        return propietario;
    }
    
    
    /**
     *@brief consulta si un jugador puede  hipotecar una propiedad o no
     * @return salida true si ha podido hipotecarla
     */
    Boolean hipotecar(Jugador jugador){
        Boolean salida = false;
        
        if(!hipotecado && esEsteElPropietario(jugador)){
            jugador.recibe(getImporteHipoteca());
            hipotecado = true;
            salida = true;
        }
        
        return salida;
    }
    
    /**
     * @brief Quiero saber si el propietario está encarcelado
     * @return carcel true si lo esta
     */
    private Boolean propietarioEncarcelado(){
        Boolean carcel = false;
        if(propietario.encarcelado)
            carcel = true;
        return carcel;
    }
    
    
    /**
     *@brief consulta si tiene propietario una propiedad
     * @return tiene true si lo tiene false en otro caso.
     */
    Boolean tienePropietario(){
        Boolean tiene = false;
        if (propietario != null)
            tiene= true;
        return tiene;
    }
    

    
    /**
     *@brief proporciona una representación en forma de cadera del estado del objeto
     * @return mensaje el estado del objeto
     */
    public String toString(){
        String mensaje= new String();
        
        mensaje = "\n nombre: " + nombre +
                " \n alquilerBase: " + Float.toString(alquilerBase)+ 
                " \n factorInteresesHipoteca: " + Float.toString(factorInteresesHipoteca)+
                " \n factorRevalorizacion: " + Float.toString(factorRevalorizacion)+ 
                " \n hipotecaBase: " + Float.toString(hipotecaBase) +
                " \n hipotecado: " + Boolean.toString(hipotecado)+ 
                " \n numCasas: " + Integer.toString(numCasas)+ 
                " \n numHoteles: " + Integer.toString(numHoteles)+ 
                " \n precioCompra: " + Float.toString(precioCompra)+ 
                " \n precioEdificar: " + Float.toString(precioEdificar);
        if (propietario != null)
                mensaje = mensaje +" \n propietario: " + propietario.getNombre();
        
        return mensaje;
    }
    
    
    /**
     *@brief Transaccion de pago y recibo de alquiler por parte del inquilino 
     * y el propietario
     */
    void tramitarAlquiler(Jugador jugador){
        
        if(tienePropietario())
            if(!esEsteElPropietario(jugador)){
                float precio = getPrecioAlquiler();
                jugador.pagaAlquiler(precio);
                propietario.recibe(precio);
            }
    }
    
    /**
     *@brief si el jugador pasado como parámetro es el propietario
        del título, y éste no está hipotecado, entonces se da al propietario (método recibe de
        Jugador) el precio de venta , se desvincula el propietario de la propiedad, y se eliminan las
        casas y hoteles, devolviendo true.
     * @return ok true si puede vender
     */
    Boolean vender(Jugador jugador){
        Boolean vendido = false;
        if(esEsteElPropietario(jugador) && !hipotecado){
            jugador.recibe(getPrecioVenta());
            propietario = null;
            numCasas = 0;
            numHoteles = 0;
            vendido = true;
        }

        return vendido;
    }

    
    /*******************____MAIN PRUEBA___*************************************/
    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        // TODO code application logic here
//        System.out.println(TipoCasilla.CALLE);
//       Jugador j1 = new Jugador("alicia");
//
//      TituloPropiedad tit = new TituloPropiedad("mititulo", 100, 100, 100, 111, 111);
//      System.out.println(j1.getPropiedades().toString());
//      tit.actualizaPropietarioPorConversion(j1);
//     
//
//      System.out.println( tit.toString());
//      System.out.println(tit.getImporteCancelarHipoteca());
//      if (tit.tienePropietario())
//        System.out.println("si tiene propietario");
//      else 
//        System.out.println( "no tiene propietario");
//    }
    
}
