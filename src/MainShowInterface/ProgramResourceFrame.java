package MainShowInterface;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ProgramResourceFrame extends JFrame{
	
PRFpanel mainPanel=new PRFpanel();
	
	 public ProgramResourceFrame() {
		// TODO Auto-generated constructor stub
	
		this.add(mainPanel);
		this.setTitle("Ô´³ÌÐò");
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setBounds( 800,350, 500,500);
		this.setResizable(false);		
	}
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//     new ProgramResourceFrame();
//      
//	}
}
