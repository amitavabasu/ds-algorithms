package com.amit.alogs.hr.medium;

public class HR1NewYearChaos {

	public void minimumBribes(int[] q){
		/*
		 * get every element from end of the array q.
		 * check it's natural position that is element-1, make a decision how much it has shifted
		 * if the shift is more than 2 say "Too chaotic" else put that element in right place and increment the shift count or bribe count
		 */
		int bribeCount = 0;
		if(q==null || q.length < 2){
			System.out.println(bribeCount);
		}
		int qLength = q.length;
		for(int i=qLength-1; i>=0; i--){
			//System.out.println(Arrays.toString(q));
			int element = q[i];
			int j=i;
			while(element<i+1 && j>=0){
				j--;
				element = q[j];
			}
			int expectedElementIndex = element-1;
			int shiftOfThisElement = expectedElementIndex-j;
			if(shiftOfThisElement>2){//<-- if shift is more than 2 just return
				System.out.println("Too chaotic");
				return;
			}else{
				bribeCount = bribeCount+shiftOfThisElement;
				for(int k=0; k<shiftOfThisElement; k++){//<-- put the element in the right place, and shift all the elements
					int temp = q[j];
					q[j] = q[j+1];
					q[j+1] = temp;
					j++;
				}
			}
		}
		System.out.println(bribeCount);
	}
	
	public static void main(String[] args){
		int[] q = {2,1,5,3,4};
		//int[] q = {2, 5, 1, 3, 4};
		//int[] q = {2, 1, 5, 4, 3};
		//int[] q = {1, 2, 5, 3, 7, 8, 6, 4};
		HR1NewYearChaos sm = new HR1NewYearChaos();
		sm.minimumBribes(q);
	}
}
