package com.amit.alogs.hr.easy;

import java.util.Arrays;

public class HR12MaximumToys {

	public int maximumToys(int[] prices, int k) {
		int max = 0;
		if(prices==null||prices.length==0||k==0){
			return max;
		}
		Arrays.sort(prices);
		boolean done = false;
		int i = 0;
		int total = 0;
		while(!done){
			int price = prices[i];
			total = total+price;
			if(total<=k){
				max++;
				i++;
			}else{
				done=true;
			}
		}
		return max;
	}
	
	public static void main(String[] args){
		int[] prices = {1,2,3,4};
		int k = 7;
		HR12MaximumToys sm = new HR12MaximumToys();
		System.out.println(sm.maximumToys(prices, k));
	}
}
