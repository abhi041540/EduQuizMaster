package quizGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

public class UserPort extends JFrame implements ActionListener
{
	 static JButton userbt;JButton back,profile,avatar,history;String user;MainWindow mw;	
	JSplitPane jsp; JPanel ufet,p1,dp;JLabel userid;JScrollPane js;ImageIcon im;
public UserPort(String str,ImageIcon im,String user,MainWindow mw)
{
	super(str);
	this.user=user;
	this.mw=mw;
	this.im=im;
	userbt=new JButton(im);
	userbt.setBackground(Color.white);
	back=new JButton("BACK");
	back.setFont(new Font("Arial",Font.BOLD,20));
	back.setBackground(Color.white);
	profile=new JButton("PROFILE");
	profile.setFont(new Font("Arial",Font.BOLD,20));
	profile.setBackground(Color.white);
	avatar=new JButton("AVATAR");
	avatar.setFont(new Font("Arial",Font.BOLD,20));
	avatar.setBackground(Color.white);
	history=new JButton("HISTORY");
	history.setFont(new Font("Arial",Font.BOLD,20));
	history.setBackground(Color.white);
	
	
	userid=new JLabel("     "+user);
	userid.setFont(new Font("Arial",Font.BOLD,20));
	p1=new JPanel() ;
	dp=new JPanel()
	{
		@Override
		public void paintComponent(Graphics g)
		{
			g.drawImage(ImageBox.USER_DEFAULT_PANEL, 0, 0, getWidth(), getHeight(), this);
		}
	};
	p1.setLayout(new BoxLayout(p1,BoxLayout.PAGE_AXIS));
	p1.add(userbt);p1.add(userid);
    setSize(Toolkit.getDefaultToolkit().getScreenSize());
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    jsp=new JSplitPane();
    jsp.setEnabled(false);
    jsp.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
    ufet=new JPanel();
    ufet.setLayout(new GridLayout(6,1,10,10));
    p1.setOpaque(false);
    p1.setBackground(new Color(0,0,0,0));
    ufet.add(p1);ufet.add(profile);ufet.add(avatar);ufet.add(history);ufet.add(back);
   ufet.setPreferredSize(new Dimension(im.getIconWidth()+70,getHeight()+90));ufet.setBackground(Color.white);
    js= new JScrollPane(ufet);
    jsp.setLeftComponent(js);
    jsp.setRightComponent(dp);
    add(jsp);
    setVisible(true);
    back.addActionListener(this);
    profile.addActionListener(this);
    avatar.addActionListener(this);
    history.addActionListener(this);
    
    
    
}
public void setIcon(int avatar_no)
{
	if(avatar_no==0)
	     userbt.setIcon(new ImageIcon(ImageBox.USER_BUTTON0));
	  else if(avatar_no==1)
		  userbt.setIcon(new ImageIcon(ImageBox.USER_BUTTON1));
	  else if(avatar_no==2)
		  userbt.setIcon(new ImageIcon(ImageBox.USER_BUTTON2));
	  else if(avatar_no==3)
		  userbt.setIcon(new ImageIcon(ImageBox.USER_BUTTON3));
	  else if(avatar_no==4)
		  userbt.setIcon(new ImageIcon(ImageBox.USER_BUTTON4));
	  else if(avatar_no==5)
		  userbt.setIcon(new ImageIcon(ImageBox.USER_BUTTON5));
	  else if(avatar_no==6)
		  userbt.setIcon(new ImageIcon(ImageBox.USER_BUTTON6));
}
@Override
public void actionPerformed(ActionEvent e)
 {
   if(e.getSource()==back)
   {
	   mw.setVisible(true);
	   mw.getUser(this);
	   setVisible(false);
   }
   else if(e.getSource()==avatar)
   {
	  jsp.setRightComponent(new AvatarPanel(mw,user,ufet,this)); 
   }
   else if(e.getSource()==profile)
   {
	   jsp.setRightComponent(new ProfilePanel((ImageIcon)userbt.getIcon(),user,this));
   }
   else if(e.getSource()==history)
   {
	  jsp.setRightComponent(new QuizHistory(user));
   }
   
 }

}

