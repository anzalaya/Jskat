import java.util.*;

/**
 *The class ReizenAI focuses on doing a clever reizen
 *@author Alexandre Anzala-Yamajako
 *@version 0.0
 *@see Player
 */

public class ReizenAI extends Player{

  /**
   * random generator used to take decisions
   */
  Random generator;

  /**
   * the max reizen it can do wo overbidding
   */
  int max_reizen;

  /**
   * Constructor of an AI player
   */
  public ReizenAI(String n){
    super(n,false);
    Random generator=new Random();
  }

  public Card play(){
    return player_hand.getHand().get(generator.nextInt(player_hand.getCurrentHandSize()));
  }

  public boolean decideReizen(int value){
    return generator.nextInt(100)<50;
  }


  public Board.GameType decideGametype(){
    return null;
  }

  public List<Card> chooseSkat(){
    return null;
  }

  /**
   *Calculate  max multiplicator for all possible game and choose the biggest
   */
  private void HandMultiplicatorCalculator(){

    boolean with_without;
    int mult_max=0;
    Board.GameType g_max=null;
    max_reizen=0;

    for (Board.GameType g : Board.GameType.values()){
      if (g==Board.GameType.NULL) continue;
      with_without=player_hand.getHand().contains(Card.orderedCard(g,31));
      mult_max++;
      for (int j=30 ;j>18;j--){ 
        if(player_hand.getHand().contains(Card.orderedCard(g,j))!=with_without) break;
        mult_max++;
      }
      mult_max++;
      if (g==Board.GameType.GRAND && mult_max>5){
        mult_max=5;
      }
      g_max=(max_reizen<mult_max*g.getColorValue() ? g : g_max);
      max_reizen=(max_reizen<mult_max*g.getColorValue() ? mult_max*g.getColorValue() : max_reizen);
    }
  }

  public  List<Integer> chooseModifiers(){
    return null;
  }
}
