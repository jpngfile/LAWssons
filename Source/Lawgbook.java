import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.File;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

/**
 * Data management class for a class. All other classes access data from this class. <br>
 * It's called 'Lawgbook' because it's a pun off of the name Law.
 *
 * @author Jason P'ng
 * @version 3.0 October 30th, 2014
 */
public class Lawgbook
{
  /**
   * List of the global primitive data types.
   */
  final static String [] variableList = {"Title","TotalWeeks","WeeksPassed","ActivityMin","LessonTime"};
  /**
   * List containing all student data.
   */
  ArrayList<Student> students = new ArrayList<Student>();
  /**
   * List containing activity data.
   */
  ArrayList<Activity> activities = new ArrayList<Activity>();
  /**
   * List containing lesson data.
   */
  ArrayList<Lesson> lessons = new ArrayList<Lesson>();
  /**
   * List containing lessons sorted by priority.
   */
  ArrayList<Activity> sorted = new ArrayList<Activity>();
  /**
   * The date this lawgbook is opened.
   */
  Date date;
  /**
   * The name of the class this lawgbook is for.
   */
  String title;
  /**
   * A string version of the date for this lawgbook.
   */
  String formatDate;
  /**
   * The amount of weeks this class is taught for. It is assumed there is only 1 lesson per week.
   */
  int totalWeeks;
  /**
   * The amount of weeks that have passed since this lawgbook was created. This is recorded based on how many times the
   * lawgbook has been updated with a lesson.
   */
  int weeksPassed;
  /**
   * The set minimum amount of times an activity must be taught. This is used to determine priority when ranking activities.
   */
  int activityMin;
  /**
   * The duration in minutes of a single lesson.
   */
  int lessonTime;
  /**
   * The file that is used to represent this lawgbook when saving.
   */
  File fileName;
  /**
   * Determines if the current data in the lawgbook is in a saved state.
   */
  boolean saved;
  /**
   * Constructor for the lawgbook. It records the current date and formats it to String.
   */
  public Lawgbook()
  {
    date = new Date (System.currentTimeMillis());
    DateFormat format = new SimpleDateFormat ("EEE, d MMM yyyy");
    formatDate = format.format (date);
  }
  
  /**
   * Returns the String format of the date for this lawgbook.
   *
   * @return The formatted String of this lawgbook's date.
   */
  public String getFormatDate ()
  {
    return formatDate;
  }
  
  /**
   * Returns the name of the class for this lawgbook.
   * 
   * @return The name of this lawgbook's class.
   */
  public String getTitle ()
  {
    return title;
  }
  
  /**
   * Sets the title of this class given a String.
   * 
   * @param newTitle The new class name to set this lawgbook to.
   */
  public void setTitle (String newTitle)
  {
    title = newTitle;
  }
  
    /**
   * Sets the minimum amount of times each activity needs to be taught. This affects lesson priority ranking.
   *
   * @param num The number to set the minimum amount of times to teach each activity.
   */
  public void setActivityMin (int num)
  {
    activityMin = num; 
  }
  
  /**
   * Returns the minimum amount of times each activity needs to be taught.
   *
   * @return The minimum amount of times to teach each activity.
   */
  public int getActivityMin ()
  {
    return activityMin;
  }
  
  /**
   * Sets the amount of weeks that have passed since the creation of this lawgbook. This is tracked by the amount of times
   * the lawgbook has been updated with a lesson.
   * 
   * @param num Number to set the amount of weeks that have passed.
   */
  public void setWeeksPassed (int num)
  {
    weeksPassed = num;
  }
  
  /**
   * Returns the amount of weeks that have passed since the creation of this lawgbook. This is tracked by the amount of times
   * the lawgbook has been updated with a lesson.
   * 
   * @return The amount of weeks that have passed.
   */
  public int getWeeksPassed ()
  {
    return weeksPassed;
  }
  
  /**
   * Sets the amount of weeks the class runs for. This affects activity priority.
   * 
   * @param num The amount of weeks this class will run for.
   */
  public void setTotalWeeks (int num)
  {
    totalWeeks = num;
  }
  
  /**
   * Returns the amount of weeks the class runs for. This affects activity priority.
   * 
   * @return The amount of weeks this class will run for.
   */
  public int getTotalWeeks ()
  {
    return totalWeeks;
  }
  
