package com.amit.alogs.hr.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HR19CellStateChanges {
	
    public static List<Integer> cellCompete(int[] states, int days) {
	List<Integer> list = new ArrayList<>();
	for(int s:states){
		list.add(s);
	}
	list.add(0);
    while(days>0){
    	int prev = 0;
    	for(int i=0; i<states.length; i++){
    		int next = list.get(i+1);
    		        if(prev==next){
    		            prev = list.get(i);
    		            list.set(i, 0);
    		        }else{
    		            prev = list.get(i);
    		            list.set(i, 1);
    		        }
    	}
    	days--;
    }
    list.remove(states.length);
    return list;
 }

	public static void main(String[] args){
		int[] states = {1, 0, 0, 0, 0, 1, 0, 0}; //--> 0 1 0 0 1 0 1 0
		int d = 1;
//		int[] states = {1, 1, 1, 0, 1, 1, 1, 1}; //--> 0 0 0 0 0 1 1 0
//		int d = 2;
		System.out.println(Arrays.toString(cellCompete(states, d).toArray()));
	}
}
