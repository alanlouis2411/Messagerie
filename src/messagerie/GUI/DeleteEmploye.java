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
import messagerie.DAO.EmployeDAO;
import messagerie.Employe;
import myconnections.DBConnection;

/**
 *
 * @author alanl
 */
public class DeleteEmploye extends javax.swing.JPanel {

    /**
     * Creates new form DeleteEmploye
     */
    public DeleteEmploye() {
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
        tfId = new javax.swing.JTextField();
        btId = new javax.swing.JButton();
        btConf = new javax.swing.JButton();
        btRetour = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 153, 153));
        setLayout(new java.awt.GridLayout(5, 1, 20, 20));

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Entrez l'id de l'employé à virer:");
        add(jLabel1);

        tfId.setBackground(new java.awt.Color(255, 255, 255));
        tfId.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        tfId.setForeground(new java.awt.Color(0, 0, 0));
        tfId.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfIdKeyTyped(evt);
            }
        });
        add(tfId);

        btId.setBackground(new java.awt.Color(51, 51, 51));
        btId.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btId.setForeground(new java.awt.Color(255, 255, 255));
        btId.setText("Voir les id");
        btId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIdActionPerformed(evt);
            }
        });
        add(btId);

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
    }// </editor-fold>//GEN-END:initComponents

    private void btRetourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRetourActionPerformed
        Fenetre.f.setContentPane(new MenuGestEmp());
        Fenetre.f.repaint();
        Fenetre.f.revalidate();
    }//GEN-LAST:event_btRetourActionPerformed

    private void tfIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfIdKeyTyped
        int l = evt.getKeyChar();
        if(!Character.isDigit(l)){
            evt.consume();
        }
    }//GEN-LAST:event_tfIdKeyTyped

    private void btIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIdActionPerformed
        try {
            String liste = "";
            Connection dbConnect = DBConnection.getConnection();
            if (dbConnect == null) {
                System.exit(1);
            }
            Statement stmt = dbConnect.createStatement();
            ResultSet rs = stmt.executeQuery("select * from employe order by idemp");
            while(rs.next()){
                liste = liste + "\n" + rs.getInt("IDEMP") + " " + rs.getString("NOM");
            }
            JOptionPane.showMessageDialog(this,"Liste des employés :" + liste,"Information",JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(ReadEmploye.class.getName()).log(Level.SEVERE, null, ex);
        }
        DBConnection.closeConnection();
    }//GEN-LAST:event_btIdActionPerformed

    private void btConfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConfActionPerformed
        String id = tfId.getText();
        String mat = "", nom = "", prenom = "";
        int idbur = 0;
        if(id.equals("")){
            JOptionPane.showMessageDialog(this,"Vous n'avez entré aucun id.","Erreur !",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            try {
                Connection dbConnect = DBConnection.getConnection();
                if (dbConnect == null) {
                    System.exit(1);
                }
                int id_virer = Integer.parseInt(id);
                int flag = 0;
                Statement stmt = dbConnect.createStatement();
                ResultSet rs = stmt.executeQuery("select * from employe");
                while(rs.next()){
                    if(id_virer == rs.getInt("IDEMP")){
                        flag = 1;
                        mat = rs.getString("MATRICULE");
                        nom = rs.getString("NOM");
                        prenom = rs.getString("PRENOM");
                        idbur = rs.getInt("IDBUR");
                        break;
                    }
                }
                if(flag == 0){
                    JOptionPane.showMessageDialog(this,"L'id entré ne correspond à aucun employé.","Erreur !",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    Employe emp = new Employe(id_virer, mat, nom, prenom, idbur);
                    EmployeDAO empdao = new EmployeDAO();
                    empdao.delete(emp);
                    JOptionPane.showMessageDialog(this,"L'employé à bien été viré.","Succès",JOptionPane.INFORMATION_MESSAGE);
                    Fenetre.f.setContentPane(new MenuGestEmp());
                    Fenetre.f.repaint();
                    Fenetre.f.revalidate();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DeleteEmploye.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btConfActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btConf;
    private javax.swing.JButton btId;
    private javax.swing.JButton btRetour;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField tfId;
    // End of variables declaration//GEN-END:variables
}
