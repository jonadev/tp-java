/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concesionaria.views;

import concesionaria.Concesionaria;
import concesionaria.dominio.Auto;
import concesionaria.dominio.Moto;
import concesionaria.dominio.TipoVehiculo;
import concesionaria.dominio.Vehiculo;
import concesionaria.servicios.VehiculosService;
import java.awt.Color;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author kbz
 */
public class EdicionVehiculosPanel extends javax.swing.JPanel {

    /**
     * Creates new form testPanel
     */
    private Long idVehiculo;
    private JFrame container;
    private Concesionaria concesionariaFrame;
    
    public EdicionVehiculosPanel(JFrame container, Concesionaria concesionariaFrame) {
        initComponents();
        this.button3.setLabel("GUARDAR");
        this.button3.setBackground(Color.GREEN);
        this.button2.setVisible(false);
        this.jTextFieldImpuestos.setVisible(false);
        this.jLabel19.setVisible(false);
        this.jLabel20.setVisible(false);
        this.jTextFieldComisionVendedor.setVisible(false);
        this.setAutoVisibilityFields(false);
        this.setMotoVisibilityFields(false);
        this.container = container;
        this.concesionariaFrame = concesionariaFrame;
    }
    
    public EdicionVehiculosPanel(Long idVehiculo, JFrame container,Concesionaria concesionariaFrame) {
        
        initComponents();
        this.jTextFieldImpuestos.setEditable(false);
        this.jTextFieldImpuestos.setEnabled(false);
        this.jTextFieldComisionVendedor.setEditable(false);
        this.jTextFieldComisionVendedor.setEnabled(false);
        this.idVehiculo = idVehiculo;
        this.SetVehiculoInForm(idVehiculo);
        this.container = container;
        this.concesionariaFrame = concesionariaFrame;
    }
    
    private void SetVehiculoInForm(Long idVehiculo){
        
        VehiculosService vehiculosService = new VehiculosService();
        
        Vehiculo vehiculo =  vehiculosService.getById(idVehiculo);
        
        if (vehiculo == null) 
            return;
        
        this.setVisibilityFieldsByVehiculo(vehiculo);
        
        jTextFieldMarca.setText(vehiculo.getMarca());
        jTextFieldPatente.setText(vehiculo.getPatente());
        jTextFieldColor.setText(vehiculo.getColor());
        jTextFieldAnio.setText(Integer.toString(vehiculo.getAnio()));
        jTextFieldCantidadRuedas.setText(Integer.toString(vehiculo.getRuedas()));
        jTextFieldModelo.setText(vehiculo.getModelo());
        jTextFieldTipoCombustible.setText(vehiculo.getTipoCombustible());
        jTextFieldCantidadKilometros.setText(Integer.toString(vehiculo.getCantidadKilometros()));
        jTextFieldCilindrada.setText(Integer.toString(vehiculo.getCilindrada()));
        jTextFieldPrecio.setText(Double.toString(vehiculo.getPrecio()));
        
        jCheckBoxCajaAutomatica.setSelected(vehiculo.isCajaAutomatica());
        
        jTextFieldComisionVendedor.setText(Double.toString(vehiculo.calcularComisionVendedor()));
        jTextFieldImpuestos.setText(Double.toString(vehiculo.calcularImpuesto()));
        
        this.setCustomFields(vehiculo);
    }
    
    private void setVisibilityFieldsByVehiculo(Vehiculo vehiculo){
        
        jLabelCantidadPuertas.setVisible(vehiculo.getClass() == Auto.class);
        jLabelCantidadAirbags.setVisible(vehiculo.getClass() == Auto.class);
        jLabelLitrosBaul.setVisible(vehiculo.getClass() == Auto.class);
        jLabelIncluyeCasco.setVisible(vehiculo.getClass() == Moto.class);
        jLabelCantidadTiempos.setVisible(vehiculo.getClass() == Moto.class);
        
        jTextFieldCantidadPuertas.setVisible(vehiculo.getClass() == Auto.class);
        jTextFieldCantidadAirbags.setVisible(vehiculo.getClass() == Auto.class);
        jTextFieldLitrosBaul.setVisible(vehiculo.getClass() == Auto.class);
        jCheckBoxIncluyeCasco.setVisible(vehiculo.getClass() == Moto.class);
        jTextFieldCantidadTiempos.setVisible(vehiculo.getClass() == Moto.class);
    }
    
