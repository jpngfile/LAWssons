import java.util.ArrayList;
/**
 * Data class for storing information on class activities.
 * 
 * @author Jason P'ng
 * @version 3.0 October 30th, 2014
 */
public class Activity
{
  /**
   * The name of the activity.
   */
 private String name;
 /**
  * The amount of times this activity has been done during this class.
  */
 private int completed;
 
 /**
  * List of equipment for this activity.
  */
 private ArrayList<String> items;
 /**
  * Time to execute this activity. Default time is 5 minutes.
  */
 private int time;
 /**
  * Standard constructor given no information. This is not used in the program.
  */
 public Activity ()
 {
   name = null;
   completed = 0;
   time = 5;
   items = new ArrayList<String>();
 }
 
 /**
  * Constructor given the name. Used for creating new activities when completed is automatically 0.
  * 
  * @param name The given name of the activity.
  */
 public Activity (String name)
 {
  this.name = name;
  completed = 0;
  time = 5;
  items = new ArrayList<String>();
 }
  
 /**
  * Constructor with a given name and times completed. This is used when reading in a saved activity that was made before.
  *
  * @param name The given name of the activity.
  * @param completed The amount of times this activity has been taught.
  */
 public Activity (String name, int completed)
 {
   this.name = name;
   this.completed = completed;
   time = 5;
  items = new ArrayList<String>();
 }
 
 /**
  * Sets the name of this activity.
  * 
  * @param newName The name to set this activity to have.
  */
 public void setName (String newName)
 {
   name = newName;
 }
 
 /**
  * Returns the name of this activity.
  * 
  * @return The name of this activity.
  */
 public String getName ()
 {
   return name;
 }
 
 /**
  * Sets the amount of times this activity has been taught.
  * 
  * @param num The number to set the amount of times the activity has been taught.
  * @return true if the the value was changed, false if not. The value doesn't change if the value given is less than 0.
  */
 public boolean setCompleted (int num)
 {
   if (num < 0){
     //error. This cannot be less than 0.
     return false;
   }
   completed = num;
   return true;
 }
 
 /**
  * Returns the amount of times this activity has been taught.
  * 
  * @return The amount of times this activity has been taught.
  */
 public int getCompleted ()
 {
   return completed;
 }
 
 /**
  * Returns the list of items for this activity.
  * 
  * @return The ArrayList of items as part of this activity.
  */
 public ArrayList<String> getItems ()
 {
   return items;
 }
 
  /**
  * Sets the list of items for this activity.
  * 
  * @param The ArrayList of items for this activity.
  */
 public void setItems (ArrayList<String> i)
 {
   items = i;
 }
 
 /**
  * Returns the amount of items this activity requires.
  * 
  * @return the amount of items in the item arrayList
  */
 public int getItemCount ()
 {
   return items.size();
 }
 
 /**
  * Returns the time this activity requires.
  * 
  * @return The time in minutes this activity requires.
  */
 public int getTime ()
 {
   return time;
 }
 
 /**
  * Sets the amount of time in minutes this activity needs.
  * 
  * @param The amount of time in minutes the activity needs.
  */
 public void setTime (int newTime)
 {
   time = newTime;
 }
 
 /**
  * Returns the name of this activity.
  * 
  * @return The name of this activity.
  */
 @Override
 public String toString ()
 {
   return getName();
 }
 
 /**
  * Returns if this activity has the same name as another.
  * 
  * @param b The activity to compare this activity to.
  * @return Whether or not this activity has the same name as the other.
  */
 @Override
 public boolean equals (Object b)
 {
   if (b != null){
   if (b.toString().equals (getName()))
     return true;
   }
   return false;
 }
}