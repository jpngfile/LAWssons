import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Table model for the table within the DisplayPanel.
 * 
 * @author Jason P'ng
 * @version 3.0 October 30th, 2014
 */
public class NewTableModel extends AbstractTableModel
{
  //String [] colNames = new String[]{"Activity", "Amount completed", "Student 1", "Student 2"};
  /**
   * Arraylist of the column headers
   */
  ArrayList<String> colNames = new ArrayList<String>();
  
  /**
   * Keeps track of the amount of rows, which is equal to the amount of activities.
   */
   int rowCount;   
   /**
    * Keeps track of the amount of columns, which is the amount of students + 3.
    */
   int colCount;   
   /**
    * The lawgbook data class to retrieve data from.
    */
  Lawgbook lawgbook = new Lawgbook ();
  
  /**
   * Constructor to set the amount of rows and columns and add necessary columns.
   * The starting columns are "Activity", "Amount Completed", and "Rank".
   */
  public NewTableModel ()
  {
    rowCount = 0;
    colCount = 0;
    addColumn ("Activity");
    addColumn ("Amount completed");
    addColumn ("Rank");
  }
  
  /**
   * Returns the column header at the given index.
   * 
   * @param index The index of the column
   * @return The header name of the column
   */
  public String getColumnName (int index)
  {
   return colNames.get(index); 
  }
  
  /**
   * Returns the amount of rows in the table.
   * 
   * @return The amount of rows in the table.
   */
  public int getRowCount()
  {
    return rowCount;
  }
  
  /**
   * Returns the amount of columns in the table.
   * 
   * @return The amount of columns in the table.
   */
  public int getColumnCount()
  {
    return colCount;
  }
  
  /**
   * Sets the recorded number of columns in the table.
   * 
   * @param num The amount of columns in the table.
   */
  public void setColumnCount (int num)
  {
   colCount = num; 
  }
  
  /**
   * Sets the recorded number of rows in the table.
   * 
   * @param num The amount of rows in the table.
   */
  public void setRowCount (int num)
  {
    rowCount = num;
  }
  
  /**
   * Returns the value at a given row and column.
   * If in the first column, it returns an Activity name.
   * If in the second column, it returns the amount completed of an Activity.
   * If in the third column, it returns the priority rank of an Activity.
   * Otherwise it returns the Student rank of an Activity.
   * If the row or column isn't in the table, "Null is returned"
   * 
   * @return value at a cell in the table.
   */
  public Object getValueAt (int row, int col)
  {
    try{
    switch (col)
    {
      case 0 : return lawgbook.getActivity (row).getName();
      case 1 : return lawgbook.getActivity (row).getCompleted();
      case 2 : return lawgbook.getRank (lawgbook.getActivity (row));
      default : return lawgbook.getStudent (col - 3).getRank(lawgbook.getActivity (row));
    }
    }
    catch (NullPointerException e)
    {
     return "Null"; 
    }
  }
  
  /**
   * Adds a new activity to the lawgbook with the given name.
   * 
   * @param name The name of the new activity.
   */
  public void addActivity (String name)
  {
    lawgbook.addActivity (name);///This should remember to add that activity to every student
    rowCount++;
  }
  
  /**
   * Adds a new column to the table with the given header name.
   * 
   * @param name The header name for the new column.
   */
  public void addColumn (String name)
  {
   colNames.add (name);
   colCount++;
   if (colCount > 3)
   {
     lawgbook.addStudent (name);//This should take care of everything necessary, like filling the ranking arrayList
   }
  }
  
  /**
   * Adds a new colummn to the table with the given header name.
   * This just adds the column and does not assume it to be a Student.
   * 
   * @param name The header name for the column.
   * @param state Overload to make the method distinct.
   */
  public void addColumn (String name,boolean state)
  {
    colNames.add (name);
    colCount++;
  }
  
