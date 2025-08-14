package quizGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class QuizCatarory extends JFrame implements ActionListener
{
	MainWindow mw;String user; JPanel pi,pc;JComboBox jc1,jc2,jc3,jc4;
	String jcs1[]=new String[8],jcs2[]=new String[3],jcs3[]=new String[20],jcs4[]=new String[12];
	JLabel l1,l2,l3,l4; Font fn; JButton b1,c1;ImageIcon im;
  public QuizCatarory(MainWindow mw,String str,String user,ImageIcon im)
  {
	  super(str);
	  this.mw=mw;
	  this.user=user;
	 this.im=im;
	  addMouseListener(new MouseAdapter() 
	  {
		 @Override
		 public void mouseClicked(MouseEvent me)
		 {
			System.out.println(me.getX()+","+me.getY());
		 }
	  });
	  setSize(Toolkit.getDefaultToolkit().getScreenSize());
	  setDefaultCloseOperation(EXIT_ON_CLOSE);
	  pi=new JPanel()
	  {
		@Override
		public void paintComponent(Graphics g)
		{
			g.drawImage(ImageBox.QUIZ_CATAGORY, 0, 0, getWidth(), getHeight(), this);
		}
	  };
	  pc=new JPanel();
	  pc.setLayout(new GridLayout(8,1,3,3));
	  pc.setOpaque(false);
	  pc.setBackground(new Color(0,0,0,0));
	  l1= new JLabel("Category");
	  l2= new JLabel("Difficulty");
	  l3= new JLabel("Number Of Qustion");
	  l4= new JLabel("Tag");
	  fn=new Font("Arial",Font.BOLD,14);
	   l1.setFont(fn);
	   l2.setFont(fn);
	   l3.setFont(fn);
	   l4.setFont(fn);
	  
	   jcs1[0]="Linux";
	   jcs1[1]="Bash";
	   jcs1[2]="Uncategorized";
	   jcs1[3]="Dock";
	   jcs1[4]="SQL";
	   jcs1[5]="CMS";
	   jcs1[6]="Code";
	   jcs1[7]="DevOps";
	   
	   jcs2[0]="Easy";
	   jcs2[1]="Medium";
	   jcs2[2]="Hard";
	  
	   jcs3[0]="1";
	   jcs3[1]="2";
	   jcs3[2]="3";
	   jcs3[3]="4";
	   jcs3[4]="5";
	   jcs3[5]="6";
	   jcs3[6]="7";
	   jcs3[7]="8";
	   jcs3[8]="9";
	   jcs3[9]="10";
	   jcs3[10]="11";
	   jcs3[11]="12";
	   jcs3[12]="13";
	   jcs3[13]="14";
	   jcs3[14]="15";
	   jcs3[15]="16";
	   jcs3[16]="17";
	   jcs3[17]="18";
	   jcs3[18]="19";
	   jcs3[19]="20";
	   
	   jcs4[0]="JavaScript";
	   jcs4[1]="Python";
	   jcs4[2]="PHP";
	   jcs4[3]="MySql";
	   jcs4[4]="Bash";
	   jcs4[5]="DevOps";
	   jcs4[6]="HTML";
	   jcs4[7]="Kubernetes";
	   jcs4[8]="Laravel";
	   jcs4[9]="Linux";
	   jcs4[10]="Docker";
	   jcs4[11]="WordPress";
	   setContentPane(pi);
	   jc1=new JComboBox(jcs1);
	   jc2=new JComboBox(jcs2);
	   jc3=new JComboBox(jcs3);
	   jc4=new JComboBox(jcs4);
	   jc1.setFont(fn);
	   jc2.setFont(fn);
	   jc3.setFont(fn);
	   jc4.setFont(fn);
	   pc.add(l1);pc.add(jc1);pc.add(l2);pc.add(jc2);pc.add(l3);pc.add(jc3);pc.add(l4);pc.add(jc4);
	   pc.setBounds(414, 396, 550, 400);
	   b1=new JButton("BACK");
	   b1.setFont(fn);
	   b1.setBounds(87, 664, 159, 33);
	   b1.setBackground(Color.LIGHT_GRAY);
	   c1=new JButton("CONTINUE");
	   c1.setFont(fn);
	   c1.setBackground(Color.LIGHT_GRAY);
	   c1.setBounds(1095, 664, 159, 33);
	   add(pc);add(b1);add(c1);
	 b1.addActionListener(this);
	 c1.addActionListener(this);
	 setLayout(null);
	 revalidate();
	 repaint();
	  setVisible(true);
  }
@Override
public void actionPerformed(ActionEvent e) 
{
	if(e.getSource()==b1)
	{
		mw.setVisible(true);
		dispose();
	}
	else
	{
		String war="";
		if(jc1.getSelectedIndex()==0)
		{
			war=war+"Category : Linux";
		}
		if(jc2.getSelectedIndex()==0)
		{
			war=war+" , Difficulty : Easy";
		}
		if(jc3.getSelectedIndex()==0)
		{
			war=war+" , Number Of Qustion : 1";
		}
		if(jc4.getSelectedIndex()==0)
		{
			war=war+" , Tag : JavaScript";
		}
		if(jc1.getSelectedIndex()!=0 && jc2.getSelectedIndex()!=0 && jc3.getSelectedIndex()!=0 && jc4.getSelectedIndex()!=0)
		{
			war= "Category : "+(String)jc1.getSelectedItem()+" , Difficulty : "+(String)jc2.getSelectedItem()+" , Number Of Qustion : "
					+(String)jc3.getSelectedItem()+" , Tag : "+(String)jc4.getSelectedItem();
		}
		int n=JOptionPane.showConfirmDialog(this, war, "SURE?", JOptionPane.OK_CANCEL_OPTION);
		if(n==JOptionPane.OK_OPTION)
		{
			new QuizStart(mw,"QUIZ STARTED(Once You Saved The Response You Cannot Come Back)",(String)jc1.getSelectedItem(),(String)jc2.getSelectedItem(),(String)jc3.getSelectedItem(),(String)jc4.getSelectedItem(),user,im,this);
		}

	}
	
}
  
}
