package quizGame;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignupDialog extends Dialog implements ActionListener
{
	static Frame f=null;
	JLabel l1,l2,l3;JPasswordField pf,pf1;
	JTextField t1;JButton b1,b2;Font fn;
    public SignupDialog(JFrame f1,String str)
    {
         super(f,str);
         setVisible(true);
         setSize(500,300);
         setLocationRelativeTo(f1);
         setModal(true);
         addWindowListener(new WindowAdapter()
         {
        	 @Override
        	 public void windowClosing(WindowEvent we)
        	 {
        		 dispose();
        	 }
         });
         setLayout(new FlowLayout());
       pf=new JPasswordField(28);
       pf1=new JPasswordField(28);
  	   t1=new JTextField(20);
  	   l1=new JLabel("ENTER USERID");
  	   l3=new JLabel("CONFIRM PASSWORD");
  	   l2=new JLabel("ENTER PASSWORD");
  	   b1=new JButton("SAVE");
  	   b2= new JButton("BACK");
  	   fn=new Font("Arial",Font.BOLD,20);
  	   pf.setFont(fn);
  	   pf1.setFont(fn);
  	   t1.setFont(fn);
  	   l3.setFont(fn);
  	   b2.setFont(fn);
  	   l1.setFont(fn);
  	   l2.setFont(fn);
  	   b1.setFont(fn);
  	   add(l1);add(t1);add(l2);add(pf);add(l3);add(pf1);add(b1);add(b2);
  	   b1.addActionListener(this);
	   b2.addActionListener(this);
	   setBackground(Color.LIGHT_GRAY);
    }
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)
		{
		if(t1.getText()=="" || (t1.getText()).isEmpty() || pf.getText()=="" || (pf.getText()).isEmpty() || t1.getText()==null || pf.getText()==null || pf1.getText()=="" || (pf1.getText()).isEmpty() || pf1.getText()==null)
		{
			JOptionPane.showMessageDialog(this, "PLEASE ENTER VALIDE USER INFORMATION");
		}
		else if(((pf.getText()).equals(pf1.getText()))==false)
		{
			JOptionPane.showMessageDialog(this, "CONFIRM PASSWORD IS INCORRECT");
			  t1.setText(null);
			  pf.setText(null);
			  pf1.setText(null);
		}
		else
		{
			String user,password,stat,stat1;
			user=t1.getText();
			password=pf.getText();
//			stat="insert into user(userid,password,quiz_no) values ('"+user+"','"+password+"',?)";
			stat="insert into user(userid,password,quiz_no) values (?,?,?)";
			try 
			{
				Connection con=QDB.getConnection();
				stat1="select * from user where userid='"+user+"'";
				Statement stt= con.createStatement();
				ResultSet rst=stt.executeQuery(stat1);
				if(rst.next())
				{
				 JOptionPane.showMessageDialog(this, "USERID NOT AVAILABE");
				  t1.setText(null);
				  pf.setText(null);
				  pf1.setText(null);
				}
				else
				{
				PreparedStatement st= con.prepareStatement(stat);
			     st.setString(1, user);
			     st.setString(2, password);
			     st.setInt(3, 0);
				int rs=st.executeUpdate();
				if(rs!=0)
				{
					JOptionPane.showMessageDialog(this, "SIGNUP SUCCESSFULLY");
					  t1.setText(null);
					  pf.setText(null);
					  pf1.setText(null);
				}
			}
				con.close();
			}
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		}
		}
		else
		{
			dispose();
		}
		
	}
}
