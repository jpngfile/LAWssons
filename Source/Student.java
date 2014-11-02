import java.util.ArrayList;

/**
 * Data class for information of a specific Student.
 * 
 * @author Jason P'ng
 * @version 3.0 October 30th, 2014
 */
public class Student
{
  /**
   * The name of the Student.
   */
  private String name;
  
  /**
   * List of the rankings of activities of the Student.
   */
  private ArrayList<Ranking> ranks;
  
  /**
   * Basic constructor of the Student given no info.
   * This is not used in the program
   */
  public Student ()
  {
   this.name = "No name";
   ranks = new ArrayList<Ranking>();
  }
  
  /**
   * Constructor of the Student given the name.
   * 
   * @param name of the Student.
   */
  public Student (String name)
  {
    this.name = name;
    ranks = new ArrayList<Ranking>();
  }
  
  /**
   * Returns the name of the Student.
   * 
   * @return The name of the Student.
   */
  public String getName ()
  {
    return name;
  }
  
  /**
   * Sets the name of the Student.
   * 
   * @param newName The name of the Student.
   */
  public void setName (String newName)
  {
    name = newName;
  }
  
  /**
   * Returns the rank of the given Activity name.
   * 
   * @param activity The name of the Activity.
   * @return the ranking this student has for the given Activity.
   */
  public int getRank (String activity)
  {
    Ranking r = getRanking (activity);
    if (r == null){
      //error
      return Integer.MIN_VALUE;
    }
    return r.getRank();
  }
  
  /**
   * Returns the rank of the given Activity.
   * 
   * @param activity The Activity.
   * @return the ranking this student has for the given Activity.
   */
  public int getRank (Activity activity)
  {
    return (getRank (activity.getName()));
  }
  
  //make sure this works. Otherwise use indexes
  /**
   * Sets the rank of the given Activity name and rank.
   * 
   * @param activity The name of the activity ranking to modify.
   * @param rank The rank to set the Activity to. 
   * @return If the ranking was changed successfully.
   */
  public boolean setRanking (String activity,int rank)
  {
    Ranking r = getRanking (activity);
    if (r == null){
      //error. Ranking is not listed.
      return false;
    }
    else if (rank < 0){
      //error. Ranks cannot be less than 0.
      return false;
    }
    r.setRank (rank);
    return true;
  }
  
  /**
   * Sets the rank of the given Activity name and rank.
   * 
   * @param activity The activity ranking to modify.
   * @param rank The rank to set the Activity to. 
   * @return If the ranking was changed successfully.
   */
  public boolean setRanking (Activity activity,int rank)
  {
    return setRanking (activity.getName(),rank);
  }
  
  /**
   * Adds a  rank of the given Activity and rank.
   * 
   * @param activity The activity to add
   * @param rank The rank to set the Activity to. 
   * @return If the ranking was added successfully.
   */
  public boolean addRanking (Activity activity, int rank)
  {
    return addRanking (activity.getName(),rank);
  }
  
  /**
   * Adds a  rank of the given Activity name and rank.
   * 
   * @param activity The name of the activity to add
   * @param rank The rank to set the Activity to. 
   * @return If the ranking was added successfully.
   */
  public boolean addRanking (String activity, int rank)
  {
    if (getRanking (activity) != null){
      //error. Already has this activity
      return false;
    }
    else if (rank < 0){
      //error. Rank is less than 0.
      return false;
    }
    ranks.add (new Ranking (activity,rank));
    return true;
  }
  
  /**
   * Removes the rank of the given Activity name.
   * 
   * @param activity The name of the activity to remove
   * @return If the ranking was removed successfully.
   */
  public boolean removeRanking (String activity)
  {
    Ranking r = getRanking (activity);
    if (r == null){
      //error. Student is not part of this activity.
      return false;
    }
    ranks.remove (r);
    return true;
  }
  
  /**
   * Removes the rank of the given Activity.
   * 
   * @param activity The Activity to remove
   * @return If the ranking was removed successfully.
   */
  public boolean removeRanking (Activity activity)
  {
    return removeRanking (activity.getName());
  }
  
  /**
   * Returns the Ranking of the given Activity name.
   * 
   * @return The Ranking of the given Activity name.
   */
  private Ranking getRanking (String activity)
  {
    for (Ranking r : ranks)
    {
      if (r.getActivity().equals (activity)){
        return r;
      }
    }
    return null;
  }

  /**
   * Returns the activity of the given index based on the Rankings the Student has.
   * 
   * @param index The index of the Activity.
   * @return The name of the Activity at the given index.
   */
  public String getActivity (int index)
  {
   return ranks.get(index).getActivity(); 
  }
  
  /**
   * Returns the number of ranks the Student has.
   * 
   * @return The number of ranks the Student has.
   */
  public int getNumRanks ()
  {
    return ranks.size();
  }
  
  //Private class to only be edited by Student  
  /**
   * Private data class for storing the rank of a given Activity.
   */
  private class Ranking
  {
    /**
     * The rank of the Activity.
     */
    private int rank;
    /**
     * The Activity this ranking ranks.
     */
    private String activity;
    
    /**
     * Constructor given no data.
     * Activty is considered null and rank is 0.
     */
    public Ranking ()
    {
      rank = 0;
      activity = null;
    }
    
    /**
     * Constructor given the activity name.
     * The rank is set to 0.
     * 
     * @param activity The name of the activity.
     */
    public Ranking (String activity)
    {
     this.activity = activity;
     rank = 0;
    }
    
    /**
     * Constructor given the Activity name and rank.
     * 
     * @param activity The name of the activity.
     * @param rank The rank for the Activity.
     */
    public Ranking (String activity, int rank)
    {
      this.activity = activity;
      this.rank = rank;
    }
    
    /**
     * Returns the activity name of the Ranking.
     * 
     * @return The activity name of the Ranking.
     */
    public String getActivity ()
    {
      return activity;
    }
    
    /**
     * Returns the rank of the Ranking.
     * 
     * @return The rank of the Ranking.
     */
    public int getRank ()
    {
      return rank;
    }
    
    /**
     * Sets the activity name of the Ranking.
     * 
     * @param activity The activity name of the Ranking.
     */
    public void setActivity (String activity)
    {
      this.activity = activity;
    }
    
    /**
     * Sets the rank of the Ranking.
     * 
     * @param rank The rank of the Ranking.
     */
    public void setRank (int rank)
    {
      this.rank = rank;
    }
  }
}