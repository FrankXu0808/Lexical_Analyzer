package MiniDfa;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import Matrix.Matrix;
import NFAtoDFA.NewStatus;
import NFAtoDFA.NfaToDfa;
import REtoNFA.nfaPart;
import searchNFA.searchNFA;

public class MiniDfa {
public  Division Div=new Division();//����
public  List<NewStatus> newStatus= new ArrayList<>();//״̬����
public  NewStatus[][] StatusMatrix;//
public   List<Character> enterword=new ArrayList<>();//�����ַ�
public  String [][] DFA;
public  List<NewStatus> NfaToDfa_newStatus;
public  List<Character> searchNFA_enterword;
public Matrix Matrix;
public Division getDiv() {
	return Div;
}

public void setDiv(Division div) {
	Div = div;
}

public List<NewStatus> getNewStatus() {
	return newStatus;
}

public void setNewStatus(List<NewStatus> newStatus) {
	this.newStatus = newStatus;
}

public NewStatus[][] getStatusMatrix() {
	return StatusMatrix;
}

public void setStatusMatrix(NewStatus[][] statusMatrix) {
	StatusMatrix = statusMatrix;
}

public List<Character> getEnterword() {
	return enterword;
}

public void setEnterword(List<Character> enterword) {
	this.enterword = enterword;
}

public String[][] getDFA() {
	return DFA;
}

public void setDFA(String[][] dFA) {
	DFA = dFA;
}

public Matrix getMatrix() {
	return Matrix;
}

public void setMatrix(Matrix matrix) {
	this.Matrix = matrix;
}

public List<NewStatus> getNfaToDfa_newStatus() {
	return NfaToDfa_newStatus;
}

public void setNfaToDfa_newStatus(List<NewStatus> nfaToDfa_newStatus) {
	NfaToDfa_newStatus = nfaToDfa_newStatus;
}

public List<Character> getSearchNFA_enterword() {
	return searchNFA_enterword;
}

public void setSearchNFA_enterword(List<Character> searchNFA_enterword) {
	this.searchNFA_enterword = searchNFA_enterword;
}

public MiniDfa(List<NewStatus> NfaToDfa_newStatus,List<Character> searchNFA_enterword) {
	//newStatus=NfaToDfa.newStatus;
	for (NewStatus strings : NfaToDfa_newStatus) {
		newStatus.add(strings);
	}
	//enterword=searchNFA.enterword;
	for (Character strings : searchNFA_enterword) {
		enterword.add(strings);
	}
}
//public static String startStatus;
//public static List<String> endStatus=new ArrayList<>();
//public static int miniDfaStartStatusIndex;//��Сdfa�ĳ�̬��index
//public static List<Integer> miniDfaEndStatusIndexList=new ArrayList<>();//��Сdfa����̬��index����

//������
public  void MiniDfa() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
	//��ʼ������
	List<Integer> sNOf=new ArrayList<>();
	List<Integer> F=new ArrayList<>();
	for (NewStatus newStatus2 : newStatus) {
		if(newStatus2.isTail()) {
			boolean exist=false;
			for (Integer integer : F) {
				if(integer==Integer.parseInt(newStatus2.getName())) exist=true;
			}
			if(!exist) F.add(Integer.parseInt(newStatus2.getName()));
		}
		else {
			boolean exist=false;
			for (Integer integer : sNOf) {
				if(integer==Integer.parseInt(newStatus2.getName())) exist=true;
			}
			if(!exist) sNOf.add(Integer.parseInt(newStatus2.getName()));
			
			
			
		}
	}
	Div.getDivision().add(F);
	Div.getDivision().add(sNOf);
	
	System.out.println(F);
//	�ȶ���̬�����л���
//	System.out.println("�ȶ���̬�����л���");
	for (int i = 1; i < Div.getDivision().size(); i++) {	
	if(Div.getDivision().get(i).size()>1) {
	StatusMatrix=createMatrix(Div.getDivision().get(i),enterword);
	
	int divSize=Div.getDivision().size();
	
	operate(Div,i);
	
	int opDivSize=Div.getDivision().size();
	
	if(divSize==opDivSize) break;
	 }
	}
//	System.out.println("&&&&&&&&&&&&&&");
//    for (int i = 0; i < Div.getDivision().size(); i++) {
//		for(int j=0;j<Div.getDivision().get(i).size();j++) {
//			System.out.print(Div.getDivision().get(i).get(j)+" ");
//			
//		}System.out.println();
//	}
	//��s-f�����л���
	for (int i = 1; i < Div.getDivision().size(); i++) {	
		if(Div.getDivision().get(i).size()>1) {
		StatusMatrix=createMatrix(Div.getDivision().get(i),enterword);
		
		int divSize=Div.getDivision().size();
		
		operate(Div,i);
		
		int opDivSize=Div.getDivision().size();
		
		if(divSize==opDivSize) break;
		 }
		}
//	System.out.println("&&&&&&&&&&&&&&");
//    for (int i = 0; i < Div.getDivision().size(); i++) {
//		for(int j=0;j<Div.getDivision().get(i).size();j++) {
//			System.out.print(Div.getDivision().get(i).get(j)+" ");
//			
//		}System.out.println();
//	}
	//ȷ����״̬����̬����ȥ��״̬
	//��̬����̬��
	for (int i=0;i<Div.getDivision().size();i++) {
		for (Integer integer : Div.getDivision().get(i)) {
			
		//
		for (NewStatus status : newStatus) {
			if(status.getName().equals(integer+"")) {
				if(status.isHead()) {
					Div.setStartStatus(i);
				}
				if(status.isTail()) {
					//System.out.println("name="+status.getName());
					boolean exist=false;
				   for (Integer integer2 : Div.getEndStatus()) {
					if(i==integer2) {
						exist=true;break;
					}
				}
					if(!exist) {
						//System.out.println("add:"+i+" to "+Div.getEndStatus().toString());
						Div.getEndStatus().add(i);}
				}
			}
		}
	}
}
	