    private void setAutoVisibilityFields(Boolean visible){
        
        jLabelCantidadPuertas.setVisible(visible);
        jLabelCantidadAirbags.setVisible(visible);
        jLabelLitrosBaul.setVisible(visible);
        
        jTextFieldCantidadPuertas.setVisible(visible);
        jTextFieldCantidadAirbags.setVisible(visible);
        jTextFieldLitrosBaul.setVisible(visible);
    }
    
    private void setMotoVisibilityFields(Boolean visible){
        
        jLabelIncluyeCasco.setVisible(visible);
        jLabelCantidadTiempos.setVisible(visible);
        
        jCheckBoxIncluyeCasco.setVisible(visible);
        jTextFieldCantidadTiempos.setVisible(visible);
    }
    
    private void setCustomFields(Vehiculo vehiculo){
        if (vehiculo.getClass() == Auto.class) {
            
            Auto auto = (Auto) vehiculo;
            
            jRadioButtonAuto.setSelected(true);
            
            jTextFieldCantidadPuertas.setText(Integer.toString(auto.getPuertas()));
            jTextFieldCantidadAirbags.setText(Integer.toString(auto.getCantidadAirbags()));
            jTextFieldLitrosBaul.setText(Integer.toString(auto.getLitrosBaul()));
        }
        else if(vehiculo.getClass() == Moto.class){
             
            Moto moto = (Moto) vehiculo;
            jRadioButtonMoto.setSelected(true);
            jCheckBoxIncluyeCasco.setSelected(moto.isCascoIncluido());
            jTextFieldCantidadTiempos.setText(Integer.toString(moto.getCantidadTiempoMotor()));
        }
    }
    
