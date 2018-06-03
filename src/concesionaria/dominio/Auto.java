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
public class Auto extends Vehiculo {
    private int puertas;
    private int litrosBaul;
    private int cantidadAirbags;

    public int getPuertas() {
        return puertas;
    }

    public void setPuertas(int puertas) {
        this.puertas = puertas;
    }

    public int getLitrosBaul() {
        return litrosBaul;
    }

    public void setLitrosBaul(int litrosBaul) {
        this.litrosBaul = litrosBaul;
    }

    public int getCantidadAirbags() {
        return cantidadAirbags;
    }

    public void setCantidadAirbags(int cantidadAirbags) {
        this.cantidadAirbags = cantidadAirbags;
    }

    @Override
    public double calcularComisionVendedor() {
        return this.precio * 0.10;
    }

    @Override
    public double calcularImpuesto() {
        return this.precio * 0.21;
    }
    
    
}