	//ȥ��״̬
	List<Integer> deadStatus=new ArrayList<>();//��״̬�ļ���
	for (int i=0;i<Div.getDivision().size();i++) {
    
	 if(Div.getDivision().size()==1) {
		 boolean isdeadStatus=true;
		 for (NewStatus status : newStatus) {
			if(!(status.getFromStatus()!=null&&status.getFromStatus().equals(Div.getDivision().get(i).get(0)+"")&&status.getStatus().equals(status.getFromStatus()))) {
				isdeadStatus=false;
			}
		}
		 if(isdeadStatus) {
			 //System.out.println("��״̬");
			 deadStatus.add(Div.getDivision().get(i).get(0));
		 }
	 }
	 
	}
	for (int i = 0; i < Div.getDivision().size(); i++) {
		if(Div.getDivision().get(i).size()==1) {
			int content=Div.getDivision().get(i).get(0);
			for (Integer integer : deadStatus) {
				if(integer==content) {
					Div.getDivision().remove(i);
					i--;
				}
			}
		}
	}
	//����miniDfa����,ȷ����̬,��̬
	String [][]miniDfa=new String[Div.getDivision().size()][enterword.size()];
	for (int i = Div.getDivision().size()-1; i>=0; i--) {
		//�ж��Ƿ��ǳ�̬
//		if(Div.getDivision().get(i).contains(Integer.parseInt(Matrix.startStatus))){
//			startStatus=i+"";
//		}
		
		Collections.sort(Div.getDivision().get(i));
		for (int j = 0; j < enterword.size(); j++) {
			int index=0;
			boolean isnull=false;
		  for (int j2 = 0; j2 < Div.getDivision().size(); j2++) {
//			  if(Matrix.matrix[Div.getDivision().get(i).get(0)-1]==null||
//				(Matrix.matrix[Div.getDivision().get(i).get(0)-1]!=null&&
//				Matrix.matrix[Div.getDivision().get(i).get(0)-1][j]==null)	  ) {
//				  isnull=true;
//			  }
			  if((Div.getDivision().get(i).get(0)-1)>=Matrix.matrix.length||
					  Matrix.matrix[Div.getDivision().get(i).get(0)-1][j]==null) {
				  isnull=true;
			  }
			  //move�����Ľ����div��ĳ��״̬��
			  else {
			  Integer integer=Integer.parseInt(Matrix.matrix[Div.getDivision().get(i).get(0)-1][j]);
			  if(Div.getDivision().get(j2).contains(integer)) {
				index=j2;break;
			}
			  }
		}	
		if(!isnull)
		miniDfa[i][j]=index+"";
		}
	}
//	System.out.println("MiNinfa����:");
//	for (int i = 0; i < miniDfa.length; i++) {
//		System.out.print(i+" ");
//		for (int j = 0; j < miniDfa[i].length; j++) {
//			System.out.print(enterword.get(j)+":"+miniDfa[i][j]+" ");
//		}System.out.println();
//	}
	DFA=miniDfa;
//	System.out.println("��̬�±�Ϊ��"+Div.getStartStatus());
//	System.out.println("��̬�±�ļ���Ϊ:");
//	for (Integer integer : Div.getEndStatus()) {
//		System.out.print(integer+" ");
//	}
//	System.out.println();
//	System.out.println("-----------------------");
//	System.out.println("��С��dfa:");
//	for (List<Integer> part : Div.getDivision()) {
//		System.out.print("����:");
//		for (Integer integer : part) {
//			System.out.print(integer+" ");
//		}System.out.println();
//	}
//	System.out.println("��̬�±�Ϊ��"+Div.getStartStatus());
//	System.out.println("��̬�±�ļ���Ϊ:");
//	for (Integer integer : Div.getEndStatus()) {
//		System.out.print(integer+" ");
//	}System.out.println("-------------------------");
}
public  boolean isSamePart(String a,String b) {//�Ƿ���Թ�Ϊһ��
	
	for (List<Integer> part : Div.getDivision()) {
		boolean ain=false;
		boolean bin=false;
		for (Integer integer : part) {
			if(Integer.parseInt(a)==integer) {ain=true;}
			if(Integer.parseInt(b)==integer) {bin=true;}
		}
		if(ain&&bin) {
			//System.out.println(a+" and "+b+" are in a part");
			return true;
		}
	}
	return false;
}
//���ֲ���
private  Division operate(Division div,int index) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
	List<String> OnOneGroup=new ArrayList<>();
	for (int i = 0; i < StatusMatrix.length-1; i++) {
		for (int j = i+1; j < StatusMatrix.length; j++) {
			boolean equal=true;
		  
		 for (int k = 0; k < StatusMatrix[i].length; k++) {
			   if((StatusMatrix[i][k]==null&&StatusMatrix[j][k]!=null)
					   ||(StatusMatrix[j][k]==null&&StatusMatrix[i][k]!=null)) {
				   equal=false;break;
			   }
			   else if(StatusMatrix[i][k]!=null&&StatusMatrix[j][k]!=null) {
				   if(!isSamePart(StatusMatrix[i][k].getName(),StatusMatrix[j][k].getName())) {
				equal=false;break;
			}
			 }
		   }
		 if(equal) {
			 System.out.println("$$$$$$$$$");
			 System.out.println(StatusMatrix[i][0].getFromStatus()+"="+StatusMatrix[j][0].getFromStatus());
			 if(OnOneGroup.isEmpty()) OnOneGroup.add(StatusMatrix[i][0].getFromStatus()+" "+StatusMatrix[j][0].getFromStatus());
			 else{
				 String judge="";
				 String laString=OnOneGroup.get(OnOneGroup.size()-1);
				 for (int k = 0; k < laString.length(); k++) {
					if(laString.charAt(k)!=' ') judge+=laString.charAt(k);
					else break;
				}
				 if(StatusMatrix[i][0].getFromStatus().equals(judge)) {//�жϵõ�����״̬������һ�����д���
					String re=OnOneGroup.get(OnOneGroup.size()-1);
					OnOneGroup.remove(OnOneGroup.size()-1);
					OnOneGroup.add(re+" "+StatusMatrix[j][0].getFromStatus());
					System.out.println("Ҫappend�ı��ַ���"+OnOneGroup.get(OnOneGroup.size()-1));
					System.out.println("�д��ڱ�״̬"+StatusMatrix[i][0].getFromStatus());
				 }
				 else {
					 String news=StatusMatrix[i][0].getFromStatus()+" "+StatusMatrix[j][0].getFromStatus();
					 if(!OnOneGroup.get(OnOneGroup.size()-1).contains(news)) {
					 OnOneGroup.add(news);
					 }
				 }
				 
				 
			 }
		 }
		 
		}
		
		
	}
	List<Integer> ori=div.getDivision().get(index);
	List<Integer> forNotInAPart=new ArrayList<>();
	div.getDivision().remove(index);
	
	System.out.println(OnOneGroup);
	if(OnOneGroup.isEmpty()) {
		for (Integer integer : ori) {
			List<Integer> newpart=new ArrayList<>();
			newpart.add(integer);
			div.getDivision().add(newpart);
		}
	}
	else {
	for (String string : OnOneGroup) {
		System.out.println("����һ�����ɷֵ���:"+string);
		List<Integer> newpart=new ArrayList<>();
		for (String string2 : string.split("\\s")) {
			newpart.add(Integer.parseInt(string2));
		for (int j = 0; j < ori.size(); j++) {

				if(ori.get(j)==Integer.parseInt(string2)) {
					forNotInAPart.add(ori.get(j));
				}
			}

		}
		for (Integer integer : ori) {
			boolean exist=false;
			for (Integer integer2 : forNotInAPart) {
				if(integer2==integer) {
					exist=true;break;
				}
			}
			if(!exist) {
				List<Integer> newpartFromOri=new ArrayList<>();
				newpartFromOri.add(integer);
				div.getDivision().add(newpartFromOri);
			}
		}
		div.getDivision().add(newpart);
	}
	}
	return div;
}
private  NewStatus[][] createMatrix(List<Integer> sdivision, List<Character> enterword) {

	NewStatus[][] StatusMatrix=new NewStatus[sdivision.size()][enterword.size()];
	for (int i = 0; i < StatusMatrix.length; i++) {
		for (int j = 0; j < StatusMatrix[i].length; j++) {
			NewStatus temp=null;
			for (NewStatus newStatus : newStatus) {
				if(newStatus.getFromStatus()!=null&&(newStatus.getFromStatus().equals(((Integer)(sdivision.get(i))).toString()) &&newStatus.getWord()==enterword.get(j))) {
					temp=newStatus;break;
				}
			}
			//if(temp==null) System.out.println("�������ʽ����û�ҵ��ȶ�Ŀ���NewStatus");
				StatusMatrix[i][j]=temp;
		}
	}
	//System.out.println("�������ʽsuccess");
	return StatusMatrix;
}


}
