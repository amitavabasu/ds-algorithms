package com.amit.alogs.hr.easy;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HR10MakingAnagrams {

	public int makeAnagram(String a, String b) {
		int totalDeleteCount = 0;
		if(a==null || b==null){
			return totalDeleteCount;
		}
		Map<Character,Integer> countMap = new HashMap<>();
		for(int i=0;i<a.length();i++){
			Character c = a.charAt(i);
			Integer count = countMap.get(c);
			if(count==null){
				countMap.put(c, 1);
			}else{
				countMap.put(c,count+1);
			}
		}
		Set<Character> keys = countMap.keySet();
		if(keys!=null){
			//keys = Collections.sort(keys);
			for(Character key:keys){
				Integer count = Math.abs(countMap.get(key));
				System.out.println(key+"==>"+count);
			}
		}
		for(int i=0;i<b.length();i++){
			Character c = b.charAt(i);
			Integer count = countMap.get(c);
			if(count==null){
				countMap.put(c, -1);
			}else{
				countMap.put(c,count-1);
			}
		}
		System.out.println("=======================================================");
		keys = countMap.keySet();
		if(keys!=null){
			//keys = Collections.sort(keys);
			for(Character key:keys){
				Integer count = Math.abs(countMap.get(key));
				System.out.println(key+"==>"+count);
			}
		}
		keys = countMap.keySet();
		if(keys!=null){
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
