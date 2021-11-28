/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

/**
 *
 * @author HUỲNH QUANG VINH
 */
public class Notification extends javax.swing.JDialog {

    private final ClientUI index;

    public Notification(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        index = (ClientUI) parent;
        loadDataNofitication();
    }

    private void loadDataNofitication() {
        String notifiLine = "";
        for (String notifi : index.getListNotification()) {
            notifiLine += notifi + System.lineSeparator();
        }
        txtNotifications.setText(notifiLine);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNotifications = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocation(new java.awt.Point(500, 200));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)));
        jPanel1.setLayout(null);

        txtNotifications.setColumns(20);
        txtNotifications.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        txtNotifications.setRows(5);
        txtNotifications.setText("Bạn vừa được chia sẻ một file từ huynhquangvinh01121999@gmail.com\nBạn vừa được chia sẻ một file từ huynhquangvinh01121999@gmail.com\nBạn vừa được chia sẻ một file từ huynhquangvinh01121999@gmail.com\nBạn vừa được chia sẻ một file từ huynhquangvinh01121999@gmail.com\nBạn vừa được chia sẻ một file từ huynhquangvinh01121999@gmail.com\nBạn vừa được chia sẻ một file từ huynhquangvinh01121999@gmail.com\nBạn vừa được chia sẻ một file từ huynhquangvinh01121999@gmail.com\nBạn vừa được chia sẻ một file từ huynhquangvinh01121999@gmail.com\nBạn vừa được chia sẻ một file từ huynhquangvinh01121999@gmail.com\nBạn vừa được chia sẻ một file từ huynhquangvinh01121999@gmail.com");
        txtNotifications.setWrapStyleWord(true);
        txtNotifications.setFocusable(false);
        txtNotifications.setMargin(new java.awt.Insets(2, 4, 2, 2));
        jScrollPane1.setViewportView(txtNotifications);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(0, 30, 541, 172);

        jLabel1.setFont(new java.awt.Font("Tempus Sans ITC", 1, 14)); // NOI18N
        jLabel1.setText("Notifications");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 5, 110, 20);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons8-cancel-24.png"))); // NOI18N
        jLabel2.setToolTipText("Close");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel2);
        jLabel2.setBounds(510, 3, 30, 25);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Notification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Notification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Notification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Notification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Notification dialog = new Notification(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtNotifications;
    // End of variables declaration//GEN-END:variables
}
