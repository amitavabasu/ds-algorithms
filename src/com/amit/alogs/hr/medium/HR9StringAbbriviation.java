package com.amit.alogs.hr.medium;

public class HR9StringAbbriviation {

	public static String stringAbbriviation(String a, String b) {
		boolean[][] memory = new boolean[a.length()+1][b.length()+1];
		memory[0][0] = true;
		boolean upperExists = false;
		for(int i=0; i<a.length(); i++) {
			char c = a.charAt(i);
			if(Character.isUpperCase(c)||upperExists) {
				memory[i+1][0]=false;
				upperExists = false;
			}else {
				memory[i+1][0] = true;
			}
		}
		for(int row=1; row<=a.length(); row++) {
			int i=row-1;
			for(int col=1; col<=b.length(); col++) {
				int j = col-1;
				if(a.charAt(i)==b.charAt(j)) {
					memory[row][col] = memory[row-1][col-1];
				}else if(Character.isUpperCase(a.charAt(i))) {
					memory[row][col] = false;
				}else if(Character.toUpperCase(a.charAt(i))==b.charAt(j)) {
					memory[row][col] = memory[row-1][col-1] || memory[row-1][col];
				}else {
					memory[row][col] = memory[row-1][col];
				}
			}
		}
		return memory[a.length()][b.length()]?"YES":"NO";
	}
	
	public static void main(String[] args) {
		String a = "daBcd";
		String b = "ABCE";
		System.out.println(stringAbbriviation(a,b));
		
	}
	
	 
}