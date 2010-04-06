/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * InitGUI.java
 *
 * Created on Apr 2, 2010, 7:43:49 PM
 */

/**
 *
 * @author anzalaya
 */
public class InitGUI extends javax.swing.JFrame {

  /** Creates new form InitGUI */
  public InitGUI(Thread t) {
    initComponents();
    thread=t;
  }

  /** This method is called from within the constructor to
   * initialize the form.
   */
  private void initComponents() {

    connect_panel = new javax.swing.JPanel();
    name_label = new javax.swing.JLabel();
    connect_button = new javax.swing.JButton();
    name_field = new javax.swing.JTextField();
    server_port = new javax.swing.JTextField();
    port_label = new javax.swing.JLabel();
    server_name = new javax.swing.JTextField();
    server_label = new javax.swing.JLabel();
    welcome = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    connect_panel.setBackground(new java.awt.Color(39, 163, 101));

    name_label.setLabelFor(name_field);
    name_label.setText("Player Name");

    connect_button.setText("Connect");
    connect_button.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
        connect_buttonActionPerformed(evt);
        }
        });

    port_label.setText("Server Port");

    server_label.setText("Server Name");

    welcome.setFont(new java.awt.Font("DejaVu Sans", 1, 18));
    welcome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    welcome.setText("Welcome to JSkat");
    welcome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

    javax.swing.GroupLayout connect_panelLayout = new javax.swing.GroupLayout(connect_panel);
    connect_panel.setLayout(connect_panelLayout);
    connect_panelLayout.setHorizontalGroup(
        connect_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(connect_panelLayout.createSequentialGroup()
          .addContainerGap()
          .addGroup(connect_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addComponent(welcome)
            .addComponent(connect_button)
            .addGroup(connect_panelLayout.createSequentialGroup()
              .addGroup(connect_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(name_label)
                .addComponent(server_label)
                .addComponent(port_label))
              .addGap(32, 32, 32)
              .addGroup(connect_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(name_field, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                .addComponent(server_port)
                .addComponent(server_name))))
          .addContainerGap(23, Short.MAX_VALUE))
        );
    connect_panelLayout.setVerticalGroup(
        connect_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(connect_panelLayout.createSequentialGroup()
          .addContainerGap()
          .addComponent(welcome)
          .addGap(25, 25, 25)
          .addGroup(connect_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(name_label)
            .addComponent(name_field, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGap(18, 18, 18)
          .addGroup(connect_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(server_label)
            .addComponent(server_port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGap(18, 18, 18)
          .addGroup(connect_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
            .addComponent(port_label)
            .addComponent(server_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGap(18, 18, 18)
          .addComponent(connect_button)
          .addContainerGap(32, Short.MAX_VALUE))
        );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(connect_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(connect_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

    pack();
  }

  private void connect_buttonActionPerformed(java.awt.event.ActionEvent evt) {
    synchronized(thread){
      thread.notifyAll();
    }
  }

  private Thread thread;

  private javax.swing.JButton connect_button;
  private javax.swing.JPanel connect_panel;
  private javax.swing.JLabel name_label;
  private javax.swing.JLabel port_label;
  private javax.swing.JLabel server_label;
  private javax.swing.JLabel welcome;

  public javax.swing.JTextField name_field;
  public javax.swing.JTextField server_name;
  public javax.swing.JTextField server_port;

}
