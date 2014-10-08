import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class AddItemPanel extends JPanel implements ActionListener
{
  //Add static list of words to not make doubles of
  public AddItemPanel (String itemName)
  {
    setPreferredSize (new Dimension (200,100));
    JLabel promptLabel = new JLabel ("Enter the name of the new " + itemName + ".");
    JTextField inputField = new JTextField ();
    JButton enterButton = new JButton ("Enter");
    enterButton.addActionListener (this);
    add (promptLabel);
    add (inputField);
    add (enterButton);
    setVisible (true);
  }
  
  public void actionPerformed (ActionEvent ae)
  {
    
  }
}