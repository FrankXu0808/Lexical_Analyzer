package FileOperate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

public class FileOutPut {
	public static void writeByFileOutputStream(String _sDestFile,

			String _sContent) throws IOException {
//		String path="/example/filetest"; 
	
		try {
			RandomAccessFile rf=new RandomAccessFile(_sDestFile,"rw"); 
			//����һ����RandomAccessFile�Ķ��󣬲�ʵ���� 
			rf.seek(rf.length());//��ָ���ƶ����ļ�ĩβ 
			rf.writeBytes(_sContent); 
			rf.close();//�ر��ļ��� 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
			}
	public static void clearInfoForFile(String fileName) {
        File file =new File(fileName);
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter =new FileWriter(file);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}