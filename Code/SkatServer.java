/**
 *The class SkatServer is the entry point for the server side
 *@author Alexandre Anzala-Yamajako
 *@version 0.1
 */
public class SkatServer{
public static void main(String[] args){
  Skat skat=new Skat();
  skat.getServer().publishInfo();
  skat.getServer().handleConnections();
}
}
