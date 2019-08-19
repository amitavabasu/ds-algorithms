package com.amit.alogs.hr.medium;

import java.util.Arrays;
import java.util.Stack;

public class HR11MinMaxRiddle {
	/*
	 * Get the maximum of all minimum values in an array window sizing 1 to n where n is the length of the array
	 * Example for array 2 6 1 12
	 * Min of winsize-1 is 12
	 * Min of winsizw-2 is 2
	 * Min of winsize-3 is 1
	 * Min of winsize-4 is 1
	 * So result to return is: 12,2,1,1
	 */
    static long[] riddle(long[] arr) {
    	/*
    	 * create two arrays right and left to keep the index of the min element of current element on 
    	 * both sides
    	 */
    	Stack<Integer> stack = new Stack<>();
    	int[] right = new int[arr.length];
    	int[] left = new int[arr.length];
    	//Initialize the right array with n=arr.length and left array with -1.
    	for(int i=0; i<arr.length; i++){
    		right[i] = arr.length;
    		left[i] = -1;
    	}
    	
    	//Use a stack to create the right array
    	for(int i=0; i<arr.length; i++){
    		while(!stack.empty() && arr[stack.peek()] >= arr[i]){
    			stack.pop();
    		}
    		if(!stack.empty())
    			left[i] = stack.peek();
    		stack.push(i);//<-- push the index of right min element. Keep n=arr.length if not found/available
    	}
    	//Clear stack to be reused for left array
    	stack.clear();
    	//Use the same stack to create left array
    	for(int i=arr.length-1; i>=0; i--){
    		while(!stack.empty() && arr[stack.peek()] >= arr[i]){
    			stack.pop();
    		}
    		if(!stack.empty())
    			right[i] = stack.peek();
    		stack.push(i);//<-- push the index of the left min element in left array. Keep -1 if not found/available
    	}
    	long[] res = new long[arr.length];
    	//Iterate over the left and right array calculating the window size
    	//String the max value of the window size in result
    	for(int i=0; i<arr.length; i++){
    		int winSize = right[i]-left[i]-1;//<-- calculate window size
    		res[winSize-1] = Math.max(res[winSize-1],arr[i]);//<-- store the max of current result value and the element at that location
    	}
    	//Iterate over the result array in reverse order checking for adjacent max values and restoring the max ones.
    	//Why this is required? - That is because the result array may have 0 (zeros) for particular window size.  
    	for(int i=arr.length-2; i>=1; i--){
    		res[i] = Math.max(res[i], res[i+1]);
    	}
    	return res;
    }
    
    //Complicated but Inefficient solution using Dynamic programming O(n*2) time complexity 
    static long[] riddleAlternate(long[] arr) {
    	if(arr==null||arr.length==0){
    		return null;
    	}
    	if(arr.length==1){
    		return arr;
    	}
    	//Create DP array to store minimum elements of each window size
    	long[][] min = new long[arr.length][arr.length];
    	//Create the result array which will hold maximum element of each minimum series 
    	long[] max = new long[arr.length];
    	//initialize the DP array with 0th element
    	min[0][0] = arr[0];
    	max[0] = arr[0];
    	//Iterate the entire array for window size and/or each element value
    	for(int i=1;i<arr.length; i++){
    		long val = arr[i]; //<-- get the array value
    		max[0] = Math.max(max[0], val);//<-- store the maximum for the window-size 1
    		min[0][i] = val;//<-- store minimum as the first value in min array
    		for(int j=i; j>0; j--){
    			min[i-j+1][j-1] = Math.min(val, min[i-j][j-1]);//<-- now calculate the minimum with current value of the array and all previous rows value
    			max[i-j+1] = Math.max(max[i-j+1], min[i-j+1][j-1]);//<-- simultaneously calculate the max of each row of the DP min array
    		}
    	}
//    	for(int i=0; i<arr.length; i++){
//    		System.out.println(Arrays.toString(min[i]));
//    	}
    	return max;//<-- return the max array
    }
	
	public static void main(String[] args) {
		//long[] arr = {2, 6, 1, 12};
		//long[] arr = {1, 2, 3, 5, 1, 13, 3};
		//long[] arr = {3, 5, 4, 7, 6, 2};
		long[] arr = {11, 2, 3, 14, 5, 2, 11, 12};
		System.out.println(Arrays.toString(riddleAlternate(arr)));
		System.out.println(Arrays.toString(riddle(arr)));
	}
}