import java.util.*;
/**
 *The class Board describes the board, its deck, its players, its trick
 *@author Alexandre Anzala-Yamajako
 *@version 0.8
 *@see Skat
 */
public class Board extends Observable{

  /**
   * The different type of game
   */
  public static enum GameType{ 
    CLUBS(12,Card.Color.CLUBS), 
      SPADES(11,Card.Color.SPADES), 
      HEARTS(10,Card.Color.HEARTS), 
      DIAMONDS(9,Card.Color.DIAMONDS), 
      GRAND(24,null),
      NULL(23,null);

    /**
     * Value of the game
     */
    private final int color_value;

    private final Card.Color color;

    GameType(int v,Card.Color c){
      color_value=v;
      color=c;
    }

    public int getColorValue(){
      return color_value;
    }

    public Card.Color getColor(){
      return color;
    }
  }

  private static int[] reizen_array={18, 20, 22, 23, 24, 27, 30, 33, 35, 36, 40, 44, 45, 46, 48, 50, 54, 55, 59, 60, 63, 66, 70, 72, 77, 80, 81, 84, 88, 90, 96, 99, 100, 108, 110, 117, 120, 121, 126, 130, 132, 135, 140, 143, 144, 150, 153, 154, 156, 160, 162, 165, 168, 170, 176, 180, 187, 192, 198, 204, 216, 240, 264};

  /**
   * Value of the max bid
   */
  private int reizen_value;

  /**
   * Bid Proposal
   */
  private int reizen_proposal;

  private boolean answer_proposal;

  public boolean getAnswerProposal(){
    return answer_proposal;
  }
  public int getReizenProposal(){
    return reizen_proposal;
  }

  /**
   * the skat
   */
  List<Card> skat;

  /**
   * modifier hand
   */
  private boolean modifier_hand;

  /**
   * was the game won
   */
  private boolean won_game;

  /**
   * Access to won_game
   */
  public boolean isWon(){
    return won_game;
  }

  int max_reizen_value;

  public int getMaxReizenValue(){
    return max_reizen_value;
  }
  /**
   * modifier hand schneider
   */
  private boolean modifier_schneider;

  /**
   * modifier hand schneider schwarz
   */
  private boolean modifier_schwarz;

  /**
   * modifier hand schneider schwarz ouvert
   */
  private boolean modifier_ouvert;

  /**
   * The trump color for this game, (null if grand or null ?)
   */
  private Card.Color trump;

  /**
   *The type of the game
   */
  private GameType game_type;

  /**
   * participating players
   */
  private List<Player> tab_player;

  /**
   * dealing player
   */
  private  int dealer_index;

  /**
   * Action player
   */
  private int action_player;

  /**
   * Taking player
   */
  private int index_taker;

  private Trick t;

  /**
   * Player who won the last trick
   */
  private int last_winner;

  public int getLastWinner(){
    return last_winner;
  }

  public Trick getTrick(){
    return t;
  }

  public int getReizenValue(){
    return reizen_value;
  }

  /**
   * The deck for that game
   */
  private Deck deck;

  /**
   * The trick list
   */
  private List<Trick> board_tricks;

  /**
   * The turn number inside a game
   */
  private int turn;

  /**
   * Access to <code>tab_player</code>
   * @return tab_player
   */
  public List<Player> getTabPlayer(){
    return tab_player;
  }

  public List<Card> getSkat(){
    return skat;
  }

  public List<Trick> getTricks(){
    return board_tricks;
  }

  /**
   * Access to <code>action_player</code>
   * @return action_player
   */
  public int getActionPlayerIndex(){
    return action_player;
  }
  /**
   * Access to <code>dealer_index</code>
   * @return dealer_index
   */
  public int getDealer(){
    return dealer_index;
  }

  /**
   * Access to <code>turn</code>
   * @return turn
   */
  public int getTurn(){
    return turn;
  }

  private int game;

  /**
   * Access to <code>game</code>
   * @return game
   */
  public int getGame(){
    return game;
  }

  /**
   * Color asked by the first card of a trick
   */
  private Card asked_info;

  /**
   * Access to <code>game_type</code>
   * @return type of game
   */
  public GameType getGameType(){
    return game_type;
  }

  /**
   *Access to trum
   *@return the trump color, null if null or grand
   */
  public Card.Color getTrumpColor(){
    return trump;
  }

  /**
   *Access to asked_info
   *@return the color asked by the initial player
   */
  public Card getAskedInfo(){
    return asked_info;
  }

  /**
   * Access to modifier hand
   * @return <code>modifier_hand</code>
   */
  public boolean isHand(){
    return modifier_hand;
  }

