package com.amit.alogs.hr.hard;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class HR8NoPrefixSet {
	
		public static class Node{
			public Node[] children = new Node[26];
			public boolean exists = false;
		}
		
		static boolean add(Node root, String value) {
			Node node = root;
			for(int i=0; i<value.length(); i++){
				char c = value.charAt(i);
				if(node.children[c-'a']==null){
					Node newNode = new Node();
					node.children[c-'a'] = newNode;
					node = newNode;
				}else{
					node = node.children[c-'a'];
					if(node.exists) {
						return false;
					}
				}
			}
			node.exists = true;
			for(Node n:node.children) {
				if(n!=null) {
					return false;
				}
			}
			return true;
		}
	    
	    
	    
	    public static void main(String[] args) throws IOException {
	    	//Scanner scanner = new Scanner(System.in);
	        File file = new File("C:\\Users\\amita\\eclipse-workspace\\ds-algorithms\\test-dada\\NoPrefixSet.txt");
	        Scanner scanner = new Scanner(file);
	        String length = scanner.nextLine();
	        int len = Integer.parseInt(length);
	        String[] arr = new String[len]; 
	        for (int i = 0; i < len; i++) {
	            arr[i] = scanner.nextLine();
	        }
	        boolean badfound = false;
	    	Node root = new Node();
	        for(String s:arr) {
	        	if(add(root,s)){
	        		System.out.println(s);
	        		continue;
	        	}else {
	        		System.out.println("BAD SET");
	        		System.out.println(s);
	        		badfound = true;
	        		break;
	        	}
	        }
	        if(!badfound)
	        	System.out.println("GOOD SET");
	        scanner.close();
	   }
}
