import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;
import java.awt.event.*;
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
  public DisplayPanel ()
  {
    //This is later determined by the lawgbook this is connected to
    JLabel titleLabel = new JLabel ("Law's Swimming class");
    
    //setPreferredSize (new Dimension (500,500));
    //tables start at index 1,1
    model = new NewTableModel ();
    table = new JTable (model);
    //This will be taken from Lawgbook and formated with a string formatter. Will have to update later
    JLabel dateLabel = new JLabel (model.getLawgbook().formatDate);//"Oct 5 2014");
    //table.setModel (model);
    //Is this needed?
    JScrollPane scrollpane = new JScrollPane (table);
    table.setFillsViewportHeight (true);
    
    //may have to redesign for the removal buttons
    JButton addStudentButton = new JButton ("Add student");
    JButton addActivityButton = new JButton ("Add activity");
    JButton removeStudentButton = new JButton ("Remove Student");
    JButton removeActivityButton = new JButton ("Remove Activity");
    JButton printButton = new JButton ("Print");
    JButton changeDateButton = new JButton ("Change date");
    JButton makeLessonButton = new JButton ("Make lesson");
    JButton seeLessonButton = new JButton ("See lessons");
    JButton updateButton = new JButton ("Update");
    
    addStudentButton.addActionListener (this);
    addActivityButton.addActionListener (this);
   removeStudentButton.addActionListener (this);
    removeActivityButton.addActionListener (this);
    printButton.addActionListener (this);
    changeDateButton.addActionListener (this);
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
                                              
                                              .addComponent (changeDateButton)
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
                                            
                                            .addComponent (changeDateButton)
                                            .addComponent (makeLessonButton)
                                            .addComponent (seeLessonButton)
                                            .addComponent (printButton))
                            );
    
    
    setLayout (layout);
    //add (scrollpane);
    setVisible (true);
    refreshHeaders();
  }
  //set up a table and some buttons here
  
  public void actionPerformed (ActionEvent ae)
  {
   String a = ae.getActionCommand ();
   if (a.equals ("Add student"))
   {
     //What does this return if the textfield is empty?
     String studentName = JOptionPane.showInputDialog (this, "Enter the name of the student.");
     if (studentName != null && studentName.length() > 0){
     System.out.println (studentName);
     model.addColumn (studentName);
     System.out.println ("Added student");
     table.addColumn (new TableColumn(model.getColumnCount() - 1));
     repaint ();
     }
   }
   else if (a.equals ("Add activity"))
   {
     String activityName = JOptionPane.showInputDialog (this, "Enter the name of the activity.");
     if (activityName != null && activityName.length() > 0){
     model.addActivity (activityName);
     System.out.println ("Added activity");
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
     System.out.println ("removed activity : " + activityName);
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
   else if (a.equals ("Change date"))
   {
   }
   else if (a.equals ("Make lesson"))
   {
     
   }
   else if (a.equals ("See lessons"))
   {
   }
   else if (a.equals ("Update"))
   {
     
   }
  }
  
  public void refreshHeaders ()
  {
    for (int x = 0;x < model.getColumnCount();x++)
    {
      table.getTableHeader().getColumnModel().getColumn(x).setHeaderValue (model.getColumnName(x));
      System.out.println ("Index " + x + ": " + table.getTableHeader().getColumnModel().getColumn(x).getHeaderValue ());
    }
  }
  
}