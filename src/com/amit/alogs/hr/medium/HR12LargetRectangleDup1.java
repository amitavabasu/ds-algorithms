package com.amit.alogs.hr.medium;

import java.util.Arrays;
import java.util.Stack;

public class HR12LargetRectangleDup1 {
	
	public static long largestRectangle(int[] arr) {
		if(arr==null || arr.length<1)
			return 0;
		long maxRect = Long.MIN_VALUE;
		long[] left = new long[arr.length];
		Arrays.fill(left, -1);
		long[] right = new long[arr.length];
		Arrays.fill(right, arr.length);
		Stack<Integer> stack = new Stack<>();
		for(int i=0;i<arr.length; i++) {
			while(!stack.empty() && arr[i]<=arr[stack.peek()]) {
				stack.pop();
			}
			if(!stack.empty()) {
				left[i] = stack.peek();
			}
			stack.push(i);
		}
		stack.clear();
		for(int i=arr.length-1;i>=0; i--) {
			while(!stack.empty() && arr[i]<=arr[stack.peek()]) {
				stack.pop();
			}
			if(!stack.empty()) {
				right[i] = stack.peek();
			}
			stack.push(i);
		}
		for(int i=0; i<arr.length; i++) {
			long rectSize = (right[i] - left[i] - 1) * arr[i];
			if(rectSize > maxRect) {
				maxRect = rectSize;
			}
		}
		return maxRect;
	}
}