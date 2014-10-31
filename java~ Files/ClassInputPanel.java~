import javax.swing.*;

public class ClassInputPanel extends JPanel
{
  JTextField nameField;
  JTextField weekField;
  JTextField activityField;
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