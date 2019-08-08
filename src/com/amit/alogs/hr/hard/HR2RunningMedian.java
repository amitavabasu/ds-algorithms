package com.amit.alogs.hr.hard;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
		if(a.length==1){
			result[0] = a[0];
			return result;
		}
		if(a.length==2){
			result[0] = a[0];
			result[1] = new Double((Double.valueOf(a[0])+Double.valueOf(a[1]))/2);
			return result;
		}
		result[0] = a[0];
		result[1] = new Double((Double.valueOf(a[0])+Double.valueOf(a[1]))/2);
		double lastMedian = result[1];
		PriorityQueue<Integer> heap1 = new PriorityQueue<>(Collections.reverseOrder()); //<-- maximum on top
		PriorityQueue<Integer> heap2 = new PriorityQueue<>();//<-- minimum on top
		if(a[0]<a[1]){
			heap1.offer(a[0]);
			heap2.offer(a[1]);
		}else{
			heap1.offer(a[1]);
			heap2.offer(a[0]);
		}
		for(int i=2; i<a.length; i++){
			if(a[i]<lastMedian){
				heap1.offer(a[i]);
			}else{
				heap2.offer(a[i]);
			}
			if(heap1.size()-heap2.size()>1){
				heap2.offer(heap1.poll());
			}else if(heap1.size()-heap2.size()<-1){
				heap1.offer(heap2.poll());
			}
			if(heap1.size()==heap2.size()){
				lastMedian = result[i] = new Double((Double.valueOf(heap1.peek())+Double.valueOf(heap2.peek()))/2.00d);
			}else if(heap1.size()>heap2.size()){
				lastMedian = result[i] = new Double(heap1.peek());
			}else{
				lastMedian = result[i] = new Double(heap2.peek());
			}
		}
		return result;
    }
	
    static int equalStacks(int[] h1, int[] h2, int[] h3) {
        /*
         * Write your code here.
         */
        int lastSameHeight = 0;
        if(h1==null || h1.length==0 || h2==null || h2.length==0 || h3==null || h3.length==0)
        return lastSameHeight;
        int i1 = h1.length-1;
        int i2 = h2.length-1;
        int i3 = h3.length-1;
        boolean done = false;
        int H1 = h1[i1];
        int H2 = h2[i2];
        int H3 = h3[i3];
        int nh1 = H1;
        int nh2 = H2;
        int nh3 = H3;
        while(!done){
            if(H1==H2 && H2==H3 && H1==H3){
            	lastSameHeight = H1;
            	if(i1>0) {
            		i1--;
            		nh1 = H1+h1[i1];
            	}else {
            		done = true;
            	}
            	if(i2>0) {
            		i2--;
            		nh2 = H2+h2[i2];
            	}else {
            		done = true;
            	}
            	if(i3>0) {
            		i3--;
            		nh3 = H3+h3[i3];
            	}else {
            		done = true;
            	}
            }else{
            	if(H1<=H2 && H1<=H3) {
                	if(i1>0) {
                		i1--;
                		nh1 = H1+h1[i1];
                	}else {
                		done = true;
                	}
            	}
            	if(H2<=H1 && H2<=H3) {
                	if(i2>0) {
                		i2--;
                		nh2 = H2+h2[i2];
                	}else {
                		done = true;
                	}
            	}
            	if(H3<=H2 && H3<=H1) {
                	if(i3>0) {
                		i3--;
                		nh3 = H3+h3[i3];
                	}else {
                		done = true;
                	}
            	}
            }
            H1 = nh1;
            H2 = nh2;
            H3 = nh3;
        }
        return lastSameHeight;
    }

	
	public static void main(String[] args){
//		int[] a = {12,4,5,3,8,7};
//		HR2RunningMedian obj = new HR2RunningMedian();
//		System.out.println(Arrays.toString(obj.runningMedian(a)));
		int[] h1 = {3,2,1,1,1};
		int[] h2 = {4,3,2};
		int[] h3 = {1,1,4,1};
		System.out.println(equalStacks(h1,h2,h3));
	}
}
