package com.amit.tests;

import java.util.HashMap;
import java.util.Map;

public class Test3FBCheckOrderByProvidedOrder {
	//Check if the order of array of strings by providing an order of alphabet
	
	public static Map<Character, Integer> charMap = new HashMap<>();
	
	
	public static boolean checkOrder(String[] words, char[] alphabet) {
		boolean ordered = false;
		for(int i=0;i<alphabet.length; i++) {
			charMap.put(alphabet[i], i+1);
		}
		
		String word = words[0];
		int[] first = new int[word.length()];
		int i = 0;
		for(char c:word.toCharArray()) {
			first[i++] = charMap.get(c);
		}
		boolean done = false;
		int j = 1;
		while(!done) {
			int[] second = new int[Math.max(words[j].length(),words[j-1].length())];
			for(i=0; i<words[j].length(); i++) {
				second[i] = charMap.get(words[j].charAt(i));
			}
			for(int k=0; k<first.length && k<second.length; k++) {
				if(first[k]<=second[k]) {
					continue;
				}else {
					return ordered;
				}
			}
			if(j<words.length-1) {
				j++;
				first=second;
			}else {
				done = true;
			}
		}
		return true;
	}
	
	
	
	
	public static void main(String[] args) {
		char[] alphabets = {'c', 'a', 'd', 'b', 't', 'z', 'p'};
		String[] words = {"ca", "cab", "cat", "cap"};
		System.out.println(checkOrder(words,alphabets));
	}
}
