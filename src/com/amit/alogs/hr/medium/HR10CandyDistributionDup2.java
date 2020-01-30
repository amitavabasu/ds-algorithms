package com.amit.alogs.hr.medium;

import java.util.Arrays;

public class HR10CandyDistributionDup2 {
	
	/*
	 * Distribute candy with minimum numbers.
	 * Key to the solution - traverse left to right first incrementing based on rule then traverse right to left doing same rule 
	 */

	public static long candies(int n, int[] arr) {
		long count = 0l;
		if(n==0 || arr==null || arr.length==0) {
			return count;
		}
		if(arr.length==1) {
			return 1;
		}
		long[] distribution = new long[arr.length];
		distribution[0] = 1;
		System.out.println(Arrays.toString(arr));
		for(int i=1; i<n; i++) {
			if(arr[i]>arr[i-1]) {
				distribution[i] = distribution[i-1]+1;
			}else {
				distribution[i] = 1;
			}
		}
		System.out.println(Arrays.toString(distribution));
		count = distribution[n-1];
		for(int i=n-2; i>-1; i--) {
			if(arr[i]>arr[i+1])
				if(distribution[i]<=distribution[i+1])
					distribution[i] = distribution[i+1]+1;
			count += distribution[i];
		}
		System.out.println(Arrays.toString(distribution));
		return count;
	}
}