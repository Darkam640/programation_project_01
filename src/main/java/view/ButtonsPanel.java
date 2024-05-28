/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Lenovo
 */
public class ButtonsPanel extends javax.swing.JPanel {

    /**
     * Creates new form ButtonsPanel
     */
    public ButtonsPanel() 
    {
        initComponents();
    }
    
    public void escucharBotones(ActionListener manejador)
    {
        this.btAgregar.addActionListener(manejador);
        this.btBuscar.addActionListener(manejador);
        this.btEliminar.addActionListener(manejador);
        this.btSalir.addActionListener(manejador);
    }
    
    public JButton getAddButton() {
        return btAgregar;
    }

    public JButton getExitButton() {
        return btSalir;
    }
    
    public JButton getRemoveButton() {
        return btEliminar;
    }
    
    public JButton getFindButton() {
        return btBuscar;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btAgregar = new javax.swing.JButton();
        btBuscar = new javax.swing.JButton();
        btEliminar = new javax.swing.JButton();
        btSalir = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 102, 102));

        btAgregar.setFont(new java.awt.Font("Eras Medium ITC", 0, 18)); // NOI18N
        btAgregar.setText("Agregar");

        btBuscar.setFont(new java.awt.Font("Eras Medium ITC", 0, 18)); // NOI18N
        btBuscar.setText("Buscar");

        btEliminar.setFont(new java.awt.Font("Eras Medium ITC", 0, 18)); // NOI18N
        btEliminar.setText("Eliminar");

        btSalir.setFont(new java.awt.Font("Eras Medium ITC", 0, 18)); // NOI18N
        btSalir.setText("Salir");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btAgregar)
                .addGap(26, 26, 26)
                .addComponent(btBuscar)
                .addGap(31, 31, 31)
                .addComponent(btEliminar)
                .addGap(28, 28, 28)
                .addComponent(btSalir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btAgregar)
                    .addComponent(btBuscar)
                    .addComponent(btEliminar)
                    .addComponent(btSalir))
                .addContainerGap(34, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAgregar;
    private javax.swing.JButton btBuscar;
    private javax.swing.JButton btEliminar;
    private javax.swing.JButton btSalir;
    // End of variables declaration//GEN-END:variables
}
