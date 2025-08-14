package quizGame;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.Timer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class QuizStart extends JDialog implements ActionListener,ItemListener
{
	String cat,diff,tag,user;MainWindow mw;ImageIcon im;JSplitPane js;JPanel pl,p,p2,p3,p4,p5,pr;
	JButton userbt,atb[],next,time,subm; JLabel l1;JPanel pmat; int qno,min,sec,quc=0;int i,cont=0;
	Font fn;JScrollPane jsc;String data;JSONParser jpar;JSONArray ja;JDialog dj; int ans[],qc=0,qq;
	Thread t;JSONObject job;Checkbox c1,c2,c3,c4,c5,c6;Timer tm;JFrame f1;
  public QuizStart(MainWindow mw,String str,String cat,String diff,String qno,String tag,String user,ImageIcon im,JFrame f1)
  {
	  super(mw,str);
	  dj=this;
	  this.f1=f1;
	  this.mw=mw;
	  this.cat=cat;
	  this.diff=diff;
	  this.tag=tag;
	  this.qno=Integer.parseInt(qno);
	  qq=this.qno;
	  this.user=user;
	  this.im=im;
	  min=this.qno-1;
	  sec=60;
	  time=new JButton("REMAINING TIME:- "+(min+1)+" : 00");
	  t= new Thread() 
	  {
		@Override
		public void run()
		{
			apiCall();
			 tm= new Timer(1000,new ActionListener()
				{
					
					@Override
					public void actionPerformed(ActionEvent e)
					{
						sec--;
						if(min==0 && sec==0)
						{
							tm.stop();
							new ResultWindow(ans,"RESULT WINDOW",user,cat,diff,qq,time.getText(),mw);
							f1.dispose();
							dj.dispose();
						}
						else if(sec==0)
						{
							min=min-1;
							sec=60;
						}
						
						time.setText("REMAINING TIME:- "+(min)+" : "+sec);
						revalidate();
					}
				});
				tm.start();
			  
		}
	  };
	  t.start();
	  fn=new Font("Arial",Font.BOLD,20);
	 p= new JPanel() 
	 {
		@Override
		public void paintComponent(Graphics g)
		{
			g.drawImage(ImageBox.PROFILE_PANEL, 0, 0, getWidth(), getHeight(), this);
		}
		
	 };
	  
	  atb=new JButton[this.qno];
	  ans=new int[this.qno];
	  subm=new JButton("SUBMIT RESPONSE");
	  next=new JButton("SAVE & NEXT");
	  setModal(true);
	  js=new JSplitPane();
	  p2= new JPanel();
	  p2.setOpaque(false);
	  p2.setBackground(new Color(0,0,0,0));
	  p3= new JPanel();
	  p3.setOpaque(false);
	  p3.setBackground(new Color(0,0,0,0));
	  p4= new JPanel();
	  p4.setOpaque(false);
	  p4.setBackground(new Color(0,0,0,0));
	  p4.setLayout(new BorderLayout());
	  p5= new JPanel();
	  p5.setOpaque(false);
	  p5.setBackground(new Color(0,0,0,0));
	  p5.setLayout(new BorderLayout());
	  p3.setLayout(new GridLayout(2,1,2,2));
	  p2.setLayout(new GridLayout(3,1,2,2));
	  pl= new JPanel();
	  pl.setOpaque(false);
	  pl.setBackground(new Color(0,0,0,0));
	  pl.setLayout(new BoxLayout(pl,BoxLayout.Y_AXIS));
	  userbt=new JButton(im);
	  userbt.setBackground(Color.white);
	 
	  pmat=new JPanel();
	  pmat.setLayout(new GridLayout(4,4,2,2));
	  pmat.setOpaque(false);
	  pmat.setBackground(new Color(0,0,0,0));
	  for(i=0;i<=this.qno-1;i++)
	  {
			  atb[i]=new JButton(""+(i+1));
	          atb[i].setFont(new Font("Arial",Font.BOLD,30));
	          atb[i].setBackground(Color.yellow);
	  }
	  l1=new JLabel("USERID:- "+user);
	  js.setEnabled(false);
	  time.setFont(fn);
	  time.setBackground(Color.white);
	  l1.setFont(fn);
	  pmat.setFont(fn);
	  subm.setFont(fn);
	  subm.setBackground(Color.white);
	  next.setFont(fn);
	  next.setBackground(Color.white);
	  for(i=0;i<=this.qno-1;i++)
	  {
		  pmat.add(atb[i]);
	  }
	  p.setPreferredSize(new Dimension(350,getHeight()));
	 p3.add(userbt);p3.add(l1);p2.add(next);p2.add(subm);p4.add(time);p5.add(pmat);
	 pl.add(p4);pl.add(p3);pl.add(p5);pl.add(p2);
	  p.add(pl);
	  js.setLeftComponent(p);
	  add(js);
	  setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	  setSize(Toolkit.getDefaultToolkit().getScreenSize());
	  subm.addActionListener(this);
	  next.addActionListener(this);
	  try 
	  {
		t.join();
	  }
	  catch (InterruptedException e) 
	  {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
	  if(cont==1)
	  {
		 int n=JOptionPane.showConfirmDialog(this, "YOU SELECTRD UNRELATABLE TAG SO PLEASE TRY AGAIN","WRONG TAG", JOptionPane.OK_CANCEL_OPTION);
			dj.dispose();
			tm.stop();	
	  }
	  else
	  {
	  getQustion(quc);
	  setResizable(false);
	  setVisible(true);
	  }
	  
  }
@Override
public void actionPerformed(ActionEvent e)
{
	if(e.getSource()==next)
	{
		
		if(qc==0)
		{
			atb[quc].setBackground(Color.red);
		}
		int qucc=quc+1;
		if(qucc<=qno-1)
		{
		quc+=1;
		getQustion(quc);
		}
	}
	else if(e.getSource()==subm)
	{
		tm.stop();
		new ResultWindow(ans,"RESULT WINDOW",user,cat,diff,qno,time.getText(),mw);
		f1.dispose();
		dj.dispose();
	}
   	
}
public void apiCall()
{
	String apikey=QDB.APIKEY;
    String apiurl="https://quizapi.io/api/v1/questions?apiKey="+apikey+"&difficulty="+diff+"&limit="+qno+"&tags="+tag+"&category="+cat.toLowerCase();
	try 
	{
		URL url=new URL(apiurl);
		HttpURLConnection con=(HttpURLConnection)url.openConnection();
//		int m= con.getResponseCode();
//		System.out.println(m);
		BufferedReader reed=new BufferedReader(new InputStreamReader(con.getInputStream()));
		String line;StringBuilder data1=new StringBuilder();
		for(;(line=reed.readLine())!=null;)
		{
			data1.append(line);
		}
		data=data1.toString();
//		System.out.println(data);
		jpar= new JSONParser();
		ja=(JSONArray)jpar.parse(data); 
//		System.out.println(ja);
		reed.close();con.disconnect();
	} 
	catch (Exception e) 
	{
		cont=1;
	}
}
public void getQustion(int qnc)
{
//	String qusno=qusno.valueOf(qnc);
	qc=0;
	pr=new JPanel()
	{
		@Override
		public void paintComponent(Graphics g)
		{
			g.drawImage(ImageBox.QUIZ_BACKGROUND, 0, 0, getWidth(), getHeight(), this);
		}
		
	};
	pr.setLayout(new GridLayout(11,1,10,10));
	JButton b1= new JButton("QUSTION NO. "+(qnc+1));
	b1.setFont(new Font("Arial",Font.BOLD,20));
	b1.setOpaque(false);
	b1.setBackground(new Color(0,0,0,0));
	CheckboxGroup cgb= new CheckboxGroup();
	job=(JSONObject)ja.get(qnc);
	TextArea tex= new TextArea();
	tex.setBackground(Color.white);
	tex.setText("    "+(String)job.get("question"));
	tex.setFont(new Font("Arial",Font.BOLD,30));
	tex.setEditable(false);
	JSONObject ansc=(JSONObject)job.get("answers");
	c1= new Checkbox("   "+(String)ansc.get("answer_a"),cgb,false);
	c2= new Checkbox("   "+(String)ansc.get("answer_b"),cgb,false);
	c3= new Checkbox("   "+(String)ansc.get("answer_c"),cgb,false);
	c4= new Checkbox("   "+(String)ansc.get("answer_d"),false,cgb);
	c5= new Checkbox("   "+(String)ansc.get("answer_e"),cgb,false);
	c6= new Checkbox("   "+(String)ansc.get("answer_f"),false,cgb);
	c1.setFont(new Font("Arial",Font.BOLD,20));
	c2.setFont(new Font("Arial",Font.BOLD,20));
	c3.setFont(new Font("Arial",Font.BOLD,20));
	c4.setFont(new Font("Arial",Font.BOLD,20));
	c5.setFont(new Font("Arial",Font.BOLD,20));
	c6.setFont(new Font("Arial",Font.BOLD,20));
	JPanel cb1,cb2,cb3,cb4,cb5,cb6;
	cb1= new JPanel();
	cb1.setLayout(new GridLayout(1,3,3,3));
	cb1.add(c1);
	cb1.setBackground(Color.white);
	cb4= new JPanel();
	cb4.setLayout(new GridLayout(1,3,3,3));
	cb4.add(c2);
	cb4.setBackground(Color.white);
	cb2= new JPanel();
	cb2.setLayout(new GridLayout(1,3,3,3));
	cb2.add(c3);
	cb2.setBackground(Color.white);
	cb5= new JPanel();
	cb5.setLayout(new GridLayout(1,3,3,3));
	cb5.add(c4);
	cb5.setBackground(Color.white);
	cb3= new JPanel();
	cb3.setLayout(new GridLayout(1,3,3,3));
	cb3.add(c5);
	cb3.setBackground(Color.white);
	cb6= new JPanel();
	cb6.setLayout(new GridLayout(1,3,3,3));
	cb6.add(c6);
	cb6.setBackground(Color.white);
	pr.add(b1);pr.add(new JLabel(""));
	pr.add(tex);pr.add(cb1);pr.add(cb4);pr.add(cb2);pr.add(cb5);pr.add(cb3);pr.add(cb6);
	js.setRightComponent(pr);
    c1.addItemListener(this);
    c2.addItemListener(this);
    c3.addItemListener(this);
    c4.addItemListener(this);
    c5.addItemListener(this);
    c6.addItemListener(this);
	
}
@Override
public void itemStateChanged(ItemEvent e)
{
	qc=1;
	JSONObject ca=(JSONObject)job.get("correct_answers");
	if(e.getSource()==c1)
	{
	 if(((String)ca.get("answer_a_correct")).equalsIgnoreCase("true"))
	 {
		 ans[quc]=1;
	 }
	}
	else if(e.getSource()==c2)
	{
		if(((String)ca.get("answer_b_correct")).equalsIgnoreCase("true"))
		 {
			 ans[quc]=1;
		 }
	}
	else if(e.getSource()==c3)
	{
		if(((String)ca.get("answer_c_correct")).equalsIgnoreCase("true"))
		 {
			 ans[quc]=1;
		 }
	}
	else if(e.getSource()==c4)
	{
		if(((String)ca.get("answer_d_correct")).equalsIgnoreCase("true"))
		 {
			 ans[quc]=1;
		 }
	}
	else if(e.getSource()==c5)
	{
		if(((String)ca.get("answer_e_correct")).equalsIgnoreCase("true"))
		 {
			 ans[quc]=1;
		 }
	}
	else if(e.getSource()==c6)
	{
		if(((String)ca.get("answer_f_correct")).equalsIgnoreCase("true"))
		 {
			 ans[quc]=1;
		 }
	}
	atb[quc].setBackground(Color.green);
}
}
