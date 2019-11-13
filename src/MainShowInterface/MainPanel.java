package MainShowInterface;

import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import DfaSimulator.simulator;
import FileOperate.FileOutPut;
import MiniDfa.MiniDfa;
import NFAtoDFA.NfaToDfa;
import REtoNFA.reToPostfixExpression;
import searchNFA.searchNFA;

public class MainPanel extends JPanel{
	 public JButton sumbit;
	 JTextArea textArea;
	 public String res;
	 public static List<String> reTitle=new ArrayList<>();//正规式代表的符号意义，如digital
public MainPanel() {
	  textArea = new JTextArea();  
     
     this.setLayout(new BorderLayout());  
     textArea.setText("输入正规式，按行分隔开..."); 
     textArea.setSize(200, 500);
     //当TextArea里的内容过长时生成滚动条  
     this.add(new JScrollPane(textArea),BorderLayout.CENTER); 
     sumbit=new JButton("提交上述正规式!");
     //sumbit.setBounds(0, 300, 200, 50);
     this.add(sumbit,BorderLayout.SOUTH);
     
     action();
     
}
public void action() {
	sumbit.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			MainFrame.mainFrame.setVisible(false);
			res=textArea.getText();
			try {
				createSimulator(res);
			} catch (NoSuchFieldException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			new ProgramResourceFrame();
		}
	});
}
//正规式 NFA DFA minDFA 的构造过程，并将结果写入resource.txt
public void createSimulator(String res) throws InterruptedException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, IOException {
	
	StringBuffer stringBuffer=new StringBuffer();
	for (String reOfTitle : res.split("\n")) {
		//System.out.println(reOfTitle);
		stringBuffer.append("--->"+reOfTitle+"\n");
		String re="";String title="";
		for (int i = 0; i < reOfTitle.length(); i++) {
			
			if(reOfTitle.charAt(i)==':') {
				re=reOfTitle.substring(i+1, reOfTitle.length());break;
			}
			title+=reOfTitle.charAt(i);
		}
		reTitle.add(title);
		//System.out.println(re);
		searchNFA searchNFA=new searchNFA();
		reToPostfixExpression reToPostfixExpression=new reToPostfixExpression();
		searchNFA.searchNFA(reToPostfixExpression.reToNFA(re).getHead());
		List<Integer> start=new ArrayList<>();
		start.add(reToPostfixExpression.result.get(reToPostfixExpression.result.size()-1).getHead().getStatus());
		NfaToDfa nfaToDfa=new NfaToDfa();
		nfaToDfa.setNodes(searchNFA.nodes);
		nfaToDfa.setEnterword(searchNFA.enterword);
		nfaToDfa.setSearchNFA_endStatus(searchNFA.endStatus);
		nfaToDfa.NfaToDfa(start);
		MiniDfa dfa=new MiniDfa(nfaToDfa.newStatus,searchNFA.enterword);
		dfa.setNfaToDfa_newStatus(nfaToDfa.newStatus);
		dfa.setSearchNFA_enterword(searchNFA.enterword);
		dfa.setMatrix(nfaToDfa.Matrix);
		dfa.MiniDfa();
		DFAList.getDfas().add(dfa);
		//System.out.println(simulator.simulator("aaaaabb1", dfa.DFA, dfa.Div.endStatus, dfa.Div.startStatus, dfa.enterword));
	  //dfa矩阵   
		  FileOutPut.clearInfoForFile("src/FileOperate/resource.txt");
                 stringBuffer.append("dfa Matrix:\n");
		for (int i = 0; i < nfaToDfa.Matrix.matrix.length; i++) {
			stringBuffer.append(i+1+" ");
			for (int j = 0; j < nfaToDfa.Matrix.matrix[i].length; j++) {
			stringBuffer.append(nfaToDfa.enterword.get(j)+":"+nfaToDfa.Matrix.matrix[i][j]+" ");
			}stringBuffer.append("\n");
     	}
		stringBuffer.append("Start status:"+nfaToDfa.Matrix.startStatus+"\n");
		stringBuffer.append("Final status:\n");

		for (String integer : nfaToDfa.Matrix.endStatus) {
			
			stringBuffer.append(integer+" ");
		}
		stringBuffer.append("\n\n");
		//minidfa
		stringBuffer.append("MiNiDfa Matrix:\n");

		for (int i = 0; i < dfa.DFA.length; i++) {
			//System.out.print(i+" ");
			stringBuffer.append(i+" ");
			for (int j = 0; j < dfa.DFA[i].length; j++) {
				stringBuffer.append(dfa.enterword.get(j)+":"+dfa.DFA[i][j]+" ");
				//System.out.print(dfa.enterword.get(j)+":"+dfa.DFA[i][j]+" ");
			}stringBuffer.append("\n");
			//System.out.println();
		}
		stringBuffer.append("Start Status:"+dfa.Div.getStartStatus()+"\n");
		//System.out.println("初态下标为："+dfa.Div.getStartStatus());
		stringBuffer.append("Final Status:\n");
		//System.out.println("终态下标的集合为:");
		for (Integer integer : dfa.Div.getEndStatus()) {
			//System.out.print(integer+" ");
			stringBuffer.append(integer+" ");
		}
		stringBuffer.append("\n\n");
	}
	String content=new String(stringBuffer);
  FileOutPut.writeByFileOutputStream("src/FileOperate/resource.txt", content);

}

}
