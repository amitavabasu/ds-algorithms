package com.amit.alogs.hr.easy;

public class HR21GCDOfAnArray {
	
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
		int second = arr[0];
		for(int i=1; i<length; i++){
			int first = arr[i];
			while(first>0){
				int quotient = second/first;
				int reminder = second - quotient*first;
				
				second=first;
				first=reminder;
			}
		}
		return second;
	}
	
	
	
	public static void main(String[] args){
		int[] arr = {5, 25,30,45, 40}; //--> 0 1 0 0 1 0 1 0
		System.out.println(findGCD(arr, arr.length));
		System.out.println(findGCDNonRecursive(arr, arr.length));
	}
}
