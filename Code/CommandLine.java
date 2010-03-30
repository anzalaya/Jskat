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
   // res+="Stats of "+view.players_name.get(0)+" game played "+view.stats.get(0)[0]+", game taken "+view.stats.get(0)[1]+", game taken won "+view.stats.get(0)[2]+", game not taken "+view.stats.get(0)[3]+", game not taken won "+view.stats.get(0)[4]+"\n";
   // res+="Stats of "+view.players_name.get(1)+" game played "+view.stats.get(1)[0]+", game taken "+view.stats.get(1)[1]+", game taken won "+view.stats.get(1)[2]+", game not taken "+view.stats.get(1)[3]+", game not taken won "+view.stats.get(1)[4]+"\n";
   // res+="Stats of "+view.players_name.get(2)+" game played "+view.stats.get(2)[0]+", game taken "+view.stats.get(2)[1]+", game taken won "+view.stats.get(2)[2]+", game not taken "+view.stats.get(2)[3]+", game not taken won "+view.stats.get(2)[4]+"\n";
    System.out.println(res);
  }

  public  void drawGameInfo(){
    System.out.println("Playing game "+view.game);
  }

  public  void drawRoleInfo(){
    for (int i=0; i<3; i++){
      System.out.println(view.players_name.get(i)+": "+view.role.get(i));
    }
  }

  public  void drawHandInfo(){
    String res="Hand: ";
    for (int i=0; i< view.hand.size();i++){
      res+=i+" "+view.hand.get(i).toString()+" |";
    }
    System.out.println(res);
  }

  public  void drawReizenInfo(){
    System.out.println(view.players_name.get(view.reizen_index)+"("+view.role.get(view.reizen_index)+")"+(view.reizen_answer? " accepts ":" refuses ")+view.reizen_value);
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
    String res="Skat: ";
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
    String res="Trick: ";
    for (int i=0; i<view.size_trick;i++){
      res+=i+" "+view.current_trick[i].toString()+" |";
    }
    System.out.println(res);
  }

  public  void drawTrickListInfo(){
    String res="";
    for (int i=0; i<view.trick_list.size();i++){
      res+=i+": "+view.trick_list.get(i).toString()+"\n";
    }
    System.out.println(res);
  }

  public  void drawTurnInfo(){
    String res="Playing turn "+view.turn;
    System.out.println(res);
  }

  public  void drawOuvertInfo(){
    String res="Hand ouvert "+view.players_name.get(view.index_taker)+": ";
    for (int i=0; i< view.hand.size();i++){
      res+=i+" "+view.hand_taker_ouvert.get(i).toString()+" |";
    }
    System.out.println(res);
  }

  public  void drawTurnStartInfo(){
    String res="\nNew turn";
    System.out.println(res);
    drawGameInfo();
    drawTurnInfo();
    drawRoleInfo();
    drawTakerInfo();
    drawGameTypeInfo();
    drawModifInfo();
    drawHandInfo();
    if (view.index_taker==0 && view.tab_modifiers.get(0).intValue()==0){
      drawSkatInfo();
    }
    if (view.tab_modifiers.get(3).intValue()==1){
      drawOuvertInfo();
    }

  }

  public  void drawGameStartInfo(){
    clear();
    String res="New game";
    System.out.println(res);
    drawNameInfo();
  }

  public  void drawLastTrickWinnerInfo(){
    String res=view.players_name.get(view.trick_winner)+" won the last trick";
    System.out.println(res);
  }

  public  void drawResultGameInfo(){
    String res=view.players_name.get(view.index_taker)+(view.win?" won ":" lost ")+"and gets "+view.value_game+" points";
    System.out.println(res);
  }

  public  void drawScoreInfo(){
    String res="";
    System.out.println(res);
  }

  public  void drawTakerInfo(){
    String res=view.players_name.get(view.index_taker)+"("+view.role.get(view.index_taker)+")"+" won the reizen with a bid of "+view.reizen_value;
    System.out.println(res);
  }

}
