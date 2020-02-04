package com.amit.tests;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Test0Solutions {

	
    static long minTime(long[] machines, long goal) {//<-- simplies solution, increment days and check if the goal is reached or not
    	Arrays.sort(machines);
    	long days = machines[0];
    	long produced = 0;
    	while(produced<goal){
    		for(int i=0;i<machines.length; i++){
    			if(days>=machines[i]){
    				if(days%machines[i]==0)
    					produced += 1;
    			}else{
    				break;
    			}
    		}
    		days++;
    	}
    	return days-1;
    }
    
    static long minTime3(long[] machines, long goal) {//<-- this is the best solution, find minimum days set to 0, 
    												  //find max day and then do binary search to find the correct number of days when goal is met  
    	long max = machines[0];
    	for(long m:machines){
    		if(m>max){
    			max = m;//<-- find the max machine fist, so that it can be used to calculate maxDays
    		}
    	}
    	long minDay = 0;//<-- set minimum days to 0 
    	long maxDay = goal*max;//<-- calculate max days
    	long result = -1;//<-- initialize result
    	while(minDay < maxDay){//<-- repeat until manDay less than maxDay
    		long searchDay = (minDay+maxDay) / 2;//<-- find search day at the middle
    		long unitProducedOnAndBeforeSearchDay = 0;//<-- initialize how many will be produced on that day
    		for(long m:machines){//<-- for each machine
    			unitProducedOnAndBeforeSearchDay += searchDay / m;//<-- calculate how many it will produce on the search day
    		}
    		if(unitProducedOnAndBeforeSearchDay < goal){//<-- if total production less than goal
    			minDay = searchDay+1;//<-- set minDay to one day advance
    		}else{//<-- else when goal is met, this means we produced too many or exactly what is required 
    			result = searchDay;//<-- store the search day, in case we produced what exactly required
    			maxDay = searchDay;//<-- also set the maxDay to search day, because if we produced too many, wee need to continue the binary search until minDay remains less than maxDay 
    		}
    	}
    	return result;//<-- return result
    }
    
    static long minTime2(long[] machines, long goal) {//<-- alternate using O(n log n) [because of sorting] time & O(n) space complexity
    	Map<Long,Long> map = new HashMap<>();//<-- use a map
    	for(long m:machines){//<-- for all machines
    		map.put(m, map.getOrDefault(m, 0l)+1);//<-- put counter in the map
    	}
    	Set<Long> keys = map.keySet();//Get the key-set from the map
    	List<Long> keysList =  new ArrayList<Long>(keys);
    	Collections.sort(keysList);//Sort the key-set
    	long minGap = Long.MAX_VALUE;
    	for(int i=1; i<keysList.size(); i++){//<-- Iterate over the key-set to find the minGap between adjacent elements   
    		if(minGap<keysList.get(i-1)-keysList.get(i));
    			minGap = keysList.get(i)-keysList.get(i-1);
    	}
    	long days = machines[0];//<-- initialize number of days required to first element 
    	long produced = 0;//<-- initialized produced to 0
    	while(produced<goal){//<-- iterate until produced less than goal
    		for(long key:keysList){//<-- iterate over key-set
    			if(days>=key){//<-- if days grater than equal to key
    				if(days%key==0)//<-- if that key produces on that days
    					produced += map.get(key);//<-- increment produced by key count
    			}
    		}
    		days += minGap;//<-- increment days by the minimum gap, it could be one
    	}
    	return days-minGap;//<-- when produced reaches goal return days-minGap because minGap is added unnecessarily in the last iteration.
    }
    
    static String timeConversion(String s) {
    	DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("hh:mm:ssa");
    	DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    	return outputFormatter.format(inputFormatter.parse(s));
    }    
	
	public static void main(String[] args){
		long[] machines = {4, 5, 6};
		long goal = 12;
		System.out.println(minTime(machines,goal));	
		System.out.println("--finish--");
		System.out.println(minTime2(machines,goal));
		System.out.println("--finish--");
		System.out.println(minTime3(machines,goal));		
		System.out.println("--finish--");
		System.out.println(minTimeToAchiveGoal(machines,goal));		
		System.out.println("--finish--");
//		String s = "07:05:45PM";
//		System.out.println(timeConversion(s));
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	static long minTimeToAchiveGoal(long[] machines, long goal) {
		if(machines==null || machines.length==0) {
			return -1l;
		}
		if(goal<=0)
			return 0;
		long minDays = 0;
		long max = machines[0];
		for(long m:machines) {
			if(m>max) {
				max = m;
			}
		}
		long maxDays = goal*max;
		long result = 0l;
		while(maxDays>minDays) {
			long searchDay = (minDays+maxDays)/2;
			long totalUnitsGeneratedOnSearchDay = 0;
			for(long m:machines) {
				totalUnitsGeneratedOnSearchDay += searchDay/m;
			}
			if(totalUnitsGeneratedOnSearchDay < goal) {
				searchDay += 1;
				minDays = searchDay+1;
			}else {
				result = searchDay;
				maxDays = searchDay;
			}
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
