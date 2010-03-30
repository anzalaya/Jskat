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



    public View(String s){
      players_name=new ArrayList<String>(3);
      players_name.add(0,s);
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
      role.add(0,Player.Role.valueOf(res));
      role.set(1,Player.Role.idToRole((role.get(0).getRoleId()+1)%3));
      role.set(2,Player.Role.idToRole((role.get(1).getRoleId()+1)%3));
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

  public Client(String sp, int p,String name,boolean graphic){
    super();
    server_name=sp;
    server_port=p;
    in=new PipedInputStream();
    reader=(graphic ? new Scanner(in) : new Scanner(System.in));
    try{
      c=new Connection(new Socket(server_name,server_port));
    } catch (IOException e) {
      System.err.println("connection to "+server_name+" impossible");
      e.printStackTrace();
      System.exit(1);
    }
    view=new View(name);
  }

  /**
   * Access to <code>c</code>
   * @return c
   */
  public Connection getConnection(){
    return c;
  }

  /**
   * Access to <code>c</code>
   * @return c
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
    System.out.println("AI?");
    boolean reply=reader.nextBoolean();
    c.out.println(0);
    c.out.println(reply);
  }

  public synchronized void processNmRequest(){
    String reply=view.players_name.get(0);
    c.out.println(1);
    c.out.println(reply);
  }


  public synchronized void processRzRequest(){
    System.out.println("Reizen? "+c.in.nextInt());
    boolean reply=reader.nextBoolean();
    c.out.println(2);
    c.out.println(reply);
  }

  public synchronized void processGmTRequest(){
    System.out.println("Game Type ?");
    int reply=reader.nextInt();
    c.out.println(3);
    c.out.println(reply);
  }

  public synchronized void processGmMRequest(){
    System.out.println("Game Modifier?");
    boolean reply=reader.nextBoolean();
    c.out.println(4);
    c.out.println(reply);
  }

  public synchronized void processSkRequest(){
    System.out.println("Skat?");
    int reply=reader.nextInt();
    c.out.println(5);
    c.out.println(reply);
  }

  public synchronized void processPlRequest(){
    System.out.println("Play?");
    int reply=reader.nextInt();
    c.out.println(6);
    c.out.println(reply);
  }

  public synchronized void processExRequest(){
    System.out.println("Exit");
    try{
      c.close();
    }catch (IOException e){
      e.printStackTrace();
    }
    System.exit(1);
  }

  public void run(){
    int chooser;
    CommandLine ihm=new CommandLine(getView(),this);
    while(true){
      chooser=c.in.nextInt();
      switch(chooser){
        case 0:processAIRequest(); break;
        case 1:processNmRequest(); break;
        case 2:processRzRequest(); break;
        case 3:processGmTRequest(); break;
        case 4:processGmMRequest(); break;
        case 5:processSkRequest(); break;
        case 6:processPlRequest(); break;
        case 7:processExRequest(); break;
        case 8:view.processNameInfo(); break;
        case 9:view.processStatInfo(); break;
        case 10:view.processGameInfo(); break;
        case 11:view.processRoleInfo(); break;
        case 12:view.processHandInfo(); break;
        case 13:view.processReizenInfo(); break;
        case 14:view.processModifInfo(); break;
        case 15:view.processSkatInfo(); break;
        case 16:view.processGameTypeInfo(); break;
        case 17:view.processTrickInfo(); break;
        case 18:view.processTrickListInfo(); break;
        case 19:view.processTurnInfo(); break;
        case 20:view.processOuvertInfo(); break;
        case 21:view.turnStart(); break;
        case 22:view.gameStart(); break;
        case 23:view.processTrickWinnerInfo(); break;
        case 24:view.processResultGameInfo(); break;
        case 25:view.processScoreInfo(); break;
        case 26:view.processTakerInfo(); break;
        default: System.err.println("Bad numbering"); System.exit(1);
      }
      if (chooser>7){
        view.sendNotification(chooser);
      }
    }
  }

  public static void main(String[] args){
    (new Client(args[0],Integer.parseInt(args[1]),"Test",false)).start();
  }
}
