import java.io.*;
import java.util.*;

/**
 *The class CommandLine 
 *@author Alexandre Anzala-Yamajako
 *@version 0.0
 *@see HumanInterface
 */

public class CommandLine extends HumanInterface{

  public CommandLine(Client.View v,Thread t){
    super(v,t);
  }

  protected void clear(){
    for (int i=0; i<40;i++){
      System.out.println();
    }
  }

  public  void drawNameInfo(){
    System.out.println(0+": "+view.players_name.get(0));
    System.out.println(1+": "+view.players_name.get(1));
    System.out.println(2+": "+view.players_name.get(2));
  }

  public  void drawStatInfo(){
    String res="";
    System.out.println(res);
  }

  public  void drawGameInfo(){
    System.out.println("Playing game "+view.game);
  }

  public  void drawRoleInfo(){
    for (int i=0; i<3; i++){
      System.out.println(i+": "+view.role.get(i));
    }
  }

  public  void drawHandInfo(){
    String res="";
    for (int i=0; i< view.hand.size();i++){
      res+=i+" "+view.hand.get(i).toString()+" |";
    }
    System.out.println(res);
  }

  public  void drawReizenInfo(){
    System.out.println("player "+view.reizen_index+(view.reizen_answer? " accepts ":" refuses ")+view.reizen_value);
  }

  public  void drawModifInfo(){
    String res="The game is";
    res+=(view.tab_modifiers.get(0).intValue()==1 ? " " : " not ")+"hand,";
    res+=(view.tab_modifiers.get(1).intValue()==1 ? " " : " not ")+"schneider,";
    res+=(view.tab_modifiers.get(2).intValue()==1 ? " " : " not ")+"schwartz,";
    res+=(view.tab_modifiers.get(3).intValue()==1 ? " " : " not ")+"ouvert";
    System.out.println(res);
  }

  public  void drawSkatInfo(){
    String res="";
    for (int i=0; i< view.skat.size();i++){
      res+=(i+10)+" "+view.skat.get(i).toString()+" |";
    }
    System.out.println(res);
  }

  public  void drawGameTypeInfo(){
    String res=view.game_type.toString();
    System.out.println(res);
  }

  public  void drawTrickInfo(){
    String res="";
    for (int i=0; i<view.current_trick.length;i++){
      res+=i+" "+view.current_trick[i].toString()+" |";
    }
    System.out.println(res);
  }

  public  void drawTrickListInfo(){
    String res="";
    System.out.println(res);
  }

  public  void drawTurnInfo(){
    String res="Playing turn "+view.turn;
    System.out.println(res);
  }

  public  void drawOuvertInfo(){
    String res="";
    System.out.println(res);
  }

  public  void drawTurnStartInfo(){
    drawLastTrickWinnerInfo();
    String res="new turn";
    System.out.println(res);
    drawGameInfo();
    drawTurnInfo();
    drawNameInfo();
    drawRoleInfo();
    drawTakerInfo();
    drawGameTypeInfo();
    drawModifInfo();
    drawHandInfo();
    if (view.tab_modifiers.get(0).intValue()==1)drawSkatInfo();
  }

  public  void drawGameStartInfo(){
    clear();
    String res="New game";
    System.out.println(res);
    drawNameInfo();
  }

  public  void drawLastTrickWinnerInfo(){
    String res="Player "+view.index_taker+" won the last trick";
    System.out.println(res);
  }

  public  void drawResultGameInfo(){
    String res="Player "+view.index_taker+(view.win?" won ":" lost ")+"and gets "+view.value_game+ "points";
    System.out.println(res);
  }

  public  void drawScoreInfo(){
    String res="";
    System.out.println(res);
  }

  public  void drawTakerInfo(){
    String res="Player "+view.index_taker+" won the reizen";
    System.out.println(res);
  }

}
