import javax.swing.*;
import java.awt.*;

public class LessonInputPanel extends JPanel
{
  JTextField titleField;
  JTextField numField;
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