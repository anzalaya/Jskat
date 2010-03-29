import java.io.*;
import java.util.*;

/**
 *The class HumanInterface implements the 
 *@author Alexandre Anzala-Yamajako
 *@version 0.0
 *@see Client
 */

public abstract class HumanInterface implements Observer{

  protected Client.View view;
  protected Thread view_thread;

  public void update(Observable o, Object arg){
    Integer decide=(Integer)arg;
    switch(decide.intValue()){
      case  8:drawNameInfo();  break;
      case  9:drawStatInfo();  break;
      case 10:drawGameInfo();  break;
      case 11:drawRoleInfo();  break;
      case 12:drawHandInfo();  break;
      case 13:drawReizenInfo();  break;
      case 14:drawModifInfo();  break;
      case 15:drawSkatInfo();  break;
      case 16:drawGameTypeInfo();  break;
      case 17:drawTrickInfo();  break;
      case 18:drawTrickListInfo();  break;
      case 19:drawTurnInfo();  break;
      case 20:drawOuvertInfo();  break;
      case 21:drawTurnStartInfo();  break;
      case 22:drawGameStartInfo();  break;
      case 23:drawLastTrickWinnerInfo();  break;
      case 24:drawResultGameInfo();  break;
      case 25:drawScoreInfo();  break;
      case 26:drawTakerInfo();  break;
      default: System.err.println("Error in observer"); System.exit(1);
    }
    synchronized(view_thread){
      view_thread.notifyAll();
    }
  }

  public HumanInterface(Client.View v,Thread t){
    view=v;
    view_thread=t;
    v.addObserver(this);
  }

  protected abstract void clear();
  public abstract void drawNameInfo();
  public abstract void drawStatInfo();
  public abstract void drawGameInfo();
  public abstract void drawRoleInfo();
  public abstract void drawHandInfo();
  public abstract void drawReizenInfo();
  public abstract void drawModifInfo();
  public abstract void drawSkatInfo();
  public abstract void drawGameTypeInfo();
  public abstract void drawTrickInfo();
  public abstract void drawTrickListInfo();
  public abstract void drawTurnInfo();
  public abstract void drawOuvertInfo();
  public abstract void drawTurnStartInfo();
  public abstract void drawGameStartInfo();
  public abstract void drawLastTrickWinnerInfo();
  public abstract void drawResultGameInfo();
  public abstract void drawScoreInfo();
  public abstract void drawTakerInfo();

}                         
