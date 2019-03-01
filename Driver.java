import java.io.*;
public class Driver{
    public static void main(String[]args){
      String filename = args[0];
      boolean gen = indexOf(args,"gen") != -1;
      int rDim = -1;
      int cDim = -1;
      if(gen){
        rDim = Integer.parseInt(args[indexOf(args,"gen")+1]);
        cDim = Integer.parseInt(args[indexOf(args,"gen")+2]);
      }
      boolean anim = indexOf(args,"anim") != -1;
      try{
        if(gen){
          MazeGen g = new MazeGen(rDim,cDim);
          g.generate();
          g.writeToFile(filename);
        }
        Maze m = new Maze(filename);
        if(anim) m.setAnimate(true);
        System.out.println(m.solve());
        System.out.println(m);
      }catch(IOException e){
        System.out.println("Invalid filename: "+filename);
      }
    }
    private static int indexOf(String[] L,String term){
      for(int i=0;i<L.length;i++){
        if(term.equals(L[i])) return i;
      }
      return -1;
    }
}
