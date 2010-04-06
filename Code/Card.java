import java.util.*;
import java.io.*;

/**
 *The class Card describes a playing card and the set of cards
 *@author Alexandre Anzala-Yamajako
 *@version 1.0
 *@see Deck
 *@see Hand
 *@see Board
 */
public class Card implements Comparable<Card>, Serializable  {

public static final long serialVersionUID = 1L;

  /**
   * The four colors
   */
  public static enum Color {CLUBS, SPADES, HEARTS, DIAMONDS}

  /**
   * The 8 faces
   */
  public static enum Face { 
    J(2),
      ACE(11),
      TEN(10),
      K(4),
      Q(3),
      NINE(0),
      EIGHT(0),
      SEVEN(0) ;

    /**
     * card value
     */
    private final int value;
    /*
     * @return value of the face
     */
    public int getValue(){
      return value;
    }

    Face(int v){
      value=v;
    }

  }

  /**
   * Actual card set
   */
  public static Set<Card> card_set;

  /**
   * Order of cards in a clubs game
   */
  private static List<Card> order_game_clubs  ;

  /**
   * Order of cards in a spades game
   */
  private static List<Card> order_game_spades ;

  /**
   * Order of cards in a hearts game
   */
  private static List<Card> order_game_hearts ;

  /**
   * Order of cards in a diamonds game
   */
  private static List<Card> order_game_diamonds;

  /**
   * Order of cards in a grand game
   */
  private static List<Card> order_game_grand  ;

  /**
   * Order of cards in a null game
   */
  private static List<Card> order_game_null   ;

  /**
   * Get sorted cards according to the game played
   * @see Player
   * @return <code>Card</code>
   */
  public static Card orderedCard(Board.GameType g,int index ){
    Card c=null;
    switch (g) {
      case CLUBS: c=order_game_clubs.get(index);break;
      case SPADES: c=order_game_spades.get(index);break;
      case HEARTS: c=order_game_hearts.get(index);break;
      case DIAMONDS: c=order_game_diamonds.get(index);break;
      case GRAND: c=order_game_grand.get(index);break;
    }
    return c;
  }

  /**
   * @return string description of the card set
   */
  public static String cardSettoString(){
    String res="Card set";
    for(Card c : card_set){
      res=res+"\n"+c.toString();
    }return res+"\n";
  }

  /**
   * @return string description of the card order in the clubs game
   */
  public static String clubsToString(){
    String res="Clubs order";
    for(Card c : order_game_clubs){
      res=res+"\n"+c.toString();
    }return res+"\n";
  }

  /**
   * @return string description of the card order in the diamonds game
   */
  public static String diamondsToString(){
    String res="Diamonds order";
    for(Card c : order_game_diamonds){
      res=res+"\n"+c.toString();
    }return res+"\n";
  }

  /**
   * @return string description of the card order in the hearts game
   */
  public static String heartsToString(){
    String res="Hearts order";
    for(Card c : order_game_hearts){
      res=res+"\n"+c.toString();
    }return res+"\n";
  }

  /**
   * @return string description of the card order in the spades game
   */
  public static String spadesToString(){
    String res="Spades order";
    for(Card c : order_game_spades){
      res=res+"\n"+c.toString();
    }return res+"\n";
  }
  /**
   * @return string description of the card order in the grand game
   */
  public static String grandToString(){
    String res="Grand order";
    for(Card c : order_game_grand){
      res=res+"\n"+c.toString();
    }return res+"\n";
  }
  /**
   * @return string description of the card order in the null game
   */
  public static String nullToString(){
    String res="Null order";
    for(Card c : order_game_null){
      res=res+"\n"+c.toString();
    }return res+"\n";
  }

  public static boolean sameColor(Board.GameType game_type,Card c1,Card c2){
    if (game_type==Board.GameType.NULL) return c1.getColor()==c2.getColor();

    if (c1.getFace()==Face.J && c2.getFace()==Face.J) return true;

    if (c1.getFace()!=Face.J && c2.getFace()!=Face.J) return c1.getColor()==c2.getColor();

    if (game_type==Board.GameType.GRAND) return false;

    return ( (c1.getFace()==Face.J && c2.getColor()==game_type.getColor()) || (c2.getFace()==Face.J && c1.getColor()==game_type.getColor()) );
  }

