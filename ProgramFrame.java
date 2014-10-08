import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ProgramFrame extends JFrame implements ActionListener
{
 public ProgramFrame ()
 {
   super ("Lawssons");
   //add (new IntroPanel ());
   DisplayPanel display = new DisplayPanel ();
   getContentPane().add (display);
   JMenuItem newItem = new JMenuItem ("New");
   JMenuItem openItem = new JMenuItem ("Open");
   JMenuItem saveItem = new JMenuItem ("Save");
   JMenuItem saveAsItem = new JMenuItem ("SaveAs");
   JMenuItem quitItem = new JMenuItem ("Quit");
   
   JMenu file = new JMenu ("File");
   file.add (newItem);
   file.add (openItem);
   file.add (saveItem);
   file.add (saveAsItem);
   file.add (quitItem);
   
   JMenuBar fileMenu = new JMenuBar ();
   fileMenu.add (file);
   setJMenuBar (fileMenu);
   
   newItem.addActionListener (this);
   openItem.addActionListener (this);
   saveItem.addActionListener (this);
   saveAsItem.addActionListener (this);
   quitItem.addActionListener (this);
  //setLocation (0,0);
  
  addKeyListener (new KListen ());
  setVisible (true);
  setSize ((int)(display.getPreferredSize().getWidth() + 15),(int)display.getPreferredSize().getHeight());
  setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
 }
 
 public void actionPerformed (ActionEvent ae)
 {
  String a = ae.getActionCommand ();
  if (a.equals ("Quit"))
  {
    System.exit (0);
  }
 }
 
 class KListen extends KeyAdapter
 {
  public void keyPressed (KeyEvent k)
  {
    int a = k.getKeyCode();
  }
 }
}