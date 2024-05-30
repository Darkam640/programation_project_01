package view;

import controller.ReservationDetailsDTO;
import controller.ReservationManager;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class FRM_Client extends javax.swing.JFrame {

    private ReservationManager reservationManager;
    

    public FRM_Client() {
        this.reservationManager = reservationManager;
        initComponents();
        cargarComboEstacionamiento();
        cargarComboHorario();
        cargarComboVehiculo();
        this.jTextField1.setEditable(false);
    }

    public ButtonsPanel getPanelBotones() {
        return this.buttonsPanel1;
    }

    public void setEscuchadoresBotones(ActionListener manejador) {
        this.buttonsPanel1.escucharBotones(manejador);
    }

    public void setEscuchadoresCombo(ActionListener manejador) {
        this.cbEstacionamiento.addActionListener(manejador);
        this.cbTipoVehiculo.addActionListener(manejador);
        this.cbHorario.addActionListener(manejador);
    }

    public void setEscuchadoresItem(ItemListener manejador) {
        this.cbEstacionamiento.addItemListener(manejador);
        this.cbTipoVehiculo.addItemListener(manejador);
        this.cbHorario.addItemListener(manejador);
    }

    public void mensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public void limpiar() {
        this.jtNombre.setText("");
        this.jtContacto.setText("");
        this.cbEstacionamiento.setSelectedItem(null);
        this.cbTipoVehiculo.setSelectedItem(null);
        this.cbHorario.setSelectedItem(null);
        this.jTextField1.setText("");
    }
    
    public void cleanAdd() {
        this.jtNombre.setText("");
        this.cbEstacionamiento.setSelectedItem(null);
        this.cbTipoVehiculo.setSelectedItem(null);
        this.cbHorario.setSelectedItem(null);
    }


    public void cargarComboEstacionamiento() {
        this.cbEstacionamiento.addItem("Norte");
        this.cbEstacionamiento.addItem("Sur");
        this.cbEstacionamiento.addItem("Este");
        this.cbEstacionamiento.addItem("Oeste");
    }

    public void cargarComboHorario() {
        this.cbHorario.addItem("Medio dia");
        this.cbHorario.addItem("Dia entero");
        this.cbHorario.addItem("Noche");
    }

    public void cargarComboVehiculo() {
        this.cbTipoVehiculo.addItem("Vehiculo");
        this.cbTipoVehiculo.addItem("Motocicleta");
    }

    public void setTotalPrice(double price) {
        jTextField1.setText(String.valueOf(price));
    }

    public String getSelectedItemEstacionamiento() {
        return (String) this.cbEstacionamiento.getSelectedItem();
    }

    public String getSelectedItemHorario() {
        return (String) this.cbHorario.getSelectedItem();
    }

    public String getSelectedItemVehiculo() {
        return (String) this.cbTipoVehiculo.getSelectedItem();
    }

    public void addAddReservationListener(ActionListener addReservationListener) {
        this.buttonsPanel1.getAddButton().addActionListener(addReservationListener);
    }

    public void addCancelReservationListener(ActionListener cancelReservationListener) {
        this.buttonsPanel1.getRemoveButton().addActionListener(cancelReservationListener);
    }

    public String getClientName() {
        return this.jtNombre.getText();
    }

    public String getClientContact() {
        return this.jtContacto.getText();
    }

    public String getSelectedSide() {
        return getSelectedItemEstacionamiento();
    }

    public void showReservationNotFoundMessage() {
        JOptionPane.showMessageDialog(this, "Reserva no encontrada.");
    }

    public void updateView() {
        // Implementación de actualización de la vista
    }

    public void showNoAvailableSpaceMessage() {
        JOptionPane.showMessageDialog(this, "No hay espacios disponibles.");
    }

    public void showReservationExistsMessage() {
        JOptionPane.showMessageDialog(this, "Ya existe una reserva activa con este contacto.");
    }

    public void showInvalidSideMessage() {
        JOptionPane.showMessageDialog(this, "El lado seleccionado es invalido.");
    }

    public void showReservationDetails(ReservationDetailsDTO reservation) {
        jtNombre.setText(reservation.getClientName());
        jtContacto.setText(reservation.getClientContact());

        cbEstacionamiento.setSelectedItem(reservation.getParkingSpaceSide());
        cbTipoVehiculo.setSelectedItem(reservation.getVehicleType());

        String durationHours = reservation.getDurationHours();
        switch (durationHours) {
            case "6":
                cbHorario.setSelectedItem("Medio dia");
                break;
            case "24":
                cbHorario.setSelectedItem("Dia entero");
                break;
            case "8":
                cbHorario.setSelectedItem("Noche");
                break;
            default:
                cbHorario.setSelectedItem(null);
                break;
        }

        jTextField1.setText(String.valueOf(reservation.getTotalPrice()));
    }

    public void addSearchReservationListener(ActionListener searchReservationListener) {
        this.buttonsPanel1.getFindButton().addActionListener(searchReservationListener);
    }

    public void addBackButtonListener(ActionListener backButtonListener) {
        this.buttonsPanel1.getExitButton().addActionListener(backButtonListener);
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtNombre = new javax.swing.JTextField();
        jtContacto = new javax.swing.JTextField();
        cbEstacionamiento = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbTipoVehiculo = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbHorario = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        buttonsPanel1 = new view.ButtonsPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));
        jPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                jPanel1ComponentHidden(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Registro de reservaciones");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 410, 80));

        jLabel2.setFont(new java.awt.Font("Gill Sans MT", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Nombre");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 100, 50));

        jLabel3.setFont(new java.awt.Font("Gill Sans MT", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Contacto");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, -1, -1));

        jtNombre.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jPanel1.add(jtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 190, -1));

        jtContacto.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jPanel1.add(jtContacto, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 190, -1));

        cbEstacionamiento.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jPanel1.add(cbEstacionamiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 90, 100, -1));

        jLabel4.setFont(new java.awt.Font("Gill Sans MT", 1, 21)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Seleccione el lugar ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, -1, -1));

        jLabel5.setFont(new java.awt.Font("Gill Sans MT", 1, 21)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Tipo de vehiculo");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 150, -1, -1));

        cbTipoVehiculo.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jPanel1.add(cbTipoVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 190, 100, -1));

        jLabel6.setFont(new java.awt.Font("Gill Sans MT", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Horario de estacionamiento");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(431, 247, -1, -1));

        cbHorario.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        jPanel1.add(cbHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 300, 100, -1));

        jTextField1.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(0, 0, 0));
        jTextField1.setText(" ");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 330, 96, 40));

        jLabel8.setFont(new java.awt.Font("Gill Sans MT", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Total a pagar:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 330, -1, -1));
        jPanel1.add(buttonsPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, 560, 130));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1ComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel1ComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1ComponentHidden

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.ButtonsPanel buttonsPanel1;
    private javax.swing.JComboBox<String> cbEstacionamiento;
    private javax.swing.JComboBox<String> cbHorario;
    private javax.swing.JComboBox<String> cbTipoVehiculo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jtContacto;
    private javax.swing.JTextField jtNombre;
    // End of variables declaration//GEN-END:variables

}
