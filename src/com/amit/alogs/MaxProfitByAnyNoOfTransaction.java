package com.amit.alogs;

public class MaxProfitByAnyNoOfTransaction {
	public static double calculate(double[] prices) {
		if(prices==null || prices.length==0) {
			return 0;
		}
		int noOfDays = prices.length;
		double[] previousDayProfits = new double[noOfDays];
		double[] currentDayProfits = new double[noOfDays];

		for(int d=0; d<noOfDays; d++) {
			double maxProfitSoFar = Double.NEGATIVE_INFINITY;
			if(d>0) {
				previousDayProfits = currentDayProfits;
				currentDayProfits = new double[noOfDays];
			}
			for(int j=1; j<noOfDays; j++) {
				if(maxProfitSoFar < previousDayProfits[j-1]-prices[j-1]) {
					maxProfitSoFar = previousDayProfits[j-1]-prices[j-1];
				}
				double newProfit = maxProfitSoFar+prices[j];
				currentDayProfits[j] = Math.max(currentDayProfits[j-1], newProfit);
			}
		}
		return currentDayProfits[noOfDays-1];
	}
	

	public static void main(String[] args) {
		double[] prices = {5, 2, 10};
		System.out.println(calculate(prices));
	}
}