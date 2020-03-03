package com.amit.alogs.hr.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SumOfFourNumbers {
	
	 
	
	public static List<Integer[]> fourNumberSum(int[] array, int targetSum) {
		List<Integer[]> result = new ArrayList<>();
		Map<Integer,List<Integer[]>> map = new HashMap<>();
		for(int i=0; i<array.length-1; i++) {
			for(int j=i+1; j<array.length; j++) {
				int forward = array[i]+array[j];
				if(map.get(targetSum-forward)!=null) {
					List<Integer[]> mapPart = map.get(targetSum-forward);
					for(Integer[] part:mapPart) {
						result.add(new Integer[] {part[0],part[1],array[i],array[j]});
					}
				}else {
					//Do nothing
				}
			}
			for(int j=0; j<i; j++) {
				int backward = array[j]+array[i];
				List<Integer[]> mapPart = map.get(backward);
				if(mapPart==null) {
					Integer[] part = {array[j],array[i]};
					List<Integer[]> partList = new ArrayList<Integer[]>();
					partList.add(part);
					map.put(backward, partList);
				}else {
					mapPart.add(new Integer[] {array[j],array[i]});
				}
			}
		}
		return result;
	}	  
	 
	public static void main(String[] args) {
		int[] data = {7,6,4,-1,1,2};
		int targetSum = 16;
		List<Integer[]> result = fourNumberSum(data, targetSum);
		for(Integer[] res:result) {
			System.out.println(Arrays.toString(res));
		}
	}
}