  /**
   * Get index_taker
   * @return <code>index_taker</code>
   */
  public int getIndexTaker(){
    return index_taker;
  }

  /**
   * Access to modifier schneider
   * @return <code>modifier_schneider</code>
   */
  public boolean isSchneider(){
    return modifier_schneider;
  }

  /**
   * Access to modifier schwarz
   * @return <code>modifier_schwarz</code>
   */
  public boolean isSchwarz(){
    return modifier_schwarz;
  }

  /**
   * Access to modifier ouvert
   * @return <code>modifier_ouvert</code>
   */
  public boolean isOuvert(){
    return modifier_ouvert;
  }

  /**
   * Calculate the modifier multiplicator
   * @return the modifier multiplicator as a function of the modifiers
   */
  public int bonusMultiplicator(){
    return (modifier_hand?1:0) + (modifier_schneider?1:0) + (modifier_schwarz?1:0) + (modifier_ouvert?1:0);
  }

  public Board(Player p0,Player p1,Player p2){
    board_tricks=new ArrayList<Trick>(10);
    tab_player=new ArrayList<Player>(3);
    skat=new ArrayList<Card>(2);

    tab_player.add(0,p0);
    tab_player.add(1,p1);
    tab_player.add(2,p2);
    deck=new Deck();
    deck.setBoard(this);//set the board for the cards
    game=0;
    game_type=GameType.GRAND;//game_type cannot be null;
    dealer_index=0;
  }

  /**
   * play a game
   */
  public void play_game(){
    List<Card> chosen_skat;
    List<Integer> chosen_modifiers;

    sendNotification(8);

    sendNotification(22);

    sendNotification(9);

    sendNotification(10);

    updateOrderOfPlay();

    sendNotification(11);

    boolean valid_win;
    switch(tab_player.get(0).getRole()){
      case DEALER  :dealer_index = 0;break;
      case SPEAKER :dealer_index = 2;break;
      case LISTENER:dealer_index = 1;break;
      default      :dealer_index =-1;
    }
    turn=0;
    deck.reset();
    deck.shuffle();
    deal(dealer_index);

    sendNotification(12);

    if (!reizen(dealer_index)){
      game++;
      endGame();
      return;
    }
    sendNotification(26);
    tab_player.get(0).getStat()[0]++;
    tab_player.get(1).getStat()[0]++;
    tab_player.get(2).getStat()[0]++;
    last_winner=index_taker;
    tab_player.get(index_taker).getStat()[1]++;
    tab_player.get((index_taker+1)%3).getStat()[3]++;
    tab_player.get((index_taker+2)%3).getStat()[3]++;

    action_player=index_taker;
    game_type=tab_player.get(index_taker).decideGametype();

    sendNotification(16);

    trump=game_type.getColor();

    tab_player.get(0).sortHand();
    tab_player.get(1).sortHand();
    tab_player.get(2).sortHand();

    sendNotification(12);

    action_player=index_taker;
    chosen_modifiers=tab_player.get(index_taker).chooseModifiers();
    modifier_hand=chosen_modifiers.get(0).equals(new Integer(1));
    modifier_schneider=chosen_modifiers.get(1).equals(new Integer(1));
    modifier_schwarz=chosen_modifiers.get(2).equals(new Integer(1));
    modifier_ouvert=chosen_modifiers.get(3).equals(new Integer(1));
    while (!validModifiers()){
      action_player=index_taker;
      chosen_modifiers=tab_player.get(index_taker).chooseModifiers();
      modifier_hand=chosen_modifiers.get(0).equals(new Integer(1));
      modifier_schneider=chosen_modifiers.get(1).equals(new Integer(1));
      modifier_schwarz=chosen_modifiers.get(2).equals(new Integer(1));
      modifier_ouvert=chosen_modifiers.get(3).equals(new Integer(1));
    }

    sendNotification(14);

    if (modifier_ouvert){
      sendNotification(20);
    }

    tab_player.get(index_taker).addCards(skat);
    tab_player.get(index_taker).HandMultiplicatorCalculator(game_type);
    if (modifier_hand){
      tab_player.get(index_taker).remove(skat);
    }else{
      sendNotification(15);
      action_player=index_taker;
      chosen_skat=tab_player.get(index_taker).chooseSkat();
      tab_player.get(index_taker).remove(chosen_skat);
      //cas ou pas hand, on remplace
      skat.clear();
      skat.addAll(chosen_skat);
    }
    for (int i=1;i<=10;i++){
      sendNotification(21);
      sendNotification(19);
      doTurn();
      sendNotification(18);
      sendNotification(23);
    }

    valid_win=checkVictoryPoint(index_taker);

    if (game_type==GameType.NULL){
      if (modifier_ouvert && modifier_hand){
        max_reizen_value=59;
      }else{
        max_reizen_value=(modifier_ouvert ? 46 : 35);
      }
      if (!modifier_ouvert && !modifier_hand){
        max_reizen_value=23;
      }
    }else{
      max_reizen_value=(bonusMultiplicator()+((tab_player.get(index_taker).getTricks().size()==10) ? 1 : 0 )+((tab_player.get(index_taker).getGameScore()>=90) ? 1 : 0 ))*game_type.getColorValue();
    }
    won_game=valid_win && (reizen_value<=max_reizen_value);
    if (won_game){
      tab_player.get(index_taker).modifOverall(max_reizen_value);
      tab_player.get(index_taker).getStat()[2]++;
    }else{
      tab_player.get(index_taker).modifOverall(-2*max_reizen_value);
      tab_player.get((index_taker+1)%3).getStat()[4]++;
      tab_player.get((index_taker+2)%3).getStat()[4]++;
    }
    sendNotification(24);
    sendNotification(25);
    game++;
    endGame();
  }

