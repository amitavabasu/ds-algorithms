package com.amit.alogs.hr.hard;



import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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
    	System.out.println("------------------------------");
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
        	System.out.println("------------------------------");
    		//Prepare for next day
    		//pickup last two stacks from the list
        	i=0;
    		while(i<list.size()-1 && list.size()>1){
       			Stack<Integer> stack1 = list.get(i);
       			Stack<Integer> stack2 = list.get(i+1);
       			if(stack2.lastElement()>=stack1.peek()){
       				//a merge is possible
       				for(int x=0; x<stack2.size(); x++){
       					stack1.push(stack2.get(x));
       				}
       				//set stack 2 as null
       				list.set(i+1, stack1);
       				list.set(i, null);
       				i++;
       			}else{
       				i++;
       			}
            	for(Stack<Integer> stack:list){
            		System.out.println(stack==null?"null":Arrays.toString(stack.toArray()));
            	}
            	System.out.println("------------------------------");
    		}
    	}
    	for(Stack<Integer> stack:list){
    		System.out.println(Arrays.toString(stack.toArray()));
    	}
    	System.out.println("------------------------------");
   	return dayCount;
    }
	public static void main(String[] args) throws IOException {
		int[] p = {6, 5, 8, 7, 4, 7, 3, 1, 1, 10};
		System.out.println(poisonousPlants(p));
	}
}
