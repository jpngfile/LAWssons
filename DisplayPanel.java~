import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.util.*;
import java.awt.print.PrinterException;

/**
 * The main class for displaying the status of a class in the form of a table and interacting with it.
 * @author Jason P'ng
 * @version October 4th, 2014
 */
public class DisplayPanel extends JPanel implements ActionListener
{
  NewTableModel model;
  JTable table;
  JLabel titleLabel;
  public DisplayPanel ()
  {
    //tables start at index 1,1
    model = new NewTableModel ();
    table = new JTable (model);
    //This will be taken from Lawgbook and formated with a string formatter. Will have to update later
    JLabel dateLabel = new JLabel (model.getLawgbook().getFormatDate());
     //This is later determined by the lawgbook this is connected to
    titleLabel = new JLabel (model.getLawgbook().getTitle());
    //Is this needed?
    JScrollPane scrollpane = new JScrollPane (table);
    table.setFillsViewportHeight (true);
    
    //may have to redesign for the removal buttons
    JButton addStudentButton = new JButton ("Add student");
    JButton addActivityButton = new JButton ("Add activity");
    JButton removeStudentButton = new JButton ("Remove Student");
    JButton removeActivityButton = new JButton ("Remove Activity");
    JButton printButton = new JButton ("Print");
    //JButton changeDateButton = new JButton ("Change date");
    JButton makeLessonButton = new JButton ("Make lesson");
    JButton seeLessonButton = new JButton ("See lessons");
    JButton updateButton = new JButton ("Update");
    
    addStudentButton.addActionListener (this);
    addActivityButton.addActionListener (this);
    removeStudentButton.addActionListener (this);
    removeActivityButton.addActionListener (this);
    printButton.addActionListener (this);
    //changeDateButton.addActionListener (this);
    makeLessonButton.addActionListener (this);
    seeLessonButton.addActionListener (this);
    updateButton.addActionListener (this);
    GroupLayout layout = new GroupLayout (this);
    layout.setAutoCreateGaps (true);
    layout.setAutoCreateContainerGaps (true);
    layout.setHorizontalGroup (layout.createParallelGroup ()
                                 .addComponent (titleLabel)
                                 .addComponent (dateLabel)
                                 .addComponent (scrollpane)
                                 .addGroup (layout.createSequentialGroup ()
                                              .addComponent (updateButton)
                                              .addComponent (addStudentButton)
                                              .addComponent (addActivityButton)
                                              
                                              .addComponent (removeStudentButton)
                                              .addComponent (removeActivityButton)
                                              
                                              //.addComponent (changeDateButton)
                                              .addComponent (makeLessonButton)
                                              .addComponent (seeLessonButton)
                                              .addComponent (printButton))
                              );
    layout.setVerticalGroup (layout.createSequentialGroup ()
                               .addComponent (titleLabel)
                               .addComponent (dateLabel)
                               .addComponent (scrollpane)
                               .addGroup (layout.createParallelGroup ()
                                            .addComponent (updateButton)
                                            .addComponent (addStudentButton)
                                            .addComponent (addActivityButton)
                                            
                                            .addComponent (removeStudentButton)
                                            .addComponent (removeActivityButton)
                                            
                                            //.addComponent (changeDateButton)
                                            .addComponent (makeLessonButton)
                                            .addComponent (seeLessonButton)
                                            .addComponent (printButton))
                            );   
    setLayout (layout);
    //add (scrollpane);
    setVisible (true);
    
  }
  
