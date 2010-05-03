/**
 *The class TextGUI describes a text based GUI
 *@author Alexandre Anzala-Yamajako
 *@version 0.1
 */

public class TextGUI extends HumanInterface{

  private BoardGUI board;  
  private int modifier_state=0;
  private int skat_state=0;
  private Thread spawn;
  private Skat localserver;

  public TextGUI(){
    board=new BoardGUI(this) ;
  }

  public void MmiInit(){
    System.out.println("MmiInit");
    board.connect_dialog.setVisible(true);
    if (board.locality){
      spawn=new Thread(
          new Runnable(){
          public void run(){
          localserver=new Skat(true);
          localserver.getServer().publishInfo();
          localserver.getServer().handleConnections();
          }
          }
          );
      spawn.start();
      try{
        Thread.sleep(500);
      }catch(InterruptedException e){
        System.err.println("Faulty sleep");
        System.exit(1);
      }
      board.server_name.setText(localserver.getServer().getName());
      board.server_port.setText(localserver.getServer().getPort());
    }
    board.name_label[0].setText(board.name_field.getText());
    writer.println(board.name_field.getText());
    writer.println(board.server_name.getText());
    writer.println(board.server_port.getText());
    if (board.locality){
      java.awt.EventQueue.invokeLater(new Runnable() {
          public void run() {
          board.invisibility();
          board.setVisible(true);
          }
          });
    }
  }

  public void MmiAIRequest(){
    System.out.println("MmiAIRequest");
    board.ai_label.setText("You will play with "+(3-view.human_player)+" AI players");
    board.connect_dialog.setVisible(true);
    if (board.ai_answer){
      writer.println(true);
      java.awt.EventQueue.invokeLater(new Runnable() {
          public void run() {
          board.invisibility();
          board.setVisible(true);
          }
          });
    }else{
      writer.println(false);
    }
  }

  public void MmiNmRequest(){
    System.out.println("MmiNmRequest");
  }

