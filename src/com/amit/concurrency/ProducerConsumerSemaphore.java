package com.amit.concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class ProducerConsumerSemaphore {

		private static final int BUFFER_SIZE = 4;
		private final Queue<Character> buffer = new LinkedList<>();
		private final Semaphore writePermits = new Semaphore(BUFFER_SIZE);
		private final Semaphore readPermits = new Semaphore(0);

		class Producer implements Runnable {
		    @Override
		    public void run() {
		        for(int i=0; i<26; i++) {
		        	try {
			            writePermits.acquireUninterruptibly();
			            char c = (char)('A'+i); 
			            System.out.println("Producing: "+c);
			            buffer.add(c);
		        	}finally {
		        		readPermits.release();
		        	}
		        }
		    }
		}

		class Consumer implements Runnable {
		    @Override
		    public void run() {
		        for(int i=0; i<26; i++) {
		        	try {
			            readPermits.acquireUninterruptibly();
			            char c = buffer.remove();
			            System.out.println("\tConsuming: "+c);
		        	}finally {
		        		writePermits.release();
		        	}
		        }
		    }
		}

		public static void main(String[] args) {

		    ProducerConsumerSemaphore obj = new ProducerConsumerSemaphore();
		    Producer p1 = obj.new Producer();
//		    Producer p2 = obj.new Producer();
//		    Producer p3 = obj.new Producer();
		    Consumer c1 = obj.new Consumer();
//		    Consumer c2 = obj.new Consumer();
//		    Consumer c3 = obj.new Consumer();
		    Thread t1 = new Thread(p1);
//		    Thread t2 = new Thread(p2);
//		    Thread t3 = new Thread(p3);
		    Thread t4 = new Thread(c1);
//		    Thread t5 = new Thread(c2);
//		    Thread t6 = new Thread(c3);
		    t1.start();
//		    t2.start();
//		    t3.start();
		    t4.start();
//		    t5.start();
//		    t6.start();
		}
}