    private boolean validaciones(){

        
        //Validacion campo Marca
        if(jTextFieldMarca.getText().isEmpty()){

            JOptionPane.showMessageDialog(null, "El campo marca no puede ser vacio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else if (!(Pattern.matches("^(\\w+ ?)*$", jTextFieldMarca.getText())))  {

            JOptionPane.showMessageDialog(null, "El campo marca no puede contener caracteres especiales.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        //Validacion campo Patente

        if(jTextFieldPatente.getText().isEmpty()){

            JOptionPane.showMessageDialog(null, "El campo patente no puede ser vacio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else if (!(Pattern.matches("^(\\w+ ?)*$", jTextFieldPatente.getText())))  {
          JOptionPane.showMessageDialog(null, "El campo patente no puede contener caracteres especiales.", "Error", JOptionPane.ERROR_MESSAGE);
          return false;
        }

        //Validacion campo Color
        if(jTextFieldColor.getText().isEmpty()){

            JOptionPane.showMessageDialog(null, "El campo color no puede ser vacio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else if (!(Pattern.matches("^[0-9a-zA-Z]+$", jTextFieldColor.getText())))  {
           JOptionPane.showMessageDialog(null, "El campo color no puede contener caracteres especiales.", "Error", JOptionPane.ERROR_MESSAGE);
           return false;
        }


        //Validacion campo Año
        if(jTextFieldAnio.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "El campo año no puede ser vacio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else if (!(Pattern.matches("^[0-9a-zA-Z]+$", jTextFieldAnio.getText())))  {
           JOptionPane.showMessageDialog(null, "El campo año no puede contener caracteres especiales.", "Error", JOptionPane.ERROR_MESSAGE);
           return false;
        }
        else if(!(Pattern.matches("^\\d{4}$", jTextFieldAnio.getText())))  {
            JOptionPane.showMessageDialog(null, "El campo año tiene que tener 4 caracteres.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;

        }

        //Validacion campo Modelo

        if(jTextFieldModelo.getText().isEmpty()){

            JOptionPane.showMessageDialog(null, "El campo modelo no puede ser vacio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else if (!(Pattern.matches("^(\\w+ ?)*$", jTextFieldModelo.getText())))  {
           JOptionPane.showMessageDialog(null, "El campo modelo no puede contener caracteres especiales.", "Error", JOptionPane.ERROR_MESSAGE);
           return false;
        }

        //Validacion campo Tipo Combustible

        if(jTextFieldTipoCombustible.getText().isEmpty()){

            JOptionPane.showMessageDialog(null, "El campo tipo combustible no puede ser vacio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else if (!(Pattern.matches("^(\\w+ ?)*$", jTextFieldTipoCombustible.getText())))  {
           JOptionPane.showMessageDialog(null, "El campo tipo combustible no puede contener caracteres especiales.", "Error", JOptionPane.ERROR_MESSAGE);
           return false;
        }

        //Validacion campo Cantidad Kilometros

        if(jTextFieldCantidadKilometros.getText().isEmpty()){

            JOptionPane.showMessageDialog(null, "El campo cantidad de kilometros no puede ser vacio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else if (!(Pattern.matches("^(\\w+ ?)*$", jTextFieldCantidadKilometros.getText())))  {
           JOptionPane.showMessageDialog(null, "El campo cantidad de kilometros no puede contener caracteres especiales.", "Error", JOptionPane.ERROR_MESSAGE);
           return false;
        }

        //Validacion campo Cilindrada

        if(jTextFieldCilindrada.getText().isEmpty()){

            JOptionPane.showMessageDialog(null, "El campo cilindrada no puede ser vacio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else if (!(Pattern.matches("^(\\w+ ?)*$", jTextFieldCilindrada.getText())))  {
           JOptionPane.showMessageDialog(null, "El campo cilindrada no puede contener caracteres especiales.", "Error", JOptionPane.ERROR_MESSAGE);
           return false;
        }

         //Validacion campo Precio

        if(jTextFieldPrecio.getText().isEmpty()){

            JOptionPane.showMessageDialog(null, "El campo precio no puede ser vacio.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else if (!(Pattern.matches("^[0-9]+([.][0-9]+)?$", jTextFieldPrecio.getText())))  {
           JOptionPane.showMessageDialog(null, "El campo precio solo puede tener valores decimales con . (punto)", "Error", JOptionPane.ERROR_MESSAGE);
           return false;
        }

        if(this.jRadioButtonAuto.isSelected()) {
            // VALIDAR LITROS DE AUTO
            //Validacion campo Cantidad Puertas DE AUTO
            
            if(jTextFieldCantidadPuertas.getText().isEmpty()){

                JOptionPane.showMessageDialog(null, "El campo cantidad de puertas no puede ser vacio.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            else if (!(Pattern.matches("^\\d+$", jTextFieldCantidadPuertas.getText())))  {
               JOptionPane.showMessageDialog(null, "El campo cantidad de puertas solo puede tener valores enteros", "Error", JOptionPane.ERROR_MESSAGE);
               return false;
            }

            //Validacion campo Cantidad Airbags DE AUTO

            if(jTextFieldCantidadAirbags.getText().isEmpty()){

                JOptionPane.showMessageDialog(null, "El campo cantidad de airbag no puede ser vacio.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            else if (!(Pattern.matches("^\\d+$", jTextFieldCantidadAirbags.getText())))  {
               JOptionPane.showMessageDialog(null, "El campo cantidad de airbag solo puede tener valores enteros", "Error", JOptionPane.ERROR_MESSAGE);
               return false;
            }
        }else {
          //Validacion campo Cantidad Tiempos DE MOTO

            if(jTextFieldCantidadTiempos.getText().isEmpty()){

                JOptionPane.showMessageDialog(null, "El campo cantidad de tiempo no puede ser vacio.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            else if (!(Pattern.matches("^\\d+$", jTextFieldCantidadTiempos.getText())))  {
               JOptionPane.showMessageDialog(null, "El campo cantidad de tiempo solo puede tener valores enteros", "Error", JOptionPane.ERROR_MESSAGE);
               return false;
            }
        }
      
        return true;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jTextFieldColor = new javax.swing.JTextField();
        panel1 = new java.awt.Panel();
        jLabel1 = new javax.swing.JLabel();
        button2 = new java.awt.Button();
        button3 = new java.awt.Button();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldMarca = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldPatente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldAnio = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldModelo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldTipoCombustible = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldCantidadKilometros = new javax.swing.JTextField();
        jTextFieldCilindrada = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldPrecio = new javax.swing.JTextField();
        jLabelCajaAutomatica = new javax.swing.JLabel();
        jCheckBoxCajaAutomatica = new javax.swing.JCheckBox();
        jRadioButtonAuto = new javax.swing.JRadioButton();
        jRadioButtonMoto = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        jLabelCantidadPuertas = new javax.swing.JLabel();
        jTextFieldCantidadPuertas = new javax.swing.JTextField();
        jLabelCantidadAirbags = new javax.swing.JLabel();
        jTextFieldCantidadAirbags = new javax.swing.JTextField();
        jLabelIncluyeCasco = new javax.swing.JLabel();
        jCheckBoxIncluyeCasco = new javax.swing.JCheckBox();
        jLabelCantidadTiempos = new javax.swing.JLabel();
        jTextFieldCantidadTiempos = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextFieldComisionVendedor = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTextFieldImpuestos = new javax.swing.JTextField();
        jLabelCantidadRuedas = new javax.swing.JLabel();
        jTextFieldCantidadRuedas = new javax.swing.JTextField();
        jLabelLitrosBaul = new javax.swing.JLabel();
        jTextFieldLitrosBaul = new javax.swing.JTextField();

        jTextFieldColor.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jTextFieldColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldColorActionPerformed(evt);
            }
        });

        panel1.setBackground(new java.awt.Color(21, 77, 161));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Concesionaria Buenos Aires");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
        );

        button2.setBackground(new java.awt.Color(217, 52, 52));
        button2.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setLabel("ELIMINAR");
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        button3.setBackground(new java.awt.Color(52, 152, 219));
        button3.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        button3.setForeground(new java.awt.Color(255, 255, 255));
        button3.setLabel("EDITAR");
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel3.setText("MARCA");

        jTextFieldMarca.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jTextFieldMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldMarcaActionPerformed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel4.setText("PATENTE");

        jTextFieldPatente.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jTextFieldPatente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPatenteActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel5.setText("COLOR");

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel6.setText("AÑO");

        jTextFieldAnio.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jTextFieldAnio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldAnioActionPerformed(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel7.setText("MODELO");

        jTextFieldModelo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jTextFieldModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldModeloActionPerformed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel8.setText("TIPO COMBUSTIBLE");

        jTextFieldTipoCombustible.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jTextFieldTipoCombustible.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTipoCombustibleActionPerformed(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel9.setText("CANTIDAD KILOMETROS");

        jTextFieldCantidadKilometros.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jTextFieldCantidadKilometros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCantidadKilometrosActionPerformed(evt);
            }
        });

        jTextFieldCilindrada.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jTextFieldCilindrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCilindradaActionPerformed(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel10.setText("CILINDRADA");

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel11.setText("PRECIO");

        jTextFieldPrecio.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jTextFieldPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPrecioActionPerformed(evt);
            }
        });

        jLabelCajaAutomatica.setBackground(new java.awt.Color(255, 255, 255));
        jLabelCajaAutomatica.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabelCajaAutomatica.setText("CAJA AUTOMATICA");

        jRadioButtonAuto.setText("Auto");
        jRadioButtonAuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonAutoActionPerformed(evt);
            }
        });

        jRadioButtonMoto.setText("Moto");
        jRadioButtonMoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMotoActionPerformed(evt);
            }
        });

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel13.setText("TIPO DE VEHICULO");

        jLabelCantidadPuertas.setBackground(new java.awt.Color(255, 255, 255));
        jLabelCantidadPuertas.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabelCantidadPuertas.setText("CANTIDAD PUERTAS");

        jTextFieldCantidadPuertas.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jTextFieldCantidadPuertas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCantidadPuertasActionPerformed(evt);
            }
        });

        jLabelCantidadAirbags.setBackground(new java.awt.Color(255, 255, 255));
        jLabelCantidadAirbags.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabelCantidadAirbags.setText("CANTIDAD AIRBAGS");

        jTextFieldCantidadAirbags.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jTextFieldCantidadAirbags.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCantidadAirbagsActionPerformed(evt);
            }
        });

        jLabelIncluyeCasco.setBackground(new java.awt.Color(255, 255, 255));
        jLabelIncluyeCasco.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabelIncluyeCasco.setText("INCLUYE CASCO");

        jLabelCantidadTiempos.setBackground(new java.awt.Color(255, 255, 255));
        jLabelCantidadTiempos.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabelCantidadTiempos.setText("CANTIDAD TIEMPOS");

        jTextFieldCantidadTiempos.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jTextFieldCantidadTiempos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCantidadTiemposActionPerformed(evt);
            }
        });

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel19.setText("COMISION VENDEDOR");

        jTextFieldComisionVendedor.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jTextFieldComisionVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldComisionVendedorActionPerformed(evt);
            }
        });

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel20.setText("IMPUESTOS");

        jTextFieldImpuestos.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jTextFieldImpuestos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldImpuestosActionPerformed(evt);
            }
        });

        jLabelCantidadRuedas.setBackground(new java.awt.Color(255, 255, 255));
        jLabelCantidadRuedas.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabelCantidadRuedas.setText("CANTIDAD RUEDAS");

        jTextFieldCantidadRuedas.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jTextFieldCantidadRuedas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCantidadRuedasActionPerformed(evt);
            }
        });

        jLabelLitrosBaul.setBackground(new java.awt.Color(255, 255, 255));
        jLabelLitrosBaul.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabelLitrosBaul.setText("LITROS BAUL");

        jTextFieldLitrosBaul.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jTextFieldLitrosBaul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldLitrosBaulActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jTextFieldMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(102, 102, 102))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jTextFieldPatente)
                                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
                                            .addGap(129, 129, 129)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                                            .addComponent(jTextFieldColor))
                                        .addGap(105, 105, 105)))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                                        .addComponent(jTextFieldAnio, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGap(110, 110, 110)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jTextFieldTipoCombustible, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                                        .addComponent(jTextFieldModelo, javax.swing.GroupLayout.Alignment.LEADING)))
                                .addGap(112, 112, 112)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 24, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel10)
                                    .addComponent(jTextFieldCantidadKilometros)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextFieldCilindrada)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextFieldPrecio))
                                .addGap(81, 81, 81)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jTextFieldCantidadPuertas)
                                                .addComponent(jLabelCantidadPuertas))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jTextFieldCantidadAirbags)
                                                .addComponent(jLabelCantidadAirbags)))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextFieldComisionVendedor)
                                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jTextFieldImpuestos)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jTextFieldLitrosBaul, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelLitrosBaul, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)))
                                .addContainerGap(2063, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButtonAuto)
                                    .addComponent(jRadioButtonMoto)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCheckBoxCajaAutomatica)
                                    .addComponent(jLabelCantidadRuedas)
                                    .addComponent(jTextFieldCantidadRuedas, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(87, 87, 87)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBoxIncluyeCasco)
                                    .addComponent(jLabelIncluyeCasco, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelCantidadTiempos)
                                    .addComponent(jTextFieldCantidadTiempos, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelCajaAutomatica, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(21, 21, 21))))
            .addGroup(layout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93)
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldCantidadKilometros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldComisionVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(jLabelCantidadPuertas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCantidadPuertas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jTextFieldPatente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextFieldCilindrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel20)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextFieldImpuestos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabelCantidadAirbags)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextFieldCantidadAirbags, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextFieldColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelLitrosBaul)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldLitrosBaul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabelCajaAutomatica))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCheckBoxCajaAutomatica))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(40, 40, 40)
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldTipoCombustible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabelCantidadRuedas)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldCantidadRuedas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jRadioButtonAuto)
                                        .addGap(2, 2, 2)
                                        .addComponent(jRadioButtonMoto)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jLabelIncluyeCasco)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBoxIncluyeCasco)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelCantidadTiempos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldCantidadTiempos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldColorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldColorActionPerformed

    private void jTextFieldMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldMarcaActionPerformed

    private void jTextFieldPatenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPatenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPatenteActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        // TODO add your handling code here:
       if (this.validaciones()){
            
       VehiculosService vehiculosService = new VehiculosService();
        
        if (jRadioButtonAuto.isSelected()) {
            Auto auto = new Auto();
            this.setCommonValues(auto);
            auto.setCantidadAirbags(Integer.parseInt(jTextFieldCantidadAirbags.getText()));
            auto.setPuertas(Integer.parseInt(jTextFieldCantidadPuertas.getText()));
            auto.setLitrosBaul(Integer.parseInt(jTextFieldLitrosBaul.getText()));
            
            vehiculosService.crearOActualizar(auto);
        }
        else if(jRadioButtonMoto.isSelected()){
            Moto moto = new Moto();
            this.setCommonValues(moto);
            moto.setCascoIncluido(jCheckBoxIncluyeCasco.isSelected());
            moto.setCantidadTiempoMotor(Integer.parseInt(jTextFieldCantidadTiempos.getText()));
            
            vehiculosService.crearOActualizar(moto);
        }
           
        this.concesionariaFrame.loadTable();
        this.container.dispose();
        return;
       }
    }//GEN-LAST:event_button3ActionPerformed

    private void setCommonValues(Vehiculo vehiculo){
        
        vehiculo.setId(this.idVehiculo);
        vehiculo.setRuedas(Integer.parseInt(jTextFieldCantidadRuedas.getText()));
        vehiculo.setMarca(jTextFieldMarca.getText());
        vehiculo.setModelo(jTextFieldModelo.getText());
        vehiculo.setPatente(jTextFieldPatente.getText());
        vehiculo.setCajaAutomatica(jCheckBoxCajaAutomatica.isSelected());
        vehiculo.setCantidadKilometros(Integer.parseInt(jTextFieldCantidadKilometros.getText()));
        vehiculo.setCilindrada(Integer.parseInt(jTextFieldCilindrada.getText()));
        vehiculo.setColor(jTextFieldColor.getText());
        vehiculo.setAnio(Integer.parseInt(jTextFieldAnio.getText()));
        vehiculo.setPrecio(Double.parseDouble(jTextFieldPrecio.getText()));
        vehiculo.setTipoCombustible(jTextFieldTipoCombustible.getText());
    }
    
    
    private void jTextFieldAnioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldAnioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldAnioActionPerformed

    private void jTextFieldModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldModeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldModeloActionPerformed

    private void jTextFieldTipoCombustibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTipoCombustibleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTipoCombustibleActionPerformed

    private void jTextFieldCantidadKilometrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCantidadKilometrosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCantidadKilometrosActionPerformed

    private void jTextFieldCilindradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCilindradaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCilindradaActionPerformed

    private void jTextFieldPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPrecioActionPerformed

    private void jRadioButtonMotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMotoActionPerformed
        
        jRadioButtonAuto.setSelected(false);
        this.setAutoVisibilityFields(false);
        this.setMotoVisibilityFields(true);
    }//GEN-LAST:event_jRadioButtonMotoActionPerformed

    private void jTextFieldCantidadPuertasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCantidadPuertasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCantidadPuertasActionPerformed

    private void jTextFieldCantidadAirbagsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCantidadAirbagsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCantidadAirbagsActionPerformed

    private void jTextFieldCantidadTiemposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCantidadTiemposActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCantidadTiemposActionPerformed

    private void jTextFieldComisionVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldComisionVendedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldComisionVendedorActionPerformed

    private void jTextFieldImpuestosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldImpuestosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldImpuestosActionPerformed

    private void jTextFieldCantidadRuedasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCantidadRuedasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCantidadRuedasActionPerformed

    private void jTextFieldLitrosBaulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldLitrosBaulActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldLitrosBaulActionPerformed

    private void jRadioButtonAutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonAutoActionPerformed
        // TODO add your handling code here:
        jRadioButtonMoto.setSelected(false);
        this.setAutoVisibilityFields(true);
        this.setMotoVisibilityFields(false);
        
    }//GEN-LAST:event_jRadioButtonAutoActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        VehiculosService vehiculosService = new VehiculosService();
        int tipo;
        if(jRadioButtonAuto.isSelected())
            tipo = TipoVehiculo.AUTO.ordinal();
        else
            tipo = TipoVehiculo.MOTO.ordinal();
            
        vehiculosService.eliminar(this.idVehiculo, tipo);
        
         this.concesionariaFrame.loadTable();
        this.container.dispose();
    }//GEN-LAST:event_button2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button button2;
    private java.awt.Button button3;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JCheckBox jCheckBoxCajaAutomatica;
    private javax.swing.JCheckBox jCheckBoxIncluyeCasco;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelCajaAutomatica;
    private javax.swing.JLabel jLabelCantidadAirbags;
    private javax.swing.JLabel jLabelCantidadPuertas;
    private javax.swing.JLabel jLabelCantidadRuedas;
    private javax.swing.JLabel jLabelCantidadTiempos;
    private javax.swing.JLabel jLabelIncluyeCasco;
    private javax.swing.JLabel jLabelLitrosBaul;
    private javax.swing.JRadioButton jRadioButtonAuto;
    private javax.swing.JRadioButton jRadioButtonMoto;
    private javax.swing.JTextField jTextFieldAnio;
    private javax.swing.JTextField jTextFieldCantidadAirbags;
    private javax.swing.JTextField jTextFieldCantidadKilometros;
    private javax.swing.JTextField jTextFieldCantidadPuertas;
    private javax.swing.JTextField jTextFieldCantidadRuedas;
    private javax.swing.JTextField jTextFieldCantidadTiempos;
    private javax.swing.JTextField jTextFieldCilindrada;
    private javax.swing.JTextField jTextFieldColor;
    private javax.swing.JTextField jTextFieldComisionVendedor;
    private javax.swing.JTextField jTextFieldImpuestos;
    private javax.swing.JTextField jTextFieldLitrosBaul;
    private javax.swing.JTextField jTextFieldMarca;
    private javax.swing.JTextField jTextFieldModelo;
    private javax.swing.JTextField jTextFieldPatente;
    private javax.swing.JTextField jTextFieldPrecio;
    private javax.swing.JTextField jTextFieldTipoCombustible;
    private java.awt.Panel panel1;
    // End of variables declaration//GEN-END:variables
}
