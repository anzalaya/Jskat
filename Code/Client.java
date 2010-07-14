import java.io.*;
import java.util.*;
import java.net.*;

/**
 *The class Client implements the server side of the network code for Skat
 *@author Alexandre Anzala-Yamajako
 *@version 0.0
 *@see Server
 *@see WorkerThread
 */

public class Client extends Thread{

  /**
   *A nested class that describes the info a client has
   */
  public class View extends Observable{
    public int human_player;
    public List<String> players_name;
    public List<int[]> stats;
    public int game;
    public List<Player.Role> role;
    public List<Card> hand;
    public int reizen_value;
    public boolean reizen_answer;
    public int reizen_index;
    public List<Integer> tab_modifiers;
    public List<Card> skat;
    public Board.GameType game_type;
    public Card[] current_trick;
    public List<Trick> trick_list;
    public int turn;
    public List<Card> hand_taker_ouvert;
    public int ouvert_index;
    public int trick_winner;
    public int index_taker;
    public boolean win;
    public int value_game;
    public int[] scores;
    public int size_trick;
    public boolean quit_game;



    public View(){
      players_name=new ArrayList<String>(3);
      players_name.add("");
      players_name.add("");
      players_name.add("");
      role=new ArrayList<Player.Role>(3);
      role.add(0,null);
      role.add(null);
      role.add(null);
      stats=new ArrayList<int[]>(3);
      stats.add(null);
      stats.add(null);
      stats.add(null);
    }

    public synchronized void gameStart(){
      turn=1;
    }

    public synchronized void turnStart(){
      size_trick=0;
    }

    public synchronized void processNameInfo(){
      int index;
      index=c.in.nextInt();
      players_name.set(index,c.in.next());
      index=c.in.nextInt();
      players_name.set(index,c.in.next());
    }

    public synchronized void processStatInfo(){
      int index=c.in.nextInt();
      try{
        stats.set(index,(int[])c.in_stream.readObject());
      }catch (IOException e){
        System.err.println("Serialization error:"+e.toString());
        e.printStackTrace();
        System.exit(1);
      }catch (ClassNotFoundException e){
        System.err.println("Class not found error:"+e.toString());
        e.printStackTrace();
        System.exit(1);
      }
    }

    public synchronized void processGameInfo(){
      game=c.in.nextInt();
    }

    public synchronized void processRoleInfo(){
      String res =c.in.next();
      role.set(0,Player.Role.valueOf(res));
      role.set(1,Player.Role.idToRole((role.get(0).getRoleId()+1)%3));
      role.set(2,Player.Role.idToRole((role.get(1).getRoleId()+1)%3));
      System.out.println("0->"+role.get(0).toString());
      System.out.println("1->"+role.get(1).toString());
      System.out.println("2->"+role.get(2).toString());
    }

    public synchronized void processHandInfo(){
      try{
        hand=(List<Card>)c.in_stream.readObject();
      }catch (IOException e){
        System.err.println("Serialization error:"+e.toString());
        e.printStackTrace();
        System.exit(1);
      }catch (ClassNotFoundException e){
        System.err.println("Class not found error:"+e.toString());
        e.printStackTrace();
        System.exit(1);
      }
    }

    public synchronized void processReizenInfo(){
      reizen_index=c.in.nextInt();
      reizen_value=c.in.nextInt();
      reizen_answer=c.in.nextBoolean();
    }


    public synchronized void processModifInfo(){
      tab_modifiers=new ArrayList<Integer>(4);
      tab_modifiers.add(c.in.nextBoolean() ? new Integer(1) : new Integer(0));
      tab_modifiers.add(c.in.nextBoolean() ? new Integer(1) : new Integer(0));
      tab_modifiers.add(c.in.nextBoolean() ? new Integer(1) : new Integer(0));
      tab_modifiers.add(c.in.nextBoolean() ? new Integer(1) : new Integer(0));
    }

    public synchronized void processSkatInfo(){
      try{
        skat=(List<Card>)c.in_stream.readObject();
      }catch (IOException e){
        System.err.println("Serialization error:"+e.toString());
        e.printStackTrace();
        System.exit(1);
      }catch (ClassNotFoundException e){
        System.err.println("Class not found error:"+e.toString());
        e.printStackTrace();
        System.exit(1);
      }
    }

