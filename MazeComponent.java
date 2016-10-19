// Name: Yunxi Liu
// USC loginid: yunxiliu
// CS 455 PA3
// Fall 2016

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.JComponent;

/**
   MazeComponent class
   
   A component that displays the maze and path through it if one has been found.
*/
public class MazeComponent extends JComponent
{
   private Maze maze;
   
   private static final int START_X = 10; // where to start drawing maze in frame
   private static final int START_Y = 10;
   private static final int BOX_WIDTH = 20;  // width and height of one maze unit
   private static final int BOX_HEIGHT = 20;
   private static final int INSET = 2;  // how much smaller on each side to make entry/exit inner box
   
   
   /**
      Constructs the component.
      @param maze   the maze to display
   */
   public MazeComponent(Maze maze) 
   {   
	   this.maze = maze;
   }

   
   /**
     Draws the current state of maze including the path through it if one has
     been found.
     @param g the graphics context
   */
   public void paintComponent(Graphics g)
   {
	   Graphics2D g2 = (Graphics2D) g;
	   g2.setColor(Color.BLACK);
	   g2.drawRect(START_X, START_Y, maze.numCols() * BOX_WIDTH, maze.numRows() * BOX_HEIGHT);
	   for(int i = 0; i < maze.numRows(); i++){
		   for(int j = 0; j < maze.numCols(); j++){
			   if(maze.hasWallAt(new MazeCoord(i, j))){
				   g2.setColor(Color.BLACK);
			   }
			   else{
				   g2.setColor(Color.WHITE);
			   }
			   g2.fillRect(START_X + j * BOX_WIDTH, START_Y + i * BOX_HEIGHT, BOX_WIDTH, BOX_HEIGHT); // draw each block of the maze
		   }
	   }
	   g2.setColor(Color.YELLOW); 
	   g2.fillRect(START_X + INSET + maze.getEntryLoc().getCol() * BOX_WIDTH, 
			   START_Y + INSET + maze.getEntryLoc().getRow() * BOX_HEIGHT, 
			   BOX_WIDTH - 2 * INSET, BOX_HEIGHT - 2 * INSET); // draw the entry block
	   g2.setColor(Color.GREEN);
	   g2.fillRect(START_X + INSET + maze.getExitLoc().getCol() * BOX_WIDTH, 
			   START_Y + INSET + maze.getExitLoc().getRow() * BOX_HEIGHT, 
			   BOX_WIDTH - 2 * INSET, BOX_HEIGHT - 2 * INSET); // draw the exit block
	   
	   LinkedList<MazeCoord> path = maze.getPath();
	   if(path.size() != 0){
		   drawPath(g2, path);
	   }
   }
   
   
   /**
    * A helper function to draw the path if there is one.
    * @param g2 the graphics content
    * @param path the path of the maze
    */
   private void drawPath(Graphics2D g2, LinkedList<MazeCoord> path){
	   
	   ListIterator<MazeCoord> iter = path.listIterator();
	   g2.setColor(Color.BLUE);
	   
	   MazeCoord coord = iter.next();
	   int x1 = coord.getCol() + 1; // the start point of a single line
	   int y1 = coord.getRow() + 1;
	   
	   while(iter.hasNext()){
		   coord = iter.next();
		   int x2 = coord.getCol() + 1; // the end point of a single line
		   int y2 = coord.getRow() + 1;
		   g2.drawLine(START_X + x1 * BOX_WIDTH - BOX_WIDTH / 2, START_Y + y1 * BOX_HEIGHT - BOX_HEIGHT / 2, 
				   START_X + x2 * BOX_WIDTH - BOX_WIDTH / 2, START_Y + y2 * BOX_HEIGHT - BOX_HEIGHT / 2);
		   x1 = x2;
		   y1 = y2;
	   }
   }
   
}



