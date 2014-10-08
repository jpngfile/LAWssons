import javax.swing.*;
import java.awt.*;

public class InputFrame extends JFrame
{
 public InputFrame (JPanel panel)
 {
   super ("Input");
   setSize (panel.getPreferredSize());
   getContentPane().add (panel);
   setVisible (true);
   requestFocus ();
   setDefaultCloseOperation (JFrame.HIDE_ON_CLOSE);
 }
}