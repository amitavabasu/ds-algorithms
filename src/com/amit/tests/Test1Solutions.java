package com.amit.tests;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
		    public String getExcryptedName(String name) throws IllegalArgumentException{
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
	}
}
