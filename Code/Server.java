import java.io.*;
import java.util.*;
import java.net.*;

/**
 *The class Server implements the server side of the network code for Skat
 *@author Alexandre Anzala-Yamajako
 *@version 0.0
 *@see Skat
 */


public class Server {
  /**
   * The server socket
   */
  ServerSocket server_socket;

  /**
   * The list of active connections
   */
  List<Connection> client_connections;
  
  /**
   * server port
   */
  int server_port;

  /**
   * server name
   */
  String server_name;

  /*
   * Create a socket and binds to a port
   */
  public Server(){
    client_connections=new ArrayList<Connection>();
    try {
      server_socket = new ServerSocket(0);
    } catch (IOException e) {
      System.err.println("Could not listen on chosen port");
      System.exit(1);
    }
    server_port=server_socket.getLocalPort();
    try{
    server_name=InetAddress.getLocalHost().getHostName();
    }catch (UnknownHostException e){
      System.err.println("Could not determine localhost");
      System.exit(1);
    }
  }

  /*
   * Accepts incoming connections and dispatch
   */
  public void handleConnections(){
    Socket c=null;
    Connection conn;
    while(true){
    try {
    c=server_socket.accept();
    } catch (IOException e) {
      System.err.println("accept failed");
      System.exit(1);
    }
    conn=new Connection(c);
    client_connections.add(conn);
    (new WorkerThread.ConnectThread(conn)).start();
    }
  }

  /*
   * Closes all open connections
   */
  public void closeAll(){
    for (Connection c_socket : client_connections){
      try{
      c_socket.close();
    } catch (IOException e) {
      System.err.println("Could not close client socket");
      System.exit(1);
    }
      client_connections.remove(c_socket);
    }
      try{
    server_socket.close();
    } catch (IOException e) {
      System.err.println("Could not close server socket");
      System.exit(1);
    }
  }

  /*
   * Prints server nfo
   */
  public void publishInfo(){
    System.out.println(server_name+" "+server_port);
  }

}

