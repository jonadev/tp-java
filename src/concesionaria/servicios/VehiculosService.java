/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concesionaria.servicios;

import concesionaria.dominio.Auto;
import concesionaria.dominio.Moto;
import concesionaria.dominio.Vehiculo;
import concesionaria.repositorios.VehiculoRepositorio;
import java.util.List;



/** 
 *
 * @author kbz
 */
public class VehiculosService {
    
    VehiculoRepositorio repositorio = new VehiculoRepositorio();
    
    public void Crear(Vehiculo vehiculo){
        
        repositorio.guardar(vehiculo);
    }
    
    public void CrearOActualizar(Vehiculo vehiculo){
        
        if (vehiculo.getId() == null) {
            repositorio.guardar(vehiculo);
        }
        else{
             repositorio.actualizar(vehiculo);
        }
    }
    
    
    public Vehiculo getById(Long id){
        return repositorio.obtener(id);
    }
    
    public List<Vehiculo> getAll(){
        List<Vehiculo> vehiculos  = repositorio.listar();
           
        Auto auto1 = new Auto();
        
        auto1.setId(Long.parseLong("1"));
        auto1.setMarca("VW");
        auto1.setModelo("Gol");
        auto1.setPatente("AA00044");
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
        
        auto2.setId(Long.parseLong("2"));
        auto2.setMarca("Ford");
        auto2.setModelo("Ka");
        auto2.setPatente("AA00044");
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
        
        moto1.setId(Long.parseLong("3"));
        moto1.setMarca("Zanella");
        moto1.setModelo("Sexy");
        moto1.setPatente("AA00044");
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
