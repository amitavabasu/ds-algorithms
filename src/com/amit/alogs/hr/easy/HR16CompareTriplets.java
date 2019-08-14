package com.amit.alogs.hr.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HR16CompareTriplets {
	
    static List<Integer> compareTriplets(List<Integer> a, List<Integer> b) {
    	if(a==null || b==null || a.size()!=b.size()){
    		return null;
    	}
    	int ascore = 0;
    	int bscore = 0;
    	for(int i=0; i<a.size(); i++){
    		int ai = a.get(i);
    		int bi = b.get(i);
    		if(ai>bi){
    			ascore++;
    		}else if(ai<bi){
    			bscore++;
    		}else{
    			//do nothing
    		}
    	}
    	return new ArrayList<Integer>(Arrays.asList(ascore, bscore));
    }
	
	
	public static void main(String[] args){
		List<Integer> a = new ArrayList<>(Arrays.asList(17, 28, 30)); 
		List<Integer> b = new ArrayList<>(Arrays.asList(99, 16, 8));
		System.out.println(Arrays.toString(compareTriplets(a,b).toArray()));
	}
}
