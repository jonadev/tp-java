/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concesionaria;

import concesionaria.dominio.Vehiculo;
import concesionaria.servicios.Logger;
import concesionaria.servicios.VehiculosService;
import concesionaria.views.DesignConcesionariaList2;
import concesionaria.views.ListadoVehiculos;
import concesionaria.views.prueba;
import java.awt.Component;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author joni
 */
public class Concesionaria extends javax.swing.JFrame {

    /**
     * Creates new form Listado
     */
    public Concesionaria() {
        initComponents();
        
        ListadoVehiculos listadoVehiculos = new ListadoVehiculos();
        
      
    /*   java.awt.EventQueue.invokeLater(new Runnable() {
    @Override
    public void run() {
        listadoVehiculos.toFront();
        listadoVehiculos.repaint();
        listadoVehiculos.setVisible(true);
    }
});*/

       // listadoVehiculos.dispatchEvent(new WindowEvent(listadoVehiculos, WindowEvent.WINDOW_CLOSING));
        Logger logger = Logger.getInstancia();
        logger.log("poniendo panel");
        
         vehiculosTable.removeAll();
        
        VehiculosService vehiculoService = new VehiculosService();
        
         ArrayList<Vehiculo> vehiculos = vehiculoService.getAll();
         
            
         String[] columnNames = {
                        "Marca",
                        "Modelo",
                        "Año",
                        "Kilometros",
                        "Precio"};
         
         Object[][] data = {
            {"Kathy", "Smith",
             "Snowboarding", new Integer(5), new Boolean(false)},
            {"John", "Doe",
             "Rowing", new Integer(3), new Boolean(true)},
            {"Sue", "Black",
             "Knitting", new Integer(2), new Boolean(false)},
            {"Jane", "White",
             "Speed reading", new Integer(20), new Boolean(true)},
            {"Joe", "Brown",
             "Pool", new Integer(10), new Boolean(false)}
        };
         
         DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
          
         vehiculosTable.setModel(tableModel);
         
         for (Vehiculo vehiculo : vehiculos) {
             
            Object[] vehiculosData = new Object[] {vehiculo.getMarca(), 
                                                    vehiculo.getModelo(), 
                                                    vehiculo.getAnio(), 
                                                    vehiculo.getCantidadKilometros(), 
                                                    vehiculo.getPrecio(), 
                                                    };
 
            tableModel.addRow(vehiculosData);
        }
         
          
         logger.log("Set Table");
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        vehiculosTable = new javax.swing.JTable();
        panel3 = new java.awt.Panel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        vehiculosTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(vehiculosTable);

        panel3.setBackground(new java.awt.Color(21, 77, 161));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Concesionaria Buenos Aires");

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel3Layout.createSequentialGroup()
                .addGap(218, 218, 218)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(168, Short.MAX_VALUE))
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 901, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(53, 53, 53)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(164, 164, 164)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Concesionaria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Concesionaria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Concesionaria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Concesionaria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Logger logger = Logger.getInstancia();
                logger.log("Iniciando Concesionaria");
                new Concesionaria().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Panel panel1;
    private java.awt.Panel panel2;
    private java.awt.Panel panel3;
    private javax.swing.JTable vehiculosTable;
    // End of variables declaration//GEN-END:variables
}
