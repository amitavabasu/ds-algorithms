package com.amit.alogs.hr.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HR20BetweenTwoSets {
	
	public static int getTotalX(List<Integer> a, List<Integer> b) {
		int count = 0;
		if(a==null || b==null || a.size()==0 || b.size() == 0){
			return count;
		}
		int n = a.size();
		int m = b.size();
		Collections.sort(a);
		Collections.sort(b);
		int min = a.get(n-1);
		int max = b.get(m-1);
		int num = min;
		while(num<=max){
			boolean firstCond = true;
			for(int i=0; i<n; i++){
				if(num%a.get(i)!=0){
					firstCond = false;
					break;
				}
			}
			if(firstCond){
				boolean secondCond = true;
				//The num is a prospective one, proceed with next check
				for(int j=0; j<m; j++){
					if(b.get(j)%num!=0){
						secondCond = false;
						break;
					}
				}
				if(secondCond){
					System.out.println("------>"+num);
					count++;
				}
			}
			//Generate next num add min to it
			num += min;
		}
		return count;
	}
	public static void main(String[] args){
		List<Integer> a = new ArrayList<>(Arrays.asList(3,4));
		List<Integer> b = new ArrayList<>(Arrays.asList(24,48));
		System.out.println(getTotalX(a, b));
	}
}
