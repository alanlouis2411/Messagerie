/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messagerie.GUI;

import jaco.mp3.player.MP3Player;
import java.io.File;
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
public class CreateEmploye extends javax.swing.JPanel {

    /**
     * Creates new form CreateEmploye
     */
    public CreateEmploye() {
        initComponents();
    }

    public static final String song = "C:\\Users\\alanl\\Desktop\\error.mp3";
    static MP3Player mp3player = new MP3Player(new File(song));
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        tfMat = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfNom = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfPrenom = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfBureau = new javax.swing.JTextField();
        btRetour = new javax.swing.JButton();
        btConf = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 153, 153));
        setLayout(new java.awt.GridLayout(5, 2, 20, 20));

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Matricule :");
        add(jLabel1);

        tfMat.setBackground(new java.awt.Color(255, 255, 255));
        tfMat.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        tfMat.setForeground(new java.awt.Color(0, 0, 0));
        tfMat.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfMat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfMatKeyTyped(evt);
            }
        });
        add(tfMat);

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Nom :");
        add(jLabel2);

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

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Prénom :");
        add(jLabel3);

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

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Id du bureau :");
        add(jLabel4);

        tfBureau.setBackground(new java.awt.Color(255, 255, 255));
        tfBureau.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        tfBureau.setForeground(new java.awt.Color(0, 0, 0));
        tfBureau.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfBureau.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfBureauKeyTyped(evt);
            }
        });
        add(tfBureau);

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
        Fenetre.f.setContentPane(new MenuGestEmp());
        Fenetre.f.repaint();
        Fenetre.f.revalidate();
    }//GEN-LAST:event_btRetourActionPerformed

    private void btConfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConfActionPerformed
        String matricule = tfMat.getText();
        String nom = tfNom.getText();
        String prenom = tfPrenom.getText();
        String idbureau = tfBureau.getText();
        if(matricule.equals("") || nom.equals("") || prenom.equals("") || idbureau.equals("")){
            mp3player.play();
            JOptionPane.showMessageDialog(this,"Un ou plusieurs champs sont vides !","Erreur",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            try {
                int idbur = Integer.parseInt(idbureau);
                Connection dbConnect = DBConnection.getConnection();
                if (dbConnect == null) {
                    System.exit(1);
                }
                Statement stmt = dbConnect.createStatement();
                ResultSet rs = stmt.executeQuery("select * from employe order by idemp desc");
                int flag = 0;
                int bur_exist = 0;
                int id_new_emp = 1;
                if(rs.next()){
                    id_new_emp = rs.getInt("IDEMP") + 1;
                }
                rs = stmt.executeQuery("select * from employe order by idemp");
                while(rs.next()){
                    if(matricule.equals(rs.getString("MATRICULE"))){
                        flag = 1;                        
                    }
                }
                if(flag == 1){
                    mp3player.play();
                    JOptionPane.showMessageDialog(this,"Le matricule entré est déjà utilisé !","Erreur",JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    DBConnection.closeConnection();
                    Connection dbConnect2 = DBConnection.getConnection();
                    if (dbConnect2 == null) {
                    System.exit(1);
                    }
                    Statement stmt2 = dbConnect2.createStatement();
                    ResultSet rs2 = stmt2.executeQuery("select * from bureau");
                    while(rs2.next()){
                        if(idbur == rs2.getInt("IDBUR")){
                            bur_exist = 1;
                            break;
                        }
                    }
                    if(bur_exist == 0){
                        mp3player.play();
                        JOptionPane.showMessageDialog(this,"L'id ne correspond à aucun bureau !","Erreur",JOptionPane.INFORMATION_MESSAGE);
                    }                   
                    else{                        
                        Employe emp = new Employe(id_new_emp, matricule, nom, prenom, idbur);
                        EmployeDAO emdao = new EmployeDAO();                 
                        emdao.create(emp);
                        JOptionPane.showMessageDialog(this,"L'employé a bien été ajouté.","Succès",JOptionPane.INFORMATION_MESSAGE);
                        Fenetre.f.setContentPane(new MenuGestEmp());
                        Fenetre.f.repaint();
                        Fenetre.f.revalidate();
                    }
                }
                DBConnection.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(CreateEmploye.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btConfActionPerformed

    private void tfMatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfMatKeyTyped
        int l = evt.getKeyChar();
        if(!Character.isDigit(l)){
            evt.consume();
        }
    }//GEN-LAST:event_tfMatKeyTyped

    private void tfBureauKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBureauKeyTyped
        int l = evt.getKeyChar();
        if(!Character.isDigit(l)){
            evt.consume();
        }
    }//GEN-LAST:event_tfBureauKeyTyped

    private void tfNomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNomKeyTyped
        char l = evt.getKeyChar();
        if(!Character.isLetter(l)){
            evt.consume();
        }
    }//GEN-LAST:event_tfNomKeyTyped

    private void tfPrenomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPrenomKeyTyped
        char l = evt.getKeyChar();
        if(!Character.isLetter(l)){
            evt.consume();
        }
    }//GEN-LAST:event_tfPrenomKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btConf;
    private javax.swing.JButton btRetour;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField tfBureau;
    private javax.swing.JTextField tfMat;
    private javax.swing.JTextField tfNom;
    private javax.swing.JTextField tfPrenom;
    // End of variables declaration//GEN-END:variables
}
