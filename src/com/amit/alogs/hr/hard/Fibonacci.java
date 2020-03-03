package com.amit.alogs.hr.hard;

public class Fibonacci {
	public static int getNthFib(int n) {
		int[] memory = new int[n + 1];
		memory[0] = 1;
		if (n > 1)
			memory[1] = 1;
		if (n > 2)
			memory[2] = 2;
		if (n > 3)
			memory[3] = 3;
		if (n > 4)
			memory[4] = 5;
		if (n > 5)
			memory[5] = 8;
		if (n == 1) {
			return memory[n - 1];
		} else {
			return fib(memory, n);
		}
	}

	public static int fib(int[] memory, int n) {
		if (memory[n - 1] != 0)
			return memory[n - 1];
		return memory[n - 1] = fib(memory, n - 1) + fib(memory, n - 2);
	}
	
	public static void main(String[] args) {
		System.out.println(getNthFib(1));
	}

}
