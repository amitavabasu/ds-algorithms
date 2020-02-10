package com.amit.concurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.IntStream;

public class Java8Concurrency {
	public void stop(ExecutorService executor) {
		try {
			executor.shutdown();
			executor.awaitTermination(60, TimeUnit.SECONDS);
		}catch(InterruptedException e) {System.out.print("Thread interrupted");}
		finally {
			if(!executor.isTerminated()) {
				System.out.println("Killing non finished tasks");
			}
			executor.shutdownNow();
		}
	}
	public void sleep(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		}catch(InterruptedException e) {
			System.out.print("Thread interrupted");
		}
	}
	
	
	private int count = 0;
	public void increment() {
		count += 1;
	}
	public synchronized void syncIncrement() {
		count += 1;
	}
	
	ReentrantLock lock = new ReentrantLock();
	public void lockingIncrement() {
		lock.lock();
		try {
			count +=1;
		}catch(Exception e) {e.printStackTrace();}
		finally {
			lock.unlock();
		}
	}

	
	public void execute1() {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		count = 0;
		IntStream.range(0, 10000).forEach(i -> executor.submit(this::increment));
		stop(executor);
		System.out.println(count);
	}
	public void execute2() {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		count = 0;
		IntStream.range(0, 10000).forEach(i -> executor.submit(this::syncIncrement));
		stop(executor);
		System.out.println(count);
	}
	public void execute3() {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		count = 0;
		IntStream.range(0, 10000).forEach(i -> executor.submit(this::lockingIncrement));
		stop(executor);
		System.out.println(count);
	}
	
	public void execute4() {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		ReentrantLock localLock = new ReentrantLock();
		executor.submit(() -> {
			localLock.lock();
			try {
				sleep(2);
			}finally {
				localLock.unlock();
			}
		});
		
		executor.submit(() ->{
			System.out.println("IsLocked: "+localLock.isLocked());
			System.out.println("IsLocked By me: "+localLock.isHeldByCurrentThread());
			System.out.println("Try Lock to aquire: "+localLock.tryLock());
		});
		
		sleep(3);

		executor.submit(() ->{
			System.out.println("IsLocked: "+localLock.isLocked());
			if(!localLock.isLocked()) {
				localLock.lock();
			}
			System.out.println("IsLocked By me: "+localLock.isHeldByCurrentThread());
			System.out.println("Try Lock to aquire: "+localLock.tryLock());
		});
		stop(executor);
	}
	
	public void execute5() {
		Map<String, Double> producerMap = new HashMap<>();
		ExecutorService executor = Executors.newFixedThreadPool(4);
		ReadWriteLock rwLock = new ReentrantReadWriteLock();
		executor.submit(() -> {
			rwLock.writeLock().lock();
			try {
				sleep(2);
				producerMap.put("production1", Math.random());
			}finally {
				rwLock.writeLock().unlock();
			}
		});
		
		Runnable consumer = () ->{
			rwLock.readLock().lock();
			try {
				for(String s:producerMap.keySet()) {
					System.out.println(s+"==>"+producerMap.get(s));
				}
			}finally {
				rwLock.readLock().unlock();
			}
		};
		
		executor.submit(consumer);
		
		executor.submit(() -> {
			rwLock.writeLock().lock();
			try {
				sleep(2);
				producerMap.put("production2", Math.random());
			}finally {
				rwLock.writeLock().unlock();
			}
		});
		
		executor.submit(consumer);
		stop(executor);
	}
	
	public void execute6() {
		Map<String, Double> producerMap = new HashMap<>();
		ExecutorService executor = Executors.newFixedThreadPool(4);
		StampedLock sLock = new StampedLock();
		executor.submit(() -> {
			long stamp = sLock.writeLock();
			try {
				sleep(2);
				producerMap.put("production1", Math.random());
			}finally {
				sLock.unlockWrite(stamp);
			}
		});
		
		Runnable consumer = () ->{
			long stamp = sLock.readLock();
			try {
				for(String s:producerMap.keySet()) {
					System.out.println(s+"==>"+producerMap.get(s));
				}
			}finally {
				sLock.unlockRead(stamp);
			}
		};
		
		executor.submit(consumer);
		
		executor.submit(() -> {
			long stamp = sLock.writeLock();
			try {
				sleep(2);
				producerMap.put("production2", Math.random());
			}finally {
				sLock.unlockWrite(stamp);
			}
		});
		
		executor.submit(consumer);
		stop(executor);
	}
	
	public void execute7() {
		Map<String, Double> producerMap = new HashMap<>();
		ExecutorService executor = Executors.newFixedThreadPool(4);
		StampedLock sLock = new StampedLock();
		
		Runnable consumer = () ->{
			long stamp = sLock.tryOptimisticRead();
			try {
				System.out.println("Optimistic Lock Valid: "+sLock.validate(stamp));
				if(sLock.validate(stamp)) {
					for(String s:producerMap.keySet()) {
						System.out.println(s+"==>"+producerMap.get(s));
					}
				}
				sleep(2);
				System.out.println("Optimistic Lock Valid: "+sLock.validate(stamp));
				if(sLock.validate(stamp)) {
					for(String s:producerMap.keySet()) {
						System.out.println(s+"==>"+producerMap.get(s));
					}
				}
				sleep(2);
				System.out.println("Optimistic Lock Valid: "+sLock.validate(stamp));
				if(sLock.validate(stamp)) {
					for(String s:producerMap.keySet()) {
						System.out.println(s+"==>"+producerMap.get(s));
					}
				}
			}finally {
				sLock.unlock(stamp);
			}
		};
		
		executor.submit(consumer);
		
		
		executor.submit(() -> {
			long stamp = sLock.writeLock();
			try {
				sleep(1);
				producerMap.put("production1", Math.random());
			}finally {
				sLock.unlockWrite(stamp);
			}
		});
		
		executor.submit(() -> {
			long stamp = sLock.writeLock();
			try {
				sleep(1);
				producerMap.put("production2", Math.random());
			}finally {
				sLock.unlockWrite(stamp);
			}
		});
		sleep(3);
		executor.submit(consumer);
		stop(executor);
	}
	
	public static void main(String[] args) {
		Java8Concurrency app = new Java8Concurrency();
//		app.execute1();
//		app.execute2();
//		app.execute3();
//		app.execute4();
//		app.execute5();
//		app.execute6();
//		app.execute7();
	}
	
}
