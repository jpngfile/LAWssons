import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
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
/**
 * The primary JFrame for containing the program.
 * FileIO is executed from here.
 * For FileIO, will have to error trap for failed operations so that data isn't cleared prematurely
 * 
 * @author Jason P'ng
 * @version  1.6 October 17th, 2014
 */
public class ProgramFrame extends JFrame implements ActionListener
{
  DisplayPanel display;
  JPanel mainPanel;
  JFileChooser fileChooser;
  public ProgramFrame ()
  {
    super ("Lawssons");
    //add (new IntroPanel ());
    mainPanel = new JPanel (new CardLayout());
    
    display = new DisplayPanel ();
    setSize ((int)(display.getPreferredSize().getWidth() + 15),(int)display.getPreferredSize().getHeight());
    mainPanel.setSize (this.getSize());    
    mainPanel.add ("intro",new IntroPanel());
    mainPanel.add ("display",display);
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
    //setLocation (0,0);
    addWindowListener (new WindowAdapter ()
                         {
      public void windowClosing (WindowEvent event)
      {
        checkSaved();
        System.exit (0);
      }
    });
    addKeyListener (new KListen ());
    setVisible (true);    
    setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    
    fileChooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter ("Lawg files","lawg");
    fileChooser.setFileFilter (filter);
    fileChooser.addChoosableFileFilter (filter);//necessary?
  }
  
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
    else if (a.equals ("Quit"))
    {
      checkSaved ();
      System.exit (0);
    }
  }
  
  public void show (String panel)
  {
    CardLayout cl = (CardLayout)(mainPanel.getLayout());
    cl.show (mainPanel,panel);
    revalidate();
  }
  //make sure l is a reference and not value
  //should save weeks and weeks passed
  public boolean save (File file)
  {
    try
    {
      PrintWriter out = new PrintWriter (new FileWriter (file));
      //write info here
      Lawgbook l = display.getLawgbook();
      out.println ("Lawssons File: to be read by the Lawssons program");
      out.println (l.getTitle());
      out.println (l.getTotalWeeks());
      out.println (l.getWeeksPassed());
      out.println (l.getActivityMin());
      out.println (l.getNumActivities());
      for (Activity a : l.getActivities()){
        out.println (a.getName());
        out.println (a.getCompleted());
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
        out.println (f.getDate());
        out.println (f.getActivities().size());
        for (Activity a : f.getActivities ()){
          out.println (a.getName());
        }
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
  
  public boolean saveAs ()
  {
    File file;
    fileChooser.showSaveDialog (this);
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
    return false;
  }
  
  //should read in amount of weeks and weeks passed now
  public void open ()
  {
    //Check for saving the current file
    if (checkSaved ()){
      Lawgbook l = display.getLawgbook ();
      File file;
      fileChooser.showOpenDialog(this); //I need to access a component for this to work. May have to move location of IO Methods
      file = fileChooser.getSelectedFile();
      if (file != null){
        //Create temporary arrayLists in case something goes wrong
        ArrayList<Activity> a = new ArrayList<Activity>();
        a.addAll (l.getActivities());
        ArrayList<Student> st = new ArrayList<Student>();
        st.addAll (l.getStudents());
        ArrayList<Lesson> lessons = new ArrayList<Lesson>();
        lessons.addAll (l.getLessons());
        String oldTitle = l.getTitle();
        int [] stats = new int []{l.getTotalWeeks(),l.getWeeksPassed(),l.getActivityMin()};
        try{
          BufferedReader in = new BufferedReader (new FileReader (file));        
          l.clearData();
          //read in all the info
          in.readLine ();//The header
          l.setTitle (in.readLine ());
          l.setTotalWeeks (Integer.parseInt (in.readLine ()));
          l.setWeeksPassed (Integer.parseInt (in.readLine ()));
          l.setActivityMin (Integer.parseInt (in.readLine ()));
          int numActivities = Integer.parseInt (in.readLine());
          for (int x = 0;x < numActivities;x++){
            String name = in.readLine ();
            int completed = Integer.parseInt (in.readLine ());
            l.addActivity (name,completed);
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
            long time = Long.parseLong(in.readLine ());
            Date newDate = new Date (time);
            int listLength = Integer.parseInt (in.readLine ());
            ArrayList<Activity> list = new ArrayList<Activity>();
            for (int y = 0;y < listLength;y++){
              list.add (new Activity (in.readLine ()));
            }
            l.addLesson (new Lesson (newDate,list));
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
        catch (NumberFormatException n)
        {
          l.clearData();
          l.getActivities().addAll(a);
          l.getStudents().addAll (st);
          l.getLessons().addAll (lessons);
          l.setTitle (oldTitle);
          l.setTotalWeeks (stats [0]);
          l.setWeeksPassed (stats[1]);
          l.setActivityMin (stats [2]);
          JOptionPane.showMessageDialog (this,"Corrupted Data.","File IO error",JOptionPane.ERROR_MESSAGE);
        }
        catch (NullPointerException p)
        {
          l.clearData();
          l.getActivities().addAll (a);
          l.getStudents().addAll (st);
          l.getLessons().addAll (lessons);
          l.setTitle (oldTitle);
          l.setTotalWeeks (stats [0]);
          l.setWeeksPassed (stats[1]);
          l.setActivityMin (stats [2]);
          JOptionPane.showMessageDialog (this,"Corrupted Data.","File IO error",JOptionPane.ERROR_MESSAGE);
        }
      }
    }
  }
  
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
          Lawgbook l = display.getLawgbook ();
          l.setTitle (name);
          l.setTotalWeeks (weekNum);
          l.setWeeksPassed (0);
          l.setActivityMin (activityMin);
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
  
  public boolean saveFile ()
  {
    if (display.getLawgbook().getFile() == null){
      return saveAs();
    }
    else{
      return save(display.getLawgbook().getFile());
    }
  }
  
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
  
  class KListen extends KeyAdapter
  {
    public void keyPressed (KeyEvent k)
    {
      int a = k.getKeyCode();
    }
  }
}