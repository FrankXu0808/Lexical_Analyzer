package REtoNFA;
//NFA�ṹ
public class nfaPart {
 Node head;//��̬���
 Node tail;//��̬���
 String realmean; //NFA����ʾ�ļǺ�������
public String getRealmean() {
	return realmean;
}
public void setRealmean(String realmean) {
	this.realmean = realmean;
}
public Node getHead() {
	return head;
}
public void setHead(Node head) {
	this.head = head;
}
public Node getTail() {
	return tail;
}
public void setTail(Node tail) {
	this.tail = tail;
}
}
