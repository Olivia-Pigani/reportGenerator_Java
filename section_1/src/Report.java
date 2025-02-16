import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class Report {

    private long id;

    private static long idIncrementor = 0;

    private double totalSaleAmount;

    private LocalDate startRange;

    private LocalDate endRange;

    private List<SaleFile> saleFileList = new ArrayList<>();
	
	public Report(){
		this.id = ++idIncrementor;
	}

    public Report(List<SaleFile> saleFileList) {
		this.id = ++idIncrementor;
        this.saleFileList = saleFileList;
    }

    public long getId() {
        return id;
    }

    public List<SaleFile> getSaleFileList() {
        return saleFileList;
    }

    public void setSaleFileList(List<SaleFile> saleFileList) {
        this.saleFileList = saleFileList;
    }

    public double getTotalSaleAmount() {
        return totalSaleAmount;
    }

    public void setTotalSaleAmount(double totalSaleAmount) {
        this.totalSaleAmount = totalSaleAmount;
    }

    public LocalDate getStartRange() {
        return startRange;
    }

    public void setStartRange(LocalDate startRange) {
        this.startRange = startRange;
    }

    public LocalDate getEndRange() {
        return endRange;
    }

    public void setEndRange(LocalDate endRange) {
        this.endRange = endRange;
    }
}
