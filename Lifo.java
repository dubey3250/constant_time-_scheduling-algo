import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.lang.*;
import java.awt.event.*;
class Lifo extends JFrame implements Runnable , ActionListener
{
	JButton b1,b2,b3,b4,b5,b6;
	JLabel lb,lb2,lb3,lb4;
	JTextField t1,t2,t3,t4;
	String str1,str2,str3;
	int Cal_min=0,f=0;
	String Stk[]=new String[100];
	int burst[]=new int[100];
	String Temp_stk[]=new String[100];
	int Temp_burst[]=new int[100];
	int top=-1,temp=0,i=0;
	String msg="";
	JButton Sb[]=new JButton[50];
	Thread t;
	JMenuBar mb;
	JMenu file,edit,help;
	JMenuItem cut,copy,paste,selectAll,exit;
	public Lifo()
	{
		b1=new JButton("Stack");
		b2=new JButton("Set Size of Stack");
		b3=new JButton("Process name");
		b4=new JButton("Burst time");
		b5=new JButton("Add Process");
		b6=new JButton("Process");
		lb=new JLabel();
		lb2=new JLabel();
		lb3=new JLabel();
		lb4=new JLabel();
		add(lb);
		add(lb2);
		add(lb3);
		add(lb4);
		
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		add(b5);
		add(b6);
		
		cut=new JMenuItem("cut");
		copy=new JMenuItem("copy");
		paste=new JMenuItem("paste");
		selectAll=new JMenuItem("selectAll");
		exit=new JMenuItem("Exit CTRL+F4 ");

		cut.addActionListener(this);
		copy.addActionListener(this);
		paste.addActionListener(this);
		selectAll.addActionListener(this);
		exit.addActionListener(this);

		mb=new JMenuBar();
		mb.setBounds(0,0,1000,40);

		file=new JMenu("File");
		edit=new JMenu("Edit");
		help=new JMenu("Help");

		edit.add(cut);
		edit.add(copy);
		edit.add(paste);
		edit.add(selectAll);
		file.add(exit);

		mb.add(file);
		mb.add(edit);
		mb.add(help);
		add(mb);
		mb.setBackground(Color.white);	
		t1=new JTextField(20);
		t2=new JTextField(25);
		t3=new JTextField(25);
		t4=new JTextField(25); //Unused field
		add(t1);
		add(t2);
		add(t3);
		add(t4); // unused
		
		lb4.setBounds(500,520,300,40);
		lb4.setText("Developed by Prakash dubey");
		lb4.setFont (new Font ("Times new Roman", Font.BOLD,20));
		lb4.setForeground(Color.white);
		
		lb2.setFont (new Font ("Verdana", Font.BOLD,27));
		lb.setBounds(260,20,300,40);
		lb2.setBounds(270,350,700,30);		// for process..
		lb3.setFont (new Font ("Verdana", Font.BOLD,27));
		lb3.setForeground(Color.white);
		lb2.setForeground(Color.white);
		lb3.setBounds(270,390,500,30);
		
		b1.setBounds(20,45,200,50);
		b2.setBounds(500,100,200,50);
		b3.setBounds(500,160,200,50);
		b4.setBounds(500,220,200,50);
		b5.setBounds(500,280,200,50);
		b6.setBounds(300,280,200,50);
		
		t1.setBounds(300,100,180,50);
		t2.setBounds(300,160,180,50);
		t3.setBounds(300,220,180,50);

		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		t=new Thread(this);
		//t.start();
		setVisible(true);
		setLayout(null);
		setSize(1000,600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700, 600));
		getContentPane().setBackground(new Color(10,30,30));
		ImageIcon img = new ImageIcon("light.jpg");
		setIconImage(img.getImage());
		
	}
	public void actionPerformed(ActionEvent ev)
	{
		if(ev.getSource()==b2)
		{
			
			str1=t1.getText();
			int len=Integer.parseInt(str1);
			b1.setLabel("Stack size:  "+str1);
			for(int i=0,j=0;i<len;i++)
			{
				j=j+45;
				Sb[i]=new JButton();
				add(Sb[i]);
				Sb[i].setBackground(Color.blue);
				Sb[i].setForeground(Color.yellow);
				Sb[i].setFont(new Font ("Times New Roman", Font.BOLD,20));
				Sb[i].setBounds(20,60+j,200,40);
			}
		}
		else if(ev.getSource()==b5)
		{
			if(top==(Integer.parseInt(str1))-1)
			{
				lb.setText("Stack is full....");
			}
			else 
			{		
				top++;
				str2=t2.getText();
				str3=t3.getText();
				Stk[top]=str2;
				burst[top]=Integer.parseInt(str3);
				Sb[top].setLabel(str2+"------------------"+str3);
			}	
			
		}
		else if(ev.getSource()==b6)
		{
			/* This will be thread part for the execution of the process 
					long startTime = System.currentTimeMillis();
					myfunction();
					long endTime = System.currentTimeMillis();
					long searchTime = endTime - startTime;
			*/
			if(top==-1)
			{
				lb.setText("Process are complited..");
			}
			else
			{
				int i;
				for(i=top;i>=0;i--)
				{
					Cal_min=(Cal_min+burst[i]);
					//System.out.println(Cal_min);
				}
				Cal_min=(Cal_min/(top+1));
				lb3.setText("calculated mean="+Cal_min);
				t.start(); //start of the thread..
			}
		}
		if(ev.getSource()==exit)
		{
			System.exit(0);
		}
	}		
	public void run()
	{   
		int first=top;
		//System.out.println(top);
		while(true)
		{
			if(top!=-1)
			{
				//block of process execution.....
				if((top==first)&&(burst[top]>Cal_min))
				{
					msg=msg+Stk[top]+"->"+Cal_min;
					lb2.setText(msg);
					Temp_stk[i]=Stk[top];
					Temp_burst[i]=(burst[top]-Cal_min);		
					i++;
				}
				else if(burst[top]==Cal_min)
				{
					msg=msg+Stk[top]+"->"+Cal_min;
					lb2.setText(msg);
				}
				else if(burst[top]>Cal_min)
				{
					if((Cal_min+temp)>burst[top])
					{
						msg=msg+Stk[top]+"->"+burst[top];
						lb2.setText(msg);
						//Temp_stk[i]=Stk[top];
						//Temp_burst[i]=(burst[top]-Cal_min-temp); //condition to be checked in last block
						i++;
						temp=temp+(Cal_min+temp-burst[top]);
						//System.out.println(Temp_burst[i]);
					}
					else
					{
						
						msg=msg+Stk[top]+"->"+(Cal_min+temp);
						lb2.setText(msg);
						Temp_stk[i]=Stk[top];
						Temp_burst[i]=(burst[top]-Cal_min-temp); //condition to be checked in last block
						i++;
						temp=0;
					}		
					
				}
				else if(burst[top]<Cal_min)
				{
					msg=msg+Stk[top]+"->"+burst[top];
					lb2.setText(msg);
					//Temp_stk[i]=Stk[top];
					temp=temp+(Cal_min-burst[top]);
				}
				top--;
			}
			else if(top==-1)
			{
					while((i!=-1))
					{
						if(Temp_burst[i]>0)
						{
						msg=msg+Temp_stk[i]+"->"+Temp_burst[i];
						lb2.setText(msg);
						//Temp_stk[i]=Stk[top];
						//temp=temp+(Cal_min-burst[top]);
						}
						i--;
						f=1;
					}
					
			}
			if(f==1)
			{
				//lb.setText("Processess are complited..");
				//System.out.println("hi..............");
				JFrame frame=new JFrame();
				JOptionPane.showMessageDialog(frame,"Processess are complited..");
				f=0;
			}
			try
		    {
				Thread.sleep(2000);
			}
			catch(Exception e)
			{
					
			}
			
		}
	}
	public static void main(String sd[])
	{
		new Lifo();
	}
} 