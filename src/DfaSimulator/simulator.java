package DfaSimulator;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import Matrix.Matrix;
import Matrix.Smove;

public class simulator {
	//0 ��̬ 1����̬ 2�Ƿ��ַ�����
	//ģ����
public static int simulator(String enterstring, String[][] matrix,List<Integer> endStatus,int startstatus,List<Character> wordlimit) {
	List<Integer> S=new ArrayList<>();
	S.add(startstatus);//��ʼ����̬����
	//System.out.println("��"+enterstring+"����ģ��");
	int finall=0;
	
		
		
		//while((tempchar=reader.read())!=-1) 
		for (int j = 0; j < enterstring.length(); j++) 
		{
			//System.out.print("ģ������:"+j+":"+enterstring.charAt(j));
			int wordindex=-1;
			for (int i = 0; i < wordlimit.size(); i++) {
				if(wordlimit.get(i)==enterstring.charAt(j)) {
					wordindex=i;break;
				}
			}
			if(wordindex>=0) {
				//System.out.print(S.toString()+"����"+wordindex);
			List<Integer> S1=Smove.smove(matrix, S, wordindex);
			//System.out.println("�õ�"+S1.toString());
			S.clear();S=S1;
			
			if(S.isEmpty()) return 2;
			else if(endStatus.contains(S.get(0))) finall=0;//��̬
			else finall=1;//����̬
			}
			else {
				System.out.println("�Ƿ��ַ�:"+enterstring.charAt(j));return 2;
			}
		}

	return finall;

}
}
