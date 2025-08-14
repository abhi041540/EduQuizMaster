package quizGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ProfilePanel extends JPanel implements ActionListener
{
	JButton userbt,updp,delete;JLabel l1;JPanel p1,p2; JFrame f1;
	Font fn; String user;
 public ProfilePanel(ImageIcon im,String user,JFrame f1)
 {
	 this.f1=f1;
	 this.user=user;
	 fn= fn=new Font("Arial",Font.BOLD,30);
	 setLayout(null);
	 setBackground(Color.LIGHT_GRAY);
	 delete=new JButton("DELETE ACCOUNT");
	 delete.setFont(fn);
	userbt= new JButton(im);
	userbt.setBackground(Color.white);
	 l1=new JLabel(" USERID:- "+user);
	 l1.setFont(fn);
	 updp=new JButton("UPDATE PASSWORD");
	 updp.setFont(fn);
	 add(new JLabel(""));
	 p2=new JPanel();
//	 p2.setLayout(new BoxLayout(p2,BoxLayout.PAGE_AXIS));
	 p2.setOpaque(false);
	 p2.setBackground(new Color(0,0,0,0));
	 p2.add(userbt);p2.add(l1);p2.add(updp);p2.add(delete);
	 addMouseListener(new MouseAdapter()
	  {
		 @Override
		 public void mouseClicked(MouseEvent me)
		 {
			System.out.println(me.getX()+","+me.getY());
		 }
	  });
	p2.setBounds(392,264,460,400);
	add(p2);
	 updp.addActionListener(this);
	 updp.setBackground(Color.white);
	 delete.setBackground(Color.white);
	 delete.addActionListener(this);
	 
 }
 @Override
 public void paintComponent(Graphics g)
 {
	 g.drawImage(ImageBox.PROFILE_PANEL, 0, 0, getWidth(), getHeight(), this);
 }
@Override
public void actionPerformed(ActionEvent e) 
{
	if(e.getSource()==updp)
	new UpdatePassDialog(f1,"CREATE NEW PASSWORD");
	else if(e.getSource()==delete)
	{
		 int n=JOptionPane.showConfirmDialog(this, "ARE YOU SURE?", "CONFIRM", JOptionPane.OK_CANCEL_OPTION);
		 if(n==JOptionPane.OK_OPTION)
		 {
		try 
		{
			Connection con= QDB.getConnection();
			Statement st= con.createStatement();
			st.executeUpdate("delete from quiz_history where userid='"+user+"'");
			st.executeUpdate("delete from user where userid='"+user+"'");
			System.exit(0);
		} 
		catch (SQLException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		 }
	}
}
}
