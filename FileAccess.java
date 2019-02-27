import java.io.*;
import java.util.*;
public class FileAccess{
  public static void main(String[] args){
    Scanner sca = getScanner("Maze1.txt");
    //PRINT LINES, DO NOTHING MORE
    //while(sca.hasNext()) System.out.println(sca.nextLine());
    //GENERATE 2D ARRAY, PRINT
    char[][] map = getMap(sca);
    for(int i=0;i<map.length;i++){
      System.out.println(Arrays.toString(map[i]));
    }
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
  public static char[][] getMap(Scanner sca){
    ArrayList<String> lines = new ArrayList<String>();
    while(sca.hasNextLine()) lines.add(sca.nextLine());
    char[][] out = new char[lines.size()][];
    for(int i=0;i<out.length;i++){
      out[i] = lines.get(i).toCharArray();
    }
    return out;
  }

    public static void solve(char[][] map){
      int[] startCoords = findChar(map,'S');
      int[] endCoords = findChar(map,'E');
      solver(startCoords[0],startCoords[1],endCoords[0],endCoords[1]);
    }
    private static void solver(int curR,int curC,int endR,int endC){

    }
    private static int[] findChar(char[][] map, char c){
      return null;
    }
}
