package com.amit.alogs;

import java.math.BigInteger;
import java.text.DecimalFormat;

import javax.swing.text.NumberFormatter;

public class EulerProblems {

	public static long sumOdMultipleOfTwoGivenNumbers(int a, int b, int limit){
		long sum = 0;
		boolean done = false;
		int i=1;
		int j=1;
		while(!done){
			int amultiple = i*a;
			int bmultiple = j*b;
			if(amultiple<limit){
				if(amultiple%b!=0){
					System.out.println("Adding for a: "+amultiple);
					sum = sum+amultiple;
				}
				i++;
			}
			if(bmultiple<limit){
				System.out.println("Adding for b: "+bmultiple);
				sum = sum+bmultiple;
				j++;
			}
			if(amultiple >= limit && bmultiple >= limit){
				done = true;
			}
		}
		return sum;
	}
	
	
	public static long fibonaciiSum(int limit){
		long sum = 2;
		int first = 1;
		int second = 2;
		System.out.println(first);
		System.out.println(second);
		boolean done = false;
		while(!done){
			int fib = first+second;
			if(fib>limit){
				done = true;
			}else{
				System.out.println(fib);
				if(fib%2==0){
					sum = sum+fib;
				}
				first = second;
				second = fib;
				
			}
		}
		return sum;
	}
	
	public static int primeFactors(long n){
		if(n>0){
			int p = 2;
			boolean done = false;
			while(!done){
				if(n < p*p){
					System.out.println(n);
					done = true;
				}else{
					if(n%p==0){
						System.out.println(p);
						n = n/p;
					}else{
						p=p+1;
					}
				}
			}
		}
		return 0;
	}
	public static boolean isPalindrome(String s){
		StringBuilder sb = new StringBuilder(s);
		sb.reverse();
		String temp = sb.toString();
		return temp.equals(s);
	}
	
	public static long higestPalindrome(int n){
		char[] s1 = new char[n];
		char[] s2 = new char[n];
		s1[0] = '9';
		s2[0] = '1'; 
		for(int i=1; i<n; i++ ){
			s1[i]='9';
			s2[i]='0';
		}
		int max = Integer.parseInt(new String(s1));
		int min = Integer.parseInt(new String(s2));
		int maxPal = 0;
		for(int i=max; i>=min; i--){
			for(int j=max; j>=min; j--){
				int num = i*j;
				if(isPalindrome(num+"")){
					if(num>maxPal){
						maxPal = num;
					}
				}
			}
		}
		return maxPal;
	}
	
	public static long nthPrime(int num){
		long[] primes = new long[num];
		primes[0] = 2;
		primes[1] = 3;
		primes[2] = 5;
		primes[3] = 7;
		System.out.println(2+"-"+1);
		System.out.println(3+"-"+2);
		System.out.println(5+"-"+3);
		System.out.println(7+"-"+4);
		int count = 4;
		long n = 11;
		boolean done = false;
		while(!done){
			//Check in n is prime or not
			boolean flag = true;
			for(int i=1; (i<num)&&(primes[i]!=0)&&primes[i]<=Math.sqrt(n); i++){
				long diviser = primes[i];
				if(n%diviser==0){
					flag = false;
					break;
				}
			}
			if(flag){
				primes[count]=n;
				System.out.println(n+"-"+(count+1));
				count++;
			}
			if(count>=num){
				done = true;
			}else{
				n +=2;
			}
		}
		return primes[primes.length-1];
	}
	
	public static int factorialSum(int n, int[] fact){
		int sum = 0;
		while(n>0){
			sum += fact[n%10];
			n = n/10;
		}
		return sum;
	}
	
	public static void generateNumbersWhichAreSumOfTheFactOfItsDigits(int n){
		int[] fact = new int[10];
		fact[0] = 1;
		for (int i = 1; i < 10; i++)
		    fact[i] = fact[i - 1] * i;
		int count = 0;
		int i=3;
		while(i<=1499999){
			if(i==factorialSum(i, fact)){
				System.out.println(i);
				count++;
			}
			i++;
		}
	}
	
	public static void sumOfPower(){
//		double pow = Math.pow(a, b);
//		DecimalFormat f = new DecimalFormat("#");
//		String s = f.format(pow);
//		System.out.println(s);
//		System.out.println(s.length());
//		long sum = 0;
//		int count = 1;
//		for(int i=0; i<s.length(); i++){
//			char c = s.charAt(i);
//			System.out.println(c+"-"+count++);
//			sum += Integer.parseInt(c+"");
//		}
//		System.out.println(sum);
//		int n = 10;
//		BigInteger a,b,r;
//		BigInteger p=new BigInteger("100");
//		BigInteger s=new BigInteger("0");
//		b=new BigInteger("2");
//		  a=b.pow(n);
//		    while(!a.equals(BigInteger.ZERO))
//		        {
//		         r=a.remainder(p);
//		         s=s.add(r);
//		         a=a.divide(p);
//		        }
//		    System.out.println(s);
		    int s=0; 
		    double n= Math.pow(2,1000);
		    while(n > 0){
		    	s+=n%10;
		    	n=n/10;		    	
		    }
		    System.out.println(s);		
	}
	
	
	public static void main(String[] args){
		//System.out.println(sumOdMultipleOfTwoGivenNumbers(3,5,1000));
		//System.out.println(fibonaciiSum(4000000));
		//System.out.println(primeFactors(600851475143L));
		//System.out.println(isPalindrome("abaaaaa"));
		//System.out.println(higestPalindrome(5));
		//System.out.println(nthPrime(10001));
		//generateNumbersWhichAreSumOfTheFactOfItsDigits(3);
		sumOfPower();
	}
	
}
