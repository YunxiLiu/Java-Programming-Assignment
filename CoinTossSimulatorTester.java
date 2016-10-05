/* Name: Yunxi Liu
 * USC loginid: yunxiliu
 * CSCI 455 PA1
 */

import java.util.Scanner;

/**
 * class CoinTossSimulatorTester
 * 
 * Unit test based on console to test the constructor, trial results and reset
 */
public class CoinTossSimulatorTester {
	public static void main(String[] args){
		
		Scanner in = new Scanner(System.in);
		CoinTossSimulator cointoss = new CoinTossSimulator();
		System.out.println("After constructor:");
		printResult(cointoss.getNumTrials(),cointoss.getTwoHeads(),cointoss.getTwoTails(),cointoss.getHeadTails(),cointoss.correctTest());
		
		//test with 1 trial
		cointoss.run(1);
		System.out.println("After run(1):" );
		printResult(cointoss.getNumTrials(),cointoss.getTwoHeads(),cointoss.getTwoTails(),cointoss.getHeadTails(),cointoss.correctTest());
		
		//test with 10 trials
		cointoss.run(10);
		System.out.println("After run(10):" );
		printResult(cointoss.getNumTrials(),cointoss.getTwoHeads(),cointoss.getTwoTails(),cointoss.getHeadTails(),cointoss.correctTest());

		//test with 100 trials
		cointoss.run(100);
		System.out.println("After run(100):" );
		printResult(cointoss.getNumTrials(),cointoss.getTwoHeads(),cointoss.getTwoTails(),cointoss.getHeadTails(),cointoss.correctTest());

		//test with 1000 trials
		cointoss.run(1000);
		System.out.println("After run(1000):" );
		printResult(cointoss.getNumTrials(),cointoss.getTwoHeads(),cointoss.getTwoTails(),cointoss.getHeadTails(),cointoss.correctTest());

		//reset the trials
		cointoss.reset();
		System.out.println("After reset:" );
		printResult(cointoss.getNumTrials(),cointoss.getTwoHeads(),cointoss.getTwoTails(),cointoss.getHeadTails(),cointoss.correctTest());

		//test with 1000 trials
		cointoss.run(1000);
		System.out.println("After run(1000):" );
		printResult(cointoss.getNumTrials(),cointoss.getTwoHeads(),cointoss.getTwoTails(),cointoss.getHeadTails(),cointoss.correctTest());
		
		//test with 10 trials
		cointoss.run(10);
		System.out.println("After run(10):" );
		printResult(cointoss.getNumTrials(),cointoss.getTwoHeads(),cointoss.getTwoTails(),cointoss.getHeadTails(),cointoss.correctTest());
				
	}
	
	/* print the result of the trials
	 * @param numTrials  the number of total trials
	 * @param twoHeads  the number two heads
	 * @param headTails  the number of one head and one tail
	 * @param twoTails  the number of two tails
	 * @param correct  whether the trials is correct
	 */
	private static void printResult(int numTrials, int twoHeads, int twoTails, int headTails, boolean correct){
		System.out.println("Number of trials: " + numTrials);
		System.out.println("Two-head tosses: " + twoHeads);
		System.out.println("Two-tail tosses: " + twoTails);
		System.out.println("One-head one-tail tosses: " + headTails);
		System.out.println("Tosses add up correctly? " + correct);
		System.out.println();
	}
}
