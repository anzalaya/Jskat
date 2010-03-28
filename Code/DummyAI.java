import java.util.*;

/**
 *The class Dummy AI is there for testing purposes
 *@author Alexandre Anzala-Yamajako
 *@version 0.0
 *@see Player
 */

public class DummyAI extends Player{
  
  /**
   * random generator used to take decisions
   */
  Random generator;

    /**
     * Constructor of an AI player
     */
  public DummyAI(String n){
    super(n,false);
    generator=new Random();
  }

  public Card play(){
    int hsize=player_hand.getCurrentHandSize();
    return player_hand.getHand().get(generator.nextInt(hsize));
  }

  public boolean decideReizen(int value){
    boolean b=(generator.nextInt(100)<50 ? true : false );
    return b;
  }


  public Board.GameType decideGametype(){
    switch(generator.nextInt(6)){
      
      case 0: return Board.GameType.NULL;
      case 1: return Board.GameType.GRAND;
      case 2: return Board.GameType.CLUBS;
      case 3: return Board.GameType.SPADES;
      case 4: return Board.GameType.HEARTS;
      default: return Board.GameType.DIAMONDS;
    }
  }

  public List<Card> chooseSkat(){
    List<Card> temp_skat=new ArrayList<Card>(2);
    Card c1=player_hand.getHand().get(generator.nextInt(player_hand.getCurrentHandSize()));
    Card c2=player_hand.getHand().get(generator.nextInt(player_hand.getCurrentHandSize()));
    while (c1.equals(c2)){
    c2=player_hand.getHand().get(generator.nextInt(player_hand.getCurrentHandSize()));
    }
    temp_skat.add(c1);
    temp_skat.add(c2);
    return temp_skat;
  }

  public List<Integer> chooseModifiers(){
    List<Integer> l=Arrays.asList((generator.nextBoolean() ? 1 : 0),(generator.nextBoolean() ? 1 : 0),(generator.nextBoolean() ? 1 : 0),(generator.nextBoolean() ? 1 : 0));
    return l;
  }
}
