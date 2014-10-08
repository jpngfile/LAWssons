import javax.swing.*;
import java.awt.*;
import javax.swing.table.AbstractTableModel;

public class NewTableModel extends AbstractTableModel
{
  String [] colNames = new String[]{"Activity", "Amount completed", "Student 1", "Student 2"};
   Object [][] data = new Object [][]
   {{"Swimming", new Integer (0), new Integer (3), new Integer (2)},
     {"Diving", new Integer (0), new Integer (1), new Integer (2)},
     {"Tredding", new Integer (0), new Integer (3), new Integer (3)},
     {"First-aid", new Integer (0), new Integer (3), new Integer (1)}
   };
  public NewTableModel ()
  {
    
  }
  
  public String getColumnName (int index)
  {
   return colNames [index]; 
  }
  public int getRowCount()
  {
    return 4;
  }
  
  public int getColumnCount()
  {
    return 4;
  }
  
  public Object getValueAt (int row, int col)
  {
    return data[row][col];
  }
  
}