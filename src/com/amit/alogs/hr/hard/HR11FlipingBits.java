package com.amit.alogs.hr.hard;



import java.io.IOException;

public class HR11FlipingBits {
		
	public static long flipBits(long n){
	    long finalResult = 4294967295L;
		//4294967080
//	    long finalResult = 0L;
//	    System.out.println(Long.toBinaryString(finalResult)+"-------------------0");
//	    for (int i = 0; i < 32; i++) {
//	        finalResult = finalResult | (1L << i);
//	        String s = Long.toBinaryString(finalResult);
//	        System.out.println(s+"-------------------"+i+"-------"+Long.parseLong(s, 2));	        
//	    }
//	    System.out.println(Long.toBinaryString(n)+"-------------------n");
//	    System.out.println(Long.toBinaryString(finalResult)+"-------------------finalResult");
	    long res = n ^ finalResult;
//	    System.out.println(Long.toBinaryString(res)+"-------------------result");
	    return res;		
	}
	
    public static void main(String[] args) throws IOException {
    	long n = 215L;
    	System.out.println(flipBits(n));
   }
}
