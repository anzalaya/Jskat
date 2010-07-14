import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;


/**
 *The class WorkerThread encapsulate the different thread used for connection and board management.
 *@author Alexandre Anzala-Yamajako
 *@version 0.1
 */
public class WorkerThread{

  /**
   *The class BoardThread handles a board and the input/output with the clients
   *@author Alexandre Anzala-Yamajako
   *@version 0.1
   */
  public static class BoardThread extends Thread implements Observer{
    Board board;
    List<Connection> connection_array;
    List<PrintWriter> output_flow_array;
    List<Scanner> input_flow_array;
    List<HumanPlayer> player_list;
    int human_number;
    Thread spawn;

    /**
     * Constructs a object with 3 human players
     */
    public BoardThread(Connection s0,Connection s1,Connection s2){
      super();
      output_flow_array=new ArrayList<PrintWriter>(3);
      input_flow_array=new ArrayList<Scanner>(3);
      player_list=new ArrayList<HumanPlayer>(3);
      connection_array=new ArrayList<Connection>(3);

      connection_array.add(s0);
      connection_array.add(s1);
      connection_array.add(s2);

      human_number=3;
    }

    /**
     * Constructs a object with 2 human players
     */
    public BoardThread(Connection s0,Connection s1){
      super();
      output_flow_array=new ArrayList<PrintWriter>(2);
      input_flow_array=new ArrayList<Scanner>(2);
      player_list=new ArrayList<HumanPlayer>(2);
      connection_array=new ArrayList<Connection>(2);

      connection_array.add(s0);
      connection_array.add(s1);

      human_number=2;
    }

    /**
     * Constructs a object with 1 human players
     */
    public BoardThread(Connection s0){
      super();
      output_flow_array=new ArrayList<PrintWriter>(1);
      input_flow_array=new ArrayList<Scanner>(1);
      player_list=new ArrayList<HumanPlayer>(1);
      connection_array=new ArrayList<Connection>(1);

      connection_array.add(s0);
      human_number=1;
    }

    public void setObserver(){
      board.addObserver(this);
    }

    public void update(Observable o, Object arg){
      Integer decide=(Integer)arg;
      switch(decide.intValue()){
        case Protocol.NAME_INFO        : sendNameInfo(); break;
        case Protocol.STAT_INFO        : sendStatInfo(); break;
        case Protocol.GAME_INFO        : sendGameInfo(); break;
        case Protocol.ROLE_INFO        : sendRoleInfo(); break;
        case Protocol.HAND_INFO        : sendHandInfo(); break;
        case Protocol.REIZEN_INFO      : sendReizenInfo(); break;
        case Protocol.MODIF_INFO       : sendModifInfo(); break;
        case Protocol.SKAT_INFO        : sendSkatInfo(); break;
        case Protocol.GAMETYPE_INFO    : sendGameTypeInfo(); break;
        case Protocol.TRICK_INFO       : sendTrickInfo(); break;
        case Protocol.TRICKLIST_INFO   : sendTrickListInfo(); break;
        case Protocol.TURN_INFO        : sendTurnInfo(); break;
        case Protocol.OUVERT_INFO      : sendOuvertInfo(); break;
        case Protocol.STARTTURN_INFO   : sendTurnStartInfo(); break;
        case Protocol.STARTGAME_INFO   : sendGameStartInfo(); break;
        case Protocol.TRICKWINNER_INFO : sendLastTrickWinnerInfo(); break;
        case Protocol.RESULTGAME_INFO  : sendResultGameInfo(); break;
        case Protocol.SCORE_INFO       : sendScoreInfo(); break;
        case Protocol.TAKER_INFO       : sendTakerInfo(); break;
        case Protocol.QUIT_INFO        : sendQuitInfo(); break;
        default: System.err.println("Error in observer"); System.exit(1);
      }
      System.out.println("notification "+decide);
      //      synchronized(spawn){
      //        spawn.notifyAll();
      //      }
    }

    /**
     * Request the client for an answer to stage 1 of the protocol
     */
    public String rcvName (Connection c){
      while(true){
        if (c.in.hasNextInt()){
          if (c.in.nextInt()==Protocol.NAME_CLIENT) break;
        }
        c.in.next();
        c.out.println(Protocol.NAME_CLIENT);
      }
      while(true){
        if (c.in.hasNext()){
          return c.in.next();
        }
        c.in.next();
        c.out.println(Protocol.NAME_CLIENT);
      }
    }

