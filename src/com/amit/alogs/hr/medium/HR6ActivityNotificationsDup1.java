package com.amit.alogs.hr.medium;

import java.util.LinkedList;
import java.util.Queue;

import javax.print.attribute.standard.Media;

public class HR6ActivityNotificationsDup1 {
    static int activityNotifications(int[] expenditure, int d) {
    	int notifCount = 0;
    	if(expenditure==null || expenditure.length==1 || d>=expenditure.length){
    		return notifCount;
    	}
    	int[] counts = new int[250];
    	Queue<Integer> queue = new LinkedList<>();
    	for(int i=0; i<d; i++) {
    		queue.offer(expenditure[i]);
    		counts[expenditure[i]] += 1;
    	}
    	for(int i=d; i<expenditure.length; i++) {
    		double median = 0;
    		if(d%2==0) {
    			int first = -1, second = -1;
    			int count = 0;
    			for(int j=0; j<counts.length; j++) {
    				count += counts[j];
    				if(count == d/2) {
    					first = j;
    				}else if(count > d/2) {
    					if(first<0) first = j;
    					second =  j;
    					median = ((double)first+(double)second)/2;
    					break;
    				}
    			}
    		}else {
    			int count = 0;
    			for(int j=0; j<counts.length; j++) {
    				count = count + counts[j];
    				if(count>d/2) {
    					median = (double)j;
    					break;
    				}
    			}
    		}
    		System.out.println("-"+median+"-");
    		if(expenditure[i]>=median*2) {
    			notifCount++;
    		}
    		int elem = queue.remove();
    		counts[elem] = counts[elem] - 1;
    		queue.offer(expenditure[i]);
    		counts[expenditure[i]] += 1;
    	}
    	return notifCount;
    }
}
