package com.amit.alogs.hr.hard;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class HR9PoisonusPlants {
    static int poisonousPlants(int[] p) {
    	int dayCount = 0;
    	if(p==null || p.length==0)
    		return dayCount;
    	List<Stack<Integer>> list = new LinkedList<>();
    	//preparation
    	int i=p.length-1;
    	while(i>=0){
    		int j=i-1;
    		Stack<Integer> stack = new Stack<>();
    		stack.push(p[i]);
    		while(j>=0 && p[j]>=p[i]){
    			stack.push(p[j]);
    			j--;
    			i--;
    		}
    		list.add(stack);
    		i=j;
    	}
    	for(Stack<Integer> stack:list){
    		System.out.println(Arrays.toString(stack.toArray()));
    	}
    	System.out.println("------------------------------1");
    	while(list.size()>1){
            Iterator<Stack<Integer>>  it = list.iterator();
            int count = list.size()-1;
            while(it.hasNext() && count>0){
                Stack<Integer> stack = it.next();
                if(stack!=null && !stack.isEmpty()){
                    stack.pop();
                }
                if(stack==null || stack.isEmpty()){
                    it.remove();
                }
                count--;
    		}
    		dayCount++;
        	for(Stack<Integer> stack:list){
        		System.out.println(Arrays.toString(stack.toArray()));
        	}
        	System.out.println("------------------------------2");
    		//Prepare for next day
    		//pickup last two stacks from the list
        	it = list.iterator();
        	Stack<Integer> stack1 = null;
        	if(it.hasNext()) {
        		stack1 = it.next();
        	}
    		while(it.hasNext()){
       			Stack<Integer> stack2 = it.next();
       			if(!stack2.isEmpty() && !stack1.isEmpty() && stack2.firstElement()>=stack1.peek()){
       				//a merge is possible
   					stack1.addAll(stack2);
       				//stack2.addAll(0,stack1);
   					it.remove();
       				//set stack 2 as null
//       				list.set(i+1, stack1);
//       				list.set(i, null);
//       				i++;
       			}else{
       				stack1 = stack2;
       			}
    		}
        	for(Stack<Integer> stack:list){
        		System.out.println(stack==null?"null":Arrays.toString(stack.toArray()));
        	}
        	System.out.println("------------------------------3");
    		it = list.iterator();
//    		while(it.hasNext()) {
//    			Stack<Integer> temp = it.next();
//    			if(temp==null || temp.size()==0) {
//    				it.remove();
//    			}
//    		}
        	for(Stack<Integer> stack:list){
        		System.out.println(Arrays.toString(stack.toArray()));
        	}
        	System.out.println("------------------------------4");
    	}
    	for(Stack<Integer> stack:list){
    		System.out.println(Arrays.toString(stack.toArray()));
    	}
    	System.out.println("------------------------------5");
   	return dayCount;
    }
	public static void main(String[] args) throws IOException {
		//int[] p = {6, 5, 8, 7, 4, 7, 3, 1, 1, 10};
		//int[] p = {6, 5, 8, 4, 7, 10, 9};
		//int[] p = {4, 3, 7, 5, 6, 4, 2};
		int[] p = {20, 5, 6, 15, 2, 2, 17, 2, 11, 5, 14, 5, 10, 9, 19, 12, 5};
		System.out.println(poisonousPlants(p));
	}
}
