/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concesionaria.repositorios;

import concesionaria.DatabaseConnection;
import concesionaria.dominio.Auto;
import concesionaria.dominio.Moto;
import concesionaria.dominio.TipoVehiculo;
import concesionaria.dominio.Vehiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jona
 */
public class VehiculoRepositorio extends DatabaseConnection {
        
    public void guardar(Vehiculo vehiculo) {
        Connection con = null;
        PreparedStatement vps = null;
        PreparedStatement tps = null;
        ResultSet vrs = null;
        ResultSet trs = null;
        String vquery = "INSERT INTO vehiculo (tipo,ruedas, anio, color, "
            + "cajaAutomatica, tipoCombustible, cantidadKilometros, cilindrada, "
            + "patente, marca, modelo, precio) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        
        try {
            con = getConnection();
            
            vps = con.prepareStatement(vquery, Statement.RETURN_GENERATED_KEYS);
            vps.setInt(1, vehiculo.getTipo());
            vps.setInt(2, vehiculo.getRuedas());
            vps.setInt(3, vehiculo.getAnio());
            vps.setString(4, vehiculo.getColor());
            vps.setBoolean(5, vehiculo.isCajaAutomatica());
            vps.setString(6, vehiculo.getTipoCombustible());
            vps.setInt(7, vehiculo.getCantidadKilometros());
            vps.setInt(8, vehiculo.getCilindrada());
            vps.setString(9, vehiculo.getPatente());
            vps.setString(10, vehiculo.getMarca());
            vps.setString(11, vehiculo.getModelo());
            vps.setDouble(12, vehiculo.getPrecio());
            
            vps.executeUpdate();
            vrs = vps.getGeneratedKeys();
            
            Long idVehiculo = null;
            if(vrs.next()) {
               idVehiculo = vrs.getLong(1);
               if(vehiculo.getTipo() == TipoVehiculo.AUTO.ordinal()){
                    String aquery = "INSERT INTO auto (id_auto, puertas,"
                        + " litrosBaul, cantidadAirbags) values (?, ?, ?, ?)";
                    tps = con.prepareStatement(aquery, Statement.RETURN_GENERATED_KEYS);
                    tps.setLong(1, idVehiculo);
                    tps.setInt(2, ((Auto)vehiculo).getPuertas());
                    tps.setInt(3, ((Auto)vehiculo).getLitrosBaul());
                    tps.setInt(4, ((Auto)vehiculo).getCantidadAirbags());
                    tps.executeUpdate();
                    trs = tps.getGeneratedKeys();
               } else {
                    String aquery = "INSERT INTO moto (id_moto, cascoIncluido, "
                        + "cantidadTiempoMotor) values (?, ?, ?)";
                    tps = con.prepareStatement(aquery, Statement.RETURN_GENERATED_KEYS);
                    tps.setLong(1, idVehiculo);
                    tps.setBoolean(2, ((Moto)vehiculo).isCascoIncluido());
                    tps.setInt(3, ((Moto)vehiculo).getCantidadTiempoMotor());
                    tps.executeUpdate();
                    trs = tps.getGeneratedKeys();
               }
               
            }
            System.out.println(idVehiculo);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(VehiculoRepositorio.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            close(vps, vrs);
            close(tps, null);
        }
    }
    
    public Vehiculo obtener(Long id, Integer tipo){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Vehiculo vehiculo = null;
        
        try{
            con = getConnection();
            //if tipo select auto o moto
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
        Connection con;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Vehiculo> vehiculos = new ArrayList<>();
        
        try{
            con = getConnection();
            ps = con.prepareStatement("SELECT id, color FROM vehiculo");
            rs = ps.executeQuery();
            
            while(rs.next()){
                Auto auto1 = new Auto();
                auto1.setId(rs.getLong("id"));
                auto1.setModelo(rs.getString("color"));
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
