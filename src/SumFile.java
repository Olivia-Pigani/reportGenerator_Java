import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class SumFile {

    private long id;

    private static long idIncrementor = 0;

    private double totalSaleAmount;

    private LocalDate startRange;

    private LocalDate endRange;

    private List<File> fileList = new ArrayList<>();
	
	public SumFile(){}

    public SumFile(List<File> fileList) {
        this.fileList = fileList;
    }

    public long getId() {
        return id;
    }

    public List<File> getFileList() {
        return fileList;
    }

    public void setFileList(List<File> fileList) {
        this.fileList = fileList;
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
