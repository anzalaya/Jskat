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
  private class View{
    private List<String> players_name;
    private List<int[]> stats;
    private int game;
    private List<Player.Role> role;
    private List<Card> hand;
    private int reizen_value;
    private boolean reizen_answer;
    private int reizen_index;
    private List<Integer> tab_modifiers;
    private List<Card> skat;
    private Board.GameType game_type;
    private Card[] current_trick;
    private List<Trick> trick_list;
    private int turn;
    private List<Card> hand_taker_ouvert;
    private int ouvert_index;
    private int trick_winner;
    private int index_taker;
    private boolean win;
    private int value_game;
    private int[] scores;



    public View(String s){
      players_name=new ArrayList<String>();
      role=new ArrayList<Player.Role>();
      stats=new ArrayList<int[]>();
      players_name.add(0,s);
      skat=null;
      tab_modifiers=null;
      game_type=null;
      role=null;
      game=-1;
      turn=-1;
      trick_list=null;
      current_trick=null;
      hand=null;
      trick_winner=-1;
      scores=null;
    }

    public void gameStart(){
      skat=null;
      tab_modifiers=null;
      game_type=null;
      role=null;
      game=-1;
      turn=-1;
      trick_list=null;
      current_trick=null;
      hand=null;
      trick_winner=-1;
      scores=null;
    }

    public void turnStart(){
      turn=-1;
      current_trick=null;
      hand=null;
      trick_winner=-1;
    }

    public void processNameInfo(){
      int index=c.in.nextInt();
      players_name.set(index,c.in.next());
    }

    public void processStatInfo(){
      int index=c.in.nextInt();
      try{
        stats.set(index,(int[])c.in_stream.readObject());
      }catch (IOException e){
        System.err.println("Serialization error:"+e.toString());
        System.exit(1);
      }catch (ClassNotFoundException e){
        System.err.println("Class not found error:"+e.toString());
        System.exit(1);
      }
    }

    public void processGameInfo(){
      game=c.in.nextInt();
    }

    public void processRoleInfo(){
      try{
      role.set(0,(Player.Role)c.in_stream.readObject());
      }catch (IOException e){
        System.err.println("Serialization error:"+e.toString());
        System.exit(1);
      }catch (ClassNotFoundException e){
        System.err.println("Class not found error:"+e.toString());
        System.exit(1);
      }
      role.set(1,Player.Role.idToRole((role.get(0).getRoleId()+1)%3));
      role.set(2,Player.Role.idToRole((role.get(1).getRoleId()+1)%3));
    }

    public void processHandInfo(){
      try{
      hand=(List<Card>)c.in_stream.readObject();
      }catch (IOException e){
        System.err.println("Serialization error:"+e.toString());
        System.exit(1);
      }catch (ClassNotFoundException e){
        System.err.println("Class not found error:"+e.toString());
        System.exit(1);
      }
    }

    public void processReizenInfo(){
      reizen_index=c.in.nextInt();
      reizen_value=c.in.nextInt();
      reizen_answer=c.in.nextBoolean();
    }


    public void processModifInfo(){
      try{
      tab_modifiers=(List<Integer>)c.in_stream.readObject();
      }catch (IOException e){
        System.err.println("Serialization error:"+e.toString());
        System.exit(1);
      }catch (ClassNotFoundException e){
        System.err.println("Class not found error:"+e.toString());
        System.exit(1);
      }
    }

    public void processSkatInfo(){
      try{
      skat=(List<Card>)c.in_stream.readObject();
      }catch (IOException e){
        System.err.println("Serialization error:"+e.toString());
        System.exit(1);
      }catch (ClassNotFoundException e){
        System.err.println("Class not found error:"+e.toString());
        System.exit(1);
      }
    }

    public void processGameTypeInfo(){
      try{
      game_type=(Board.GameType)c.in_stream.readObject();
      }catch (IOException e){
        System.err.println("Serialization error:"+e.toString());
        System.exit(1);
      }catch (ClassNotFoundException e){
        System.err.println("Class not found error:"+e.toString());
        System.exit(1);
      }
    }

    public void processTrickInfo(){
      try{
      current_trick=(Card[])c.in_stream.readObject();
      }catch (IOException e){
        System.err.println("Serialization error:"+e.toString());
        System.exit(1);
      }catch (ClassNotFoundException e){
        System.err.println("Class not found error:"+e.toString());
        System.exit(1);
      }
    }

    public void processTrickListInfo(){
      try{
      trick_list=(List<Trick>)c.in_stream.readObject();
      }catch (IOException e){
        System.err.println("Serialization error:"+e.toString());
        System.exit(1);
      }catch (ClassNotFoundException e){
        System.err.println("Class not found error:"+e.toString());
        System.exit(1);
      }
    }

    public void processTurnInfo(){
      turn=c.in.nextInt();
    }

    public void processOuvertInfo(){
      try{
      hand_taker_ouvert=(List<Card>)c.in_stream.readObject();
      }catch (IOException e){
        System.err.println("Serialization error:"+e.toString());
        System.exit(1);
      }catch (ClassNotFoundException e){
        System.err.println("Class not found error:"+e.toString());
        System.exit(1);
      }
    }

    public void processTrickWinnerInfo(){
      trick_winner=c.in.nextInt();
    }

    public void processResultGameInfo(){
      win=c.in.nextBoolean();
      value_game=c.in.nextInt();
    }

    public void processScoreInfo(){
      int index;
      scores=new int[3];
      index=c.in.nextInt();
      scores[index]=c.in.nextInt();
      index=c.in.nextInt();
      scores[index]=c.in.nextInt();
      index=c.in.nextInt();
      scores[index]=c.in.nextInt();
    }

    public void processTakerInfo(){
      index_taker=c.in.nextInt();
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

  public Client(String sp, int p,String name,boolean graphic){
    server_name=sp;
    server_port=p;
    in=new PipedInputStream();
    reader=(graphic ? new Scanner(in) : new Scanner(System.in));
    try{
      c=new Connection(new Socket(server_name,server_port));
    } catch (IOException e) {
      System.err.println("connection to "+server_name+" impossible");
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
      System.exit(1);
    }
    return pout;
  }


  public void processAIRequest(){
    System.out.println("AI?");
    boolean reply=reader.nextBoolean();
    c.out.println(0);
    c.out.println(reply);
  }

  public void processNmRequest(){
    System.out.println("Name?");
    String reply=reader.next();
    c.out.println(1);
    c.out.println(reply);
  }


  public void processRzRequest(){
    System.out.println("Reizen? "+c.in.nextInt());
    boolean reply=reader.nextBoolean();
    c.out.println(2);
    c.out.println(reply);
  }

  public void processGmTRequest(){
    System.out.println("Game Type ?");
    int reply=reader.nextInt();
    c.out.println(3);
    c.out.println(reply);
  }

  public void processGmMRequest(){
    System.out.println("Game Modifier?");
    boolean reply=reader.nextBoolean();
    c.out.println(4);
    c.out.println(reply);
  }

  public void processSkRequest(){
    System.out.println("Skat?");
    int reply=reader.nextInt();
    c.out.println(5);
    c.out.println(reply);
  }

  public void processPlRequest(){
    System.out.println("Play?");
    int reply=reader.nextInt();
    c.out.println(6);
    c.out.println(reply);
  }

  public void processExRequest(){
    System.out.println("Exit");
    try{
      c.close();
    }catch (IOException e){}
    System.exit(1);
  }

  public void run(){
    while(true){
        switch(c.in.nextInt()){
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
          case 21:view.gameStart(); break;
          case 22:view.turnStart(); break;
          case 23:view.processTrickWinnerInfo(); break;
          case 24:view.processResultGameInfo(); break;
          case 25:view.processScoreInfo(); break;
          case 26:view.processTakerInfo(); break;
          default: System.err.println("Bad numbering"); System.exit(1);
      }
    }
  }


  public static void main(String[] args){
    Client test=new Client(args[0],Integer.parseInt(args[1]),"Alex",false);
    test.run();
  }
}
