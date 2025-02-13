import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.stream.*;
import java.util.Comparator;

public class ReportUtil{

	public static final String REPORT_ROOT_FILEPATH = "src/generated_reports/";

	public ReportUtil(){}
	
	public Report reportGenerator(List<SaleFile> saleFileList){
		
		Report newReport = new Report();
				
		double totalSaleAmount = saleFileList.stream()
			.flatMap(file -> file.getSaleList().stream())
			.mapToDouble(Sale::getPricePerUnity)
			.sum();
		
		LocalDate startDate = saleFileList.stream()
			.flatMap(file -> file.getSaleList().stream())
			.map(Sale::getDate)
			.min(Comparator.naturalOrder())
			.orElse(LocalDate.now());
			
		LocalDate lastDate = saleFileList.stream()
			.flatMap(file -> file.getSaleList().stream())
			.map(Sale::getDate)
			.max(Comparator.naturalOrder())
			.orElse(LocalDate.now());
			
			newReport.setSaleFileList(saleFileList);
			newReport.setTotalSaleAmount(totalSaleAmount);
			newReport.setStartRange(startDate);
			newReport.setEndRange(lastDate);
		
		
			return newReport;
		
	}
	
	public String writeDataInSkeleton(Report report){
	
		StringBuilder sb = new StringBuilder();
		
		String header = getSumFileHeader(report);
		sb.append(header);
		sb.append("\n"); //allow to make a link between header and other content to make sur that text files are correctly generated.

		
		String salesContent = report.getSaleFileList().stream()
			.flatMap(file -> file.getSaleList().stream())
			.map(this::getSumFileSaleSkeleton)
			.collect(Collectors.joining());
		
			
		sb.append(salesContent);
		
		return sb.toString();

	
	}
	
	
	private String getSumFileHeader(Report report){
	
		return String.format("""	
		Report: %d
		
		total sales: %.2f 
		date range: %s to %s 
		-----------------------------------
		-----------------------------------
		""", report.getId(), report.getTotalSaleAmount(),report.getStartRange().toString(),report.getEndRange().toString());
	
	}
	
	private String getSumFileSaleSkeleton(Sale sale){

		return String.format("""
		
		-----------------------------------
		Sale id: %d
		
		
		name | quantity | date | price
		------------------------------
		%s	 |  %.2f 	|  %s  | %.2f 
		
		-----------------------------------
		""", sale.getId(),sale.getProductName(),sale.getQuantity(),sale.getDate().toString(),sale.getPricePerUnity());


	}
	
}