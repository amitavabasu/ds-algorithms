package com.amit.alogs.hr.easy;

import java.util.HashMap;
import java.util.Map;

public class HR14RansomeNote {
	/*
	 * made decision that if a string array of note will be possible to derive from another string array called magazine  
	 */
	
	public void checkMagazine(String[] magazine, String[] note){
		if(magazine==null || note==null || magazine.length==0 || note.length==0){
			System.out.println("No");
			return;
		}
		Map<String,Integer> countMap = new HashMap<>();//<-- use map to store work counts
		for(int i=0; i<magazine.length; i++){ //<-- store counts of each string in magazine
			String s = magazine[i];
			Integer count = countMap.get(s);
			if(count==null){
				countMap.put(s, 1);
			}else{
				countMap.put(s, count+1);
			}
		}
		for(int i=0; i<note.length; i++){//<-- decrement count for each element in note
			String s = note[i];
			Integer count = countMap.get(s);
			if(count==null){
				System.out.println("No");//<-- false not possible
				return;
			}else if(count==0){
				System.out.println("No");//<-- false not possible
				return;
			}else{
				count = count-1;//<-- decrement
				countMap.put(s, count);//<-- store back
			}
		}
		System.out.println("Yes");//<-- finally possible
	}
	
	public static void main(String[] args){
//		String magazine = "give me one grand today night";
//		String note = "give one grand today";
//		String magazine = "two times three is not four";
//		String note = "two times two is four";
		String magazine = "ive got a lovely bunch of coconuts";
		String note = "ive got some coconuts";
		
		HR14RansomeNote obj = new HR14RansomeNote();
		obj.checkMagazine(magazine.split("[ ]"), note.split("[ ]"));
	}
}
