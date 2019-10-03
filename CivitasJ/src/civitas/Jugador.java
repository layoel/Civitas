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
public class Jugador {
    static protected int CasasMax = 4;
    static protected int CasasPorHotel = 4;
    protected Boolean encarcelado;
    protected int HotelesMax;
    private String nombre;
    private int numCasillaActual;
    static protected float PasoPorSalida = 1000;
    static protected float PrecioLibertad = 200;
    private Boolean puedeComprar;
    private float saldo;
    static private float SaldoInicial= 7500;
    private ArrayList<TituloPropiedad> propiedades;
    private Sorpresa salvoconducto;
    
    
    Boolean cancelarHipoteca(int ip){
    
    }
    
    int cantidadCasasHoteles(){
    
    }
    
    public int compareTo(Jugador otro){
    
    }
    
    Boolean comprar(TituloPropiedad titulo){
    
    }
    Boolean construirCasa(int ip){
    
    }
    Boolean construirHotel(int ip){
    
    }
    protected Boolean debeSerEncarcelado(){
    
    }
    Boolean enBancarrota(){
    
    }
    Boolean encarcelar(int numCasillaCarcel){
    
    }
    private Boolean existeLaPropiedad(int ip){
    
    }
    
    private int getCasasMax(){
        return CasasMax;
    }
    int getCasasPorHotel(){
    
    }
    int getHotelesMax(){
    
    }
    protected String getNombre(){
    
    }
    int getNumCasillaActual(){
    
    }
    private float getPrecioLibertad(){
        return PrecioLibertad;
    } 
    private float getPremioPasoSalida(){
        return PasoPorSalida;
    }
    protected ArrayList<TituloPropiedad> getPropiedades(){
    
    }
    Boolean getPuedeComprar(){
    
    }
    protected float getSaldo(){
    
    }
    Boolean hipotecar(){
    
    }
    
    public Boolean isEncarcelado(){
    
    }
    Jugador(String nombre){
    
    }
    protected Jugador (Jugador otro){
    
    }
    Boolean modificarSaldo(float cantidad){
    
    }
    Boolean moverACasilla(int numCasilla){
    
    }
    Boolean obtenerSalvoconducto(Sorpresa sorpresa){
    
    }
    Boolean paga(float cantidad){
    
    }
    Boolean pagaAlquiler(float cantidad){
    
    }
    Boolean pasaPorSalida(){
    
    }
    private void perderSalvoConducto(){
    
    }
    Boolean puedeComprarCasilla(){
    
    }
    private Boolean puedeSalirCarcelPagando(){
    
    }
    private Boolean puedoEdificarCasa(TituloPropiedad propiedad){
    
    } 
    private Boolean puedoEdificarHotel(TituloPropiedad propiedad){
    
    }
    private Boolean puedoGastar(float precio){
    
    }
    Boolean recibe(float cantidad){
    
    }
    Boolean salirCarcelPagando(){
    
    }
    Boolean salirCarcelTirando(){
    
    }
    Boolean tieneAlgoQueGestionar(){
    
    }
    Boolean tieneSalvoconducto(){
    
    }
    public String toString(){
        String hola="h";
        return hola;
    }
    Boolean vender(int ip){
    
    }
            
    
    
    
    
    
    
    
}
