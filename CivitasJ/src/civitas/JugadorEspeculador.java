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
public class JugadorEspeculador extends Jugador{
    
    protected static int FactorEspeculador = 2;
    int fianza;
    
    
    /**
     * @brief constructor de jugador convierte a un jugador en un especulador.
     * Cambia de propietario sus propiedades para asignarlas al especulador
     * @param nombre nombre del jugador
     */
    JugadorEspeculador(Jugador otro, int fianza){
        super(otro);
        this.fianza = fianza ;
        this.HotelesMax = getHotelesMax();
        this.CasasMax = getCasasMax();
        for(TituloPropiedad p : otro.propiedades)
            p.actualizaPropietarioPorConversion(this);
        especulador = true;
    }

    @Override 
    Boolean encarcelar(int numCasillaCarcel){ 
        if(debeSerEncarcelado())
            if (saldo>fianza) {
                System.out.println("No vas a la carcel porgue pagas la fianza");
                paga(fianza);
                encarcelado=false;
                
                Diario di = Diario.getInstance();
                di.ocurreEvento("El jugador especulador "+ nombre + " paga fianza para librarse de la carcel.\n");
            }
        else
            super.encarcelar(numCasillaCarcel);
        return encarcelado;
    }
    
    
    /**
     * @brief Modificador de saldo
     * @param fianza saldo que se incrementa o decrementa al actual
     * @return ok true si lo ha podido modificar
     */
    @Override
    Boolean modificarSaldo(float cantidad){
        Boolean ok = false; 
        if ((saldo + cantidad*-1) > 0){
            saldo = saldo + cantidad;
            ok = true;
            Diario di = Diario.getInstance();
            di.ocurreEvento("El jugador Especulador" + nombre + " ha modificado su saldo a: "+saldo);
        }
        return ok;
    }
    
    
    
    /*
     * @brief El jugadorEspeculador paga la mitad de impuesto
     * @return ok true si lo ha pagado.
     */
    @Override
    Boolean pagaImpuesto(float cantidad){
        Boolean ok = false; 
        if (!isEncarcelado()){
            ok = paga(cantidad/2);
        }
        return ok;
    }
    
    
    /**
     * @brief
     * @param propiedad
     * @return ok
     */
    @Override
    protected Boolean puedoEdificarCasa(TituloPropiedad propiedad){
        Boolean ok = false; 
        float precio = propiedad.getPrecioEdificar();
        if(puedoGastar(propiedad.getPrecioEdificar()))
            if(propiedad.getNumCasas()< getCasasMax()*FactorEspeculador)
                ok = true;
        return ok;
    } 
    
    
    
    /**
     * @brief
     * @param propiedad
     * @return
     */
    @Override
    protected Boolean puedoEdificarHotel(TituloPropiedad propiedad){
        Boolean ok = false; 
        float precio= propiedad.getPrecioEdificar();
        if(puedoGastar(precio)){
            if(propiedad.getNumHoteles()< getHotelesMax()*FactorEspeculador)
                if(propiedad.getNumCasas()>=getCasasPorHotel())
                    ok = true;
        }else
            System.out.println("No puedes construir un nuevo hotel");
        return ok;
    }

    /**
     * @brief convierte a string los atributos de jugador.
     * @return text texto con la informacion del jugador especulador
     */
    @Override
    public String toString(){
        String text = new String();
        
        text = "El jugador "+ nombre+ " Es un ESPECULADOR \n Esta encarcelado? "+Boolean.toString(encarcelado)+
                "\n La fianza es: " + Integer.toString(fianza) +
                "\n Está en la casilla " + Integer.toString(numCasillaActual)+ 
                "\n Puede comprar? "+ Boolean.toString(puedeComprar)+ 
                "\n Su saldo es " + Float.toString(saldo);
        if (salvoconducto != null)
            text = text+ "\n Tiene salvoconducto?: " + salvoconducto.toString();
            text = text+ "\n Tienes " + propiedades.size()+ " propiedades";
            
        return text;
    }
    
    
    
}
