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

  public void MmiInit(){
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

  public void MmiAIRequest(){
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

  public void MmiNmRequest(){
  }

  public void MmiRzRequest(){
  }

  public void MmiGmTRequest(){
  }

  public void MmiGmMRequest(){
  }

  public void MmiSkRequest(){
  }

  public void MmiPlRequest(){
  }

  public void MmiExRequest(){
  }



  public  void drawNameInfo(){
    for (int i=0; i<3;i++){
      board.name_label[i].setText(view.players_name.get(i));
    }
  }

  public  void drawStatInfo(){
  }

  public  void drawGameInfo(){
  }

  public  void drawRoleInfo(){
    for (int i=0; i<3;i++){
      board.role_label[i].setText(view.role.get(i).toString());
    }
  }

  public  void drawHandInfo(){
    for (int i=0; i< view.hand.size();i++){
      board.hand[i].setText(view.hand.get(i).toString());
      board.hand[i].setVisible(true);
    }
  }

  public  void drawReizenInfo(){
    board.reizen_label[view.reizen_index].setText((view.reizen_answer? " accepts ":" refuses ")+view.reizen_value);
    board.reizen_label[view.reizen_index].setVisible(true);
  }

  public  void drawModifInfo(){
    String res="";
    res+=(view.tab_modifiers.get(0).intValue()==1 ? "hand, " : "");
    res+=(view.tab_modifiers.get(1).intValue()==1 ? "schneider, " : "");
    res+=(view.tab_modifiers.get(2).intValue()==1 ? "schwartz, " : "");
    res+=(view.tab_modifiers.get(3).intValue()==1 ? "ouvert " : "");
    board.modifiers_label.setText(res);
    board.modifiers_label.setVisible(true);
  }

  public  void drawSkatInfo(){
  }

  public  void drawGameTypeInfo(){
  }

  public  void drawTrickInfo(){
    //    for (int i=0; i<view.size_trick;i++){
    //      board.setTrickCard(i,view.current_trick[i].toString());
    //    }
  }

  public  void drawTrickListInfo(){
  }

  public  void drawTurnInfo(){
    // board.setGameTurn(view.game,view.turn);
  }

  public  void drawOuvertInfo(){
  }

  public  void drawTurnStartInfo(){
  }

  public  void drawGameStartInfo(){
  }

  public  void drawLastTrickWinnerInfo(){
  }

  public  void drawResultGameInfo(){
  }

  public  void drawScoreInfo(){
  }

  public  void drawTakerInfo(){
  }

  protected void clear(){
  }

}
