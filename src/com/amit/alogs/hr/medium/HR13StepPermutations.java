package com.amit.alogs.hr.medium;

public class HR13StepPermutations {
	
	
	static int stepPerms(int n, int[] memory) {
		if(n<0){
			return 0;
		}
		if(memory[n]!=0){
			return memory[n];
		}
		memory[n] = stepPerms(n-3,memory)+stepPerms(n-2,memory)+stepPerms(n-1,memory);
		return memory[n];
	}

	private static int helper(int n, int[] m) {
		if (n < 0) {
			return 0;
		}
		if (m[n] != 0) {
			return m[n];
		}
		if (n == 1) {
			return 1;
		}
		m[n] = helper(n - 3, m) + helper(n - 2, m) + helper(n - 1, m);
		return m[n];
	}
	
	public static void main(String[] args){
		int n = 3;
		int[] memory = new int[n+1];
		memory[0] = 0;
		if(n>=1)
			memory[1] = 1;
		if(n>=2)
			memory[2] = 2;
		if(n>=3)
			memory[3] = 4;
		System.out.println(stepPerms(n, memory));
	}
	
	
	
}
