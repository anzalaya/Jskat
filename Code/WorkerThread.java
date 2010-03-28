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
        case  8: sendNameInfo(); break;
        case  9: sendStatInfo(); break;
        case 10: sendGameInfo(); break;
        case 11: sendRoleInfo(); break;
        case 12: sendHandInfo(); break;
        case 13: sendReizenInfo(); break;
        case 14: sendModifInfo(); break;
        case 15: sendSkatInfo(); break;
        case 16: sendGameTypeInfo(); break;
        case 17: sendTrickInfo(); break;
        case 18: sendTrickListInfo(); break;
        case 19: sendTurnInfo(); break;
        case 20: sendOuvertInfo(); break;
        case 21: sendTurnStartInfo(); break;
        case 22: sendGameStartInfo(); break;
        case 23: sendLastTrickWinnerInfo(); break;
        case 24: sendResultGameInfo(); break;
        case 25: sendScoreInfo(); break;
        case 26: sendTakerInfo(); break;
        default: System.err.println("Error in observer"); System.exit(1);
      }
      if (decide.intValue()==8) return;
      synchronized(spawn){
        spawn.notifyAll();
      }
    }

    /**
     * Request the client for an answer to stage 1 of the protocol
     */
    public String rcvName (Connection c){
      while(true){
        if (c.in.hasNextInt()){
          if (c.in.nextInt()==1) break;
        }
        c.in.next();
        c.out.println(1);
      }
      while(true){
        if (c.in.hasNext()){
          return c.in.next();
        }
        c.in.next();
        c.out.println(1);
      }
    }

    /**
     * Request the client for an answer to stage 2 of the protocol
     */
    public boolean rcvReizen (Connection c,int reizen){
      while(true){
        if (c.in.hasNextInt()){
          if (c.in.nextInt()==2) break;
        }
        c.in.next();
        c.out.println(2);
        c.out.println(reizen);
      }
      while(true){
        if (c.in.hasNextBoolean()){
          return c.in.nextBoolean();
        }
        c.in.next();
        c.out.println(2);
        c.out.println(reizen);
      }
    }

    /**
     * Request the client for an answer to stage 3 of the protocol
     */
    public int rcvGameType (Connection c){
      while(true){
        if (c.in.hasNextInt()){
          if (c.in.nextInt()==3) break;
        }
        c.in.next();
        c.out.println(3);
      }
      while(true){
        if (c.in.hasNextInt()){
          return c.in.nextInt();
        }
        c.in.next();
        c.out.println(3);
      }
    }

    /**
     * Request the client for an answer to stage 4 of the protocol
     */
    public boolean rcvModifier (Connection c){
      while(true){
        if (c.in.hasNextInt()){
          if (c.in.nextInt()==4) break;
        }
        c.in.next();
        c.out.println(3);
      }
      while(true){
        if (c.in.hasNextBoolean()){
          return c.in.nextBoolean();
        }
        c.in.next();
        c.out.println(4);
      }
    }

    /**
     * Request the client for an answer to stage 5 of the protocol
     */
    public int rcvSkat(Connection c){
      while(true){
        if (c.in.hasNextInt()){
          if (c.in.nextInt()==5) break;
        }
        c.in.next();
        c.out.println(5);
      }
      while(true){
        if (c.in.hasNextInt()){
          return c.in.nextInt();
        }
        c.in.next();
        c.out.println(5);
      }
    }

    /**
     * Request the client for an answer to stage 6 of the protocol
     */
    public int rcvCard (Connection c){
      while(true){
        if (c.in.hasNextInt()){
          if (c.in.nextInt()==6) break;
        }
        c.in.next();
        c.out.println(6);
      }
      while(true){
        if (c.in.hasNextInt()){
          return c.in.nextInt();
        }
        c.in.next();
        c.out.println(6);
      }
    }

    public void processRzRequest(){
      //log
      Connection c=connection_array.get(board.getActionPlayerIndex());
      int possible_reizen=input_flow_array.get(board.getActionPlayerIndex()).nextInt();
      System.out.println(possible_reizen);
      boolean reply;

      c.out.println(2);
      c.out.println(possible_reizen);
      reply=rcvReizen(c,possible_reizen);
      output_flow_array.get(board.getActionPlayerIndex()).println(reply);
      player_list.get(board.getActionPlayerIndex()).setActionPlayer();
      synchronized(spawn){
        spawn.notifyAll();
      }
    }

    public void processGmTRequest(){
      Connection c=connection_array.get(board.getActionPlayerIndex());
      int reply;

      c.out.println(3);
      reply=rcvGameType(c);
      output_flow_array.get(board.getActionPlayerIndex()).println(reply);
      player_list.get(board.getActionPlayerIndex()).setActionPlayer();
      synchronized(spawn){
        spawn.notifyAll();
      }

    }

    public void processGmMRequest(){
      Connection c=connection_array.get(board.getActionPlayerIndex());
      boolean reply;
      c.out.println(4);
      reply=rcvModifier(c);
      output_flow_array.get(board.getActionPlayerIndex()).println(reply);

      c.out.println(4);
      reply=rcvModifier(c);
      output_flow_array.get(board.getActionPlayerIndex()).println(reply);

      c.out.println(4);
      reply=rcvModifier(c);
      output_flow_array.get(board.getActionPlayerIndex()).println(reply);

      c.out.println(4);
      reply=rcvModifier(c);
      output_flow_array.get(board.getActionPlayerIndex()).println(reply);

      player_list.get(board.getActionPlayerIndex()).setActionPlayer();
      synchronized(spawn){
        spawn.notifyAll();
      }
    }

    public void processSkRequest(){
      Connection c=connection_array.get(board.getActionPlayerIndex());
      int reply;

      c.out.println(5);
      reply=rcvSkat(c);
      output_flow_array.get(board.getActionPlayerIndex()).println(reply);

      c.out.println(5);
      reply=rcvSkat(c);
      output_flow_array.get(board.getActionPlayerIndex()).println(reply);

      player_list.get(board.getActionPlayerIndex()).setActionPlayer();
      synchronized(spawn){
        spawn.notifyAll();
      }
    }

    public void processPlRequest(){
      Connection c=connection_array.get(board.getActionPlayerIndex());
      int reply;

      c.out.println(6);
      reply=rcvCard(c);
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
            c.out.println(7);
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

    public void sendNameInfo(){
      for (int i=0;i<human_number;i++){
        for(int j=(i+1)%3;j!=i;j=(j+1)%3){
          if (i==j) continue;
          connection_array.get(i).out.println(8);
          connection_array.get(i).out.println(relativeIndex(i,j));
          connection_array.get(i).out.println(board.getTabPlayer().get(j).getName());
        }
      }
    }

    public void sendStatInfo(){
      for (int i=0;i<human_number;i++){
        for(int j=(i+1)%3;j!=i;j=(j+1)%3){
          connection_array.get(i).out.println(9);
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

    public void sendGameInfo(){
      for (int i=0;i<human_number;i++){
        connection_array.get(i).out.println(10);
        connection_array.get(i).out.println(board.getGame());
      }
    }

    public void sendRoleInfo(){
      for (int i=0;i<human_number;i++){
        connection_array.get(i).out.println(11);
        try{
          connection_array.get(i).out_stream.writeObject(board.getTabPlayer().get(i).getRole());
          connection_array.get(i).out_stream.reset();
        }catch (IOException e){
          System.err.println("Serialization error:"+e.toString());
          System.exit(1);
        }
      }
    }

    public void sendHandInfo(){
      for (int i=0;i<human_number;i++){
        connection_array.get(i).out.println(12);
        try{
          connection_array.get(i).out_stream.writeObject(Collections.unmodifiableList(board.getTabPlayer().get(i).getHand().getHand()));
          connection_array.get(i).out_stream.reset();
        }catch (IOException e){
          System.err.println("Serialization error:"+e.toString());
          System.exit(1);
        }
      }
    }

    public void sendReizenInfo(){
      for (int i=0;i<human_number;i++){
        connection_array.get(i).out.println(13);
        connection_array.get(i).out.println(relativeIndex(i,board.getActionPlayerIndex()));
        connection_array.get(i).out.println(board.getReizenProposal());
        connection_array.get(i).out.println(board.getAnswerProposal());
      }
    }

    public void sendModifInfo(){
      for (int i=0;i<human_number;i++){
        connection_array.get(i).out.println(14);
        connection_array.get(i).out.println(board.isHand());
        connection_array.get(i).out.println(board.isSchneider());
        connection_array.get(i).out.println(board.isSchwarz());
        connection_array.get(i).out.println(board.isOuvert());
      }
    }


    public void sendSkatInfo(){
      connection_array.get(board.getIndexTaker()).out.println(15);
      try{
        connection_array.get(board.getIndexTaker()).out_stream.writeObject(Collections.unmodifiableList(board.getSkat()));
        connection_array.get(board.getIndexTaker()).out_stream.reset();
      }catch (IOException e){
        System.err.println("Serialization error:"+e.toString());
        System.exit(1);
      }

    }

    public void sendGameTypeInfo(){
      for (int i=0;i<human_number;i++){
        connection_array.get(i).out.println(16);
        try{
          connection_array.get(i).out_stream.writeObject(board.getGameType());
          connection_array.get(i).out_stream.reset();
        }catch (IOException e){
          System.err.println("Serialization error:"+e.toString());
          System.exit(1);
        }
      }
    }

    public void sendTrickInfo(){
      for (int i=0;i<human_number;i++){
        connection_array.get(i).out.println(17);
        try{
          connection_array.get(i).out_stream.writeObject(board.getTrick().getCards());
          connection_array.get(i).out_stream.reset();
        }catch (IOException e){
          System.err.println("Serialization error:"+e.toString());
          System.exit(1);
        }
      }
    }

    public void sendTrickListInfo(){
      for (int i=0;i<human_number;i++){
        connection_array.get(i).out.println(18);
        try{
          connection_array.get(i).out_stream.writeObject(board.getTricks());
          connection_array.get(i).out_stream.reset();
        }catch (IOException e){
          System.err.println("Serialization error:"+e.toString());
          System.exit(1);
        }
      }
    }

    public void sendTurnInfo(){
      for (int i=0;i<human_number;i++){
        connection_array.get(i).out.println(19);
        connection_array.get(i).out.println(board.getTurn());
      }
    }

    public void sendOuvertInfo(){
      for (int i=0;i<human_number;i++){
        if (i==board.getIndexTaker()) continue;
        connection_array.get(i).out.println(20);
        connection_array.get(i).out.println(relativeIndex(i,board.getIndexTaker()));
        try{
          connection_array.get(i).out_stream.writeObject(Collections.unmodifiableList(board.getTabPlayer().get(board.getIndexTaker()).getHand().getHand()));
          connection_array.get(i).out_stream.reset();
        }catch (IOException e){
          System.err.println("Serialization error:"+e.toString());
          System.exit(1);
        }
      }
    }


    public void sendTurnStartInfo(){
      for (int i=0;i<human_number;i++){
        connection_array.get(i).out.println(21);
      }
    }

    public void sendGameStartInfo(){
      for (int i=0;i<human_number;i++){
        connection_array.get(i).out.println(22);
      }
    }

    public void sendLastTrickWinnerInfo(){
      for (int i=0;i<human_number;i++){
        connection_array.get(i).out.println(23);
        connection_array.get(i).out.println(relativeIndex(i,board.getLastWinner()));
      }
    }

    public void sendResultGameInfo(){
      for (int i=0;i<human_number;i++){
        connection_array.get(i).out.println(24);
        connection_array.get(i).out.println(board.isWon());
        connection_array.get(i).out.println(board.getMaxReizenValue());
      }
    }

    public void sendScoreInfo(){
      for (int i=0;i<human_number;i++){
        for(int j=i;j!=i;j=(j+1)%3){
          connection_array.get(i).out.println(25);
          connection_array.get(i).out.println(relativeIndex(i,j));
          connection_array.get(i).out.println(board.getTabPlayer().get(j).getOverallScore());
        }
      }
    }

    public void sendTakerInfo(){
      for (int i=0;i<human_number;i++){
        connection_array.get(i).out.println(26);
        connection_array.get(i).out.println(relativeIndex(i,board.getIndexTaker()));
      }
    }

    public void run(){
      String[] s=new String[human_number];

      for(int i=0;i<human_number;i++){
        PipedInputStream in;

        connection_array.get(i).out.println(1);
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
        case 1: board=new Board(player_list.get(0),new DummyAI("Computer 1"),new DummyAI("Computer 2")); break;
        case 2: board=new Board(player_list.get(0),player_list.get(1),new DummyAI("Computer 1")); break;
        case 3: board=new Board(player_list.get(0),player_list.get(1),player_list.get(2)); break;
        default: System.err.println("Wrong number of humans"); System.exit(1);
      }
      setObserver();
      spawn=new Thread(
          new Runnable(){
          public void run(){
          board.play_game();
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
      while(true){
        if (input_flow_array.get(board.getActionPlayerIndex()).hasNextInt()){
          switch_index=input_flow_array.get(board.getActionPlayerIndex()).nextInt();
        }
        switch(switch_index){
          case 2:processRzRequest(); break;
          case 3:processGmTRequest(); break;
          case 4:processGmMRequest(); break;
          case 5:processSkRequest(); break;
          case 6:processPlRequest(); break;
          default: System.err.println("Bad numbering"); System.exit(1);
        }
        checkConnection();
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

    static { 
      waiting_list=new ArrayBlockingQueue<Connection>(3);
      queueLock=new ReentrantLock();
    }

    /**
     *client connection 
     */
    private Connection client_connection;

    public ConnectThread(Connection c_s){
      super();
      client_connection=c_s;
    }

    /**
     * Request the client for an answer to stage 0 of the protocol
     */
    private boolean rcvFillRequest(Connection c){
      while(true){
        if (c.in.hasNextInt()){
          if (c.in.nextInt()==0) break;
        }
        c.in.next();
        c.out.println(0);
      }
      while(true){
        if (c.in.hasNextBoolean()){
          return c.in.nextBoolean();
        }
        c.in.next();
        c.out.println(0);
      }
    }

    /**
     * Associates group of clients to BoardThread
     */
    public void run(){
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
          c1.out.println(0);
          if (rcvFillRequest(c1)){
            if (waiting_list.size()==1){
              try{
                c2=waiting_list.take();
              }catch (InterruptedException e){
                System.err.println("take failed");
                System.exit(1);
              }
              c2.out.println(0);
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

  public static class PlayThread extends Thread{
    public void run(){
    }
  }
}
