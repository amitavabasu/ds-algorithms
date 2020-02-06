package com.amit.alogs;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MergeSort {
	
	/*
	 * Do merge sort and also calculate inversion count while doing it from a given array of integers
	 * 
	 */
	
	public static long mergeSort(int[] arr, int[] res, int l, int r) {//<-- main merge sort method which breaks gown the array, splitting it from middle recursively
		int mid;//<-- define middle
		long invCount=0;//<-- initialize inversion count to 0
		if(r>l) {//<-- if right grater than left
			mid = (l+r)/2;//<-- calculate mid
			
			invCount = mergeSort(arr,res,l,mid);//<-- recursively call merge sort for left to mid and get inversion count for left array
			invCount = invCount+mergeSort(arr,res,mid+1,r);//<-- recursively call merge sort for mid+1 to right and get inversion count for right array. ALSO add the total inversion count
			invCount = invCount+merge(arr,res,l,mid+1,r);//<-- now after both the recursion call ends it goes into merge part of the process
														 //This will actually return the inversion count. Check that middle is always mid+1
		}
		return invCount;//<-- return inversion count
	}

	public static long merge(int[] arr, int res[], int l, int m, int r) {//<-- merge method takes array of integer, result array, left, middle and right
		int i = l;//<-- initialize i to l because l can not be changed
		int j = m;//<-- initialize j to m (i.e. mid) because m can not be incremented
		int k = l;//<-- also initialize k result array index to l
		long invCount = 0;//<-- initialize inversion count to 0
		while(i<=m-1 && j<=r) {//<-- until i less than equal to m-1 (end of first half of the array) and j less than r (second half of the array)
			if(arr[i] <= arr[j]) {//<-- compare corresponding elements from both the array
				res[k++] = arr[i++];//<-- if left side element is less than equal to right side element, then put the left element into result array. Increment k & i
			}else {
				res[k++] = arr[j++];//<-- else (means right element is grater than left side element, put the right element into result array. Increment k & j
				invCount = invCount+(m-i);//<-- in this case we found that right side element is grater than left side element. This gives us an inversion.
										  //So, calculate inversion count. It is tricky that total inversion count is actually m-i means all elements right have an inversion.
										  //Add previously inversion count with current inversion count. inVCount +=m-i
			}
		}
		while(i<=m-1) {//<-- copy any remaining part of the left side of the array first to result array
			res[k++] = arr[i++];
		}
		while(j<=r) {//<-- then copy any remaining part of the right side of the array to result array
			res[k++] = arr[j++];
		}
		for(i=l; i<=r; i++) {//<-- copy back the entire result array into actual array
			arr[i] = res[i];
		}
		return invCount;//<-- finally return the inversion count 
	}
	
    static long mergeSortAndIountInversions(int[] arr) {//<-- start method
    	int[] res = new int[arr.length];
		long invCount = mergeSort(arr, res, 0, arr.length-1);//<-- call merge sort for the entire array length 0 to length-1
		System.out.println(Arrays.toString(res));
		return invCount;
    }
    
    static long inversionCount(int[] arr) {//<-- alternate inversion count to check the validity of merge sort based inversion count
    	int invCount = 0;//<-- initialize inversion count to 0
    	for(int i=0; i<(arr.length-1); i++) {//<-- for each element in array
    		for(int j=i; j<arr.length; j++) {//<-- from current (i-th) element to rest of the array
    			if (arr[i] > arr[j]) //<-- if i-th current element is grater than the j-th element  
                    invCount++; //<-- increment inversion count
    		}
    	}
    	return invCount;
    }
    
    static Queue<Integer> queue = new LinkedList<>();
    static int seqCount = 1;
    static void countContSeq(int[] arr) {
    	for(int i=0; i<arr.length; i++) {
    		queue.add(i);
    	}
    	boolean done = false;
    	while(!done) {
    		System.out.println(queue.toString());
	    	Queue<Integer> newQueue = new LinkedList<>();
	    	boolean found = false;
	    	while(!queue.isEmpty()) {
	    		int i = queue.remove();
	    		for(int j=i; j<arr.length; j++) {
	    			if(arr[j]>arr[i]) {
	    				if(!newQueue.contains(j))
	    					newQueue.add(j);
	    				found = true;
	    			}
	    		}
	    	}
			if(found) {
				seqCount++;
				queue = newQueue;
			}else {
				done = true;
			}
    	}
    	System.out.println(seqCount);
    }
    
    
	
	public static void main(String[] s) {
		//int[] arr = {2,1};		
		//int[] arr = {7, 5, 3, 1};
		//int[] arr = {2, 1, 3, 1, 2};
		//int[] arr = {2, 4, 1, 3, 5, 6};
		int[] arr = {3, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
		//int[] arr = {1, 2, 1, 5};
//		System.out.println(Arrays.toString(arr));
//		System.out.println("Correct: "+inversionCount(arr));
//		System.out.println(mergeSortAndIountInversions(arr));
		
		//int[] arr2 = {2,1};
		//int[] arr2 = {7, 5, 3, 1};
		//int[] arr2 = {2, 1, 3, 1, 2};
//		int[] arr2 = {15,14,13,12,11,10,9,8,7,6,5,4,3,2,0};
//		System.out.println(MergeSortDup1.mergeSortAndIountInversions(arr2));
		countContSeq(arr);
	}
}
