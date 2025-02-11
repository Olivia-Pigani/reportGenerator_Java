import java.util.List;
import java.util.ArrayList;

public class File {
	
	private long id;

    private static long idIncrementor = 0;
	
	private List<Sale> saleList = new ArrayList<>();
	
	public File(){
		this.id=++idIncrementor;
	}
	
	public File(List<Sale> saleList){
		this.id=++idIncrementor;
		this.saleList = saleList;	
	}
	
	public long getId(){
		return id;
	}
	
	public List<Sale> getSaleList(){
		return saleList;
	}
	
	public void setSaleList(List<Sale> saleList){
		this.saleList = saleList;
	}
	
	
	
}