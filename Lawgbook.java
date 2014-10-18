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

  File fileName;
  boolean saved;
  public Lawgbook()
  {
    date = new Date (System.currentTimeMillis());
    DateFormat format = new SimpleDateFormat ("EEE, d MMM yyyy");
    formatDate = format.format (date);
    
    title = "Law's Swimming Class";
  }
  
  public String getFormatDate ()
  {
    return formatDate;
  }
  
  public String getTitle ()
  {
    return title;
  }
  
  public void setTitle (String newTitle)
  {
    title = newTitle;
  }
  public ArrayList<Activity> createLessonList (int amount)
  {
    ArrayList<Activity> list = new ArrayList<Activity>();
    return list;
  }
  
  public Activity getActivity (int index)
  {
    try{
      return activities.get (index);
    }
    catch (IndexOutOfBoundsException e){
      return null;
    }
  }
  
  public Student getStudent (int index)
  {
    try
    {
      return students.get(index);
    }
    catch (IndexOutOfBoundsException e){
      System.out.println ("index error: " + index);
      return null;
    }
  }
  
  public void addStudent (String name)
  {
    Student s = new Student (name);
    for (Activity a : activities)
    {
      s.addRanking (a,0); 
    }
    students.add (s);
  }
  
  public void addActivity (String name)
  {
    Activity a = new Activity (name);
    activities.add (a);
    for (Student s : students)
    {
      s.addRanking (a,0); 
    }
  }
  public void addActivity (String name,int completed)
  {
    Activity a = new Activity (name,completed);
    activities.add (a);
    for (Student s : students)
    {
      s.addRanking (a,0); 
    }
  }
  
  public void addLesson (Lesson l)
  {
   lessons.add (l); 
  }
  //returns if removal was successful
  public boolean removeActivity (String name)
  {
    for (Activity a : activities)
    {
      if (a.getName().equals (name)){
        for (Student s : students){
          s.removeRanking (a);
        }
        activities.remove (a);
        return true;
      }
    }
    return false;
  }
  
  public boolean removeStudent (String name)
  {
    for (int x = 0; x < students.size();x++)
    {
      Student s = students.get(x);
      if (s.getName().equals (name))
      {
//      if (x != students.size() - 1)
//        dynamicDiff--;
        students.remove (s);
        return true;
      }
    }
    return false;
  }
  public int getRank (Activity a)
  {
    return 0; 
  }
  
  public void printLesson (Lesson l)
  {
    //Access the printer class
  }
  
  public void setFile (File newFile)
  {
    fileName = newFile;
  }
  
  public File getFile()
  {
    return fileName;
  }
  
  public boolean isSaved ()
  {
    return saved;
  }
  
  public void setSaved (boolean save)
  {
    saved = save;
  }
  
  public void clearData ()
  {
    activities.clear();
    lessons.clear();
    students.clear();
  }
  public int getNumActivities ()
  {
    return activities.size();
  }
  
  public int getNumStudents ()
  {
    return students.size();
  }
  
  public int getNumLessons ()
  {
    return lessons.size();
  }
  
  public ArrayList<Activity> getActivities ()
  {
    return activities;
  }
  public ArrayList<Student> getStudents ()
  {
    return students;
  }  
  public ArrayList<Lesson> getLessons ()
  {
    return lessons;
  }
  public static String formatFileName (File file)
  {
    String name = file.getName();
    if (name.length() < 5 || !name.substring(name.length() - 5).equals (".lawg")){
      name = name + ".lawg";
    }
    return name;
  }
}