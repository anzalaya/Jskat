/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AiGUI.java
 *
 * Created on Apr 2, 2010, 8:57:49 PM
 */

/**
 *
 * @author anzalaya
 */
public class AiGUI extends javax.swing.JFrame {

    /** Creates new form AiGUI */
    public AiGUI() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelConnectScreen = new javax.swing.JPanel();
        welcome = new javax.swing.JLabel();
        AI_Info = new javax.swing.JLabel();
        no_button = new javax.swing.JButton();
        yes_button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelConnectScreen.setBackground(new java.awt.Color(39, 163, 101));

        welcome.setFont(new java.awt.Font("DejaVu Sans", 1, 18));
        welcome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcome.setText("Welcome to JSkat");
        welcome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        no_button.setText("No");
        no_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                no_buttonActionPerformed(evt);
            }
        });

        yes_button.setText("Yes");
        yes_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yes_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelConnectScreenLayout = new javax.swing.GroupLayout(jPanelConnectScreen);
        jPanelConnectScreen.setLayout(jPanelConnectScreenLayout);
        jPanelConnectScreenLayout.setHorizontalGroup(
            jPanelConnectScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConnectScreenLayout.createSequentialGroup()
                .addGroup(jPanelConnectScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelConnectScreenLayout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(AI_Info))
                    .addGroup(jPanelConnectScreenLayout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addGroup(jPanelConnectScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanelConnectScreenLayout.createSequentialGroup()
                                .addComponent(yes_button)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(no_button))
                            .addComponent(welcome, javax.swing.GroupLayout.Alignment.LEADING))))
                .addContainerGap(119, Short.MAX_VALUE))
        );
        jPanelConnectScreenLayout.setVerticalGroup(
            jPanelConnectScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConnectScreenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(welcome)
                .addGap(70, 70, 70)
                .addComponent(AI_Info)
                .addGap(98, 98, 98)
                .addGroup(jPanelConnectScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(no_button)
                    .addComponent(yes_button))
                .addGap(48, 48, 48))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelConnectScreen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelConnectScreen, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void yes_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yes_buttonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_yes_buttonActionPerformed

    private void no_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_no_buttonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_no_buttonActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AiGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AI_Info;
    private javax.swing.JPanel jPanelConnectScreen;
    private javax.swing.JButton no_button;
    private javax.swing.JLabel welcome;
    private javax.swing.JButton yes_button;
    // End of variables declaration//GEN-END:variables

}
