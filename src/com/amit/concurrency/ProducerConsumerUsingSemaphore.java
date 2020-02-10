package com.amit.concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;


public class ProducerConsumerUsingSemaphore {
	private Queue<Character> buffer;
	private Semaphore wSemaphore;
	private Semaphore rSemaphore;
	
	public void execute(int BUFFER_SIZE) {
		buffer = new LinkedList<Character>();
		wSemaphore = new Semaphore(BUFFER_SIZE);
		rSemaphore = new Semaphore(0);
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.submit(() -> {
			for(int i=0; i<26; i++) {
				wSemaphore.acquireUninterruptibly();
				char c = (char)('A'+i);
				System.out.println("Producing " + c + " ...");
				buffer.add(c);
				rSemaphore.release();
			}
		});
		
		executor.submit(() -> {
			for(int i=0; i<26; i++) {
				rSemaphore.acquireUninterruptibly();
				char c = buffer.remove();
				System.out.println("\tConsuming " + c + " ...");
				wSemaphore.release();
			}
		});
		
	}
	

	public static void main(String[] args) {
		ProducerConsumerUsingSemaphore prodCon = new ProducerConsumerUsingSemaphore();
		prodCon.execute(4);
	}
}

