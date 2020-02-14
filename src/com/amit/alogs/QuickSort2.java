package com.amit.alogs;

import java.util.Arrays;

public class QuickSort2 {

	public static void quickSort(int[] arr, int left, int right) {
		int i=left, j=right;
		System.out.println(i+" .. "+j);
		int pivot = arr[(i+j)/2];
		while(i<j) {//This is correct do not iterate if i=j (do not do the wrong)
			while(arr[i]<pivot) {
				i++;
			}
			while(arr[j]>pivot) {
				j--;
			}
			
			if(i<j) {//This is correct do to exchange if i=j
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				System.out.println(Arrays.toString(arr) + " "+i+"<->"+j);
				i++;
				j--;
			}else if(i==j) {//This is correct if i=j no exchange but need to increment i and decrement j, so that no infinite loop occurs.
				i++;
				j--;
			}
		}
		if(j>left)
			quickSort(arr,left,j);
		if(i<right)
			quickSort(arr,i,right);
	}
	
	
	
	
	public static void main(String[] args) {
		int[] arr = {5,4,8,0,2,7,5,6,9,1,3};
		System.out.println(Arrays.toString(arr));
		quickSort(arr, 0,arr.length-1);
		System.out.println(Arrays.toString(arr));
	}
}
