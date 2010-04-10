/**
 * Describes the protocol<br/>
 *0 ConnectThread->client:0(FILL_BOARD) human_number<br/>
 *0 client->ConnectThread:0 true/false<br/>
 *<br/>
 *1 BoardThread->client:1(NAME_CLIENT)<br/>
 *1 client->BoardThread:1 name_client<br/>
 *<br/>
 *2 Board->BoardThread :2(REIZEN_REQUEST) reizen_value<br/>
 *2 BoardThread->client:2(REIZEN_REQUEST) reizen_value<br/>
 *2 client->BoardThread:2 true/false<br/>
 *2 BoardThread->Board :true/false<br/>
 *<br/>
 *3 Board->BoardThread :3(GAMETYPE_REQUEST)<br/>
 *3 BoardThread->client:3(GAMETYPE_REQUEST)<br/>
 *3 client->BoardThread:3 game_type<br/>
 *3 BoardThread->Board : game_type<br/>
 *<br/>
 *4 Board->BoardThread :4(GAMETYPE_MODIFIERS_REQUEST)<br/>
 *4 BoardThread->client:4(GAMETYPE_MODIFIERS_REQUEST)<br/>
 *4 client->BoardThread:4 true/false<br/>
 *4 BoardThread->Board : true/false<br/>
 *<br/>
 *5 Board->BoardThread :5(SKAT_REQUEST)<br/>
 *5 BoardThread->client:5(SKAT_REQUEST)<br/>
 *5 client->BoardThread:5 card_index<br/>
 *5 BoardThread->Board : card_index<br/>
 *<br/>
 *6 Board->BoardThread :6(PLAY_REQUEST)<br/>
 *6 BoardThread->client:6(PLAY_REQUEST)<br/>
 *6 client->BoardThread:6 card_index<br/>
 *6 BoardThread->Board : card_index<br/>
 *<br/>
 *7 client->BoardThread:7 (QUIT_REQUEST)<br/>
 *<br/>
 *08 BoardThread->client:08 (NAME_INFO)(1,2) name_player[+i]<br/>
 *09 BoardThread->client:09 (STAT_INFO)(0,1,2) stats<br/>
 *10 BoardThread->client:10 (GAME_INFO) game<br/>
 *11 BoardThread->client:11 (ROLE_INFO) role<br/>
 *12 BoardThread->client:12 (HAND_INFO) hand<br/>
 *13 BoardThread->client:13 (REIZEN_INFO)(0,1,2)|reizen_value|accept/refuse<br/>
 *14 BoardThread->client:14 (MODIF_INFO) tab_modifiers<br/>
 *15 BoardThread->client:15 (SKAT_INFO) skat<br/>
 *16 BoardThread->client:16 (GAMETYPE_INFO) game_type<br/>
 *17 BoardThread->client:17 (TRICK_INFO) current_trick<br/>
 *18 BoardThread->client:18 (TRICKLIST_INFO) trick_list<br/>
 *19 BoardThread->client:19 (TURN_INFO) turn<br/>
 *20 BoardThread->client:20 (OUVERT_INFO) hand_player<br/>
 *21 BoardThread->client:21 (STARTTURN_INFO)<br/>
 *22 BoardThread->client:22 (STARTGAME_INFO)<br/>
 *23 BoardThread->client:23 (TRICKWINNER_INFO) i <br/>
 *24 BoardThread->client:24 (RESULTGAME_INFO)  win/lose|valuegame<br/>
 *25 BoardThread->client:25 (SCORE_INFO) i score(i)<br/>
 *26 BoardThread->client:26 (TAKER_INFO) i <br/>
 *<br/>
 *27 HumanInterface->client: 27 (NAME_PLAYER) name_player<br/>
 *28 HumanInterface->client: 28 (NAME_SERVER) name_server<br/>
 *29 HumanInterface->client: 29 (PORT_SERVER) port<br/>
 *<br/>
 */


public class Protocol{
  /**
   * ConnectThread->client:(FILL_BOARD)<br/>
   * client->ConnectThread: true/false<br/>
   */
  public static final int FILL_BOARD=0;


  /**
   * BoardThread->client:(NAME_CLIENT)<br/>
   * client->BoardThread: name_client<br/>
   */
  public static final int NAME_CLIENT=1;


