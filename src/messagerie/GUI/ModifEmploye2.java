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
import messagerie.DAO.EmployeDAO;
import messagerie.Employe;
import static messagerie.GUI.ModifBureau2.id;
import static messagerie.GUI.ModifBureau2.s;
import static messagerie.GUI.ModifBureau2.t;
import myconnections.DBConnection;

/**
 *
 * @author alanl
 */
public class ModifEmploye2 extends javax.swing.JPanel {

    /**
     * Creates new form ModifEmploye2
     */
    
    public static String matricule, nom, prenom;
    public static int idEmploye, idBur;
    public ModifEmploye2() {
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
        tfNom = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfPrenom = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfId = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 153, 153));
        setLayout(new java.awt.GridLayout(4, 2, 20, 20));

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nouveau nom :");
        add(jLabel1);

        tfNom.setBackground(new java.awt.Color(255, 255, 255));
        tfNom.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        tfNom.setForeground(new java.awt.Color(0, 0, 0));
        tfNom.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfNom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfNomKeyTyped(evt);
            }
        });
        add(tfNom);

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Nouveau prénom :");
        add(jLabel2);

        tfPrenom.setBackground(new java.awt.Color(255, 255, 255));
        tfPrenom.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        tfPrenom.setForeground(new java.awt.Color(0, 0, 0));
        tfPrenom.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfPrenom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfPrenomKeyTyped(evt);
            }
        });
        add(tfPrenom);

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Nouvel id de bureau :");
        add(jLabel3);

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

        jButton1.setBackground(new java.awt.Color(51, 51, 51));
        jButton1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Retour");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1);

        jButton2.setBackground(new java.awt.Color(51, 51, 51));
        jButton2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Confirmer");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Fenetre.f.setContentPane(new ModifEmploye());
        Fenetre.f.repaint();
        Fenetre.f.revalidate();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        EmployeDAO.new_nom = tfNom.getText();
        EmployeDAO.new_prenom = tfPrenom.getText();
        String id = tfId.getText();
        if(EmployeDAO.new_nom.equals("") || EmployeDAO.new_prenom.equals("") || id.equals("")){
            JOptionPane.showMessageDialog(this,"Les champs sont vides !","Erreur",JOptionPane.INFORMATION_MESSAGE);
            Fenetre.f.repaint();
            Fenetre.f.revalidate();
        }
        else{
            try {
                ResultSet rs;
                int flag = 0;
                Statement stmt;
                EmployeDAO.new_id_bur = Integer.parseInt(id);
                Connection dbConnect = DBConnection.getConnection();
                if (dbConnect == null) {
                    System.exit(1);
                }
                stmt = dbConnect.createStatement();
                rs = stmt.executeQuery("select * from bureau");
                while(rs.next()){
                    if(EmployeDAO.new_id_bur == rs.getInt("IDBUR")){
                        flag = 1;
                        break;
                    }
                }
                if(flag == 1){
                    Employe emp = new Employe(idEmploye, matricule, nom, prenom, idBur);
                    EmployeDAO empdao = new EmployeDAO();
                    empdao.update(emp);
                    JOptionPane.showMessageDialog(this,"La modification à été effectuée.","Succès",JOptionPane.INFORMATION_MESSAGE);
                    Fenetre.f.setContentPane(new ModifEmploye());
                    Fenetre.f.repaint();
                    Fenetre.f.revalidate();
                }
                else{
                    JOptionPane.showMessageDialog(this,"L'id de bureau entré n'existe pas.","Erreur",JOptionPane.INFORMATION_MESSAGE);          
                }
            } catch (SQLException ex) {
                Logger.getLogger(ModifBureau2.class.getName()).log(Level.SEVERE, null, ex);
            }
            DBConnection.closeConnection();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tfIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfIdKeyTyped
        int l = evt.getKeyChar();
        if(!Character.isDigit(l)){
            evt.consume();
        }
    }//GEN-LAST:event_tfIdKeyTyped

    private void tfPrenomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPrenomKeyTyped
        char l = evt.getKeyChar();
        if(!Character.isLetter(l)){
            evt.consume();
        }
    }//GEN-LAST:event_tfPrenomKeyTyped

    private void tfNomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNomKeyTyped
        char l = evt.getKeyChar();
        if(!Character.isLetter(l)){
            evt.consume();
        }
    }//GEN-LAST:event_tfNomKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField tfId;
    private javax.swing.JTextField tfNom;
    private javax.swing.JTextField tfPrenom;
    // End of variables declaration//GEN-END:variables
}
