import java.time.LocalDate;
import java.util.Objects;

public class SaleFile {

    private long id;

    private static long idIncrementor = 0;

    private String productName;

    private double quantity;

    private double pricePerUnity;

    private LocalDate date;
	
	public SaleFile(){
		this.id = ++idIncrementor;
	}


    public SaleFile(String productName, double quantity, double pricePerUnity, LocalDate date) {
        this.id = ++idIncrementor;
        this.productName = Objects.requireNonNull(productName, "product name can not be null");
        this.quantity = quantity;
        this.pricePerUnity = pricePerUnity;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPricePerUnity() {
        return pricePerUnity;
    }

    public void setPricePerUnity(double pricePerUnity) {
        this.pricePerUnity = pricePerUnity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
