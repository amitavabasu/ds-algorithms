package com.amit.alogs.hr.hard;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class HR8NoPrefixSet {
		//Create a Trie data structure containing a single boolean field exists.
		//If it is an intermediate string exists is false or if a complete string exist is true
		public static class Node{
			public Node[] children = new Node[26];
			public boolean exists = false;
		}
		//Implement add function given the root node as param
		static boolean add(Node root, String value) {
			//Start with root node
			Node node = root;//<-- root node is placeholder does not represent any character
			//For all the char in the string (i.e. for entire string)
			for(int i=0; i<value.length(); i++){
				char c = value.charAt(i); //<-- get each char 
				if(node.children[c-'a']==null){//<-- if children for that char is null it's ok to create new node
					Node newNode = new Node();//<-- create new node
					node.children[c-'a'] = newNode;//Assign new node as child
					node = newNode;//<-- set node and new node
				}else{//<-- if the node at that char is not null this char exists
					node = node.children[c-'a'];//<-- assign node to the specific children
					if(node.exists) {//<-- if this node is a node which as already existed i.e. exists as true return false
						return false;
					}
				}
			}
			//When all the string passed
			node.exists = true;//<-- set the last node as exited node by setting exists as true
			for(Node n:node.children) {//<-- now check for all it's children
				if(n!=null) {//<-- if a child node exists it's a substring of a previous string, hence return false
					return false;
				}
			}
			//If all children is null return true
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
	    	Node root = new Node();//<-- create root node as placeholder
	        for(String s:arr) {//<-- read all strings
	        	if(add(root,s)){//<-- for each string add the string, if it returns true continue
	        		System.out.println(s);
	        		continue;
	        	}else {//<-- if it return false, a substring exists
	        		System.out.println("BAD SET");//<-- print BAD set and print the string it failed
	        		System.out.println(s);
	        		badfound = true;//<-- set a flag badFound as true
	        		break;
	        	}
	        }
	        if(!badfound)//<-- if badFound flag is false print as GOOD SET
	        	System.out.println("GOOD SET");
	        scanner.close();
	   }
}
