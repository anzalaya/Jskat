import java.util.*;

/**
 *The class Deck describes a set of card
 *@author Alexandre Anzala-Yamajako
 *@version 1.0
 *@see Hand 
 *@see Board
 */
public class Deck{
  /**
   * Total number of cards
   */
  private final int size_deck=32;

  /**
   * Number of dealt cards
   */
  private int dealt_cards;

  /**
   * Actual card set
   */
  private List<Card> cards;

  /** 
   * Constructor creating an unshuffled deck of cards.
   */
  public Deck(){
    cards=new ArrayList<Card>(32);
    dealt_cards=0;
    cards.addAll(Card.card_set);
  }

  /** 
   * Shuffle the deck into a random order.
   */
  public void shuffle(){
    int random_index;
    Random generator=new Random();
    Card temp_card;
    for (int i=0;i<size_deck;i++){
      random_index=generator.nextInt(size_deck);
      temp_card=cards.get(random_index);
      cards.remove(random_index);
      cards.add(temp_card);
    }
  }

  /** 
   * Put all cards back in the deck.
   */
  public void reset(){
    dealt_cards=0;
    cards.clear();
    cards.addAll(Card.card_set);
  }


  /** 
   * Indirect access to the private member dealt_card.
   * @return the number of card left in the deck.
   */
  public int cardsLeft(){
    return size_deck - dealt_cards;
  }

  /**
   * Deals one card from the deck and returns it.
   * @return the card on the top of the deck
   */

  public Card dealCard(){
    Card temp_card;
    temp_card=cards.get(0);
    cards.remove(0);
    dealt_cards++;
    return temp_card;
  }

  /**
   * Deals t cards from the deck and return them.
   * @param t number of card to be dealt
   * @return the card on the top of the deck
   */

  public List<Card> dealCards(int t){
    List<Card> l=new ArrayList<Card>(t);
    for (int j=0;j<t;j++){
    l.add(cards.get(0));
    cards.remove(0);
    }
    dealt_cards+=t;
    return l;
  }

  public String toString(){
    String res="Deck of " + size_deck + " cards\n"+ dealt_cards +" cards dealt\n";
    for (Card c : cards){
      res=res+c.toString()+" ";
    }
    return res;
  }

  /**
   * Set the board for each card of the deck
   */
  public void setBoard(Board b){
    for (Card c : cards){
      c.setBoard(b);
    }
  }
}
