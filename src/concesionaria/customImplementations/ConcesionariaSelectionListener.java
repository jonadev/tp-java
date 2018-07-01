/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concesionaria.customImplementations;

import concesionaria.Concesionaria;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author kbz
 */
public class ConcesionariaSelectionListener implements  ListSelectionListener {

    private Concesionaria concesionaria;
    
    public ConcesionariaSelectionListener(Concesionaria concesionaria){
        
        this.concesionaria = concesionaria;
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        
        Long idVehiculo = Long.parseLong(this.concesionaria.getVehiculosTable().getValueAt(this.concesionaria.getVehiculosTable().getSelectedRow(), 0).toString());
        this.concesionaria.openEditVehiculo(idVehiculo);
    }
    
}
