package com.amit.concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


public class ProducerConsumerUsingSemaphore {
	private Queue<Character> buffer;
	private Semaphore wSemaphore;
	private Semaphore rSemaphore;
	
	public ProducerConsumerUsingSemaphore(int size) {
		buffer = new LinkedList<Character>();
		wSemaphore = new Semaphore(size);
		rSemaphore = new Semaphore(0);
	}
	
	public void execute() {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.submit(() -> {
			for(int i=0; i<26; i++) {
				try {
					wSemaphore.acquireUninterruptibly();
					char c = (char)('A'+i);
					System.out.println("Producing " + c + " ...");
					buffer.add(c);
				}finally {
					rSemaphore.release();					
				}
			}
		});
		
		executor.submit(() -> {
			for(int i=0; i<26; i++) {
				try {
					rSemaphore.acquireUninterruptibly();
					char c = buffer.remove();
					System.out.println("\tConsuming " + c + " ...");
				}finally {
					wSemaphore.release();
				}
			}
		});
		
	}
	

	public static void main(String[] args) {
		ProducerConsumerUsingSemaphore prodCon = new ProducerConsumerUsingSemaphore(4);
		prodCon.execute();
	}
}

