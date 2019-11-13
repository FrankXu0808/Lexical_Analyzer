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
	public List<ErrorString> errorString=new ArrayList<>();//识别失败的字符
	public List<MarkFlow> markFlows=new ArrayList<>();//记号流序列
public PRFpanel() {
	 textArea = new JTextArea();     
    this.setLayout(new BorderLayout());  
    textArea.setText("abb 1 abbbbbb 2 "); 
    textArea.setSize(200, 500);
    //当TextArea里的内容过长时生成滚动条  
    this.add(new JScrollPane(textArea),BorderLayout.CENTER); 
     sumbit = new JButton("提交源程序!");
    //sumbit.setBounds(0, 300, 200, 50);
    this.add(sumbit,BorderLayout.SOUTH);
    sumbit();
    
}
//定义提交事件，进行词法识别的过程
public void sumbit() {
	sumbit.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			String resouce=textArea.getText();
			//去空格和换行符
			String handleStrings=resouce.replaceAll("\\s+","");
			//对文本域的文本通过换行符分成不同的行
			String[] textResouce=resouce.split("(\n)+");
			handleStrings=handleStrings.replaceAll("\n","");
			
			String replace="()*";
			String kit="『』ㄨ";
		   for (int s=0;s<textResouce.length;s++) {
			
		
			for (int i = 0; i < textResouce[s].length(); i++) {
				if(replace.contains(textResouce[s].charAt(i)+"")) {
					if(textResouce[s].charAt(i)=='(') {
						textResouce[s]=textResouce[s].replace('(', '『');
					}
					else if(textResouce[s].charAt(i)==')') {
						textResouce[s]=textResouce[s].replace(')', '』');
					}
//					else if(handleString.charAt(i)=='|') {
//						handleString=handleString.replace('|', 'Ⅰ');
//					}
					else if(textResouce[s].charAt(i)=='*') {
						textResouce[s]=textResouce[s].replace('*', 'ㄨ');
					}
				}
			}
			}
		   String content="";
			System.out.println("源程序");
			content+="resource programm:\n";
			System.out.println(handleStrings);
		    content+=handleStrings+"\n";
			//判断程序，得出记号流
			for (int j = 0; j < textResouce.length; j++) {
				textResouce[j]=textResouce[j].replaceAll("\\s+","");
				scanResource(textResouce[j], DFAList.getDfas(),j);
				
			}
			
			content+="MarkFlow:\n";
			System.out.println("记号流:");
			int currentRow=0;
			for (int i=0;i<markFlows.size();i++) {
				if(kit.contains(markFlows.get(i).getWord())) {
					if(markFlows.get(i).getWord().equals("『")) {
						markFlows.get(i).setWord("(");
					}
					if(markFlows.get(i).getWord().equals("』")) {
						markFlows.get(i).setWord(")");
					}
					if(markFlows.get(i).getWord().equals("ㄨ")) {
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
			
			
//			//得出这个记号流在哪一行
//			int markIndex=0;//markflow index
//			for (int i = 0; i < textResouce.length; i++) {}
			//合并相邻的错误字符
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
//扫描源程序,并进行词法识别
public void scanResource(String resource,List<MiniDfa> dfaList,int line) {
	int bindex=0,eindex=0;//最大匹配的字符的开始位置和结束位置
	int e_pre_index=0,e_next_eindex=0;//上个错误字符的位置和当前错误字符的位置
	
	
	a:for (int i = 0; i < resource.length(); i++) {
		//首先判断该字符是否合法
		boolean islegal=false;
		int errorstringOfDfaIndex=0;
		b:for (int j = 0; j < dfaList.size(); j++,errorstringOfDfaIndex=j) {
			
			MiniDfa dfa=dfaList.get(j);
			//如果该字符合法
			//System.out.println(dfa.getEnterword());
			if(dfa.getEnterword().contains(resource.charAt(i))) {
				//System.out.println(dfa.getEnterword()+"包含"+resource.charAt(i));
				islegal=true;
				//针对此dfa进行最大匹配,从最大匹配的开始位置开始
				int result=-1;//代表模拟dfa的结果
				for (int k = bindex; k < resource.length(); k++) {
					//System.out.print(resource.substring(bindex, k+1));
					result=simulator.simulator(resource.substring(bindex, k+1), dfa.DFA, 
							dfa.getDiv().getEndStatus(), dfa.getDiv().getStartStatus(), dfa.getEnterword());
					//System.out.println("经过模拟结果是"+result);
					//寻找最大匹配时遇到了不属于这个dfa的字符
					if(result==2) {
						//System.out.print(resource.substring(bindex, k+1));
						//System.out.println(resource.substring(bindex, eindex));
						if(bindex!=eindex) {
						result=simulator.simulator(resource.substring(bindex, eindex), dfa.DFA, 
								dfa.getDiv().getEndStatus(), dfa.getDiv().getStartStatus(), dfa.getEnterword());
						//System.out.println("经过模拟结果是"+result);
						}
						else {
							islegal=false;break;
						}
						//最大匹配结果不是终态,跳出这个寻找最大匹配的循环，看下一个dfa是否能从头接受这个字符
						if(result==1) {
						   //eindex=bindex+1;
						   islegal=false;break;
						}
						//终态，记号流
						else if(result==0){
							//生成记号流
							//System.out.println("一个记号流"+bindex+"-> "+eindex+":"+resource.substring(bindex,eindex));
							MarkFlow  markFlow=new MarkFlow();
							markFlow.setWord(resource.substring(bindex,eindex));
						    markFlow.setTitle(MainPanel.reTitle.get(j));
						    markFlow.setOnline(line);
						    //调整下个记号流寻找的bindex和endindex的位置
						  
						    markFlows.add(markFlow);
						    
							i=eindex-1;
							//System.out.println("i="+i);
							
							bindex=eindex;
							
							islegal=false;
							break b;
						}
							
						
					}
					else {//寻找最大匹配是发现这串字符处于终态或中间态
						//到达源程序结尾处
						if(k==resource.length()-1) {
							//终态，记号流
							if(result==0) {
								//if(k==0) 
									eindex=k+1;
							//生成记号流
								//System.out.println("一个记号流"+bindex+"-> "+eindex+":"+resource.substring(bindex,eindex));
								MarkFlow  markFlow=new MarkFlow();
								markFlow.setWord(resource.substring(bindex,eindex));
							    markFlow.setTitle(MainPanel.reTitle.get(j));
							    markFlow.setOnline(line);
							    //调整下个记号流寻找的bindex和endindex的位置
							    markFlows.add(markFlow);
								i=eindex-1;
								//System.out.println(i);
								
								bindex=eindex;
								
								islegal=false;
								break b;
							}
							//中间态
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
		
		//非法
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
