package com.amit.alogs.hr.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HR17DiagolanSumOf2DList {
	
    public static int diagonalDifference(List<List<Integer>> arr) {
    if(arr==null || arr.size()==0){
        return 0;
    }
    int n = arr.size();
    int s1 = 0;
    int s2 = 0;
    for(int i=0, j=0; i<n; i++,j++){
        s1 += arr.get(i).get(j);
        s2 += arr.get(i).get(n-j-1);
    }
    return Math.abs(s1-s2);
    }
	
	public static void main(String[] args){
		List<List<Integer>> arr = new ArrayList<List<Integer>>();
		arr.add(new ArrayList<Integer>(Arrays.asList(11,2,4))); 
		arr.add(new ArrayList<Integer>(Arrays.asList(4,5,6)));
		arr.add(new ArrayList<Integer>(Arrays.asList(10,8,-12)));
		System.out.println(diagonalDifference(arr));
	}
}
