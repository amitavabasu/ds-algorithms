package com.amit.alogs;
import java.util.Arrays;

public class MergeSort {
	
	
	public static long mergeSort(int[] arr, int[] res, int l, int r) {
		int mid;
		long invCount=0;
		if(r>l) {
			mid = (l+r)/2;
			
			invCount = mergeSort(arr,res,l,mid);
			invCount = invCount+mergeSort(arr,res,mid+1,r);
			invCount = invCount+merge(arr,res,l,mid+1,r);
		}
		return invCount;
	}

	public static long merge(int[] arr, int res[], int l, int m, int r) {
		int i = l;
		int j = m;
		int k = l;
		long invCount = 0;
		while(i<=m-1 && j<=r) {
			if(arr[i] <= arr[j]) {
				res[k++] = arr[i++];
			}else {
				res[k++] = arr[j++];
				invCount = invCount+(m-i);
			}
		}
		
		while(i<=m-1)
			res[k++] = arr[i++];
		while(j<=r) {
			res[k++] = arr[j++];
		}
		for(i=l; i<=r; i++) {
			arr[i] = res[i];
		}
		return invCount;
	}
	
    static long countInversions(int[] arr) {
    	int[] res = new int[arr.length];
		return mergeSort(arr, res, 0, arr.length-1);
    }
    
    static long inversionCount(int[] arr) {
    	int invCount = 0;
    	for(int i=0; i<(arr.length-1); i++) {
    		for(int j=i; j<arr.length; j++) {
    			if (arr[i] > arr[j]) 
                    invCount++; 
    		}
    	}
    	return invCount;
    }
    
    
    
	
	public static void main(String[] s) {
		//int[] arr = {7, 5, 3, 1};
		int[] arr = {2, 1, 3, 1, 2};
		//int[] arr = {2, 4, 1, 3, 5, 6};
		int[] res = new int[arr.length];
		System.out.println(inversionCount(arr));
		System.out.println(mergeSort(arr, res, 0, arr.length-1));
		//System.out.println(countInversions(arr));
		System.out.println(Arrays.toString(arr));
		System.out.println(Arrays.toString(res));
		System.out.println(inversionCount(arr));
	}
}
