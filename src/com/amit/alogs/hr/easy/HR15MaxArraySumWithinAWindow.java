package com.amit.alogs.hr.easy;

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
	
	public static void main(String[] args){
		int[] arr = {5,7,9,10,20,30,8,1};
		System.out.println(maxSubArraySumInAGivenWindowAlternateMethod(arr, 3));
		System.out.println(maxSubArraySumInAGivenWindow(arr, 3));
	}
}
