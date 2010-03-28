import java.io.*;
import java.util.*;
/**
 *The class player supersides human or npc player
 *@author Alexandre Anzala-Yamajako
 *@version 1.0
 *@see Board
 */
public abstract class Player {
  /**
   * List of player ever to play the game (not counting AIs)
   */
  protected static List<Player> player_list=new ArrayList<Player>();

  /**
   * The 3 possible roles of a player
   */
  public static enum Role {
    DEALER   (0),
             LISTENER (1),
             SPEAKER  (2);

    Role(int id){
      role_id=id;
    }

    protected final int role_id;

    public int getRoleId(){
      return role_id;
    }

    public static Role idToRole(int id){
      switch (id){
        case 0 : return Role.valueOf("DEALER");
        case 1 : return Role.valueOf("LISTENER");
        case 2 : return Role.valueOf("SPEAKER");
      }
      return null;
    }
  }

  /**
   * true if the player is human
   */
  boolean human;

  /**
   * statistics, jeux joues, pris, gagne, pas pris, gagne
   */
  protected int[] stat={0,0,0,0,0};

  /**
   * player role
   */
  protected Role player_role;

  /**
   * player name
   */
  protected String name;

  /**
   * player passwd, stored in clear and maybe useless
   */
  protected String passwd;

  /**
   * overall score
   */
  protected int overall_score;

  /**
   * score in the current game
   */
  protected int game_score;

  /**
   * The multiplicator according to the hand, no modifiers
   * This variable is set only if the player is alone
   */
  protected int hand_multiplicator;

  /**
   * unique id
   */
  protected int id;

  /**
   * player hand
   */
  protected Hand player_hand;

  /**
   * number of the tricks won by player, the type is int.
   */
  protected List<Integer> player_tricks;

  /**
   * Determines humanity of the player.
   * @return yes if player is human, no otherwise
   */
  public boolean isHuman(){
    return human;
  }

  /**
   * Access to <code>stat</code>
   * @return <code>stat</code>
   */
  public int[] getStat(){
    return stat;
  }

  /**
   * Access to <code>player_tricks</code>
   * @return <code>player_tricks</code>
   */
  public List<Integer> getTricks(){
    return player_tricks;
  }

  /**
   * Access to <code>player_hand</code>
   * @return <code>player_hand</code>
   */
  public Hand getHand(){
    return player_hand;
  }

  /**
   * Access to <code>id</code>
   * @return <code>id</code>
   */
  public int getId(){
    return id;
  }

  /**
   * Access to <code>hand_multiplicator</code>
   * @return <code>hand_multiplicator</code>
   */
  public int getHandMultiplicator(){
    return hand_multiplicator;
  }

  /**
   * Access to <code>game_score</code>
   * @return <code>game_score</code>
   */
  public int getGameScore(){
    return game_score;
  }

  /**
   * Access to <code>overall_score</code>
   * @return <code>overall_score</code>
   */
  public int getOverallScore(){
    return overall_score;
  }

  /**
   * Sets <code>passw</code>
   */
  public void setPasswd(String s){
    passwd=s;
  }

  /**
   * Checks <code>passw</code>
   * @return true if correct
   */
  public boolean checkPasswd(String s){
    return passwd.equals(s);
  }

  /**
   * Gets <code>name</code>
   * @return <code>name</code>
   */
  public String getName(){
    return name;
  }

  /**
   * Gets <code>player_role</code>
   * @return <code>player_role</code>
   */
  public Role getRole(){
    return player_role;
  }

  /**
   * Fix the name and id of a player
   */
  public Player(String n,boolean humanity){
    id=player_list.size();
    player_list.add(this);
    name=n;
    human=humanity;
    overall_score=0;
    player_hand=new Hand();
    player_tricks=new ArrayList<Integer>();
  }

  /**
   *add/substract a value to the <code>overall_score</code>
   *@param modif the value to be added
   */
  public void modifOverall(int modif){
    overall_score+=modif;
  }

  /**
   *add/substract a value to the <code>game_score</code>
   *@param modif the value to be added
   */
  public void modifGameScore(int modif){
    game_score+=modif;
  }

  /**
   * Cleans up after a game
   */
  public void endGame(){
    game_score=0;
    clearHand();
    player_tricks.clear();
    hand_multiplicator=0;
  }

  /**
   * Clear this player's hand
   */
  public void clearHand(){
    player_hand.clearHand();
  }

  /**
   * Retracts the given card from the hand
   * @param c card
   */
  public void remove(Card c){
    player_hand.remove(c);
  }

  /**
   * Retracts the given lists of cards from the hand
   * @param l list of card
   */
  public void remove(List<Card> l){
    player_hand.remove(l);
  }

  /**
   * Add a card to the hand
   */
  public void addCard(Card c){
    player_hand.addCard(c);
  }

  /**
   * Add a set of cards to the hand
   */
  public void addCards(Collection<Card> c){
    player_hand.addCards(c);
  }


  /**
   *Calculate  <code>hand_multiplicator</code>
   *WARNING take skat into account and remeber this is done pre call 
   */
  public void HandMultiplicatorCalculator(Board.GameType g){


    boolean with_without;
    hand_multiplicator=0;


    with_without=player_hand.getHand().contains(Card.orderedCard(g,31));


    hand_multiplicator++;
    for (int j=30 ;j>18;j--){ 
      if(player_hand.getHand().contains(Card.orderedCard(g,j))!=with_without) break;
      hand_multiplicator++;
    }
    hand_multiplicator++;

    if (g==Board.GameType.GRAND && hand_multiplicator>5){
      hand_multiplicator=5;
    }
  }


  /**
   * Set role
   * @param ind index of the role to be set
   */
  public void setRole(int ind){
    player_role=Role.idToRole(ind);
  }





  /**
   * checks if player has color
   * @return true if player does, false otherwise
   */
  public boolean hasColor(boolean is_null, Card.Color color){
    for (Card c : player_hand.getHand()){
      if (c.getColor()==color){
        if (is_null || c.getFace()!=Card.Face.J) return true;
      }
    }
    return false;
  }

  /**
   * String representation of a player
   */
  public String toString(){
    String res;
    res="Player "+name+"("+id+") is a "+(human ? "human\n" : "AI\n");
    res+="game played "+stat[0]+", game taken "+stat[1]+", game taken won "+stat[2]+", game not taken "+stat[3]+", game not taken won "+stat[4]+"\n";
    res+="His role is "+player_role+"\n";
    res+="His score overall is "+overall_score+" and his score in current game is "+game_score+" \n" ;
    res+="His hand contains:\n"+(player_hand.getCurrentHandSize()==0 ? "nothing" :player_hand.toString() )+ "\n";
    res+="He won tricks number: ";
    for (Integer i : player_tricks){
      res=res+i.toString()+", ";
    }
    return res;
  }

  /**
   * Sorts the hand of a player
   */
  public void sortHand(){
    player_hand.sortHand();
  }

  /**
   * Allow implementation for human and AI
   * @return the card to play
   */
  public abstract Card play();

  /**
   * Allow implementation for human and AI
   * @param value is the current bid
   * @return yes if the reizen is acceptable
   */
  public  abstract boolean decideReizen(int value);


  /**
   * Allow implementation for human and AI
   * @return the chosen GameType
   */
  public  abstract Board.GameType decideGametype();

  /**
   * Allow implementation for human and AI
   * @return the chosen skat for non hand games
   */
  public  abstract List<Card> chooseSkat();

  /**
   * Allow implementation for human and AI
   * @return list of option
   */
  public  abstract List<Integer> chooseModifiers();
}


