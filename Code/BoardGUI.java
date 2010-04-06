/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BoardGUI.java
 *
 * Created on Apr 5, 2010, 6:13:59 PM
 */

/**
 *
 * @author anzalaya
 */
public class BoardGUI extends javax.swing.JFrame {

  /** Creates new form BoardGUI */
  public BoardGUI(Thread t) {
    initComponents();
    thread=t;
  }

  /** This method is called from within the constructor to
   * initialize the form.
   */
  private void initComponents() {

    card_trick_label = new javax.swing.JLabel[3];
    card_trick_label[0] = new javax.swing.JLabel();
    card_trick_label[1] = new javax.swing.JLabel();
    card_trick_label[2] = new javax.swing.JLabel();

    game_type_dialog = new javax.swing.JDialog();
    game_type_panel = new javax.swing.JPanel();
    choose_game_label = new javax.swing.JLabel();
    clubs_radio_button = new javax.swing.JRadioButton();
    spades_radio_button = new javax.swing.JRadioButton();
    hearts_radio_button = new javax.swing.JRadioButton();
    diamonds_radio_button = new javax.swing.JRadioButton();
    null_radio_button = new javax.swing.JRadioButton();
    grand_radio_button = new javax.swing.JRadioButton();
    sepator0 = new javax.swing.JSeparator();
    choose_modifiers_label = new javax.swing.JLabel();
    hand_radio_button = new javax.swing.JRadioButton();
    schneider_radio_button = new javax.swing.JRadioButton();
    schwarz_radio_button = new javax.swing.JRadioButton();
    ouvert_radio_button = new javax.swing.JRadioButton();
    sepator1 = new javax.swing.JSeparator();
    send_game_type_info = new javax.swing.JButton();
    game_chooser_group = new javax.swing.ButtonGroup();
    result_game_dialog = new javax.swing.JDialog();
    result_game_panel = new javax.swing.JPanel();
    result_game_label = new javax.swing.JLabel();
    name_result_label0 = new javax.swing.JLabel();
    name_result_label1 = new javax.swing.JLabel();
    name_result_label2 = new javax.swing.JLabel();
    game_score_label = new javax.swing.JLabel();
    separator2 = new javax.swing.JSeparator();
    separator3 = new javax.swing.JSeparator();
    score_label0 = new javax.swing.JLabel();
    score_label1 = new javax.swing.JLabel();
    score_label2 = new javax.swing.JLabel();
    overall_score_label = new javax.swing.JLabel();
    tricks_button = new javax.swing.JButton();
    quit_button = new javax.swing.JButton();
    next_game_button = new javax.swing.JButton();
    board_panel = new javax.swing.JPanel();
    name_label0 = new javax.swing.JLabel();
    name_label1 = new javax.swing.JLabel();
    name_label2 = new javax.swing.JLabel();
    role_label0 = new javax.swing.JLabel();
    role_label2 = new javax.swing.JLabel();
    role_label1 = new javax.swing.JLabel();
    hand0 = new javax.swing.JLabel();
    hand1 = new javax.swing.JLabel();
    hand2 = new javax.swing.JLabel();
    hand3 = new javax.swing.JLabel();
    hand4 = new javax.swing.JLabel();
    hand5 = new javax.swing.JLabel();
    hand6 = new javax.swing.JLabel();
    hand7 = new javax.swing.JLabel();
    hand8 = new javax.swing.JLabel();
    hand9 = new javax.swing.JLabel();
    reizen_label0 = new javax.swing.JLabel();
    reizen_label1 = new javax.swing.JLabel();
    reizen_label2 = new javax.swing.JLabel();
    yes_reizen_button = new javax.swing.JButton();
    no_reizen_button = new javax.swing.JButton();
    last_trick_button = new javax.swing.JButton();
    skat_label0 = new javax.swing.JLabel();
    skat_label1 = new javax.swing.JLabel();
    game_label = new javax.swing.JLabel();
    turn_label = new javax.swing.JLabel();
    gametype_label = new javax.swing.JLabel();
    modifiers_label = new javax.swing.JLabel();

    game_type_dialog.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
    game_type_dialog.setTitle("Game");
    game_type_dialog.setAlwaysOnTop(true);
    game_type_dialog.setBounds(new java.awt.Rectangle(0, 0, 400, 250));
    game_type_dialog.setMinimumSize(new java.awt.Dimension(400, 250));
    game_type_dialog.setModal(true);
    game_type_dialog.setResizable(false);

    game_type_panel.setBackground(new java.awt.Color(39, 163, 101));
    game_type_panel.setMaximumSize(new java.awt.Dimension(400, 250));
    game_type_panel.setMinimumSize(new java.awt.Dimension(400, 250));
    game_type_panel.setPreferredSize(new java.awt.Dimension(400, 250));
    game_type_panel.setLayout(null);

    choose_game_label.setText("Choose Game");
    game_type_panel.add(choose_game_label);
    choose_game_label.setBounds(150, 10, 100, 15);

    game_chooser_group.add(clubs_radio_button);
    clubs_radio_button.setText("Clubs");
    game_type_panel.add(clubs_radio_button);
    clubs_radio_button.setBounds(0, 50, 60, 22);

    game_chooser_group.add(spades_radio_button);
    spades_radio_button.setText("Spades");
    game_type_panel.add(spades_radio_button);
    spades_radio_button.setBounds(100, 50, 80, 22);

    game_chooser_group.add(hearts_radio_button);
    hearts_radio_button.setText("Hearts");
    game_type_panel.add(hearts_radio_button);
    hearts_radio_button.setBounds(210, 50, 70, 22);

    game_chooser_group.add(diamonds_radio_button);
    diamonds_radio_button.setText("Diamonds");
    game_type_panel.add(diamonds_radio_button);
    diamonds_radio_button.setBounds(310, 50, 90, 22);

    game_chooser_group.add(null_radio_button);
    null_radio_button.setText("Null");
    game_type_panel.add(null_radio_button);
    null_radio_button.setBounds(50, 90, 50, 22);

    game_chooser_group.add(grand_radio_button);
    grand_radio_button.setText("Grand");
    game_type_panel.add(grand_radio_button);
    grand_radio_button.setBounds(260, 90, 63, 22);

    sepator0.setMinimumSize(new java.awt.Dimension(40, 6));
    game_type_panel.add(sepator0);
    sepator0.setBounds(0, 120, 400, 10);

    choose_modifiers_label.setText("Choose Modifiers");
    game_type_panel.add(choose_modifiers_label);
    choose_modifiers_label.setBounds(150, 130, 110, 15);

    hand_radio_button.setText("Hand");
    game_type_panel.add(hand_radio_button);
    hand_radio_button.setBounds(0, 160, 60, 22);

    schneider_radio_button.setText("Schneider");
    schneider_radio_button.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
        schneider_radio_buttonActionPerformed(evt);
        }
        });
    game_type_panel.add(schneider_radio_button);
    schneider_radio_button.setBounds(90, 160, 90, 22);

    schwarz_radio_button.setText("Schwarz");
    schwarz_radio_button.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
        schwarz_radio_buttonActionPerformed(evt);
        }
        });
    game_type_panel.add(schwarz_radio_button);
    schwarz_radio_button.setBounds(220, 160, 80, 22);

    ouvert_radio_button.setText("Ouvert");
    ouvert_radio_button.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
        ouvert_radio_buttonActionPerformed(evt);
        }
        });
    game_type_panel.add(ouvert_radio_button);
    ouvert_radio_button.setBounds(330, 160, 70, 22);

    sepator1.setMinimumSize(new java.awt.Dimension(40, 6));
    game_type_panel.add(sepator1);
    sepator1.setBounds(0, 190, 400, 10);

    send_game_type_info.setText("Send");
    send_game_type_info.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
        send_game_type_infoActionPerformed(evt);
        }
        });
    game_type_panel.add(send_game_type_info);
    send_game_type_info.setBounds(160, 210, 67, 27);

    javax.swing.GroupLayout game_type_dialogLayout = new javax.swing.GroupLayout(game_type_dialog.getContentPane());
    game_type_dialog.getContentPane().setLayout(game_type_dialogLayout);
    game_type_dialogLayout.setHorizontalGroup(
        game_type_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(game_type_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    game_type_dialogLayout.setVerticalGroup(
        game_type_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(game_type_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

    game_type_panel.getAccessibleContext().setAccessibleName("null");

    result_game_dialog.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
    result_game_dialog.setTitle("Result");
    result_game_dialog.setAlwaysOnTop(true);
    result_game_dialog.setModal(true);
    result_game_dialog.setResizable(false);

    result_game_panel.setBackground(new java.awt.Color(39, 163, 101));
    result_game_panel.setLayout(null);

    result_game_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    result_game_label.setText("<html><p style=\"text-align: center;\">Player0 loses<br>with 60 vs. 60<br>The value of the game is -100</p></html>");
    result_game_panel.add(result_game_label);
    result_game_label.setBounds(100, 30, 193, 60);

    name_result_label0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    name_result_label0.setText("Player0");
    result_game_panel.add(name_result_label0);
    name_result_label0.setBounds(10, 130, 50, 15);

    name_result_label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    name_result_label1.setText("Player1");
    result_game_panel.add(name_result_label1);
    name_result_label1.setBounds(170, 130, 50, 15);

    name_result_label2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    name_result_label2.setText("Player2");
    result_game_panel.add(name_result_label2);
    name_result_label2.setBounds(340, 130, 50, 15);

    game_score_label.setText("Score");
    result_game_panel.add(game_score_label);
    game_score_label.setBounds(180, 0, 47, 15);
    result_game_panel.add(separator2);
    separator2.setBounds(0, 90, 400, 10);
    result_game_panel.add(separator3);
    separator3.setBounds(0, 190, 400, 6);

    score_label0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    score_label0.setText("1000");
    result_game_panel.add(score_label0);
    score_label0.setBounds(10, 160, 40, 15);

    score_label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    score_label1.setText("1000");
    result_game_panel.add(score_label1);
    score_label1.setBounds(170, 160, 50, 15);

    score_label2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    score_label2.setText("1000");
    result_game_panel.add(score_label2);
    score_label2.setBounds(340, 160, 50, 15);

    overall_score_label.setText("Overall Score");
    result_game_panel.add(overall_score_label);
    overall_score_label.setBounds(150, 100, 90, 15);

    tricks_button.setText("Tricks");
    result_game_panel.add(tricks_button);
    tricks_button.setBounds(160, 240, 90, 27);

    quit_button.setText("Quit");
    result_game_panel.add(quit_button);
    quit_button.setBounds(330, 240, 70, 27);

    next_game_button.setText("Next Game");
    next_game_button.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
        next_game_buttonActionPerformed(evt);
        }
        });
    result_game_panel.add(next_game_button);
    next_game_button.setBounds(0, 240, 90, 27);

    javax.swing.GroupLayout result_game_dialogLayout = new javax.swing.GroupLayout(result_game_dialog.getContentPane());
    result_game_dialog.getContentPane().setLayout(result_game_dialogLayout);
    result_game_dialogLayout.setHorizontalGroup(
        result_game_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(result_game_panel, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
        );
    result_game_dialogLayout.setVerticalGroup(
        result_game_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(result_game_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Skat");
    setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    setMinimumSize(new java.awt.Dimension(1000, 650));
    setName("board_frame");
    setResizable(false);

    board_panel.setBackground(new java.awt.Color(39, 163, 101));
    board_panel.setLayout(null);

    name_label0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    name_label0.setText("Player0");
    board_panel.add(name_label0);
    name_label0.setBounds(460, 620, 100, 20);

    name_label1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    name_label1.setText("Player1");
    board_panel.add(name_label1);
    name_label1.setBounds(10, 10, 90, 20);

    name_label2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    name_label2.setText("Player2");
    board_panel.add(name_label2);
    name_label2.setBounds(900, 10, 90, 20);

    role_label0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    role_label0.setText("LISTENER");
    board_panel.add(role_label0);
    role_label0.setBounds(470, 600, 90, 15);

    role_label2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    role_label2.setText("LISTENER");
    board_panel.add(role_label2);
    role_label2.setBounds(907, 40, 80, 15);

    role_label1.setText("LISTENER");
    board_panel.add(role_label1);
    role_label1.setBounds(10, 40, 70, 15);

    hand0.setFont(new java.awt.Font("DejaVu Sans", 0, 9));
    hand0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    hand0.setText("SEVEN DIAMONDS");
    hand0.setMaximumSize(new java.awt.Dimension(90, 15));
    hand0.setMinimumSize(new java.awt.Dimension(90, 15));
    hand0.setPreferredSize(new java.awt.Dimension(90, 15));
    hand0.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
        hand0MouseClicked(evt);
        }
        });
    board_panel.add(hand0);
    hand0.setBounds(0, 580, 90, 15);

    hand1.setFont(new java.awt.Font("DejaVu Sans", 0, 9));
    hand1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    hand1.setText("SEVEN DIAMONDS");
    hand1.setMaximumSize(new java.awt.Dimension(90, 15));
    hand1.setMinimumSize(new java.awt.Dimension(90, 15));
    hand1.setPreferredSize(new java.awt.Dimension(90, 15));
    hand1.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
        hand1MouseClicked(evt);
        }
        });
    board_panel.add(hand1);
    hand1.setBounds(100, 580, 90, 15);

    hand2.setFont(new java.awt.Font("DejaVu Sans", 0, 9));
    hand2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    hand2.setText("SEVEN DIAMONDS");
    hand2.setMaximumSize(new java.awt.Dimension(90, 15));
    hand2.setMinimumSize(new java.awt.Dimension(90, 15));
    hand2.setPreferredSize(new java.awt.Dimension(90, 15));
    hand2.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
        hand2MouseClicked(evt);
        }
        });
    board_panel.add(hand2);
    hand2.setBounds(200, 580, 90, 15);

    hand3.setFont(new java.awt.Font("DejaVu Sans", 0, 9));
    hand3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    hand3.setText("SEVEN DIAMONDS");
    hand3.setMaximumSize(new java.awt.Dimension(90, 15));
    hand3.setMinimumSize(new java.awt.Dimension(90, 15));
    hand3.setPreferredSize(new java.awt.Dimension(90, 15));
    hand3.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
        hand3MouseClicked(evt);
        }
        });
    board_panel.add(hand3);
    hand3.setBounds(300, 580, 90, 15);

    hand4.setFont(new java.awt.Font("DejaVu Sans", 0, 9));
    hand4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    hand4.setText("SEVEN DIAMONDS");
    hand4.setMaximumSize(new java.awt.Dimension(90, 15));
    hand4.setMinimumSize(new java.awt.Dimension(90, 15));
    hand4.setPreferredSize(new java.awt.Dimension(90, 15));
    hand4.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
        hand4MouseClicked(evt);
        }
        });
    board_panel.add(hand4);
    hand4.setBounds(400, 580, 90, 15);

    hand5.setFont(new java.awt.Font("DejaVu Sans", 0, 9));
    hand5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    hand5.setText("SEVEN DIAMONDS");
    hand5.setMaximumSize(new java.awt.Dimension(90, 15));
    hand5.setMinimumSize(new java.awt.Dimension(90, 15));
    hand5.setPreferredSize(new java.awt.Dimension(90, 15));
    hand5.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
        hand5MouseClicked(evt);
        }
        });
    board_panel.add(hand5);
    hand5.setBounds(500, 580, 90, 15);

    hand6.setFont(new java.awt.Font("DejaVu Sans", 0, 9));
    hand6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    hand6.setText("SEVEN DIAMONDS");
    hand6.setMaximumSize(new java.awt.Dimension(90, 15));
    hand6.setMinimumSize(new java.awt.Dimension(90, 15));
    hand6.setPreferredSize(new java.awt.Dimension(90, 15));
    hand6.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
        hand6MouseClicked(evt);
        }
        });
    board_panel.add(hand6);
    hand6.setBounds(600, 580, 90, 15);

    hand7.setFont(new java.awt.Font("DejaVu Sans", 0, 9));
    hand7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    hand7.setText("SEVEN DIAMONDS");
    hand7.setMaximumSize(new java.awt.Dimension(90, 15));
    hand7.setMinimumSize(new java.awt.Dimension(90, 15));
    hand7.setPreferredSize(new java.awt.Dimension(90, 15));
    hand7.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
        hand7MouseClicked(evt);
        }
        });
    board_panel.add(hand7);
    hand7.setBounds(700, 580, 90, 15);

    hand8.setFont(new java.awt.Font("DejaVu Sans", 0, 9));
    hand8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    hand8.setText("SEVEN DIAMONDS");
    hand8.setMaximumSize(new java.awt.Dimension(90, 15));
    hand8.setMinimumSize(new java.awt.Dimension(90, 15));
    hand8.setPreferredSize(new java.awt.Dimension(90, 15));
    hand8.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
        hand8MouseClicked(evt);
        }
        });
    board_panel.add(hand8);
    hand8.setBounds(800, 580, 90, 15);

    hand9.setFont(new java.awt.Font("DejaVu Sans", 0, 9));
    hand9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    hand9.setText("SEVEN DIAMONDS");
    hand9.setMaximumSize(new java.awt.Dimension(90, 15));
    hand9.setMinimumSize(new java.awt.Dimension(90, 15));
    hand9.setPreferredSize(new java.awt.Dimension(90, 15));
    hand9.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
        hand9MouseClicked(evt);
        }
        });
    board_panel.add(hand9);
    hand9.setBounds(900, 580, 90, 15);

    reizen_label0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    reizen_label0.setText("accepts 110");
    board_panel.add(reizen_label0);
    reizen_label0.setBounds(460, 550, 110, 15);

    reizen_label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    reizen_label1.setText("accepts 110");
    board_panel.add(reizen_label1);
    reizen_label1.setBounds(120, 20, 100, 15);

    reizen_label2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    reizen_label2.setText("refuses 110");
    board_panel.add(reizen_label2);
    reizen_label2.setBounds(780, 20, 100, 15);

    yes_reizen_button.setText("100 ?");
    yes_reizen_button.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
        yes_reizen_buttonActionPerformed(evt);
        }
        });
    board_panel.add(yes_reizen_button);
    yes_reizen_button.setBounds(407, 520, 80, 27);

    no_reizen_button.setText("Pass");
    no_reizen_button.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
        no_reizen_buttonActionPerformed(evt);
        }
        });
    board_panel.add(no_reizen_button);
    no_reizen_button.setBounds(550, 520, 70, 27);

    last_trick_button.setText("Last Trick");
    last_trick_button.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
        last_trick_buttonActionPerformed(evt);
        }
        });
    board_panel.add(last_trick_button);
    last_trick_button.setBounds(10, 610, 150, 27);

    skat_label0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    skat_label0.setText("SEVEN DIAMONDS");
    skat_label0.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
        skat_label0MouseClicked(evt);
        }
        });
    board_panel.add(skat_label0);
    skat_label0.setBounds(375, 490, 130, 15);

    skat_label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    skat_label1.setText("SEVEN DIAMONDS");
    skat_label1.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
        skat_label1MouseClicked(evt);
        }
        });
    board_panel.add(skat_label1);
    skat_label1.setBounds(520, 490, 140, 15);

    card_trick_label[0].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    card_trick_label[0].setText("SEVEN DIAMONDS");
    board_panel.add(card_trick_label[0]);
    card_trick_label[0].setBounds(450, 290, 130, 15);

    card_trick_label[1].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    card_trick_label[1].setText("SEVEN DIAMONDS");
    board_panel.add(card_trick_label[1]);
    card_trick_label[1].setBounds(320, 230, 150, 15);

    card_trick_label[2].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    card_trick_label[2].setText("SEVEN DIAMONDS");
    board_panel.add(card_trick_label[2]);
    card_trick_label[2].setBounds(560, 230, 130, 20);

    game_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    game_label.setText("game 100");
    board_panel.add(game_label);
    game_label.setBounds(480, 10, 80, 20);

    turn_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    turn_label.setText("turn 10");
    board_panel.add(turn_label);
    turn_label.setBounds(480, 40, 70, 20);

    gametype_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    gametype_label.setText("Player0 chose diamonds");
    board_panel.add(gametype_label);
    gametype_label.setBounds(420, 70, 180, 20);

    modifiers_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    modifiers_label.setText("hand, schneider, schwarz, ouvert");
    board_panel.add(modifiers_label);
    modifiers_label.setBounds(370, 100, 300, 20);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(board_panel, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(board_panel, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
        );

    pack();
  }

  private void yes_reizen_buttonActionPerformed(java.awt.event.ActionEvent evt) {
  }

  private void no_reizen_buttonActionPerformed(java.awt.event.ActionEvent evt) {
  }

  private void hand0MouseClicked(java.awt.event.MouseEvent evt) {
  }

  private void hand1MouseClicked(java.awt.event.MouseEvent evt) {
  }

  private void hand2MouseClicked(java.awt.event.MouseEvent evt) {
  }

  private void hand3MouseClicked(java.awt.event.MouseEvent evt) {
  }

  private void hand4MouseClicked(java.awt.event.MouseEvent evt) {
  }

  private void hand5MouseClicked(java.awt.event.MouseEvent evt) {
  }

  private void hand6MouseClicked(java.awt.event.MouseEvent evt) {
  }

  private void hand7MouseClicked(java.awt.event.MouseEvent evt) {
  }

  private void hand8MouseClicked(java.awt.event.MouseEvent evt) {
  }

  private void hand9MouseClicked(java.awt.event.MouseEvent evt) {
  }

  private void send_game_type_infoActionPerformed(java.awt.event.ActionEvent evt) {
  }

  private void schneider_radio_buttonActionPerformed(java.awt.event.ActionEvent evt) {
  }

  private void schwarz_radio_buttonActionPerformed(java.awt.event.ActionEvent evt) {
  }

  private void ouvert_radio_buttonActionPerformed(java.awt.event.ActionEvent evt) {
  }

  private void last_trick_buttonActionPerformed(java.awt.event.ActionEvent evt) {
  }

  private void skat_label0MouseClicked(java.awt.event.MouseEvent evt) {
  }

  private void skat_label1MouseClicked(java.awt.event.MouseEvent evt) {
  }

  private void next_game_buttonActionPerformed(java.awt.event.ActionEvent evt) {
  }


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JPanel board_panel;
  private javax.swing.JPanel result_game_panel;
  private javax.swing.JPanel game_type_panel;
  private Thread thread;

  public javax.swing.JLabel card_trick_label[];
