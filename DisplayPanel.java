import javax.swing.*;
import java.awt.*;
import javax.swing.table.*;

/**
 * The main class for displaying the status of a class in the form of a table and interacting with it.
 * @author Jason P'ng
 * @version October 4th, 2014
 */
public class DisplayPanel extends JPanel
{
  public DisplayPanel ()
  {
    //This is later determined by the lawgbook this is connected to
    JLabel titleLabel = new JLabel ("Law's Swimming class");
    //This will be taken from Lawgbook and formated with a string formatter
    JLabel dateLabel = new JLabel ("Oct 5 2014");
    //setPreferredSize (new Dimension (500,500));
    //tables start at index 1,1
    JTable table = new JTable ();///data, colNames);
    NewTableModel model = new NewTableModel ();
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
  
}