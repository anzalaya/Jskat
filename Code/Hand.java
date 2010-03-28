import java.io.*;
import java.util.*;

/**
 *The class Hand describes a player's hand
 *@author Alexandre Anzala-Yamajako
 *@version 0.1
 *@see Player
 */
public class Hand {
  /**
   * number of cards in a hand
   */
  private static final int hand_size=10;

  /**
   * actual cards
   */
  private List<Card> hand;

  /**
   * Current number of cards in the hand
   */
  private int current_hand_size;

  public Hand(){
    hand=new ArrayList<Card>(hand_size);
  }

  /**
   * number of cards in a hand
   */
  public int getCurrentHandSize(){
    return current_hand_size;
  }

  /**
   * Clear this hand
   */
  public void clearHand(){
    hand.clear();
    current_hand_size=0;
  }
  /**
   * Retracts the given card from the hand
   * @param c the card to be removed
   */
  public void remove(Card c){
    hand.remove(hand.indexOf(c));
    current_hand_size--;
  }

  /**
   * Retracts the given list of cards from the hand
   * @param l the list to be removed
   */
  public void remove(List<Card> l){
    hand.removeAll(l);
    current_hand_size=hand.size();
  }

  /**
   * Add a card to the hand
   */
  public void addCard(Card c){
    hand.add(c);
    current_hand_size++;
    Collections.sort(hand,Collections.reverseOrder());
  }

  public String toString(){
    String res=current_hand_size + " out of "+ hand_size+" in hand\n";
      for (int i=0;i<current_hand_size ;i++){
        res=res+" ["+i+"]"+hand.get(i).toString();
      }
    return res;
  }

  /**
   * Add a set of cards to the hand
   */
  public void addCards(Collection<Card> c){
    hand.addAll(c);
    current_hand_size+=c.size();
    Collections.sort(hand,Collections.reverseOrder());
  }

  public void sortHand(){
    Collections.sort(hand,Collections.reverseOrder());
  }

  /**
   * Access to <code>hand</code>
   * @return <code>hand</code>
   */
  public List<Card> getHand(){
    return hand;
  }
}
