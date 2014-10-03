import javax.swing.*;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.*;
import java.io.*;
/**
 * Placeholder panel until the JTable is set up
 * @author Jason P'ng
 */
public class IntroPanel extends JPanel
{
  BufferedImage img;
 public IntroPanel ()
 {
   setPreferredSize (new Dimension (500,500));
   setVisible (true);
   try
   {
    img = ImageIO.read (getClass().getResourceAsStream ("IntroPage.png")); 
   }
   catch (IOException e)
   {
   }
 }
  
 public void paintComponent (Graphics g)
 {
   super.paintComponent (g);
   g.drawImage (img,0,0,null);
 }
}