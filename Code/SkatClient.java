
/**
 *The class SkatClient is the entry point for the client side
 *@author Alexandre Anzala-Yamajako
 *@version 0.1
 */
public class SkatClient{
  public static void main(String[] args){
    (new Client(args[0],Integer.parseInt(args[1]))).start();
  }
}
