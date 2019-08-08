package com.amit.alogs.hr.hard;


import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class HR3AverageWaitTime {

	public static class Task{
		public int arrivalTime;
		public int serviceTime;
		public Task(int a, int b){
			this.arrivalTime = a;
			this.serviceTime = b;
		}
	}
	
    
    public static class ArrivalTimeComparator implements Comparator<Task>{
		@Override
		public int compare(Task o1, Task o2) {
			if(o1.arrivalTime == o2.arrivalTime){
				return 0;
			}else if(o1.arrivalTime > o2.arrivalTime){
				return 1;
			}else{
				return -1;
			}
		}
    }
    
    public static class ServiceComparator implements Comparator<Task>{
		@Override
		public int compare(Task o1, Task o2) {
			if(o1.serviceTime == o2.serviceTime){
				return 0;
			}else if(o1.serviceTime > o2.serviceTime){
				return 1;
			}else{
				return -1;
			}
				
		}
    }
    
	
	/*
     * Complete the minimumAverage function below.
     */
    static long minimumAverage(int[][] customers) {
        /*
         * Write your code here.
         */
    	int N = customers.length;
    	List<Task> tasks = new ArrayList<>();
    	for(int i=0; i<N; i++){
    		Task t = new Task(customers[i][0],customers[i][1]);
    		tasks.add(t);
    	}
    	Collections.sort(tasks,new ArrivalTimeComparator());
    	long currentTime = 0;
    	long serviceTimeForThisTask = 0;
    	long waitTimeDueToPreviousTasks = 0;
    	long totalWaitTime = 0;
    	long cumulativeWaitTime = 0;
    	boolean done = false;
    	int i = 0;
    	PriorityQueue<Task> queue = new PriorityQueue<>(new ServiceComparator());
    	Task t = tasks.get(i);
    	serviceTimeForThisTask = t.serviceTime;
    	totalWaitTime = serviceTimeForThisTask+waitTimeDueToPreviousTasks;
    	currentTime = t.arrivalTime+totalWaitTime;
    	cumulativeWaitTime = cumulativeWaitTime+totalWaitTime; 
    	//System.out.println("----------");
    	while(!done){
    		//System.out.println(t.arrivalTime+"  "+t.serviceTime+"  "+currentTime+"  "+serviceTimeForThisTask+"  "+totalWaitTime+" "+cumulativeWaitTime);
    		while(i+1<tasks.size() && tasks.get(i+1).arrivalTime <= currentTime){
    			queue.offer(tasks.get(i+1));
    			i++;
    		}
    		if(queue.isEmpty()){
    			if(i+1>=tasks.size()){
    				done = true;
    			}else{
    				t = tasks.get(i+1);
    				serviceTimeForThisTask = t.serviceTime;
    				waitTimeDueToPreviousTasks = 0;
    				totalWaitTime = serviceTimeForThisTask + waitTimeDueToPreviousTasks;
    				currentTime = t.arrivalTime+serviceTimeForThisTask;
    				cumulativeWaitTime = cumulativeWaitTime+totalWaitTime;
    				i++;
    			}
    		}else{
    			t = queue.remove();
    			serviceTimeForThisTask = t.serviceTime;
    			waitTimeDueToPreviousTasks = currentTime - t.arrivalTime;
    			totalWaitTime = serviceTimeForThisTask + waitTimeDueToPreviousTasks;
    			currentTime = currentTime+serviceTimeForThisTask;
    			cumulativeWaitTime = cumulativeWaitTime+totalWaitTime;
    		}
    	}
    	//System.out.println(cumulativeWaitTime);
    	//System.out.println(cumulativeWaitTime/N);
    	return  ((cumulativeWaitTime/N));
    }

    //private static final Scanner scanner = new Scanner(System.in);
    //8485548331
    //-106545300
    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        File file = new File("E:\\MyOwnProject\\data\\test6.txt");
        Scanner scanner = new Scanner(file);

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        int[][] customers = new int[n][2];

        for (int customersRowItr = 0; customersRowItr < n; customersRowItr++) {
            String[] customersRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

            for (int customersColumnItr = 0; customersColumnItr < 2; customersColumnItr++) {
                int customersItem = Integer.parseInt(customersRowItems[customersColumnItr]);
                customers[customersRowItr][customersColumnItr] = customersItem;
            }
        }

        long result = minimumAverage(customers);

        System.out.println(String.valueOf(result));
        //bufferedWriter.newLine();

        //bufferedWriter.close();

        //scanner.close();
    }
}

