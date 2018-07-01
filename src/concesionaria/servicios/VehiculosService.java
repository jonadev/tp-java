/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concesionaria.servicios;

import concesionaria.dominio.Auto;
import concesionaria.dominio.Moto;
import concesionaria.dominio.TipoVehiculo;
import concesionaria.dominio.Vehiculo;
import concesionaria.repositorios.VehiculoRepositorio;
import java.util.List;



/** 
 *
 * @author kbz
 */
public class VehiculosService {
    
    VehiculoRepositorio repositorio = new VehiculoRepositorio();
    
    public void crear(Vehiculo vehiculo){
        
        repositorio.guardar(vehiculo);
    }
    
    public void crearOActualizar(Vehiculo vehiculo){
        
        if (vehiculo.getId() == null) {
            System.out.println("Creando nuevo vehiculo: "
                + (vehiculo.getTipo() == TipoVehiculo.AUTO.ordinal() ? "auto " : "moto "));
            repositorio.guardar(vehiculo);
        }
        else{
            System.out.println("Borrando " + 
                (vehiculo.getTipo() == TipoVehiculo.AUTO.ordinal() ? "auto " : "moto ") 
                    + vehiculo.getId());
            repositorio.actualizar(vehiculo);
        }
    }
    
    
    public Vehiculo getById(Long id){
        System.out.println("Obteniendo vehiculo " + id);
        return repositorio.obtener(id);
    }
     
    public List<Vehiculo> getAll(){
        List<Vehiculo> vehiculos = repositorio.listar();
        return vehiculos;
    }
    
    public void eliminar(Long id, int tipo){
        System.out.println("Borrando " + 
                (tipo == TipoVehiculo.AUTO.ordinal() ? "auto " : "moto ") + id);
        repositorio.eliminar(id,tipo);
    }
    
}
