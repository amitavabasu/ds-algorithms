package com.amit.alogs.hr.hard;

public class HR5EqualStacks {
    static int equalStacks(int[] h1, int[] h2, int[] h3) {
        /*
         * get final height of unequal 3 stacks of unequal block heights represented by 3 arrays h1, h2 & h3
         * can be done in reverse order as well by reversing all the arrays
         */
        int lastSameHeight = 0;
        if(h1==null || h1.length==0 || h2==null || h2.length==0 || h3==null || h3.length==0)
        	return lastSameHeight;//<-- to store last calculated same height
        int i1 = h1.length-1;//<-- start all indexes from length-1
        int i2 = h2.length-1;
        int i3 = h3.length-1;
        boolean done = false;//<-- initialize done as false
        int H1 = h1[i1];//<-- these are height 
        int H2 = h2[i2];
        int H3 = h3[i3];
        int nh1 = H1;//<-- these new calculated height
        int nh2 = H2;
        int nh3 = H3;
        while(!done){
            if(H1==H2 && H2==H3 && H1==H3){//<-- if all heights are same
            	lastSameHeight = H1;//<-- store last same height
            	if(i1>0) {//If more cylinder exists
            		i1--;
            		nh1 = H1+h1[i1];//<-- calculate new height
            	}else {
            		done = true;//<-- if no more cylinders we are done
            	}
            	if(i2>0) {//Do same for second stack
            		i2--;
            		nh2 = H2+h2[i2];
            	}else {
            		done = true;//<-- if no more cylinders we are done
            	}
            	if(i3>0) {//Do same for third stack
            		i3--;
            		nh3 = H3+h3[i3];
            	}else {
            		done = true;//<-- if no more cylinders we are done
            	}
            }else{
            	if(H1<=H2 && H1<=H3) {//<-- if first stack is lowe or equal than second and third
                	if(i1>0) {
                		i1--;
                		nh1 = H1+h1[i1];//<-- calculate new height
                	}else {
                		done = true;//<-- if no more cylinder then we are done
                	}
            	}
            	if(H2<=H1 && H2<=H3) {//<-- if second stack less or equal to first and third
                	if(i2>0) {
                		i2--;
                		nh2 = H2+h2[i2];//<-- calculate new height
                	}else {
                		done = true;//<-- if no more cylinders we are done
                	}
            	}
            	if(H3<=H2 && H3<=H1) {//<-- if thirst stack less or equal first and second
                	if(i3>0) {
                		i3--;
                		nh3 = H3+h3[i3];//<-- calculate ne height
                	}else {
                		done = true;//<-- if no more cylinders we are done
                	}
            	}
            }
            H1 = nh1;//<-- assign newly calculated height to appropriate height
            H2 = nh2;
            H3 = nh3;
        }
        return lastSameHeight;//<-- when done return last same height stored
    }

	public static void main(String[] args){
		int[] h1 = {3,2,1,1,1};
		int[] h2 = {4,3,2};
		int[] h3 = {1,1,4,1};
		System.out.println(equalStacks(h1,h2,h3));
	}
}
