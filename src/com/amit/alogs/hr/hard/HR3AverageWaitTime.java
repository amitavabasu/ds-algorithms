package com.amit.alogs.hr.hard;

import java.io.*;
import java.util.*;

/*
 * Find average wait time in pizza store
 */

public class HR3AverageWaitTime {
	
	/*
	 * Task is the class which holds two important data of a customer, arrival time and service time
	 */
	public static class Task{
		public int arrivalTime;
		public int serviceTime;
		public Task(int a, int b){
			this.arrivalTime = a;
			this.serviceTime = b;
		}
	}
	
	/*
	 * This is a comparator which compare two tasks by arrival time returning first arrival first 
	 */
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

	/*
	 * This is a comparator which compare two tasks by service time returning lowest service time first 
	 */
    public static class ServiceTimeComparator implements Comparator<Task>{
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
     * Minimum average calculation
     */
    static long minimumAverage(int[][] customers) {
    	int N = customers.length;
    	List<Task> tasks = new ArrayList<>();
    	for(int i=0; i<N; i++){//<-- create & add tasks in an array list
    		Task t = new Task(customers[i][0],customers[i][1]);
    		tasks.add(t);
    	}
    	Collections.sort(tasks,new ArrivalTimeComparator()); //<-- sort the array list by arrival time
    	long currentTime = 0;
    	long serviceTimeForThisTask = 0;
    	long waitTimeDueToPreviousTasks = 0;
    	long totalWaitTime = 0;
    	long cumulativeWaitTime = 0;
    	boolean done = false;
    	int i = 0;//<-- index of the current task we are processing
    	PriorityQueue<Task> queue = new PriorityQueue<>(new ServiceTimeComparator());//Use a priority queue (heap) to store the tasks which arrives when one task is going on. This is so that we can pickup the task which needs least service time
    	Task t = tasks.get(i);//<-- start with first arrived task
    	serviceTimeForThisTask = t.serviceTime;
    	totalWaitTime = serviceTimeForThisTask+waitTimeDueToPreviousTasks;//<-- calculate total time
    	currentTime = t.arrivalTime+totalWaitTime;//<-- this is the time when this task will finish
    	cumulativeWaitTime = cumulativeWaitTime+totalWaitTime; 
    	//System.out.println("----------");
    	while(!done){
    		//System.out.println(t.arrivalTime+"  "+t.serviceTime+"  "+currentTime+"  "+serviceTimeForThisTask+"  "+totalWaitTime+" "+cumulativeWaitTime);
    		while(i+1<tasks.size() && tasks.get(i+1).arrivalTime <= currentTime){//<-- take all tasks arriving while the current task finishes
    			queue.offer(tasks.get(i+1));//<-- put them in queue
    			i++;//<-- increment current task index
    		}
    		if(queue.isEmpty()){//<-- if no tasks in queue
    			if(i+1>=tasks.size()){//<-- check if any more tasks needs to be processed
    				done = true;//<-- if not set done as true
    			}else{//<-- otherwise at this point queue is empty but have more task to process
    				t = tasks.get(i+1);//<-- pickup the next task
    				serviceTimeForThisTask = t.serviceTime;
    				waitTimeDueToPreviousTasks = 0;
    				totalWaitTime = serviceTimeForThisTask + waitTimeDueToPreviousTasks;
    				currentTime = t.arrivalTime+serviceTimeForThisTask; //<-- calculate time when this task will finish
    				cumulativeWaitTime = cumulativeWaitTime+totalWaitTime;
    				i++;//<-- increment process task count
    			}
    		}else{//<-- when queue is not empty
    			t = queue.remove();//<-- remove the next task in queue 
    			serviceTimeForThisTask = t.serviceTime;
    			waitTimeDueToPreviousTasks = currentTime - t.arrivalTime;
    			totalWaitTime = serviceTimeForThisTask + waitTimeDueToPreviousTasks;//<-- calculate total wait time
    			currentTime = currentTime+serviceTimeForThisTask;
    			cumulativeWaitTime = cumulativeWaitTime+totalWaitTime;//<-- calculate cumulative wait time as well
    		}
    	}
    	//System.out.println(cumulativeWaitTime);
    	//System.out.println(cumulativeWaitTime/N);
    	return  ((cumulativeWaitTime/N));//<-- finally return average wait time
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

        scanner.close();
    }
}

