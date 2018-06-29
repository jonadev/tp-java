/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concesionaria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author joni
 */
public abstract class DatabaseConnection {
    
    private String ip = "127.0.0.1";
    private String puerto = "3306";
    private String esquema = "concesionaria";
    private String user = "root";
    private String pass = "root";
    private String  connectionUrl = "jdbc:mysql://"+ ip + ":" + puerto + "/" 
            + esquema + "?" + "user=" + user + "&password=" + pass;
    private static Connection connection;
    
    protected synchronized Connection getConnection(){
        if(connection == null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(connectionUrl);
            } catch (SQLException e) {
                System.out.println("SQL Exception: "+ e.toString());
            } catch (ClassNotFoundException cE) {
                System.out.println("Class Not Found Exception: "+ cE.toString());
            }
        }
        return connection;
    }
    
    //TODO Cerrar los ps, con, etc
}
