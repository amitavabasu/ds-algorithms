package com.amit.alogs.hr.easy;

import java.util.Arrays;

public class HR12MaximumToys {
	/*
	 * How many maximum toys can be purchased given an array of prices for toys and an amount k
	 * 
	 */

	public int maximumToys(int[] prices, int k) {
		/*
		 * Simple algorithm, needs a to sort the array
		 */
		int max = 0;
		if(prices==null||prices.length==0||k==0){
			return max;
		}
		Arrays.sort(prices);//<-- sort the array
		boolean done = false;
		int i = 0;
		int total = 0;//<-- initialize total to 0
		while(!done){
			int price = prices[i];
			total = total+price;//<-- calculate total price
			if(total<=k){//<-- if total less that equal the amount provided increment count
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
