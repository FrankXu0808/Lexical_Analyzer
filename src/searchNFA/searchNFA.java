package searchNFA;

import java.util.ArrayList;
import java.util.List;

import REtoNFA.Arc;
import REtoNFA.Node;
import REtoNFA.nfaPart;

public class searchNFA {
	public  int  status=1;//状态
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	public int getStartStstus() {
		return startStstus;
	}
	public void setStartStstus(int startStstus) {
		this.startStstus = startStstus;
	}
	public List<Integer> getEndStatus() {
		return endStatus;
	}
	public void setEndStatus(List<Integer> endStatus) {
		this.endStatus = endStatus;
	}
	public  List<Node> nodes=new ArrayList<>();
	public  List<Character> enterword=new ArrayList<>();
	public  int startStstus;
	public  List<Integer> endStatus=new ArrayList<>();
public  void searchNFA(Node head) {//深度优先遍历NFA
	
	Node node=head;
	startStstus=head.getStatus();
	Node nextNode=null;
	    if(node.getStatus()==0) {
	    	node.setStatus(status++);
	    	nodes.add(node);
	    }
		
		
		for (int i = 0; i < node.getArcs().size(); i++) {
			
			//System.out.print("从状态"+node.getStatus()+"到状态");
			nextNode=toNextNode(node, i);
			if(nextNode.getStatus()==0) {
			nextNode.setStatus(status++);
			nodes.add(nextNode);
			}
			//System.out.print(nextNode.getStatus());	
			
			if(nextNode.getArcs().isEmpty()) {//终态
				boolean exist=false;
				for (Integer integer : endStatus) {
					if(integer==nextNode.getStatus()) {
						exist=true;break;
					}
				}
				if(!exist) endStatus.add(nextNode.getStatus());
				
			}
			char word=node.getArcs().get(i).getWord();
			//System.out.println("，由字符"+word+"到达.");
		    boolean exist=false;
			for (Character character : enterword) {
				if(character==word) {
					exist=true;
				}
			}
			if(!exist&&word!='!') enterword.add(word);
			if(nextNode.getStatus()>node.getStatus()) 	searchNFA(nextNode);
		
		}
		
	
}
public  Node toNextNode(Node node,int arcindex) {
	
	return node.getArcs().get(arcindex).getPto();
}
}
