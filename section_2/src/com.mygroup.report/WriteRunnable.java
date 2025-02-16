package com.mygroup.report;

import java.io.File;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.BrokenBarrierException;

import com.mygroup.ioutil.IoUtil;

public class WriteRunnable implements Runnable{
	
	private static final ReentrantLock txtLock = new ReentrantLock();
	private static final ReentrantLock binLock = new ReentrantLock();
	private static final ReentrantLock directoryLock = new ReentrantLock();
	private CyclicBarrier barrier;
	private Report report;
	private ReportUtil reportUtil;
	private String directoryTargetName;

	public WriteRunnable(String directoryTargetName, Report report, CyclicBarrier barrier){
		this.directoryTargetName = directoryTargetName;
		this.report = report;
		this.barrier=barrier;
		this.reportUtil=new ReportUtil();
	}
	
	@Override
	public void run(){		
	
		 makeNewDirectory(this.directoryTargetName);
	
		 boolean isThreadDoATask = false;

        while (!isThreadDoATask) {
            if (txtLock.tryLock()) {
                try {
                    System.out.println(Thread.currentThread().getName() + " is working on text file ...");
                    Thread.sleep(1000); //fake processing time, for demo 
                    String reportStr = reportUtil.writeDataInSkeleton(this.report);
                    IoUtil.makeTextFile(ReportUtil.REPORT_ROOT_FILEPATH,reportStr, this.report.getId());
                    isThreadDoATask = true; 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    txtLock.unlock(); 
                }
            } 
            else if (binLock.tryLock()) {
                try {
                    System.out.println(Thread.currentThread().getName() + " is working on binary file ...");
                    Thread.sleep(1000); //fake processing time, for demo 
                    String reportStr = reportUtil.writeDataInSkeleton(this.report);
                    IoUtil.makeBinaryFile(ReportUtil.REPORT_ROOT_FILEPATH,reportStr, this.report.getId());
                    isThreadDoATask = true; 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    binLock.unlock(); 
                }
            }
        }

        try {
		System.out.println(Thread.currentThread().getName() + " is waiting at the barrier...");
        barrier.await();
        System.out.println(Thread.currentThread().getName() + " has passed the barrier!");        
		} catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
	}
	
	private void makeNewDirectory(String directoryName){
		    directoryLock.lock();
        try {
            File reportsDir = new File("generated_reports");
            if (!reportsDir.exists()) {
                reportsDir.mkdirs();
                System.out.println("Directory 'generated_reports' has been made.");
            }
        } finally {
            directoryLock.unlock();
        }
	}
}
