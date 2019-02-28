import java.util.*;
import java.io.*;
public class Maze{

    private static int[][] moveStatic = {{1,0}, //down
                                         {0,1}, //right
                                         {-1,0},//up
                                         {0,-1} //left
                                       };
    private char[][]maze;
    private boolean animate;//false by default

    /*Constructor loads a maze text file, and sets animate to false by default.

      1. The file contains a rectangular ascii maze, made with the following 4 characters:
      '#' - Walls - locations that cannot be moved onto
      ' ' - Empty Space - locations that can be moved onto
      'E' - the location of the goal (exactly 1 per file)

      'S' - the location of the start(exactly 1 per file)

      2. The maze has a border of '#' around the edges. So you don't have to check for out of bounds!


      3. When the file is not found OR the file is invalid (not exactly 1 E and 1 S) then:

         throw a FileNotFoundException or IllegalStateException

    */

    public Maze(String filename) throws FileNotFoundException{
      Scanner sca = getScanner(filename);
      maze = getMap(sca);
      if(!(mapIsValid())) throw new IllegalStateException("missing or too many starts and ends");
    }
    private static Scanner getScanner(String filename)throws FileNotFoundException{
      File f = new File(filename);
      return new Scanner(f);
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
    private boolean mapIsValid(){
      boolean foundE = false;
      boolean foundS = false;
      for(int i=0;i<maze.length;i++){
        for(int j=0;j<maze[i].length;j++){
          if(foundE && maze[i][j] == 'E') return false;
          if(foundS && maze[i][j] == 'S') return false;
          foundE = foundE || maze[i][j]=='E';
          foundS = foundS || maze[i][j]=='S';
        }
      }
      return foundE && foundS;
    }



    private void wait(int millis){
         try {
             Thread.sleep(millis);
         }
         catch (InterruptedException e) {
         }
     }


    public void setAnimate(boolean b){

        animate = b;

    }


    public void clearTerminal(){

        //erase terminal, go to top left of screen.

        System.out.println("\033[2J\033[1;1H");

    }



    /*Wrapper Solve Function returns the helper function

      Note the helper function has the same name, but different parameters.
      Since the constructor exits when the file is not found or is missing an E or S, we can assume it exists.

    */
    public int solve(){

            //find the location of the S.
      int[] startCoords = findChar('S');

            //erase the S
      maze[ startCoords[0] ][ startCoords[1] ] = '@';

            //and start solving at the location of the s.

            //return solve(???,???);
      return solve(startCoords[0] , startCoords[1]);
    }

    /*
      Recursive Solve function:

      A solved maze has a path marked with '@' from S to E.

      Returns the number of @ symbols from S to E when the maze is solved,
      Returns -1 when the maze has no solution.


      Postcondition:

        The S is replaced with '@' but the 'E' is not.

        All visited spots that were not part of the solution are changed to '.'

        All visited spots that are part of the solution are changed to '@'
    */
    private int solve(int row, int col){ //you can add more parameters since this is private


        //automatic animation! You are welcome.
        if(animate){

            clearTerminal();
            System.out.println(this);

            wait(20);
        }

        //BASE CASE: current location is E
        if(maze[row][col] == 'E'){
          return 1;
        }
        else{
          //set char to @
          maze[row][col] = '@';
          //recurse down each of the four directions
          //(if any succeed, return their value +1)
          for(int i=0;i<4;i++){
            int nextR = row + moveStatic[i][0];
            int nextC = col + moveStatic[i][1];
            int val = solve(nextR,nextC);
            if(val != -1){
              return 1 + val;
            }
          }
          //if all return -1, set char to . and return -1
          maze[row][col] = '.';
          return -1;
        }
    }

    public String toString(){
      String out = "";
      for(char[] row:maze){
        for(char c:row){
          out += c;
        }
        out += '\n';
      }
      return out;
    }

    private int[] findChar(char c){
      for(int i=0;i<maze.length;i++){
        for(int j=0;j<maze[i].length;j++){
          if(maze[i][j] == c){
            int[] out = {i,j};
            return out;
          }
        }
      }
      return null;
    }
}
