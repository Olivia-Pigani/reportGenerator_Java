import java.util.ArrayList;
import java.util.List;
import java.io.PrintStream;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class HMI {

	private Scanner scanner = new Scanner(System.in);
	private static int choice;
	

	public HMI(){}
	
	public int mainMenu(){
		
		
		System.out.println(
		
		"""
		==== Welcome to the report generator ! ====
		Please make a choice:
		1. generate a report
		2. quit the program
		"""
		);
		
		choice = scanner.nextInt();
		scanner.nextLine(); 
		return choice;
	}
	
	
	public List<File> generateReport(){
		
		int fileAmount;
		
		do{
				System.out.println("How much files do you want to use to make the report (you can use 4 files maximum) ? ");
				fileAmount = scanner.nextInt();
				scanner.nextLine();
		}while(fileAmount > 4 || fileAmount < 1);
			
			
		return filesMaker(fileAmount);
		
	}
	
	private List<File> filesMaker(int fileAmount){
		
		List<File> fileList = new ArrayList<>();
		
		for(int i = 1; i<=fileAmount;i++){
			File newFile = new File();
			
			System.out.println("file number " + i + ": ");
			System.out.println("How much sales for this file ?");
			int salesAmount = scanner.nextInt();
			scanner.nextLine();
			
			for(int j = 0 ; j< salesAmount;j++){				
				System.out.println("First sale:");
				
				System.out.println("Name of the product ?");
				String name = scanner.nextLine();
				
				System.out.println("Price per unit (in €)?");
				double price = scanner.nextDouble();
				scanner.nextLine();
				
				System.out.println("Sold quantity ?");
				double quantity = scanner.nextDouble();
				scanner.nextLine();
				
				System.out.println("The date of the sale (dd/MM/yyyy format) ?");
				String date = scanner.nextLine();
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate dateLD = LocalDate.parse(date, dtf);
				
				Sale newSale = new Sale (name, quantity, price, dateLD);
				newFile.getSaleList().add(newSale);
			}
				

				fileList.add(newFile);	

		}
			return fileList;
		
		
		
	}
	
	private void quitProgram(){
		System.out.println("bye !");
	}


























}
