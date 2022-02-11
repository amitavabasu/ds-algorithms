package com.amit.concurrency;

import java.util.concurrent.TimeUnit;

class Buffer {
    private char [] buffer;
    private int count = 0, in = 0, out = 0;

    Buffer(int size)
    {
         buffer = new char[size];
    }

    public synchronized void put(char c) {
         while(count == buffer.length) 
         {
              try { wait(); }
              catch (InterruptedException e) { } 
              finally { } 
         } 
         System.out.println("Producing " + c + " ...");
         buffer[in] = c; 
         in = (in + 1) % buffer.length; 
         count++; 
         notify(); 
    }

    public synchronized char get() {
         while (count == 0) 
         {
              try { wait(); }
              catch (InterruptedException e) { } 
              finally { } 
         } 
         char c = buffer[out]; 
         out = (out + 1) % buffer.length;
         count--;
         System.out.println("Consuming " + c + " ..."); 
         notify(); 
         return c;
    }
}
     

class Producer extends Thread {
   private Buffer buffer;
     
   Producer(Buffer b) { buffer = b; }
   public void run() {
     for(int i = 0; i < 26; i++) {
        buffer.put((char)('A'+ i%26 ));
        try {
        	TimeUnit.SECONDS.sleep(1);
        }catch(InterruptedException e) {}
     }
   }
}    

class Consumer extends Thread {
   private Buffer buffer;
   
   Consumer(Buffer b) { buffer = b; }
   public void run() {
     for(int i = 0; i < 26; i++) {
        buffer.get();
        try {
        	TimeUnit.SECONDS.sleep(1);
        }catch(InterruptedException e) {}
     }
   }
}

public class ProducerConsumer
{
  public static void main(String [] args)
  {
     Buffer b = new Buffer(4);
     Producer p = new Producer(b);
     Consumer c = new Consumer(b);
     Producer p2 = new Producer(b);
     Consumer c2 = new Consumer(b);
     p.start();
     c.start();
     p2.start();
     c2.start();
  }
}

