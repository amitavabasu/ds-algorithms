package com.amit.alogs.hr.hard;

public class HR5EqualStacks {
    static int equalStacks(int[] h1, int[] h2, int[] h3) {
        /*
         * get final height of unequal 3 stacks of unequal block heights represented by 3 arrays h1, h2 & h3
         * can be done in reverse order as well by reversing all the arrays
         */
        int lastSameHeight = 0;
        if(h1==null || h1.length==0 || h2==null || h2.length==0 || h3==null || h3.length==0)
        return lastSameHeight;
        int i1 = h1.length-1;
        int i2 = h2.length-1;
        int i3 = h3.length-1;
        boolean done = false;
        int H1 = h1[i1];
        int H2 = h2[i2];
        int H3 = h3[i3];
        int nh1 = H1;
        int nh2 = H2;
        int nh3 = H3;
        while(!done){
            if(H1==H2 && H2==H3 && H1==H3){
            	lastSameHeight = H1;
            	if(i1>0) {
            		i1--;
            		nh1 = H1+h1[i1];
            	}else {
            		done = true;
            	}
            	if(i2>0) {
            		i2--;
            		nh2 = H2+h2[i2];
            	}else {
            		done = true;
            	}
            	if(i3>0) {
            		i3--;
            		nh3 = H3+h3[i3];
            	}else {
            		done = true;
            	}
            }else{
            	if(H1<=H2 && H1<=H3) {
                	if(i1>0) {
                		i1--;
                		nh1 = H1+h1[i1];
                	}else {
                		done = true;
                	}
            	}
            	if(H2<=H1 && H2<=H3) {
                	if(i2>0) {
                		i2--;
                		nh2 = H2+h2[i2];
                	}else {
                		done = true;
                	}
            	}
            	if(H3<=H2 && H3<=H1) {
                	if(i3>0) {
                		i3--;
                		nh3 = H3+h3[i3];
                	}else {
                		done = true;
                	}
            	}
            }
            H1 = nh1;
            H2 = nh2;
            H3 = nh3;
        }
        return lastSameHeight;
    }

	public static void main(String[] args){
		int[] h1 = {3,2,1,1,1};
		int[] h2 = {4,3,2};
		int[] h3 = {1,1,4,1};
		System.out.println(equalStacks(h1,h2,h3));
	}
}
