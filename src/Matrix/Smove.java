package Matrix;

import java.util.ArrayList;
import java.util.List;

public class Smove {
	
	public static List<Integer> smove(String[][] matrix,List<Integer> status,int wordindex) {
		List<Integer> S=new ArrayList<>();
		
		for (int i = 0; i < status.size(); i++) {
			if(matrix[status.get(i)][wordindex]==null) {
				//²»ÍùSÖÐÌí¼Ó
			}
			else {
				System.out.println("   length="+matrix.length+"     parse->"+status.get(i)+","+wordindex+":"+matrix[status.get(i)][wordindex]);
			int temp=Integer.parseInt(matrix[status.get(i)][wordindex]);
			if(!S.contains(temp)) S.add(temp);}
		}
		return S;
		
	}
}
