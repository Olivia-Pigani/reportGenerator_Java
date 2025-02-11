import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SumFile {

    private long id;

    private static long idIncrementor = 0;

    private double totalSaleAmount;

    private LocalDate startRange;

    private LocalDate endRange;

    private List<Sale> saleList = new ArrayList<>();

    public SumFile(List<Sale> saleList) {
        this.saleList = saleList;
    }

    public long getId() {
        return id;
    }

    public List<Sale> getSaleList() {
        return saleList;
    }

    public void setSaleList(List<Sale> saleList) {
        this.saleList = saleList;
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
