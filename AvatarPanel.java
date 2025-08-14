package quizGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class AvatarPanel extends JPanel implements ActionListener
{
	MainWindow mw; JButton b0,b1,b2,b3,b4,b5,b6;
	ImageIcon im1,im2,im3,im4,im5,im6,im0;
	JToolBar tb;String user;JPanel p1; JPanel uf;UserPort up;
 public AvatarPanel(MainWindow mw,String user,JPanel uf,UserPort up)
 {
	 this.user=user;
	 this.mw=mw;
	 this.up=up;
	 setLayout(new GridLayout(5,1,10,10));
	 im0= new ImageIcon(ImageBox.USER_BUTTON0);
	 im1=new ImageIcon(ImageBox.USER_BUTTON1);
	 im2=new ImageIcon(ImageBox.USER_BUTTON2);
	 im3=new ImageIcon(ImageBox.USER_BUTTON3);
	 im4=new ImageIcon(ImageBox.USER_BUTTON4);
	 im5=new ImageIcon(ImageBox.USER_BUTTON5);
	 im6=new ImageIcon(ImageBox.USER_BUTTON6);
	 b0= new JButton(im0);
	 b1= new JButton(im1);
	 b2= new JButton(im2);
	 b3= new JButton(im3);
	 b4= new JButton(im4);
	 b5= new JButton(im5);
	 b6= new JButton(im6);
	 tb=new JToolBar();
	 p1=new JPanel();
     p1.add(tb);
     tb.add(b0);
	 tb.add(b1);
	 tb.add(b2);
	 tb.add(b3);
	 tb.add(b4);
	 tb.add(b5);
	 tb.add(b6);
	 tb.setEnabled(false);
	 p1.setOpaque(false);
	 p1.setBackground(new Color(0,0,0,0));
	 tb.setOrientation(JToolBar.HORIZONTAL);
	 add(new JLabel("")); add(new JLabel(""));
	 add(p1); add(new JLabel("")); add(new JLabel(""));
	 setBackground(Color.LIGHT_GRAY);
	 b0.addActionListener(this);
	 b1.addActionListener(this);
	 b2.addActionListener(this);
	 b3.addActionListener(this);
	 b4.addActionListener(this);
	 b5.addActionListener(this);
	 b6.addActionListener(this);
	 
 }
 @Override 
 public void paintComponent(Graphics g)
 {
	g.drawImage(ImageBox.PROFILE_PANEL, 0, 0, getWidth(), getHeight(), this); 
 }
@Override
public void actionPerformed(ActionEvent e)
{
	ImageIcon im=(ImageIcon) ((JButton)e.getSource()).getIcon();
	int n=0;
	if(im==im0)
	{
		n=0;
	}
	else if(im==im1)
	{
		n=1;
	}
	else if(im==im2)
	{
		n=2;
	}
	else if(im==im3)
	{
		n=3;
	}
	else if(im==im4)
	{
		n=4;
	}
	else if(im==im5)
	{
		n=5;
	}
	else if(im==im6)
	{
		n=6;
	}
	try 
	{
		Connection con= QDB.getConnection();
		PreparedStatement ps= con.prepareStatement("update user set avatar_no=? where userid='"+user+"'");
	     ps.setInt(1, n);
	     ps.executeUpdate();
	     mw.getAvatar(n,1);
	     up.setIcon(n);
	     up.p1.revalidate();
	     up.p1.repaint();
	} 
	catch (SQLException e1)
	{
		e1.printStackTrace();
	}
}
}