  public void MmiRzRequest(){
    System.out.println("MmiRzRequest");
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

  public void MmiGmTRequest(){
    System.out.println("MmiGmTRequest");
    board.game_type_dialog.setVisible(true);
    System.out.println(board.gameType_answer);
    writer.println(board.gameType_answer);
  }

  public void MmiGmMRequest(){
    System.out.println("MmiGmMRequest");
    if (modifier_state==0){
      writer.println(board.hand_modif);
    }
    if (modifier_state==1){
      writer.println(board.schneider_modif);
    }
    if (modifier_state==2){
      writer.println(board.schwarz_modif);
    }
    if (modifier_state==3){
      writer.println(board.ouvert_modif);
    }
    modifier_state++;
  }

  public void MmiSkRequest(){
    System.out.println("MmiSkRequest");
    board.skat_rq=true;
    synchronized(Thread.currentThread()){
      try {
        Thread.currentThread().wait();
      } catch (InterruptedException e) {
        System.err.println("Failed wait"+e.toString());
        System.exit(1);
      }
    }
    writer.println(board.skat_answer);
    board.skat_rq=false;
    System.out.println("skat_state="+skat_state);
    if(skat_state==1){
      board.skat_label[0].setVisible(false);
      board.skat_label[1].setVisible(false);
    }else{
      skat_state++;
    }
  }

  public void MmiPlRequest(){
    System.out.println("MmiPlRequest");
    board.play_rq=true;
    synchronized(Thread.currentThread()){
      try {
        Thread.currentThread().wait();
      } catch (InterruptedException e) {
        System.err.println("Failed wait"+e.toString());
        System.exit(1);
      }
    }
    writer.println(board.play_answer);
    board.play_rq=false;
  }

  public void MmiExRequest(){
    System.out.println("MmiExRequest");
    synchronized(Thread.currentThread()){
      try {
        Thread.currentThread().wait();
      } catch (InterruptedException e) {
        System.err.println("Failed wait"+e.toString());
        System.exit(1);
      }
    }
    writer.println(board.quit_answer);
  }



  public  void drawNameInfo(){
    System.out.println("drawNameInfo");
    for (int i=0; i<3;i++){
      board.name_label[i].setText(view.players_name.get(i));
      board.name_result_label[i].setText(view.players_name.get(i));
    }
  }

  public  void drawStatInfo(){
    System.out.println("drawStatInfo");
  }

  public  void drawGameInfo(){
    System.out.println("drawGameInfo");
    board.game_label.setText("Game "+(view.game+1));
    board.game_label.setVisible(true);
  }

  public  void drawRoleInfo(){
    System.out.println("drawRoleInfo");
    for (int i=0; i<3;i++){
      board.role_label[i].setText(view.role.get(i).toString());
      board.role_label[i].setVisible(true);
    }
  }

  public  void drawHandInfo(){
    System.out.println("drawHandInfo");
    for (int i=0; i<10; i++){
      board.hand[i].setText("...");
      board.hand[i].setVisible(true);
      if (i>=view.hand.size()) continue;
      board.hand[i].setText(view.hand.get(i).toString());
    }
    if (view.hand.size()>10){
      System.out.println(view.hand.get(10).toString());
      System.out.println(view.hand.get(11).toString());
    }
  }

  public  void drawReizenInfo(){
    System.out.println("drawReizenInfo");
    board.reizen_label[view.reizen_index].setText((view.reizen_answer? " accepts ":" refuses ")+view.reizen_value);
    board.reizen_label[view.reizen_index].setVisible(true);
    synchronized(Thread.currentThread()){
      try{
        Thread.currentThread().sleep(1000);
      }catch(InterruptedException e){
        System.err.println("Faulty sleep");
        System.exit(1);
      }
    }
  }

  public  void drawModifInfo(){
    System.out.println("drawModifInfo");
    String res="";
    res+=(view.tab_modifiers.get(0).intValue()==1 ? "hand, " : "");
    res+=(view.tab_modifiers.get(1).intValue()==1 ? "schneider, " : "");
    res+=(view.tab_modifiers.get(2).intValue()==1 ? "schwartz, " : "");
    res+=(view.tab_modifiers.get(3).intValue()==1 ? "ouvert " : "");
    board.modifiers_label.setText(res);
    board.modifiers_label.setVisible(true);
  }

  public  void drawSkatInfo(){
    System.out.println("drawSkatInfo");
    if (skat_state==1) return;
    board.skat_label[0].setText(view.skat.get(0).toString());
    board.skat_label[1].setText(view.skat.get(1).toString());
    board.skat_label[0].setVisible(true);
    board.skat_label[1].setVisible(true);
  }

  public  void drawGameTypeInfo(){
    System.out.println("drawGameTypeInfo");
    board.reizen_label[0].setVisible(false);
    board.reizen_label[1].setVisible(false);
    board.reizen_label[2].setVisible(false);
    board.gametype_label.setText(view.players_name.get(view.index_taker)+" bid "+view.reizen_value+" on "+view.game_type.toString());
    board.gametype_label.setVisible(true);
  }

  public  void drawTrickInfo(){
    System.out.println("drawTrickInfo");
    int base=view.trick_winner;
    System.out.println("drawTrickInfo trickwinner="+view.trick_winner);
    for (int i=0; i<3;i++){
      board.card_trick_label[(i+base)%3].setText("");
      board.card_trick_label[(i+base)%3].setVisible(true);
      if (i>=view.size_trick) continue;
      board.card_trick_label[(i+view.trick_winner)%3].setText(view.current_trick[i].toString());
    }
    synchronized(Thread.currentThread()){
      try{
        Thread.currentThread().sleep(1000);
      }catch(InterruptedException e){
        System.err.println("Faulty sleep");
        System.exit(1);
      }
    }
    if (view.size_trick==3){
      board.card_trick_label[0].setVisible(false);
      board.card_trick_label[1].setVisible(false);
      board.card_trick_label[2].setVisible(false);
    }
  }

  public  void drawTrickListInfo(){
    System.out.println("drawTrickListInfo "+view.turn);
    for (int i=0; i<view.trick_list.size();i++){
      board.trick_label[i].setText((i+1)+": "+view.trick_list.get(i).getCards()[0]+", "+view.trick_list.get(i).getCards()[1] +", "+view.trick_list.get(i).getCards()[2]);
    }
  }

  public  void drawTurnInfo(){
    System.out.println("drawTurnInfo");
    board.turn_label.setText("Turn "+(view.turn));
    board.turn_label.setVisible(true);
    for (int i=0; i<10;i++){
      board.trick_label[i].setVisible(false);
    }
    if (view.turn>1){
      board.last_trick_button.setVisible(true);
      board.trick_label[view.trick_list.size()-1].setVisible(true);
    }
  }

  public  void drawOuvertInfo(){
    System.out.println("drawOuvertInfo");
  }

  public  void drawTurnStartInfo(){
    System.out.println("drawTurnStartInfo "+view.turn);
    synchronized(Thread.currentThread()){
      try{
        Thread.currentThread().sleep(1000);
      }catch(InterruptedException e){
        System.err.println("Faulty sleep");
        System.exit(1);
      }
    }
  }

  public  void drawGameStartInfo(){
    System.out.println("drawGameStartInfo");
    board.invisibility();
    board.setVisible(true);
    board.name_label[1].setVisible(true);
    board.name_label[2].setVisible(true);
    modifier_state=0;
    skat_state=0;
    synchronized(Thread.currentThread()){
      try{
        Thread.currentThread().sleep(1000);
      }catch(InterruptedException e){
        System.err.println("Faulty sleep");
        System.exit(1);
      }
    }
  }

  public  void drawLastTrickWinnerInfo(){
    System.out.println("drawLastTrickWinnerInfo");
  }

  public  void drawResultGameInfo(){
    System.out.println("drawResultGameInfo");
    for (int i=0; i<10;i++){
      board.trick_label[i].setVisible(true);
    }
    board.setVisible(false);
    board.result_game_dialog.setVisible(true);
  }

  public  void drawScoreInfo(){
    System.out.println("drawScoreInfo");
  }

  public void drawQuitInfo(){
    if (view.quit_game){
      System.exit(1);
    }else{
      board.setVisible(true);
    }
  }

  public  void drawTakerInfo(){
    System.out.println("drawTakerInfo");
  }

  protected void clear(){
  }

}
