package REtoNFA;

import java.util.ArrayList;
import java.util.List;
//NFA���
public class Node {
private int status=0;//״̬
private List<Arc> arcs=new ArrayList<>();//��̽�㼯��
public int getStatus() {
	return status;
}

public List<Arc> getArcs() {
	return arcs;
}

public void setArcs(List<Arc> arcs) {
	this.arcs = arcs;
}
public void addArc(Arc arc) {
	arcs.add(arc);
}
public void setStatus(int status) {
	this.status = status;
}

}
