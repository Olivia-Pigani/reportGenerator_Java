import java.io.OutputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;

public class IoUtil{
	
	
	public static void makeTextFile(String content, long fileNumber){
		
		//example: a file with fileNumber 4, it will be -> generated_reports/report_4.txt
		File file = new File(ReportUtil.REPORT_ROOT_FILEPATH + fileNumber + ".txt"); 
		
		if(file.exists()){
			file.delete();
		}
		
		try(var writer = new BufferedWriter(new FileWriter(file))){
			writer.write(content);
			System.out.println("The text file was successfully generated!");
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
	public static void makeBinaryFile(String content, long fileNumber){
		
		//example: generated_reports/report_4.bin
		File file = new File(ReportUtil.REPORT_ROOT_FILEPATH + fileNumber + ".bin"); 
		
		if(!file.exists()){
			file.delete();
		}

		try(var outputStream = new FileOutputStream(file)){
			outputStream.write(content.getBytes());
			System.out.println("The binary file was successfully generated!");
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
}