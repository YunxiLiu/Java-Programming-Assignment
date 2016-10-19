import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class MazeTester {
	private static Maze maze;
	public static void main(String[] args){
		String fileName = "";

	      try {

	         if (args.length < 1) {
	            System.out.println("ERROR: missing file name command line argument");
	         }
	         else {
	            fileName = args[0];
	            
	            maze = readMazeFile(fileName);

	            
	         }

	      }
	      catch (FileNotFoundException exc) {
	         System.out.println("File not found: " + fileName);
	      }
	      catch (IOException exc) {
	         exc.printStackTrace();
	      }
	      
	      LinkedList<MazeCoord> path = maze.getPath();
			ListIterator<MazeCoord> iter = path.listIterator();
			while(iter.hasNext()){
				MazeCoord coor = iter.next();
				System.out.println(coor.getRow()+","+coor.getCol());
			}
	   }
	private static Maze readMazeFile(String fileName) throws IOException {
		   
		   File inputFile = new File(fileName);
		   Scanner in = new Scanner(inputFile);
		   
		   int row = in.nextInt();
		   int col = in.nextInt();
		   boolean[][] maze = new boolean[row][col];
		   for(int i = 0; i < row; i++){
			   String str = in.next();
			   for(int j = 0; j < col; j++){
				   if(str.charAt(j) == '1'){
					   maze[i][j] = true;
				   }
				   else{
					   maze[i][j] = false;
				   }
			   }
		   }
		   return new Maze(maze, new MazeCoord(in.nextInt(), in.nextInt()), new MazeCoord(in.nextInt(), in.nextInt()));
	   }
		
}

