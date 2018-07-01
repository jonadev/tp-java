/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package concesionaria.dominio;

/**
 *
 * @author laboratorios
 */
public class Moto extends Vehiculo {
    private boolean cascoIncluido;
    private int cantidadTiempoMotor;

    public boolean isCascoIncluido() {
        return cascoIncluido;
    }

    public void setCascoIncluido(boolean cascoIncluido) {
        this.cascoIncluido = cascoIncluido;
    }

    public int getCantidadTiempoMotor() {
        return cantidadTiempoMotor;
    }

    public void setCantidadTiempoMotor(int cantidadTiempoMotor) {
        this.cantidadTiempoMotor = cantidadTiempoMotor;
    }

    /**
     *
     * @return double, que es el calculo de comision que le corresponde a cada 
     * vendedor para cada moto
     */
    @Override
    public double calcularComisionVendedor() {
        if (this.cantidadTiempoMotor == 2) 
            return this.precio * 0.2;
        
        return this.precio * 0.1;
    }

    /**
     * Calculo de impuesto para motos
     * @return double
     */
    @Override
    public double calcularImpuesto() {
        if (this.cantidadTiempoMotor == 2) 
            return this.precio * 0.3;
        
        return this.precio * 0.21;
    }   
    
    /**
     *
     * @return entero que respresenta el tipo de vehiculo, en este caso la moto
     */
    @Override
    public int getTipo() {
        return  TipoVehiculo.MOTO.ordinal(); 
    }   
}
