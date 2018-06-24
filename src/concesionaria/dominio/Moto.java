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

    @Override
    public double calcularComisionVendedor() {
        if (this.cantidadTiempoMotor == 2) 
            return this.precio * 0.2;
        
        return this.precio * 0.1;
    }

    @Override
    public double calcularImpuesto() {
        if (this.cantidadTiempoMotor == 2) 
            return this.precio * 0.3;
        
        return this.precio * 0.21;
    }   
}
