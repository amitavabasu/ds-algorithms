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

	
    static long minTime(long[] machines, long goal) {
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
    
    static long minTime3(long[] machines, long goal) {
    	long max = machines[0];
    	for(long m:machines){
    		if(m>max){
    			max = m;
    		}
    	}
    	long minDay = 0;
    	long maxDay = goal*max;
    	long result = -1;
    	while(minDay < maxDay){
    		long searchDay = (minDay+maxDay) / 2;
    		long unitProducedOnAndBeforeSearchDay = 0;
    		for(long m:machines){
    			unitProducedOnAndBeforeSearchDay += searchDay / m;
    		}
    		if(unitProducedOnAndBeforeSearchDay < goal){
    			minDay = searchDay+1;
    		}else{
    			result = searchDay;
    			maxDay = searchDay;
    		}
    	}
    	return result;
    }
    
    static long minTime2(long[] machines, long goal) {
    	Map<Long,Long> map = new HashMap<>();
    	for(long m:machines){
    		map.put(m, map.getOrDefault(m, 0l)+1);
    	}
    	Set<Long> keys = map.keySet();
    	List<Long> keysList =  new ArrayList<Long>(keys);
    	Collections.sort(keysList);
    	long minGap = Long.MAX_VALUE;
    	for(int i=1; i<keysList.size(); i++){
    		if(minGap<keysList.get(i-1)-keysList.get(i));
    			minGap = keysList.get(i)-keysList.get(i-1);
    	}
    	long days = machines[0];
    	long produced = 0;
    	while(produced<goal){
    		for(long key:keysList){
    			if(days>=key){
    				if(days%key==0)
    					produced += map.get(key);
    			}
    		}
    		days += minGap;
    	}
    	return days-minGap;
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
		
//		String s = "07:05:45PM";
//		System.out.println(timeConversion(s));
		
	}
}
