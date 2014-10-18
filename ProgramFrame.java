import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
 * 
 * @author Jason P'ng
 * @version  1.6 October 17th, 2014
 */
public class ProgramFrame extends JFrame implements ActionListener
{
  DisplayPanel display;
    JFileChooser fileChooser;
 public ProgramFrame ()
 {
   super ("Lawssons");
   //add (new IntroPanel ());
   display = new DisplayPanel ();
   getContentPane().add (display);
   JMenuItem newItem = new JMenuItem ("New");
   JMenuItem openItem = new JMenuItem ("Open");
   JMenuItem saveItem = new JMenuItem ("Save");
   JMenuItem saveAsItem = new JMenuItem ("SaveAs");
   JMenuItem quitItem = new JMenuItem ("Quit");
   
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
  
  addKeyListener (new KListen ());
  setVisible (true);
  setSize ((int)(display.getPreferredSize().getWidth() + 15),(int)display.getPreferredSize().getHeight());
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
  }
  else if (a.equals ("Save"))
  {
    if (display.getLawgbook().getFile() == null){
      saveAs();
    }
    else{
      save(display.getLawgbook().getFile());
    }
  }
  else if (a.equals ("SaveAs"))
  {
  }
  else if (a.equals ("Open"))
  {
    open ();
    display.refreshData();
  }
  else if (a.equals ("Quit"))
  {
    System.exit (0);
  }
 }
 
 //make sure l is a reference and not value
 public void save (File file)
  {
    try
    {
      PrintWriter out = new PrintWriter (new FileWriter (file));
      //write info here
      Lawgbook l = display.getLawgbook();
      out.println ("Lawssons File: to be read by the Lawssons program");
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
    }
    catch (IOException e){
    }
  }
  
  public void saveAs ()
  {
    File file;
    fileChooser.showSaveDialog (this);
    file = fileChooser.getSelectedFile();   
    //format the file
    File newFile = new File (file.getParent(),Lawgbook.formatFileName (file));
    //check for overwriting
    int choice = JOptionPane.YES_OPTION;
    if (newFile.exists()){
      choice = JOptionPane.showConfirmDialog (this,"There is another file of the same name. Do you wish to overwrite?","Overwrite?",JOptionPane.YES_NO_OPTION);
    }
    if (choice == JOptionPane.YES_OPTION){
    save (newFile);
    }
  }
  
  public void open ()
  {
    //Check for saving the current file
    Lawgbook l = display.getLawgbook ();
    if (!l.isSaved()){
      int choice = JOptionPane.NO_OPTION;
      choice = JOptionPane.showConfirmDialog (this,"There is unsaved data. Would you like to save it?","Save data?",JOptionPane.YES_NO_OPTION);
      if (choice == JOptionPane.YES_OPTION){
        actionPerformed (new ActionEvent (this,0,"Save"));
      }
    }
    File file;
    fileChooser.showOpenDialog(this); //I need to access a component for this to work. May have to move location of IO Methods
    file = fileChooser.getSelectedFile();
    try{
      BufferedReader in = new BufferedReader (new FileReader (file));
      l.clearData();//could put this at the end to make sure all data is read in properly first
      //read in all the info
      in.readLine ();//The header
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
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog (this,"File could not be found.","File IO error",JOptionPane.ERROR_MESSAGE);
    }
    catch (NumberFormatException n)
    {
      JOptionPane.showMessageDialog (this,"Corrupted Data.","File IO error",JOptionPane.ERROR_MESSAGE);
    }
    catch (NullPointerException p)
    {
      JOptionPane.showMessageDialog (this,"Corrupted Data.","File IO error",JOptionPane.ERROR_MESSAGE);
    }

  }
  
  public void newFile ()
  {
    //Check for saving the current file
    //Open input dialog
    //If input is good, clear the current file
    //make all necessary status changes
  } 
 class KListen extends KeyAdapter
 {
  public void keyPressed (KeyEvent k)
  {
    int a = k.getKeyCode();
  }
 }
}