import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.stream.*;
import java.util.Comparator;

public class ReportUtil{

	public static final String REPORT_ROOT_FILEPATH = "src/generated_reports/report_";

	public ReportUtil(){}
	
	public Report reportGenerator(List<File> fileList){
		
		Report newReport = new Report();
				
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
			
			newReport.setFileList(fileList);
			newReport.setTotalSaleAmount(totalSaleAmount);
			newReport.setStartRange(startDate);
			newReport.setEndRange(lastDate);
		
		
			return newReport;
		
	}
	
	public String writeDataInSkeleton(Report report){
	
		StringBuilder sb = new StringBuilder();
		
		String header = getSumFileHeader(report);
		sb.append(header);

		
		String salesContent = report.getFileList().stream()
			.flatMap(file -> file.getSaleList().stream())
			.map(this::getSumFileSaleSkeleton)
			.collect(Collectors.joining());
		
			
		sb.append(salesContent);
		
		return sb.toString();

	
	}
	
	
	private String getSumFileHeader(Report report){
	
		return String.format("""	
		Report %d
		
		total sales: %.2f 
		date range: %s to %s 
		-----------------------------------
		-----------------------------------
		""", report.getId(), report.getTotalSaleAmount(),report.getStartRange().toString(),report.getEndRange().toString());
	
	}
	
	private String getSumFileSaleSkeleton(Sale sale){

		return String.format("""
		
		-----------------------------------
		Sale %d
		
		
		name | quantity | date | price
		------------------------------
		%s	 |  %.2f 	|  %s  | %.2f 
		
		-----------------------------------
		
		""", sale.getId(),sale.getProductName(),sale.getQuantity(),sale.getDate().toString(),sale.getPricePerUnity());


	}
	
}