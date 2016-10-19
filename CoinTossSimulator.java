/* Name: Yunxi Liu
 * USC loginid: yunxiliu
 * CSCI 455 PA1
 */

import java.util.Random;
/**
 * class CoinTossSimulator
 * 
 * Simulates trials of tossing two coins and allows the user to access the
 * cumulative results.
 * 
 * NOTE: we have provided the public interface for this class.  Do not change
 * the public interface.  You can add private instance variables, constants, 
 * and private methods to the class.  You will also be completing the 
 * implementation of the methods given. 
 * 
 * Invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()
 * 
 */
public class CoinTossSimulator {
	
	private int trials;
	private int twoHeads;
	private int twoTails;
	private int headTails;

   /**
      Creates a coin toss simulator with no trials done yet.
   */
   public CoinTossSimulator() {
	   
	   trials = 0;
	   twoHeads = 0;
	   twoTails = 0;
	   headTails = 0;
   }

   /**
      Runs the simulation for numTrials more trials. Multiple calls to this
      without a reset() between them add these trials to the simulation
      already completed.
      
      @param numTrials  number of trials to for simulation; must be >= 0
    */
   public void run(int numTrials) {
	   
	   trials += numTrials;
	   Random randomGenerator = new Random();
	   int[] oneResult = {0, 0};
	   for(int i = 0; i < numTrials; i++){
		   oneResult[0] = randomGenerator.nextInt(2);
		   oneResult[1] = randomGenerator.nextInt(2);
		   if(oneResult[0] == 0 && oneResult[1] == 0){
			   twoHeads++;
		   }
		   else if(oneResult[0] == 1 && oneResult[1] == 1){
			   twoTails++;
		   }
		   else {
			   headTails++;
		   }
	   }	   
   }

   /**
      Get number of trials performed since last reset.
   */
   public int getNumTrials() {
       return trials; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
      Get number of trials that came up two heads since last reset.
   */
   public int getTwoHeads() {
       return twoHeads; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
     Get number of trials that came up two tails since last reset.
   */  
   public int getTwoTails() {
       return twoTails; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
     Get number of trials that came up one head and one tail since last reset.
   */
   public int getHeadTails() {
       return headTails; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
      Resets the simulation, so that subsequent runs start from 0 trials done.
    */
   public void reset() {
	   
	   trials = 0;
	   twoHeads = 0;
	   twoTails = 0;
	   headTails = 0;
   }

   
   /**
      Test the correctness of the trials.
    */
   public boolean correctTest(){

	   if(trials == twoHeads + twoTails + headTails){
		   return true;
	   }
	   else{
		   return false;
	   }
   }    
}
