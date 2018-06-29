/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concesionaria.repositorios;

import concesionaria.DatabaseConnection;
import concesionaria.dominio.Vehiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joni
 */
public class VehiculoRepositorio extends DatabaseConnection {
    private final String tabla = "vehiculo";
    
    public void guardar() {
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("select asd from vehiculo");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) { 
                System.out.println (rs.getString("asd")); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(VehiculoRepositorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Vehiculo obtener(Long id){
        return null;
    }
    
    public List<Vehiculo> listar(){
        return null;
    }
    
    public void eliminar(){
    
    }
    
}