    public synchronized void processGameTypeInfo(){
      try{
        game_type=(Board.GameType)c.in_stream.readObject();
      }catch (IOException e){
        System.err.println("Serialization error:"+e.toString());
        e.printStackTrace();
        System.exit(1);
      }catch (ClassNotFoundException e){
        System.err.println("Class not found error:"+e.toString());
        e.printStackTrace();
        System.exit(1);
      }
    }

    public synchronized void processTrickInfo(){
      try{
        current_trick=(Card[])c.in_stream.readObject();
        size_trick++;
      }catch (IOException e){
        System.err.println("Serialization error:"+e.toString());
        e.printStackTrace();
        System.exit(1);
      }catch (ClassNotFoundException e){
        System.err.println("Class not found error:"+e.toString());
        e.printStackTrace();
        System.exit(1);
      }
    }

    public synchronized void processTrickListInfo(){
      try{
        trick_list=(List<Trick>)c.in_stream.readObject();
      }catch (IOException e){
        System.err.println("Serialization error:"+e.toString());
        e.printStackTrace();
        System.exit(1);
      }catch (ClassNotFoundException e){
        System.err.println("Class not found error:"+e.toString());
        e.printStackTrace();
        System.exit(1);
      }
    }

    public synchronized void processTurnInfo(){
      turn=c.in.nextInt();
    }

    public synchronized void processOuvertInfo(){
      try{
        hand_taker_ouvert=(List<Card>)c.in_stream.readObject();
      }catch (IOException e){
        System.err.println("Serialization error:"+e.toString());
        e.printStackTrace();
        System.exit(1);
      }catch (ClassNotFoundException e){
        System.err.println("Class not found error:"+e.toString());
        e.printStackTrace();
        System.exit(1);
      }
    }

    public synchronized void processTrickWinnerInfo(){
      trick_winner=c.in.nextInt();
    }

    public synchronized void processResultGameInfo(){
      win=c.in.nextBoolean();
      value_game=c.in.nextInt();
    }

    public synchronized void processScoreInfo(){
      int index;
      scores=new int[3];
      index=c.in.nextInt();
      scores[index]=c.in.nextInt();
      index=c.in.nextInt();
      scores[index]=c.in.nextInt();
      index=c.in.nextInt();
      scores[index]=c.in.nextInt();
    }

    public synchronized void processQuitInfo(){
      quit_game=c.in.nextBoolean();
    }

    public synchronized void processTakerInfo(){
      index_taker=c.in.nextInt();
    }

    public synchronized void sendNotification(int code){//bof
      setChanged();
      notifyObservers(new Integer(code));
      //      System.out.println("proutD");
      //      while(!hasChanged()) {
      //        synchronized(Thread.currentThread()){
      //          try {
      //            Thread.currentThread().wait();
      //          } catch (InterruptedException e) {System.err.println("Failed wait"+e.toString());System.exit(1);}
      //        }
      //      }
      //      System.out.println("proutE");
    }

  }

  /**
   * Connection to the server
   */
  private Connection c;

  /**
   * server name
   */
  private String server_name;

  /**
   * server name
   */
  private int server_port;

  /**
   * reader of user input
   */
  private Scanner reader;
  /**
   * Writer to user mmi
   */
  private PrintWriter writer;

  /**
   * the endpoint of the pipe
   */
  private PipedInputStream in;

  /**
   * view of a player
   */
  private View view;

  public View getView(){
    return view;
  }

  public Client(){
    super();
    in=new PipedInputStream();
    reader=new Scanner(in);
    view=new View();
  }

  /**
   * Access to <code>c</code>
   * @return c
   */
  public Connection getConnection(){
    return c;
  }

  public void setOutputStream(PipedOutputStream o){
    writer= new PrintWriter(o,true);
  }

