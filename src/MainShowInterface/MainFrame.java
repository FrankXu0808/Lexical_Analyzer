package MainShowInterface;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
//程序入口
public class MainFrame extends JFrame{
public static MainFrame mainFrame;
	
	
	
	
MainPanel mainPanel=new MainPanel();
	
	private MainFrame(){
		this.add(mainPanel);
		this.setTitle("正规式");
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setBounds( 700,150, 700,700);
		this.setResizable(false);		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
     mainFrame=new MainFrame();
      
	}
	
}
