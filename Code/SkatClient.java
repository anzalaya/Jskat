
/**
 *The class SkatClient is the entry point for the client side
 *@author Alexandre Anzala-Yamajako
 *@version 0.1
 */
public class SkatClient{
  public static void main(String[] args){
    if (args.length>0){
    (new CommandLine()).start();
    }else{
    (new TextGUI()).start();
    }
  }
}