    /**
     * Request the client for an answer to stage 2 of the protocol
     */
    public boolean rcvReizen (Connection c,int reizen){
      while(true){
        if (c.in.hasNextInt()){
          if (c.in.nextInt()==Protocol.REIZEN_REQUEST) break;
        }
        c.in.next();
        c.out.println(Protocol.REIZEN_REQUEST);
        c.out.println(reizen);
      }
      while(true){
        if (c.in.hasNextBoolean()){
          return c.in.nextBoolean();
        }
        c.in.next();
        c.out.println(Protocol.REIZEN_REQUEST);
        c.out.println(reizen);
      }
    }

    /**
     * Request the client for an answer to stage 3 of the protocol
     */
    public int rcvGameType (Connection c){
      while(true){
        if (c.in.hasNextInt()){
          if (c.in.nextInt()==Protocol.GAMETYPE_REQUEST) break;
        }
        c.in.next();
        c.out.println(Protocol.GAMETYPE_REQUEST);
      }
      while(true){
        if (c.in.hasNextInt()){
          return c.in.nextInt();
        }
        c.in.next();
        c.out.println(Protocol.GAMETYPE_REQUEST);
      }
    }

    /**
     * Request the client for an answer to stage 4 of the protocol
     */
    public boolean rcvModifier (Connection c){
      while(true){
        if (c.in.hasNextInt()){
          if (c.in.nextInt()==Protocol.GAMETYPE_MODIFIERS_REQUEST) break;
        }
        c.in.next();
        c.out.println(Protocol.GAMETYPE_MODIFIERS_REQUEST);
      }
      while(true){
        if (c.in.hasNextBoolean()){
          return c.in.nextBoolean();
        }
        c.in.next();
        c.out.println(Protocol.GAMETYPE_MODIFIERS_REQUEST);
      }
    }

    /**
     * Request the client for an answer to stage 5 of the protocol
     */
    public int rcvSkat(Connection c){
      while(true){
        if (c.in.hasNextInt()){
          if (c.in.nextInt()==Protocol.SKAT_REQUEST) break;
        }
        c.in.next();
        c.out.println(Protocol.SKAT_REQUEST);
      }
      while(true){
        if (c.in.hasNextInt()){
          return c.in.nextInt();
        }
        c.in.next();
        c.out.println(Protocol.SKAT_REQUEST);
      }
    }

    /**
     * Request the client for an answer to stage 6 of the protocol
     */
    public int rcvCard (Connection c){
      while(true){
        if (c.in.hasNextInt()){
          if (c.in.nextInt()==Protocol.PLAY_REQUEST) break;
        }
        c.in.next();
        c.out.println(Protocol.PLAY_REQUEST);
      }
      while(true){
        if (c.in.hasNextInt()){
          return c.in.nextInt();
        }
        c.in.next();
        c.out.println(Protocol.PLAY_REQUEST);
      }
    }

    /**
     * Request the client for an answer to stage 7 of the protocol
     */
    private boolean rcvQuit(Connection c){
      while(true){
        if (c.in.hasNextInt()){
          if (c.in.nextInt()==Protocol.QUIT_REQUEST) break;
        }
        c.in.next();
        c.out.println(Protocol.QUIT_REQUEST);
      }
      while(true){
        if (c.in.hasNextBoolean()){
          return c.in.nextBoolean();
        }
        c.in.next();
        c.out.println(Protocol.QUIT_REQUEST);
      }
    }

    public synchronized void processRzRequest(){
      Connection c=connection_array.get(board.getActionPlayerIndex());
      int possible_reizen=input_flow_array.get(board.getActionPlayerIndex()).nextInt();
      boolean reply;

      c.out.println(Protocol.REIZEN_REQUEST);
      c.out.println(possible_reizen);
      reply=rcvReizen(c,possible_reizen);
      output_flow_array.get(board.getActionPlayerIndex()).println(reply);
      player_list.get(board.getActionPlayerIndex()).setActionPlayer();
      synchronized(spawn){
        spawn.notifyAll();
      }
    }

    public synchronized void processGmTRequest(){
      Connection c=connection_array.get(board.getActionPlayerIndex());
      int reply;

      c.out.println(Protocol.GAMETYPE_REQUEST);
      reply=rcvGameType(c);
      output_flow_array.get(board.getActionPlayerIndex()).println(reply);
      player_list.get(board.getActionPlayerIndex()).setActionPlayer();
      synchronized(spawn){
        spawn.notifyAll();
      }

    }