  public void actionPerformed (ActionEvent ae)
  {
    String a = ae.getActionCommand ();
    if (a.equals ("Add student"))
    {
      //What does this return if the textfield is empty?
      String studentName = JOptionPane.showInputDialog (this, "Enter the name of the student.");
      if (studentName != null && studentName.length() > 0){
        //System.out.println (studentName);
        model.addColumn (studentName);
        //System.out.println ("Added student");
        table.addColumn (new TableColumn(model.getColumnCount() - 1));
        repaint ();
      }
    }
    else if (a.equals ("Add activity"))
    {
      String activityName = JOptionPane.showInputDialog (this, "Enter the name of the activity.");
      if (activityName != null && activityName.length() > 0){
        model.addActivity (activityName);
        //System.out.println ("Added activity");
        repaint ();}
    }
    else if (a.equals ("Remove Student"))
    {
      //prevent the removal of essential columns
      String studentName = JOptionPane.showInputDialog (this, "Enter the name of the student.");
      //Needs to be restricted so that only valid students are deleted
      if (studentName != null && studentName.length() > 0){    
        //Note: tables assume the use of arrays, so removing a column will not update it dynamically, it just skips that index
        String name = (model.getColumnName(model.getColumnCount() - 1));
        //Make sure removal was successful first
        if (model.removeStudent (studentName)){
          table.removeColumn (table.getColumn (name));     
          refreshHeaders();
        }
      }     
      repaint ();
    }
    else if (a.equals ("Remove Activity"))
    {
      String activityName = JOptionPane.showInputDialog (this, "Enter the name of the activity.");
      if (activityName != null && activityName.length() > 0){
        model.removeActivity (activityName);
        //System.out.println ("removed activity : " + activityName);
        repaint ();} 
    }
    else if (a.equals ("Print"))
    {
      try
      {
        table.print ();
      }
      catch (PrinterException e)
      {
      }
    }
//    else if (a.equals ("Change date"))
//    {
//    }
    else if (a.equals ("Make lesson"))
    {
      LessonInputPanel panel = new LessonInputPanel ();
      int choice = JOptionPane.showConfirmDialog (this,panel,"New Lesson Input",JOptionPane.OK_CANCEL_OPTION);
      if (choice == JOptionPane.OK_OPTION){
        try{
          String title = panel.titleField.getText();
          int num = Integer.parseInt (panel.numField.getText());
          ArrayList<Activity> list = new ArrayList<Activity>();
          list.addAll (getLawgbook().sorted.subList (0,num));
          getLawgbook().addLesson (new Lesson (title,new Date (System.currentTimeMillis()),list));
          JOptionPane.showMessageDialog (this,"You've successfuly made a lesson.","Lesson Created",JOptionPane.INFORMATION_MESSAGE);
        }
        catch (NumberFormatException e)
        {
          JOptionPane.showMessageDialog (this,"Input is invalid.","Input error",JOptionPane.ERROR_MESSAGE);
        }
        catch (IndexOutOfBoundsException ie)
        {
          JOptionPane.showMessageDialog (this,"Number of activities cannot exceed total number of activities.","Input error",JOptionPane.ERROR_MESSAGE);
        }
      }
    }
    else if (a.equals ("See lessons"))
    {
      ActionListener al = ((ActionListener)getParent().getParent().getParent().getParent().getParent());
      al.actionPerformed (new ActionEvent (al,0,"See lessons"));
    }
    else if (a.equals ("Update"))
    {      
    }
  }
  
  public Lawgbook getLawgbook ()
  {
    return model.getLawgbook ();
  }
  
  public void refreshHeaders ()
  {
    for (int x = 0;x < model.getColumnCount();x++)
    {
      table.getTableHeader().getColumnModel().getColumn(x).setHeaderValue (model.getColumnName(x));
      //System.out.println ("Index " + x + ": " + table.getTableHeader().getColumnModel().getColumn(x).getHeaderValue ());
    }
  }
  
  public void refreshData ()
  {
    int newColumnCount = (getLawgbook().getNumStudents() + 3);
    int columnCount = model.getColumnCount ();
    for (int x = columnCount;x < newColumnCount;x++)
    {
      model.addColumn (getLawgbook().getStudent (x - 3).getName(),false);
      table.addColumn (new TableColumn (model.getColumnCount() - 1));      
    }
    //Put in code for removing columns as well
    for (int x = columnCount;x > newColumnCount;x--)
    {
      String name = model.removeColumn (model.getColumnName (x - 1));
      table.removeColumn (table.getColumn (name));
    }
    model.setRowCount (getLawgbook().getNumActivities());
    titleLabel.setText (getLawgbook().getTitle());
    table.updateUI ();
  }
}