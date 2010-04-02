import java.io.*;
import java.util.*;

/**
 *The class Skat describe a full game of skat
 *@author Alexandre Anzala-Yamajako
 *@version 0.1
 */
public class Skat{

  /**
   * List of board 
   */
  private List<Board> board_list;

  /**
   * List of connected players
   */
  private List<Player> connected_player;

  /**
   * The server associated with skat
   */
  private Server server;

  public Skat(){
    board_list=new ArrayList<Board>();
    connected_player=new ArrayList<Player>();
    server=new Server();
  }
  

  /**
   * Access to <code>server</code>
   * @return server
   */
  public Server getServer(){
    return server;
  }



}
