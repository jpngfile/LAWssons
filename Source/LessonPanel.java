import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import java.util.ArrayList;
import java.util.Arrays;

//Will have to change from list to a table.
/**
 * Panel for displaying the lessons of a class.
 * 
 * @author Jason P'ng
 * @version 3.0 October 30th, 2014
 */
public class LessonPanel extends JPanel implements ActionListener
{
  /**
   * The label to display the name of the lesson.
   */
  JLabel classLabel;
  /**
   * Table to display the activities and their time.
   */
  JTable list;
  /**
   * Text area to display the list of equipment needed.
   */
  JList<String> itemList;
  /**
   * Text area to write comments about the lesson.
   */
  JTextArea commentArea;
  /**
   * Reference to the lawgbook to access the lessons.
   */
  Lawgbook lawgbook;
  /**
   * The index of the lesson being displayed.
   */
  int index = 0;
  /**
   * Names of the table columns for displaying activities.
   */
  public static final Object [] columnNames = {"Activity","Time"};
  /**
   * Constructor for the panel setting up the components and layout.
   * 
   * @param d The dimensions for the panel. It is set to match the DisplayPanel.
   * @param l The lawgbook to retrieve data from.
   */
  public LessonPanel (Dimension d,Lawgbook l)
  {
    super ();
    lawgbook = l;
    setSize (d);
    classLabel = new JLabel ("Class");
    JLabel itemLabel = new JLabel ("Equipment");
    JLabel commentLabel = new JLabel ("Comments");
    list = new JTable();
    JScrollPane display = new JScrollPane (list);
    itemList = new JList<String>(new String [0]);
    commentArea = new JTextArea();
    commentArea.setLineWrap (true);
    commentArea.getDocument().addDocumentListener (new DocumentListener (){
      public void removeUpdate (DocumentEvent e)
      {
        lawgbook.getLessons().get(index).setComments (commentArea.getText());
      }
      public void insertUpdate (DocumentEvent e)
      {
        lawgbook.getLessons().get(index).setComments (commentArea.getText());
      }
      public void changedUpdate (DocumentEvent e)
      {
        lawgbook.getLessons().get(index).setComments (commentArea.getText());
      }
    });
    JButton previousButton = new JButton ("Previous");
    JButton editButton = new JButton ("Edit");
    JButton printButton = new JButton ("Print");
    JButton deleteButton = new JButton ("Delete");
    JButton returnButton = new JButton ("Return");
    JButton nextButton = new JButton ("Next");
    previousButton.addActionListener (this);
    returnButton.addActionListener (this);//perhaps I could attach this to the program frame instead
    editButton.addActionListener (this);
    printButton.addActionListener (this);
    deleteButton.addActionListener (this);
    nextButton.addActionListener (this);
    GroupLayout layout = new GroupLayout (this);
    
    layout.setAutoCreateGaps (true);
    layout.setAutoCreateContainerGaps (true);
    layout.setHorizontalGroup (layout.createParallelGroup ()
                                 .addComponent (classLabel)                                              
                                 .addGroup (layout.createSequentialGroup()
                                              .addComponent (display)
                                              .addGroup (layout.createParallelGroup()
                                                           .addComponent (itemLabel)
                                                           .addComponent (itemList))
                                              .addGroup (layout.createParallelGroup()
                                                           .addComponent (commentLabel)
                                                           .addComponent (commentArea)))
                                 .addGroup (layout.createSequentialGroup ()
                                              .addComponent (previousButton)
                                              .addComponent (editButton)
                                              .addComponent (printButton)
                                              .addComponent (deleteButton)
                                              .addComponent (returnButton)
                                              .addComponent (nextButton)
                                           )
                              );
    layout.setVerticalGroup (layout.createSequentialGroup()
                               .addComponent (classLabel)
                               .addGroup (layout.createParallelGroup()
                                            .addComponent (display)
                                            .addGroup (layout.createSequentialGroup ()
                                                         .addComponent (itemLabel)
                                                         .addComponent (itemList))
                                            .addGroup (layout.createSequentialGroup ()
                                                         .addComponent (commentLabel)
                                                         .addComponent (commentArea)))
                               .addGroup (layout.createParallelGroup ()
                                            .addComponent (previousButton)
                                            .addComponent (editButton)
                                            .addComponent (printButton)
                                            .addComponent (deleteButton)
                                            .addComponent (returnButton)
                                            .addComponent (nextButton)
                                         )
                            );
    setLayout (layout);
    setVisible (true);
  }
  
