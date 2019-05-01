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
public class ModifBureau2 extends javax.swing.JPanel {

    /**
     * Creates new form ModifBureau2
     */
    public static String s, t;
    public static int id;
    
    public ModifBureau2() {
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

        jLabel1 = new javax.swing.JLabel();
        tfSigle = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfTel = new javax.swing.JTextField();
        btRetour = new javax.swing.JButton();
        btConf = new javax.swing.JButton();

        setBackground(new java.awt.Color(102, 0, 102));
        setLayout(new java.awt.GridLayout(3, 2, 20, 20));

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nouveau sigle :");
        add(jLabel1);

        tfSigle.setBackground(new java.awt.Color(255, 255, 255));
        tfSigle.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        tfSigle.setForeground(new java.awt.Color(0, 0, 0));
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
        jLabel2.setText("Nouveau n° de téléphone :");
        add(jLabel2);

        tfTel.setBackground(new java.awt.Color(255, 255, 255));
        tfTel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        tfTel.setForeground(new java.awt.Color(0, 0, 0));
        tfTel.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfTel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfTelKeyTyped(evt);
            }
        });
        add(tfTel);

        btRetour.setBackground(new java.awt.Color(51, 51, 51));
        btRetour.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btRetour.setForeground(new java.awt.Color(204, 204, 0));
        btRetour.setText("Retour");
        btRetour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRetourActionPerformed(evt);
            }
        });
        add(btRetour);

        btConf.setBackground(new java.awt.Color(51, 51, 51));
        btConf.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btConf.setForeground(new java.awt.Color(204, 204, 0));
        btConf.setText("Confirmer");
        btConf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConfActionPerformed(evt);
            }
        });
        add(btConf);
    }// </editor-fold>//GEN-END:initComponents

    private void btRetourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRetourActionPerformed
        Fenetre.f.setContentPane(new ModifBureau());
        Fenetre.f.repaint();
        Fenetre.f.revalidate();
    }//GEN-LAST:event_btRetourActionPerformed

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

    private void btConfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConfActionPerformed
        try {
            BureauDAO.new_sig = tfSigle.getText();
            BureauDAO.new_tel = tfTel.getText();
            ResultSet rs;
            int flag = 0;
            Statement stmt;
            Connection dbConnect = DBConnection.getConnection();
            if (dbConnect == null) {
                System.exit(1);
            }
            stmt = dbConnect.createStatement();
            rs = stmt.executeQuery("select * from bureau");
            while(rs.next()){
                if(id != rs.getInt("IDBUR") && (BureauDAO.new_sig.equals(rs.getString("SIGLE")) || BureauDAO.new_tel.equals("TEL"))){
                    flag = 1;
                    break;
                }
            }
            if(flag == 1){
                JOptionPane.showMessageDialog(this,"Sigle/N° de téléphone déjà existant !","Erreur",JOptionPane.INFORMATION_MESSAGE);
                Fenetre.f.setContentPane(new ModifBureau2());
                Fenetre.f.repaint();
                Fenetre.f.revalidate();
            }
            else{
                Bureau b = new Bureau(id, s, t);
                BureauDAO bdao = new BureauDAO();
                bdao.update(b);
                Fenetre.f.setContentPane(new MenuGestBur());
                Fenetre.f.repaint();
                Fenetre.f.revalidate();
                JOptionPane.showMessageDialog(this,"Modification effectuée.","Succès",JOptionPane.INFORMATION_MESSAGE);             
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModifBureau2.class.getName()).log(Level.SEVERE, null, ex);
        }
        DBConnection.closeConnection();
    }//GEN-LAST:event_btConfActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btConf;
    private javax.swing.JButton btRetour;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField tfSigle;
    private javax.swing.JTextField tfTel;
    // End of variables declaration//GEN-END:variables
}