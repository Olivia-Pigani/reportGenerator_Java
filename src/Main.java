import java.util.List;
import java.util.ArrayList;

public class Main {
	
	private static HMI hmi = new HMI();
	private static SumFileUtil sfu = new SumFileUtil();
	private static List<SumFile> sumFileList = new ArrayList<>();
	
	public static void main(String... args){
		
		int choice = hmi.mainMenu();		
		switch(choice){
			case 1 -> generateReport();
			case 2 -> {
				hmi.quitProgram();
				System.exit(0);
			} 
		}
		
		
	}
	
	private static void generateReport(){
		
		List<File> fileList = hmi.generateReport();
		
		SumFile sumFile = sfu.sumFileGenerator(fileList);
		
		System.out.println(sumFile);
		
	}
	
	
	
}