import javax.swing.*;
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
  
  //should probably use polimorphism instead
  NewTableModel model = new NewTableModel();
 public Lawgbook()
 {
   date = new Date (System.currentTimeMillis());
   DateFormat format = new SimpleDateFormat ("EEE, d MMM yyyy");
   formatDate = format.format (date);
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
   
 }
 
 public void save ()
 {
   
 }
 
 public void saveAs ()
 {
   
 }
 
 public void open ()
 {
   
 }
 
 public void newFile ()
 {
   
 }
 
  
}