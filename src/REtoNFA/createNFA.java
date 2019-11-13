package REtoNFA;

import java.util.ArrayList;
import java.util.List;
//构造NFA
public class createNFA {
private nfaPart[] part=null;
public  nfaPart CreateSingleWord(char a) {//输入单一字符
	nfaPart part=new nfaPart();
	Node head=new Node();
	Node tail=new Node();
	List<Arc> arcs=new ArrayList<Arc>();
	Arc arc=new Arc();
	arc.setPfrom(head);
	arc.setPto(tail);
	arc.setWord(a);
	arcs.add(arc);
	head.setArcs(arcs);
	part.setHead(head);
	part.setTail(tail);
	part.setRealmean(a+"");
	return part;
}
public nfaPart createOr(nfaPart a,nfaPart b) {//或
	nfaPart part=new nfaPart();
	Node head=new Node();
	Node tail=new Node();
	List<Arc> arcs1=new ArrayList<Arc>();
	
	Arc arc1=new Arc();
	Arc arc2=new Arc();
	Arc arc3=new Arc();
	Arc arc4=new Arc();
	arc1.setPfrom(head);
	arc1.setPto(a.head);
	arc1.setWord('!');
	arc2.setPfrom(head);
	arc2.setPto(b.head);
	arc2.setWord('!');
	arc3.setPfrom(a.tail);
	arc3.setPto(tail);
	arc3.setWord('!');
	arc4.setPfrom(b.tail);
	arc4.setPto(tail);
	arc4.setWord('!');
	arcs1.add(arc1);
	arcs1.add(arc2);
	
	head.setArcs(arcs1);
	a.getTail().addArc(arc3);
	b.getTail().addArc(arc4);
	part.setHead(head);
	part.setTail(tail);
	part.setRealmean(a.getRealmean()+"|"+b.getRealmean());
	return part;
}
public nfaPart createLink(nfaPart a,nfaPart b) {//连接
	nfaPart part=new nfaPart();
	Node temp=b.getHead();
	b.setHead(a.getTail());
	b.getHead().setArcs(temp.getArcs());
	for (Arc arc : b.getHead().getArcs()) {
		arc.setPfrom(b.getHead());
	} 
		
	
	part.setHead(a.getHead());
	part.setTail(b.getTail());

	part.setRealmean(a.getRealmean()+" "+b.getRealmean());
	return part;
}
public nfaPart createClosure(nfaPart a) {//闭包
	nfaPart part=new nfaPart();
	Node head=new Node();
	Node tail=new Node();
	List<Arc> arcs1=new ArrayList<Arc>();
	
	Arc arc1=new Arc();
	Arc arc2=new Arc();
	Arc arc3=new Arc();
	Arc arc4=new Arc();
	arc1.setPfrom(head);
	arc1.setPto(a.getHead());
	arc1.setWord('!');
	arc2.setPfrom(head);
	arc2.setPto(tail);
	arc2.setWord('!');
	arc3.setPfrom(a.getTail());
	arc3.setPto(a.getHead());
	arc3.setWord('!');
	arc4.setPfrom(a.getTail());
	arc4.setPto(tail);
	arc4.setWord('!');
	arcs1.add(arc1);
	arcs1.add(arc2);
	
	head.setArcs(arcs1);
	a.getTail().addArc(arc3);
	a.getTail().addArc(arc4);
	part.setHead(head);
	part.setTail(tail);
	part.setRealmean(a.getRealmean()+"*");
	return part;
}
public nfaPart[] getPart() {
	return part;
}
public void setPart(nfaPart[] part) {
	this.part = part;
}
}
