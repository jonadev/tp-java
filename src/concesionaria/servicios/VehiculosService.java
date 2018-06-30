/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concesionaria.servicios;

import concesionaria.dominio.Auto;
import concesionaria.dominio.Moto;
import concesionaria.dominio.Vehiculo;
import java.util.ArrayList;


/** 
 *
 * @author kbz
 */
public class VehiculosService {
    
    public ArrayList<Vehiculo> getAll(){
        
        return this.getRepositoryVehiculos();
    }
    
    
     public Vehiculo getById(int id){
        
        ArrayList<Vehiculo> vehiculos =  this.getRepositoryVehiculos();
        
        for (Vehiculo vehiculo : vehiculos){
            
            if (vehiculo.getId() == id) {
                return vehiculo;
            }
        }
        
        return null;
    }
    
    
    private ArrayList<Vehiculo> getRepositoryVehiculos(){
        ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
        
        Auto auto1 = new Auto();
        
        auto1.setId(1);
        auto1.setMarca("VW");
        auto1.setModelo("Gol");
        auto1.setCajaAutomatica(true);
        auto1.setCantidadAirbags(3);
        auto1.setCantidadKilometros(1000);
        auto1.setCilindrada(1600);
        auto1.setColor("Rojo");
        auto1.setLitrosBaul(300);
        auto1.setAnio(2017);
        auto1.setPrecio(180000);
        auto1.setPuertas(3);
        auto1.setTipoCombustible("Nafta");
        
        vehiculos.add(auto1);
        
        Auto auto2 = new Auto();
        
        auto2.setId(2);
        auto2.setMarca("Ford");
        auto2.setModelo("Ka");
        auto2.setCajaAutomatica(true);
        auto2.setCantidadAirbags(1);
        auto2.setCantidadKilometros(12000);
        auto2.setCilindrada(1500);
        auto2.setColor("Azul");
        auto2.setLitrosBaul(250);
        auto2.setAnio(2017);
        auto2.setPrecio(180000);
        auto2.setPuertas(3);
        auto2.setTipoCombustible("Diesel");
        
        vehiculos.add(auto2);
        
        Moto moto1 = new Moto();
        
        moto1.setId(3);
        moto1.setMarca("Zanella");
        moto1.setModelo("Sexy");
        moto1.setCajaAutomatica(false);
        moto1.setCantidadKilometros(1600);
        moto1.setCilindrada(110);
        moto1.setColor("Azul");
        moto1.setAnio(2017);
        moto1.setPrecio(20000);
        moto1.setTipoCombustible("Nafta");
        moto1.setCantidadTiempoMotor(4);
        moto1.setCascoIncluido(false);
        vehiculos.add(moto1);
        
        return vehiculos;
    }
    
}
