package REtoNFA;
//NFA���仡
public class Arc {
private Node Pfrom;//ǰ�����
private Node Pto;//��̽��
char word;//���ܵ������ַ�
public Node getPfrom() {
	return Pfrom;
}
public void setPfrom(Node pfrom) {
	Pfrom = pfrom;
}
public Node getPto() {
	return Pto;
}
public void setPto(Node pto) {
	Pto = pto;
}
public char getWord() {
	return word;
}
public void setWord(char word) {
	this.word = word;
}
}
