public class Ranking
{
 String activity;
 int rank;
 public Ranking (String activity, int rank)
 {
   this.activity = activity;
   this.rank = rank;
 }
 public String getActivity ()
 {
  return activity;
 }
 public void setActivity (String newActivity)
 {
  this.activity = newActivity; 
 }
 public int getRank ()
 {
   return rank;
 }
 public void setRank (int newRank)
 {
   this.rank = newRank;
 }
}