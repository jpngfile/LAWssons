import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
/**
 * Intrductory screen when the program starts before any lawgbook has been opened.
 * @author Jason P'ng
 * @version 3.0 October 30th, 2014
 */
public class IntroPanel extends JPanel
{
  /**
   * The image to cover the panel.
   */
  BufferedImage img;
  
  /**
   * Constructor to load the panel image.
   */
 public IntroPanel ()
 {
   setPreferredSize (new Dimension (700,400));
   setVisible (true);
   try
   {
    img = ImageIO.read (getClass().getResourceAsStream ("IntroPage.png")); 
   }
   catch (IOException e)
   {
   }
 }
  
 /**
  * Overriden paint method to paint the image onto the panel.
  * 
  * @param g Graphics used by component to paint image.
  */
 @Override
 public void paintComponent (Graphics g)
 {
   super.paintComponent (g);
   g.drawImage (img,0,0,null);
 }
}