  /**
   * Sets the displayed name on the name label.
   * 
   * @param newName The name of the current lesson.
   */
  public void setName (String newName)
  {
    classLabel.setText (newName);
  }
  
  /**
   * Sets the activities of the displayed list.
   * depreciated.
   * @param newList List to set the list data to.
   */
//  public void setList (ArrayList<Activity> newList)
//  {
//    list.setListData (newList.toArray(new Activity [0]));
//  }
  
  /**
   * Implementation for actionListener interface. Sets actions for each button.
   * 
   * @param ae Action triggering event to track source to know which button was pressed.
   */
  @Override
  public void actionPerformed (ActionEvent ae)
  {
    String a = ae.getActionCommand ();
    if (a.equals ("Previous"))
    {
      index--;
      if (index  < 0){
        index = lawgbook.getNumLessons() - 1; 
      }
      setData (index);
    }
    else if (a.equals ("Edit"))
    {
      Lesson l = lawgbook.getLessons().get(index);
      LessonEditPanel panel = new LessonEditPanel (l);
      int choice = JOptionPane.showConfirmDialog (this,panel,"Editing",JOptionPane.OK_CANCEL_OPTION);
      if (choice == JOptionPane.OK_OPTION){        
        l.setActivities (panel.getRemoveActivities());
        //setList (panel.getRemoveActivities ());
        setData (index);
        lawgbook.setSaved (false);
      }
    }
    else if (a.equals ("Delete"))
    {
      lawgbook.getLessons().remove (index);
      lawgbook.setSaved (false);
      if (lawgbook.getNumLessons () == 0)
      {
        actionPerformed (new ActionEvent (this,0,"Return"));
      }
      else{
        if (index == lawgbook.getNumLessons ()){
          index--; 
        }
        setData (index);
      }
    }
    else if (a.equals ("Print"))
    {
      Printer p = new Printer ();
      for (int x = 0;x < getNumActivities();x++)
      {
        p.println (lawgbook.getLessons().get(index).getActivities().get(x).toString());
      }
      p.printUsingDialog();
    }
    else if (a.equals ("Return"))
    {
      ActionListener al = ((ActionListener)getParent().getParent().getParent().getParent().getParent());
      al.actionPerformed (new ActionEvent (al,0,"Return"));
    }
    else if (a.equals ("Next"))
    {
      index++;
      if (index >= lawgbook.getNumLessons()){
        index = 0; 
      }
      setData (index);
    }
  }
  
  /**
   * Returns the amount of activities in the current lesson.
   * 
   * @return The amount of activities in the current lesson.
   */
  public int getNumActivities ()
  {
    return lawgbook.getLessons().get(index).getActivities().size();
  }
  
  /**
   * Returns the index of the current lesson
   * 
   * @return The index of the current lesson
   */
  public int getIndex ()
  {
    return index;
  }
  
  /**
   * Sets the lesson shown in the list given the index of the lesson in the lawgbook.
   * 
   * @param index The index of the lesson to be shown.
   */
  public void setData (int index)
  {    
    Activity [] aList = (Activity [])(lawgbook.getLessons().get(index).getActivities().toArray(new Activity [0]));
    Object [][]data = new Object [aList.length][2];
    ArrayList<String> items = new ArrayList<String>();
    for (int x = 0;x < aList.length;x++){
      data [x][0] = aList[x].toString();
      data [x][1] = aList[x].getTime();
      //System.out.println (aList[x].getItemCount());
      for (int y = 0;y < aList[x].getItemCount();y++){
        items.add (aList[x].getItems().get(y));
      }
    }
    list.setModel (new DefaultTableModel(data,columnNames));
    itemList.setListData (items.toArray(new String [0]));
    commentArea.setText (lawgbook.getLessons().get(index).getComments());
    setName (lawgbook.getLessons().get(index).getTitle());
  }
  