  /**
   * Sets the length of a lesson.
   * 
   * @param The length in minutes of a lesson.
   */
  public void setLessonTime (int newLessonTime)
  {
    lessonTime = newLessonTime;
  }
  
  /**
   * Returns the duration of a lesson.
   * 
   * @return The time in minutes of a single lesson.
   */
  public int getLessonTime ()
  {
    return lessonTime;
  }
  /**
   * Creates an empty arraylist of activities of the given size.
   * 
   * @param amount The size for the arraylist to be.
   * @return Empty list of Activity of the given size.
   */
  public ArrayList<Activity> createLessonList (int amount)
  {
    ArrayList<Activity> list = new ArrayList<Activity>();
    return list;
  }
  
  /**
   * Returns the activity at the given index.
   * 
   * @param index The index at which to retrieve the activity.
   * @return The Activity at the given index.
   */
  public Activity getActivity (int index)
  {
    try{
      return activities.get (index);
    }
    catch (IndexOutOfBoundsException e){
      return null;
    }
  }
  
  /**
   * Returns the activity with the given name.
   * 
   * @param name The name of the activity.
   * @return the Activity with the given name. If the activity can't be found, null is returned.
   */
  public Activity getActivity (String name)
  {
    for (Activity a : getActivities()){
      if (a.toString().equals (name)){
      return a;
      }
    }
    return null;
  }
  /**
   * Returns the Student at the given index.
   * 
   * @param index The index to retrieve the Student from.
   * @return The Student at the given index.
   */
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
  
  /**
   * Adds a new Student with the given name to the arrayList of students.
   * All rankings for the new students are set to 0.
   * 
   * @param name The name to be given the new Student.
   */
  public void addStudent (String name)
  {
    Student s = new Student (name);
    for (Activity a : activities)
    {
      s.addRanking (a,0); 
    }
    students.add (s);
  }
  
  /**
   * Adds the given Student to the arrayList of students.
   * This is used when reading in previously saved data.
   * 
   * @param s The Student to add to the data.
   */
  public void addStudent (Student s)
  {
    students.add (s); 
  }
  
  /**
   * Adds a new activity with the given name to the data.
   * All students rank it as 0 and the activity rankings are refreshed.
   * 
   * @param name The name for the new Activity.
   */
  public void addActivity (String name)
  {
    Activity a = new Activity (name);
    activities.add (a);
    for (Student s : students)
    {
      s.addRanking (a,0); 
    }
    rankActivities();
  }
  
  /**
   * Adds a new activity to the data given name and the amount of times it has been taught.
   * All students set the rank for this Activity as 0 and the Activity priority ranking is refreshed.
   * This method is used when reading in previously saved data.
   * 
   * @param name The name for the Activity.
   * @param completed The amount of times this Activity is set to have been taught.
   */
  public void addActivity (String name,int completed)
  {
    Activity a = new Activity (name,completed);
    activities.add (a);
    for (Student s : students)
    {
      s.addRanking (a,0); 
    }
    rankActivities();
  }
  
  /**
   * Adds a new activity to the data given name and the amount of times it has been taught.
   * All students set the rank for this Activity as 0 and the Activity priority ranking is refreshed.
   * This method is used when reading in previously saved data.
   * 
   * @param name The name for the Activity.
   * @param completed The amount of times this Activity is set to have been taught.
   */
  public void addActivity (Activity a)
  {
    activities.add (a);
    for (Student s : students)
    {
      s.addRanking (a,0); 
    }
    rankActivities();
  }
  
  /**
   * Adds a the given Lesson to the data.
   * 
   * @param l The lesson to be added to the data.
   */
  public void addLesson (Lesson l)
  {
    lessons.add (l); 
  }
  
