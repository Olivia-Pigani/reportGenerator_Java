import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ExecutionException;


public class Main {
	
	private static HMI hmi = new HMI();
	private static ReportUtil reportUtil = new ReportUtil();
	
	public static void main(String... args){
		
		start();
	}
	
	private static void start(){
		int choice = hmi.mainMenu();		
		switch(choice){
			case 1 -> generateReport();
			case 2 -> readReport();
			case 3 -> quitProgram();
			
		}
	}
	
	private static void generateReport(){
		
		List<SaleFile> saleFileList = hmi.generateReportMenu();	
		
		CountDownLatch latch = new CountDownLatch(1); //for t1
		CyclicBarrier barrier = new CyclicBarrier(2, () -> System.out.println("the report is ready!"));// for t2 and t3
		
		try(ExecutorService executor = Executors.newFixedThreadPool(3)){
			
			Future<Report> reportFuture = executor.submit(new ReportCallable(latch,saleFileList));
			Report report = reportFuture.get();
			
			latch.await(); //t2 and t3 wait until t1 has finished it's task.
			
			for(int i = 0; i < 2; i++){
				executor.submit(new WriteRunnable(report, barrier));
			}
			
		}catch(InterruptedException |  ExecutionException ex){
			ex.printStackTrace();
		}
			
		start();
	}
	
	private static void readReport(){
		hmi.readReportMenu();
		start();
	}
	
	private static void quitProgram(){
		hmi.quitProgramMenu();
		System.exit(0);
	}
	
}