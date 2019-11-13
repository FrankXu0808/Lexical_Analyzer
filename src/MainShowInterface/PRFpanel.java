package MainShowInterface;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import DfaSimulator.simulator;
import FileOperate.FileOutPut;
import MiniDfa.MiniDfa;

public class PRFpanel extends JPanel{
	public JTextArea textArea;
	public JButton sumbit;
	public List<ErrorString> errorString=new ArrayList<>();//ʶ��ʧ�ܵ��ַ�
	public List<MarkFlow> markFlows=new ArrayList<>();//�Ǻ�������
public PRFpanel() {
	 textArea = new JTextArea();     
    this.setLayout(new BorderLayout());  
    textArea.setText("abb 1 abbbbbb 2 "); 
    textArea.setSize(200, 500);
    //��TextArea������ݹ���ʱ���ɹ�����  
    this.add(new JScrollPane(textArea),BorderLayout.CENTER); 
     sumbit = new JButton("�ύԴ����!");
    //sumbit.setBounds(0, 300, 200, 50);
    this.add(sumbit,BorderLayout.SOUTH);
    sumbit();
    
}
//�����ύ�¼������дʷ�ʶ��Ĺ���
public void sumbit() {
	sumbit.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			String resouce=textArea.getText();
			//ȥ�ո�ͻ��з�
			String handleStrings=resouce.replaceAll("\\s+","");
			//���ı�����ı�ͨ�����з��ֳɲ�ͬ����
			String[] textResouce=resouce.split("(\n)+");
			handleStrings=handleStrings.replaceAll("\n","");
			
			String replace="()*";
			String kit="������";
		   for (int s=0;s<textResouce.length;s++) {
			
		
			for (int i = 0; i < textResouce[s].length(); i++) {
				if(replace.contains(textResouce[s].charAt(i)+"")) {
					if(textResouce[s].charAt(i)=='(') {
						textResouce[s]=textResouce[s].replace('(', '��');
					}
					else if(textResouce[s].charAt(i)==')') {
						textResouce[s]=textResouce[s].replace(')', '��');
					}
//					else if(handleString.charAt(i)=='|') {
//						handleString=handleString.replace('|', '��');
//					}
					else if(textResouce[s].charAt(i)=='*') {
						textResouce[s]=textResouce[s].replace('*', '��');
					}
				}
			}
			}
		   String content="";
			System.out.println("Դ����");
			content+="resource programm:\n";
			System.out.println(handleStrings);
		    content+=handleStrings+"\n";
			//�жϳ��򣬵ó��Ǻ���
			for (int j = 0; j < textResouce.length; j++) {
				textResouce[j]=textResouce[j].replaceAll("\\s+","");
				scanResource(textResouce[j], DFAList.getDfas(),j);
				
			}
			
			content+="MarkFlow:\n";
			System.out.println("�Ǻ���:");
			int currentRow=0;
			for (int i=0;i<markFlows.size();i++) {
				if(kit.contains(markFlows.get(i).getWord())) {
					if(markFlows.get(i).getWord().equals("��")) {
						markFlows.get(i).setWord("(");
					}
					if(markFlows.get(i).getWord().equals("��")) {
						markFlows.get(i).setWord(")");
					}
					if(markFlows.get(i).getWord().equals("��")) {
						markFlows.get(i).setWord("*");
					}
				}
				boolean iserror=false;
				if(markFlows.get(i).getTitle().equals("digital")) {
					if(i<=markFlows.size()-2) {
						if(markFlows.get(i+1).getTitle().equals("id")||
								markFlows.get(i+1).getTitle().equals("KW")) {
							ErrorString error=new ErrorString();
							error.setLine(markFlows.get(i).getOnline());
							error.setString(markFlows.get(i).getWord()+markFlows.get(i+1).getWord());
							errorString.add(error);
							i=i+1;
							iserror=true;
						}
					}
				}
				if(iserror==false) {
				System.out.println("<line:"+markFlows.get(i).getOnline()+
						"> <"+markFlows.get(i).getTitle()+":"+
						markFlows.get(i).getWord()+">");
				content+="<line:"+markFlows.get(i).getOnline()+
						"> <"+markFlows.get(i).getTitle()+":"+
						markFlows.get(i).getWord()+">"+"\n";}
				
			}
			
			
//			//�ó�����Ǻ�������һ��
//			int markIndex=0;//markflow index
//			for (int i = 0; i < textResouce.length; i++) {}
			//�ϲ����ڵĴ����ַ�
//			if(!errorString.isEmpty()) {
//			for (int j = 0; j < errorString.size()-1; j++) {
//				for (int j2 = j+1; j2 < errorString.size(); j2++) {
//					if
//				}
//			}
			if(!errorString.isEmpty()) {
			System.out.println("ERROR:");
			content+="ERROR:\n";
			for (ErrorString error : errorString) {
				content+="In Line"+error.getLine()+
						":--Detail:Characters ->"+error.getString()+
						"<- can not be processed;\n";
				System.out.println("In Line"+error.getLine()+
						":--Detail:Characters ->"+error.getString()+
						"<- can not be processed;");
			}
			}
			try {
				FileOutPut.writeByFileOutputStream("src/FileOperate/resource.txt", content);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		});
}
//ɨ��Դ����,�����дʷ�ʶ��
public void scanResource(String resource,List<MiniDfa> dfaList,int line) {
	int bindex=0,eindex=0;//���ƥ����ַ��Ŀ�ʼλ�úͽ���λ��
	int e_pre_index=0,e_next_eindex=0;//�ϸ������ַ���λ�ú͵�ǰ�����ַ���λ��
	
	
	a:for (int i = 0; i < resource.length(); i++) {
		//�����жϸ��ַ��Ƿ�Ϸ�
		boolean islegal=false;
		int errorstringOfDfaIndex=0;
		b:for (int j = 0; j < dfaList.size(); j++,errorstringOfDfaIndex=j) {
			
			MiniDfa dfa=dfaList.get(j);
			//������ַ��Ϸ�
			//System.out.println(dfa.getEnterword());
			if(dfa.getEnterword().contains(resource.charAt(i))) {
				//System.out.println(dfa.getEnterword()+"����"+resource.charAt(i));
				islegal=true;
				//��Դ�dfa�������ƥ��,�����ƥ��Ŀ�ʼλ�ÿ�ʼ
				int result=-1;//����ģ��dfa�Ľ��
				for (int k = bindex; k < resource.length(); k++) {
					//System.out.print(resource.substring(bindex, k+1));
					result=simulator.simulator(resource.substring(bindex, k+1), dfa.DFA, 
							dfa.getDiv().getEndStatus(), dfa.getDiv().getStartStatus(), dfa.getEnterword());
					//System.out.println("����ģ������"+result);
					//Ѱ�����ƥ��ʱ�����˲��������dfa���ַ�
					if(result==2) {
						//System.out.print(resource.substring(bindex, k+1));
						//System.out.println(resource.substring(bindex, eindex));
						if(bindex!=eindex) {
						result=simulator.simulator(resource.substring(bindex, eindex), dfa.DFA, 
								dfa.getDiv().getEndStatus(), dfa.getDiv().getStartStatus(), dfa.getEnterword());
						//System.out.println("����ģ������"+result);
						}
						else {
							islegal=false;break;
						}
						//���ƥ����������̬,�������Ѱ�����ƥ���ѭ��������һ��dfa�Ƿ��ܴ�ͷ��������ַ�
						if(result==1) {
						   //eindex=bindex+1;
						   islegal=false;break;
						}
						//��̬���Ǻ���
						else if(result==0){
							//���ɼǺ���
							//System.out.println("һ���Ǻ���"+bindex+"-> "+eindex+":"+resource.substring(bindex,eindex));
							MarkFlow  markFlow=new MarkFlow();
							markFlow.setWord(resource.substring(bindex,eindex));
						    markFlow.setTitle(MainPanel.reTitle.get(j));
						    markFlow.setOnline(line);
						    //�����¸��Ǻ���Ѱ�ҵ�bindex��endindex��λ��
						  
						    markFlows.add(markFlow);
						    
							i=eindex-1;
							//System.out.println("i="+i);
							
							bindex=eindex;
							
							islegal=false;
							break b;
						}
							
						
					}
					else {//Ѱ�����ƥ���Ƿ����⴮�ַ�������̬���м�̬
						//����Դ�����β��
						if(k==resource.length()-1) {
							//��̬���Ǻ���
							if(result==0) {
								//if(k==0) 
									eindex=k+1;
							//���ɼǺ���
								//System.out.println("һ���Ǻ���"+bindex+"-> "+eindex+":"+resource.substring(bindex,eindex));
								MarkFlow  markFlow=new MarkFlow();
								markFlow.setWord(resource.substring(bindex,eindex));
							    markFlow.setTitle(MainPanel.reTitle.get(j));
							    markFlow.setOnline(line);
							    //�����¸��Ǻ���Ѱ�ҵ�bindex��endindex��λ��
							    markFlows.add(markFlow);
								i=eindex-1;
								//System.out.println(i);
								
								bindex=eindex;
								
								islegal=false;
								break b;
							}
							//�м�̬
							else {
								 //eindex=bindex+1;
								 islegal=false;break;
							}
						}
						else {
							if(result==0) eindex=k+1;
						}
						
					}
				}
			}
		}
		
		//�Ƿ�
		if(!islegal) {
			if(errorstringOfDfaIndex==dfaList.size()) {
			bindex=i+1;
			eindex=bindex;
			if(errorString.isEmpty()) {
				ErrorString String=new ErrorString();
				String.setIndex(i);
				String.setString(resource.charAt(i)+"");
				String.setLine(line);
				errorString.add(String);
			}
			else {
			if(line==errorString.get(errorString.size()-1).getLine()) {
				ErrorString es=errorString.get(errorString.size()-1);
				//System.out.println("$$$$$$"+(i-es.getIndex())+"-"+(es.getString().length()-1));
				
				if(i-es.getIndex()==es.getString().length()) {
					es.setString(es.getString()+resource.charAt(i));
//					System.out.println("@@@");
				}
			}else {
				ErrorString String=new ErrorString();
				String.setIndex(i);
				String.setString(resource.charAt(i)+"");
				String.setLine(line);
				errorString.add(String);
			}
			
		  }
		
		}
       }
	 }
	}

}
