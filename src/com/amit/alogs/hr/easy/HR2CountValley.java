package com.amit.alogs.hr.easy;

public class HR2CountValley {

	public int countValleys(int n, String s){
		int valleyCount = 0;
		int mountainCount = 0;
		int currentPosition = 0;
		int previousPosition = 0;
		if(n<=1 || s==null || s.length()<=1){
			return valleyCount;
		}
		for(int i=0; i<=n-1; i++){
			char path = s.charAt(i);
			switch(path){
			case 'U':currentPosition++;
				break;
			case 'D':currentPosition--;
				break;
			default: 
				break;
			}
			if(currentPosition==0){
				if(previousPosition==-1){
					valleyCount++;
				}else if (previousPosition==1){
					mountainCount++;
				}else{
					//previous position could be at 0 so skip it
				}
			}
			previousPosition = currentPosition;
		}
		System.out.println("ValleyCount: "+valleyCount+"; MountainCount: "+mountainCount);
		return valleyCount;
	}
	
	public static void main(String[] args){
		String s = "UDDDUDUUUUUDDDDDDUUUUUUUUU";
		int n = s.length();
		HR2CountValley sm = new HR2CountValley();
		System.out.println(sm.countValleys(n, s));
	}
}
