import javax.swing.*;
import java.awt.*;
import javax.swing.table.AbstractTableModel;
import java.util.*;

public class NewTableModel extends AbstractTableModel
{
  //String [] colNames = new String[]{"Activity", "Amount completed", "Student 1", "Student 2"};
  ArrayList<String> colNames = new ArrayList<String>();
   int rowCount;
   int colCount;
  Lawgbook lawgbook = new Lawgbook ();
  public NewTableModel ()
  {
    rowCount = 0;
    colCount = 0;
    addColumn ("Activity");
    addColumn ("Amount completed");
    addColumn ("Rank");
  }
  
  public String getColumnName (int index)
  {
   return colNames.get(index); 
  }
  
  public int getRowCount()
  {
    return rowCount;
  }
  
  public int getColumnCount()
  {
    return colCount;
  }
  
  public void setColumnCount (int num)
  {
   colCount = num; 
  }
  
  public void setRowCount (int num)
  {
    rowCount = num;
  }
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
  
  public void addActivity (String name)
  {
    lawgbook.addActivity (name);///This should remember to add that activity to every student
    rowCount++;
  }
  
  public void addColumn (String name)
  {
   colNames.add (name);
   colCount++;
   if (colCount > 3)
   {
     lawgbook.addStudent (name);//This should take care of everything necessary, like filling the ranking arrayList
   }
  }
  
  public void addColumn (String name,boolean state)
  {
    colNames.add (name);
   colCount++;
  }
  
  //Note: first column, then row
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
  
  public String removeColumn (String name)
  {
    colCount--;
    colNames.remove (name);
    return name;
  }
  
  public void removeActivity (String name)
  {
    if (lawgbook.removeActivity (name)){
      //System.out.println ("success");
      rowCount--;
    }
  }
  public boolean isCellEditable (int rowIndex, int columnIndex)
  {
    if (columnIndex != 2)
    return true;
    return false;
  }
  
  public Lawgbook getLawgbook ()
  {
    return lawgbook;
  }
  //Assuming there is not more than one student of a given name
  public ArrayList<Integer> getStudentInfo (String student)
  {
    return null;
  }
  
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