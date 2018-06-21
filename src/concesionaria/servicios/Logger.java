/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concesionaria.servicios;

/**
 * Clase Singleton de logueo
 * Utilizar metodo getInstancia
 */
public class Logger {
    private static Logger log;
    private Logger(){};
    
    public static Logger getInstancia(){
        if(log == null)
            return new Logger();
        else
            return log;
    }
    
    public void log(String mensaje){
        System.out.println(mensaje);
    }
}
