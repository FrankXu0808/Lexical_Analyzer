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
			//定义一个类RandomAccessFile的对象，并实例化 
			rf.seek(rf.length());//将指针移动到文件末尾 
			rf.writeBytes(_sContent); 
			rf.close();//关闭文件流 
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
