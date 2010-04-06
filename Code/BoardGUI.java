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
    skat_rq=false;
    play_rq=false;
  }

  /** This method is called from within the constructor to
   * initialize the form.
   */
  private void initComponents() {

    card_trick_label = new javax.swing.JLabel[3];
    card_trick_label[0] = new javax.swing.JLabel();
    card_trick_label[1] = new javax.swing.JLabel();
    card_trick_label[2] = new javax.swing.JLabel();

    hand = new javax.swing.JLabel[10];
    hand[0] = new javax.swing.JLabel();
    hand[1] = new javax.swing.JLabel();
    hand[2] = new javax.swing.JLabel();
    hand[3] = new javax.swing.JLabel();
    hand[4] = new javax.swing.JLabel();
    hand[5] = new javax.swing.JLabel();
    hand[6] = new javax.swing.JLabel();
    hand[7] = new javax.swing.JLabel();
    hand[8] = new javax.swing.JLabel();
    hand[9] = new javax.swing.JLabel();

    name_result_label = new javax.swing.JLabel[3];
    name_result_label[0] = new javax.swing.JLabel();
    name_result_label[1] = new javax.swing.JLabel();
    name_result_label[2] = new javax.swing.JLabel();

    score_label = new javax.swing.JLabel[3];
    score_label[0] = new javax.swing.JLabel();
    score_label[1] = new javax.swing.JLabel();
    score_label[2] = new javax.swing.JLabel();

    name_label = new javax.swing.JLabel[3];
    name_label[0] = new javax.swing.JLabel();
    name_label[1] = new javax.swing.JLabel();
    name_label[2] = new javax.swing.JLabel();

    role_label = new javax.swing.JLabel[3];
    role_label[0] = new javax.swing.JLabel();
    role_label[2] = new javax.swing.JLabel();
    role_label[1] = new javax.swing.JLabel();

    reizen_label = new javax.swing.JLabel[3];
    reizen_label[0] = new javax.swing.JLabel();
    reizen_label[1] = new javax.swing.JLabel();
    reizen_label[2] = new javax.swing.JLabel();

    skat_label = new javax.swing.JLabel[3];
    skat_label[0] = new javax.swing.JLabel();
    skat_label[1] = new javax.swing.JLabel();
    game_type_dialog = new javax.swing.JDialog();
    game_type_panel = new javax.swing.JPanel();
    choose_game_label = new javax.swing.JLabel();
    clubs_radio_button = new javax.swing.JRadioButton();
    spades_radio_button = new javax.swing.JRadioButton();
    hearts_radio_button = new javax.swing.JRadioButton();
    diamonds_radio_button = new javax.swing.JRadioButton();
    null_radio_button = new javax.swing.JRadioButton();
    grand_radio_button = new javax.swing.JRadioButton();
    separator0 = new javax.swing.JSeparator();
    separator1 = new javax.swing.JSeparator();
    separator2 = new javax.swing.JSeparator();
    separator3 = new javax.swing.JSeparator();
    choose_modifiers_label = new javax.swing.JLabel();
    hand_radio_button = new javax.swing.JRadioButton();
    schneider_radio_button = new javax.swing.JRadioButton();
    schwarz_radio_button = new javax.swing.JRadioButton();
    ouvert_radio_button = new javax.swing.JRadioButton();
    send_game_type_info = new javax.swing.JButton();
    game_chooser_group = new javax.swing.ButtonGroup();
    result_game_dialog = new javax.swing.JDialog();
    result_game_panel = new javax.swing.JPanel();
    result_game_label = new javax.swing.JLabel();
    game_score_label = new javax.swing.JLabel();
    overall_score_label = new javax.swing.JLabel();
    tricks_button = new javax.swing.JButton();
    quit_button = new javax.swing.JButton();
    next_game_button = new javax.swing.JButton();
    board_panel = new javax.swing.JPanel();
    yes_reizen_button = new javax.swing.JButton();
    no_reizen_button = new javax.swing.JButton();
    last_trick_button = new javax.swing.JButton();
    game_label = new javax.swing.JLabel();
    turn_label = new javax.swing.JLabel();
    gametype_label = new javax.swing.JLabel();
    modifiers_label = new javax.swing.JLabel();

    game_type_dialog.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
    game_type_dialog.setTitle("Game");
    game_type_dialog.setAlwaysOnTop(true);
    game_type_dialog.setBounds(new java.awt.Rectangle(0, 0, 600, 400));
    game_type_dialog.setMinimumSize(new java.awt.Dimension(600, 400));
    game_type_dialog.setModal(true);
    game_type_dialog.setResizable(false);

    game_type_panel.setBackground(new java.awt.Color(39, 163, 101));
    game_type_panel.setMaximumSize(new java.awt.Dimension(500, 400));
    game_type_panel.setMinimumSize(new java.awt.Dimension(500, 400));
    game_type_panel.setPreferredSize(new java.awt.Dimension(500, 400));
    game_type_panel.setLayout(null);

    choose_game_label.setText("Choose Game");
    game_type_panel.add(choose_game_label);
    choose_game_label.setBounds(150, 10, 100, 15);

    game_chooser_group.add(clubs_radio_button);
    clubs_radio_button.setText("Clubs");
    clubs_radio_button.setBackground(new java.awt.Color(39, 163, 101));
    game_type_panel.add(clubs_radio_button);
    clubs_radio_button.setBounds(5, 50, 100, 22);

    game_chooser_group.add(spades_radio_button);
    spades_radio_button.setText("Spades");
    spades_radio_button.setBackground(new java.awt.Color(39, 163, 101));
    game_type_panel.add(spades_radio_button);
    spades_radio_button.setBounds(115, 50, 100, 22);

    game_chooser_group.add(hearts_radio_button);
    hearts_radio_button.setText("Hearts");
    hearts_radio_button.setBackground(new java.awt.Color(39, 163, 101));
    game_type_panel.add(hearts_radio_button);
    hearts_radio_button.setBounds(225, 50, 100, 22);

    game_chooser_group.add(diamonds_radio_button);
    diamonds_radio_button.setText("Diamonds");
    diamonds_radio_button.setBackground(new java.awt.Color(39, 163, 101));
    game_type_panel.add(diamonds_radio_button);
    diamonds_radio_button.setBounds(335, 50, 100, 22);

    game_chooser_group.add(null_radio_button);
    null_radio_button.setText("Null");
    null_radio_button.setBackground(new java.awt.Color(39, 163, 101));
    game_type_panel.add(null_radio_button);
    null_radio_button.setBounds(60, 90, 100, 22);

    game_chooser_group.add(grand_radio_button);
    grand_radio_button.setText("Grand");
    grand_radio_button.setBackground(new java.awt.Color(39, 163, 101));
    game_type_panel.add(grand_radio_button);
    grand_radio_button.setBounds(280, 90, 100, 22);

    separator0.setMinimumSize(new java.awt.Dimension(40, 6));
    game_type_panel.add(separator0);
    separator0.setBounds(0, 120, 600, 10);

    choose_modifiers_label.setText("Choose Modifiers");
    game_type_panel.add(choose_modifiers_label);
    choose_modifiers_label.setBounds(150, 130, 110, 15);

    hand_radio_button.setText("Hand");
    game_type_panel.add(hand_radio_button);
    hand_radio_button.setBounds(0, 160, 100, 22);
  //  hand_radio_button.setBackground(new java.awt.Color(39, 163, 101));
    hand_radio_button.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
        hand_radio_buttonActionPerformed(evt);
        }
        });

    schneider_radio_button.setText("Schneider");
   // schneider_radio_button.setBackground(new java.awt.Color(39, 163, 101));
    schneider_radio_button.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
        schneider_radio_buttonActionPerformed(evt);
        }
        });
    game_type_panel.add(schneider_radio_button);
    schneider_radio_button.setBounds(90, 160, 100, 22);

    schwarz_radio_button.setText("Schwarz");
   // schwarz_radio_button.setBackground(new java.awt.Color(39, 163, 101));
    schwarz_radio_button.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
        schwarz_radio_buttonActionPerformed(evt);
        }
        });
    game_type_panel.add(schwarz_radio_button);
    schwarz_radio_button.setBounds(220, 160, 100, 22);

    ouvert_radio_button.setText("Ouvert");
  //  ouvert_radio_button.setBackground(new java.awt.Color(39, 163, 101));
    ouvert_radio_button.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
        ouvert_radio_buttonActionPerformed(evt);
        }
        });
    game_type_panel.add(ouvert_radio_button);
    ouvert_radio_button.setBounds(330, 160, 100, 22);

    separator1.setMinimumSize(new java.awt.Dimension(40, 6));
    game_type_panel.add(separator1);
    separator1.setBounds(0, 190, 600, 10);

    send_game_type_info.setText("Send");
    send_game_type_info.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
        send_game_type_infoActionPerformed(evt);
        }
        });
    game_type_panel.add(send_game_type_info);
    send_game_type_info.setBounds(160, 210, 100, 27);

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

    name_result_label[0].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    name_result_label[0].setText("Player0");
    result_game_panel.add(name_result_label[0]);
    name_result_label[0].setBounds(10, 130, 50, 15);

    name_result_label[1].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    name_result_label[1].setText("Player1");
    result_game_panel.add(name_result_label[1]);
    name_result_label[1].setBounds(170, 130, 50, 15);

    name_result_label[2].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    name_result_label[2].setText("Player2");
    result_game_panel.add(name_result_label[2]);
    name_result_label[2].setBounds(340, 130, 50, 15);

    game_score_label.setText("Score");
    result_game_panel.add(game_score_label);
    game_score_label.setBounds(180, 0, 47, 15);
    result_game_panel.add(separator2);
    separator2.setBounds(0, 90, 400, 10);
    result_game_panel.add(separator3);
    separator3.setBounds(0, 190, 400, 6);

    score_label[0].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    score_label[0].setText("1000");
    result_game_panel.add(score_label[0]);
    score_label[0].setBounds(10, 160, 40, 15);

    score_label[1].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    score_label[1].setText("1000");
    result_game_panel.add(score_label[1]);
    score_label[1].setBounds(170, 160, 50, 15);

    score_label[2].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    score_label[2].setText("1000");
    result_game_panel.add(score_label[2]);
    score_label[2].setBounds(340, 160, 50, 15);

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

    name_label[0].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    name_label[0].setText("Player0");
    board_panel.add(name_label[0]);
    name_label[0].setBounds(460, 620, 100, 20);

    name_label[1].setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    name_label[1].setText("Player1");
    board_panel.add(name_label[1]);
    name_label[1].setBounds(10, 10, 90, 20);

    name_label[2].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    name_label[2].setText("Player2");
    board_panel.add(name_label[2]);
    name_label[2].setBounds(900, 10, 90, 20);

    role_label[0].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    role_label[0].setText("LISTENER");
    board_panel.add(role_label[0]);
    role_label[0].setBounds(470, 600, 90, 15);

    role_label[2].setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    role_label[2].setText("LISTENER");
    board_panel.add(role_label[2]);
    role_label[2].setBounds(907, 40, 80, 15);

    role_label[1].setText("LISTENER");
    board_panel.add(role_label[1]);
    role_label[1].setBounds(10, 40, 70, 15);

    hand[0].setFont(new java.awt.Font("DejaVu Sans", 0, 9));
    hand[0].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    hand[0].setText("SEVEN DIAMONDS");
    hand[0].setMaximumSize(new java.awt.Dimension(90, 15));
    hand[0].setMinimumSize(new java.awt.Dimension(90, 15));
    hand[0].setPreferredSize(new java.awt.Dimension(90, 15));
    hand[0].addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
        hand0MouseClicked(evt);
        }
        });
    board_panel.add(hand[0]);
    hand[0].setBounds(0, 580, 90, 15);

    hand[1].setFont(new java.awt.Font("DejaVu Sans", 0, 9));
    hand[1].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    hand[1].setText("SEVEN DIAMONDS");
    hand[1].setMaximumSize(new java.awt.Dimension(90, 15));
    hand[1].setMinimumSize(new java.awt.Dimension(90, 15));
    hand[1].setPreferredSize(new java.awt.Dimension(90, 15));
    hand[1].addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
        hand1MouseClicked(evt);
        }
        });
    board_panel.add(hand[1]);
    hand[1].setBounds(100, 580, 90, 15);

    hand[2].setFont(new java.awt.Font("DejaVu Sans", 0, 9));
    hand[2].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    hand[2].setText("SEVEN DIAMONDS");
    hand[2].setMaximumSize(new java.awt.Dimension(90, 15));
    hand[2].setMinimumSize(new java.awt.Dimension(90, 15));
    hand[2].setPreferredSize(new java.awt.Dimension(90, 15));
    hand[2].addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
        hand2MouseClicked(evt);
        }
        });
    board_panel.add(hand[2]);
    hand[2].setBounds(200, 580, 90, 15);

    hand[3].setFont(new java.awt.Font("DejaVu Sans", 0, 9));
    hand[3].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    hand[3].setText("SEVEN DIAMONDS");
    hand[3].setMaximumSize(new java.awt.Dimension(90, 15));
    hand[3].setMinimumSize(new java.awt.Dimension(90, 15));
    hand[3].setPreferredSize(new java.awt.Dimension(90, 15));
    hand[3].addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
        hand3MouseClicked(evt);
        }
        });
    board_panel.add(hand[3]);
    hand[3].setBounds(300, 580, 90, 15);

    hand[4].setFont(new java.awt.Font("DejaVu Sans", 0, 9));
    hand[4].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    hand[4].setText("SEVEN DIAMONDS");
    hand[4].setMaximumSize(new java.awt.Dimension(90, 15));
    hand[4].setMinimumSize(new java.awt.Dimension(90, 15));
    hand[4].setPreferredSize(new java.awt.Dimension(90, 15));
    hand[4].addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
        hand4MouseClicked(evt);
        }
        });
    board_panel.add(hand[4]);
    hand[4].setBounds(400, 580, 90, 15);

    hand[5].setFont(new java.awt.Font("DejaVu Sans", 0, 9));
    hand[5].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    hand[5].setText("SEVEN DIAMONDS");
    hand[5].setMaximumSize(new java.awt.Dimension(90, 15));
    hand[5].setMinimumSize(new java.awt.Dimension(90, 15));
    hand[5].setPreferredSize(new java.awt.Dimension(90, 15));
    hand[5].addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
        hand5MouseClicked(evt);
        }
        });
    board_panel.add(hand[5]);
    hand[5].setBounds(500, 580, 90, 15);

    hand[6].setFont(new java.awt.Font("DejaVu Sans", 0, 9));
    hand[6].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    hand[6].setText("SEVEN DIAMONDS");
    hand[6].setMaximumSize(new java.awt.Dimension(90, 15));
    hand[6].setMinimumSize(new java.awt.Dimension(90, 15));
    hand[6].setPreferredSize(new java.awt.Dimension(90, 15));
    hand[6].addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
        hand6MouseClicked(evt);
        }
        });
    board_panel.add(hand[6]);
    hand[6].setBounds(600, 580, 90, 15);

    hand[7].setFont(new java.awt.Font("DejaVu Sans", 0, 9));
    hand[7].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    hand[7].setText("SEVEN DIAMONDS");
    hand[7].setMaximumSize(new java.awt.Dimension(90, 15));
    hand[7].setMinimumSize(new java.awt.Dimension(90, 15));
    hand[7].setPreferredSize(new java.awt.Dimension(90, 15));
    hand[7].addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
        hand7MouseClicked(evt);
        }
        });
    board_panel.add(hand[7]);
    hand[7].setBounds(700, 580, 90, 15);

    hand[8].setFont(new java.awt.Font("DejaVu Sans", 0, 9));
    hand[8].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    hand[8].setText("SEVEN DIAMONDS");
    hand[8].setMaximumSize(new java.awt.Dimension(90, 15));
    hand[8].setMinimumSize(new java.awt.Dimension(90, 15));
    hand[8].setPreferredSize(new java.awt.Dimension(90, 15));
    hand[8].addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
        hand8MouseClicked(evt);
        }
        });
    board_panel.add(hand[8]);
    hand[8].setBounds(800, 580, 90, 15);

    hand[9].setFont(new java.awt.Font("DejaVu Sans", 0, 9));
    hand[9].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    hand[9].setText("SEVEN DIAMONDS");
    hand[9].setMaximumSize(new java.awt.Dimension(90, 15));
    hand[9].setMinimumSize(new java.awt.Dimension(90, 15));
    hand[9].setPreferredSize(new java.awt.Dimension(90, 15));
    hand[9].addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
        hand9MouseClicked(evt);
        }
        });
    board_panel.add(hand[9]);
    hand[9].setBounds(900, 580, 90, 15);

    reizen_label[0].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    reizen_label[0].setText("accepts 110");
    board_panel.add(reizen_label[0]);
    reizen_label[0].setBounds(460, 550, 110, 15);

    reizen_label[1].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    reizen_label[1].setText("accepts 110");
    board_panel.add(reizen_label[1]);
    reizen_label[1].setBounds(120, 20, 100, 15);

    reizen_label[2].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    reizen_label[2].setText("refuses 110");
    board_panel.add(reizen_label[2]);
    reizen_label[2].setBounds(780, 20, 100, 15);

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

    skat_label[0].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    skat_label[0].setText("SEVEN DIAMONDS");
    skat_label[0].addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
        skat_label0MouseClicked(evt);
        }
        });
    board_panel.add(skat_label[0]);
    skat_label[0].setBounds(375, 490, 130, 15);

    skat_label[1].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    skat_label[1].setText("SEVEN DIAMONDS");
    skat_label[1].addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
        skat_label1MouseClicked(evt);
        }
        });
    board_panel.add(skat_label[1]);
    skat_label[1].setBounds(520, 490, 140, 15);

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
    reizen_answer=true;
    synchronized(thread){
      thread.notifyAll();
    }
  }

  private void no_reizen_buttonActionPerformed(java.awt.event.ActionEvent evt) {
    reizen_answer=false;
    synchronized(thread){
      thread.notifyAll();
    }
  }

  private void hand0MouseClicked(java.awt.event.MouseEvent evt) {
    if (!play_rq && !skat_rq) return;
    synchronized(thread){
      thread.notifyAll();
    }
  }

  private void hand1MouseClicked(java.awt.event.MouseEvent evt) {
    if (!play_rq && !skat_rq) return;
    synchronized(thread){
      thread.notifyAll();
    }
  }

  private void hand2MouseClicked(java.awt.event.MouseEvent evt) {
    if (!play_rq && !skat_rq) return;
    synchronized(thread){
      thread.notifyAll();
    }
  }

  private void hand3MouseClicked(java.awt.event.MouseEvent evt) {
    if (!play_rq && !skat_rq) return;
    synchronized(thread){
      thread.notifyAll();
    }
  }

  private void hand4MouseClicked(java.awt.event.MouseEvent evt) {
    if (!play_rq && !skat_rq) return;
    synchronized(thread){
      thread.notifyAll();
    }
  }

  private void hand5MouseClicked(java.awt.event.MouseEvent evt) {
    if (!play_rq && !skat_rq) return;
    synchronized(thread){
      thread.notifyAll();
    }
  }

  private void hand6MouseClicked(java.awt.event.MouseEvent evt) {
    if (!play_rq && !skat_rq) return;
    synchronized(thread){
      thread.notifyAll();
    }
  }

  private void hand7MouseClicked(java.awt.event.MouseEvent evt) {
    if (!play_rq && !skat_rq) return;
    synchronized(thread){
      thread.notifyAll();
    }
  }

  private void hand8MouseClicked(java.awt.event.MouseEvent evt) {
    if (!play_rq && !skat_rq) return;
    synchronized(thread){
      thread.notifyAll();
    }
  }

  private void hand9MouseClicked(java.awt.event.MouseEvent evt) {
    if (!play_rq && !skat_rq) return;
    synchronized(thread){
      thread.notifyAll();
    }
  }

  private void send_game_type_infoActionPerformed(java.awt.event.ActionEvent evt) {
    gameType_answer=(null_radio_button.isSelected() ? 1 : 0)+1*(grand_radio_button.isSelected() ? 1 : 0)+2*(clubs_radio_button.isSelected() ? 1 : 0)+3*(spades_radio_button.isSelected() ? 1 : 0)+4*(hearts_radio_button.isSelected() ? 1 : 0)+5*(diamonds_radio_button.isSelected() ? 1 : 0);
    hand_modif=hand_radio_button.isSelected();
    schneider_modif=schneider_radio_button.isSelected();
    schwarz_modif=schwarz_radio_button.isSelected();
    ouvert_modif=ouvert_radio_button.isSelected();
    synchronized(thread){
      thread.notifyAll();
    }
  }

  private void hand_radio_buttonActionPerformed(java.awt.event.ActionEvent evt){
    if (hand_radio_button.isSelected()) return;
    schneider_radio_button.setSelected(false);
    schwarz_radio_button.setSelected(false);
    if (null_radio_button.isSelected()) return;
    ouvert_radio_button.setSelected(false);
  }

  private void schneider_radio_buttonActionPerformed(java.awt.event.ActionEvent evt) {
    if (null_radio_button.isSelected()){
      schneider_radio_button.setSelected(false);
      return;
    }
    hand_radio_button.doClick();
  }

  private void schwarz_radio_buttonActionPerformed(java.awt.event.ActionEvent evt) {
    if (null_radio_button.isSelected()){
      schwarz_radio_button.setSelected(false);
      return;
    }
    schneider_radio_button.doClick();
  }

  private void ouvert_radio_buttonActionPerformed(java.awt.event.ActionEvent evt) {
    if (null_radio_button.isSelected()){
      return;
    }
    schwarz_radio_button.doClick();
  }

  private void last_trick_buttonActionPerformed(java.awt.event.ActionEvent evt) {
    synchronized(thread){
      thread.notifyAll();
    }
  }

  private void skat_label0MouseClicked(java.awt.event.MouseEvent evt) {
    if (!skat_rq) return;
    synchronized(thread){
      thread.notifyAll();
    }
  }

  private void skat_label1MouseClicked(java.awt.event.MouseEvent evt) {
    if (!skat_rq) return;
    synchronized(thread){
      thread.notifyAll();
    }
  }

  private void next_game_buttonActionPerformed(java.awt.event.ActionEvent evt) {
    synchronized(thread){
      thread.notifyAll();
    }
  }


  private javax.swing.JPanel board_panel;
  private javax.swing.JPanel result_game_panel;
  private javax.swing.JPanel game_type_panel;
  private Thread thread;

  public boolean reizen_answer;
  public boolean skat_rq;
  public int skat_answer;
  public boolean play_rq;
  public int play_answer;
  public int gameType_answer;
  public boolean hand_modif;
  public boolean schneider_modif;
  public boolean schwarz_modif;
  public boolean ouvert_modif;

  public javax.swing.JLabel card_trick_label[];
  public javax.swing.JLabel hand[];
  public javax.swing.JLabel name_label[];

  public javax.swing.JLabel role_label[];

  public javax.swing.JLabel name_result_label[];

  public javax.swing.JLabel reizen_label[];

  public javax.swing.JLabel score_label[];

  public javax.swing.JLabel skat_label[];

  public javax.swing.JLabel choose_game_label;
  public javax.swing.JLabel choose_modifiers_label;
  public javax.swing.JRadioButton clubs_radio_button;
  public javax.swing.JRadioButton diamonds_radio_button;
  public javax.swing.ButtonGroup game_chooser_group;
  public javax.swing.JLabel game_label;
  public javax.swing.JLabel game_score_label;
  public javax.swing.JDialog game_type_dialog;
  public javax.swing.JRadioButton grand_radio_button;
  public javax.swing.JRadioButton hand_radio_button;
  public javax.swing.JRadioButton hearts_radio_button;
  public javax.swing.JLabel turn_label;
  public javax.swing.JLabel gametype_label;
  public javax.swing.JLabel modifiers_label;
  public javax.swing.JButton last_trick_button;
  public javax.swing.JButton next_game_button;
  public javax.swing.JButton no_reizen_button;
  public javax.swing.JRadioButton null_radio_button;
  public javax.swing.JRadioButton ouvert_radio_button;
  public javax.swing.JLabel overall_score_label;
  public javax.swing.JButton quit_button;
  public javax.swing.JDialog result_game_dialog;
  public javax.swing.JLabel result_game_label;
  public javax.swing.JRadioButton schneider_radio_button;
  public javax.swing.JRadioButton schwarz_radio_button;
  public javax.swing.JButton send_game_type_info;
  public javax.swing.JSeparator separator2;
  public javax.swing.JSeparator separator3;
  public javax.swing.JSeparator separator0;
  public javax.swing.JSeparator separator1;
  public javax.swing.JRadioButton spades_radio_button;
  public javax.swing.JButton tricks_button;
  public javax.swing.JButton yes_reizen_button;

  public static void main(String[] args){
    final BoardGUI init=new BoardGUI(null);
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
        init.setVisible(true);
        init.game_type_dialog.setVisible(true);
        }
        });
  }

  public void invisibility(){
    game_label.setVisible(false);
    turn_label.setVisible(false);
    gametype_label.setVisible(false);
    modifiers_label.setVisible(false);

    last_trick_button.setVisible(false);

    yes_reizen_button.setVisible(false);
    no_reizen_button.setVisible(false);

    for (int i=0;i<card_trick_label.length;i++){
      card_trick_label[i].setVisible(false);
    }

    for (int i=0;i<hand.length;i++){
      hand[i].setVisible(false);
    }

    name_label[1].setVisible(false);
    name_label[2].setVisible(false);

    for (int i=0;i<role_label.length;i++){
      role_label[i].setVisible(false);
    }

    for (int i=0;i<reizen_label.length;i++){
      reizen_label[i].setVisible(false);
    }

    skat_label[0].setVisible(false);
    skat_label[1].setVisible(false);
  }
}
