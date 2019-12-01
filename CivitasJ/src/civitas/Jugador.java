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
public class Jugador implements Comparable<Jugador>{
   
    static protected int CasasMax = 4;
    static protected int CasasPorHotel = 4;
    protected Boolean encarcelado;
    static protected int HotelesMax = 4;
    protected String nombre;
    protected int numCasillaActual;
    static protected float PasoPorSalida = 1000;
    static protected float PrecioLibertad = 200;
    protected Boolean puedeComprar;
    protected float saldo;
    static private float SaldoInicial= 75000;
    protected ArrayList<TituloPropiedad> propiedades;
    protected Sorpresa salvoconducto;
    
    
    /**
     * @brief constructor de jugador
     * @param n nombre del jugador
     */
    Jugador(String nombre){
        this.nombre = nombre;
        encarcelado = false;
        numCasillaActual= 0 ;
        puedeComprar = false;
        saldo = SaldoInicial;
        propiedades = new ArrayList<>();
        salvoconducto = null;
    }
    
    
    
    
    /**
     * @brief constructor de copia de jugador
     * @param otro jugador al que copia
     */
    protected Jugador (Jugador otro){
        this.nombre = otro.nombre;
        this.encarcelado = otro.isEncarcelado();
        this.HotelesMax = otro.getHotelesMax();
        this.numCasillaActual = otro.getNumCasillaActual();
        this.propiedades = otro.getPropiedades();
        this.puedeComprar = otro.getPuedeComprar();
        this.saldo = otro.getSaldo();
        this.salvoconducto = otro.salvoconducto; 
    }
    



    /**
     * @brief encarcela al jugador si no tiene salvoconducto para evitar la carcel
     * @return ok true si debe ser encarcelado
     */
    protected Boolean debeSerEncarcelado(){
        Boolean ok = false; //si ya está encarcelado o bien tiene salvoconducto
        if (!isEncarcelado()){
            if(!tieneSalvoconducto()){
                ok = true;  
            }
            else {
                perderSalvoConducto();
                Diario di = Diario.getInstance();
                di.ocurreEvento("El jugador "+ nombre + " se libra de la carcel.\n");
            }
        }
        return ok;
    }    
    
    
    
    /**
     * @brief manda a la casilla de la carcel
     * @return encarcelado true si lo ha mandado false si no.
     */
    Boolean encarcelar(int numCasillaCarcel){ 
        if (debeSerEncarcelado()){
            moverACasilla(numCasillaCarcel);
            encarcelado = true;
            Diario di =Diario.getInstance();
            di.ocurreEvento("El jugador "+ nombre + " ha sido encarcelado.\n");
        }
        return encarcelado;
    } 
    
    
    
    
    
    
    /**
     * @brief compara el saldo de ambos jugadores
     * @return 0 si son iguales
     *        -1 si jugador < otro
     *        +1 si jugador > otro
     */
    @Override
    public int compareTo(Jugador otro){
        return Float.compare(saldo, otro.getSaldo());
    }
    
    
    
    
    /**
     * @brief El jugador esta en bancarrota si tieen saldo negativo
     * @return ok true si lo está 
     */
    Boolean enBancarrota(){
        Boolean ok = false; 
        if (saldo<=0.0){
            ok = true;
        }
        return ok;
    }
        
    
    
        
    
    /**
     * @brief La cantidad de casas y hoteles que tiene el jugador
     * @return numero de propiedades
     */
    int cantidadCasasHoteles(){
        return propiedades.size();
    }
        

    
    
    
    /**
     * @brief consulta sobre si existe una propiedad en concreto.
     * @param ip identificador de la propiedad
     * @return ok true si existe
     */
     Boolean existeLaPropiedad(int ip){ //cambio la visibilidad de private a paquete
        Boolean ok = false; 
        if (ip < propiedades.size())
            ok = true;
        return ok;
    }

    
    
    
    /**
     * @brief consultor de CasasMax
     * @return numero maximo de casas
     */
    protected int getCasasMax(){
        return CasasMax;
    }
    
    
    
