import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
/**
 * The primary JFrame for containing the program.
 * FileIO is executed from here.
 * 
 * @author Jason P'ng
 * @version  3.0 October 30th, 2014
 */
//Note: Lawgbook could be static. There is only ever one and it is used by many classes
//Note: FileIO could be modified to allow any amount of new variables to lawgbook
//Will need to call methods based on String
public class ProgramFrame extends JFrame implements ActionListener
{
  /**
   * The panel that displays the data table.
   */
  DisplayPanel display;
  
  /**
   * Panel that displays the lessons of the lawgbook.
   */
  LessonPanel lessons;
  
  /**
   * The unseen panel that contains all displayable panels.
   */
  JPanel mainPanel;
  
  /**
   * Dialog panel for choosing files for FileIO.
   */
  JFileChooser fileChooser;
  
  /**
   * Constructor for setting up the frame and all panels.
   */
  public ProgramFrame ()
  {
    super ("Lawssons");
    //add (new IntroPanel ());
    mainPanel = new JPanel (new CardLayout());
    
    display = new DisplayPanel ();
    lessons = new LessonPanel(display.getSize(),display.getLawgbook());
    setSize ((int)(display.getPreferredSize().getWidth() + 15),(int)display.getPreferredSize().getHeight());
    mainPanel.setSize (this.getSize());    
    mainPanel.add ("intro",new IntroPanel());
    mainPanel.add ("display",display);
    mainPanel.add ("lessons", lessons);
    getContentPane().add(mainPanel);
    JMenuItem newItem = new JMenuItem ("New");
    JMenuItem openItem = new JMenuItem ("Open");
    JMenuItem saveItem = new JMenuItem ("Save");
    JMenuItem saveAsItem = new JMenuItem ("SaveAs");
    JMenuItem quitItem = new JMenuItem ("Quit");
    
//    saveItem.setEnabled (false);
//    saveAsItem.setEnabled (false);
    JMenu file = new JMenu ("File");
    file.add (newItem);
    file.add (openItem);
    file.add (saveItem);
    file.add (saveAsItem);
    file.add (quitItem);
    
    JMenuBar fileMenu = new JMenuBar ();
    fileMenu.add (file);
    setJMenuBar (fileMenu);
    
    newItem.addActionListener (this);
    openItem.addActionListener (this);
    saveItem.addActionListener (this);
    saveAsItem.addActionListener (this);
    quitItem.addActionListener (this);
    
    ImageIcon icon = new ImageIcon ("icon.png");
    setIconImage (icon.getImage());
    setLocation (200,100);
    addWindowListener (new WindowAdapter ()
                         {
      public void windowClosing (WindowEvent event)
      {
        checkSaved();
        System.exit (0);
      }
    });
    addKeyListener (new KListen ());
    setResizable (false);
    setVisible (true);    
    setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    
    fileChooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter ("Lawg files","lawg");
    fileChooser.setFileFilter (filter);
    fileChooser.addChoosableFileFilter (filter);//necessary?
    //System.out.println (display.getSize());
  }
  
  @Override
  public void actionPerformed (ActionEvent ae)
  {
    String a = ae.getActionCommand ();
    if (a.equals ("New"))
    {
      newFile();      
    }
    else if (a.equals ("Save"))
    {
      if (display.getLawgbook().getTitle() != null)
        saveFile();
    }
    else if (a.equals ("SaveAs"))
    {
      if (display.getLawgbook().getTitle() != null)
        saveAs();
    }
    else if (a.equals ("Open"))
    {
      open ();
      display.refreshData();      
    }
    else if (a.equals ("See lessons"))
    {
      if (display.getLawgbook().getNumLessons() > 0){
        lessons.setData (lessons.getIndex());
        show ("lessons"); 
      }
      else{
        JOptionPane.showMessageDialog (this,"There are no lessons to show.","No lessons",JOptionPane.ERROR_MESSAGE);
      }     
    }
    else if (a.equals ("Return"))
    {
      show ("display");
    }
    else if (a.equals ("Quit"))
    {
      checkSaved ();
      System.exit (0);
    }
  }
  
  /**
   * Shows the panel designated by the given String.
   * This uses the cardLayout in the main panel to flip between panels.
   * 
   * @param panel The String to designate the panel to be shown.
   */
  public void show (String panel)
  {
    CardLayout cl = (CardLayout)(mainPanel.getLayout());
    cl.show (mainPanel,panel);
    revalidate();
  }
  //make sure l is a reference and not value
  //should save weeks and weeks passed
  
