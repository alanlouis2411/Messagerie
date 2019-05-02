/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messagerie.GUI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import myconnections.DBConnection;
import messagerie.DAO.MessageDAO;

/**
 *
 * @author alanl
 */
public class BoiteRecep extends javax.swing.JPanel {

    /**
     * Creates new form BoiteRecep
     */
    int id = MenuEmp.idemp;
    MessageDAO mdao = new MessageDAO();
    public BoiteRecep() throws SQLException {
        initComponents();
        mdao.read(id);
        msgReçus.setText(MessageDAO.boite);
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
        msgReçus = new javax.swing.JTextArea();
        btRetour = new javax.swing.JButton();

        setBackground(new java.awt.Color(51, 51, 255));
        setLayout(new java.awt.BorderLayout());

        msgReçus.setBackground(new java.awt.Color(255, 255, 255));
        msgReçus.setColumns(20);
        msgReçus.setForeground(new java.awt.Color(0, 0, 0));
        msgReçus.setLineWrap(true);
        msgReçus.setRows(20);
        msgReçus.setTabSize(50);
        jScrollPane1.setViewportView(msgReçus);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        btRetour.setBackground(new java.awt.Color(51, 51, 51));
        btRetour.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btRetour.setForeground(new java.awt.Color(255, 255, 255));
        btRetour.setText("Retour");
        btRetour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRetourActionPerformed(evt);
            }
        });
        add(btRetour, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void btRetourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRetourActionPerformed
        Fenetre.f.setContentPane(new MenuEmp2());
        Fenetre.f.repaint();
        Fenetre.f.revalidate();
    }//GEN-LAST:event_btRetourActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btRetour;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea msgReçus;
    // End of variables declaration//GEN-END:variables

}