    /**
     * @brief Consultor de CasasPorHotel
     * @return numero de casas que se cambian por un hotel
     */
    int getCasasPorHotel(){
        return CasasPorHotel;
    }
    
    
    
    /**
     * @brief Consultor de Hoteles max
     * @return numero maximo de hoteles
     */
    protected int getHotelesMax(){
        return HotelesMax;
    }
    
    
    
    /**
     * @brief Consultor del nombre del jugador
     * @return nombre del jugador
     */
    protected String getNombre(){
        return nombre;
    }
    
    
    
    /**
     * @brief Consultor de la casilla actual
     * @return numCasillaActual
     */
    int getNumCasillaActual(){
        return numCasillaActual;
    }
    
    
    
    /**
     * @brief Consultor del precio que hay que pagar para salir en libertad
     * @return PrecioLibertad
     */
    private float getPrecioLibertad(){
        return PrecioLibertad;
    } 
    
    
    
    /**
     * @brief Consultor del premio que se obtiene por pasar por salida
     * @return PremioPasoSalida dinero que se le da al jugador.
     */
    private float getPremioPasoSalida(){
        return PasoPorSalida;
    }
    
    
    
    /**
     * @brief Consultor de propiedades
     * @return propiedades del jugador actual
     */
     public ArrayList<TituloPropiedad> getPropiedades(){
        return propiedades;
    }
    
    
    
    /**
     * @brief Consultor de puede comprar
     * @return puedeComprar
     */
    Boolean getPuedeComprar(){
        return puedeComprar;
    }
    
    
    
    /**
     * @brief Consultor de saldo
     * @return saldo
     */
    protected float getSaldo(){
        return saldo;
    }
    
    
    
    
    /**
     * @brief Consultor de encarcelado
     * @encarcelado true si lo está
     */
    public Boolean isEncarcelado(){
        return encarcelado;
    }

    
    
    
    /**
     * @brief Modificador de saldo
     * @param cantidad saldo que se incrementa o decrementa al actual
     * @return ok true si lo ha podido modificar
     */
    Boolean modificarSaldo(float cantidad){
        Boolean ok = true; 
        saldo = saldo + cantidad;
        Diario di = Diario.getInstance();
        di.ocurreEvento("El jugador " + nombre + " ha modificado su saldo a: "+saldo);
        return ok;
    }
    
    
    
    /**
     * @brief Modificador de numCasillaActual
     * @param numCasilla casilla a la que se mueve
     * @return ok true si se ha podido mover a esa casilla
     */
    Boolean moverACasilla(int numCasilla){
        Boolean ok = false; 
        if (!isEncarcelado()){
            numCasillaActual = numCasilla;
            puedeComprar = false;
            Diario di = Diario.getInstance();
            di.ocurreEvento("El jugador " + nombre+ " avanza a la casilla "+ numCasilla);
            ok = true;
        }
        return ok;
    }
    
    
    
    /**
     * @brief si no esta encarcelado se guarda la sorpresa en salvoconducto.
     * @return ok true si lo ha obtenido
     */
    Boolean obtenerSalvoconducto(Sorpresa sorpresa){
        Boolean ok = false; 
        if(!isEncarcelado()){
            salvoconducto = sorpresa;
            ok = true;
        }
        return ok;
    }
    
    
    
    /**
     * @brief modifica el saldo del jugador
     * @param cantidad cuanto ha de pagar el jugador
     * @return ok true si ha pagado correctamente.
     */
    Boolean paga(float cantidad){
        Boolean ok = modificarSaldo(cantidad*-1);
        return ok;
    }
    
    
    
    /**
     * @brief El jugador paga Alquiler
     * @param cantidad cuanto ha de pagar
     * @return ok true si lo ha pagado.
     */
    Boolean pagaAlquiler(float cantidad){
        Boolean ok = pagaImpuesto(cantidad);
        return ok;
    }
    
    
    
