package com.amit.alogs.hr.medium;



import java.io.IOException;

public class HR16MinimumPassesRequired {
		
	static long minimumPasses(long m, long w, long p, long n) {
		long candiesGenerated = 0;//<-- initialize candies generated as 0
		long actualPasses = 0;//<-- to hold actual pass required
		long possibleActualPasses = Long.MAX_VALUE;//<-- put the max long as max possible passes

		while (candiesGenerated < n) {//Until Candies generated less than the goal 
			long currentRequiredpasses = (long) (((p - candiesGenerated) / (double) m) / w);//<-- avoiding overflow
			if (currentRequiredpasses <= 0) { // <-- check if we have enough candy to buy m or hire w
				long howmanyCanBuyOrHire = (candiesGenerated / p);//<-- calculate how many we can buy or hire 
				long totalAvailable	=	howmanyCanBuyOrHire + m + w;//<-- calculate how many will be total available, so that we can calculate the max multiplication factor
				long half = (long)totalAvailable/2;//<-- calculate half which will be ideal for max multiplication factor 
				if (m > w) {//<-- make decision weather to hire more worker or buy more machines
					m = Math.max(m, half);
					w = totalAvailable - m;
				} else {
					w = Math.max(w, half);
					m = totalAvailable - w;
				}
				candiesGenerated = candiesGenerated % p;//<-- calculate total candies generated in this pass after prices are paid
				currentRequiredpasses++;//<-- increment current passes 1 more
			}
			//Below is estimation process
			long nextPassCandyBeGenerated;//<-- how many candies will be generated in the next pass
			long actualCandyToBeGenerated;//<-- how many candies needed to be generated after next pass
			try {//<-- put a try catch to find if there is overflow or not
				nextPassCandyBeGenerated = Math.multiplyExact(m, w);//<-- calculate how many candies can be generated in next pass
				//To buy next set of machines or hire new workers, i.e. to exceed the price how many actual candies to be generated
				actualCandyToBeGenerated = Math.multiplyExact(currentRequiredpasses, nextPassCandyBeGenerated);
				
				candiesGenerated += actualCandyToBeGenerated;//<-- calculate total candies generated
				actualPasses += currentRequiredpasses;//<-- increment actual pass with current required pass
				long possibleNecessaryPasses = (long) Math.ceil((n - candiesGenerated) / (double) nextPassCandyBeGenerated);//Calculate how many candies to be generated 
				possibleActualPasses = Math.min(possibleActualPasses, actualPasses + possibleNecessaryPasses);//Find and initialize minimum possible passes
			} catch (ArithmeticException ex) {
				// we need to add current pass
				 actualPasses += 1;
				// increment will be 1 because of overflow
				possibleActualPasses = Math.min(possibleActualPasses, actualPasses + 1);
				break;
			}
		}

		return Math.min(possibleActualPasses, actualPasses);
	}

	public static void main(String[] args) throws IOException {
    	long result = minimumPasses(1, 2, 1, 60);
    	System.out.println(result);
   }
}
