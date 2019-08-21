package com.amit.alogs.hr.easy;

import java.util.Arrays;
import java.util.Stack;

public class HR15MaxArraySumWithinAWindow {
	/*
	 * return max sum of a given window size within an array of integers
	 * 
	 */
	public static int maxSubArraySumInAGivenWindow(int[] arr, int window){
		
		int sum = 0;
		int i=0;
		while(i<window){//<-- calculate sum of the window
			sum = sum+arr[i];
			i++;
		}
		int maxSum = sum;
		while(i<arr.length) {//<-- iterate over rest of the array
			sum = sum-arr[i-window]+arr[i];//<-- calculate next sum
			if(maxSum<sum){//<-- check and reassign maximum sum
				maxSum = sum;
			}
				i++;
		}
		return maxSum;//<-- return maximum sum
	}
	
	public static int maxSubArraySumInAGivenWindowAlternateMethod(int[] arr, int window){
		int maxSum = Integer.MIN_VALUE;
		int sum = 0;
		int i=0;
		int j=0;
		while(i<arr.length){
			if(i<window){
				sum = sum+arr[i];
				i++;
			}else{
				if(sum>maxSum){
					maxSum = sum;
				}
				sum = sum-arr[j]+arr[i];
				i++;
				j++;
			}
		}
		if(sum>maxSum){
			maxSum = sum;
		}
		return maxSum;
	}
	
	
	
	
	static long largestRectangle(int[] h) {
	    Stack<Integer[]> stack = new Stack<>();
	    h = Arrays.copyOf(h, h.length+1);
	    int j;
	    int maximum = 0;
	    for(int i = 0; i <= h.length-1; i++) {
	        for(j = i; !stack.isEmpty();) {
		    	Integer[] topPrevRangeIndexes = stack.peek();
		    	int prevRangeStart = topPrevRangeIndexes[0];
		    	int prevRangeEnd = topPrevRangeIndexes[1];
		    	if(h[prevRangeStart] >= h[i]){
		            j = prevRangeEnd;
		            maximum = Math.max(maximum, h[prevRangeStart] * (i - j));
		            System.out.println("popping: "+prevRangeStart+"-"+prevRangeEnd);
		            stack.pop();
		    	}else{
		    		break;
		    	}
	        }
	        System.out.println("pushing: "+i+"-"+j);
	        stack.push(new Integer[]{i,j});
	    }
	    return maximum;
	}
	
	public static void main(String[] args){
		int[] arr = {5,7,9,10,20,30,8,1};
		System.out.println(maxSubArraySumInAGivenWindowAlternateMethod(arr, 3));
		System.out.println(maxSubArraySumInAGivenWindow(arr, 3));
		//int[] h = {5,4,3,2,1};
		//int[] h = {1,2,3,4,5};
		//int[] h = {8979, 4570, 6436, 5083, 7780, 3269, 5400, 7579, 2324, 2116};
		//int[] h = {1,2,3,4,5,4,6,2,1};
		//System.out.println(largestRectangle(h));
	}
}