    /**
     * @brief El jugador paga impuesto
     * @return ok true si lo ha pagado.
     */
    Boolean pagaImpuesto(float cantidad){
        Boolean ok = false; 
        if (!isEncarcelado()){
            ok = paga(cantidad);
        }
        return ok;
    }
    
    
    
    /**
     * @brief cada vez que pasa por salida incrementa su saldo
     * @return true siempre
     */
    Boolean pasaPorSalida(){
        Boolean ok = true; 
        modificarSaldo(PasoPorSalida);
        Diario di = Diario.getInstance();
        di.ocurreEvento("El jugador "+ nombre + 
                "ha pasado por salida, incrementa su saldo en "+ PasoPorSalida);
        return ok;
    }
    
    
    
    /**
     * @brief se marca cmo usada la sorpresa y se hace nula.
     */
    protected void perderSalvoConducto(){
        ((SorpresaSalirCarcel)salvoconducto).usada();
        salvoconducto = null;
    }
    
    
    
    /**
     * @brief Solo puede comprar si no esta encarcelado
     * @return puedeComprar true si puede comprar
     */
    Boolean puedeComprarCasilla(){
         
        if (!isEncarcelado())
            puedeComprar = true;
        else
            puedeComprar=false;
        
        return puedeComprar;
    }
    
    
    
    /**
     * @brief Si tiene saldo puede pagar y salir
     * @return true si lo tiene
     */
    private Boolean puedeSalirCarcelPagando(){
        Boolean ok = false; 
        if (saldo >= PrecioLibertad )
            ok =  true;
        return ok;
    }
    
    
    

    /**
     * @brief comprueba el saldo del jugador
     * @return ok si el saldo es el que necesita para comprar
     */
    protected Boolean puedoGastar(float precio){
        Boolean ok = false; 
        if (!isEncarcelado() && (saldo >= precio))
            ok = true;
        return ok;
    }
    
    
    
    /**
     * @brief  se llama al método modificarSaldo con el
     * mismo parámetro y se devuelve lo que devuelve este
     * último método.
     * @param cantidad cuanto recibe
     * @return ok true si recibe alguna cantidad
     */
    Boolean recibe(float cantidad){
        Boolean ok = false; 
        if(!isEncarcelado()){
            ok = modificarSaldo(cantidad);
        }
        return ok;
    }
    
    
    
    /**
     * @brief consulta si tiene dinero para pagar su salida de la carcel
     * @return ok true si puede salir pagando 
     */
    Boolean salirCarcelPagando(){
        Boolean ok = false; 
        if(isEncarcelado() && puedeSalirCarcelPagando()){
            paga(PrecioLibertad);
            encarcelado = false;
            ok = true;
            Diario di = Diario.getInstance();
            di.ocurreEvento("El jugador "+ nombre+ "sale de la carcel pagando "+
                PrecioLibertad);
        }
        return ok;
    }
    
    
    
    /**
     * @brief El dado es el encargado de decir si sale de la carcel el jugador
     * @return salir true si puede salir
     */
    Boolean salirCarcelTirando(){ 
        Boolean salir = false;
        Dado da = Dado.getInstance();
        if(da.salgoDeLaCarcel()){
            encarcelado = false;
            salir = true;
            Diario di = Diario.getInstance();
            di.ocurreEvento("El jugador ha sacado "+ da.getUltimoResultado()+ 
                " tirando el dado para salir de la carcel");
        }
        return salir;
    }
    
    
    
    /**
     * @brief Si el jugador tiene propiedades
     * @return true si las tiene
     */
    Boolean tieneAlgoQueGestionar(){
        Boolean ok = false; 
        if(propiedades.size()>0)
            ok = true;
        return ok;
    }
    
    
    
    /**
     * @brief si salvoconducto no es null es que lo tiene
     * return ok true si lo tiene
     */
    Boolean tieneSalvoconducto(){
        Boolean ok = false; 
        if (salvoconducto != null)
            ok = true;
        return ok;
    }
    
    
    