  /**
   * Cleans up after a game
   */
  public void endGame(){
    skat.clear();
    board_tricks.clear();
    tab_player.get(0).endGame();
    tab_player.get(1).endGame();
    tab_player.get(2).endGame();
  }

  /**
   * Checks if the taker has enogh point to win the game, the checking of overbidding is not done here.
   * @param index_taker index of the taker
   * @return whether the taker won or not
   */
  public boolean checkVictoryPoint(int index_taker){
    if (game_type==GameType.NULL) return (tab_player.get(index_taker).getTricks().size()==0);

    if (modifier_schwarz && tab_player.get(index_taker).getTricks().size()!=10) return false;

    if (modifier_schneider && tab_player.get(index_taker).getGameScore()>=90) return false;

    if (tab_player.get(index_taker).getGameScore()>60) return false;

    return true;
  }

  public void sendNotification(int code){
    setChanged();
    notifyObservers(new Integer(code));
 //   while(!hasChanged()) {
 //   System.out.println("Sleep");
 //     synchronized(Thread.currentThread()){
 //       try {
 //         Thread.currentThread().wait();
 //       } catch (InterruptedException e) {System.err.println("Failed wait"+e.toString());System.exit(1);}
 //     }
 //   System.out.println("Wake up");
 //   }
  }

  /**
   * Organizes the reizen
   * @param dealer_index position du dealer
   * @return true if somebody takes false otherwise
   */
  public boolean reizen(int dealer_index){
    int ind=0;
    int speaker_index=(dealer_index+2)%3;
    int listener_index=(dealer_index+1)%3;
    while(ind<63){
      action_player=speaker_index;
      reizen_proposal=reizen_array[ind];

      answer_proposal=tab_player.get(speaker_index).decideReizen(reizen_array[ind]);
      sendNotification(13);

      if (answer_proposal){
        action_player=listener_index;
        reizen_proposal=reizen_array[ind];

        answer_proposal=tab_player.get(listener_index).decideReizen(reizen_array[ind]);
        sendNotification(13);
        if (answer_proposal){
          ind++;
          continue;
        }else{
          index_taker=speaker_index;
          reizen_value=reizen_array[ind];
          return true;
        }
      }else{
        if (speaker_index!=dealer_index){
          //          ind++;
          speaker_index=dealer_index;
          continue;
        }else{
          if (ind!=0){
            index_taker=listener_index;
            reizen_value=reizen_array[ind-1];
            return true;
          }else{
            action_player=listener_index;
            reizen_proposal=reizen_array[ind];

        answer_proposal=tab_player.get(listener_index).decideReizen(reizen_array[ind]);
            sendNotification(13);
            if (answer_proposal){
              index_taker=listener_index;
              reizen_value=reizen_array[ind];
              return true;
            }else{
              return false;
            }
          }
        }
      }
    }
    index_taker=listener_index;
    reizen_value=(ind<63 ? reizen_array[ind] : reizen_array[ind-1]);
    return true;
  }

  /**
   * deals cards to the players
   * 3, 2(skat) 4, 3
   * @param dealer_index position du dealer
   */
  public void deal(int dealer_index){
    tab_player.get((dealer_index+1)%3).addCards(deck.dealCards(3));
    tab_player.get((dealer_index+2)%3).addCards(deck.dealCards(3));
    tab_player.get((dealer_index+3)%3).addCards(deck.dealCards(3));
    skat.addAll(deck.dealCards(2));
    tab_player.get((dealer_index+1)%3).addCards(deck.dealCards(4));
    tab_player.get((dealer_index+2)%3).addCards(deck.dealCards(4));
    tab_player.get((dealer_index+3)%3).addCards(deck.dealCards(4));
    tab_player.get((dealer_index+1)%3).addCards(deck.dealCards(3));
    tab_player.get((dealer_index+2)%3).addCards(deck.dealCards(3));
    tab_player.get((dealer_index+3)%3).addCards(deck.dealCards(3));
  }

