/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newvivo.Screens;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.UIManager;

/**
 *
 * @author Joseph Bermingham
 */
public class ProjectLoad extends javax.swing.JFrame {

    /**
     * Creates new form projectLoaded
     */
    public ProjectLoad() {
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

        addWordDoc = new javax.swing.JButton();
        viewTagsBTN = new javax.swing.JButton();
        viewTagStatsBTN = new javax.swing.JButton();
        createNewTagBTN = new javax.swing.JButton();
        openWordDocBTN1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        addWordDoc.setText("Add New Word Doc");
        addWordDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addWordDocActionPerformed(evt);
            }
        });

        viewTagsBTN.setText("View Tagged Sections of text");
        viewTagsBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewTagsBTNActionPerformed(evt);
            }
        });

        viewTagStatsBTN.setText("Check Tag Statistics of File(s)");

        createNewTagBTN.setText("Create New Tag");
        createNewTagBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createNewTagBTNActionPerformed(evt);
            }
        });

        openWordDocBTN1.setText("Open Word Document");
        openWordDocBTN1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openWordDocBTN1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(createNewTagBTN, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                    .addComponent(addWordDoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(viewTagsBTN, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                    .addComponent(viewTagStatsBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(openWordDocBTN1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(openWordDocBTN1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(addWordDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(createNewTagBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(viewTagsBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(viewTagStatsBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addWordDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addWordDocActionPerformed
// Opens a blank word document with the add in open.
        // TODO add your handling code here:
        final JFileChooser fc = new JFileChooser(System.getProperty("user.dir") + "/Projects");

        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int returnVal = fc.showOpenDialog(this);
        System.out.println(returnVal);
        String newline = "\n";

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();          
            System.out.println(file.getPath());
            //create a new project from the chosen project directory,
            Main.mainObj.projectObj.addDocument(file);

        } else {
            System.out.println("Open command canceled by user." + newline);
        }    }//GEN-LAST:event_addWordDocActionPerformed

    private void viewTagsBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewTagsBTNActionPerformed
        Main.mainObj.projectLoad.setVisible(false);
        Main.mainObj.viewTags.refreshScreen();
        Main.mainObj.viewTags.setVisible(true);
    }//GEN-LAST:event_viewTagsBTNActionPerformed

    private void createNewTagBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createNewTagBTNActionPerformed
        Main.mainObj.createTag.setVisible(true);
        Main.mainObj.projectLoad.setVisible(false);


    }//GEN-LAST:event_createNewTagBTNActionPerformed

    private void openWordDocBTN1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openWordDocBTN1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_openWordDocBTN1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ProjectLoad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProjectLoad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProjectLoad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProjectLoad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProjectLoad().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addWordDoc;
    private javax.swing.JButton createNewTagBTN;
    private javax.swing.JButton openWordDocBTN1;
    private javax.swing.JButton viewTagStatsBTN;
    private javax.swing.JButton viewTagsBTN;
    // End of variables declaration//GEN-END:variables
}
