import java.io.*;
/**
 *The class Trick describe a trick of 3 cards
 *usual cycle is <code>Trick</code>, 3x <code>addCard</code>, <code>declareWinner</code>, <code>getValue</code>
 *@author Alexandre Anzala-Yamajako
 *@version 0.5
 *@see Board
 *@see Player
 */

public class Trick implements Serializable{

public static final long serialVersionUID = 1L;

  /**
   * board on which this trick is played
   */
  transient private Board board;

  /**
   * cards in the trick
   */
  private Card[] trick_cards;

  /**
   * winner in the current state
   */
  private int current_winner;

  /**
   * Value of the trick
   */
  private int value;


  /**
   *the card played by the <code>current_winner</code>
   */
  transient private Card current_winning_card;

  public Trick(Board b){
    board=b;
    trick_cards=new Card[3];
    value=0;
    current_winner=0;
    current_winning_card=null;
  }

  /**
   * add a card to the trick, assume checking is done
   */
  public void addCard(Card c,int order){
    trick_cards[order]=c;
    value+=c.getValue();
    if (order==0){
      current_winner=0;
      current_winning_card=c;
      return;
    }
    if (board.getGameType()==Board.GameType.CLUBS||board.getGameType()==Board.GameType.SPADES||board.getGameType()==Board.GameType.HEARTS||board.getGameType()==Board.GameType.DIAMONDS){//game colors
      if (current_winning_card.getColor()!=board.getTrumpColor() && current_winning_card.getFace()!=Card.Face.J){//if cwc is not trum
        if (c.getColor()==board.getTrumpColor() || c.getFace() ==Card.Face.J ){//if the played card is trump I win
          current_winning_card=c;
          current_winner=order;
        }else{//if the played card isn't trump
          if (c.getColor()==current_winning_card.getColor()){//and they have the same color then compare
            resolve(c,current_winning_card,order,current_winner);
          }//else nothing
        }
      }else{//if it is trump then resolve marche
        resolve(c,current_winning_card,order,current_winner);
      }
    }
    if (board.getGameType()==Board.GameType.NULL){//game null
      if (c.getColor()==current_winning_card.getColor()){//if they have the same color then compare
        resolve(c,current_winning_card,order,current_winner);
      }//else nothing
    }
    if (board.getGameType()==Board.GameType.GRAND){//game grand
      if (current_winning_card.getFace()!=Card.Face.J){//if cwc is not trum
        if (c.getFace() == Card.Face.J ){//if the played card is trump I win
          current_winning_card=c;
          current_winner=order;
        }else{//if the played card isn't trump
          if (c.getColor()==current_winning_card.getColor()){//and they have the same color then compare
            resolve(c,current_winning_card,order,current_winner);
          }//else nothing
        }
      }else{//if it is trump then resolve marche
        resolve(c,current_winning_card,order,current_winner);
      }
    }
  }

  /**
   *Simplify the code of <code>addCard</code>, by deciding
   */
  private void resolve(Card c1, Card c2,int order1, int order2){
    if (c1.compareTo(c2)>0){
      current_winning_card=c1;
      current_winner=order1;
    }else{
      current_winning_card=c2;
      current_winner=order2;
    }
  }

  /**
   *Declares a winner
   *@return order of the winner
   */
  public int declareWinner(){
    return current_winner;
  }

  /**
   * Access to the value of a trick
   * @return <code>value</code>
   */
  public int getValue(){
    return value;
  }

  public String toString(){
    return "["+trick_cards[0].toString()+","+trick_cards[1].toString()+","+trick_cards[2].toString()+"] The current winner is player["+current_winner+"] and the value is "+value;
  }

/**
 * Set the board
 * @param b the board
 */
  public void setBoard(Board b){
    board=b;
  }

  public Card[] getCards(){
    return trick_cards;
  }
}
