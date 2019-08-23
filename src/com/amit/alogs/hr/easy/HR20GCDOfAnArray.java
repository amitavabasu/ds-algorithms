package com.amit.alogs.hr.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HR20GCDOfAnArray {
	
	public static int gcd(int a, int b){
		if(a==0){
			return b;
		}else{
			return gcd(b%a, a);
		}
	}
	
	public static int findGCD(int[] arr, int length){
		int res = arr[0];
		for(int i=1; i<length; i++){
			res = gcd(arr[i], res);
		}
		return res;
	}
	
	
	public static int gcdNonRecursive(int a, int b){
		int q, r;
		
		// loop till remainder is 0
		while (b > 0)
		{
			q = a / b;		// quotient 
			r = a - q * b;	// remainder

			// or we can simply use (a % b) to calculate r

			// a becomes b and b becomes r (a % b)
			a = b;
			b = r;
		}

		return a;
	}
	
	public static int findGCDNonRecursive(int[] arr, int length){
		int first = arr[0];
		System.out.println(gcdNonRecursive(5, 25));
		
		
		for(int i=1; i<length; i++){
			first = gcdNonRecursive(first,arr[i]);
//			int a = res;
//			int b = arr[i];
//			while(b>0){
//				a=b;
//				b=b%a;
//			}
//			System.out.println(a);
//			res = a;
		}
		return first;
	}
	
	
	
	public static void main(String[] args){
		int[] arr = {5, 25}; //--> 0 1 0 0 1 0 1 0
		System.out.println(findGCD(arr, arr.length));
		System.out.println(findGCDNonRecursive(arr, arr.length));
	}
}
