package com.amit.alogs.hr.hard;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class HR9PoisonusPlants {
    static int poisonousPlants(int[] p) {
    	int dayCount = 0;//<-- initialize how many days after the plant will survive
    	if(p==null || p.length==0)
    		return dayCount;
    	List<Stack<Integer>> list = new LinkedList<>();//<-- use a linked list to hold a stack (a list again)
    	//preparation
    	int i=p.length-1;//<-- initialize i to the last index
    	while(i>=0){//<-- until first index reached
    		int j=i-1;//<-- initialize j to the element before i-th element
    		Stack<Integer> stack = new Stack<>();//<-- create a new stack
    		stack.push(p[i]);//<-- push the i-th element in the stack
    		while(j>=0 && p[j]>=p[i]){//<-- while j is not reached first element and previous element grater than equal to current
    			stack.push(p[j]);//<-- push that element in stack
    			j--;//<-- decrement both i & j 
    			i--;
    		}
    		list.add(stack);//<-- add this stack or list to the list
    		i=j;//<-- set i to j to traverse rest of the array
    	}
    	//FOR TEST ONLY
    	for(Stack<Integer> stack:list){
    		System.out.println(Arrays.toString(stack.toArray()));
    	}
    	while(list.size()>1){//<-- until there is only one stack left on the list
            Iterator<Stack<Integer>>  it = list.iterator();//<-- get the iterator of the list
            int count = list.size()-1;//<-- initialize count to the last index of the adday
            while(it.hasNext() && count>0){//<-- until iterator have more element and contains more than 1
                Stack<Integer> stack = it.next();//<-- get the stack from the list
                if(stack!=null && !stack.isEmpty()){//<-- if that stack is not empty pop out that topmost element
                    stack.pop();
                }
                if(stack==null || stack.isEmpty()){//<-- if after poping out the last element if the stack become empty or null, remove it from the outer list 
                    it.remove();
                }
                count--;//<-- decrement count
    		}
    		dayCount++;//<-- at this moment we completed one day
    		//FOR TEST ONLY
        	for(Stack<Integer> stack:list){
        		System.out.println(Arrays.toString(stack.toArray()));
        	}
    		//Now prepare for next day
    		//pickup last two stacks from the list, and see if they can be merged or not
        	it = list.iterator();//<-- get an iterator of the list
        	Stack<Integer> stack1 = null;//<-- stack one
        	if(it.hasNext()) {
        		stack1 = it.next();//<-- get stack one
        	}
    		while(it.hasNext()){//<-- when have more than one stack in the list
       			Stack<Integer> stack2 = it.next();//<-- pickup stack2
       			if(!stack2.isEmpty() && !stack1.isEmpty() && stack2.firstElement()>=stack1.peek()){//<-- when none of the stack are empty compare first element with the last element
       				//If stack2 first element is grater or equal to stack1 first element a a merge is possible.
   					stack1.addAll(stack2);//<-- merge the stack in a single statement
   					it.remove();//<-- now if a merge possible then iterator pointing to the second element and the and it got merged to the first element, so remove the second/current element using iterator
       			}else{
       				stack1 = stack2;//<-- if two list is not mergable, move stack1 to stack2 and next stack two will be picked up in next iteration 
       			}
    		}
    		//FOR TEST ONLY
        	for(Stack<Integer> stack:list){
        		System.out.println(stack==null?"null":Arrays.toString(stack.toArray()));
        	}
    	}
   	return dayCount;//<-- return the day count
    }
	public static void main(String[] args) throws IOException {
		//int[] p = {6, 5, 8, 7, 4, 7, 3, 1, 1, 10};
		//int[] p = {6, 5, 8, 4, 7, 10, 9};
		//int[] p = {4, 3, 7, 5, 6, 4, 2};
		int[] p = {20, 5, 6, 15, 2, 2, 17, 2, 11, 5, 14, 5, 10, 9, 19, 12, 5};
		System.out.println(poisonousPlants(p));
	}
}
