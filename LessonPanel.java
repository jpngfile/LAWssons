import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class LessonPanel extends JPanel implements ActionListener
{
  JFrame parent;
  JLabel classLabel;
  JList<Activity> list;
  Lawgbook lawgbook;
  int index = 0;
  public LessonPanel (JFrame parent,Lawgbook l)
  {
    super ();
    lawgbook = l;
    //Activity [] aList = (Activity[])l.getLessons().get(index).getActivities().toArray();
    this.parent = parent;
    setSize (parent.getSize());
    classLabel = new JLabel ("Class");
    
    list = new JList<Activity>(new Activity[0]);
    JButton previousButton = new JButton ("Previous");
    JButton editButton = new JButton ("Edit");
    JButton printButton = new JButton ("Print");
    JButton deleteButton = new JButton ("Delete");
    JButton returnButton = new JButton ("Return");
    JButton nextButton = new JButton ("Next");
    previousButton.addActionListener (this);
    returnButton.addActionListener (this);//perhaps I could attach this to the program frame instead
    editButton.addActionListener (this);
    printButton.addActionListener (this);
    deleteButton.addActionListener (this);
    nextButton.addActionListener (this);
    add (classLabel,BorderLayout.NORTH);
    add (list,BorderLayout.CENTER);
    add (previousButton,BorderLayout.SOUTH);
    add (editButton,BorderLayout.SOUTH);
    add (returnButton,BorderLayout.SOUTH);
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
      index--;
      if (index  < 0){
       index = lawgbook.getNumLessons() - 1; 
      }
      list.setListData ((Activity [])(lawgbook.getLessons().get(index).getActivities().toArray(new Activity [0])));
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
      ActionListener al = ((ActionListener)getParent().getParent().getParent().getParent().getParent());
      al.actionPerformed (new ActionEvent (al,0,"Return"));
    }
    else if (a.equals ("Next"))
    {
      index++;
      if (index >= lawgbook.getNumLessons()){
       index = 0; 
      }
      list.setListData ((Activity [])(lawgbook.getLessons().get(index).getActivities().toArray(new Activity [0])));
    }
  }
  
  public int getIndex ()
  {
    return index;
  }
  
  public void setData (int index)
  {
    Activity [] aList = (Activity [])(lawgbook.getLessons().get(index).getActivities().toArray(new Activity [0]));
   list.setListData (aList);
  }
}