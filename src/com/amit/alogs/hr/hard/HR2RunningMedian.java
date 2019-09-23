package com.amit.alogs.hr.hard;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class HR2RunningMedian {
	/* This is a wonderful problem & solution 
	 * Time Complexity O(n) and Space complexity O(1)
	 * */ 
	public double[] runningMedian(int[] a) {
		if(a==null){
			return null;
		}
		double[] result = new double[a.length];
		if(a.length==1){//<-- if the array has only one element
			result[0] = a[0];
			return result;
		}
		if(a.length==2){//If the array has only two element
			result[0] = a[0];
			result[1] = Double.valueOf((Double.valueOf(a[0])+Double.valueOf(a[1]))/2);
			return result;
		}
		//Calculate and store first two running median for 3rd and onwords
		result[0] = a[0];
		result[1] = Double.valueOf((Double.valueOf(a[0])+Double.valueOf(a[1]))/2);
		double lastMedian = result[1];//<-- start the value of second median
		PriorityQueue<Integer> heap1 = new PriorityQueue<>(Collections.reverseOrder()); //<-- maximum on top
		PriorityQueue<Integer> heap2 = new PriorityQueue<>();//<-- minimum on top
		if(a[0]<a[1]){//<-- offer first two items in appropriate heap
			heap1.offer(a[0]);
			heap2.offer(a[1]);
		}else{
			heap1.offer(a[1]);
			heap2.offer(a[0]);
		}
		for(int i=2; i<a.length; i++){//<-- from third element onwards
			if(a[i]<lastMedian){//<-- check with current median & push it to appropriate heap
				heap1.offer(a[i]);
			}else{
				heap2.offer(a[i]);
			}
			if(heap1.size()-heap2.size()>1){//Keep the heap size balanced, no heap should contains more than one extra element
				heap2.offer(heap1.poll());
			}else if(heap1.size()-heap2.size()<-1){
				heap1.offer(heap2.poll());
			}
			if(heap1.size()==heap2.size()){//<-- if both heap size same 
				lastMedian = result[i] = Double.valueOf((Double.valueOf(heap1.peek())+Double.valueOf(heap2.peek()))/2.00d);
			}else if(heap1.size()>heap2.size()){//<-- if heap1 have larger size
				lastMedian = result[i] = Double.valueOf(heap1.peek());
			}else{//<-- if heap2 is larger in size 
				lastMedian = result[i] = Double.valueOf(heap2.peek());
			}
		}
		return result;
    }
	
	public static void main(String[] args){
		int[] a = {12,4,5,3,8,7};
		HR2RunningMedian obj = new HR2RunningMedian();
		System.out.println(Arrays.toString(obj.runningMedian(a)));
	}
}
