/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.MenuManager;
import java.awt.event.ActionListener;
import model.User;

/**
 *
 * @author Lenovo
 */
public class FRM_Menu extends javax.swing.JFrame {

    /**
     * Creates new form FRM_Menu
     */
    public FRM_Menu() {
        initComponents();
        esconderMenu();
    }

    public void escucharMenu(ActionListener manejador) {
        this.jmiReporte.addActionListener(manejador);
        this.jmiReservaciones.addActionListener(manejador);
    }

    public void escucharBotonesUser(ActionListener manejador) {
        this.btIniciarSesion.addActionListener(manejador);
        this.btCerrarSesion.addActionListener(manejador);
    }

    public void setUsuario(User user) {
        jtUsuario.setText(user.getUsername());
        jtContraseña.setText(user.getPassword());
    }
    
    public User getUsuario() {
        return new User(jtUsuario.getText(), jtContraseña.getText());
    }

    public void limpiar() {
        jtUsuario.setText("");
        jtContraseña.setText("");
    }

    public void esconderMenu() {
        jmiReporte.setVisible(false);
        jmiReservaciones.setVisible(false);
    }

    public void mostrarMenu() {
        jmiReporte.setVisible(true);
        jmiReservaciones.setVisible(true);
    }

    public void mostrarLogin() {
        jLabel1.setVisible(true);
        jLabel2.setVisible(true);
        jtUsuario.setVisible(true);
        jtContraseña.setVisible(true);
        btIniciarSesion.setVisible(true);
        btCerrarSesion.setVisible(true);
    }

    public void esconderLogin() {
        jLabel1.setVisible(false);
        jLabel2.setVisible(false);
        jtUsuario.setVisible(false);
        jtContraseña.setVisible(false);
        btIniciarSesion.setVisible(false);
        btCerrarSesion.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtContraseña = new javax.swing.JTextField();
        jtUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btIniciarSesion = new javax.swing.JButton();
        btCerrarSesion = new javax.swing.JButton();
        btReportes = new javax.swing.JButton();
        btReservaciones = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jmiReservaciones = new javax.swing.JMenu();
        jmiReporte = new javax.swing.JMenuItem();
        Reservaciones = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));

        jLabel1.setFont(new java.awt.Font("Gill Sans MT", 0, 18)); // NOI18N
        jLabel1.setText("Usuario");

        jLabel2.setFont(new java.awt.Font("Gill Sans MT", 0, 18)); // NOI18N
        jLabel2.setText("Contraseña");

        jLabel3.setFont(new java.awt.Font("Bodoni MT", 0, 24)); // NOI18N
        jLabel3.setText("Inicio de sesion Parqueo Juarez");

        btIniciarSesion.setFont(new java.awt.Font("Gill Sans MT", 0, 18)); // NOI18N
        btIniciarSesion.setText("Iniciar sesion");
        btIniciarSesion.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 204, 255)));

        btCerrarSesion.setText("Cerrar sesion");

        btReportes.setFont(new java.awt.Font("Eras Medium ITC", 0, 18)); // NOI18N
        btReportes.setText("Reportes");
        btReportes.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 51)));
        btReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReportesActionPerformed(evt);
            }
        });

        btReservaciones.setFont(new java.awt.Font("Eras Medium ITC", 0, 18)); // NOI18N
        btReservaciones.setText("Reservaciones");
        btReservaciones.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 51)));
        btReservaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReservacionesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btReservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(423, 423, 423))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(208, 208, 208))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(btCerrarSesion))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(231, 231, 231)
                        .addComponent(btIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btReservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btCerrarSesion)
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(btIniciarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(175, 175, 175))
        );

        jmiReservaciones.setText("File");

        jmiReporte.setText("Reporte");
        jmiReservaciones.add(jmiReporte);

        Reservaciones.setText("Reservaciones");
        jmiReservaciones.add(Reservaciones);

        jMenuBar1.add(jmiReservaciones);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReportesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btReportesActionPerformed

    private void btReservacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReservacionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btReservacionesActionPerformed



    public void addLoginListener(MenuManager.LoginListener loginListener) {
        btIniciarSesion.addActionListener(loginListener);
    }

    public void addLogoutListener(MenuManager.LogoutListener logoutListener) {
        btCerrarSesion.addActionListener(logoutListener);
    }

    public String getUsername() {
        return jtUsuario.getText();
    }

    public String getPassword() {
        return jtContraseña.getText();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Reservaciones;
    private javax.swing.JButton btCerrarSesion;
    private javax.swing.JButton btIniciarSesion;
    private javax.swing.JButton btReportes;
    private javax.swing.JButton btReservaciones;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JMenuItem jmiReporte;
    private javax.swing.JMenu jmiReservaciones;
    private javax.swing.JTextField jtContraseña;
    private javax.swing.JTextField jtUsuario;
    // End of variables declaration//GEN-END:variables
}
