import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import java.sql.Date;

/**
 * The main class for displaying the status of a class in the form of a table and interacting with it.
 * @author Jason P'ng
 * @version October 4th, 2014
 */
public class DisplayPanel extends JPanel implements ActionListener
{
  /**
   * The data model that accesses the lawgbook to then provide the appropriate data to the table.
   */
  NewTableModel model;
  /**
   * The table that displays the data about the class.
   */
  JTable table;
  /**
   * Label to display the name of the class.
   */
  JLabel titleLabel;
  /**
   * Label to display how many weeks are in the class left.
   */
  JLabel weekLabel;
  /**
   * Background image for the display.
   */
  BufferedImage img;
  /**
   * The constructor of the panel to set up the table,buttons, and all formatting.
   */
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
    weekLabel = new JLabel ("Week " + model.getLawgbook().getWeeksPassed() + "/" + model.getLawgbook().getTotalWeeks());
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
    JButton editActivityButton = new JButton ("Edit Activity");
    
    addStudentButton.addActionListener (this);
    addActivityButton.addActionListener (this);
    removeStudentButton.addActionListener (this);
    removeActivityButton.addActionListener (this);
    printButton.addActionListener (this);
    //changeDateButton.addActionListener (this);
    makeLessonButton.addActionListener (this);
    seeLessonButton.addActionListener (this);
    updateButton.addActionListener (this);
    editActivityButton.addActionListener (this);
    GroupLayout layout = new GroupLayout (this);
    layout.setAutoCreateGaps (true);
    layout.setAutoCreateContainerGaps (true);
    layout.setHorizontalGroup (layout.createParallelGroup ()
                                 .addComponent (titleLabel)
                                 .addComponent (dateLabel)
                                 .addComponent (weekLabel)
                                 .addComponent (scrollpane)
                                 .addGroup (layout.createSequentialGroup ()
                                              .addComponent (updateButton)
                                              .addComponent (addStudentButton)
                                              .addComponent (addActivityButton)
                                              
                                              .addComponent (removeStudentButton)
                                              .addComponent (removeActivityButton)
                                              .addComponent (editActivityButton)
                                              //.addComponent (changeDateButton)
                                              .addComponent (makeLessonButton)
                                              .addComponent (seeLessonButton)
                                              .addComponent (printButton))
                              );
    layout.setVerticalGroup (layout.createSequentialGroup ()
                               .addComponent (titleLabel)
                               .addComponent (dateLabel)
                               .addComponent (weekLabel)
                               .addComponent (scrollpane)
                               .addGroup (layout.createParallelGroup ()
                                            .addComponent (updateButton)
                                            .addComponent (addStudentButton)
                                            .addComponent (addActivityButton)
                                            
                                            .addComponent (removeStudentButton)
                                            .addComponent (removeActivityButton)
                                            .addComponent (editActivityButton)
                                            //.addComponent (changeDateButton)
                                            .addComponent (makeLessonButton)
                                            .addComponent (seeLessonButton)
                                            .addComponent (printButton))
                            );   
    setLayout (layout);
    //add (scrollpane);
    try
   {
    img = ImageIO.read (getClass().getResourceAsStream ("Background.png")); 
   }
   catch (IOException e)
   {
   }
    setVisible (true);
    
  }
  
  /**
   * Implementation for actionListener to connect buttons with actions.
   *
   * @param ae The command that triggers this method to know which button was pressed.
   */
  public void actionPerformed (ActionEvent ae)
  {
    String a = ae.getActionCommand ();
    if (a.equals ("Add student"))
    {
      //What does this return if the textfield is empty?
      String studentName = JOptionPane.showInputDialog (this, "Enter the name of the student.");
      if (studentName != null && studentName.length() > 0){
        model.addColumn (studentName);
        table.addColumn (new TableColumn(model.getColumnCount() - 1));
        repaint ();
      }
    }
    else if (a.equals ("Add activity"))
    {
      String activityName = JOptionPane.showInputDialog (this, "Enter the name of the activity.");
      if (activityName != null && activityName.length() > 0){
        model.addActivity (activityName);
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
    else if (a.equals ("Edit Activity"))
    {
      ActivityEditPanel panel = new ActivityEditPanel ();
      int choice = JOptionPane.showConfirmDialog (this, panel, "Edit Activity",JOptionPane.OK_CANCEL_OPTION);
      if (choice == JOptionPane.OK_OPTION)
      {
        panel.refreshStatus();//save the current activity open
        for (int x = 0;x < getLawgbook().getNumActivities();x++)
        {
          Activity ac = getLawgbook().getActivities().get(x);
          ac.setTime (panel.times [x]);
          JComboBox s = panel.itemBoxes.get(x);
          ArrayList<String> i = new ArrayList<String>();
          for (int y = 0;y < s.getItemCount();y++)
          {
            i.add ((String)s.getItemAt (y));
          }
          ac.setItems (i);
        }
      }
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
          ArrayList<Activity> list = new ArrayList<Activity>();
          int currentTime = 0;
          for (int x = 0;x < getLawgbook().getNumActivities();x++){
          currentTime += getLawgbook().sorted.get (x).getTime();
          if (currentTime > getLawgbook().getLessonTime()){
            break;
          }
          list.add (getLawgbook().sorted.get(x));
          }
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
      LessonUpdatePanel panel = new LessonUpdatePanel ();
      int choice = JOptionPane.showConfirmDialog (this,panel,"Update",JOptionPane.OK_CANCEL_OPTION);
      if (choice == JOptionPane.OK_OPTION){
        ArrayList<Activity> list = panel.getItem().getActivities();
        for (Activity c : getLawgbook().getActivities()){
          for (Activity b : list){
            if (c.equals (b)){
              c.setCompleted (c.getCompleted() + 1);
              break;
            }
          }
        }
        getLawgbook().setWeeksPassed(getLawgbook().getWeeksPassed() + 1);
        if (getLawgbook().getWeeksPassed() == getLawgbook().getTotalWeeks()){
          JOptionPane.showMessageDialog (this,"This class has finished.",getLawgbook().getWeeksPassed() + " weeks passed",JOptionPane.INFORMATION_MESSAGE);
        }
        refreshData ();
      }
    }
  }
  
  /**
   * Returns the lawgbook that the tablemodel is using.
   * 
   * @return the lawgbook that the tablemodel uses.
   */
  public Lawgbook getLawgbook ()
  {
    return model.getLawgbook ();
  }
  
  /**
   * Refreshes the headers of the columns to match the data. This is called when the number of columns is changed.
   */
  public void refreshHeaders ()
  {
    for (int x = 0;x < model.getColumnCount();x++)
    {
      table.getTableHeader().getColumnModel().getColumn(x).setHeaderValue (model.getColumnName(x));
    }
  }
  
  /**
   * Refreshes all the data in the table to match the lawgbook. This is called when new data is read in from file.
   */
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
    weekLabel.setText ("Week " + getLawgbook().getWeeksPassed() + "/" + getLawgbook().getTotalWeeks());
    table.updateUI ();
  }
  
  /**
   * An input panel for choosing the lesson to update the data with.
   */
  private class LessonUpdatePanel extends JPanel
  {
    /**
     * The combobox in which lessons can be selected from.
     */
    JComboBox<Lesson> lessonBox;
    /**
     * The constructor for setting up the components and formatting.
     */
   public LessonUpdatePanel ()
      {
      lessonBox = new JComboBox<Lesson>(getLawgbook().getLessons().toArray(new Lesson [0]));
      add (new JLabel ("Lesson: "));
      add (lessonBox,BorderLayout.CENTER);
      setVisible (true);
      }
   
   /**
    * Returns the lesson that is currently  being selected.
    *
    * @return The lesson selected in the <code>JComboBox</code>.
    */
   public Lesson getItem ()
   {
     return (Lesson)lessonBox.getSelectedItem ();
   }
  }
  
  @Override
  public void paintComponent (Graphics g)
  {
   super.paintComponent (g);
   g.drawImage (img,0,0,null);
  }
  
  /**
   * Panel for interface when editing activities for time and equipment.
   */
  private class ActivityEditPanel extends JPanel implements ItemListener,ActionListener
  {
    ArrayList<JComboBox> itemBoxes;
    JComboBox<Activity> aBox;
    JComboBox itemBox;
    int [] times;
    int aNum = 0;
    String [] tempItems;
    JTextField itemField = new JTextField (10);
    JTextField timeField = new JTextField (10);
    public ActivityEditPanel ()
    {
      //Initialize all components
      aBox = new JComboBox <Activity> (getLawgbook().getActivities().toArray (new Activity[0]));
      itemBoxes = new ArrayList<JComboBox>();
      times = new int [getLawgbook().getNumActivities()];
      tempItems = new String [getLawgbook().getNumActivities()];
      int index = 0;
      for (Activity a : getLawgbook().getActivities())
      {
        try{
        itemBoxes.add (new JComboBox<String> (a.getItems().toArray (new String [0])));
        }
        catch (NullPointerException e)
        {
          itemBoxes.add (new JComboBox<String>());
        }
        times [index] = a.getTime();
        index++;        
      }
      itemBox = new JComboBox<String> (itemBoxes.get(0).getModel());
      timeField.setText (Integer.toString (times [0]));
      //Create additional components
      JLabel subLabel = new JLabel ("Equipment");
      JLabel timeLabel = new JLabel ("Time");
      JButton addButton = new JButton ("Add");
      JButton removeButton = new JButton ("Remove");
      JButton setButton = new JButton ("Set");
      
      addButton.addActionListener (this);
      removeButton.addActionListener (this);
      setButton.addActionListener (this);
      aBox.addItemListener (this);
      //use layout manager to organize components
      GroupLayout layout = new GroupLayout (this);
      layout.setAutoCreateContainerGaps (true);
      layout.setAutoCreateGaps (true);
      layout.setHorizontalGroup (layout.createParallelGroup()
                                   .addComponent (aBox)
                                   .addComponent (subLabel)
                                   .addGroup (layout.createSequentialGroup ()
                                                .addComponent (itemField)
                                                .addComponent (addButton))
                                   .addGroup (layout.createSequentialGroup()
                                                .addComponent (itemBox)
                                                .addComponent (removeButton))
                                   .addComponent (timeLabel)
                                   .addGroup (layout.createSequentialGroup()
                                                .addComponent (timeField)
                                                .addComponent (setButton))
                                   );
      layout.setVerticalGroup (layout.createSequentialGroup ()
                                  .addComponent (aBox)
                                   .addComponent (subLabel)
                                   .addGroup (layout.createParallelGroup ()
                                                .addComponent (itemField)
                                                .addComponent (addButton))
                                   .addGroup (layout.createParallelGroup()
                                                .addComponent (itemBox)
                                                .addComponent (removeButton))
                                 .addComponent (timeLabel)
                                   .addGroup (layout.createParallelGroup()
                                                .addComponent (timeField)
                                                .addComponent (setButton))
                                   );
      setLayout (layout);
      setVisible (true);
    }
    
    public void actionPerformed (ActionEvent ae)
    {
      String a = ae.getActionCommand ();
      if (a.equals ("Add")){
        String s = itemField.getText();
        if (s != null && !s.equals ("")){
          itemBox.addItem (s);
          itemField.setText ("");
        }
      }
      else if (a.equals ("Remove")){
        itemBox.removeItem (itemBox.getSelectedItem());       
      }
      else if (a.equals ("Set")){
        String s = timeField.getText();
        if (NewTableModel.isNumeric (s)){
          times [aNum] = Integer.parseInt (s);
          //some kind of confirmation message
        }
        else
        {
          //Error message
        }
      }
    }
    
    public void refreshStatus ()
    {
       
      itemBoxes.get (aNum).setModel (itemBox.getModel());
//      itemBoxes.get (aNum).removeAllItems();
//      System.out.println (aNum);
//     for (int x = 0;x < itemBox.getItemCount();x++)
//     {
//       itemBoxes.get (aNum).addItem (itemBox.getItemAt (x));
//     }
      int i = aBox.getSelectedIndex();
      itemBox.setModel(itemBoxes.get (i).getModel());
      timeField.setText (Integer.toString (times [i]));
      itemField.setText("");
      aNum = i;
    }
    public void itemStateChanged (ItemEvent e)
    {
      //if the activity has changed, set the boxes accordingly 
     refreshStatus();
    }
  }
}