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
        List<Vehiculo> vehiculos = repositorio.listar();
        return vehiculos;
    }
    
}
