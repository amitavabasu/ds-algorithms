package com.amit.alogs.hr.medium;

public class HR13StepPermutations {
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
		if(n<0){//<-- if n is less than or 0 return 0
			return 0;
		}
		if(memory[n]!=0){//<-- if it's available in memory for n return that value;
			return memory[n];
		}
		memory[n] = stepPerms(n-3,memory)+stepPerms(n-2,memory)+stepPerms(n-1,memory);//<-- use same like fibonacci series.  
		return memory[n];
	}

	public static void main(String[] args){
		int n = 4;
		int[] memory = new int[n+1];//<-- create a memory 1 more than n
		memory[0] = 0; //<-- set initial as 0
		if(n>=1)
			memory[1] = 1;//<-- set 1 as 1
		if(n>=2)
			memory[2] = 2;//<-- set 2 as 2
		if(n>=3)
			memory[3] = 4;//<-- set 3 as 4
		System.out.println(stepPerms(n, memory));
	}
	
	
	
}
