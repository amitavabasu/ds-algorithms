package com.amit.alogs;

import java.util.Arrays;

public class MergeSortDup1 {
    
	static long mergeSort(int[] arr, int[] res, int l, int r) {
		int m;
		long invCount = 0;
		if(r>l) {
			m = (l+r)/2;
			invCount = mergeSort(arr,res,l,m);
			invCount = invCount+mergeSort(arr, res, m+1, r);
			invCount = invCount+merge(arr,res,l,m+1,r);
		}
		return invCount;
	}
	
	static long merge(int[] arr, int[] res, int l, int m, int r) {
		int invCount = 0;
		int i=l;
		int j=m;
		int k=l;
		while(i<=m-1 && j<=r) {
			if(arr[i]<=arr[j]) {
				res[k++] = arr[i++];
			}else {
				res[k++] = arr[j++];
				invCount = invCount+(m-i);
			}
		}
		while(i<=m-1) {
			res[k++] = arr[i++];
		}
		while(j<=r) {
			res[k++] = arr[j++];
		}
		for(i=l;i<=r;i++) {
			arr[i] = res[i];
		}
		return invCount;
	}
	
	
	static long mergeSortAndIountInversions(int[] arr) {
    	if(arr==null || arr.length==0 || arr.length==1)
    		return 0l;
    	int length = arr.length;
    	int[] res = new int[length];
    	long invCount = mergeSort(arr,res,0,length-1);
    	System.out.println(Arrays.toString(res));
    	return invCount;
    }
}
