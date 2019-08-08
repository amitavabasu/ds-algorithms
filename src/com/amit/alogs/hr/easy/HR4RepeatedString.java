package com.amit.alogs.hr.easy;

public class HR4RepeatedString {

	public long countaInRepeatedString(String s, long n) {
		long count = 0;
		if(s==null || s.length()==0 || n==0){
			return count;
		}
		long length = s.length();
		long replacedlength = s.replace("a", "").length();
		long howManya = length - replacedlength;
		if(howManya==0){
			return 0;
		}
		long howManyOccurances = n/length;
		if(howManyOccurances==0){
			
		}
		long howManyRemindera = 0;
		long reminderLength = n%length;
		if(reminderLength!=0){
			String substring = s.substring(0, new Long(reminderLength).intValue());
			howManyRemindera = substring.length() - substring.replace("a", "").length();
		}else{
			
		}
		count = howManyOccurances*howManya+howManyRemindera;
		return count;
	}
	
	public static void main(String[] args){
//		String s = "aba";
//		long n = 10;
//		String s = "a";
//		long n = 1000000000000l;
		String s = "abcac";
		long n = 10l;
		HR4RepeatedString sm = new HR4RepeatedString();
		System.out.println(sm.countaInRepeatedString(s, n));
	}
}
