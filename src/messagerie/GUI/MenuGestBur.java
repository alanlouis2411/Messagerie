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
public class MenuGestBur extends javax.swing.JPanel {

    /**
     * Creates new form MenuGestBur
     */
    public MenuGestBur() {
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
        btDelete = new javax.swing.JButton();
        btRetour = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 0, 204));
        setLayout(new java.awt.GridLayout(5, 1, 20, 20));

        btCrea.setBackground(new java.awt.Color(51, 51, 51));
        btCrea.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btCrea.setForeground(new java.awt.Color(255, 255, 255));
        btCrea.setText("Créer bureau");
        btCrea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCreaActionPerformed(evt);
            }
        });
        add(btCrea);

        btRead.setBackground(new java.awt.Color(51, 51, 51));
        btRead.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btRead.setForeground(new java.awt.Color(255, 255, 255));
        btRead.setText("Voir bureau");
        btRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReadActionPerformed(evt);
            }
        });
        add(btRead);

        btModif.setBackground(new java.awt.Color(51, 51, 51));
        btModif.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btModif.setForeground(new java.awt.Color(255, 255, 255));
        btModif.setText("Modifier bureau");
        btModif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModifActionPerformed(evt);
            }
        });
        add(btModif);

        btDelete.setBackground(new java.awt.Color(51, 51, 51));
        btDelete.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btDelete.setForeground(new java.awt.Color(255, 255, 255));
        btDelete.setText("Supprimer bureau");
        btDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteActionPerformed(evt);
            }
        });
        add(btDelete);

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
        Fenetre.f.setContentPane(new CreateBureau());
        Fenetre.f.repaint();
        Fenetre.f.revalidate();
    }//GEN-LAST:event_btCreaActionPerformed

    private void btReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReadActionPerformed
        Fenetre.f.setContentPane(new ReadBureau());
        Fenetre.f.repaint();
        Fenetre.f.revalidate();
    }//GEN-LAST:event_btReadActionPerformed

    private void btModifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btModifActionPerformed
        Fenetre.f.setContentPane(new ModifBureau());
        Fenetre.f.repaint();
        Fenetre.f.revalidate();
    }//GEN-LAST:event_btModifActionPerformed

    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteActionPerformed
        Fenetre.f.setContentPane(new DeleteBureau());
        Fenetre.f.repaint();
        Fenetre.f.revalidate();
    }//GEN-LAST:event_btDeleteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCrea;
    private javax.swing.JButton btDelete;
    private javax.swing.JButton btModif;
    private javax.swing.JButton btRead;
    private javax.swing.JButton btRetour;
    // End of variables declaration//GEN-END:variables
}
