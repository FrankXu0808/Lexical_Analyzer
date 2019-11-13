package Matrix;

import java.util.ArrayList;
import java.util.List;

import NFAtoDFA.NfaToDfa;
import REtoNFA.nfaPart;
import searchNFA.searchNFA;

public class Matrix {
public  String startStatus="";
public  List<String> endStatus=new ArrayList<>();
public  String[][] matrix;
public Matrix(int NfaToDfa_sizecount,int enterword_size) {
	matrix=new String[NfaToDfa_sizecount][enterword_size];
}
public String getStartStatus() {
	return startStatus;
}
public String[][] getMatrix() {
	return matrix;
}
public void setMatrix(String[][] matrix) {
	this.matrix = matrix;
}
public void setStartStatus(String startStatus) {
	this.startStatus = startStatus;
}
public  List<String> getEndStatus() {
	return endStatus;
}
public  void setEndStatus(List<String> endStatus) {
	this.endStatus = endStatus;
}



}