  /**
   * Removes an Activity with the given name from the data.
   * 
   * @param name The name of the activity to remove.
   * @return Whether or not an activity was removed.
   */
  public boolean removeActivity (String name)
  {
    rankActivities();
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
  
  /**
   * Removes a Student with the given name from the data.
   * 
   * @param name The name of the Student to be removed.
   * @return Whether or not a Student was removed.
   */
  public boolean removeStudent (String name)
  {
    rankActivities();
    for (int x = 0; x < students.size();x++)
    {
      Student s = students.get(x);
      if (s.getName().equals (name))
      {
        students.remove (s);
        return true;
      }
    }
    return false;
  }
  
  /**
   * Returns the priority ranking of the given Activity.
   * 
   * @param a The Activity to retrieve the ranking of.
   * @return The ranking of the Activity. A higher number indicates a lower priority.
   * -1 is returned if the Activity can't be found.
   */
  public int getRank (Activity a)
  {
    //Implement this method to determine the priority of an activity
    if (!sorted.isEmpty()){
      rankActivities();
      return sorted.indexOf (a);
    }
    return -1;
  }
  
  /**
   * Depreciated method.
   * Does nothing.
   * @param l Lesson to be printed.
   */
  public void printLesson (Lesson l)
  {
    //Access the printer class
  }
  
  /**
   * Sets the File that the lawgbook is to be saved as.
   * 
   * @param newFile The file to save this lawgbook as.
   */
  public void setFile (File newFile)
  {
    fileName = newFile;
  }
  
  /**
   * Returns the File that this lawgbook is saved as.
   * 
   * @return The file this lawgbook is saved as.
   */
  public File getFile()
  {
    return fileName;
  }
  
  /**
   * Returns whether or not this lawgbook has all data saved in a file.
   * 
   * @return boolean indicating if all the data in the lawgbook is saved.
   */
  public boolean isSaved ()
  {
    return saved;
  }
  
  /**
   * Sets the saved status for this lawgbook.
   * 
   * @param save The status for if this lawgbook is saved. True is saved, and false is unsaved.
   */
  public void setSaved (boolean save)
  {
    saved = save;
  }
  
  /**
   * Clears all data from this lawgbook.
   */
  public void clearData ()
  {
    activities.clear();
    lessons.clear();
    students.clear();
  }
  
  /**
   * Returns the amount of activities in the lawgbook.
   * 
   * @return The amount of activities in the lawgbook.
   */
  public int getNumActivities ()
  {
    return activities.size();
  }
  /**
   * Returns the amount of Students in the lawgbook.
   * 
   * @return The amount of Students in the lawgbook.
   */
  public int getNumStudents ()
  {
    return students.size();
  }
  
  /**
   * Returns the amount of Lessons in the lawgbook.
   * 
   * @return The amount of Lessons in the lawgbook.
   */
  public int getNumLessons ()
  {
    return lessons.size();
  }
  
  /**
   * Returns the ArrayList of Activitys for the lawgbook.
   * 
   * @return The ArrayList of Activities for the lawgbook.
   */
  public ArrayList<Activity> getActivities ()
  {
    return activities;
  }
  /**
   * Returns the ArrayList of Students for the lawgbook.
   * 
   * @return The ArrayList of Students for the lawgbook.
   */
  public ArrayList<Student> getStudents ()
  {
    return students;
  }
  /**
   * Returns the ArrayList of Lessons for the lawgbook.
   * 
   * @return The ArrayList of Lessons for the lawgbook.
   */
  public ArrayList<Lesson> getLessons ()
  {
    return lessons;
  }
  
  /**
   * Sorts the activities in order of priority and stores the sorted list in the sorted ArrayList.
   * The amount of times completed takes priority over the rankings of students unless the times completed
   * has surpassed the minimum amount of times required to be taught.
   */
  public void rankActivities ()
  {
      sorted.clear();
      sorted.addAll (activities);
      Collections.sort (sorted, new Comparator<Activity> (){
        public int compare (Activity a,Activity b)
        {
          int aLeft = (totalWeeks - weeksPassed) - a.getCompleted();
          int bLeft = (totalWeeks - weeksPassed) - b.getCompleted();
          if (aLeft != bLeft && (a.getCompleted() < activityMin || b.getCompleted() < activityMin)){
            if (aLeft < bLeft){
              return 1;
            }
            else{
              return -1;
            }
          }
          else
          {
            int aRanks = 0;
            int bRanks = 0;
            for (Student s : students)
            {
              aRanks += s.getRank (a);
              bRanks += s.getRank (b);
            }
            if (aRanks < bRanks){
              return -1;
            }
            else if (aRanks > bRanks){
              return 1;
            }
            else{
              return 0;
            }
          }
        }
      });
  }
  
  /**
   * Returns a formated filename to have the '.lawg' extension if it doesn't already have it.
   * 
   * @param file The file to be formated.
   * @return The name of the file with the '.lawg' extenstion.
   */
  public static String formatFileName (File file)
  {
    String name = file.getName();
    if (name.length() < 5 || !name.substring(name.length() - 5).equals (".lawg")){
      name = name + ".lawg";
    }
    return name;
  }
  }