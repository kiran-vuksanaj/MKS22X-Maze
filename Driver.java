import java.io.*;
public class Driver{
    public static void main(String[]args){
      String filename = args[0];
      try{
        Maze f;
        f = new Maze(filename);//true animates the maze.

        //f.setAnimate(true);
        System.out.println(f.solve());
        System.out.println(f);
      }catch(FileNotFoundException e){
        System.out.println("Invalid filename: "+filename);
      }
    }
    private static boolean contains(String[] L,String term){
      for(String s : L){
        if(term.equals(s)) return true;
      }
      return false;
    }
}
