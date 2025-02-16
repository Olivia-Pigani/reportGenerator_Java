package com.mygroup.main;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.mygroup.hmi.HMI;
import com.mygroup.report.ReportUtil;
import com.mygroup.report.ReportCallable;
import com.mygroup.report.WriteRunnable;
import com.mygroup.report.Report;
import com.mygroup.report.SaleFile;
import com.mygroup.language.ChosenLanguage;
import com.mygroup.language.Language;

public class Main {
	
	private static HMI hmi = new HMI();
	private static ReportUtil reportUtil = new ReportUtil();
	private static ChosenLanguage chosenLanguage = ChosenLanguage.getInstance();
	private static final String TARGET_DIRECTORY = "generated_reports";
	
	public static void main(String... args){

		start();
	}
	
	private static void start(){
		int choice = hmi.mainMenu();		
		switch(choice){
			case 1 -> generateReport();
			case 2 -> readReport();
			case 3 -> languageSetting();
			case 4 -> quitProgram();
			
		}
	}
	
	private static void generateReport(){
		
		List<SaleFile> saleFileList = hmi.generateReportMenu();	
		
		CountDownLatch latch = new CountDownLatch(1); //for t1
		CyclicBarrier barrier = new CyclicBarrier(2, () -> System.out.println("the report is ready!"));// for t2 and t3
		
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		try{		
			Future<Report> reportFuture = executor.submit(new ReportCallable(latch,saleFileList));
			Report report = reportFuture.get();
			
			latch.await(); //t2 and t3 wait until t1 has finished it's task.
			
			for(int i = 0; i < 2; i++){
				executor.submit(new WriteRunnable(TARGET_DIRECTORY, report, barrier));
			}
			
		}catch(InterruptedException |  ExecutionException ex){
			ex.printStackTrace();
		}finally{
			executor.shutdown();	
			executorWaiting(executor); //necessary for printing menu after writeRunnables work.
			start();
		}
			
		
	}
	
	private static void readReport(){
		hmi.readReportMenu();
		start();
	}
	
	private static void languageSetting(){
		int languageChoice = hmi.languageSettingMenu();
		
		if(languageChoice == 1){
		chosenLanguage.setGeneralLanguage(Language.FRENCH);
		}else if (languageChoice == 2) {
		chosenLanguage.setGeneralLanguage(Language.ENGLISH);
		}
		
		start();
	}
	
	private static void quitProgram(){
		hmi.quitProgramMenu();
		System.exit(0);
	}
	
	private static void executorWaiting(ExecutorService executor){
		try{
			boolean tasksFinished = executor.awaitTermination(2,TimeUnit.SECONDS);
			if(tasksFinished){
				System.out.println("All tasks completed within the timeout.");
			} else {
				System.out.println("Timeout occurred before all tasks finished.");
			}
		}catch (InterruptedException e) {
            System.out.println("Await termination interrupted.");
            Thread.currentThread().interrupt();
	}
	
}}