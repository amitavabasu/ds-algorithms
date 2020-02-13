package com.amit.tests;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class Test5SortAndMerge {

	public static void sortAndMerge(Integer[][] data) {
		if(data==null || data.length==0) {
			return;
		}
		Arrays.sort(data, new Comparator<Integer[]>() {
			@Override
			public int compare(Integer[] arr1, Integer[] arr2) {
				return arr1[0].compareTo(arr2[0]);
			}
		});
		for(int i=0;i<data.length; i++) {
			System.out.println(Arrays.toString(data[i]));
		}
		Stack<Integer[]> stack = new Stack<>();
		stack.push(data[0]);
		for(int i=1; i<data.length; i++) {
			Integer[] previous = stack.pop();
			Integer[] current = data[i];
			if(previous[1]>=current[0]) {
				//Orverlap
				previous[1] = current[1];
				stack.push(previous);
			}else {
				//No overlap
				stack.push(previous);
				stack.push(current);
			}
		}
		Stack<Integer[]> result = new Stack<>();
		while(!stack.isEmpty()) {
			result.push(stack.pop());
			
		}
		while(!result.isEmpty()) {
			Integer[] line = result.pop();
			System.out.println(line[0]+" - "+line[1]);
		}
	}
	
	public static void main(String[] args) {
		Integer[][] data = {{1,4},{6,7},{3,5},{7,8},{8,9}, {2,3}};
		sortAndMerge(data);
	}
	
}