    /**
     * @brief convierte a string los atributos de jugador.
     */
    public String toString(){
        String text = new String();
        
        text = "El jugador "+ nombre+ "\n Esta encarcelado? "+Boolean.toString(encarcelado)+
                "\n Está en la casilla " + Integer.toString(numCasillaActual)+ 
                "\n Puede comprar? "+ Boolean.toString(puedeComprar)+ 
                "\n Su saldo es " + Float.toString(saldo);
        if (salvoconducto != null)
            text = text+ "\n Tiene salvoconducto?: " + salvoconducto.toString();
        //if (propiedades.size()>0)
            text = text+ "\n Tienes "+ propiedades.size()+ " propiedades";
        
        /*text = text+ "\n El HotelesMax" + Integer.toString(HotelesMax)+
                "\n Su saldo inicial: " +Float.toString(SaldoInicial)+
                "\n CasasMax "+ Integer.toString(CasasMax)+
                "\n CasasPorHotel"+ Integer.toString(CasasPorHotel)+
                "\n PasoPorSalida"+ Float.toString(PasoPorSalida)+
                "\n PrecioLibertad" + Float.toString(PrecioLibertad);
        */
        return text;
    }
    
    
    
    /**
     * @brief Si el jugador es propietario vende la propiedad 
     * @param ip indice de la propiedad
     * @return ok true si ha podido venderla.
     */
    Boolean vender(int ip){
        Boolean ok = false; 
        if(!isEncarcelado()){
            if(existeLaPropiedad(ip)){
                ok = propiedades.get(ip).vender(this); //la propiedad es vendida por el jugador
                if (ok){
                    Diario di = Diario.getInstance();
                    di.ocurreEvento("el jugador "+nombre+ " ha vendido la propiedad "+
                            propiedades.get(ip).getNombre());
                    propiedades.remove(ip); 
                }
            }
        }
        return ok;
    }
         
    
    
    
    /**
     * @brief
     */
    Boolean hipotecar(int ip){
        Boolean result = false; 
        
        if(!encarcelado){
            if(existeLaPropiedad(ip)){
                TituloPropiedad propiedad = propiedades.get(ip);
                result = propiedad.hipotecar(this);
            }
            if(result)
                Diario.getInstance().ocurreEvento("El jugador "+ nombre+ "hipoteca la propiedad " + Integer.toString(ip));
        }
        
        return result; 
    }
    
    
    
    /**
     * @brief cancela la hipoteca de una propiedad perteneciente al jugador
     * @param ip el indice de la propiedad
     * @return result true si se consigue cancelar la hipoteca
     */
    Boolean cancelarHipoteca(int ip){
        Boolean result = false; 
        if(!encarcelado)
            if (this.existeLaPropiedad(ip)){
                TituloPropiedad propiedad = propiedades.get(ip);
                float cantidad = propiedad.getImporteCancelarHipoteca();
                Boolean puedoGastar = this.puedoGastar(cantidad);
                if(puedoGastar){
                    result = propiedades.get(ip).cancelarHipoteca(this);
                    if(result){
                        Diario.getInstance().ocurreEvento("El jugador " + nombre + " cancela la hipoteca de la propiedad " + ip);
                    }
                }
                
            }
        return result;
    }

    
    
    /**
     * @brief
     * @param titulo
     * @return result
     */
    Boolean comprar(TituloPropiedad titulo){
        Boolean result = false; 
        if(!encarcelado)
            if(puedeComprar){
                float precio = titulo.getPrecioCompra();
                if(puedoGastar(precio)){
                    result= titulo.comprar(this);
                    if(result){
                        propiedades.add(titulo);
                        Diario.getInstance().ocurreEvento("El jugador "+ nombre+ " compra la propiedad " + titulo.toString() );
                    }
                }
                    
            }
        return result;
    }
    
    
    
