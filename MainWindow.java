package quizGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainWindow extends JFrame implements ActionListener 
{
	JButton userbt,b1,b2;ImageIcon im,im1; String user;int iw,ih;
	JPanel p1,p2;JLabel l1;int avatar_no; JFrame userspace; JLayeredPane layer;
	public MainWindow(String user,String args,int avatar_no)
  {
	  super(args);
	  this.avatar_no=avatar_no;
	  this.user=user;
	  layer=new JLayeredPane();
	  addWindowListener(new WindowAdapter()
      {
     	 @Override
     	 public void windowClosing(WindowEvent we)
     	 {
     		 System.exit(0);
     	 }
      });
	 
	  p1=new JPanel() {
		  @Override
		  public void paintComponent(Graphics g)
		  {
			g.drawImage(ImageBox.MAIN_WINDOW, 0, 0, getWidth(), getHeight(), this);
		  }
	  };
	  
	  setContentPane(p1);
	  setSize(Toolkit.getDefaultToolkit().getScreenSize());
	
	  p2=new JPanel();
	  p2.setLayout(new BoxLayout(p2,BoxLayout.PAGE_AXIS));
//	  addMouseListener(new MouseAdapter()
//	  {
//		 @Override
//		 public void mouseClicked(MouseEvent me)
//		 {
//			System.out.println(me.getX()+","+me.getY());
//		 }
//	  });
	  l1= new JLabel("   "+user);
	  l1.setForeground(Color.white);
	   getAvatar(avatar_no,0);
	   setLayout(null);
	  userbt= new JButton(im);
	  userbt.setBackground(Color.black);
      userbt.setVerticalTextPosition(SwingConstants.BOTTOM);
	   userbt.setPreferredSize(new Dimension(im.getIconWidth()-1,im.getIconHeight()));
	   p2.setOpaque(false);
	  p2.setBackground(new Color(0,0,0,0));
	  b1=new JButton(new ImageIcon(ImageBox.QUIZ_START));
	  b1.setBounds(518, 181, 305, 66);
	  b1.setBackground(Color.black);
	  im1= new ImageIcon(ImageBox.QUIZ_CATINFO);
	  b2=new JButton(im1);
	  b2.setBounds(420, 298, im1.getIconWidth()-30, im1.getIconHeight()-35);
	  b2.setBackground(Color.white);
	  l1.setFont(new Font("Arial",Font.BOLD,20));add(l1); 
	  p2.add(userbt);p2.add(l1);
//	  iw=im.getIconWidth()-6 ;ih=im.getIconHeight()+40;
	  iw= new ImageIcon(ImageBox.USER_BUTTON3).getIconWidth()-6 ;ih=new ImageIcon(ImageBox.USER_BUTTON3).getIconHeight()+40;
	  p2.setBounds(1241, 38,iw, ih);
	  layer.add(p2,JLayeredPane.MODAL_LAYER);
	  layer.add(b1,JLayeredPane.PALETTE_LAYER);
	  layer.add(b2,JLayeredPane.PALETTE_LAYER);
	  layer.setBounds(0, 0, getWidth(), getHeight());
	  add(layer);
	  userbt.addActionListener(this);
	    b1.addActionListener(this);
	    b2.addActionListener(this);
	    revalidate();
	    repaint();
	    setVisible(true);
  }
@Override
public void actionPerformed(ActionEvent e)
{
	if(e.getSource()==b2)
	{
	  new DetailDialogBox(this,"IMPORTANT");	
	}
	else if(e.getSource()==userbt)
	{
		if(userspace==null)
		{
		new UserPort("USER SPACE",im,user,this);
		setVisible(false);
		revalidate();
		repaint();
		}
		else
		{
			userspace.setVisible(true);
			setVisible(false);
			revalidate();
			repaint();
		}
	}
	else if(e.getSource()==b1)
	{
		new QuizCatarory(this,"SELECTQUIZ CATAGORY TO START",user,(ImageIcon)userbt.getIcon());
		setVisible(false);
	}
	
}
public void getAvatar(int avatar_no,int no)
{
	if(no==0)
	{
	    if(avatar_no==0)
		     im=new ImageIcon(ImageBox.USER_BUTTON0);
	         
		  else if(avatar_no==1)
			  im=new ImageIcon(ImageBox.USER_BUTTON1);
		  else if(avatar_no==2)
			  im=new ImageIcon(ImageBox.USER_BUTTON2);
		  else if(avatar_no==3)
			  im=new ImageIcon(ImageBox.USER_BUTTON3);
		  else if(avatar_no==4)
			  im=new ImageIcon(ImageBox.USER_BUTTON4);
		  else if(avatar_no==5)
			  im=new ImageIcon(ImageBox.USER_BUTTON5);
		  else if(avatar_no==6)
			  im=new ImageIcon(ImageBox.USER_BUTTON6);
	}
	else
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
	   p2. revalidate();
	   p2. repaint();
	   revalidate();
	   repaint();
	 
}
public void getUser(JFrame f)
{
	userspace=f;
}
}