  /**
   * Saves the data in the lawgbook into the given file.
   * 
   * @param file The file to save the data in.
   * @return If the saving operation was successful.
   */
  public boolean save (File file)
  {
    try
    {
      PrintWriter out = new PrintWriter (new FileWriter (file));
      //write info here
      Lawgbook l = display.getLawgbook();
      out.println ("Lawssons File: to be read by the Lawssons program");
      for (String s : Lawgbook.variableList)
      {
        try{
          Method method = l.getClass().getMethod ("get" + s);
          out.println (method.invoke (l));
        }
        catch (SecurityException a){}
        catch (NoSuchMethodException b){}
        catch (IllegalArgumentException c){}
        catch (IllegalAccessException d){}
        catch (InvocationTargetException e){}             
      }
      out.println (l.getNumActivities());
      for (Activity a : l.getActivities()){
        out.println (a.getName());
        out.println (a.getCompleted());
        out.println (a.getTime());
        out.println (a.getItemCount());
        for (String s : a.getItems()){
          out.println (s);
        }
      }
      //amount of rankings is equal to the amount of activites
      out.println (l.getNumStudents());
      for (Student s : l.getStudents()){
        out.println (s.getName());
        for (int x = 0;x < s.getNumRanks();x++){
          out.println (s.getActivity(x));
          out.println (s.getRank (s.getActivity(x)));
        }
      }
      out.println (l.getNumLessons());
      for (Lesson f : l.getLessons ()){
        out.println (f.getTitle());
        out.println (f.getDate().getTime());
        out.println (f.getActivities().size());
        for (Activity a : f.getActivities ()){
          out.println (a.getName());
        }
        out.println (f.getComments());
      }
      out.println ("End of File");
      out.close();
      //Do necessary checks here
      l.setSaved (true);
      l.setFile (file);
      return true;
    }
    catch (IOException e){
      return false;
    }
  }
  
  /**
   * Saves the current data with a file name.
   * This will open a file dialog for saving the name of the file.
   * If this is the first time data has been saved, this method will run.
   * 
   * @return Whether or not the saving was successful.
   */
  public boolean saveAs ()
  {
    File file;
    int fileChoice = fileChooser.showSaveDialog (this);
    if (fileChoice == JFileChooser.APPROVE_OPTION){
    file = fileChooser.getSelectedFile();   
    //format the file
    if (file != null){
      File newFile = new File (file.getParent(),Lawgbook.formatFileName (file));
      //check for overwriting
      int choice = JOptionPane.YES_OPTION;
      if (newFile.exists()){
        choice = JOptionPane.showConfirmDialog (this,"There is another file of the same name. Do you wish to overwrite?","Overwrite?",JOptionPane.YES_NO_OPTION);
      }
      if (choice == JOptionPane.YES_OPTION){
        return save (newFile);
      }
    }
    }
    return false;
  }
  
  //should read in amount of weeks and weeks passed now
  /**
   * Opens a file dialog for opening .lawg files.
   * If successful, the lawg file will be read and put into the current lawgbook.
   */
  public void open ()
  {
    //Check for saving the current file
    if (checkSaved ()){
      final Lawgbook l = display.getLawgbook ();
      File file;
      //This crashed a few times with a nullpointerException
      fileChooser.showOpenDialog(this); //I need to access a component for this to work. May have to move location of IO Methods
      file = fileChooser.getSelectedFile();
      if (file != null){
        //Create temporary arrayLists in case something goes wrong
        final ArrayList<Activity> a = new ArrayList<Activity>();
        a.addAll (l.getActivities());
        final ArrayList<Student> st = new ArrayList<Student>();
        st.addAll (l.getStudents());
        final ArrayList<Lesson> lessons = new ArrayList<Lesson>();
        lessons.addAll (l.getLessons());
        final String oldTitle = l.getTitle();
        final int [] stats = new int []{l.getTotalWeeks(),l.getWeeksPassed(),l.getActivityMin(),l.getLessonTime()};
        class Restore {
          public  void restoreData (){
            l.clearData();
            l.getActivities().addAll(a);
            l.getStudents().addAll (st);
            l.getLessons().addAll (lessons);
            l.setTitle (oldTitle);
            l.setTotalWeeks (stats [0]);
            l.setWeeksPassed (stats[1]);
            l.setActivityMin (stats [2]);
            l.setLessonTime (stats [3]);
          }
        }
        try{
          BufferedReader in = new BufferedReader (new FileReader (file));        
          l.clearData();
          //read in all the info
          in.readLine ();//The header
          l.setTitle (in.readLine ());
          l.setTotalWeeks (Integer.parseInt (in.readLine ()));
          l.setWeeksPassed (Integer.parseInt (in.readLine ()));
          l.setActivityMin (Integer.parseInt (in.readLine ()));
          l.setLessonTime (Integer.parseInt (in.readLine()));
          int numActivities = Integer.parseInt (in.readLine());
          for (int x = 0;x < numActivities;x++){
            String name = in.readLine ();
            int completed = Integer.parseInt (in.readLine ());
            int time = Integer.parseInt (in.readLine());
            int numItems = Integer.parseInt (in.readLine ());
            ArrayList<String> items = new ArrayList<String>();
            for (int y = 0;y < numItems;y++){
              items.add (in.readLine());
            }
            Activity ac = new Activity (name,completed);
            ac.setTime (time);
            ac.setItems (items);
            l.addActivity (ac);
          }
          int numStudents = Integer.parseInt (in.readLine ());
          for (int x = 0;x < numStudents;x++){
            String name = in.readLine ();
            l.addStudent (name);
            Student s = l.getStudent (x);
            for (int y = 0;y < numActivities;y++){
              String aName = in.readLine ();
              int rank = Integer.parseInt (in.readLine ());
              s.setRanking (aName,rank);
            }
          }
          int numLessons = Integer.parseInt (in.readLine ());
          for (int x = 0;x < numLessons;x++){
            String title = in.readLine();
            long time = Long.parseLong(in.readLine ());
            Date newDate = new Date (time);
            int listLength = Integer.parseInt (in.readLine ());
            ArrayList<Activity> list = new ArrayList<Activity>();
            for (int y = 0;y < listLength;y++){
              list.add (l.getActivity (in.readLine()));
            }
            String comments = in.readLine ();
            l.addLesson (new Lesson (title,newDate,list,comments));
          }
          in.readLine ();//footer
          in.close(); 
          //Do all checks to make sure the data is all good
          //necessary status changes
          l.setSaved (true);
          l.setFile (file);
          //Display the info
          show ("display");
        }        
        catch (IOException e)
        {
          JOptionPane.showMessageDialog (this,"File could not be found.","File IO error",JOptionPane.ERROR_MESSAGE);
        }
        //could clear this up with a nested method
//        catch (NumberFormatException n)
//        {
//          (new Restore()).restoreData ();
//          JOptionPane.showMessageDialog (this,"Corrupted Data.","File IO error",JOptionPane.ERROR_MESSAGE);
//        }
//        catch (NullPointerException p)
//        {
//          (new Restore()).restoreData ();
//          JOptionPane.showMessageDialog (this,"Corrupted Data.","File IO error",JOptionPane.ERROR_MESSAGE);
//        }
      }
    }
  }
  
