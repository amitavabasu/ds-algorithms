package com.amit.alogs.hr.medium;

import java.util.Arrays;
import java.util.Stack;

public class HR12LargetRectangle {
	/*
	 * get the largest rectangle size given an array of heights h[]
	 * Ex1: 1,2,3,4,5 ==> largest rectangle = 9 (3h x 3unit = 9)
	 * Ex2: 5,4,3,2,1 ==> largest rectangle = 9 (3h x 3unit = 9)
	 * Ex3: 20,25,20,20,5 ==> largest rectangle = 80 (20h x 4unit = 80)
	 * Ex4: 20,25,20,20,19 ==> largest rectangle = 95 (19h x 5unit = 95)
	 */
	static long largestRectangle(int[] h){
		long largestRect = 0l;
		if(h==null||h.length==0){
			return largestRect;
		}
		if(h.length==1){
			return h[0];
		}
		//create two array to store the index of the next minimum height of the current 
		//element and previous minimum height of the current element 
		int[] right = new int[h.length];
		int[] left = new int[h.length];
		//Initialize all right array element to length of the h array and left array element to -1
		for(int i=0; i<right.length; i++){
			right[i] = right.length;
			left[i] = -1;
		}
		//use a stack to calculate next & previous minimum element of the current element
		Stack<Integer> stack = new Stack<>();
		for(int i=0; i<h.length; i++){//<-- iterate over entire array
			while(!stack.empty() && h[i] <= h[stack.peek()]){//<-- if current height is less than or equal to 
															 //previously stored index (to the left) of current element pop because 
															 //previously stored element is not the minimum element  
				//right[stack.peek()] = i;
				stack.pop();
			}
			if(!stack.empty())
				left[i] = stack.peek();//<-- if stack is not empty this means we found the index of an 
									   //element which is less than current element previously stored so put that in left array because we are now traversing left to right
			stack.push(i);//<-- always push the current index to be compared
		}
		stack.clear();
		for(int i=h.length-1; i>=0; i--){
			while(!stack.empty() && h[i] <= h[stack.peek()]){//<-- if current height is less than or equal to 
				 											 //previously stored index (to the right) of current element then pop because 
				 											 //previously stored element is not the minimum element
				stack.pop();
			}
			if(!stack.empty())
				right[i] = stack.peek();//<-- if stack is not empty we found the index of an
										//element which is less than current element so put that index in right array because now we are traversing right to left
			stack.push(i);
		}
		System.out.println("right: "+Arrays.toString(right));
		System.out.println("left: "+Arrays.toString(left));
		
		//now go over the array h calculating rectangle-sizes and find the maximum
		int[] maxCompArray = new int[h.length]; 
		int[] unitArray = new int[h.length];
		for(int i=0; i<h.length; i++){
			int unitSize = right[i]-left[i]-1;
			unitArray[i] = unitSize;
			maxCompArray[i] = h[i]*unitSize;
			largestRect = Math.max(largestRect, h[i]*unitSize);
		}
		System.out.println("unit comparison: "+Arrays.toString(unitArray));
		System.out.println("max comparison: "+Arrays.toString(maxCompArray));
		return largestRect;
	}
	
	static long largestRectangleExp(int[] h) {
	    Stack<Integer[]> stack = new Stack<>();
	    h = Arrays.copyOf(h, h.length+1);
	    int j;
	    int maximum = 0;
	    int[] right = new int[h.length-1];
	    int[] left = new int[h.length-1];
	    String[] unitArray = new String[h.length-1];
	    String[] maxCompArray = new String[h.length-1];
	    
	    for(int i = 0; i < h.length; i++) {
	    	j = i;
	        while(!stack.isEmpty()) {
		    	Integer[] topPrevRangeIndexes = stack.peek();
		    	int prevRangeStart = topPrevRangeIndexes[0];
		    	int prevRangeEnd = topPrevRangeIndexes[1];
		    	if(h[i] <= h[prevRangeStart]){
		            j = prevRangeEnd;
		            //right[prevRangeStart] = i;
		            left[prevRangeStart] = j-1;
		            if(unitArray[prevRangeStart]==null) {
		            	unitArray[prevRangeStart] = i-j+"";
		            }else {
		            	unitArray[prevRangeStart] = unitArray[prevRangeStart]+","+(i-j)+"";
		            }
		            if(maxCompArray[prevRangeStart]==null) {
		            	maxCompArray[prevRangeStart] = (h[prevRangeStart] * (i - j))+"";
		            }else {
		            	maxCompArray[prevRangeStart] = maxCompArray[prevRangeStart]+","+(h[prevRangeStart] * (i - j))+"";
		            }
		            maximum = Math.max(maximum, h[prevRangeStart] * (i - j));
		            //System.out.println("popping: "+prevRangeStart+"-"+prevRangeEnd+"; h["+i+"]="+h[i]+"; probable: "+h[prevRangeStart] * (i - j)+"; max="+maximum);
		            stack.pop();
//		    	}else if(h[i] == h[prevRangeStart]){
//		            j = prevRangeEnd;
//		            left[prevRangeStart] = j-1;
//		            stack.pop();
		    	}else {
		    		break;
		    	}
	        }
	        if(!stack.empty())
	        	right[i] = stack.peek()[0];
	        //System.out.println("pushing: "+i+"-"+j);
	        stack.push(new Integer[]{i,j});
	    }
		System.out.println("right: "+Arrays.toString(right));
		System.out.println("left: "+Arrays.toString(left));
		System.out.println("unit comparison: "+Arrays.toString(unitArray));
		System.out.println("max comparison: "+Arrays.toString(maxCompArray));
	    return maximum;
	}
	
	
	static long largestRectangleBetter(int[] h) {
	    Stack<Integer[]> stack = new Stack<>();
	    h = Arrays.copyOf(h, h.length+1);
	    int j;
	    int maximum = 0;
	    for(int i = 0; i < h.length; i++) {
	        for(j = i; !stack.isEmpty();) {
		    	Integer[] topPrevRangeIndexes = stack.peek();
		    	int prevRangeStart = topPrevRangeIndexes[0];
		    	int prevRangeEnd = topPrevRangeIndexes[1];
		    	if(h[i] < h[prevRangeStart]){
		            j = prevRangeEnd;
		            maximum = Math.max(maximum, h[prevRangeStart] * (i - j));
		            //System.out.println("popping: "+prevRangeStart+"-"+prevRangeEnd+"; h["+i+"]="+h[i]+"; probable: "+h[prevRangeStart] * (i - j)+"; max="+maximum);
		            stack.pop();
		    	}else{
		    		break;
		    	}
	        }
	        //System.out.println("pushing: "+i+"-"+j);
	        stack.push(new Integer[]{i,j});
	    }
	    
	    return maximum;
	}
	
	public static void main(String[] args) {
		//int[] h = {5,4,3,2,1};
		//int[] h = {1,2,3,4,5};
		//int[] h = {8979, 4570, 6436, 5083, 7780, 3269, 5400, 7579, 2324, 2116};
		//int[] h = {1,2,3,4,5,4,6,2,1};
		int[] h = {20,25,20,20,20,19};
		//int[] h = {20,25,20,20,5};
		System.out.println(Arrays.toString(h));
		System.out.println(largestRectangle(h));
		System.out.println(largestRectangleExp(h));
	}
}