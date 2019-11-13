package REtoNFA;
//NFA结构
public class nfaPart {
 Node head;//初态结点
 Node tail;//终态结点
 String realmean; //NFA所表示的记号流名称
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
