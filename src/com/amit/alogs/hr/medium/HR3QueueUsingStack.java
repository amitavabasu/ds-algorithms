package com.amit.alogs.hr.medium;

import java.util.Stack;

public class HR3QueueUsingStack {
	
//	private static class Node<T> {
//		public T value;
//		public Node previous;
//		public Node next;
//		public Node(T input) {
//			value = input;
//		}
//	}
	
//	private static class MyQueue<T> {
//		Node first = null;
//		Node last = null;
//		public void enqueue(T item){
//			Node node = new Node(item);
//			if(first==null) {
//				first = node;
//			}else {
//				first.previous = node;
//				node.next = first;
//				first = node;
//			}
//			if(last==null) {
//				last = node;
//			}
//		}
//		public T dequeue(){
//			if(last==null) {
//				return null;
//			} else {
//				T value = (T)last.value;
//				last = last.previous;
//				if(last!=null) {
//					last.next = null;
//				}
//				return value;
//			}
//		}
//		
//		public T peek(){
//			if(last==null) {
//				return null;
//			} else {
//				T value = (T)last.value;
//				return value;
//			}
//		}
//	}

	
	private static class MyQueue<T> {
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
