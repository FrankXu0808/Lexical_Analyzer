package REtoNFA;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import searchNFA.searchNFA;

//将正规式转化为只包含（），|，*，!(空) _	连接的正规式
public class reToPostfixExpression {
	private static String sym="[-+?";
	private  List<String> partMean=new ArrayList<>();
	private  nfaPart aPart=null;
	private  nfaPart bPart=null;
	public   List<nfaPart> result=new ArrayList<>();
	private  createNFA createNFA=new createNFA();
    private static String traREtoSimply(String re) {
    	for (int i = 0; i < re.length(); i++) {
			if(re.charAt(i)=='-') {
				String temp = "";
				for (char j =re.charAt(i-1); j <=re.charAt(i+1); j++) {
					temp+=j;
				}
				
				re=re.replaceFirst(re.charAt(i-1)+"-"+re.charAt(i+1), temp);
			}
			else if(re.charAt(i)=='+') {
				if(re.charAt(i-1)==')') {
					int index=-1;
					String temp="(";
					for(int j=i-2;j>=0;j--) {
						
						if(re.charAt(j)=='(') {
							index=j;break;
						}
						temp+=re.charAt(j);
					}
					if(index==-1) System.out.println("正规式错误!");
					re=re.replaceFirst("\\+","*"+temp+")");
				}
				else {
					re=re.replaceFirst("\\+","*"+re.charAt(i));
				}
				
			}
			else if(re.charAt(i)=='?'){
				if(re.charAt(i-1)==')') {
					int index=-1;
					String temp="";
					for(int j=i-1;j>=0;j--) {
						temp+=re.charAt(j);
						if(re.charAt(j)=='(') {
							index=j;break;
						}
					
					}
					if(index==-1) System.out.println("正规式错误!");
					re=re.replaceFirst("\\?","|!");
				}
				else {
					re=re.replaceFirst("\\?","|!");
				}
				
			}
			
		}
    	for (int i = 0; i < re.length(); i++) {
    		String temp="";
			if(re.charAt(i)=='[') {
				for (int j = i+1; j < re.length(); j++) {
					if(re.charAt(j+1)!=']') {
					temp+=re.charAt(j)+"|";
					
					}
					else {
						temp+=re.charAt(j);
						break;
					}
					
				}
			re=re.replaceFirst("\\[.*\\]", temp);
		}
			
		}
    	
		return re;
		
	}
	public  nfaPart reToNFA(String re) {//将正规式转化为NFA
	    
		String op="()|* #";
		if(re.charAt(0)=='('&&re.charAt(1)=='|') {
			
					//将()|* #替换掉 『』() Ⅰ| ㄨ * 
					
					for (int i = 0; i < re.length(); i++) {
						if(op.contains(re.charAt(i)+"")) {
							if(re.charAt(i)=='(') {
								re=re.replace('(', '『');
							}
							else if(re.charAt(i)==')') {
								re=re.replace(')', '』');
							}
//							else if(re.charAt(i)=='|') {
//								re=re.replace('|', 'Ⅰ');
//							}
							else if(re.charAt(i)=='*') {
								re=re.replace('*', 'ㄨ');
							}
						}
					}
				}
			
		
		
		Stack<String> OPTR = new Stack<>();
		Stack<String> OPND = new Stack<>();
		OPTR.push("#");
		re+="#";
		int i=0;
		while(i<re.length()||(!OPTR.empty()&&OPTR.peek()!="#")) {
			if(op.indexOf(re.charAt(i))<0) {
				
				OPND.push(re.charAt(i)+"");
				i++;
			} 
			else {
				switch(Precede(OPTR.peek().charAt(0),re.charAt(i))) {
				case'<'://栈顶元素优先级低
					OPTR.push(re.charAt(i)+"");i++;break;
				case'=':{
		   			OPTR.pop();i++;break;
				}
				case'>':{
					String theta=OPTR.pop();
					if(theta.equals("*")) {
						String c=OPND.pop();
						OPND.push(c+theta);
						// System.out.println("运算符"+theta);
						createNFAPart(c,"",theta);
					}
					else {
					String b=OPND.pop();
					String a=OPND.pop();
					OPND.push(a+theta+b);
				 //System.out.println("运算符"+theta);
					createNFAPart(a,b,theta);
					
					
					
					}
					break;
				}
					
				}
				
			}
		}
		
//		for (int j = 0; j < result.size(); j++) {
//			System.out.println(result.get(j).getRealmean());
//		}
		return result.get(result.size()-1);
		
	}
	
	 private  void createNFAPart(String a, String b, String peek) {//构造NFA
		//将a,b转化为nfaPart
		 nfaPart part=new nfaPart();
		 if(a.length()==1) aPart=createNFA.CreateSingleWord(a.charAt(0));
		 else {
			aPart=result.get(result.size()-1);
		 }
		 if(b.length()==1) bPart=createNFA.CreateSingleWord(b.charAt(0));	
		 else if(b.length()>1){
			 for (int i = 0; i < result.size(); i++) {
				if(a.equals(result.get(i).getRealmean())) {
					aPart=result.get(i);
				}
			}
			 bPart=result.get(result.size()-1);
		 }
		 if(peek.equals("|")) {
			 part=createNFA.createOr(aPart, bPart);
			 
		 }
		 else if(peek.equals("*")) {
			 part=createNFA.createClosure(aPart);
		 }
		 else if(peek.equals(" ")) {
			 part=createNFA.createLink(aPart, bPart);
		 }
		 
		 result.add(part);
		 
	 }
	private static char Precede(char a, char c) {//符号优先级判断
		 if(a=='('&&c==')') return '=';
			if(a=='*'){
				switch(c){
					case'*':return '>';
					case' ':return '>';
					case'|':return '>';
					case'(':return '<';
					case')':return '>';
					case'#':return '>';
				
				}
			}
			if(a==' '){
				switch(c){
				case'*':return '<';
				case' ':return '>';
				case'|':return '>';
				case'(':return '<';
				case')':return '>';
				case'#':return '>';
				}
			}
			if(a=='|'){
				switch(c){
				case'*':return '<';
				case' ':return '<';
				case'|':return '>';
				case'(':return '<';
				case')':return '>';
				case'#':return '>';
				}
			}
			if(a=='('){
				switch(c){
				case'*':return '<';
				case' ':return '<';
				case'|':return '<';
				case'(':return '<';
				}
			}
			if(a==')'){
				switch(c){
				case'*':return '>';
				case' ':return '>';
				case'|':return '>';
				
				case')':return '>';
				case'#':return '>';
				}
			}
			if(a=='#'){
				switch(c){
				case'*':return '<';
				case' ':return '<';
				case'|':return '<';
				case'(':return '<';
				
				case'#':return '=';
				}
			}

		return 0;
	}
	

}
