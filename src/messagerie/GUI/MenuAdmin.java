/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messagerie.GUI;

/**
 *
 * @author alanl
 */
public class MenuAdmin extends javax.swing.JPanel {

    /**
     * Creates new form MenuAdmin
     */
    public MenuAdmin() {
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

        labAdmin = new javax.swing.JLabel();
        btBureaux = new javax.swing.JButton();
        btEmpl = new javax.swing.JButton();
        btRetour = new javax.swing.JButton();

        setBackground(new java.awt.Color(102, 0, 102));
        setLayout(new java.awt.GridLayout(4, 1, 15, 15));

        labAdmin.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        labAdmin.setForeground(new java.awt.Color(204, 204, 0));
        labAdmin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labAdmin.setText("Administrateur");
        add(labAdmin);

        btBureaux.setBackground(new java.awt.Color(51, 51, 51));
        btBureaux.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btBureaux.setForeground(new java.awt.Color(204, 204, 0));
        btBureaux.setText("Gestion bureaux");
        btBureaux.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBureauxActionPerformed(evt);
            }
        });
        add(btBureaux);

        btEmpl.setBackground(new java.awt.Color(51, 51, 51));
        btEmpl.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btEmpl.setForeground(new java.awt.Color(204, 204, 0));
        btEmpl.setText("Gestion employés");
        btEmpl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEmplActionPerformed(evt);
            }
        });
        add(btEmpl);

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
    }// </editor-fold>//GEN-END:initComponents

    private void btBureauxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBureauxActionPerformed
        Fenetre.f.setContentPane(new MenuGestBur());
        Fenetre.f.repaint();
        Fenetre.f.revalidate();
    }//GEN-LAST:event_btBureauxActionPerformed

    private void btEmplActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEmplActionPerformed
        Fenetre.f.setContentPane(new MenuGestEmp());
        Fenetre.f.repaint();
        Fenetre.f.revalidate();
    }//GEN-LAST:event_btEmplActionPerformed

    private void btRetourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRetourActionPerformed
        Fenetre.f.setContentPane(new MenuPr());
        Fenetre.f.repaint();
        Fenetre.f.revalidate();
    }//GEN-LAST:event_btRetourActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBureaux;
    private javax.swing.JButton btEmpl;
    private javax.swing.JButton btRetour;
    private javax.swing.JLabel labAdmin;
    // End of variables declaration//GEN-END:variables
}
