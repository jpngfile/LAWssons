import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.GroupLayout;

/**
 * Panel for displaying textfield when creating a new lesson.
 * It includes textfield for the name and the amount of activities in the lesson.
 * 
 * @author Jason P'ng
 * @version 3.0 October 30th, 2014
 */
public class LessonInputPanel extends JPanel
{
  /**
   * The textfield for inputting the title of the lesson.
   */
  JTextField titleField;
  /**
   * The textfield for inputting the amount of activities for the lesson
   */
  JTextField numField;
  
  /**
   * The constructor of the panel for setting up the components and layout.
   */
  public LessonInputPanel ()
  {
    super ();
    setSize (50,100);
    JLabel titleLabel = new JLabel ("Lesson Title: ");
    JLabel numLabel = new JLabel ("Amount of Activities: ");
    titleField = new JTextField (10);
    numField = new JTextField (10);
    GroupLayout layout = new GroupLayout (this);
    layout.setHorizontalGroup (layout.createSequentialGroup()
                                 .addGroup (layout.createParallelGroup()
                                              .addComponent (titleLabel)
                                              .addComponent (numLabel)
                                              )
                                 .addGroup (layout.createParallelGroup()
                                              .addComponent (titleField)
                                              .addComponent (numField)
                                              )
                                 );
    layout.setVerticalGroup (layout.createSequentialGroup()
                              .addGroup (layout.createParallelGroup()
                                              .addComponent (titleLabel)
                                              .addComponent (titleField)
                                              )
                                 .addGroup (layout.createParallelGroup()
                                              .addComponent (numLabel)
                                              .addComponent (numField)
                                              )
                                 );
    setLayout (layout);
    setVisible (true);
  }
}