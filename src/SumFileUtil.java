import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.stream.*;
import java.util.Comparator;


public class SumFileUtil{

	public SumFileUtil(){}
	
	
	public SumFile sumFileGenerator(List<File> fileList){
		
		SumFile newSumFile = new SumFile();
				
		double totalSaleAmount = fileList.stream()
			.flatMap(file -> file.getSaleList().stream())
			.mapToDouble(Sale::getPricePerUnity)
			.sum();
		
		LocalDate startDate = fileList.stream()
			.flatMap(file -> file.getSaleList().stream())
			.map(Sale::getDate)
			.min(Comparator.naturalOrder())
			.orElse(LocalDate.now());
			
		LocalDate lastDate = fileList.stream()
			.flatMap(file -> file.getSaleList().stream())
			.map(Sale::getDate)
			.max(Comparator.naturalOrder())
			.orElse(LocalDate.now());
			
			newSumFile.setFileList(fileList);
			newSumFile.setTotalSaleAmount(totalSaleAmount);
			newSumFile.setStartRange(startDate);
			newSumFile.setEndRange(lastDate);
		
		
			return newSumFile;
		
	}
	
	public String getSumFileData(SumFile sumFile){
	
		StringBuilder sb = new StringBuilder();
		
		String header = getSumFileHeader(sumFile);
		sb.append(header);

		
		String salesContent = sumFile.getFileList().stream()
			.flatMap(file -> file.getSaleList().stream())
			.map(this::getSumFileSaleSkeleton)
			.collect(Collectors.joining());
		
			
		sb.append(salesContent);
		
		return sb.toString();

	
	}
	
	
	private String getSumFileHeader(SumFile sumFile){
	
	return String.format("""
		
		-----------------------------------
		-----------------------------------
		
		File summary %d
		
		total sales: %d 
		date range: %s to %s 
		
		-----------------------------------
		-----------------------------------
		
		""", sumFile.getId(), sumFile.getTotalSaleAmount(),sumFile.getStartRange(),sumFile.getEndRange());
	
	}
	
		private String getSumFileSaleSkeleton(Sale sale){

		return String.format("""
		
		-----------------------------------
		-----------------------------------
		
		Sale %d
		
		
		name | quantity | date | price
		------------------------------
		%s	 |  %d		|  %s  | %d
		
		
		-----------------------------------
		-----------------------------------
		
		""", sale.getId(),sale.getProductName(),sale.getQuantity(),sale.getPricePerUnity(),sale.getDate());


		}
	
}