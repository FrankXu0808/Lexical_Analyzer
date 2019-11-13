package NFAtoDFA;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
//�������㷨״̬����
public class NewStatus implements Cloneable{
public List<Integer> status;//״̬��

public String name;//����ʶ
public char word='!';//�����ַ�
public String fromStatus;//��Դ״̬����
public boolean head=false;//�Ƿ������ʼ״̬���
public boolean tail=false;//�Ƿ������̬���
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
             stu = (NewStatus)super.clone();   //ǳ����  
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
