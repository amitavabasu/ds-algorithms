package com.amit.alogs.hr.easy;

import java.util.HashSet;
import java.util.Set;

public class HR1SockMarchant {

	public int sockMarchant(int n, int[] ar){
		int count = 0;
		if(n<=1 || ar==null || ar.length<=1){
			return count;
		}
		Set<Integer> countMap = new HashSet<Integer>();
		for(int i=0; i<=n-1; i++){
			int color = ar[i];
			if(countMap.contains(color)){
				count++;
				System.out.println("Pair found for color: "+color+"; i="+i+"; count="+count);				
				boolean status = countMap.remove(color);
				System.out.println("Removed color: "+color+"; i="+i+"; count="+count+"; removal="+status);
			}else{
				boolean status = countMap.add(color);
				System.out.println("Added color: "+color+"; i="+i+"; count="+count+"; addition="+status);
			}
		}
//		Set<Integer> keys = countMap.keySet();
//		for(Integer key:keys){
//			Integer ccount = countMap.get(key);
//			Integer pair = ccount/2;
//			count=count+pair;
//		}
		return count;
	}
	
	
	public static void main(String[] args){
//		int n = 9;
//		int[] ar = {10, 20, 20, 10, 10, 30, 50, 10, 20};
		int n = 100;
		int[] ar = {50,49,38,49,78,36,25,96,10,67,78,58,98,8,53,1,4,7,29,6,59,93,74,3,67,47,12,85,84,40,81,85,89,70,33,66,6,9,13,67,75,42,24,73,49,28,25,5,86,53,10,44,45,35,47,11,81,10,47,16,49,79,52,89,100,36,6,57,96,18,23,71,11,99,95,12,78,19,16,64,23,77,7,19,11,5,81,43,14,27,11,63,57,62,3,56,50,9,13,45};
//		int n = 15;
//		int[] ar = {6,5,2,3,5,2,2,1,1,5,1,3,3,3,5};
		HR1SockMarchant sm = new HR1SockMarchant();
		System.out.println(sm.sockMarchant(n, ar));
	}
}