    public synchronized void processGmMRequest(){
      Connection c=connection_array.get(board.getActionPlayerIndex());
      boolean reply;
      c.out.println(Protocol.GAMETYPE_MODIFIERS_REQUEST);
      reply=rcvModifier(c);
      output_flow_array.get(board.getActionPlayerIndex()).println(reply);

      c.out.println(Protocol.GAMETYPE_MODIFIERS_REQUEST);
      reply=rcvModifier(c);
      output_flow_array.get(board.getActionPlayerIndex()).println(reply);

      c.out.println(Protocol.GAMETYPE_MODIFIERS_REQUEST);
      reply=rcvModifier(c);
      output_flow_array.get(board.getActionPlayerIndex()).println(reply);

      c.out.println(Protocol.GAMETYPE_MODIFIERS_REQUEST);
      reply=rcvModifier(c);
      output_flow_array.get(board.getActionPlayerIndex()).println(reply);

      player_list.get(board.getActionPlayerIndex()).setActionPlayer();
      synchronized(spawn){
        spawn.notifyAll();
      }
    }

    public synchronized void processSkRequest(){
      Connection c=connection_array.get(board.getActionPlayerIndex());
      int reply;

      c.out.println(Protocol.SKAT_REQUEST);
      reply=rcvSkat(c);
      output_flow_array.get(board.getActionPlayerIndex()).println(reply);

      c.out.println(Protocol.SKAT_REQUEST);
      reply=rcvSkat(c);
      output_flow_array.get(board.getActionPlayerIndex()).println(reply);

      player_list.get(board.getActionPlayerIndex()).setActionPlayer();
      synchronized(spawn){
        spawn.notifyAll();
      }
    }


    public synchronized void processPlRequest(){
      Connection c=connection_array.get(board.getActionPlayerIndex());
      int reply;

      c.out.println(Protocol.PLAY_REQUEST);
      reply=rcvCard(c);
      output_flow_array.get(board.getActionPlayerIndex()).println(reply);

      player_list.get(board.getActionPlayerIndex()).setActionPlayer();
      synchronized(spawn){
        spawn.notifyAll();
      }
    }

    public synchronized void processQtRequest(){
      System.out.println("BoardThread::processQtRequest "+board.getActionPlayerIndex());
      Connection c=connection_array.get(board.getActionPlayerIndex());
      boolean reply;
      c.out.println(Protocol.QUIT_REQUEST);
      reply=rcvQuit(c);
      System.out.println("BoardThread::processQtRequest apres reponse"+board.getActionPlayerIndex());
      output_flow_array.get(board.getActionPlayerIndex()).println(reply);

      player_list.get(board.getActionPlayerIndex()).setActionPlayer();
      synchronized(spawn){
        spawn.notifyAll();
      }
    }


    int relativeIndex(int i,int j){
      int index = (j-i);
      return (index < 0 ? 3-index : index);
    }

    /**
     * closes all communication between the worker thread the client and the board
     */
    public void closeConnections(){
      for (int i=0;i<human_number;i++){
        try{
          connection_array.get(i).close();
        }catch (IOException e){
          System.err.println("closing error");
          System.exit(1);
        }
        input_flow_array.get(i).close();
        output_flow_array.get(i).close();
      }
    }

    /**
     * Verifies that we still have every connections open
     */
    public void checkConnection(){
      boolean exit=false;
      for (Connection c : connection_array){
        if(c.socket.isClosed()){
          exit=true;
        }
      }
      if (exit){
        for (Connection c : connection_array){
          if(!c.socket.isClosed()){
            c.out.println(Protocol.QUIT_REQUEST);
            try{
              c.close();
            }catch (IOException e){
              System.err.println("closing error");
              System.exit(1);
            }
          }
        }
      }
    }

    public synchronized void sendNameInfo(){
      for (int i=0;i<human_number;i++){
        connection_array.get(i).out.println(Protocol.NAME_INFO);
        for(int j=(i+1)%3;j!=i;j=(j+1)%3){
          connection_array.get(i).out.println(relativeIndex(i,j));
          connection_array.get(i).out.println(board.getTabPlayer().get(j).getName());
        }
      }
    }

