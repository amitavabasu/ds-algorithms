package com.amit.alogs.hr.medium;

import java.util.Arrays;

public class HR10CandyDistribution {
	
	/*
	 * Distribute candy with minimum numbers.
	 * Key to the solution - traverse left to right first incrementing based on rule then traverse right to left doing same rule 
	 */

	public static long candies(int n, int[] arr) {
		if(arr==null || arr.length==0){
			return 0;
		}
		if(arr.length==1){
			return 1;
		}
		int[] distribution = new int[arr.length];
		int curDistNum = 1;
		int preDistNum;
		distribution[0] = curDistNum;
		preDistNum = curDistNum;
		System.out.println(Arrays.toString(arr));
		for(int i=1; i<arr.length; i++){
			int cur = arr[i];
			int pre = arr[i-1];
			if(cur>pre){
				distribution[i] = ++preDistNum;
			}else{
				distribution[i] = 1;
				preDistNum = 1;
			}
		}
		System.out.println(Arrays.toString(distribution));
		long count = distribution[arr.length-1];
		for(int i=arr.length-1; i>0; i--){
			int cur = arr[i-1];
			int pre = arr[i];
			if(cur>pre){
				if(distribution[i-1]<=distribution[i]){
					distribution[i-1] = distribution[i]+1;
				}else{
					//nothing needs to be done
				}
			}else{
				//nothing needs to be done
			}
			count +=distribution[i-1];
		}
		System.out.println(Arrays.toString(distribution));
		return count;
	}
	
	public static void main(String[] args) {
		int[] arr = {2,4,2,6,1,7,8,9,2,1};
		//int[] arr = {2,4,3,5,2,6,4,5};
		System.out.println(candies(arr.length,arr));
		System.out.println(HR10CandyDistributionDup2.candies(arr.length, arr));
	}
	
	 
}