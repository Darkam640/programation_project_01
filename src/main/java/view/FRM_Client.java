package view;

import controller.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class FRM_Client extends javax.swing.JFrame {

    /**
     * Creates new form FRM_Client
     */
    public FRM_Client() {
        initComponents();
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

    public void setClientContact(String contact) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

    public void showReservationDetails(String clientName, String clientContact, String selectedSide, String selectedItemVehiculo, String selectedItemHorario) {
        jtNombre.setText(clientName);
        jtContacto.setText(clientContact);
        cbEstacionamiento.setSelectedItem(selectedSide);
        cbTipoVehiculo.setSelectedItem(selectedItemVehiculo);
        cbHorario.setSelectedItem(selectedItemHorario);
    }
    
    public void addSearchReservationListener(ReservationManager.SearchReservationListener searchReservationListener) {
        this.buttonsPanel1.getFindButton().addActionListener(searchReservationListener);
    }
    
    public void addBackButtonListener(ReservationManager.BackMenuListener backMenulistener) {
        this.buttonsPanel1.getExitButton().addActionListener(backMenulistener);
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtNombre = new javax.swing.JTextField();
        jtContacto = new javax.swing.JTextField();
        buttonsPanel1 = new view.ButtonsPanel();
        cbEstacionamiento = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbTipoVehiculo = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbHorario = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        jLabel1.setFont(new java.awt.Font("Bodoni MT", 0, 24)); // NOI18N
        jLabel1.setText("Registro Clientes");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Nombre");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Contacto");

        jLabel4.setText("Seleccione el lugar ");

        jLabel5.setText("Tipo de vehiculo");

        jLabel6.setText("Horario de estacionamiento");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(54, 54, 54))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(cbEstacionamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbTipoVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(16, 16, 16)))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtNombre)
                            .addComponent(jtContacto, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(128, 128, 128)
                                .addComponent(jLabel6))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(buttonsPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbEstacionamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbTipoVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jtContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                        .addComponent(buttonsPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jtContacto;
    private javax.swing.JTextField jtNombre;
    // End of variables declaration//GEN-END:variables

    

}
