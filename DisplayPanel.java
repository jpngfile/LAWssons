import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;
import java.awt.event.*;

/**
 * The main class for displaying the status of a class in the form of a table and interacting with it.
 * @author Jason P'ng
 * @version October 4th, 2014
 */
public class DisplayPanel extends JPanel implements ActionListener
{
  NewTableModel model;
  JTable table;
  Lawgbook book = new Lawgbook ();
  public DisplayPanel ()
  {
    //This is later determined by the lawgbook this is connected to
    JLabel titleLabel = new JLabel ("Law's Swimming class");
    //This will be taken from Lawgbook and formated with a string formatter. Will have to update later
    JLabel dateLabel = new JLabel (book.formatDate);//"Oct 5 2014");
    //setPreferredSize (new Dimension (500,500));
    //tables start at index 1,1
    table = new JTable ();///data, colNames);
    model = new NewTableModel ();
    table.setModel (model);
    //Is this needed?
    JScrollPane scrollpane = new JScrollPane (table);
    table.setFillsViewportHeight (true);
    
    JButton addStudentButton = new JButton ("Add student");
    JButton addActivityButton = new JButton ("Add activity");
    JButton printButton = new JButton ("Print");
    JButton changeDateButton = new JButton ("Change date");
    JButton makeLessonButton = new JButton ("Make lesson");
    JButton seeLessonButton = new JButton ("See lessons");
    JButton updateButton = new JButton ("Update");
    
    addStudentButton.addActionListener (this);
    addActivityButton.addActionListener (this);
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
                                            .addComponent (changeDateButton)
                                            .addComponent (makeLessonButton)
                                            .addComponent (seeLessonButton)
                                            .addComponent (printButton))
                            );
    
    
    setLayout (layout);
    //add (scrollpane);
    setVisible (true);
  }
  //set up a table and some buttons here
  
  public void actionPerformed (ActionEvent ae)
  {
   String a = ae.getActionCommand ();
   if (a.equals ("Add student"))
   {
     String studentName = JOptionPane.showInputDialog (this, "Enter the name of the student.");
     if (studentName != null){
     model.addColumn (studentName);
     System.out.println ("Added student");
     table.addColumn (new TableColumn(model.getColumnCount() - 1));
     repaint ();
     }
   }
   else if (a.equals ("Add activity"))
   {
     String activityName = JOptionPane.showInputDialog (this, "Enter the name of the activity.");
     if (activityName != null){
     model.addActivity (activityName);
     System.out.println ("Added activity");
     repaint ();}
   }
   else if (a.equals ("Print"))
   {
     
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
  
}