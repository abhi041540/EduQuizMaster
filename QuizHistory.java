package quizGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;


public class QuizHistory extends JPanel implements Runnable 
{
	JSplitPane js; String qname,user,cat,grade;int qno=0;double res;
	JPanel rp,lp;JScrollPane scp; JButton qn;JProgressBar pba;
    public QuizHistory (String user)
    {
    	this.user=user;
    	rp= new JPanel();
    	setOpaque(false);
    	setBackground(new Color(0,0,0,0));
    	rp.setBackground(Color.white);
    	Thread t= new Thread(this,"QUIZ THREAD");
    	t.start();
    	js= new  JSplitPane();
    	js.setEnabled(false);
    	js.setOrientation(JSplitPane.VERTICAL_SPLIT);
    	js.setOpaque(false);
    	js.setBackground(new Color(0,0,0,0));
    	
    	rp.setLayout(new BoxLayout(rp, BoxLayout.PAGE_AXIS));
    	scp=new JScrollPane(rp);
    	scp.setOpaque(false);
    	scp.setBackground(new Color(0,0,0,0));
    	try 
    	{
			t.join();
		} 
    	catch (InterruptedException e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	res=res/qno;
    	lp=new JPanel();
    	lp.setOpaque(false);
    	lp.setBackground(new Color(0,0,0,0));
    	qn=new JButton("NUMBER OF QUIZES : "+qno);
    	qn.setBackground(Color.white);
    	qn.setFont(new Font("Arial",Font.BOLD,20));
    	pba= new JProgressBar();
    	pba.setOrientation(JProgressBar.HORIZONTAL);
    	pba.setMaximum(100);
    	pba.setMinimum(0);
    	pba.setValue((int)res);
    	if(res<=50)
    	{
    		pba.setForeground(Color.red);
    	}
    	else
    	{
    		pba.setForeground(Color.green);
    	}
    	pba.setStringPainted(true);
    	pba.setFont(new Font("Arial",Font.BOLD,20));
    	lp.add(qn);lp.add(pba);
    	js.setLeftComponent(lp);
    	js.setRightComponent(scp);
    	add(js);
    	
    }
    @Override
    public void paintComponent(Graphics g)
    {
    	g.drawImage(ImageBox.PROFILE_PANEL, 0, 0, getWidth(), getHeight(), this);
    }
	@Override
	public void run()
	{
		String qua="select * from quiz_history where userid='"+user+"'";
		try 
		{
			Connection con= QDB.getConnection();
			Statement st= con.createStatement();
			ResultSet rs= st.executeQuery(qua);
			for(;rs.next()!=false;)
			{
				rp.add(new DataPanel(rs.getString("quiz_name"),rs.getString("quiz_catag"),rs.getString("quiz_grade"),user,this,rs.getString("quiz_date"),rs.getString("quiz_duration")));
				res=res+rs.getDouble("quiz_result");
				qno+=1;
			}
			con.close();
			rs.close();
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
