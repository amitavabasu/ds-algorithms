package com.amit.alogs.hr.easy;

import java.util.Arrays;

public class HR7ArrayLeftRotation {

	public int[] rotLeft1(int[] a, int d) {
		if(a==null || a.length <= 1 || d==0){
			return a;
		}
		for(int i=0; i<d; i++){
			int first = a[0];
			for(int j=1; j<a.length; j++){
				a[j-1] = a[j];
			}
			a[a.length-1]=first;
		}
		return a;
	}
	
	public int[] rotLeft2(int[] a, int d) {
		if(a==null || a.length <= 1 || d==0){
			return a;
		}
		int indexAt = 0;
		int valueAt = a[indexAt];
		boolean done = false;
		while(!done){
			int nextIndex = indexAt-d;
			if(nextIndex<0){
				int drift = ((-(nextIndex))%a.length);
				if(drift==0){
					nextIndex=0;
				}else{
					nextIndex = a.length-(-(nextIndex))%a.length;
				}
			}
			System.out.println(nextIndex);
			if(nextIndex==0){
				a[nextIndex] = valueAt;
				done = true;
			}else{
				int temp = a[nextIndex]; 
				a[nextIndex] = valueAt; 
				valueAt = temp; 
				indexAt = nextIndex;
			}
		}
		return a;
	}
	
	public int[] rotLeft3(int[] a, int d) {
		if(a==null || a.length <= 1 || d==0){
			return a;
		}
		int[] result = new int[a.length];
		for(int indexAt=0; indexAt<a.length; indexAt++){
			int nextIndex = indexAt-d;
			if(nextIndex<0){
				int drift = ((-(nextIndex))%a.length);
				if(drift==0){
					nextIndex=0;
				}else{
					nextIndex = a.length-(-(nextIndex))%a.length;
				}
			}
			System.out.println(nextIndex);
			result[nextIndex] = a[indexAt];
		}
		return result;
	}
	
	public static void main(String[] args){
		int[] a = {0,1,2,3,4};
		int d = 12;
		HR7ArrayLeftRotation sm = new HR7ArrayLeftRotation();
		System.out.println(Arrays.toString(sm.rotLeft1(a,d)));
		int[] b = {0,1,2,3,4};
		System.out.println(Arrays.toString(sm.rotLeft2(b,d)));
		int[] c = {0,1,2,3,4};
		System.out.println(Arrays.toString(sm.rotLeft2(c,d)));
	}
}
