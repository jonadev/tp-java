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
    
    /**
     * Crea o Actualiza los vehiculos dependiendo del id de vehiculos.
     * 
     * @param vehiculo 
     */
    public void crearOActualizar(Vehiculo vehiculo){
        
        if (vehiculo.getId() == null) {
            System.out.println("Creando nuevo vehiculo: "
                + (vehiculo.getTipo() == TipoVehiculo.AUTO.ordinal() ? "auto " : "moto "));
            repositorio.guardar(vehiculo);
        }
        else{
            System.out.println("Actualizando " + 
                (vehiculo.getTipo() == TipoVehiculo.AUTO.ordinal() ? "auto " : "moto ") 
                    + vehiculo.getId());
            repositorio.actualizar(vehiculo);
        }
    }
    
    /**
     * Obtiene el vehiculo a partir del id
     * 
     * @param id Long
     * @return Vehiculo
     */
    public Vehiculo getById(Long id){
        System.out.println("Obteniendo vehiculo " + id);
        return repositorio.obtener(id);
    }
     
    /**
     * Obtiene el listado de vehiculos
     * 
     * @return List
     */
    public List<Vehiculo> getAll(){
        List<Vehiculo> vehiculos = repositorio.listar();
        return vehiculos;
    }
    
    /**
     * Elimina el vehiculo relacionado con el id y el tipo
     * 
     * @param id Long
     * @param tipo int
     */
    public void eliminar(Long id, int tipo){
        System.out.println("Borrando " + 
                (tipo == TipoVehiculo.AUTO.ordinal() ? "auto " : "moto ") + id);
        repositorio.eliminar(id,tipo);
    }
    
}
