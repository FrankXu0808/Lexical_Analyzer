package NFAtoDFA;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.net.ssl.SSLEngineResult.Status;

import Matrix.Matrix;
import REtoNFA.Arc;
import REtoNFA.Node;
import REtoNFA.nfaPart;
import REtoNFA.reToPostfixExpression;
import searchNFA.searchNFA;

public class NfaToDfa {
	//private static nfaPart nfa=reToPostfixExpression.result.get(reToPostfixExpression.result.size()-1);
	public  List<Node> nodes;
	//public static List<Integer> status=new  ArrayList<>();
	public  int operatecount=0;
	public  List<Character> enterword;//�����ַ�
	public  List<NewStatus> newStatus=new ArrayList<>();//״̬����
	//public static Matrix dfa=new Matrix();//����dfa��ת������
	public  int sizecount=0;//���������
	public  List<Integer> searchNFA_endStatus;
	public Matrix Matrix;
	public List<Integer> getSearchNFA_endStatus() {
		return searchNFA_endStatus;
	}
	public void setSearchNFA_endStatus(List<Integer> searchNFA_endStatus) {
		this.searchNFA_endStatus = searchNFA_endStatus;
	}
	public List<Node> getNodes() {
		return nodes;
	}
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	public List<Character> getEnterword() {
		return enterword;
	}
	public void setEnterword(List<Character> enterword) {
		this.enterword = enterword;
	}
	public  void searchNULL(Node node,List<Integer> result) {//�ձհ�����
		
//		for (Arc arc  : node.getArcs()) {
//			boolean exist=false;
//			if(arc.getWord()=='!') {
//				for (Integer integer : result) {
//					if(integer==arc.getPto().getStatus()) exist=true;
//				}
//				if(!exist) { result.add(arc.getPto().getStatus());
//				searchNULL(arc.getPto(),result);}
//			}
//		}
		Stack<Node> nodes=new Stack<>();
		nodes.add(node);
		while(!nodes.isEmpty()) {
			Node temp=nodes.pop();
			for (Arc arc : temp.getArcs()) {
				boolean exist=false;
				if(arc.getWord()=='!') {
					for (Integer integer : result) {
						if(integer==arc.getPto().getStatus()) exist=true;
					}
					if(!exist) { nodes.add(arc.getPto());result.add(arc.getPto().getStatus());
				}
			}
		}
	}
		
		
		
	}
	public  List<Integer> ClosureOperate(List<Integer> status,List<Integer> result) {
		
		for (Integer integer : status) {
			boolean exist=false;
			for (Integer integer2 : result) {
				if(integer2==integer) {exist=true;break;}
			}
			if(!exist)result.add(integer);
		}
		for (Integer integer : status) {
			
			searchNULL(nodes.get(integer-1), result);
		}
		
		return result;
		
	}
	public Matrix getMatrix() {
		return Matrix;
	}
	public  List<Integer> Smove(List<Integer> status,char a){//Smove
		List<Integer> result=new ArrayList<>();
//		if(status.toString().equals("[10,6]"));
		for (Integer integer : status) {
			
			for(Arc arc:nodes.get(integer-1).getArcs()) {
				//System.out.println("״̬��"+integer+"�л�:"+arc.getWord()+"��"+a+"�Ƚ�");
				if(arc.getWord()==a) {
					boolean exist=false;
					for (Integer integer2 : result) {
						if(integer2==arc.getPto().getStatus()) exist=true;
					}
					if(!exist) {result.add(arc.getPto().getStatus());
					//System.out.println("����smove����״̬"+arc.getPto().getStatus());
					}
				}
				
			}
		}
		return result;
	}
	//�������㷨ÿһ���Ĳ���
	public  NewStatus operateByOneWord(Character character,
			NewStatus temp){

		List<Integer> var1=Smove(temp.getStatus(), character);
		List<Integer> var2=new ArrayList<>();
		var1.addAll(var2);
	
		List<Integer> s=ClosureOperate(var1,var2);//byword�����л�����ڱ���list��ͬʱ��������

		boolean noexist=true;
		for (NewStatus ns : newStatus) {
			boolean equal=false;
			if(ns.getStatus().size()==s.size()) {
				equal=true;
//			for (int i = 0; i < s.size(); i++) {
//				if(ns.getStatus().get(i)!=s.get(i)) {
//					equal=false;break;
//				}
//			}
				if(!s.toString().equals(ns.getStatus().toString())){
					equal=false;
				}
				
			if(equal&&!s.isEmpty()) {//���������״̬��ǰ����ֹ����Ѵ���
				NewStatus nStatus=new NewStatus();
				nStatus.setName(ns.getName());
				nStatus.setStatus(s);
				nStatus.setWord(character);
				nStatus.setFromStatus(temp.getName());
				judgeEndStatus(nStatus);//�ж��Ƿ�����̬
				newStatus.add(nStatus);
				//System.out.println("��״̬"+temp.getStatus()+"���ַ�"+character+"��þ�״̬:"+s);
				noexist=false;
				return null;
			}
			}
		}
		
		if(noexist&&!s.isEmpty()) {//���״̬���µ�
			NewStatus nStatus=new NewStatus();
			statuscount++;
			nStatus.setName(statuscount+"");
			//System.out.println("%%%%%%%statuscount="+statuscount);
			nStatus.setStatus(s);
			//System.out.println(s);
			nStatus.setWord(character);
			nStatus.setFromStatus(temp.getName());
			judgeEndStatus(nStatus);//�ж��Ƿ�����̬
			newStatus.add(nStatus);
			//System.out.println("��״̬"+temp.getStatus()+"���ַ�"+character+"�����״̬:"+s);
			return nStatus;
		
		}
		return null;
	     
	}
	public static String d="";
	public static String f="";
	public  int operateByWord(NewStatus temp) {	
//		if(operatecount==0) {
//			d=temp.toString();
//		}
//		NewStatus save=temp.clone();
//		if(operatecount++==0) f=save.toString();
		//System.out.println("---------------------------");
	//System.out.println("))))))-"+save.getStatus());
	Stack<NewStatus> stack=new Stack<>();
	stack.add(temp);
	while(!stack.isEmpty()) {
	//System.out.println("@@@@@");
		NewStatus tempor=stack.pop();
		for (Character character : enterword) {
			NewStatus status;
			status=operateByOneWord(character,tempor);
			if(status!=null) {//״̬��
				stack.add(status);
				//System.out.println(status.getStatus());
			}
		}
	}
//	System.out.println("��״̬"+temp.getStatus()+"�����Ӽ���");
//		for (Character character : enterword) {	
//		
//			if(temp.getStatus()!=null&&temp.getName()!=save.getName()) {temp=save;}
//            NewStatus status;
//           // System.out.println("��״̬"+temp.getStatus()+"��ʼ");
//			status=operateByOneWord(character,temp);
//			
//			if(status!=null) {
//				temp=status;
//				operateByWord(temp);
//			}
//			
//			//System.out.println(temp+"||"+temp.getStatus());
//			if((temp.toString().equals(d)||temp.toString().equals(f))&&character==enterword.get(enterword.size()-1)) {temp=null;return 1;}
//	 }
		return 0;
	}
	private  void judgeEndStatus(NewStatus status) {//�ж��Ƿ�����̬
		
	   for (Integer integer : searchNFA_endStatus) {
		 for (Integer integer2 : status.getStatus()) {
			if(integer==integer2) {
				status.setTail(true);
				//System.out.println(status.getStatus()+"����̬");break;
			}
		}
	}
		
	}
	public  int statuscount=1;
	public  void NfaToDfa(List<Integer> status) throws InterruptedException {
	    
		List<Integer> s0;
		
		s0=ClosureOperate(status,new ArrayList<>());
		NewStatus s1=new NewStatus();
		
		s1.setName(statuscount+"");
		s1.setStatus(s0);
		s1.setHead(true);
		newStatus.add(s1);
		//��ó�̬�����������հ�
		
		
		NewStatus temp=s1;//��ǰҪ�����Ӽ���������״̬
		//boolean hasNew=true;
		//int count=0;
		int s=0;
		//while(temp!=null && s!=1) {	
			
		s=operateByWord(temp);
		
		//}
		
		for (NewStatus newStatus : newStatus) {
			if(newStatus.getFromStatus()!=null&&Integer.parseInt(newStatus.getFromStatus())>sizecount) sizecount++;
			//System.out.println(newStatus.getFromStatus()+" "+newStatus.getWord()+" ="+newStatus.getName()+"   head:"+newStatus.isHead()+"  tail:"+newStatus.isTail());
		}	
		Matrix=new Matrix(sizecount, enterword.size());
	    Matrix.startStatus=1+"";
	 
		for (int i = 1; i < newStatus.size(); i++) {
			if(newStatus.get(i).isTail()) {
				boolean exist=false;
				for (String string : Matrix.endStatus) {
					if(string.equals(newStatus.get(i).getName())) {exist=true;break;}
				}
				if(!exist) {
					
					Matrix.endStatus.add(newStatus.get(i).getName());
				}
			}
			Matrix.matrix[Integer.parseInt(newStatus.get(i).getFromStatus())-1][enterword.indexOf(newStatus.get(i).getWord())]=newStatus.get(i).getName();
			//System.out.println(newStatus.get(i).getFromStatus()+"@@"+enterword.indexOf(newStatus.get(i).getWord())+"@@"+newStatus.get(i).getName());
		}
//		System.out.println("dfa����");
//		for (int i = 0; i < Matrix.matrix.length; i++) {
//			System.out.print(i+1+" ");
//			for (int j = 0; j < Matrix.matrix[i].length; j++) {
//				System.out.print(enterword.get(j)+":"+Matrix.matrix[i][j]+" ");
//			}System.out.println();
//		}
//		System.out.println("��̬:");
//		System.out.println(Matrix.startStatus);
//		System.out.println("��̬��");
//		for (String integer : Matrix.endStatus) {
//			System.out.print(integer+" ");
//		}
//		System.out.println();		
		
	}
}
