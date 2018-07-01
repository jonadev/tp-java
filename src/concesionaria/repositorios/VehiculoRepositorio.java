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
    
    public Vehiculo obtener(Long id){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Vehiculo vehiculo = null;
        
        try{
            con = getConnection();
            ps = con.prepareStatement("SELECT * FROM vehiculo LEFT JOIN auto on id_vehiculo = ? LEFT JOIN moto on id_vehiculo = ?");
            ps.setLong(1, id);
            ps.setLong(2, id);
            rs = ps.executeQuery();
            
            if(rs.next()){
                
                if(Integer.parseInt(rs.getString("tipo")) == TipoVehiculo.AUTO.ordinal() ){
                    Auto auto = new Auto();
                    this.setCommonValues(auto,rs);
                    auto.setPuertas(rs.getInt("puertas"));
                    auto.setLitrosBaul(rs.getInt("litrosBaul"));
                    auto.setCantidadAirbags(rs.getInt("cantidadAirbags"));
   System.out.println(auto.toString());
                    return auto;
          
                }else{
                      Moto moto = new Moto();
                      this.setCommonValues(moto,rs);
                      moto.setCascoIncluido(rs.getBoolean("cascoIncluido"));
                      moto.setCantidadTiempoMotor(rs.getInt("cantidadTiempoMotor"));
                      
                      return moto;
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VehiculoRepositorio.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            close(ps,rs);
        }
        
      
        return vehiculo;
    }
    
    private void setCommonValues(Vehiculo vehiculo,ResultSet rs) throws SQLException{
       
        vehiculo.setId(rs.getLong("id_vehiculo"));
        vehiculo.setRuedas(rs.getInt("ruedas"));
        vehiculo.setAnio(rs.getInt("anio"));
        vehiculo.setColor(rs.getString("color"));
        vehiculo.setCajaAutomatica(rs.getBoolean("cajaAutomatica"));
        vehiculo.setTipoCombustible(rs.getString("tipoCombustible"));
        vehiculo.setCantidadKilometros(rs.getInt("cantidadKilometros"));
        vehiculo.setCilindrada(rs.getInt("cilindrada"));
        vehiculo.setPatente(rs.getString("patente"));
        vehiculo.setMarca(rs.getString("marca"));
        vehiculo.setModelo(rs.getString("modelo"));
        vehiculo.setPrecio(rs.getDouble("precio"));
              
    }
    
    public List<Vehiculo> listar(){
        
        Connection con;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Vehiculo> vehiculos = new ArrayList<>();
        
        try{
            con = getConnection();
            ps = con.prepareStatement("SELECT id_vehiculo, tipo, marca, modelo, anio, cantidadKilometros, precio"
                    + " FROM vehiculo");
            rs = ps.executeQuery();
            
            while(rs.next()){
                
          if(rs.getInt("tipo") == TipoVehiculo.AUTO.ordinal()){
              
                Auto auto = new Auto();
                auto.setId(rs.getLong("id_vehiculo"));
                auto.setMarca(rs.getString("marca"));
                auto.setModelo(rs.getString("modelo"));
                auto.setCantidadKilometros(rs.getInt("cantidadKilometros"));
                auto.setAnio(rs.getInt("anio"));
                auto.setPrecio(rs.getDouble("precio"));
                vehiculos.add(auto);
              
          }else{
     
                Moto moto = new Moto();
                moto.setId(rs.getLong("id_vehiculo"));
                moto.setMarca(rs.getString("marca"));
                moto.setModelo(rs.getString("modelo"));
                moto.setCantidadKilometros(rs.getInt("cantidadKilometros"));
                moto.setAnio(rs.getInt("anio"));
                moto.setPrecio(rs.getDouble("precio"));
                vehiculos.add(moto);
              
          }
                
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
