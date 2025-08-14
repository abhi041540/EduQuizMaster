package quizGame;

import java.awt.Dialog;
import java.awt.Graphics;
import java.awt.Panel;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DetailDialogBox extends JDialog
{
	ImageIcon im; JPanel p1;
   public DetailDialogBox(JFrame f,String args)
   {
	   super(f,args);
     setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	  
	   im=new ImageIcon(ImageBox.QUIZ_CATDETAILS);
	   setSize(750,500);
	   setLocationRelativeTo(f);
   p1=new JPanel() 
   {
   @Override
   public void paintComponent(Graphics g)
   {
	   g.drawImage(ImageBox.QUIZ_CATDETAILS, 0, 0, getWidth(), getHeight(), this);
   }
   };
   setContentPane(p1);
   repaint();
   this.setModal(true);
   setVisible(true);
  }
}
