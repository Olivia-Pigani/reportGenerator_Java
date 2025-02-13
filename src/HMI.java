import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.io.PrintStream;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.io.File;
import java.util.stream.*;
import java.util.InputMismatchException;
import java.time.format.DateTimeParseException;

public class HMI {

	private Scanner scanner = new Scanner(System.in);
	private static int choice;
	private static final String REPORT_DIRECTORY = "src/generated_reports";
	

	public HMI(){}
	
	public int mainMenu(){
		
		
		String welcomeMsg = (
		
		"""
		==== Welcome to the report generator ! ====
		Please make a choice:
		1. generate a report
		2. read a report
		3. quit the program
		"""
		);
		
		choice = intInputHandler(welcomeMsg);
		return choice;
	}
	
	
	public List<SaleFile> generateReportMenu(){
		
		int saleFileAmount;
		
		do{
				saleFileAmount = intInputHandler("How much files do you want to use to make the report (you can use 4 files maximum) ? ");
				
		}while(saleFileAmount > 4 || saleFileAmount < 1);
			
			
		return filesMakerMenu(saleFileAmount);
		
	}
	
	private List<SaleFile> filesMakerMenu(int saleFileAmount){
		
		List<SaleFile> saleFileList = new ArrayList<>();
		
		for(int i = 1; i<=saleFileAmount;i++){
			SaleFile newSaleFile = new SaleFile();
			
			System.out.println("file number " + i + ": ");
			int salesAmount = intInputHandler("How much sales for this file ?");
			
			for(int j = 1 ; j<= salesAmount;j++){				
				System.out.println("Sale number " + j + ": ");
				
				String name = stringInputHandler("Name of the product ?");
				
				double price = doubleInputHandler("Price per unit (in euros)?");
				
				double quantity = doubleInputHandler("Sold quantity ?");
				
				LocalDate date = dateInputHandler("The date of the sale (dd/MM/yyyy format) ?");
				
				Sale newSale = new Sale (name, quantity, price, date);
				newSaleFile.getSaleList().add(newSale);
			}
				
				saleFileList.add(newSaleFile);	

		}
			return saleFileList;
		
	}
	
	public void readReportMenu(){
		
		File directory = new File(REPORT_DIRECTORY);
		File[] allReportFiles = directory.listFiles();
		File selectedFile;
		
		
		if(allReportFiles != null && allReportFiles.length>0){
			
			Arrays.stream(allReportFiles).forEach(file -> System.out.println(file.getName()));
			int choice = intInputHandler("Which file do you want to read (select the file number please )?");
			
			selectedFile = Arrays.stream(allReportFiles)
				.filter(file -> file.getName().matches("^report_" + choice + "\\..*")) 
				.findFirst()
				.orElse(null);
				
			if(selectedFile != null){
				IoUtil.readBinaryFile(selectedFile.getName());
				IoUtil.readTextFile(selectedFile.getName());
			} else {
				System.out.println("There is no reports yet");
			}	
		
		} else {
			System.out.println("There is no reports yet");
		}
		
	}
	
	public void quitProgramMenu(){
		System.out.println("bye !");
	}
	

	private int intInputHandler(String msg){
		while(true){
		System.out.println(msg);
		
			try{
				int input = scanner.nextInt();
				scanner.nextLine();
				return input;
			}catch(InputMismatchException ex){
				System.out.println("ouch, the input must be of type number!");
				scanner.nextLine();
			}
				}
	}
	
	private String stringInputHandler(String msg){
		while(true){
			System.out.println(msg);

			try{
				String input = scanner.nextLine();
				return input;
			}catch(InputMismatchException ex){
				System.out.println("ouch, the input must be of type word!");
				scanner.nextLine();
			}
		}
	}
	
	private double doubleInputHandler(String msg) {
		while (true) {
			System.out.println(msg);
		
			try {
				double input = scanner.nextDouble();
				scanner.nextLine(); 
				return input;
			
			} catch (InputMismatchException ex) {
				System.out.println("ouch, the input must be of type number!");
				scanner.nextLine(); 
			}
		}
    }
	
	
	private LocalDate dateInputHandler(String msg){
		while(true){
			System.out.println(msg);

			try{
				String input = scanner.nextLine();
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate dateLD = LocalDate.parse(input, dtf);
				return dateLD;
			}catch(DateTimeParseException ex){
				System.out.println("ouch, the input must be of type date dd/MM/yyyy! Example : 10/02/1994");
			}
		}
	}
}
