package com.amit.alogs.hr.hard;

public class HR1ArrayManupulation {
	/* This is a wonderful problem & solution 
	 * Time Complexity O(n) and Space complexity O(1)
	 * */ 
	public long arrayManipulation(int n, int[][] queries){
		long maxResult = 0;
		if(n==0 || queries==null || queries.length==0){
			return maxResult;
		}
		long[] opArray = new long[n+1];
		for(int i=0;i<queries.length; i++){
			int a = queries[i][0];
			int b = queries[i][1];
			int k = queries[i][2];
			opArray[a-1] = opArray[a-1]+k;
			if(b<=n){
				opArray[b] = opArray[b]-k;
			}
		}
		long x = 0;
		for(int i=0; i<=n; i++){
			x = x+opArray[i];
			if(x>maxResult){
				maxResult = x;
			}
		}
		return maxResult;
	}
	
	public static void main(String[] args){
//		int n = 5;
//		int[][] queries = {{1,2,100},{2,5,100},{3,4,100}};
		int n = 10;
		int[][] queries = {{1,3,3},{4,6,7},{9,9,10}};
		HR1ArrayManupulation obj = new HR1ArrayManupulation();
		System.out.println(obj.arrayManipulation(n, queries));
	}
}
