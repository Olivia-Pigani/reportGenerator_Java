import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;


public class ReportCallable implements Callable<Report>{
	
	private CountDownLatch latch; 
	private ReportUtil reportUtil;
	private List<File> fileList;
	
	ReportCallable(CountDownLatch latch, List<File> fileList){
		this.latch=latch;
		this.fileList=fileList;
		this.reportUtil = new ReportUtil();
	}
		
	@Override
	public Report call(){
		Report report = new Report();
		try{
			System.out.println(Thread.currentThread().getName() + " is working on report making ...");
			Thread.sleep(1500); //fake processing time, for demo purpose
			report = reportUtil.reportGenerator(this.fileList);
			
			latch.countDown();
			
			System.out.println(Thread.currentThread().getName() + " has finished working on report making!");
		}catch (InterruptedException e) {
                e.printStackTrace();
            }
			return report;
	}
	
}