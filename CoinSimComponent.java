/* Name: Yunxi Liu
 * USC loginid: yunxiliu
 * CSCI 455 PA1
 */

import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/* paint component on the graphic frame
 * add bar on the component
 */
public class CoinSimComponent extends JComponent{
	
	final static int BAR_WIDTH = 60;//bar width
	final static double BOTTOM_RATIO = 6.0/7.0;//the location of bottom of the bars in a frame
	final static double BAR_RATIO = 5.8/7.0;//the height percent of the longest bar in a frame
	final static int BAR_NUM = 3;//number of bars
	
	private double ratio1;//number of two heads/number of total trials
	private double ratio2;//number of one head one tail/number of total trials
	private double ratio3;//number of two tails/number of total trials
	private int twoHeads;
	private int twoTails;
	private int headTails;
	
	/* constructor to pass trial result to the coin simulator component
	 * @param total  the number of total trials
       @param val1  the number of two heads
       @param val2  the number of one head and one tail
       @param val3  the number of two tails
	 */
	public CoinSimComponent(int total, int val1, int val2, int val3){
		ratio1 = (double)val1 / total;
		ratio2 = (double)val2 / total;
		ratio3 = (double)val3 / total;
		this.twoHeads = val1;
		this.headTails = val2;
		this.twoTails = val3;
		
	}
	
	/*call back function to draw component on the frame
	 * get called when the window gets resized
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g)
	   {
		
	      Graphics2D g2 = (Graphics2D) g;
	      Bar bar1 = new Bar((int)(getHeight() * (BOTTOM_RATIO - BAR_RATIO * ratio1)), (getWidth() - BAR_NUM * BAR_WIDTH) / (BAR_NUM + 1), BAR_WIDTH, 
	    		  (int)(getHeight() * BAR_RATIO * ratio1), 1, Color.RED, "Two Heads: " + twoHeads + "(" + (int)Math.round(ratio1 * 100) + "%)");
	      Bar bar2 = new Bar((int)(getHeight() * (BOTTOM_RATIO - BAR_RATIO * ratio2)), (getWidth() - BAR_NUM * BAR_WIDTH) / (BAR_NUM + 1) * 2 + BAR_WIDTH, BAR_WIDTH, 
	    		  (int)(getHeight() * BAR_RATIO * ratio2), 1, Color.GREEN, "A Head and a Tail: " + headTails + "(" + (int)Math.round(ratio2 * 100) + "%)");
	      Bar bar3 = new Bar((int)(getHeight() * (BOTTOM_RATIO - BAR_RATIO * ratio3)), (getWidth() - BAR_NUM * BAR_WIDTH) / (BAR_NUM + 1) * BAR_NUM + BAR_WIDTH * 2, BAR_WIDTH, 
	    		  (int)(getHeight() * BAR_RATIO * ratio3), 1, Color.BLUE, "Two Tails: " + twoTails + "(" + (100 - (int)Math.round(ratio1 * 100) - (int)Math.round(ratio2 * 100)) + "%)");
	      bar1.draw(g2);
	      bar2.draw(g2);
	      bar3.draw(g2);
	    		
	   }
}
