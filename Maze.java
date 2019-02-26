import java.io.*;
import java.util.Scanner;
public class Maze{
  public static void main(String[] args){
    Scanner sca = getScanner("Maze1.txt");
    while(sca.hasNext()) System.out.println(sca.nextLine());
  }
  public static Scanner getScanner(String fileName){
    try{
      File f = new File(fileName);
      return new Scanner(f);
    }
    catch(FileNotFoundException e){
      System.out.println("file error: "+fileName+" not found");
      return null;
    }
  }
}