    public synchronized void sendStatInfo(){
      for (int i=0;i<human_number;i++){
        connection_array.get(i).out.println(Protocol.STAT_INFO);
        connection_array.get(i).out.println(relativeIndex(i,i));
        try{
          connection_array.get(i).out_stream.writeObject(board.getTabPlayer().get(i).getStat());
          connection_array.get(i).out_stream.reset();
        }catch (IOException e){
          System.err.println("Serialization error:"+e.toString());
          System.exit(1);
        }
        for(int j=(i+1)%3;j!=i;j=(j+1)%3){
          connection_array.get(i).out.println(Protocol.STAT_INFO);
          connection_array.get(i).out.println(relativeIndex(i,j));
          try{
            connection_array.get(i).out_stream.writeObject(board.getTabPlayer().get(j).getStat());
            connection_array.get(i).out_stream.reset();
          }catch (IOException e){
            System.err.println("Serialization error:"+e.toString());
            System.exit(1);
          }
        }
      }
    }

    public synchronized void sendGameInfo(){
      for (int i=0;i<human_number;i++){
        connection_array.get(i).out.println(Protocol.GAME_INFO);
        connection_array.get(i).out.println(board.getGame());
      }
    }

    public synchronized void sendRoleInfo(){
      for (int i=0;i<human_number;i++){
        connection_array.get(i).out.println(Protocol.ROLE_INFO);
        connection_array.get(i).out.println(board.getTabPlayer().get(i).getRole().toString());
      }
    }

    public synchronized void sendHandInfo(){
      for (int i=0;i<human_number;i++){
        connection_array.get(i).out.println(Protocol.HAND_INFO);
        try{
          connection_array.get(i).out_stream.writeObject(Collections.unmodifiableList(board.getTabPlayer().get(i).getHand().getHand()));
          connection_array.get(i).out_stream.reset();
        }catch (IOException e){
          System.err.println("Serialization error:"+e.toString());
          System.exit(1);
        }
      }
    }

    public synchronized void sendReizenInfo(){
      for (int i=0;i<human_number;i++){
        connection_array.get(i).out.println(Protocol.REIZEN_INFO);
        connection_array.get(i).out.println(relativeIndex(i,board.getActionPlayerIndex()));
        connection_array.get(i).out.println(board.getReizenProposal());
        connection_array.get(i).out.println(board.getAnswerProposal());
      }
    }

    public synchronized void sendModifInfo(){
      for (int i=0;i<human_number;i++){
        connection_array.get(i).out.println(Protocol.MODIF_INFO);
        connection_array.get(i).out.println(board.isHand());
        connection_array.get(i).out.println(board.isSchneider());
        connection_array.get(i).out.println(board.isSchwarz());
        connection_array.get(i).out.println(board.isOuvert());
      }
    }


    public synchronized void sendSkatInfo(){
      if (!(board.getIndexTaker()<human_number)) return;
      connection_array.get(board.getIndexTaker()).out.println(Protocol.SKAT_INFO);
      try{
        connection_array.get(board.getIndexTaker()).out_stream.writeObject(Collections.unmodifiableList(board.getSkat()));
        connection_array.get(board.getIndexTaker()).out_stream.reset();
      }catch (IOException e){
        System.err.println("Serialization error:"+e.toString());
        System.exit(1);
      }

    }

    public synchronized void sendGameTypeInfo(){
      for (int i=0;i<human_number;i++){
        connection_array.get(i).out.println(Protocol.GAMETYPE_INFO);
        try{
          connection_array.get(i).out_stream.writeObject(board.getGameType());
          connection_array.get(i).out_stream.reset();
        }catch (IOException e){
          System.err.println("Serialization error:"+e.toString());
          System.exit(1);
        }
      }
    }

    public synchronized void sendTrickInfo(){
      for (int i=0;i<human_number;i++){
        connection_array.get(i).out.println(Protocol.TRICK_INFO);
        try{
          connection_array.get(i).out_stream.writeObject(board.getTrick().getCards());
          connection_array.get(i).out_stream.reset();
        }catch (IOException e){
          System.err.println("Serialization error:"+e.toString());
          System.exit(1);
        }
      }
    }

    public synchronized void sendTrickListInfo(){
      for (int i=0;i<human_number;i++){
        connection_array.get(i).out.println(Protocol.TRICKLIST_INFO);
        try{
          connection_array.get(i).out_stream.writeObject(board.getTricks());
          connection_array.get(i).out_stream.reset();
        }catch (IOException e){
          System.err.println("Serialization error:"+e.toString());
          System.exit(1);
        }
      }
    }

