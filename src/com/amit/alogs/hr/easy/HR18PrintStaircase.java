package com.amit.alogs.hr.easy;

import java.util.Arrays;

public class HR18PrintStaircase {
	
    static void staircase(int n) {
    	if(n==0)
    		return;
    	char[] chars = new char[n];
    	Arrays.fill(chars, ' ');
    	for(int i=0; i<n-1; i++){
    		chars[i] = '#';
    		System.out.println(chars);
    	}
    	chars[n-1] = '#';
    	System.out.print(chars);
    }
    
    static void staircase2(int n) {
    	if(n==0)
    		return;
    	char c = '#';
    	for(int i=0; i<n; i++){
    		for(int j=0; j<=i; j++){
    			System.out.print(c);
    		}
    		if(i<n-1)
    			System.out.println(System.lineSeparator());
    	}
    }
    
	
	public static void main(String[] args){
		staircase(10);
		System.out.println();
		staircase2(10);
	}
}
