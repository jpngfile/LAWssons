import java.util.ArrayList;
public class Student
{
  private String name;
  private ArrayList<Ranking> ranks;
  
  public Student ()
  {
   this.name = "No name";
   ranks = new ArrayList<Ranking>();
  }
  public Student (String name)
  {
    this.name = name;
    ranks = new ArrayList<Ranking>();
  }
  
  public String getName ()
  {
    return name;
  }
  
  public void setName (String newName)
  {
    name = newName;
  }
  
  public int getRank (String activity)
  {
    Ranking r = getRanking (activity);
    if (r == null){
      //error
      return Integer.MIN_VALUE;
    }
    return r.getRank();
  }
  
  public int getRank (Activity activity)
  {
    return (getRank (activity.getName()));
  }
  
  //make sure this works. Otherwise use indexes
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
  public boolean setRanking (Activity activity,int rank)
  {
    return setRanking (activity.getName(),rank);
  }
  public boolean addRanking (Activity activity, int rank)
  {
    return addRanking (activity.getName(),rank);
  }
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
  public boolean removeRanking (Activity activity)
  {
    return removeRanking (activity.getName());
  }
  
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
  
  
  
  //Private class to only be edited by Student
  private class Ranking
  {
    private int rank;
    private String activity;
    
    public Ranking ()
    {
      rank = 0;
      activity = null;
    }
    
    public Ranking (String activity)
    {
     this.activity = activity;
     rank = 0;
    }
    
    public Ranking (String activity, int rank)
    {
      this.activity = activity;
      this.rank = rank;
    }
    
    public String getActivity ()
    {
      return activity;
    }
    
    public int getRank ()
    {
      return rank;
    }
    
    public void setActivity (String activity)
    {
      this.activity = activity;
    }
    
    public void setRank (int rank)
    {
      this.rank = rank;
    }
  }
}