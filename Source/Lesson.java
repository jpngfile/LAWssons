import java.util.Date;
import java.util.ArrayList;

/**
 * Data class to contain all necessary data about lessons.
 * 
 * @author Jason P'ng
 * @version 3.0 October 30th, 2014
 */
public class Lesson
{
  /**
   * The date this lesson was made.
   */
  private Date date;
  /**
   * The title of this lesson.
   */
  private String title;
  /**
   * The list of activities this lesson contains.
   */
  private ArrayList<Activity> activities;
  
  /**
   * Constructor of this lesson, setting data with the given title, date, and arrayList of activities.
   * 
   * @param title The title of this lesson.
   * @param date The date this lesson was made.
   * @param activities The list of activities for this lesson to contain.
   */
  public Lesson (String title,Date date, ArrayList<Activity> activities)
  {
    this.title = title;
    this.activities = activities;
    this.date = date;
  }
  
  /**
   * Constructor for this lesson in which no data is given. This constructor is not used.
   */
  public Lesson ()
  {
    activities = new ArrayList<Activity>();
    date = null;
  }
  
  /**
   * Returns the date of this lesson.
   * 
   * @return The date of this lesson.
   */
  public Date getDate ()
  {
    return date;
  }
  
  /**
   * Returns the list of activities of this lesson.
   * 
   * @return The list of activities of this lesson.
   */
  public ArrayList<Activity> getActivities ()
  {
    return activities;
  }
  
  /**
   * Sets the date of this lesson.
   * 
   * @param newDate The date of this lesson.
   */
  public void setDate (Date newDate)
  {
    date = newDate;
  }
  
  /**
   * Sets the list of activiites of this lesson.
   * 
   * @param list The activities of this lesson.
   */
  public void setActivities (ArrayList<Activity> list)
  {
    activities = list;
  }
  
  /**
   * Returns the title of this lesson.
   * 
   * @return The title of this lesson.
   */
  public String getTitle ()
  {
    return title;
  }
  
  /**
   * Sets the title of this lesson.
   * 
   * @param newTitle The title of this lesson.
   */
  public void setTitle (String newTitle)
  {
    title = newTitle;
  }
  
  /**
   * Returns the title of this lesson.
   * 
   * @return The title of the lesson.
   */
  @Override
  public String toString ()
  {
    return getTitle();
  }
}