  /**
   * Creates a new class, and thereby a new lawgbook.
   * It opens a dialog for inputting data necessary for a new class and will cancel if data is invalid.
   */
  public void newFile ()
  {
    //Check for saving the current file
    if (checkSaved ()){
      //Open input dialog
      ClassInputPanel inputPanel = new ClassInputPanel ();
      int choice = JOptionPane.showConfirmDialog (this,inputPanel,"New Class Input",JOptionPane.OK_CANCEL_OPTION);
      if (choice == JOptionPane.OK_OPTION){
        try
        {
          String name = inputPanel.nameField.getText();
          int weekNum = Integer.parseInt (inputPanel.weekField.getText());
          int activityMin = Integer.parseInt (inputPanel.activityField.getText());
          int lessonTime = Integer.parseInt (inputPanel.lessonTimeField.getText());
          Lawgbook l = display.getLawgbook ();
          l.setTitle (name);
          l.setTotalWeeks (weekNum);
          l.setWeeksPassed (0);
          l.setActivityMin (activityMin);
          l.setLessonTime (lessonTime);
          l.setSaved (true);
          l.clearData();
          display.refreshData();
          show ("display");
        }
        catch (NumberFormatException e){
          JOptionPane.showMessageDialog (this,"Input is not acceptable.","Input error",JOptionPane.ERROR_MESSAGE);
        }
      }
      //If input is good, clear the current file
      //make all necessary status changes
    }
  } 
  
  /**
   * Saves a the current file, checking if the data has been saved before.
   * 
   * @return If the save operation was successful, true, otherwise false.
   */
  public boolean saveFile ()
  {
    if (display.getLawgbook().getFile() == null){
      return saveAs();
    }
    else{
      return save(display.getLawgbook().getFile());
    }
  }
  
  /**
   * Checks if the current file is saved, or new and doesn't need to be saved.
   * If it isn't saved, the user is prompted to save it.
   * 
   * @return If the user saves the file successfully, or acknowledges data will be lost, return true, otherwise false.
   */
  public boolean checkSaved ()
  {
    //This needs to be fixed for new files. Remove the second part
    if (!display.getLawgbook().isSaved() && display.getLawgbook().getTitle() != null){
      int choice = JOptionPane.NO_OPTION;
      choice = JOptionPane.showConfirmDialog (this,"There is unsaved data. Would you like to save it?","Save data?",JOptionPane.YES_NO_CANCEL_OPTION);
      if (choice == JOptionPane.YES_OPTION){
        return saveFile();
      }
      else if (choice == JOptionPane.CANCEL_OPTION){
        return false;
      }
    }
    return true;
  }
  
  /**
   * KeyAdapter class for keyboard input.
   * May be implemented later for keyboard shortcuts.
   */
  class KListen extends KeyAdapter
  {
    /**
     * Reactionary response for keys being pressed.
     * 
     * @param k keyboard action to find the key  being pressed.
     */
    public void keyPressed (KeyEvent k)
    {
      int a = k.getKeyCode();
    }
  }
}