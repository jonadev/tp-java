/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concesionaria.customImplementations;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kbz
 */
public class VehiculosTableModel extends DefaultTableModel {

    public VehiculosTableModel(Object[] columnNames, int rowCount) {
        super(columnNames, rowCount);
    }
     
     @Override
     public boolean isCellEditable(int row, int column){  
          return false;  
      }
}