    public synchronized void sendTurnInfo(){
      for (int i=0;i<human_number;i++){
        connection_array.get(i).out.println(Protocol.TURN_INFO);
        connection_array.get(i).out.println(board.getTurn());
      }
    }

    public synchronized void sendOuvertInfo(){
      for (int i=0;i<human_number;i++){
        if (i==board.getIndexTaker()) continue;
        connection_array.get(i).out.println(Protocol.OUVERT_INFO);
        try{
          connection_array.get(i).out_stream.writeObject(Collections.unmodifiableList(board.getTabPlayer().get(board.getIndexTaker()).getHand().getHand()));
          connection_array.get(i).out_stream.reset();
        }catch (IOException e){
          System.err.println("Serialization error:"+e.toString());
          System.exit(1);
        }
      }
    }


    public synchronized void sendTurnStartInfo(){
      for (int i=0;i<human_number;i++){
        connection_array.get(i).out.println(Protocol.STARTTURN_INFO);
      }
    }

    public synchronized void sendGameStartInfo(){
      for (int i=0;i<human_number;i++){
        connection_array.get(i).out.println(Protocol.STARTGAME_INFO);
      }
    }

    public synchronized void sendLastTrickWinnerInfo(){
      for (int i=0;i<human_number;i++){
        connection_array.get(i).out.println(Protocol.TRICKWINNER_INFO);
        connection_array.get(i).out.println(relativeIndex(i,board.getLastWinner()));
      }
    }

    public synchronized void sendResultGameInfo(){
      for (int i=0;i<human_number;i++){
        connection_array.get(i).out.println(Protocol.RESULTGAME_INFO);
        connection_array.get(i).out.println(board.isWon());
        connection_array.get(i).out.println((board.isWon() ? 1 : -2)*board.getMaxReizenValue());
      }
    }

    public synchronized void sendScoreInfo(){
      for (int i=0;i<human_number;i++){
        for(int j=i;j!=i;j=(j+1)%3){
          connection_array.get(i).out.println(Protocol.SCORE_INFO);
          connection_array.get(i).out.println(relativeIndex(i,j));
          connection_array.get(i).out.println(board.getTabPlayer().get(j).getOverallScore());
        }
      }
    }

    public synchronized void sendTakerInfo(){
      for (int i=0;i<human_number;i++){
        connection_array.get(i).out.println(Protocol.TAKER_INFO);
        connection_array.get(i).out.println(relativeIndex(i,board.getIndexTaker()));
      }
    }

    public synchronized void sendQuitInfo(){
      for (int i=0;i<human_number;i++){
        connection_array.get(i).out.println(Protocol.QUIT_INFO);
        connection_array.get(i).out.println(board.getQuitGame());
      }
      if (board.getQuitGame()){ 
        spawn.interrupt();
        closeConnections();
      }
    }

    public void run(){
      String[] s=new String[human_number];

      for(int i=0;i<human_number;i++){
        PipedInputStream in;

        connection_array.get(i).out.println(Protocol.NAME_CLIENT);
        s[i]=rcvName(connection_array.get(i));

        in=new PipedInputStream();

        input_flow_array.add(i, new Scanner(in));
        try{
          player_list.add(i,new HumanPlayer(s[i], new PipedOutputStream(in) ) );
        } catch (IOException e){
          System.err.println("pipe failed "+e.toString());
          System.exit(1);
        }
        output_flow_array.add(i, new PrintWriter(player_list.get(i).getOutputStream(),true));
      }

      switch (human_number){
        case 1: board=new Board(player_list.get(0),new DummyAI("Computer1"),new DummyAI("Computer2")); break;
        case 2: board=new Board(player_list.get(0),player_list.get(1),new DummyAI("Computer1")); break;
        case 3: board=new Board(player_list.get(0),player_list.get(1),player_list.get(2)); break;
        default: System.err.println("Wrong number of humans"); System.exit(1);
      }

      setObserver();
      spawn=new Thread(
          new Runnable(){
          public void run(){
          while (!Thread.currentThread().isInterrupted()) {
          board.play_game();
          }
          }
          }
          );
      spawn.start();

      try{
        Thread.sleep(50);
      }catch(InterruptedException e){
        System.err.println("Faulty sleep");
        System.exit(1);
      }
      int switch_index=-1;
      int store_action_player;
      while(spawn.isAlive()){
        store_action_player=board.getActionPlayerIndex();
        if (!(store_action_player<human_number)) continue;
        if (input_flow_array.get(store_action_player).hasNextInt()){
          switch_index=input_flow_array.get(store_action_player).nextInt();
        }
        System.out.println("Received "+switch_index+" from action_player: "+store_action_player+" human_number: "+human_number);
        switch(switch_index){
          case Protocol.REIZEN_REQUEST            :processRzRequest(); break;
          case Protocol.GAMETYPE_REQUEST          :processGmTRequest(); break;
          case Protocol.GAMETYPE_MODIFIERS_REQUEST:processGmMRequest(); break;
          case Protocol.SKAT_REQUEST              :processSkRequest(); break;
          case Protocol.PLAY_REQUEST              :processPlRequest(); break;
          case Protocol.QUIT_REQUEST              :processQtRequest(); break;
          default: System.err.println("Bad numbering"); System.exit(1);
        }
        //checkConnection();
      }
    }
  }

