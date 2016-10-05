/* Name: Yunxi Liu
 * USC loginid: yunxiliu
 * CSCI 455 PA1
 */

import java.util.Scanner;
import javax.swing.JFrame;

//graphic interface to view the result of trials
public class CoinSimViewer {
	public static void main(String[] args){
		
		Scanner in = new Scanner(System.in);
		CoinTossSimulator cointoss = new CoinTossSimulator();
		
		//create a new frame
		JFrame frame = new JFrame("CoinSim");
		int frameWidth = 800;
		int frameHeight = 500;
		frame.setSize(frameWidth, frameHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		//keep running the trial
		while(true){
			
			System.out.print("Continue or reset? (c/r): ");
			String ch = in.next();
			if(ch.equals("r")){
				cointoss.reset();
				frame.getContentPane().removeAll();
			}
			else if(!ch.equals("c")){
				System.out.println("ERROR: Please input 'c' or 'r'.");
				continue;
			}
			
			System.out.print("Enter number of trials: ");
			int num = in.nextInt();
			
			//error-checking of input number
			if(num <= 0){
				System.out.println("ERROR: Number entered must be greater than 0.");
				continue;
			}
			cointoss.run(num);
			showResult(frame,cointoss.getNumTrials(),cointoss.getTwoHeads(), 
				cointoss.getHeadTails(),cointoss.getTwoTails());
		}
	}
	
	/* add component to the frame
	 * @param frame  the frame the interface is shown on
	 * @param numTrials  the number of total trials
	 * @param twoHeads  the number two heads
	 * @param headTails  the number of one head and one tail
	 * @param twoTails  the number of two tails
	 */
	private static void showResult(JFrame frame, int numTrials, int twoHeads, int headTails, int twoTails){
		frame.getContentPane().removeAll();
		CoinSimComponent component = new CoinSimComponent(numTrials, twoHeads, headTails, twoTails);
		frame.add(component);
		frame.setVisible(true);
	}
}
