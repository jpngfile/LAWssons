public class Activity
{
 private String name;
 private int completed;
 
 public Activity ()
 {
   name = null;
   completed = 0;
 }
 
 public Activity (String name)
 {
  this.name = name;
  completed = 0;
 }
  
 public Activity (String name, int completed)
 {
   this.name = name;
   this.completed = completed;
 }
 
 public void setName (String newName)
 {
   name = newName;
 }
 
 public String getName ()
 {
   return name;
 }
 
 public boolean setCompleted (int num)
 {
   if (num < 0){
     //error. This cannot be less than 0.
     return false;
   }
   completed = num;
   return true;
 }
 
 public int getCompleted ()
 {
   return completed;
 }
 
 public String toString ()
 {
   return getName();
 }
 
 public boolean equals (Activity b)
 {
   if (b.getName().equals (getName()))
     return true;
   return false;
 }
}