  //Note: first column, then row
  /**
   * Sets the value at a given row and column.
   * This will take into consideration the column so that: <br>
   * 1st column: Changes the name of an activity <br>
   * 2nd column: Changes the amount complted of an activity <br>
   * 3rd column: Unchangeable since it is the activity ranking <br>
   * Anything else : Changing student ranking of an activity. <br>
   * It will also make sure that numeric values can only be changed to other numeric values.
   * 
   * @param obj The value to set the selected column to be.
   * @param rowIndex The row of the cell to change.
   * @param columnIndex The column of the cell to change.
   */
  public void setValueAt (Object obj,int rowIndex, int columnIndex)
  {
    String s = obj.toString ();
    if (columnIndex > 0)
    {      
      if (!isNumeric (s)){
        return;
      }
    }
    lawgbook.rankActivities();
    for (int x = 0;x < lawgbook.getNumActivities();x++){
    fireTableCellUpdated (x,2);
    }
    switch (columnIndex)
    {
      case 0 : lawgbook.getActivity (rowIndex).setName (s);
        break;
      case 1 : lawgbook.getActivity (rowIndex).setCompleted (Integer.parseInt (s));
        break;
      case 2: //This doesn't work
        break;
      default : lawgbook.getStudent (columnIndex - 3).setRanking (lawgbook.getActivity (rowIndex),Integer.parseInt (s));
    }    
    lawgbook.setSaved (false);
  }
  
  /**
   * Removes the student with the given name from the lawgbook.
   * 
   * @param name The name of the Student to remove.
   * @return If the Student was successfuly removed.
   */
  public boolean removeStudent (String name)
  {
    if (lawgbook.removeStudent(name)){
      colCount--;
      colNames.remove (name);
      return true;
      //System.out.println ("Name: " + colNames.get(colCount - 1));
    }
    return false;
  }
  
  /**
   * Removes the column with the given header name.
   * 
   * @param name The header of the column to remove.
   * @return The name of the column that was removed.
   */
  public String removeColumn (String name)
  {
    colCount--;
    colNames.remove (name);
    return name;
  }
  
  /**
   * Removes the activity with the given name from the lawgbook.
   * 
   * @param name The name of the activty to remove.
   */
  public void removeActivity (String name)
  {
    if (lawgbook.removeActivity (name)){
      //System.out.println ("success");
      rowCount--;
    }
  }
  
  /**
   * Returns if the cell at the given row and column is considered editeable. Every cell is editeable except for the 3rd column.
   * 
   * @param rowIndex The row of the cell.
   * @param columnIndex The column of the cell.
   * @return If the cell is editeable. It is always true unless the column index is 2.
   */
  public boolean isCellEditable (int rowIndex, int columnIndex)
  {
    if (columnIndex != 2)
    return true;
    return false;
  }
  
  /**
   * Returns the lawgbook that this TableModel accesses.
   * 
   * @return The Lawgbook the TableModel uses.
   */
  public Lawgbook getLawgbook ()
  {
    return lawgbook;
  }
  //Assuming there is not more than one student of a given name
  /**
   * Depreciated method
   * @param student The student to find the info of.
   * @return null.
   */
  public ArrayList<Integer> getStudentInfo (String student)
  {
    return null;
  }
  
  /**
   * Returns the integer value of an Object. Depreciated.
   * 
   * @param j The Object to return the integer value of.
   * @return The integer value of an Object.
   */
  public Integer integerOf (Object j)
  {
    try
    {
      return Integer.parseInt (j.toString());
    }
    catch (NumberFormatException e)
    {
      return null;
    }
  }
  
  /**
   * Returns if the given String is of a number or not.
   * 
   * @param s The String to examine for being numeric.
   * @return If the String is a numeric value.
   */
  public static boolean isNumeric (String s)
  {
   try
   {
    double d = Double.parseDouble (s); 
   }
   catch (NumberFormatException e)
   {
     return false;
   }
   return true;
  }
}