/* Name: Yunxi Liu
 * USC loginid: yunxiliu
 * CSCI 455 PA1
 */

// we added the import statements for you
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * Bar class
 * A labeled bar that can serve as a single bar in a bar graph.
 * The text for the label is centered under the bar.
 * 
 * NOTE: we have provided the public interface for this class. Do not change
 * the public interface. You can add private instance variables, constants,
 * and private methods to the class. You will also be completing the
 * implementation of the methods given.
 * 
 */
public class Bar {

   private int left, bottom, width, height;
   private Color color;
   private String text;
   
   /**
      Creates a labeled bar.  You give the height of the bar in application
      units (e.g., population of a particular state), and then a scale for how
      tall to display it on the screen (parameter unitsPerPixel). 
  
      @param bottom  location of the bottom of the label
      @param left  location of the left side of the bar
      @param width  width of the bar
      @param numUnits  height of the bar in application units
      @param unitsPerPixel  how many application units per pixel for height
      @param color  the color of the bar
      @param label  the label at the bottom of the bar
   */
   public Bar(int bottom, int left, int width, int numUnits, 
              double unitsPerPixel, Color color, String label) {
	   this.left = left;
	   this.width = width;
	   this.height = (int)(numUnits/unitsPerPixel);
	   this.bottom = bottom;
	   this.color = color;
	   text = label;
   }
   

   /**
      Draw the labeled bar. 
      @param g2  the graphics context
   */
   public void draw(Graphics2D g2) {
	   //draw the rectangle as bar
	   g2.setColor(color);
	   g2.fillRect(left,bottom,width,height);
	   g2.setColor(Color.BLACK);
	   
	   //get the size of text and draw it on the right location
	   Font font = g2.getFont();
	   FontRenderContext context = g2.getFontRenderContext();
	   Rectangle2D labelBounds = font.getStringBounds(text, context);
	   int widthOfLabel = (int)(labelBounds.getWidth());
	   int heightOfLabel = (int)(labelBounds.getHeight());
	   g2.drawString(text, (left + width / 2) - widthOfLabel / 2, bottom + height + heightOfLabel);
   }
}