  /**
   * Panel for editing lessons. It contains JComboBoxes to display available activities to remove and add from the lesson.
   */
  private class LessonEditPanel extends JPanel implements ActionListener
  {
    /**
     * Combo box containing activities that can be added to the lesson.
     */
    JComboBox<Activity> addBox;
    /**
     * Combo box containing activities that can be removed from the current lesson.
     */
    JComboBox<Activity> removeBox;
    
    /**
     * Constructor for the panel setting up the data in the combo boxes and the layout of components.
     * 
     * @param l The lesson to determine which activites can be removed and added.
     */
    public LessonEditPanel (Lesson l)
    {
      removeBox = new JComboBox<Activity> (l.getActivities().toArray (new Activity [0]));
      ArrayList<Activity> copy = new ArrayList<Activity>();
      copy.addAll(lawgbook.getActivities());
      copy = removeActivities (copy,l.getActivities());
      //copy.removeAll (l.getActivities());
      addBox = new JComboBox <Activity> (copy.toArray(new Activity [0]));
      JButton addButton = new JButton ("Add");
      JButton removeButton = new JButton ("Remove");
//    JButton cancelButton = new JButton ("Cancel");
//    JButton okButton = new JButton ("Ok");
      addButton.addActionListener (this);
      removeButton.addActionListener (this);
      GroupLayout layout = new GroupLayout (this);
      layout.setAutoCreateGaps (true);
      layout.setAutoCreateContainerGaps (true);
      layout.setHorizontalGroup (layout.createSequentialGroup ()
                                   .addGroup (layout.createParallelGroup ()
                                                .addComponent (addBox)
                                                .addComponent (removeBox))
                                   .addGroup (layout.createParallelGroup ()
                                                .addComponent (addButton)
                                                .addComponent (removeButton))
                                );
      layout.setVerticalGroup (layout.createSequentialGroup ()
                                 .addGroup (layout.createParallelGroup ()
                                              .addComponent (addBox)
                                              .addComponent (addButton))
                                 .addGroup (layout.createParallelGroup ()
                                              .addComponent (removeBox)
                                              .addComponent (removeButton))
                              );
      setLayout (layout);
      setVisible (true);
    }
    
    @Override
    public void actionPerformed (ActionEvent ae)
    {
      String a = ae.getActionCommand ();
      if (a.equals ("Add"))
      {
        if (addBox.getSelectedItem() != null){
          removeBox.addItem ((Activity)addBox.getSelectedItem ());
          addBox.removeItem (addBox.getSelectedItem());
        }
      }
      else if (a.equals ("Remove"))
      {
        if (removeBox.getSelectedItem () != null){
          addBox.addItem ((Activity)removeBox.getSelectedItem ());
          removeBox.removeItem (removeBox.getSelectedItem());
        }
      }
    }
    
    /**
     * Returns the list of activites that are not in the current lesson.
     * 
     * @return An ArrayList of activites that are not in the current lesson.
     */
    public ArrayList<Activity> getAddActivities ()
    {
      ArrayList<Activity> list = new ArrayList<Activity> ();
      for (int x = 0;x < addBox.getItemCount();x++)
      {
        list.add (addBox.getItemAt (x));
      }
      return list;
    }
    
    /**
     * Returns the list of activites that are in the current lesson.
     * 
     * @return An ArrayList of activites that are in the current lesson.
     */
    public ArrayList<Activity> getRemoveActivities ()
    {
      ArrayList<Activity> list = new ArrayList<Activity> ();
      for (int x = 0;x < removeBox.getItemCount();x++)
      {
        list.add (removeBox.getItemAt (x));
      }
      return list;
    }
    
    /**
     * Returns the original ArrayList without any of the activites in the second arrayList.
     * 
     * @param original The original ArrayList to remove activites from.
     * @param remove The ArrayList to check for duplicates of.
     * 
     * @return The origianl arrayList without any of the activities of the second
     */
    public ArrayList<Activity> removeActivities (ArrayList<Activity> original, ArrayList<Activity> remove)
    {
      for (int x = 0;x < original.size();x++)
      {
        Activity a = original.get(x);
        for (int y = 0;y < remove.size();y++)
        {
          Activity b = remove.get (y);
          if (a.equals (b)){
            original.remove (a);
            x--;
            break;
          }
        }
      }
      return original;
    }
  }
}