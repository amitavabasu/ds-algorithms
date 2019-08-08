package com.amit.alogs;


import java.util.Arrays;

public class MaxProfitByKthTransaction {
	
	public double maxProfit(double[] prices, int k){
		if(prices==null || prices.length==0 || k==0){
			return 0;
		}
		double[][] profits = new double[k+1][prices.length];
		for(int j=1;j<=k; j++){
			double maxProfit = Double.NEGATIVE_INFINITY;
			for(int i=1; i<prices.length; i++){
				if(maxProfit < profits[j-1][i-1]-prices[i-1]){
					maxProfit = profits[j-1][i-1]-prices[i-1];
				}
				double newValue = prices[i]+maxProfit;
				profits[j][i] = Math.max(profits[j][i-1], newValue);
			}
			System.out.println(Arrays.deepToString(profits));
		}
		return profits[k][prices.length-1];
	}

	public double maxProfitByKTransactions(double[] prices, int transactions){
		if(prices==null || prices.length <= 1 || transactions <= 0){
			return 0;
		}
		double[] previousRow = new double[prices.length];
		double[] currentRow = new double[prices.length];
		//double[][] profits = new double[transactions+1][prices.length];
		System.out.println(Arrays.toString(prices));
		for(int i=1; i<=transactions; i++){
			double maxProfitSoFar = Double.NEGATIVE_INFINITY;
			System.out.println(Arrays.toString(currentRow));
			if(i>1){
				previousRow = currentRow;
				currentRow = new double[prices.length];
			}
			for(int j=1; j<prices.length; j++){
				if(maxProfitSoFar < previousRow[j-1]-prices[j-1]){
					maxProfitSoFar = previousRow[j-1]-prices[j-1];
				}
				double newProfit = prices[j]+maxProfitSoFar;
				currentRow[j] = Math.max(currentRow[j-1], newProfit);
			}
		}
		System.out.println(Arrays.toString(currentRow));
		return currentRow[prices.length-1];
	}
	
	
	
	public static void main(String[] args){
		double[] prices = {5,11,3,50,60,90};
		//double[] prices = {10,11,2,50,1,90};
		MaxProfitByKthTransaction instance = new MaxProfitByKthTransaction();
		System.out.println("MaxProfit: "+instance.maxProfit(prices, 4));
		System.out.println("MaxProfit: "+instance.maxProfitByKTransactions(prices, 4));
	}
}
