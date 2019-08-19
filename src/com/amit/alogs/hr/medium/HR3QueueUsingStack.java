package com.amit.alogs.hr.medium;

import java.util.Stack;

public class HR3QueueUsingStack {
	/*
	 * Implement a queue using two stacks
	 */
	
	private static class MyQueue<T> {
		 /*
		  * Always push in first stack. while dequeu and peek if the second queue is empty or not.
		  * If not empty pop or peek from second stack.
		  * If empty pop all from first stack and push all to second stack and pop or peek from second stack
		  */
		Stack<T> stack1 = new Stack<>();
		Stack<T> stack2 = new Stack<>();
		public void enqueue(T item){
				stack1.push(item);
		}
		
		public T dequeue(){
			if(stack2.empty()){
				while(!stack1.empty()){
					stack2.push(stack1.pop());
				}
				return stack2.pop();
			}else{
				return stack2.pop();
			}
		}
		
		public T peek(){
			if(stack2.empty()){
				while(!stack1.empty()){
					stack2.push(stack1.pop());
				}
				return stack2.peek();
			}else{
				return stack2.peek();
			}
		}
	}
	
	public static void main(String[] args){
		MyQueue<Integer> queue = new MyQueue<Integer>();
		queue.enqueue(42);
		queue.dequeue();
		queue.enqueue(14);
		System.out.println(queue.peek());
		queue.enqueue(28);
		System.out.println(queue.peek());
		queue.enqueue(60);
		queue.enqueue(78);
		queue.dequeue();
		queue.dequeue();
	}
}
