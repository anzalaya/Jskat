import java.io.*;
import java.util.*;

public class ArrayGenerator{
  public static void main(String[] args){
    Set<Integer> number_set=new TreeSet<Integer>();
    for (int color=9; color<=12 ; color++){
      for (int trump=2;trump<=18;trump++){
        number_set.add(new Integer(color*trump));
      }
    }
    for (int trump=2; trump<=11;trump++){
      number_set.add(new Integer(trump*24));
    }
    number_set.add(new Integer(23));
    number_set.add(new Integer(35));
    number_set.add(new Integer(46));
    number_set.add(new Integer(59));
    System.out.println(number_set.toString()+" of size "+number_set.size());


  }
}
