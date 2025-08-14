package quizGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DataPanel extends JPanel implements Runnable,ActionListener
{
	JButton b1,b2,b3,b4;String had[],data[][],user,name,date,duration;JDialog jd; Thread t;JPanel mp;
   public DataPanel(String name,String cat,String grad,String user,JPanel mp,String date,String duration)
   {
	   this.user=user;
	   this.name=name;
	   this.date=date;
	   this.duration=duration;
	   this.mp=mp;
	   had=new String[2];
	   data=new String[8][2];
	   t= new Thread(this,"QUIZTABLE");   
	   t.start();
	   b1=new JButton(name);
	   b2=new JButton(cat);
	   b3=new JButton(grad);
	   b4=new JButton("OPEN");
	   b1.setBackground(Color.white);
	   b2.setBackground(Color.white);
	   b4.setBackground(Color.white);
	   if(grad.equalsIgnoreCase("F"))
	   {
		   b3.setBackground(Color.red);  
	   }
	   else
	   {
		   b3.setBackground(Color.green);
	   }
	   setOpaque(false);
	   setBackground(new Color(0,0,0,0));
	   b1.setFont(new Font("Arial",Font.BOLD,20));
	   b2.setFont(new Font("Arial",Font.BOLD,20));
	   b3.setFont(new Font("Arial",Font.BOLD,20));
	   b4.setFont(new Font("Arial",Font.BOLD,20));
	   add(b1);add(b2);add(b3);add(b4);
	   b4.addActionListener(this);
   }
@Override
public void run()
{
	String qua="select * from quiz_history where userid='"+user+"'and quiz_name='"+name+"' and quiz_duration='"+duration+"'and quiz_date='"+date+"'";
	had[0]="DATA TYPE";
	had[1]="DATA VALUES";
	data[0][0]="quiz_name";
	data[1][0]="quiz_date";
	data[2][0]="quiz_result";
	data[3][0]="quiz_catagory";
	data[4][0]="quiz_type";
	data[5][0]="quiz_performance";
	data[6][0]="quiz_duration";
	data[7][0]="quiz_grade";
	try 
	{
		Connection con= QDB.getConnection();
		Statement st= con.createStatement();
		ResultSet rs= st.executeQuery(qua);
		for(;rs.next()!=false;)
		{
			data[0][1]=rs.getString("quiz_name");
			data[1][1]=rs.getString("quiz_date");
			data[2][1]=String.valueOf(rs.getDouble("quiz_result"));
			data[3][1]=rs.getString("quiz_catag");
			data[4][1]=rs.getString("quiz_type");
			data[5][1]=String.valueOf(rs.getInt("quiz_peformance"));
			data[6][1]=rs.getString("quiz_duration");
			data[7][1]=rs.getString("quiz_grade");
		}
		con.close();
	}
	catch (SQLException e) 
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
@Override
public void actionPerformed(ActionEvent e) 
{
	
	try 
	{
		t.join();
	} 
	catch (InterruptedException e1) 
	{
		e1.printStackTrace();
	}
	JTable tb= new JTable(data,had);
	JScrollPane jsc= new JScrollPane(tb);
	JPanel pj= new JPanel()
	{
		@Override
		public void paintComponent(Graphics g)
		{
			g.drawImage(ImageBox.QUIZ_BACKGROUND,0, 0, getWidth(), getHeight(), this);
		}
	};
	jd=new JDialog();
	jd.setSize(600,200);
	jd.setModal(true);
	jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	jd.add(jsc);
	jd.setLocationRelativeTo(mp);
	jd.setVisible(true);
	jd.setBackground(new Color(15,20,54));
	
}
}
