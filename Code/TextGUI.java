/**
 *The class TextGUI describes a text based GUI
 *@author Alexandre Anzala-Yamajako
 *@version 0.1
 */

public class TextGUI extends HumanInterface{

  BoardGUI board;  
  InitGUI init;
  AiGUI ai;

  public TextGUI(){
    board=new BoardGUI(this) ;
    init=new InitGUI(this);
    ai=new AiGUI(this);
  }

  public synchronized void MmiInit(){
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
        init.setVisible(true);
        }
        });
    synchronized(Thread.currentThread()){
      try {
        Thread.currentThread().wait();
      } catch (InterruptedException e) {System.err.println("Failed wait"+e.toString());System.exit(1);}
    }
    init.setVisible(false);
    board.name_label[0].setText(init.name_field.getText());
    writer.println(init.name_field.getText());
    writer.println(init.server_name.getText());
    writer.println(init.server_port.getText());
  }

  public synchronized void MmiAIRequest(){
    ai.AI_Info.setText("You will play with "+(3-view.human_player)+" AI players");
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
        ai.setVisible(true);
        }
        });
    synchronized(Thread.currentThread()){
      try {
        Thread.currentThread().wait();
      } catch (InterruptedException e) {System.err.println("Failed wait"+e.toString());System.exit(1);}
    }
    ai.setVisible(false);
    if (ai.answer){
      writer.println(true);
      java.awt.EventQueue.invokeLater(new Runnable() {
          public void run() {
          board.setVisible(true);
          }
          });
    }else{
      writer.println(false);
    }
  }

  public synchronized void MmiNmRequest(){
  }

  public synchronized void MmiRzRequest(){
    board.yes_reizen_button.setText(reader.nextInt()+" ?");
    board.yes_reizen_button.setVisible(true);
    board.no_reizen_button.setVisible(true);

    synchronized(Thread.currentThread()){
      try {
        Thread.currentThread().wait();
      } catch (InterruptedException e) {
        System.err.println("Failed wait"+e.toString());
        System.exit(1);
      }
    }
    board.yes_reizen_button.setVisible(false);
    board.no_reizen_button.setVisible(false);
    writer.println(board.reizen_answer);
  }

  public synchronized void MmiGmTRequest(){
    board.game_type_dialog.setVisible(true);
    synchronized(Thread.currentThread()){
      try {
        Thread.currentThread().wait();
      } catch (InterruptedException e) {
        System.err.println("Failed wait"+e.toString());
        System.exit(1);
      }
    }
    writer.println(board.gameType_answer);
  }

  public synchronized void MmiGmMRequest(){
    board.game_type_dialog.setVisible(false);
  }

  public synchronized void MmiSkRequest(){
  }

  public synchronized void MmiPlRequest(){
  }

  public synchronized void MmiExRequest(){
  }



  public synchronized  void drawNameInfo(){
    System.out.println("drawNameInfo");
    for (int i=0; i<3;i++){
      board.name_label[i].setText(view.players_name.get(i));
    }
  }

  public synchronized  void drawStatInfo(){
  }

  public synchronized  void drawGameInfo(){
    System.out.println("drawGameInfo");
    board.turn_label.setText("Game "+view.game);
    board.turn_label.setVisible(true);
  }

  public synchronized  void drawRoleInfo(){
    System.out.println("drawRoleInfo");
    for (int i=0; i<3;i++){
      board.role_label[i].setText(view.role.get(i).toString());
      board.role_label[i].setVisible(true);
    }
  }

  public synchronized  void drawHandInfo(){
    System.out.println("drawHandInfo");
    for (int i=0; i< view.hand.size();i++){
      board.hand[i].setText(view.hand.get(i).toString());
      board.hand[i].setVisible(true);
    }
  }

  public synchronized  void drawReizenInfo(){
    System.out.println("drawReizenInfo");
    board.reizen_label[view.reizen_index].setText((view.reizen_answer? " accepts ":" refuses ")+view.reizen_value);
    board.reizen_label[view.reizen_index].setVisible(true);
    synchronized(Thread.currentThread()){
      try{
        Thread.currentThread().sleep(700);
      }catch(InterruptedException e){
        System.err.println("Faulty sleep");
        System.exit(1);
      }
    }
  }

  public synchronized  void drawModifInfo(){
    String res="";
    res+=(view.tab_modifiers.get(0).intValue()==1 ? "hand, " : "");
    res+=(view.tab_modifiers.get(1).intValue()==1 ? "schneider, " : "");
    res+=(view.tab_modifiers.get(2).intValue()==1 ? "schwartz, " : "");
    res+=(view.tab_modifiers.get(3).intValue()==1 ? "ouvert " : "");
    board.modifiers_label.setText(res);
    board.modifiers_label.setVisible(true);
  }

  public synchronized  void drawSkatInfo(){
    System.out.println("drawSkatInfo");
    board.skat_label[0].setText(view.skat.get(0).toString());
    board.skat_label[1].setText(view.skat.get(1).toString());
    board.skat_label[0].setVisible(true);
    board.skat_label[1].setVisible(true);
  }

  public synchronized  void drawGameTypeInfo(){
    board.reizen_label[0].setVisible(false);
    board.reizen_label[1].setVisible(false);
    board.reizen_label[2].setVisible(false);
    System.out.println("drawGameTypeInfo");
    board.gametype_label.setText(view.players_name.get(view.index_taker)+" chose "+view.game_type.toString());
    board.gametype_label.setVisible(true);
  }

  public synchronized  void drawTrickInfo(){
    System.out.println("drawTrickInfo");
    board.last_trick_button.setVisible(false);
    for (int i=0; i<view.size_trick;i++){
      board.card_trick_label[i].setText(view.current_trick[i].toString());
      board.card_trick_label[i].setVisible(true);
    }
    synchronized(Thread.currentThread()){
      try{
        Thread.currentThread().sleep(700);
      }catch(InterruptedException e){
        System.err.println("Faulty sleep");
        System.exit(1);
      }
    }
  }

  public synchronized  void drawTrickListInfo(){
  }

  public synchronized  void drawTurnInfo(){
    System.out.println("drawTurnInfo");
    board.turn_label.setText("Turn "+view.turn);
    board.turn_label.setVisible(true);
  }

  public synchronized  void drawOuvertInfo(){
  }

  public synchronized  void drawTurnStartInfo(){
    System.out.println("drawTurnStartInfo");
    if (view.turn>0) board.last_trick_button.setVisible(true);
    synchronized(Thread.currentThread()){
      try{
        Thread.currentThread().sleep(700);
      }catch(InterruptedException e){
        System.err.println("Faulty sleep");
        System.exit(1);
      }
    }
  }

  public synchronized  void drawGameStartInfo(){
    System.out.println("drawGameStartInfo");
    board.invisibility();
    board.name_label[1].setVisible(true);
    board.name_label[2].setVisible(true);
    synchronized(Thread.currentThread()){
      try{
        Thread.currentThread().sleep(700);
      }catch(InterruptedException e){
        System.err.println("Faulty sleep");
        System.exit(1);
      }
    }
  }

  public synchronized  void drawLastTrickWinnerInfo(){
  }

  public synchronized  void drawResultGameInfo(){
  }

  public synchronized  void drawScoreInfo(){
  }

  public synchronized  void drawTakerInfo(){
  }

  protected void clear(){
  }

}
