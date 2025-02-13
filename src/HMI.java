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
	private static ChosenLanguage chosenLanguage = ChosenLanguage.getInstance();


	public HMI(){}
	
	public int mainMenu(){
		
		String welcomeMsg = chosenLanguage.getMsg("welcome");
		
		choice = intInputHandler(welcomeMsg);
		return choice;
	}
	
	
	public List<SaleFile> generateReportMenu(){
		
		int saleFileAmount;
		
		do{
				saleFileAmount = intInputHandler(chosenLanguage.getMsg("files_sale_amount"));
				
		}while(saleFileAmount > 4 || saleFileAmount < 1);
			
			
		return filesMakerMenu(saleFileAmount);
		
	}
	
	private List<SaleFile> filesMakerMenu(int saleFileAmount){
		
		List<SaleFile> saleFileList = new ArrayList<>();
		
		for(int i = 1; i<=saleFileAmount;i++){
			SaleFile newSaleFile = new SaleFile();
			
			System.out.println(chosenLanguage.getMsg("file_number") + i + ": ");
			int salesAmount = intInputHandler(chosenLanguage.getMsg("sales_amount"));
			
			for(int j = 1 ; j<= salesAmount;j++){				
				System.out.println(chosenLanguage.getMsg("sale_number") + j + ": ");
				
				String name = stringInputHandler(chosenLanguage.getMsg("name"));
				
				double price = doubleInputHandler(chosenLanguage.getMsg("price"));
				
				double quantity = doubleInputHandler(chosenLanguage.getMsg("quantity"));
				
				LocalDate date = dateInputHandler(chosenLanguage.getMsg("date"));
				
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
			int choice = intInputHandler(chosenLanguage.getMsg("file_to_read"));
			
			selectedFile = Arrays.stream(allReportFiles)
				.filter(file -> file.getName().matches("^report_" + choice + "\\..*")) 
				.findFirst()
				.orElse(null);
				
			if(selectedFile != null){
				IoUtil.readBinaryFile(selectedFile.getName());
				IoUtil.readTextFile(selectedFile.getName());
			} else {
				System.out.println(chosenLanguage.getMsg("no_file_yet"));
			}	
		
		} else {
			System.out.println(chosenLanguage.getMsg("no_file_yet"));
		}
		
	}
	
	public int languageSettingMenu(){
		return intInputHandler("1.Français \n 2.English");
	}
	
	public void quitProgramMenu(){
		System.out.println(chosenLanguage.getMsg("bye"));
	}
	

	private int intInputHandler(String msg){
		while(true){
		System.out.println(msg);
		
			try{
				int input = scanner.nextInt();
				scanner.nextLine();
				return input;
			}catch(InputMismatchException ex){
				System.out.println(chosenLanguage.getMsg("int_or_double_input_handler"));
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
				System.out.println(chosenLanguage.getMsg("int_string_handler"));
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
				System.out.println(chosenLanguage.getMsg("int_or_double_input_handler"));
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
				System.out.println(chosenLanguage.getMsg("int_date_handler"));
			}
		}
	}
}
