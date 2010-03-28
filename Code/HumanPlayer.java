import java.util.*;
import java.io.*;

/**
 *The class HumanPlayer implements a human player
 *@author Alexandre Anzala-Yamajako
 *@version 0.5
 *@see Player
 */

public class HumanPlayer extends Player{

  /**
   * User input from keyboard/network
   */
  private Scanner reader;

  /**
   * User output to screen/network
   */
  private PrintWriter writer;

  /**
   * Input stream
   */
  private PipedInputStream in;

  /**
   * Action player bool
   */
  private boolean action_player_bool;

  /**
   * Constructor of an AI player
   */
  public HumanPlayer(String n){
    super(n,true);
    reader=new Scanner(System.in);
    writer=new PrintWriter(System.out,true);
    action_player_bool=false;
  }

  /**
   * Constructor with an external input flow
   */
  public HumanPlayer(String n, PipedOutputStream dest){
    super(n,true);
    writer=new PrintWriter(dest,true);
    in=new PipedInputStream();
    reader=new Scanner(in);
    action_player_bool=false;
  }

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
  /*
   * Close the flows
   */
  public void close(){
    reader.close();
    writer.close();
  }

  public void setActionPlayer(){
    action_player_bool=true;
  }

  /**
   * Request the input flow on whether player should take the bid or not
   * @return the answer.
   */
  public boolean decideReizen(int value){
      synchronized(Thread.currentThread()){
    writer.println(2);
    writer.println(value);
      }
    while(!action_player_bool) {
      synchronized(Thread.currentThread()){
        try {
          Thread.currentThread().wait();
        }
        catch (Exception e) {
          System.err.println(e.toString());
        }
      }
    }
    action_player_bool=false;
    while (true){
      if (reader.hasNextBoolean()){
        return reader.nextBoolean();
      }
      reader.next();
      writer.println(2);
      writer.println(value);
    }
  }


  /**
   * Request the input flow for the game type
   * @return the chosen game type.
   */
  public Board.GameType decideGametype(){
    int index=0;
    writer.println(3);
    while(!action_player_bool) {
      synchronized(Thread.currentThread()){
        try {
          Thread.currentThread().wait();
        } catch (InterruptedException e) {System.err.println("Failed wait"+e.toString());System.exit(1);}
      }
    }
    action_player_bool=false;
    while (true){
      if (reader.hasNextInt()){
        index=reader.nextInt(6);
        index=(index<0 ? -index : index);
        break;
      }
      reader.next();
      writer.println(3);
    }
    switch(index){

      case 0: return Board.GameType.NULL;
      case 1: return Board.GameType.GRAND;
      case 2: return Board.GameType.CLUBS;
      case 3: return Board.GameType.SPADES;
      case 4: return Board.GameType.HEARTS;
      default: return Board.GameType.DIAMONDS;
    }
  }


  /**
   * Request the input flow for the game modifiers
   * @return the chosen modifiers.
   */
  public List<Integer> chooseModifiers(){
    List<Integer> l;
    Integer[] modifiers_array=new Integer[4];
    writer.println(4);
    while(!action_player_bool) {
      synchronized(Thread.currentThread()){
        try {
          Thread.currentThread().wait();
        } catch (InterruptedException e) {System.err.println("Failed wait"+e.toString());System.exit(1);}
      }
    }
    action_player_bool=false;
    while(true){
      if (reader.hasNextBoolean()){
        modifiers_array[0]=(reader.nextBoolean() ? new Integer(1) : new Integer(0));
        break;
      }
      reader.next();
      writer.println(4);
    }
    while(true){
      if (reader.hasNextBoolean()){
        modifiers_array[1]=(reader.nextBoolean() ? new Integer(1) : new Integer(0));
        break;
      }
      reader.next();
      writer.println(4);
    }
    while(true){
      if (reader.hasNextBoolean()){
        modifiers_array[2]=(reader.nextBoolean() ? new Integer(1) : new Integer(0));
        break;
      }
      reader.next();
      writer.println(4);
    }
    while(true){
      if (reader.hasNextBoolean()){
        modifiers_array[3]=(reader.nextBoolean() ? new Integer(1) : new Integer(0));
        break;
      }
      reader.next();
      writer.println(4);
    }
    l=Arrays.asList(modifiers_array);
    return l;
  }

  /**
   * Request the input flow for the skat
   * @return the chosen skat.
   */
  public List<Card> chooseSkat(){
    List<Card> temp_skat=new ArrayList<Card>(2);
    writer.println(5);
    while(!action_player_bool) {
      synchronized(Thread.currentThread()){
        try {
          Thread.currentThread().wait();
        } catch (InterruptedException e) {System.err.println("Failed wait"+e.toString());System.exit(1);}
      }
    }
    action_player_bool=false;
    Card c1=null;
    Card c2=null;
    while(true){
      if (reader.hasNextInt()){
        c1=player_hand.getHand().get(reader.nextInt(12));
        break;
      }
      reader.next();
      writer.println(5);
    }
    while(true){
      if (reader.hasNextInt()){
        c2=player_hand.getHand().get(reader.nextInt(12));
        break;
      }
      reader.next();
      writer.println(5);
    }
    while (c1.equals(c2)){
      while(true){
        if (reader.hasNextInt()){
          c2=player_hand.getHand().get(reader.nextInt(12));
          break;
        }
        reader.next();
        writer.println(5);
      }
    }
    temp_skat.add(c1);
    temp_skat.add(c2);
    return temp_skat;
  }

  /**
   * Request the input flow for a card to play
   * @return the chosen card.
   */
  public Card play(){
    int index;
    writer.println(6);
    while(!action_player_bool) {
      synchronized(Thread.currentThread()){
        try {
          Thread.currentThread().wait();
        } catch (InterruptedException e) {System.err.println("Failed wait"+e.toString());System.exit(1);}
      }
    }
    action_player_bool=false;
    while (true){
      if (reader.hasNextInt()){
        index=reader.nextInt(player_hand.getCurrentHandSize()) ;
        return player_hand.getHand().get(index);
      }
      reader.next();
      writer.println(6);
    }
  }
}
