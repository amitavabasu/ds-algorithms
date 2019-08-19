package com.amit.alogs.hr.medium;

public class HR2MinSwaps {
	/*
	 * count number of swaps needed sort an array of natural numbers i.r. 5,4,3,1,2 --> 1,2,3,4,5 
	 */
	public int minimumSwaps(int[] arr){
		/*
		 * for each element check it's ideal position then swap with ideal position, if swap needed swap and increment swap count.
		 */
		int swapCount = 0;
		if(arr==null || arr.length <2){
			return swapCount;
		}
		for(int i=0; i<arr.length; i++){
			int element = arr[i];
			int idealIndex = element-1;
			if(i!=idealIndex) {
				int temp = arr[i];
				arr[i] = arr[idealIndex];
				arr[idealIndex] = temp;
				swapCount++;
			}
		}
		return swapCount;
	}
	
	public static void main(String[] args){
		//int[] arr = {7, 1, 3, 2, 4, 5, 6};
		//int[] arr = {7, 6, 5, 4, 3, 2, 1};
		//int[] arr = {1, 3, 5, 2, 4, 6, 7};
		int[] arr = {4, 3, 1, 2};
		HR2MinSwaps sm = new HR2MinSwaps();
		System.out.println(sm.minimumSwaps(arr));
	}
}
