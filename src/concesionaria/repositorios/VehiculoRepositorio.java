/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concesionaria.repositorios;

import concesionaria.DatabaseConnection;
import concesionaria.dominio.Auto;
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
 * @author Jona
 */
public class VehiculoRepositorio extends DatabaseConnection {
    private final String tabla = "vehiculo";
    
    public void guardar(Vehiculo vehiculo) {
        Connection con = null;
        PreparedStatement ps = null;
        
        try {
            con = getConnection();
            ps = con.prepareStatement("INSERT INTO vehiculo VALUES(?)");
            ps.setString(1,vehiculo.getColor());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(VehiculoRepositorio.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            close(ps,null);
        }
    }
    
    public Vehiculo obtener(Long id){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Vehiculo vehiculo = null;
        
        try{
            con = getConnection();
            ps = con.prepareStatement("SELECT id, color FROM vehiculo WHERE id = ?");
            ps.setLong(1, id);
            rs = ps.executeQuery();
            
            if(rs.next()){
                vehiculo = new Auto();
                vehiculo.setId(rs.getLong("id"));
                vehiculo.setColor(rs.getString("color"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VehiculoRepositorio.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            close(ps,rs);
        }
        return vehiculo;
    }
    
    public List<Vehiculo> listar(){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Vehiculo> vehiculos = null;
        
        try{
            con = getConnection();
            ps = con.prepareStatement("SELECT id, color FROM vehiculo");
            rs = ps.executeQuery();
            
            while(rs.next()){
                Auto auto = new Auto();
                auto.setId(rs.getLong("id"));
                auto.setColor(rs.getString("color"));
                vehiculos.add(auto);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VehiculoRepositorio.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            close(ps,rs);
        }
        return vehiculos;
    }
    
    public void eliminar(Long id){
        Connection con = null;
        PreparedStatement ps = null;
        
        try{
            con = getConnection();
            ps = con.prepareStatement("DELETE FROM vehiculo WHERE id = ?");
            ps.setLong(1,id);
            ps.executeUpdate();               
        } catch (SQLException ex) {
            Logger.getLogger(VehiculoRepositorio.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            close(ps,null);
        }
    }
    
    public void actualizar(Vehiculo vehiculo){
        Connection con = null;
        PreparedStatement ps = null;
        
        try{
            con = getConnection();
            ps = con.prepareStatement("UPDATE vehiculo SET color = ? WHERE id = ?");
            ps.setString(1, vehiculo.getColor());
            ps.setLong(2,vehiculo.getId());
            ps.executeUpdate();               
        } catch (SQLException ex) {
            Logger.getLogger(VehiculoRepositorio.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            close(ps,null);
        }
    }   
    
}
