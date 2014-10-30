import java.util.*;

public class Lesson
{
  private Date date;
  private String title;
  private ArrayList<Activity> activities;
  
  public Lesson (String title,Date date, ArrayList<Activity> activities)
  {
    this.title = title;
    this.activities = activities;
    this.date = date;
  }
  
  public Lesson ()
  {
    activities = new ArrayList<Activity>();
    date = null;
  }
  
  public Date getDate ()
  {
    return date;
  }
  
  public ArrayList<Activity> getActivities ()
  {
    return activities;
  }
  
  public void setDate (Date newDate)
  {
    date = newDate;
  }
  
  public void setActivities (ArrayList<Activity> list)
  {
    activities = list;
  }
  
  public String getTitle ()
  {
    return title;
  }
  
  public void setTitle (String newTitle)
  {
    title = newTitle;
  }
  
  public String toString ()
  {
    return getTitle();
  }
}