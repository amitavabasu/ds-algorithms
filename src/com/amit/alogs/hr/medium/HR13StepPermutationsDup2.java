package com.amit.alogs.hr.medium;

public class HR13StepPermutationsDup2 {
	/*
	 * Calculate number of permutation someone can climb stairs in 1, 2 & 3 steps at a time
	 * Given n number of steps
	 * if n=1 it is 1, --> 1
	 * if n=2 it is 2, --> 1,1|2 
	 * if n=3 it is 4, --> 3|1,2|2,1|1,1,1
	 * if n=4 it is 7, --> 3,1|1,3|2,2|1,1,2|1,2,1|2,1,1|1,1,1,1
	 */
	
	//Use DP and a memory to hold previously calculated value
	static int stepPerms(int n, int[] memory) {
		if(n<=0)
			return 0;
		if(memory[n]!=0)
			return memory[n];
		memory[n] = stepPerms(n-1,memory)+stepPerms(n-2, memory)+stepPerms(n-3, memory);
		return memory[n];
	}
}
