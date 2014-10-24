import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class LessonPanel extends JPanel implements ActionListener
{
  JFrame parent;
  JLabel classLabel;
  JList<String> list;
  public LessonPanel (JFrame parent)
  {
    this.parent = parent;
    setSize (parent.getSize());
    classLabel = new JLabel ("Class");
    list = new JList<String>(new String []{"Activity1","Activity2","Activity3","Activity3","Activity5"});
    JButton previousButton = new JButton ("Previous");
    JButton editButton = new JButton ("Edit");
    JButton printButton = new JButton ("Print");
    JButton deleteButton = new JButton ("Delete");
    JButton returnButton = new JButton ("Return");
    JButton nextButton = new JButton ("Next");
    add (classLabel,BorderLayout.NORTH);
    add (list,BorderLayout.CENTER);
    add (previousButton,BorderLayout.SOUTH);
    add (editButton,BorderLayout.SOUTH);
    add (printButton,BorderLayout.SOUTH);
    add (deleteButton,BorderLayout.SOUTH);
    add (nextButton,BorderLayout.SOUTH);
    setVisible (true);
  }
  
  public void setName (String newName)
  {
    classLabel.setText (newName);
  }
  
  public void setList (ArrayList<Activity> newList)
  {
    //list.setListData (newList.toArray());
  }
  
  public void actionPerformed (ActionEvent ae)
  {
    String a = ae.getActionCommand ();
    if (a.equals ("Previous"))
    {
      
    }
    else if (a.equals ("Edit"))
    {
      
    }
    else if (a.equals ("Delete"))
    {
      
    }
    else if (a.equals ("Print"))
    {
      
    }
    else if (a.equals ("Return"))
    {
      
    }
    else if (a.equals ("Next"))
    {
      
    }
  }
}