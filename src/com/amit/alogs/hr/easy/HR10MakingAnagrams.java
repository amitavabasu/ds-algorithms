package com.amit.alogs.hr.easy;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HR10MakingAnagrams {
	/*
	 * determine how many characters to be deleted from string a & b to make an one string anagram of other string 
	 */
	public int makeAnagram(String a, String b) {
		/*
		 * simple algorithm using map
		 */
		int totalDeleteCount = 0;
		if(a==null || b==null){
			return totalDeleteCount;
		}
		Map<Character,Integer> countMap = new HashMap<>();
		for(int i=0;i<a.length();i++){ //<-- create a map of count for string a by incrementing the count for each letter
			Character c = a.charAt(i);
			Integer count = countMap.get(c);
			if(count==null){
				countMap.put(c, 1);
			}else{
				countMap.put(c,count+1);
			}
		}
		for(int i=0;i<b.length();i++){//<-- modify the count map by decrementing count for each char in string b, if char does not exists put -1 
			Character c = b.charAt(i);
			Integer count = countMap.get(c);
			if(count==null){
				countMap.put(c, -1);
			}else{
				countMap.put(c,count-1);
			}
		}
		Set<Character> keys = countMap.keySet();
		if(keys!=null){//<-- calculate delete count, by adding the count map for both positive and negative counts 
			for(Character key:keys){
				Integer count = Math.abs(countMap.get(key));
				totalDeleteCount = totalDeleteCount + count;
			}
		}
		return totalDeleteCount;
	}
	
	public static void main(String[] args){
		String a = "fcrxzwscanmligyxyvym";
		String b = "jxwtrhvujlmrpdoqbisbwhmgpmeoke";
		HR10MakingAnagrams sm = new HR10MakingAnagrams();
		System.out.println(sm.makeAnagram(a, b));
	}
}
