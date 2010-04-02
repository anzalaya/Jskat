import java.io.*;
import java.util.*;

/**
 *The class HumanInterface implements the 
 *@author Alexandre Anzala-Yamajako
 *@version 0.0
 *@see Client
 */

public abstract class HumanInterface extends Thread implements Observer{

  protected Client.View view;
  protected Thread view_thread;
  private PipedInputStream in;
  /**
   * reader of user input
   */
  protected Scanner reader;
  /**
   * Writer to user mmi
   */
  protected PrintWriter writer;

  public HumanInterface(Client.View v,Thread t){
    view=v;
    view_thread=t;
    v.addObserver(this);
    in=new PipedInputStream();
    reader=new Scanner(in);
  }

  public void update(Observable o, Object arg){
    Integer decide=(Integer)arg;
    switch(decide.intValue()){
      case Protocol.NAME_INFO        :drawNameInfo();  break;
      case Protocol.STAT_INFO        :drawStatInfo();  break;
      case Protocol.GAME_INFO        :drawGameInfo();  break;
      case Protocol.ROLE_INFO        :drawRoleInfo();  break;
      case Protocol.HAND_INFO        :drawHandInfo();  break;
      case Protocol.REIZEN_INFO      :drawReizenInfo();  break;
      case Protocol.MODIF_INFO       :drawModifInfo();  break;
      case Protocol.SKAT_INFO        :drawSkatInfo();  break;
      case Protocol.GAMETYPE_INFO    :drawGameTypeInfo();  break;
      case Protocol.TRICK_INFO       :drawTrickInfo();  break;
      case Protocol.TRICKLIST_INFO   :drawTrickListInfo();  break;
      case Protocol.TURN_INFO        :drawTurnInfo();  break;
      case Protocol.OUVERT_INFO      :drawOuvertInfo();  break;
      case Protocol.STARTTURN_INFO   :drawTurnStartInfo();  break;
      case Protocol.STARTGAME_INFO   :drawGameStartInfo();  break;
      case Protocol.TRICKWINNER_INFO :drawLastTrickWinnerInfo();  break;
      case Protocol.RESULTGAME_INFO  :drawResultGameInfo();  break;
      case Protocol.SCORE_INFO       :drawScoreInfo();  break;
      case Protocol.TAKER_INFO       :drawTakerInfo();  break;
      default: System.err.println("Error in observer"); System.exit(1);
    }
    synchronized(view_thread){
      view_thread.notifyAll();
    }
  }


  /**
   * Gives the client something on which to write
   * @return the start point of the pipe
   */
  public PipedOutputStream getOutputStream(){
    PipedOutputStream pout=null;
    try{
      pout=new PipedOutputStream(in);
    } catch (IOException e){
      System.err.println("pipe failed "+e.toString());
      e.printStackTrace();
      System.exit(1);
    }
    return pout;
  }

  public void setOutputStream(PipedOutputStream o){
    writer=new PrintWriter(o,true);
  }


  public void run(){
    int client_message;
      while(true){
        client_message=reader.nextInt();
        switch(client_message){
          case Protocol.FILL_BOARD                : MmiAIRequest(); break;
          case Protocol.NAME_CLIENT               : MmiNmRequest(); break;
          case Protocol.REIZEN_REQUEST            : MmiRzRequest(); break;
          case Protocol.GAMETYPE_REQUEST          : MmiGmTRequest(); break;
          case Protocol.GAMETYPE_MODIFIERS_REQUEST: MmiGmMRequest(); break;
          case Protocol.SKAT_REQUEST              : MmiSkRequest(); break;
          case Protocol.PLAY_REQUEST              : MmiPlRequest(); break;
          case Protocol.QUIT_REQUEST               : MmiExRequest(); break;
          default:  System.err.println("Bad numbering"); System.exit(1);
        }
      }
  }

  protected abstract void MmiAIRequest();
  protected abstract void MmiNmRequest();
  protected abstract void MmiRzRequest();
  protected abstract void MmiGmTRequest();
  protected abstract void MmiGmMRequest();
  protected abstract void MmiSkRequest();
  protected abstract void MmiPlRequest();
  protected abstract void MmiExRequest();


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
  protected abstract void clear();

}                         
