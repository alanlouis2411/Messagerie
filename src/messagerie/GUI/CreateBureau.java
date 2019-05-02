/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messagerie.GUI;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import messagerie.Bureau;
import messagerie.DAO.BureauDAO;
import myconnections.DBConnection;

/**
 *
 * @author alanl
 */
public class CreateBureau extends javax.swing.JPanel {

    /**
     * Creates new form CreateBureau
     */
    public CreateBureau() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labSigle = new javax.swing.JLabel();
        tfSigle = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfTel = new javax.swing.JTextField();
        btRetour = new javax.swing.JButton();
        btConf = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 0, 204));
        setLayout(new java.awt.GridLayout(3, 2, 20, 20));

        labSigle.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        labSigle.setForeground(new java.awt.Color(255, 255, 255));
        labSigle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labSigle.setText("SIGLE : ");
        add(labSigle);

        tfSigle.setBackground(new java.awt.Color(255, 255, 255));
        tfSigle.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        tfSigle.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfSigle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfSigleKeyTyped(evt);
            }
        });
        add(tfSigle);

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("N° TELEPHONE :");
        add(jLabel2);

        tfTel.setBackground(new java.awt.Color(255, 255, 255));
        tfTel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        tfTel.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfTel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTelActionPerformed(evt);
            }
        });
        tfTel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfTelKeyTyped(evt);
            }
        });
        add(tfTel);

        btRetour.setBackground(new java.awt.Color(51, 51, 51));
        btRetour.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btRetour.setForeground(new java.awt.Color(255, 255, 255));
        btRetour.setText("Retour");
        btRetour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRetourActionPerformed(evt);
            }
        });
        add(btRetour);

        btConf.setBackground(new java.awt.Color(51, 51, 51));
        btConf.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btConf.setForeground(new java.awt.Color(255, 255, 255));
        btConf.setText("Confirmer");
        btConf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConfActionPerformed(evt);
            }
        });
        add(btConf);
    }// </editor-fold>//GEN-END:initComponents

    private void btRetourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRetourActionPerformed
        Fenetre.f.setContentPane(new MenuGestBur());
        Fenetre.f.repaint();
        Fenetre.f.revalidate();
    }//GEN-LAST:event_btRetourActionPerformed

    private void btConfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConfActionPerformed
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        BureauDAO bdao = new BureauDAO();
        String sigle = tfSigle.getText();
        String tel = tfTel.getText();
        int nb_lig = 0;
        int flag = 1;
        Statement stmt = null;
        ResultSet rs = null;
        if(sigle.equals("") || tel.equals("")){
            JOptionPane.showMessageDialog(this,"Un ou plusieurs champs sont vides !","Erreur",JOptionPane.INFORMATION_MESSAGE);
            Fenetre.f.setContentPane(new CreateBureau());
            Fenetre.f.repaint();
            Fenetre.f.revalidate();
        }
        else{
            try {
                stmt = dbConnect.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(CreateBureau.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                rs = stmt.executeQuery("select * from bureau");
            } catch (SQLException ex) {
                Logger.getLogger(CreateBureau.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                while(rs.next()){
                    nb_lig++;
                    String sig = rs.getString("SIGLE");
                    String t = rs.getString("TEL");
                    if((sig.equals(sigle))||(t.equals(tel))){
                        flag = 0;
                        break;
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(CreateBureau.class.getName()).log(Level.SEVERE, null, ex);
            }
            nb_lig++;
            if(flag == 0){
                JOptionPane.showMessageDialog(this,"Ce sigle/téléphone existe déja !","Erreur",JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                Bureau b = new Bureau(nb_lig, sigle, tel);
                try {
                    bdao.create(b);
                    JOptionPane.showMessageDialog(this,"Bureau créé avec succès.","Succès",JOptionPane.INFORMATION_MESSAGE);
                    Fenetre.f.setContentPane(new MenuGestBur());
                    Fenetre.f.repaint();
                    Fenetre.f.revalidate();
                } catch (SQLException ex) {
                    Logger.getLogger(CreateBureau.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        DBConnection.closeConnection();
    }//GEN-LAST:event_btConfActionPerformed

    private void tfSigleKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSigleKeyTyped
        char l = evt.getKeyChar();
        if(!Character.isLetter(l)){
            evt.consume();
        }
    }//GEN-LAST:event_tfSigleKeyTyped

    private void tfTelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfTelKeyTyped
        int l = evt.getKeyChar();
        if(!Character.isDigit(l)){
            evt.consume();
        }
    }//GEN-LAST:event_tfTelKeyTyped

    private void tfTelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btConf;
    private javax.swing.JButton btRetour;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel labSigle;
    private javax.swing.JTextField tfSigle;
    private javax.swing.JTextField tfTel;
    // End of variables declaration//GEN-END:variables
}
