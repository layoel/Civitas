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
    }
    
  /**
     * @brief consultor de CasasMax
     * @return numero maximo de casas
     */
    @Override
    protected int getCasasMax(){
        return  FactorEspeculador * CasasMax;
    }
  
    
    /**
     * @brief Consultor de Hoteles max
     * @return numero maximo de hoteles
     */
    @Override
    protected int getHotelesMax(){
        return FactorEspeculador * HotelesMax;
    }
    
    
    
    /**
     * @brief encarcela al jugador si no tiene salvoconducto para evitar la carcel
     * @return ok true si debe ser encarcelado
     */
    @Override
    protected Boolean debeSerEncarcelado(){
        Boolean ok = false; //si ya está encarcelado o bien tiene salvoconducto
        if (!isEncarcelado()){
            if(!tieneSalvoconducto()){
                if(getSaldo()> fianza)
                    ok = paga(fianza); 
                if(ok){
                    ok = false; //ha pagado la fianza ya no va a la carcel
                    Diario di = Diario.getInstance();
                   di.ocurreEvento("El jugador "+ nombre + " HA PAGADO LA FIANZA y se libra de la carcel.\n");
                }
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
