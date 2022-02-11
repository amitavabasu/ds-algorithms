package com.amit.concurrency;

import java.util.concurrent.TimeUnit;

class Storage {
    private char [] buffer;
    private int count = 0, in = 0, out = 0;

    Storage(int size)
    {
         buffer = new char[size];
    }

    public synchronized void put(char c) {
    	while(count == buffer.length) {
    		try {
    			this.wait();
    		}catch (InterruptedException ie){}finally {}
    	}
        System.out.println("Producing " + c + " ...");
        buffer[in] = c;
        in = (in +1) % buffer.length;
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
         out = (out +1) % buffer.length;
         count--;
         System.out.println("Consuming " + c + " ...");
         notify();
         return c;
    }
}

	class ProducerInner extends Thread {
	   private Storage buffer;
	     
	   ProducerInner(Storage b) { buffer = b; }
	   public void run() {
	     for(int i = 0; i < 26; i++) {
	        buffer.put((char)('A'+ i%26 ));
	        try {
	        	TimeUnit.SECONDS.sleep(1);
	        }catch(InterruptedException e) {}
	     }
	   }
	}    

	class ConsumerInner extends Thread {
	   private Storage buffer;
	   
	   ConsumerInner(Storage b) { buffer = b; }
	   public void run() {
	     for(int i = 0; i < 26; i++) {
	        buffer.get();
	        try {
	        	TimeUnit.SECONDS.sleep(1);
	        }catch(InterruptedException e) {}
	        
	     }
	   }
	}


     

public class ProducerConsumer2
{
  public static void main(String [] args)
  {
	     Storage b = new Storage(4);
	     ProducerInner p = new ProducerInner(b);
	     ConsumerInner c = new ConsumerInner(b);
	     c.start();
	     p.start();
  }
}

