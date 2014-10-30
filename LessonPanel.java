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
    GroupLayout layout = new GroupLayout (this);

    layout.setAutoCreateGaps (true);
    layout.setAutoCreateContainerGaps (true);
    layout.setHorizontalGroup (layout.createParallelGroup ()
                                 .addComponent (classLabel)
                                 .addComponent (list)
                                 .addGroup (layout.createSequentialGroup ()
                                              .addComponent (previousButton)
                                              .addComponent (editButton)
                                              .addComponent (printButton)
                                              .addComponent (deleteButton)
                                              .addComponent (returnButton)
                                              .addComponent (nextButton)
                                              )
                               );
    layout.setVerticalGroup (layout.createSequentialGroup()
                                .addComponent (classLabel)
                                 .addComponent (list)
                                 .addGroup (layout.createParallelGroup ()
                                              .addComponent (previousButton)
                                              .addComponent (editButton)
                                              .addComponent (printButton)
                                              .addComponent (deleteButton)
                                              .addComponent (returnButton)
                                              .addComponent (nextButton)
                                              )
                               );
    setLayout (layout);
    setVisible (true);
  }
  
  public void setName (String newName)
  {
    classLabel.setText (newName);
  }
  
  public void setList (ArrayList<Activity> newList)
  {
    list.setListData (newList.toArray(new Activity [0]));
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
      Lesson l = lawgbook.getLessons().get(index);
      LessonEditPanel panel = new LessonEditPanel (l);
      int choice = JOptionPane.showConfirmDialog (this,panel,"Editing",JOptionPane.OK_CANCEL_OPTION);
      if (choice == JOptionPane.OK_OPTION){
        l.setActivities (panel.getRemoveActivities());
        setList (panel.getRemoveActivities ());
      }
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
  
private class LessonEditPanel extends JPanel implements ActionListener
{
  JComboBox<Activity> addBox;
  JComboBox<Activity> removeBox;
  public LessonEditPanel (Lesson l)
  {
    removeBox = new JComboBox<Activity> (l.getActivities().toArray (new Activity [0]));
    ArrayList<Activity> copy = new ArrayList<Activity>();
    copy.addAll(lawgbook.getActivities());
    copy = removeActivities (copy,l.getActivities());
    //copy.removeAll (l.getActivities());
    addBox = new JComboBox <Activity> (copy.toArray(new Activity [0]));
    JButton addButton = new JButton ("Add");
    JButton removeButton = new JButton ("Remove");
//    JButton cancelButton = new JButton ("Cancel");
//    JButton okButton = new JButton ("Ok");
    addButton.addActionListener (this);
    removeButton.addActionListener (this);
    GroupLayout layout = new GroupLayout (this);
    layout.setAutoCreateGaps (true);
    layout.setAutoCreateContainerGaps (true);
    layout.setHorizontalGroup (layout.createSequentialGroup ()
                                 .addGroup (layout.createParallelGroup ()
                                              .addComponent (addBox)
                                              .addComponent (removeBox))
                                 .addGroup (layout.createParallelGroup ()
                                              .addComponent (addButton)
                                              .addComponent (removeButton))
                                 );
    layout.setVerticalGroup (layout.createSequentialGroup ()
                               .addGroup (layout.createParallelGroup ()
                                            .addComponent (addBox)
                                            .addComponent (addButton))
                               .addGroup (layout.createParallelGroup ()
                                            .addComponent (removeBox)
                                            .addComponent (removeButton))
                               );
    setLayout (layout);
    setVisible (true);
  }
  
  public void actionPerformed (ActionEvent ae)
  {
   String a = ae.getActionCommand ();
   if (a.equals ("Add"))
   {
     if (addBox.getSelectedItem() != null){
     removeBox.addItem ((Activity)addBox.getSelectedItem ());
     addBox.removeItem (addBox.getSelectedItem());
     }
   }
   else if (a.equals ("Remove"))
   {
     if (removeBox.getSelectedItem () != null){
     addBox.addItem ((Activity)removeBox.getSelectedItem ());
     removeBox.removeItem (removeBox.getSelectedItem());
     }
   }
  }
  
  public ArrayList<Activity> getAddActivities ()
  {
    ArrayList<Activity> list = new ArrayList<Activity> ();
    for (int x = 0;x < addBox.getItemCount();x++)
    {
      list.add (addBox.getItemAt (x));
    }
    return list;
  }
  
  public ArrayList<Activity> getRemoveActivities ()
  {
    ArrayList<Activity> list = new ArrayList<Activity> ();
    for (int x = 0;x < removeBox.getItemCount();x++)
    {
      list.add (removeBox.getItemAt (x));
    }
    return list;
  }
  public ArrayList<Activity> removeActivities (ArrayList<Activity> original, ArrayList<Activity> remove)
  {
    for (int x = 0;x < original.size();x++)
    {
      Activity a = original.get(x);
      for (int y = 0;y < remove.size();y++)
      {
        Activity b = remove.get (y);
        if (a.equals (b)){
          original.remove (a);
          x--;
          break;
        }
      }
    }
    return original;
  }
}
}