    /**
     * @brief
     */
    Boolean construirCasa(int ip){
        Boolean result = false; 
        if(!encarcelado){
            Boolean existe = existeLaPropiedad(ip);
            if(existe){
                TituloPropiedad propiedad = propiedades.get(ip);
                Boolean puedoEdificarCasa = puedoEdificarCasa(propiedad);
                if(puedoEdificarCasa){
                    result = propiedad.construirCasa(this);
                    if(result){
                    Diario.getInstance().ocurreEvento("El jugador "+ nombre+ 
                        " construye casa en la propiedad "+ Integer.toString(ip)+ 
                        " " + propiedades.get(ip).getNombre());
                    
                    }
                }
            }
        }
        return result;
    }
    
    
    
    /**
     * @brief
     */
    Boolean construirHotel(int ip){
        Boolean result = false; 
        if(encarcelado)
            return result;
        if(existeLaPropiedad(ip)){
            TituloPropiedad propiedad = propiedades.get(ip);
            Boolean puedoEdificarHotel = puedoEdificarHotel(propiedad);
            float precio = propiedad.getPrecioEdificar();
            if(puedoGastar(precio)){
                if(propiedad.getNumHoteles()<getHotelesMax()){
                    if(propiedad.getNumCasas() >= getCasasPorHotel()){
                        puedoEdificarHotel = true;
                    }
                }                       
            }
            if(puedoEdificarHotel){
                result = propiedad.construirHotel(this);
                int casasPorHotel = getCasasPorHotel();
                propiedad.derruirCasas(casasPorHotel, this);
            }else System.out.println("Necesitas 4 casas para construir un nuevo Hotel, y poder ser solvente para pagar la construccion! :) ");
            Diario.getInstance().ocurreEvento("El jugador "+ nombre+ " construye hotel en la propiedad "+ Integer.toString(ip)+" "+ propiedades.get(ip).getNombre());
            
        }
        
        return result;
    }
    
    /**
     * @brief
     * @param propiedad
     */
    protected Boolean puedoEdificarCasa(TituloPropiedad propiedad){
        Boolean ok = false; 
        float precio = propiedad.getPrecioEdificar();
        if(puedoGastar(propiedad.getPrecioEdificar()))
            if(propiedad.getNumCasas()<= getCasasMax())
                ok = true;
        return ok;
    } 
    
    
    
    /**
     * @brief
     */
    private Boolean puedoEdificarHotel(TituloPropiedad propiedad){
        Boolean ok = false; 
        float precio= propiedad.getPrecioEdificar();
        if(puedoGastar(precio)){
            if(propiedad.getNumHoteles()<= getHotelesMax())
                if(propiedad.getNumCasas()>=getCasasPorHotel())
                    ok = true;
        }else
            System.out.println("No puedes construir un nuevo hotel");
        return ok;
    }
    
    
    /*******************____MAIN PRUEBA___*************************************/
    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        // TODO code application logic here
//        System.out.println(TipoCasilla.CALLE);
//        Jugador j1 = new Jugador("Elvira");
//        Jugador j2 = j1;
//        
//        if(j1.compareTo(j2)== 0)
//            System.out.println("tienen el mismo saldo");
//        else if (j1.compareTo(j2)== 1)
//            System.out.println("j1 tiene mas pasta");
//        else
//            System.out.println("j2 tiene mas pasta");
//         System.out.println(j1.getSaldo());
//        System.out.println(j2.getSaldo());
//        System.out.println(j2.getNombre());
//        System.out.println(j2.getHotelesMax());
//        System.out.println(j2.getPuedeComprar());
//        j2.modificarSaldo(70000);
//        System.out.println(j2.getSaldo());
//        j2.paga(70000);
//        System.out.println(j2.getSaldo());
//        j2.pagaAlquiler(500);
//        System.out.println(j2.getSaldo());
//        j2.pagaImpuesto(100);
//        System.out.println(j2.getSaldo());
//        j2.recibe(100);
//        System.out.println(j2.getSaldo());
//        System.out.println(j2.tieneSalvoconducto());
//
//
//
//    }
    
}
