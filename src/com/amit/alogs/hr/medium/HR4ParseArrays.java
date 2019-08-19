package com.amit.alogs.hr.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HR4ParseArrays {
	
	/*
	 * get number of matching strings in array of strings given an array of query strings 
	 */
	
	public int[] matchingStrings(String[] strings, String[] queries){
		/*
		 * simple implementation, 
		 */
		int[] result = null;
		if(strings==null || queries==null || strings.length==0 || queries.length==0){
			 result = new int[1];
			return result;
		}
		result = new int[queries.length];
		Map<String,Integer> countMap = new HashMap<>();
		for(int i=0; i<strings.length; i++){
			Integer count = countMap.get(strings[i]);
			if(count==null){
				countMap.put(strings[i], 1);
			}else{
				countMap.put(strings[i], count+1);
			}
		}
		for(int i=0; i<queries.length; i++){
			Integer count = countMap.get(queries[i]);
			if(count==null){
				result[i] = 0;
			}else{
				result[i] = count;
			}
		}
		return result;
	}
	
	public static void main(String[] args){
//		String[] strings = {"aba","baba","aba","xzxb"};
//		String[] queries = {"aba","xzxb","ab"};
		String[] strings = {"abcde","sdaklfj","asdjf","na","basdn","sdaklfj","asdjf","na","asdjf","na","basdn","sdaklfj","asdjf"};
		String[] queries = {"abcde","sdaklfj","asdjf","na","basdn"};
		HR4ParseArrays obj = new HR4ParseArrays();
		System.out.println(Arrays.toString(obj.matchingStrings(strings, queries)));
	}
}