  static {
    order_game_clubs  =new ArrayList<Card>(32);
    order_game_spades =new ArrayList<Card>(32);
    order_game_hearts =new ArrayList<Card>(32);
    order_game_diamonds=new ArrayList<Card>(32);
    order_game_grand  =new ArrayList<Card>(32);
    order_game_null   =new ArrayList<Card>(32);

    card_set=new HashSet<Card>();

    for (Color cl : Color.values()){
      for (Face f : Face.values()){
        card_set.add(new Card(cl,f));
        order_game_clubs.add(null);
        order_game_spades.add(null);
        order_game_hearts.add(null);
        order_game_diamonds.add(null);
        order_game_grand.add(null);
        order_game_null.add(null);
      }
    }

    Card c;
    c=new Card(Color.CLUBS,Face.J);
    order_game_clubs.set(31,c);
    order_game_spades.set(31,c);
    order_game_hearts.set(31,c);
    order_game_diamonds.set(31,c);
    order_game_grand.set(31,c);
    order_game_null.set(28,c);

    c=new Card(Color.SPADES,Face.J);
    order_game_clubs.set(30,c);
    order_game_spades.set(30,c);
    order_game_hearts.set(30,c);
    order_game_diamonds.set(30,c);
    order_game_grand.set(30,c);
    order_game_null.set(20,c);

    c=new Card(Color.HEARTS,Face.J);
    order_game_clubs.set(29,c);
    order_game_spades.set(29,c);
    order_game_hearts.set(29,c);
    order_game_diamonds.set(29,c);
    order_game_grand.set(29,c);
    order_game_null.set(12,c);

    c=new Card(Color.DIAMONDS,Face.J);
    order_game_clubs.set(28,c);
    order_game_spades.set(28,c);
    order_game_hearts.set(28,c);
    order_game_diamonds.set(28,c);
    order_game_grand.set(28,c);
    order_game_null.set(4,c);

    c=new Card(Color.CLUBS,Face.ACE);
    order_game_clubs.set(27,c);
    order_game_spades.set(20,c);
    order_game_hearts.set(20,c);
    order_game_diamonds.set(20,c);
    order_game_grand.set(27,c);
    order_game_null.set(31,c);

    c=new Card(Color.SPADES,Face.ACE);
    order_game_clubs.set(20,c);
    order_game_spades.set(27,c);
    order_game_hearts.set(13,c);
    order_game_diamonds.set(13,c);
    order_game_grand.set(20,c);
    order_game_null.set(23,c);

    c=new Card(Color.HEARTS,Face.ACE);
    order_game_clubs.set(13,c);
    order_game_spades.set(13,c);
    order_game_hearts.set(27,c);
    order_game_diamonds.set(6,c);
    order_game_grand.set(13,c);
    order_game_null.set(15,c);

    c=new Card(Color.DIAMONDS,Face.ACE);
    order_game_clubs.set(6,c);
    order_game_spades.set(6,c);
    order_game_hearts.set(6,c);
    order_game_diamonds.set(27,c);
    order_game_grand.set(6,c);
    order_game_null.set(7,c);

    c=new Card(Color.CLUBS,Face.TEN);
    order_game_clubs.set(26,c);
    order_game_spades.set(19,c);
    order_game_hearts.set(19,c);
    order_game_diamonds.set(19,c);
    order_game_grand.set(26,c);
    order_game_null.set(27,c);

    c=new Card(Color.SPADES,Face.TEN);
    order_game_clubs.set(19,c);
    order_game_spades.set(26,c);
    order_game_hearts.set(12,c);
    order_game_diamonds.set(12,c);
    order_game_grand.set(19,c);
    order_game_null.set(19,c);

    c=new Card(Color.HEARTS,Face.TEN);
    order_game_clubs.set(12,c);
    order_game_spades.set(12,c);
    order_game_hearts.set(26,c);
    order_game_diamonds.set(5,c);
    order_game_grand.set(12,c);
    order_game_null.set(11,c);

    c=new Card(Color.DIAMONDS,Face.TEN);
    order_game_clubs.set(5,c);
    order_game_spades.set(5,c);
    order_game_hearts.set(5,c);
    order_game_diamonds.set(26,c);
    order_game_grand.set(5,c);
    order_game_null.set(3,c);

    c=new Card(Color.CLUBS,Face.K);
    order_game_clubs.set(25,c);
    order_game_spades.set(18,c);
    order_game_hearts.set(18,c);
    order_game_diamonds.set(18,c);
    order_game_grand.set(25,c);
    order_game_null.set(30,c);

    c=new Card(Color.SPADES,Face.K);
    order_game_clubs.set(18,c);
    order_game_spades.set(25,c);
    order_game_hearts.set(11,c);
    order_game_diamonds.set(11,c);
    order_game_grand.set(18,c);
    order_game_null.set(22,c);

    c=new Card(Color.HEARTS,Face.K);
    order_game_clubs.set(11,c);
    order_game_spades.set(11,c);
    order_game_hearts.set(25,c);
    order_game_diamonds.set(4,c);
    order_game_grand.set(11,c);
    order_game_null.set(14,c);

    c=new Card(Color.DIAMONDS,Face.K);
    order_game_clubs.set(4,c);
    order_game_spades.set(4,c);
    order_game_hearts.set(4,c);
    order_game_diamonds.set(25,c);
    order_game_grand.set(4,c);
    order_game_null.set(6,c);

    c=new Card(Color.CLUBS,Face.Q);
    order_game_clubs.set(24,c);
    order_game_spades.set(17,c);
    order_game_hearts.set(17,c);
    order_game_diamonds.set(17,c);
    order_game_grand.set(24,c);
    order_game_null.set(29,c);

    c=new Card(Color.SPADES,Face.Q);
    order_game_clubs.set(17,c);
    order_game_spades.set(24,c);
    order_game_hearts.set(10,c);
    order_game_diamonds.set(10,c);
    order_game_grand.set(17,c);
    order_game_null.set(21,c);

    c=new Card(Color.HEARTS,Face.Q);
    order_game_clubs.set(10,c);
    order_game_spades.set(10,c);
    order_game_hearts.set(24,c);
    order_game_diamonds.set(3,c);
    order_game_grand.set(10,c);
    order_game_null.set(13,c);

    c=new Card(Color.DIAMONDS,Face.Q);
    order_game_clubs.set(3,c);
    order_game_spades.set(3,c);
    order_game_hearts.set(3,c);
    order_game_diamonds.set(24,c);
    order_game_grand.set(3,c);
    order_game_null.set(5,c);

    c=new Card(Color.CLUBS,Face.NINE);
    order_game_clubs.set(23,c);
    order_game_spades.set(16,c);
    order_game_hearts.set(16,c);
    order_game_diamonds.set(16,c);
    order_game_grand.set(23,c);
    order_game_null.set(26,c);

    c=new Card(Color.SPADES,Face.NINE);
    order_game_clubs.set(16,c);
    order_game_spades.set(23,c);
    order_game_hearts.set(9,c);
    order_game_diamonds.set(9,c);
    order_game_grand.set(16,c);
    order_game_null.set(18,c);

    c=new Card(Color.HEARTS,Face.NINE);
    order_game_clubs.set(9,c);
    order_game_spades.set(9,c);
    order_game_hearts.set(23,c);
    order_game_diamonds.set(2,c);
    order_game_grand.set(9,c);
    order_game_null.set(10,c);

    c=new Card(Color.DIAMONDS,Face.NINE);
    order_game_clubs.set(2,c);
    order_game_spades.set(2,c);
    order_game_hearts.set(2,c);
    order_game_diamonds.set(23,c);
    order_game_grand.set(2,c);
    order_game_null.set(2,c);

    c=new Card(Color.CLUBS,Face.EIGHT);
    order_game_clubs.set(22,c);
    order_game_spades.set(15,c);
    order_game_hearts.set(15,c);
    order_game_diamonds.set(15,c);
    order_game_grand.set(22,c);
    order_game_null.set(25,c);

    c=new Card(Color.SPADES,Face.EIGHT);
    order_game_clubs.set(15,c);
    order_game_spades.set(22,c);
    order_game_hearts.set(8,c);
    order_game_diamonds.set(8,c);
    order_game_grand.set(15,c);
    order_game_null.set(17,c);

    c=new Card(Color.HEARTS,Face.EIGHT);
    order_game_clubs.set(8,c);
    order_game_spades.set(8,c);
    order_game_hearts.set(22,c);
    order_game_diamonds.set(1,c);
    order_game_grand.set(8,c);
    order_game_null.set(9,c);

    c=new Card(Color.DIAMONDS,Face.EIGHT);
    order_game_clubs.set(1,c);
    order_game_spades.set(1,c);
    order_game_hearts.set(1,c);
    order_game_diamonds.set(22,c);
    order_game_grand.set(1,c);
    order_game_null.set(1,c);

    c=new Card(Color.CLUBS,Face.SEVEN);
    order_game_clubs.set(21,c);
    order_game_spades.set(14,c);
    order_game_hearts.set(14,c);
    order_game_diamonds.set(14,c);
    order_game_grand.set(21,c);
    order_game_null.set(24,c);

    c=new Card(Color.SPADES,Face.SEVEN);
    order_game_clubs.set(14,c);
    order_game_spades.set(21,c);
    order_game_hearts.set(7,c);
    order_game_diamonds.set(7,c);
    order_game_grand.set(14,c);
    order_game_null.set(16,c);

    c=new Card(Color.HEARTS,Face.SEVEN);
    order_game_clubs.set(7,c);
    order_game_spades.set(7,c);
    order_game_hearts.set(21,c);
    order_game_diamonds.set(0,c);
    order_game_grand.set(7,c);
    order_game_null.set(8,c);

    c=new Card(Color.DIAMONDS,Face.SEVEN);
    order_game_clubs.set(0,c);
    order_game_spades.set(0,c);
    order_game_hearts.set(0,c);
    order_game_diamonds.set(21,c);
    order_game_grand.set(0,c);
    order_game_null.set(0,c);
  }

