import java.util.ArrayList;
import java.util.List;

public class HMI {


	public static void main(String... args){

		List<SaleFile> saleFileList = new ArrayList<>();
		SaleFile sf1 = new SaleFile();
		SaleFile sf2 = new SaleFile();
		saleFileList.add(sf1);
		saleFileList.add(sf2);


		SumFile sm = new SumFile(saleFileList);

		System.out.println(sm);

	}




























}
