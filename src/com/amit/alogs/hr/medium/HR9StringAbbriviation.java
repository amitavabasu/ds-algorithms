package com.amit.alogs.hr.medium;

public class HR9StringAbbriviation {
	
	/*
	 * find two strings if they are abbreviation of each other or not
	 * an abbreviation means
	 * 	1) delete a char from a 
	 *  2) upper case a char from a 
	 */

	public static String stringAbbriviation(String a, String b) {
		boolean[][] memory = new boolean[a.length()+1][b.length()+1];//<-- create this DP 2D array to hold each char match of both the string
		memory[0][0] = true;//<-- null characters are a match
		boolean upperExists = false;
		for(int i=0; i<a.length(); i++) {//<-- for the first row of the memory
			char c = a.charAt(i);
			if(Character.isUpperCase(c)||upperExists) {
				memory[i+1][0]=false;//<-- set to false if upper case exists
				upperExists = true;
			}else {
				memory[i+1][0] = true;//<-- else set to true
			}
		}
		for(int row=1; row<=a.length(); row++) {//<-- the entire length of a
			int i=row-1;
			for(int col=1; col<=b.length(); col++) {//<-- for entire length of b
				int j = col-1;
				if(a.charAt(i)==b.charAt(j)) {//<-- if the characters are equal
					memory[row][col] = memory[row-1][col-1];//<-- set the previous row col value.
				}else if(Character.isUpperCase(a.charAt(i))) {//<-- if the char is already in upper case and they are not equal
					memory[row][col] = false;//<-- set it as false
				}else if(Character.toUpperCase(a.charAt(i))==b.charAt(j)) {//<-- if upper cases match
					memory[row][col] = memory[row-1][col-1] || memory[row-1][col];//<-- set previous row col value or previous row same col cell which ever is true
				}else {
					memory[row][col] = memory[row-1][col];//<-- else set the value of previous row and same col value
				}
			}
		}
		return memory[a.length()][b.length()]?"YES":"NO";//<-- send YES or NO, based on last cell value
	}
	
	public static void main(String[] args) {
		String a = "daBcd";
		String b = "ABCE";
		System.out.println(stringAbbriviation(a,b));
		
	}
	
	 
}