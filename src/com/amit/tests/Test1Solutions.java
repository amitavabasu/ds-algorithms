package com.amit.tests;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Test1Solutions {

	 public static long waitingTime(List<Integer> tickets, int p) {
		    long waitTime = 0l;
		    long purchased = 0;
		    Queue<Integer> queue = new LinkedList<>();
		    for(Integer t:tickets){
		    	queue.offer(t);
		    }
		    int pos = p;
		    while(purchased < tickets.get(p)){
		    	int current = queue.remove();
		    	if(current-1>0){
		    		queue.offer(current-1);
		    	}
		    	if(pos == 0){
		    		purchased++;
		    		pos = queue.size()-1;
		    	}else{
		    		pos --;
		    	}
		    	waitTime++;
		    }
		    return waitTime;
	 }
	 
	 public static long waitingTime2(List<Integer> tickets, int p) {
		 	int numOfTicketstoBuy = tickets.get(p);
		    long waitTime = p+1;
		    if(numOfTicketstoBuy-1>0) {
		    	Queue<Integer> queue = new PriorityQueue<>();
		    	for(int i=0;i<tickets.size(); i++) {
		    		int t = tickets.get(i);
		    		if(t-1>0 && i!=p) {
		    			queue.offer(t-1);
		    		}
		    	}
		    	int purchased = 1;
		    	int currentLowest = 0;
		    	int previousLowest = 0;
		    	boolean done = false;
		    	while(!done) {
		    		if(queue.isEmpty()) {
		    			waitTime += numOfTicketstoBuy-purchased+1;
		    			done = true;
		    		}else {
		    			currentLowest = queue.remove()-previousLowest;
			    		purchased += currentLowest;
			    		if(purchased < numOfTicketstoBuy) {
			    			waitTime += currentLowest*(queue.size()+1)+currentLowest;
			    			previousLowest = currentLowest;
			    		}else if(purchased > numOfTicketstoBuy) {
			    			waitTime += (numOfTicketstoBuy-(purchased-currentLowest))*(queue.size()+1)+(numOfTicketstoBuy-(purchased-currentLowest));
			    			done = true;
			    		}else {
			    			waitTime += currentLowest*(queue.size()+1)+currentLowest;
			    			done = true;
			    		}
		    		}
		    	}
		    }
		    return waitTime;
	 }
	 
	 
	
	 class Validator {
		    public boolean validate(String name) {
		        for (int i = 0; i < name.length(); i++) {
		            char ch = name.charAt(i);
		            
		            if (ch != ' ' && !(Character.isLowerCase(ch) || Character.isUpperCase(ch))) {
		                return false;
		            }
		        }
		        
		        return true;
		    }
		}	 
	 
	 public class Encrypter{
		    public String getEncryptedName(String name) throws IllegalArgumentException{
		    Validator validator = new Validator();
		    	if(validator.validate(name)){
		    		return new StringBuilder(name.toLowerCase()).reverse().toString();
		    	}else{
		    		throw new IllegalArgumentException("Try again with valid name");
		    	}
		    }
		}
	 
	 
	 
	
	
	public static void main(String[] args){
		List<Integer> tickets = new ArrayList<>();
		tickets.add(2);
		tickets.add(6);
		tickets.add(3);
		tickets.add(4);
		tickets.add(5);
		int p = 2;
		
		System.out.println(waitingTime(tickets,p));
		System.out.println("--finish--");
		System.out.println(waitingTime2(tickets,p));
		System.out.println("--finish--");
	}
}