//  public javax.swing.JLabel card_trick_label[0];
//  public javax.swing.JLabel card_trick_label[1];
//  public javax.swing.JLabel card_trick_label[2];
  public javax.swing.JLabel choose_game_label;
  public javax.swing.JLabel choose_modifiers_label;
  public javax.swing.JRadioButton clubs_radio_button;
  public javax.swing.JRadioButton diamonds_radio_button;
  public javax.swing.ButtonGroup game_chooser_group;
  public javax.swing.JLabel game_label;
  public javax.swing.JLabel game_score_label;
  public javax.swing.JDialog game_type_dialog;
  public javax.swing.JRadioButton grand_radio_button;
  public javax.swing.JLabel hand0;
  public javax.swing.JLabel hand1;
  public javax.swing.JLabel hand2;
  public javax.swing.JLabel hand3;
  public javax.swing.JLabel hand4;
  public javax.swing.JLabel hand5;
  public javax.swing.JLabel hand6;
  public javax.swing.JLabel hand7;
  public javax.swing.JLabel hand8;
  public javax.swing.JLabel hand9;
  public javax.swing.JRadioButton hand_radio_button;
  public javax.swing.JRadioButton hearts_radio_button;
  public javax.swing.JLabel turn_label;
  public javax.swing.JLabel gametype_label;
  public javax.swing.JLabel modifiers_label;
  public javax.swing.JButton last_trick_button;
  public javax.swing.JLabel name_label0;
  public javax.swing.JLabel name_label1;
  public javax.swing.JLabel name_label2;
  public javax.swing.JLabel name_result_label0;
  public javax.swing.JLabel name_result_label1;
  public javax.swing.JLabel name_result_label2;
  public javax.swing.JButton next_game_button;
  public javax.swing.JButton no_reizen_button;
  public javax.swing.JRadioButton null_radio_button;
  public javax.swing.JRadioButton ouvert_radio_button;
  public javax.swing.JLabel overall_score_label;
  public javax.swing.JButton quit_button;
  public javax.swing.JLabel reizen_label0;
  public javax.swing.JLabel reizen_label1;
  public javax.swing.JLabel reizen_label2;
  public javax.swing.JDialog result_game_dialog;
  public javax.swing.JLabel result_game_label;
  public javax.swing.JLabel role_label0;
  public javax.swing.JLabel role_label1;
  public javax.swing.JLabel role_label2;
  public javax.swing.JRadioButton schneider_radio_button;
  public javax.swing.JRadioButton schwarz_radio_button;
  public javax.swing.JLabel score_label0;
  public javax.swing.JLabel score_label1;
  public javax.swing.JLabel score_label2;
  public javax.swing.JButton send_game_type_info;
  public javax.swing.JSeparator separator2;
  public javax.swing.JSeparator separator3;
  public javax.swing.JSeparator sepator0;
  public javax.swing.JSeparator sepator1;
  public javax.swing.JLabel skat_label0;
  public javax.swing.JLabel skat_label1;
  public javax.swing.JRadioButton spades_radio_button;
  public javax.swing.JButton tricks_button;
  public javax.swing.JButton yes_reizen_button;
}
