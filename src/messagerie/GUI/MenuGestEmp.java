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
public class MenuGestEmp extends javax.swing.JPanel {

    /**
     * Creates new form MenuGestEmp
     */
    public MenuGestEmp() {
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

        btCrea = new javax.swing.JButton();
        btRead = new javax.swing.JButton();
        btModif = new javax.swing.JButton();
        btSupp = new javax.swing.JButton();
        btRetour = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 153, 153));
        setLayout(new java.awt.GridLayout(5, 1, 20, 20));

        btCrea.setBackground(new java.awt.Color(51, 51, 51));
        btCrea.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btCrea.setForeground(new java.awt.Color(255, 255, 255));
        btCrea.setText("Ajouter employé");
        btCrea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCreaActionPerformed(evt);
            }
        });
        add(btCrea);

        btRead.setBackground(new java.awt.Color(51, 51, 51));
        btRead.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btRead.setForeground(new java.awt.Color(255, 255, 255));
        btRead.setText("Voir infos employé (+messages envoyés)");
        btRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReadActionPerformed(evt);
            }
        });
        add(btRead);

        btModif.setBackground(new java.awt.Color(51, 51, 51));
        btModif.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btModif.setForeground(new java.awt.Color(255, 255, 255));
        btModif.setText("Modifier infos employé");
        btModif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModifActionPerformed(evt);
            }
        });
        add(btModif);

        btSupp.setBackground(new java.awt.Color(51, 51, 51));
        btSupp.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btSupp.setForeground(new java.awt.Color(255, 255, 255));
        btSupp.setText("Virer employé");
        btSupp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSuppActionPerformed(evt);
            }
        });
        add(btSupp);

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
        Fenetre.f.setContentPane(new MenuAdmin());
        Fenetre.f.repaint();
        Fenetre.f.revalidate();
    }//GEN-LAST:event_btRetourActionPerformed

    private void btCreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCreaActionPerformed
        Fenetre.f.setContentPane(new CreateEmploye());
        Fenetre.f.repaint();
        Fenetre.f.revalidate();
    }//GEN-LAST:event_btCreaActionPerformed

    private void btReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReadActionPerformed
        Fenetre.f.setContentPane(new ChoixRechercheEmp());
        Fenetre.f.repaint();
        Fenetre.f.revalidate();
    }//GEN-LAST:event_btReadActionPerformed

    private void btModifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btModifActionPerformed
        Fenetre.f.setContentPane(new ModifEmploye());
        Fenetre.f.repaint();
        Fenetre.f.revalidate();
    }//GEN-LAST:event_btModifActionPerformed

    private void btSuppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSuppActionPerformed
        Fenetre.f.setContentPane(new DeleteEmploye());
        Fenetre.f.repaint();
        Fenetre.f.revalidate();
    }//GEN-LAST:event_btSuppActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCrea;
    private javax.swing.JButton btModif;
    private javax.swing.JButton btRead;
    private javax.swing.JButton btRetour;
    private javax.swing.JButton btSupp;
    // End of variables declaration//GEN-END:variables
}
