// Name: Yunxi Liu
// USC loginid: yunxiliu
// CS 455 PA3
// Fall 2016

import java.util.LinkedList;
import java.util.ListIterator;


/**
   Maze class

   Stores information about a maze and can find a path through the maze
   (if there is one).
   
   Assumptions about structure of the maze, as given in mazeData, startLoc, and endLoc
   (parameters to constructor), and the path:
     -- no outer walls given in mazeData -- search assumes there is a virtual 
        border around the maze (i.e., the maze path can't go outside of the maze
        boundaries)
     -- start location for a path is maze coordinate startLoc
     -- exit location is maze coordinate exitLoc
     -- mazeData input is a 2D array of booleans, where true means there is a wall
        at that location, and false means there isn't (see public FREE / WALL 
        constants below) 
     -- in mazeData the first index indicates the row. e.g., mazeData[row][col]
     -- only travel in 4 compass directions (no diagonal paths)
     -- can't travel through walls
 */

public class Maze {
   
   private static final boolean FREE = false;
   private static final boolean WALL = true;
   private static final int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // four possible directions in a search step
   
   private boolean[][] mazeData; // the data of the maze object
   private MazeCoord startLoc; // the start location
   private MazeCoord endLoc; // the end location
   private LinkedList<MazeCoord> path; // path in the maze from start to end

   /**
      Constructs a maze.
      @param mazeData the maze to search.  See general Maze comments for what
      goes in this array.
      @param startLoc the location in maze to start the search (not necessarily on an edge)
      @param endLoc the "exit" location of the maze (not necessarily on an edge)
      PRE: 0 <= startLoc.getRow() < mazeData.length and 0 <= startLoc.getCol() < mazeData[0].length
         and 0 <= endLoc.getRow() < mazeData.length and 0 <= endLoc.getCol() < mazeData[0].length

    */
   public Maze(boolean[][] mazeData, MazeCoord startLoc, MazeCoord endLoc)
   {
	   this.mazeData = mazeData;
	   this.startLoc = startLoc;
	   this.endLoc = endLoc;
	   path = new LinkedList<>();
   }


   /**
   Returns the number of rows in the maze
   @return number of rows
   */
   public int numRows() {
      return mazeData.length;
   }

   
   /**
   Returns the number of columns in the maze
   @return number of columns
   */   
   public int numCols() {
      return mazeData[0].length;
   } 
 
   
   /**
      Returns true iff there is a wall at this location
      @param loc the location in maze coordinates
      @return whether there is a wall here
      PRE: 0 <= loc.getRow() < numRows() and 0 <= loc.getCol() < numCols()
   */
   public boolean hasWallAt(MazeCoord loc) {
      return mazeData[loc.getRow()][loc.getCol()];
   }
   

   /**
      Returns the entry location of this maze.
    */
   public MazeCoord getEntryLoc() {
      return startLoc;
   }
   
   
   /**
   Returns the exit location of this maze.
   */
   public MazeCoord getExitLoc() {
      return endLoc;
   }

   
   /**
      Returns the path through the maze. First element is starting location, and
      last element is exit location.  If there was not path, or if this is called
      before search, returns empty list.

      @return the maze path
    */
   public LinkedList<MazeCoord> getPath() {
	   
	   return path;

   }


   /**
      Find a path through the maze if there is one.  Client can access the
      path found via getPath method.
      @return whether path was found.
    */
   public boolean search()  {  
      
	   if(mazeData[startLoc.getRow()][startLoc.getCol()] == WALL || mazeData[endLoc.getRow()][endLoc.getCol()] == WALL){
		   return false;
	   } // the start point or the end point is on the wall
	   ListIterator<MazeCoord> iter = path.listIterator();
	   helper(iter, startLoc, new boolean[mazeData.length][mazeData[0].length]);
	   if(path.size() > 0){
		   return true;
	   }
	   return false;

   }
   
   /**
    * Recursive function using DFS and backtracking to find the path. It is a helper of search() function.
    * @param iter the listIterator to operate LinkedList of path
    * @param coord the coordinate of the current point in the maze
    * @param visited to keep track of positions already visited in the search for a path 
    * @return if there is a path from the current coordinate
    * PRE: iter is for the LinkedList<MazeCoord> path and visited.length >= mazeData.length && visited[0].length >= mazeData[0].length
    */
   private boolean helper(ListIterator<MazeCoord> iter, MazeCoord coord, boolean[][] visited){
	   if(coord.getRow() < 0 || coord.getCol() < 0 || coord.getRow() >= mazeData.length || coord.getCol() >= mazeData[0].length
			   || mazeData[coord.getRow()][coord.getCol()] == WALL || visited[coord.getRow()][coord.getCol()]){
		   return false;
	   }// the current point is not available
	   iter.add(coord);
	   visited[coord.getRow()][coord.getCol()] = true;
	   
	   if(coord.getRow() == endLoc.getRow() && coord.getCol() == endLoc.getCol()){
		   return true;
	   }// reach the end point
	   
	   for(int i = 0; i < directions.length; i++){ 
		   
	   	   if(helper(iter, new MazeCoord(coord.getRow() + directions[i][0], coord.getCol() + directions[i][1]), visited))
	   		   return true; // there is a path from the current point in this direction
	   	   
	   }
	   iter.previous();
	   iter.remove();// remove the current point because there is not a path from this point
	   visited[coord.getRow()][coord.getCol()] = false;
	   return false;
   }

}
