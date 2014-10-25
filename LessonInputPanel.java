import javax.swing.*;
import java.awt.*;

public class LessonInputPanel extends JPanel
{
  JTextField titleField;
  JTextField numField;
  public LessonInputPanel ()
  {
    super ();
    JLabel titleLabel = new JLabel ("Lesson Title: ");
    JLabel numLabel = new JLabel ("Amount of Activities: ");
    titleField = new JTextField (10);
    numField = new JTextField (10);
    setLayout (new FlowLayout ());
    add (titleLabel);
    add (titleField);
    add (numLabel);
    add(numField);
    setVisible (true);
  }
}