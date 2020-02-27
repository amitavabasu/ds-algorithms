package com.amit.alogs.hr.medium;

import java.util.Arrays;

public class HR14MinimumMovesDup2 {

	public int minMove(int n) {
		int max = 1000001;
		int moves[] = new int[max];
		Arrays.fill(moves, -1);
		moves[0] = 0;
		moves[1] = 1;
		moves[2] = 2;
		moves[3] = 3;
		//Pre process
		for(int i=1; i<max; i++) {
			if(moves[i]==-1 || moves[i] > moves[i-1]+1) {
				moves[i] = moves[i-1]+1;
			}
			for(int j=1; j<=i && i*j < max; j++) {
				if(moves[i*j]==-1 || moves[i*j] > moves[i]+1) {
					moves[i*j] = moves[i]+1;
				}
			}
		}
		//System.out.println(Arrays.toString(moves));
		return moves[n];
	}
}