  /**
   * bring the board from turn i to turn i+1
   * does trick handling and rule enforcing
   */
  public void doTurn(){
    t=new Trick(this);
    Card c;

    action_player=last_winner;
    c=tab_player.get(last_winner).play();//pas de check sur la premiere carte
    t.addCard(c,0);
    sendNotification(17);
    asked_info=c;
    tab_player.get(last_winner).remove(c);


    action_player=(last_winner+1)%3;
    c=tab_player.get((last_winner+1)%3).play();//check
    while (!validPlay(tab_player.get((last_winner+1)%3),c)){
      action_player=(last_winner+1)%3;
      c=tab_player.get((last_winner+1)%3).play();
    }
    t.addCard(c,1);
    sendNotification(17);
    tab_player.get((last_winner+1)%3).remove(c);

    action_player=(last_winner+2)%3;
    c=tab_player.get((last_winner+2)%3).play();//check
    while (!validPlay(tab_player.get((last_winner+2)%3),c)){
      action_player=(last_winner+2)%3;
      c=tab_player.get((last_winner+2)%3).play();
    }
    t.addCard(c,2);
    sendNotification(17);
    tab_player.get((last_winner+2)%3).remove(c);


    last_winner=(last_winner+t.declareWinner())%3;
    tab_player.get(last_winner).modifGameScore(t.getValue());
    board_tricks.add(t);
    tab_player.get(last_winner).getTricks().add(new Integer(board_tricks.size()-1));
    turn++;
  }

  /**
   * Checks if the player is authorized to play this card at this point of the game
   * @param p the player playing the card
   * @param c the card played
   */
  private boolean validPlay(Player p,Card c){
    if (p.hasColor(game_type,asked_info)){
      if (Card.sameColor(game_type,c,asked_info)) return true;
    }else{
      return true;
    }
    return false;
  }

  /**
   * Checks if the player is authorized to have thoses modifiers
   */
  private boolean validModifiers(){
    if (game_type==GameType.NULL){
      return !(modifier_schneider || modifier_schwarz);
    }
    if (!modifier_hand){//if no hand then no modifier is allowed
      return !(modifier_ouvert || modifier_schneider || modifier_schwarz) ;
    }
    if (modifier_ouvert){
      return (modifier_schwarz && modifier_schneider); 
    }
    if (modifier_schwarz){
      return modifier_schneider; 
    }
    return true;
  }


  /**
   *

  /**
   * Updates the order of play, 
   */
  public void updateOrderOfPlay(){
    if (game==0){
      tab_player.get(0).setRole(0);
      tab_player.get(1).setRole(1);
      tab_player.get(2).setRole(2);
    }else{
      tab_player.get(0).setRole((tab_player.get(0).getRole().getRoleId()+1) % 3);
      tab_player.get(1).setRole((tab_player.get(1).getRole().getRoleId()+1) % 3);
      tab_player.get(2).setRole((tab_player.get(2).getRole().getRoleId()+1) % 3);
    }
  }

  /**
   * Converts to string
   */
  public String toString(){
    String res=deck.toString();
    res+="\nThe players are:\n"+tab_player.get(0).toString()+"\n\n"+tab_player.get(1).toString()+"\n\n"+tab_player.get(2).toString()+"\n\n";
    res+="This is game "+game+"\n";
    res+="The Skat is: \n";
    for (Card c : skat){
      res+=c.toString()+" ";
    }
    res+="\n"+tab_player.get(index_taker).getRole()+" bid "+reizen_value+" on "+game_type+"\n";
    res+=(modifier_hand ?"Hand(yes) ": "Hand(no) ")+(modifier_schneider ?"Schneider(yes) ": "Schneider(no) ")+(modifier_schwarz ?"Schwarz(yes) ": "Schwarz(no) ")+(modifier_ouvert ?"Ouvert(yes) ": "Ouvert(no) ");
    res+="\nPlayer "+last_winner+" won the last trick\n";
    res+="The tricks are:\n";
    for (int i=0;i<board_tricks.size();i++){
      res+=(i+1)+": "+board_tricks.get(i).toString()+"\n";
    }
    res+="This is turn "+turn+" and the asked color is "+asked_info.toString()+"\n";

    return res;
  }
}

