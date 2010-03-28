import java.io.*;
import java.util.*;
import java.net.*;

/**
 *The class Connection regroups a socket as well the input flow and the output flow
 *@author Alexandre Anzala-Yamajako
 *@version 0.0
 *@see WorkerThread
 *@see Server
 */
public class Connection{
  /**
   * The socket
   */
  public Socket socket;

  /**
   * Output flow
   */
  public PrintWriter out;

  /**
   * Output stream
   */
  public ObjectOutputStream out_stream;

  /**
   * Input flow
   */
  public Scanner in;

  /**
   * Input stream
   */
  public ObjectInputStream in_stream;

  /**
   * Assigns the socket and opens the flow
   */
  public Connection(Socket c){
    socket=c;
    try {
      out_stream=new ObjectOutputStream(socket.getOutputStream());
      out=new PrintWriter(out_stream, true);
    } catch (IOException e) {
      System.err.println("Impossible to get an output stream on socket");
      System.exit(1);
    }
    try {
      in_stream=new ObjectInputStream(socket.getInputStream());
      in =new Scanner(in_stream);
    } catch (IOException e) {
      System.err.println("accept failed");
      System.exit(1);
    }
  }

  /**
   * Close the flows and the socket
   */
  public void close() throws IOException{
    in.close();
    out.close();
    socket.close();
  }

}