  /**
   * The class WatcherThread checks to see if all the human connections are still alive, if not, they gracefully terminate
   *@author Alexandre Anzala-Yamajako
   *@version 0.1
   */
  public static class WatcherThread extends Thread{
    List<Connection> connection_array;
    BoardThread board_thread;

    public WatcherThread(List<Connection> c,BoardThread b){
      connection_array=c;
      board_thread=b;
    }

    public void run(){
      while (true) {
      }
    }

  }

  /**
   *The class ConnectThread handles the connection of clients and the number of AI players involved
   *@author Alexandre Anzala-Yamajako
   *@version 0.1
   */
  public static class ConnectThread extends Thread{
    /**
     * A static waiting list for new clients
     */
    private static BlockingQueue<Connection> waiting_list;

    /**
     * A lock for the waiting list
     */
    private static Lock queueLock;

    /**
     * locality
     */
    boolean local;

    static { 
      waiting_list=new ArrayBlockingQueue<Connection>(3);
      queueLock=new ReentrantLock();
    }

    /**
     *client connection 
     */
    private Connection client_connection;

    public ConnectThread(Connection c_s,boolean locality){
      super();
      client_connection=c_s;
      local=locality;
    }



    /**
     * Request the client for an answer to stage 0 of the protocol
     */
    private boolean rcvFillRequest(Connection c){
      while(true){
        if (c.in.hasNextInt()){
          if (c.in.nextInt()==Protocol.FILL_BOARD) break;
        }
        c.in.next();
        c.out.println(Protocol.FILL_BOARD);
      }
      while(true){
        if (c.in.hasNextBoolean()){
          return c.in.nextBoolean();
        }
        c.in.next();
        c.out.println(Protocol.FILL_BOARD);
      }
    }

    /**
     * Associates group of clients to BoardThread
     */
    public void run(){
      if (local){
        (new BoardThread(client_connection)).start();
        return;
      }

      Connection c1=null;
      Connection c2=null;
      Connection c3=null;
      queueLock.lock();
      try{
        try{
          waiting_list.put(client_connection);
        }catch (InterruptedException e){
          System.err.println("put failed");
          System.exit(1);
        }
        if (waiting_list.size()==3){
          try{
            c1=waiting_list.take();
            c2=waiting_list.take();
            c3=waiting_list.take();
          }catch (InterruptedException e){
            System.err.println("take failed");
            System.exit(1);
          }
          (new BoardThread(c1,c2,c3)).start();
        }else{
          try{
            c1=waiting_list.take();
          }catch (InterruptedException e){
            System.err.println("take failed");
            System.exit(1);
          }
          c1.out.println(Protocol.FILL_BOARD);
          c1.out.println(1);//number of human players
          if (rcvFillRequest(c1)){
            if (waiting_list.size()==1){
              try{
                c2=waiting_list.take();
              }catch (InterruptedException e){
                System.err.println("take failed");
                System.exit(1);
              }
              c2.out.println(Protocol.FILL_BOARD);
              c1.out.println(2);// number of human players
              if (rcvFillRequest(c2)){
                (new BoardThread(c1,c2)).start();
              }else{
                try{
                  waiting_list.put(c1);
                  waiting_list.put(c2);
                }catch (InterruptedException e){
                  System.err.println("put failed");
                  System.exit(1);
                }
              }
            }else{
              (new BoardThread(c1)).start();
            }
          }else{
            try{
              waiting_list.put(c1);
            }catch (InterruptedException e){
              System.err.println("put failed");
              System.exit(1);
            }
          }
        }
      }finally{
        queueLock.unlock();
      }
    }
  }

}
