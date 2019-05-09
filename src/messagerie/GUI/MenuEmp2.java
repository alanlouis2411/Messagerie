/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messagerie.GUI;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alanl
 */
public class MenuEmp2 extends javax.swing.JPanel {

    /**
     * Creates new form MenuEmp2
     */
    int id = MenuEmp.idemp;
    public MenuEmp2() {
        initComponents();
        labEmp.setText("Bonjour "+MenuEmp.prenom);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labEmp = new javax.swing.JLabel();
        btMail = new javax.swing.JButton();
        btRecep = new javax.swing.JButton();
        btRetour = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 153, 153));
        setForeground(new java.awt.Color(204, 204, 0));
        setLayout(new java.awt.GridLayout(4, 1, 20, 20));

        labEmp.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        labEmp.setForeground(new java.awt.Color(255, 255, 255));
        labEmp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labEmp.setText("Bonjour");
        add(labEmp);

        btMail.setBackground(new java.awt.Color(51, 51, 51));
        btMail.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btMail.setForeground(new java.awt.Color(255, 255, 255));
        btMail.setText("Envoyer mail");
        btMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMailActionPerformed(evt);
            }
        });
        add(btMail);

        btRecep.setBackground(new java.awt.Color(51, 51, 51));
        btRecep.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btRecep.setForeground(new java.awt.Color(255, 255, 255));
        btRecep.setText("Boite de réception");
        btRecep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRecepActionPerformed(evt);
            }
        });
        add(btRecep);

        btRetour.setBackground(new java.awt.Color(51, 51, 51));
        btRetour.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btRetour.setForeground(new java.awt.Color(255, 255, 255));
        btRetour.setText("Déconnexion");
        btRetour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRetourActionPerformed(evt);
            }
        });
        add(btRetour);
    }// </editor-fold>//GEN-END:initComponents

    private void btRetourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRetourActionPerformed
        Fenetre.f.setContentPane(new MenuEmp());
        Fenetre.f.repaint();
        Fenetre.f.revalidate();
    }//GEN-LAST:event_btRetourActionPerformed

    private void btMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMailActionPerformed
        Fenetre.f.setContentPane(new Mail());
        Fenetre.f.repaint();
        Fenetre.f.revalidate();
    }//GEN-LAST:event_btMailActionPerformed

    private void btRecepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRecepActionPerformed
        try {
            Fenetre.f.setContentPane(new BoiteRecep());
        } catch (SQLException ex) {
            Logger.getLogger(MenuEmp2.class.getName()).log(Level.SEVERE, null, ex);
        }
        Fenetre.f.repaint();
        Fenetre.f.revalidate();
    }//GEN-LAST:event_btRecepActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btMail;
    private javax.swing.JButton btRecep;
    private javax.swing.JButton btRetour;
    private javax.swing.JLabel labEmp;
    // End of variables declaration//GEN-END:variables

}
