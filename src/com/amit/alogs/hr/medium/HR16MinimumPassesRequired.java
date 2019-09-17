package com.amit.alogs.hr.medium;



import java.io.IOException;

public class HR16MinimumPassesRequired {
		
	static long minimumPasses(long m, long w, long p, long n) {
		long candiesGenerated = 0;
		long actualPasses = 0;
		long possibleActualPasses = Long.MAX_VALUE;

		while (candiesGenerated < n) {
			long currentRequiredpasses = (long) (((p - candiesGenerated) / (double) m) / w);//<-- avoiding overflow
			if (currentRequiredpasses <= 0) { // Have enough candy to buy m or hire w
				long howmanyCanBuyOrHire = (candiesGenerated / p); 
				long totalAvailable	=	howmanyCanBuyOrHire + m + w;
				long half = (long)totalAvailable/2;
				if (m > w) {
					m = Math.max(m, half);
					w = totalAvailable - m;
				} else {
					w = Math.max(w, half);
					m = totalAvailable - w;
				}
				candiesGenerated = candiesGenerated % p;
				currentRequiredpasses++;
			}
			long nextPassCandyBeGenerated;
			long actualCandyToBeGenerated;
			try {
				nextPassCandyBeGenerated = Math.multiplyExact(m, w);
				actualCandyToBeGenerated = Math.multiplyExact(currentRequiredpasses, nextPassCandyBeGenerated);
				candiesGenerated += actualCandyToBeGenerated;
				actualPasses += currentRequiredpasses;
				long possibleNecessaryPasses = (long) Math.ceil((n - candiesGenerated) / (double) nextPassCandyBeGenerated);
				possibleActualPasses = Math.min(possibleActualPasses, actualPasses + possibleNecessaryPasses);
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
