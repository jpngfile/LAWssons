import java.util.*;

public class Lesson
{
  private Date date;
  private ArrayList<Activity> activities;
  
  public Lesson (Date date, ArrayList<Activity> activities)
  {
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
  
  public void setActivites (ArrayList<Activity> list)
  {
    activities = list;
  }
  
}