  /**
   * card color
   */
  private final Color color;

  /**
   * card face
   */
  private final Face face ;

  /**
   * card icon name
   */
  private final String image_name;


  /**
   * The board on which the cards are used
   * see <code>compareTo</code>
   */
  transient private Board board;

  public Card(Color c, Face f){
    color=c;
    face=f;
    image_name=face+"_"+color+".gif";
  }

  public Card(String color_string, String face_string){
    color=Color.valueOf(color_string);
    face=Face.valueOf(face_string);
    image_name=face+"_"+color+".gif";
  }

  /**
   * set board
   */
  public void setBoard(Board b){
    board=b;
  }

  /**
   * Gives access to the private member image_name
   * @return the name of the image file
   */

  public String getImageName(){
    return image_name;
  }

  /**
   * @return color of the card
   * Give access to private member color
   */
  public Color getColor(){
    return color;
  }

  /**
   * @return face of the card
   * Give access to private member face
   */
  public Face getFace(){
    return face;
  }

  /**
   * @return value of the card
   * Give access to private member value
   */
  public int getValue(){
    return face.getValue();
  }

  /**
   * @return string description of the card
   */
  public String toString(){
    return "("+face+" "+color+")";
  }


  /**
   * Overload of the equal method
   * @param obj the object we compare this with
   * @return true if obj and this are the same card
   */
  public boolean equals(Object obj){
    Card obj_card=(Card)obj;
    return (face.equals(obj_card.getFace()) && color.equals(obj_card.getColor())) ; 
  }

  /**
   * Overload of the compareTo method
   * @param card_temp the object we compare this with
   * @return an integer representing this-obj 
   */
  public int compareTo(Card card_temp){
    switch(board.getGameType()){
      case CLUBS   : return (order_game_clubs.indexOf(this)   - order_game_clubs.indexOf(card_temp));
      case SPADES  : return (order_game_spades.indexOf(this)  - order_game_spades.indexOf(card_temp));
      case HEARTS  : return (order_game_hearts.indexOf(this)  - order_game_hearts.indexOf(card_temp));
      case DIAMONDS: return (order_game_diamonds.indexOf(this)- order_game_diamonds.indexOf(card_temp));
      case GRAND   : return (order_game_grand.indexOf(this)   - order_game_grand.indexOf(card_temp));
      case NULL    : return (order_game_null.indexOf(this)    - order_game_null.indexOf(card_temp));
      default : return 0;
    }
  }
}


