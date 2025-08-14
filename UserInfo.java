package quizGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UserInfo extends JFrame implements ActionListener
{
	JPanel p,p1;JLabel l1,l2;JPasswordField pf;
	JTextField t1;JButton b1,b2,b3;Font fn;
   public UserInfo(String str)
   {
	   super(str);
	   p=new JPanel()
	   {
		  @Override
		  public void paintComponent (Graphics g)
		  {
			  g.drawImage(ImageBox.USER_WINDOW, 0, 0, getWidth(), getHeight(), this);
		  }
	   };
	   this.setContentPane(p);
	   pf=new JPasswordField(30);
	   t1=new JTextField(20);
	   l1=new JLabel("  ENTER USERID  ");
	   l1.setForeground(Color.white);
	   l2=new JLabel("  ENTER PASSWORD  ");
	   l2.setForeground(Color.white);
	   b1=new JButton("LOGIN");
	   b2= new JButton("SIGNUP");
	   b3= new JButton("FORGOT PASSWORD");
	   p1= new JPanel();
	   fn=new Font("Arial",Font.BOLD,20);
	   pf.setFont(fn);
	   t1.setFont(fn);
	   b2.setFont(fn);
	   l1.setFont(fn);
	   l2.setFont(fn);
	   b1.setFont(fn);
	   b3.setFont(fn);
	   p1.add(l1);p1.add(t1);p1.add(l2);p1.add(pf);p1.add(b1);p1.add(b2);p1.add(b3);
	   p1.setOpaque(false);
	   p1.setBackground(new Color(0,0,0,0));
	   setLayout(new GridLayout(5,1,0,70));
	   setDefaultCloseOperation(EXIT_ON_CLOSE);
	   setSize(Toolkit.getDefaultToolkit().getScreenSize());
	   add(new JLabel("")); add(new JLabel(""));
	   add(p1);
	   add(new JLabel("")); add(new JLabel(""));
	   revalidate();
	   repaint();
	   setVisible(true);
	   b1.addActionListener(this);
	   b2.addActionListener(this);
	   b3.addActionListener(this);
	   
   }
@Override
public void actionPerformed(ActionEvent e)
{
	if(e.getSource()==b1)
	{
	if(t1.getText()=="" || (t1.getText()).isEmpty() || pf.getText()=="" || (pf.getText()).isEmpty() || t1.getText()==null || pf.getText()==null)
	{
		JOptionPane.showMessageDialog(this, "PLEASE ENTER VALIDE USER INFORMATION");
	}
	else
	{
		String user,password,stat;
		user=t1.getText();
		password=pf.getText();
		stat="select password,Avatar_no from user where userid='"+user+"'";
		try 
		{
			Connection con=QDB.getConnection();
			Statement st= con.createStatement();
			ResultSet rs=st.executeQuery(stat);
			if(rs.next()==true)
			{
				if((rs.getString("password")).equalsIgnoreCase(password))
				{
					new MainWindow(user,"Let's Quiz",rs.getInt("avatar_no"));
					dispose();
				}
				else
				{
				 JOptionPane.showMessageDialog(this, "INCORRECT PASSWORD PLEASE TRY AGAIN");
				 pf.setText(null);				}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "USER NOT FOUND PLEASE SIGNUP AGAIN");
				t1.setText(null);
				pf.setText(null);
			}
			con.close();
		}
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
	}
	}
	else if(e.getSource()==b2)
	{
		new SignupDialog(this,"PLEASE ENTER REQUIRED DATA TO SIGNUP");
	}
	else
	{
		t1.setText(null);
		pf.setText(null);
		new UpdatePassDialog(this,"CREATE NEW PASSWORD");
	}
	
}

public static void main(String args[])
{
   new UserInfo("ENTER USER DATA TO START");
}
}
