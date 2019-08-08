package com.amit.alogs.hr.medium;

import java.util.Arrays;
import java.util.Stack;

public class HR11MinMaxRiddle {

    static long[] riddle(long[] arr) {
        // complete this function
    	if(arr==null||arr.length==0){
    		return null;
    	}
    	if(arr.length==1){
    		return arr;
    	}
    	long[][] min = new long[arr.length][arr.length];
    	long[] max = new long[arr.length];
    	min[0][0] = arr[0];
    	max[0] = arr[0];
    	for(int i=1;i<arr.length; i++){
    		long val = arr[i];
    		max[0] = Math.max(max[0], val);
    		min[0][i] = val;
    		for(int j=i; j>0; j--){
    			min[i-j+1][j-1] = Math.min(val, min[i-j][j-1]);
    			max[i-j+1] = Math.max(max[i-j+1], min[i-j+1][j-1]);
    		}
    	}
    	for(int i=0; i<arr.length; i++){
    		System.out.println(Arrays.toString(min[i]));
    	}
		System.out.println("--");
		//System.out.println(Arrays.toString(max));
    	return max;
    }
    
    static long[] riddle2(long[] arr) {
    	Stack<Integer> stack = new Stack<>();
    	int[] right = new int[arr.length];
    	int[] left = new int[arr.length];
    	for(int i=0; i<arr.length; i++){
    		right[i] = arr.length;
    		left[i] = -1;
    	}
    	for(int i=0; i<arr.length; i++){
    		while(!stack.empty() && arr[stack.peek()] >= arr[i]){
    			stack.pop();
    		}
    		if(!stack.empty())
    			left[i] = stack.peek();
    		stack.push(i);
    	}
    	stack.clear();
    	for(int i=arr.length-1; i>=0; i--){
    		while(!stack.empty() && arr[stack.peek()] >= arr[i]){
    			stack.pop();
    		}
    		if(!stack.empty())
    			right[i] = stack.peek();
    		stack.push(i);
    	}
    	long[] res = new long[arr.length];
    	for(int i=0; i<arr.length; i++){
    		int winSize = right[i]-left[i]-1;
    		res[winSize-1] = Math.max(res[winSize-1],arr[i]);
    	}
    	for(int i=arr.length-2; i>=1; i--){
    		res[i] = Math.max(res[i], res[i+1]);
    	}
    	return res;
    }
    
    
	
	public static void main(String[] args) {
		long[] arr = {2, 6, 1, 12};
		//long[] arr = {1, 2, 3, 5, 1, 13, 3};
		//long[] arr = {3, 5, 4, 7, 6, 2};
		//long[] arr = {11, 2, 3, 14, 5, 2, 11, 12};
		System.out.println(Arrays.toString(riddle(arr)));
		System.out.println(Arrays.toString(riddle2(arr)));
	}
}