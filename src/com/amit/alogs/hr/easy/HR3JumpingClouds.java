package com.amit.alogs.hr.easy;

public class HR3JumpingClouds {

	public int countMinJump(int n, int[] c){
		int jump = 0;
		if(n<2 || c==null || c.length < 2){
			return 0;
		}
		if(n==2){
			return 1;
		}
		boolean done = false; 
		int i=0;
		while(!done){
			if(i>=n-1){
				done=true;
			}else{
				int nextVal = c[i+1];
				if(i+2 == n){
					jump++;
					i=n-1;
				}else{
					int nextToNextVal = c[i+2];
					if(nextVal==0 && nextToNextVal==0){
						jump++;
						i=i+2;
					}else{
						if(nextVal==0 && nextToNextVal==1){
							jump++;
							i=i+1;
						}
						if(nextVal==1 && nextToNextVal==0){
							jump++;
							i=i+2;
						}
					}
				}
			}
		}
		return jump;
	}
	
	public static void main(String[] args){
		int[] c = {0, 0, 1, 0, 0, 1, 0};
		//int[] c = {0, 0, 0, 0, 1, 0};
		int n = c.length;
		HR3JumpingClouds sm = new HR3JumpingClouds();
		System.out.println(sm.countMinJump(n, c));
	}
}
