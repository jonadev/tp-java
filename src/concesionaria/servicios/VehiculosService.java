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
    
    
    public Vehiculo getById(Long id){
       return new Auto();
        //return repositorio.obtener(id);
    }
     
    public List<Vehiculo> getAll(){
        List<Vehiculo> vehiculos = repositorio.listar();
        
        Long id = 1L;
     
        
        Vehiculo a = repositorio.obtener(id);

   
        return vehiculos;
    }
    
}
