package NFAtoDFA;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
//汤普孙算法状态集合
public class NewStatus implements Cloneable{
public List<Integer> status;//状态集

public String name;//结点标识
public char word='!';//接受字符
public String fromStatus;//来源状态集合
public boolean head=false;//是否包含初始状态结点
public boolean tail=false;//是否包含终态结点
public boolean isHead() {
	return head;
}
public void setHead(boolean head) {
	this.head = head;
}
public boolean isTail() {
	return tail;
}
public void setTail(boolean tail) {
	this.tail = tail;
}
public static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {    
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();    
    ObjectOutputStream out = new ObjectOutputStream(byteOut);    
    out.writeObject(src);    

    ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());    
    ObjectInputStream in = new ObjectInputStream(byteIn);    
    @SuppressWarnings("unchecked")    
    List<T> dest = (List<T>) in.readObject();    
    return dest;    
}    
@Override  
     public NewStatus clone() {  
        NewStatus stu = null;  
       try{  
             stu = (NewStatus)super.clone();   //浅复制  
        }catch(CloneNotSupportedException e) {  
           e.printStackTrace();  
         }  
        List<Integer> sList=new ArrayList<>();
        try {
			sList=deepCopy(stu.getStatus());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        stu.setStatus(sList);
        return stu;  
     }  

public char getWord() {
	return word;
}
public void setWord(char word) {
	this.word = word;
}
public String getFromStatus() {
	return fromStatus;
}
public void setFromStatus(String fromStatus) {
	this.fromStatus = fromStatus;
}
public List<Integer> getStatus() {
	return status;
}
public void setStatus(List<Integer> status) {
	this.status = status;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
}
