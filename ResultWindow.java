package quizGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ResultWindow extends JFrame implements Runnable
{
	int ans[];String time, user,cat,diff;int qno;JFrame mw;int i,cq=0;double grade;
	String gr; String qn;JPanel p1,p2,p3,pm; Font fn;JLabel l1,l2,l3,l4,l5,l6;
  public ResultWindow(int ans[],String str,String user,String cat,String diff,int qno,String time,JFrame mw)
  {
	  super(str);
	  this.ans=ans;
	  this.user=user;
	  this.cat=cat;
	  this.diff=diff;
	  this.qno=qno;
	  this.time=time;
	  this.mw=mw;
//	  System.out.println(qno);
	 for(i=0;i<=qno-1;i++)
	 {
		 if(ans[i]==1)
		 {
			 cq+=1;
		 }
	 }
	 double db=(double)cq/qno;
	 grade=db*100;
	 if(grade<=50)
	 {
		 gr="F";
	 }
	 else if(grade>50 && grade<=60)
	 {
		 gr="E";
	 }
	 else if(grade>60 && grade<=70)
	 {
		 gr="D";
	 }
	 else if(grade>70 && grade<=80)
	 {
		 gr="C";
	 }
	 else if(grade>80 && grade<=90)
	 {
		 gr="B";
	 }
	 else if(grade>90 && grade<=100)
	 {
		 gr="A";
	 }
	 qn=cat+" Quiz With "+diff+" Difficulty "+" And Number Of Qustion "+qno;
	 Thread t= new Thread(this);
	 t.start();
	 addWindowListener(new WindowAdapter() 
	 {
		 @Override 
		 public void windowClosing(WindowEvent we)
		 {
			mw.setVisible(true);
			dispose();
		 }
	 });
	 JPanel pp1= new JPanel()
	 {
		 @Override
		 public void paintComponent(Graphics g)
		 {
			 g.drawImage(ImageBox.QUIZ_RESULT, 0, 0, getWidth(), getHeight(), this);
		 }
	 };
	 setContentPane(pp1);
	 fn=new Font("Arial",Font.BOLD,35);
	 setLayout(new GridLayout(3,1,30,10));
	 pm=new JPanel();
	 pm.setLayout(new GridLayout(1,3,17,17));
	 pm.setOpaque(false);
	 pm.setBackground(new Color(0,0,0,0));
	 p1=new JPanel();
	 p1.setLayout(new BoxLayout(p1,BoxLayout.PAGE_AXIS));
	 p1.setOpaque(false);
	 p1.setBackground(new Color(0,0,0,0));
	 l1=new JLabel("               * SCORE : "+grade+"%");
	 l1.setFont(fn);
	 l2=new JLabel("               * TOTAL QUSTIONS : "+qno);
	 l2.setFont(fn);
	 l3=new JLabel("               * CORRECT ANSWERS : "+cq);
	 if(cq<=qno/2)
	 {
		 l3.setForeground(Color.red);
	 }
	 else
	 {
		 l3.setForeground(Color.green);
	 }
	 l3.setFont(fn);
	 p1.add(l1);p1.add(l2);p1.add(l3);
	 p2=new JPanel();
	 p2.setLayout(new BoxLayout(p2,BoxLayout.PAGE_AXIS));
	 p2.setOpaque(false);
	 p2.setBackground(new Color(0,0,0,0));
	 l4=new JLabel("              * TOPIC : "+cat);
	 l4.setFont(fn);
	 l5=new JLabel("              * GRADE : "+gr);
	 l5.setFont(fn);
	 if(gr.equalsIgnoreCase("f") || gr.equalsIgnoreCase("E"))
	 {
		 l5.setForeground(Color.red);
	 }
	 else
	 {
		 l5.setForeground(Color.green);
	 }
	 String fin;
	 if(gr.equalsIgnoreCase("F"))
	 {
		 fin="Fail";
	 }
	 else
	 {
		 fin="Pass";
	 }
	 l6=new JLabel("              * FINAL : "+fin);
	 l6.setFont(fn);
	 if(gr.equalsIgnoreCase("F"))
	 {
		 l6.setForeground(Color.red);
	 }
	 else
	 {
		 l6.setForeground(Color.green);
	 }
	 p2.add(l4);p2.add(l5);p2.add(l6);
	 pm.add(p1);pm.add(p2);
	 add(new JLabel(""));add(pm);add(new JLabel(""));
	 setSize(Toolkit.getDefaultToolkit().getScreenSize());
	 setVisible(true);
	 
			 }
@Override
public void run() 
{
	Date d= new Date();
	SimpleDateFormat sf=new SimpleDateFormat("dd/MM/yyyy");
	String query="insert into quiz_history values(?,?,?,?,?,?,?,?,?)";
	try 
	{
		Connection con=QDB.getConnection();
		PreparedStatement ps= con.prepareStatement(query);
		ps.setString(1, user);
		ps.setString(2, qn);
		ps.setString(3, sf.format(d));
		ps.setDouble(4, grade);
		ps.setString(5, cat);
		ps.setString(6, diff);
		ps.setInt(7, cq);
		ps.setString(8, time);
		ps.setString(9, gr);
		ps.executeUpdate();
		con.close();
	} 
	catch (SQLException e)
	{
		e.printStackTrace();
	}
	
}
}
