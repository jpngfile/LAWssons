import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
public class Lawgbook
{
  ArrayList<Student> students = new ArrayList<Student>();
  ArrayList<Activity> activities = new ArrayList<Activity>();
  ArrayList<Lesson> lessons = new ArrayList<Lesson>();
  Date date;
  String title;
  String formatDate;
  JFileChooser fileChooser;
  //should probably use polimorphism instead
  NewTableModel model = new NewTableModel();
 public Lawgbook()
 {
   date = new Date (System.currentTimeMillis());
   DateFormat format = new SimpleDateFormat ("EEE, d MMM yyyy");
   formatDate = format.format (date);
   fileChooser = new JFileChooser();
   FileNameExtensionFilter filter = new FileNameExtensionFilter ("Lawg files","lawg");
   fileChooser.setFileFilter (filter);
   fileChooser.addChoosableFileFilter (filter);//necessary?
 }
 
 public ArrayList<Activity> creatLessonList (int amount)
 {
  ArrayList<Activity> list = new ArrayList<Activity>();
  return list;
 }
 
 public int getRank (Activity a)
 {
  return 0; 
 }
 
 public void printLesson (Lesson l)
 {
   //Access the printer class
 }
 
 public void save (File file)
 {
   try{
   PrintWriter out = new PrintWriter (new FileWriter (file));
   //write info here
   out.close();
   //Do necessary checks here
   }
   catch (IOException e){
   }
 }
 
 public void saveAs ()
 {
   File file;
   //fileChooser.showSaveDialog (this);
   file = fileChooser.getSelectedFile();
   //format the file
   //check for overwriting
   save (file);
 }
 
 public void open ()
 {
   //Check for saving the current file
   File file;
   //fileChooser.showOpenDialog(this); I need to access a component for this to work. May have to move location of IO Methods
   file = fileChooser.getSelectedFile();
   try{
   BufferedReader in = new BufferedReader (new FileReader (file));
   //read in all the info
   //Do all checks to make sure the data is all good
   //necessary status changes
   //Display the info
   }
   catch (IOException e)
   {
   }
 }
 
 public void newFile ()
 {
   //Check for saving the current file
   //Open input dialog
   //If input is good, clear the current file
   //make all necessary status changes
 }
 
  
}