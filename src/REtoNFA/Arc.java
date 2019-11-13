package REtoNFA;
//NFA结点间弧
public class Arc {
private Node Pfrom;//前驱结点
private Node Pto;//后继结点
char word;//接受的输入字符
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