  /**
   * Board->BoardThread :(REIZEN_REQUEST) reizen_value<br/>
   * BoardThread->client:(REIZEN_REQUEST) reizen_value<br/>
   * client->BoardThread: true/false<br/>
   * BoardThread->Board :true/false<br/>
   */
  public static final int REIZEN_REQUEST=2;


  /**
   * Board->BoardThread :(GAMETYPE_REQUEST)<br/>
   * BoardThread->client:(GAMETYPE_REQUEST)<br/>
   * client->BoardThread: game_type<br/>
   * BoardThread->Board : game_type<br/>
   */
  public static final int GAMETYPE_REQUEST=3;


  /**
   * Board->BoardThread :(GAMETYPE_MODIFIERS_REQUEST)<br/>
   * BoardThread->client:(GAMETYPE_MODIFIERS_REQUEST)<br/>
   * client->BoardThread: true/false<br/>
   * BoardThread->Board : true/false<br/>
   */
  public static final int GAMETYPE_MODIFIERS_REQUEST=4;


  /**
   * Board->BoardThread :(SKAT_REQUEST)<br/>
   * BoardThread->client:(SKAT_REQUEST)<br/>
   * client->BoardThread: card_index<br/>
   * BoardThread->Board : card_index<br/>
   */
  public static final int SKAT_REQUEST=5;


  /**
   * Board->BoardThread :(PLAY_REQUEST)<br/>
   * BoardThread->client:(PLAY_REQUEST)<br/>
   * client->BoardThread: card_index<br/>
   * BoardThread->Board : card_index<br/>
   */
  public static final int PLAY_REQUEST=6;


  /**
   * client->BoardThread: (QUIT_REQUEST)<br/>
   */
  public static final int QUIT_REQUEST=7;


  /**
   * BoardThread->client: (NAME_INFO)(1,2) name_player[+i]<br/>
   */
  public static final int NAME_INFO=8;


  /**
   * BoardThread->client: (STAT_INFO)(0,1,2) stats<br/>
   */
  public static final int STAT_INFO=9;


  /**
   * BoardThread->client: (GAME_INFO) game<br/>
   */
  public static final int GAME_INFO=10;


  /**
   * BoardThread->client: (ROLE_INFO) role<br/>
   */
  public static final int ROLE_INFO=11;


  /**
   * BoardThread->client: (HAND_INFO) hand<br/>
   */
  public static final int HAND_INFO=12;


  /**
   * BoardThread->client: (REIZEN_INFO)(0,1,2)|reizen_value|accept/refuse<br/>
   */
  public static final int REIZEN_INFO=13;


  /**
   * BoardThread->client: (MODIF_INFO) tab_modifiers<br/>
   */
  public static final int MODIF_INFO=14;


  /**
   * BoardThread->client: (SKAT_INFO) skat<br/>
   */
  public static final int SKAT_INFO=15;


  /**
   * BoardThread->client: (GAMETYPE_INFO) game_type<br/>
   */
  public static final int GAMETYPE_INFO=16;


  /**
   * BoardThread->client: (TRICK_INFO) current_trick<br/>
   */
  public static final int TRICK_INFO=17;


  /**
   * BoardThread->client: (TRICKLIST_INFO) trick_list<br/>
   */
  public static final int TRICKLIST_INFO=18;


  /**
   * BoardThread->client: (TURN_INFO) turn<br/>
   */
  public static final int TURN_INFO=19;


  /**
   * BoardThread->client: (OUVERT_INFO) (1,2) hand_player+i<br/>
   */
  public static final int OUVERT_INFO=20;


  /**
   * BoardThread->client: (STARTTURN_INFO)<br/>
   */
  public static final int STARTTURN_INFO=21;


  /**
   * BoardThread->client: (STARTGAME_INFO)<br/>
   */
  public static final int STARTGAME_INFO=22;


  /**
   * BoardThread->client: (TRICKWINNER_INFO) i <br/>
   */
  public static final int TRICKWINNER_INFO=23;


  /**
   * BoardThread->client: (RESULTGAME_INFO)  win/lose|valuegame<br/>
   */
  public static final int RESULTGAME_INFO=24;


  /**
   * BoardThread->client: (SCORE_INFO) i score(i)<br/>
   */
  public static final int SCORE_INFO=25;


  /**
   * BoardThread->client: (TAKER_INFO) i <br/>
   */
  public static final int TAKER_INFO=26;


/**
 *27 client->HumanInterface: 30 (CONNECTION_ERROR) <br/>
 */
  public static final int CONNECTION_ERROR=27;

}
