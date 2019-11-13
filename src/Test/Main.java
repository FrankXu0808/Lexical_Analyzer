package Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import DfaSimulator.simulator;
import FileOperate.FileOutPut;
import Matrix.Matrix;
import MiniDfa.Division;
import MiniDfa.MiniDfa;
import NFAtoDFA.NfaToDfa;
import REtoNFA.reToPostfixExpression;
import searchNFA.searchNFA;

public class Main {
	public static void main(String[] args) throws IOException {
		//String re="(a|b|c|d|e|f|g|h|i|j|k|l|m|n|o|p|q|r|s|t|u|v|w|x|y|z|A|B|C|D|E|F|G|H|I|J|K|L|M|N|O|P|Q|R|S|T|U|V|W|X|Y|Z)* (0|1|2|3|4|5|6|7|8|9)*";
//		String re="(a|b)* a b b";
//		searchNFA searchNFA = null;
//		System.out.println("正规式到nfa:");
//		searchNFA.searchNFA(reToPostfixExpression.reToNFA(re).getHead());
		//System.out.println(searchNFA.endStatus);
		//if(searchNFA.endStatus==null) System.out.println("@@@@@@@@@");
//		for (Integer integer : searchNFA.endStatus) {
//			System.out.println("nfa的结束状态:"+integer);
//		}
//		System.out.println("--------------------------");
//		System.out.println("nfa到dfa:");
//		List<Integer> start=new ArrayList<>();
//		start.add(reToPostfixExpression.result.get(reToPostfixExpression.result.size()-1).getHead().getStatus());
//		NfaToDfa.NfaToDfa(start);
		
		
		//MiniDfa.MiniDfa();
//		File file=new File("D:\\tomcat\\webapps\\ROOT\\LexicalAnalyzer\\src\\Test\\resource.txt");
//		System.out.println(simulator.simulator(file, MiniDfa.DFA, MiniDfa.Div.endStatus, MiniDfa.Div.startStatus, searchNFA.enterword));
//		String replace="()|*";
//		
//		String handleString="(a|b)*";
//		for (int i = 0; i < handleString.length(); i++) {
//			if(replace.contains(handleString.charAt(i)+"")) {
//				if(handleString.charAt(i)=='(') {
//					handleString=handleString.replace('(', '『');
//				}
//				else if(handleString.charAt(i)==')') {
//					handleString=handleString.replace(')', '』');
//				}
//				else if(handleString.charAt(i)=='|') {
//					handleString=handleString.replace('|', 'Ⅰ');
//				}
//				else if(handleString.charAt(i)=='*') {
//					handleString=handleString.replace('*', 'ㄨ');
//				}
//			}
//		}
//		
//		System.out.println(handleString);
		
		
//		String a="a   \n"
//				+ "bb  bb\n\n\n"
//				+ "cc c  ccc\n";
//		System.out.println(a+"-----------");
//		for (String string : a.split("\\s+")) {
//			System.out.println(string);
//		}
//		for (String string : a.split("(\n)+")) {
//			System.out.println(string);
//		}
//	
		FileOutPut.writeByFileOutputStream("src/FileOperate/resource.txt", "dsadasdasda\ndsadsadas\n");
	}
}