  /**
   * Gives the graphic interface something on which to write
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


  public synchronized void processAIRequest(){
    view.human_player=c.in.nextInt();
    writer.println(Protocol.FILL_BOARD);
    boolean reply=reader.nextBoolean();
    c.out.println(Protocol.FILL_BOARD);
    c.out.println(reply);
  }

  public synchronized void processNmRequest(){
    String reply=view.players_name.get(0);
    c.out.println(Protocol.NAME_CLIENT);
    c.out.println(reply);
  }


  public synchronized void processRzRequest(){
    writer.println(Protocol.REIZEN_REQUEST);
    writer.println(c.in.nextInt());
    boolean reply=reader.nextBoolean();
    c.out.println(Protocol.REIZEN_REQUEST);
    c.out.println(reply);
  }

  public synchronized void processGmTRequest(){
    writer.println(Protocol.GAMETYPE_REQUEST);
    int reply=reader.nextInt();
    c.out.println(Protocol.GAMETYPE_REQUEST);
    c.out.println(reply);
  }

  public synchronized void processGmMRequest(){
    writer.println(Protocol.GAMETYPE_MODIFIERS_REQUEST);
    boolean reply=reader.nextBoolean();
    c.out.println(Protocol.GAMETYPE_MODIFIERS_REQUEST);
    c.out.println(reply);
  }

  public synchronized void processSkRequest(){
    writer.println(Protocol.SKAT_REQUEST);
    int reply=reader.nextInt();
    c.out.println(Protocol.SKAT_REQUEST);
    c.out.println(reply);
  }

  public synchronized void processPlRequest(){
    writer.println(Protocol.PLAY_REQUEST);
    int reply=reader.nextInt();
    c.out.println(Protocol.PLAY_REQUEST);
    c.out.println(reply);
  }

  public synchronized void processExRequest(){
    System.out.println("je passe par la une fois");
    writer.println(Protocol.QUIT_REQUEST);
    boolean reply=reader.nextBoolean();
    c.out.println(Protocol.QUIT_REQUEST);
    c.out.println(reply);
  }

  public synchronized void MmiNameInfo(){
    if (reader.hasNext()){
      view.players_name.set(0,reader.next());
    }
  }

  public synchronized void MmiNameServerInfo(){
    if (reader.hasNext()){
      server_name=reader.next();
    }
  }

  public synchronized void MmiPortServerInfo(){
    if (reader.hasNextInt()){
      server_port=reader.nextInt();
    }
  }

  public void run(){
    int chooser;
    MmiNameInfo();
    MmiNameServerInfo();
    MmiPortServerInfo();
    try{
      c=new Connection(new Socket(server_name,server_port));
    } catch (IOException e) {
      System.err.println("connection to "+server_name+" impossible");
      writer.println(Protocol.CONNECTION_ERROR);
    }
    while(true){
      chooser=c.in.nextInt();
      System.out.println("chooser="+chooser);
      switch(chooser){
        case Protocol.FILL_BOARD      : processAIRequest(); break;
        case Protocol.NAME_CLIENT     : processNmRequest(); break;
        case Protocol.REIZEN_REQUEST  : processRzRequest(); break;
        case Protocol.GAMETYPE_REQUEST: processGmTRequest(); break;
        case Protocol.GAMETYPE_MODIFIERS_REQUEST: processGmMRequest(); break;
        case Protocol.SKAT_REQUEST    : processSkRequest(); break;
        case Protocol.PLAY_REQUEST    : processPlRequest(); break;
        case Protocol.QUIT_REQUEST    : processExRequest(); break;
        case Protocol.NAME_INFO       : view.processNameInfo(); break;
        case Protocol.STAT_INFO       : view.processStatInfo(); break;
        case Protocol.GAME_INFO       : view.processGameInfo(); break;
        case Protocol.ROLE_INFO       : view.processRoleInfo(); break;
        case Protocol.HAND_INFO       : view.processHandInfo(); break;
        case Protocol.REIZEN_INFO     : view.processReizenInfo(); break;
        case Protocol.MODIF_INFO      : view.processModifInfo(); break;
        case Protocol.SKAT_INFO       : view.processSkatInfo(); break;
        case Protocol.GAMETYPE_INFO   : view.processGameTypeInfo(); break;
        case Protocol.TRICK_INFO      : view.processTrickInfo(); break;
        case Protocol.TRICKLIST_INFO  : view.processTrickListInfo(); break;
        case Protocol.TURN_INFO       : view.processTurnInfo(); break;
        case Protocol.OUVERT_INFO     : view.processOuvertInfo(); break;
        case Protocol.STARTTURN_INFO  : view.turnStart(); break;
        case Protocol.STARTGAME_INFO  : view.gameStart(); break;
        case Protocol.TRICKWINNER_INFO: view.processTrickWinnerInfo(); break;
        case Protocol.RESULTGAME_INFO : view.processResultGameInfo(); break;
        case Protocol.SCORE_INFO      : view.processScoreInfo(); break;
        case Protocol.TAKER_INFO      : view.processTakerInfo(); break;
        case Protocol.QUIT_INFO       : view.processQuitInfo(); break;

        default:  System.err.println("Bad numbering"); System.exit(1);
      }
      if (chooser>7){
        view.sendNotification(chooser);
      }
    }
  }
}
