package com.amit.alogs;

public class EulerProblemsSoll {

	/* calculate sum of all the multiples of two numbers a & b until to a limit
	 * Example: a=3, b=5 & limit = 20
	 * 3,5,6,9,10,12,15,18 = 78  
	 */
	public static long sumOfMultipleOfTwoGivenNumbers(int a, int b, int limit){
		long sum = 0;
		int i = 1;
		while(a*i < limit || b*i < limit){
			if(a*i<limit && a*i%b!=0){
				sum +=a*i;
			}
			if(b*i<limit)
				sum += b*i;
			i++;
		}
		return sum;
	}
	
	
	/*
	 * TEST Main method
	 */
	public static void main(String[] args){
		System.out.println(sumOfMultipleOfTwoGivenNumbers(5,3,1000));
		//System.out.println(fibonaciiSum(4000000));
		//System.out.println(primeFactors(600851475143L));
		//System.out.println(isPalindrome("abaaaaa"));
		//System.out.println(higestPalindrome(5));
		//System.out.println(nthPrime(10001));
		//generateNumbersWhichAreSumOfTheFactOfItsDigits(3);
		//sumOfPower();
	}
	
}
