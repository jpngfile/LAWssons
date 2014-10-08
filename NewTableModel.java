import javax.swing.*;
import java.awt.*;
import javax.swing.table.AbstractTableModel;
import java.util.*;

public class NewTableModel extends AbstractTableModel
{
  //String [] colNames = new String[]{"Activity", "Amount completed", "Student 1", "Student 2"};
  ArrayList<String> colNames = new ArrayList<String>();
//   Object [][] data = new Object [][]
//   {{"Swimming", new Integer (0), new Integer (3), new Integer (2)},
//     {"Diving", new Integer (0), new Integer (1), new Integer (2)},
//     {"Tredding", new Integer (0), new Integer (3), new Integer (3)},
//     {"First-aid", new Integer (0), new Integer (3), new Integer (1)}
//   };
  //Will have to reorganize the data to a different form later
  ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();
   int rowCount;
   int colCount;
  public NewTableModel ()
  {
    rowCount = 0;
    colCount = 0;
    addColumn ("Activity");
    addColumn ("Amount completed");
    addColumn ("Student 1");
    addColumn ("Student 2");
    addActivity ("Swimming");
    addActivity ("Diving");
    addActivity ("Tredding");
    addActivity ("First-aid");
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
  
  public Object getValueAt (int row, int col)
  {
    return data.get(col).get(row);
  }
  
  public void addActivity (String name)
  {
    data.get(0).add (name);
    rowCount++;
    for (int x = 1; x < data.size();x++)
    {
      data.get(x).add ("0");
    }
  }
  
  public void addColumn (String name)
  {
   colNames.add (name);
   colCount++;
   ArrayList<Object> list = new ArrayList<Object>();
    for (int x =0; x < getRowCount();x++)
    {
      list.add (new Integer (0));
    }
   data.add (list);
   //System.out.println ("Data size: " + data.size() + " Column count: " + getColumnCount());
  }
  
  //Note: first column, then row
  public void setValueAt (Object obj,int rowIndex, int columnIndex)
  {
    if (columnIndex > 0)
    {
      String s = obj.toString ();
      if (!isNumeric (s)){
        return;
      }
    }
    data.get(columnIndex).remove (rowIndex);
    data.get(columnIndex).add (rowIndex, obj);
  }
  public boolean isCellEditable (int rowIndex, int columnIndex)
  {
    return true;
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