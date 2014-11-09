import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.GroupLayout;

/**
 * An input panel for displaying textfields for creating a new class.
 * It includes fields for the class name, total number of weeks, and the minimum amount of times each activity must be taught.
 * 
 * @author Jason P'ng
 * @version 3.0 October 30th, 2014
 */
public class ClassInputPanel extends JPanel
{
  /**
   * Textfield for the name of the class
   */
  JTextField nameField;
  /**
   * Textfield for the total amount of weeks this class will run.
   */
  JTextField weekField;
  /**
   * Textfield for the minimum amount of times each activity must run during the class.
   */
  JTextField activityField;
  /**
   * Constructor for setting up the panel with all the formatting and labels.
   */
  public ClassInputPanel ()
  {
    JLabel nameLabel = new JLabel ("Class Name: ");
    JLabel weekLabel = new JLabel ("Number of weeks: ");
    JLabel activityLabel = new JLabel ("Min. times for each activity: ");
     nameField = new JTextField (15);
     weekField = new JTextField (15);
     activityField = new JTextField (15);
    GroupLayout layout = new GroupLayout (this);
    layout.setAutoCreateGaps (true);
    layout.setAutoCreateContainerGaps (true);
    layout.setHorizontalGroup (layout.createSequentialGroup ()
                                 .addGroup (layout.createParallelGroup()
                                 .addComponent (nameLabel)
                                 .addComponent (weekLabel)
                                 .addComponent (activityLabel))
                                 .addGroup (layout.createParallelGroup ()
                                              .addComponent (nameField)
                                              .addComponent (weekField)
                                              .addComponent (activityField))
                                 );
    layout.setVerticalGroup (layout.createSequentialGroup ()
                               .addGroup (layout.createParallelGroup ()
                                            .addComponent (nameLabel)
                                            .addComponent (nameField))
                               .addGroup (layout.createParallelGroup ()
                                            .addComponent (weekLabel)
                                            .addComponent (weekField))
                               .addGroup (layout.createParallelGroup ()
                                            .addComponent (activityLabel)
                                            .addComponent (activityField))
                               );
    setLayout (layout);
    setVisible (true);
  }
}