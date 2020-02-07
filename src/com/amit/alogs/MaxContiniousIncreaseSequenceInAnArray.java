package com.amit.alogs;

import java.util.LinkedList;
import java.util.Queue;

public class MaxContiniousIncreaseSequenceInAnArray {
	
    static Queue<Integer> queue = new LinkedList<>();
    static int seqCount = 1;
    
    static void countContSeq(int[] arr) {
    	for(int i=0; i<arr.length; i++) {
    		queue.add(i);
    	}
    	boolean done = false;
    	while(!done) {
    		System.out.println(queue.toString());
	    	Queue<Integer> newQueue = new LinkedList<>();
	    	boolean found = false;
	    	while(!queue.isEmpty()) {
	    		int i = queue.remove();
	    		for(int j=i+1; j<arr.length; j++) {
	    			if(arr[j]>arr[i]) {
	    				if(!newQueue.contains(j))
	    					newQueue.add(j);
	    				found = true;
	    			}
	    		}
	    	}
			if(found) {
				seqCount++;
				queue = newQueue;
			}else {
				done = true;
			}
    	}
    	System.out.println(seqCount);
    }
    
	public static void main(String[] s) {
		//int[] arr = {3, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
		int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
		countContSeq(arr);
	}

}
