package com.amit.alogs.hr.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HR6StoringAndFindingContactsByName {
	/*
	 * find the number of contacts available by typing a substring
	 */
	
	//Use Trie data structure
	//Having an array of nodes for each char and a count to indicate how many children+childrens children it has 
	public static class Node{
		public Node[] children = new Node[26];
		public int count = 0;
		public Node(int count){
			this.count = count;
		}
	}
	
	//Ading a new contact node given it's name as value
	static void add(Node root, String value) {
		Node node = root;//<-- start with the root node which is a placeholder
		for(int i=0; i<value.length(); i++){//<-- For the entire length of the string
			char c = value.charAt(i);//<-- get each char
			if(node.children[c-'a']==null){//<-- if the node for this character is null
				node.count++;//<-- increment the node count of the current node, (as there will be one additional node under it
				Node newNode = new Node(0);//<-- create a new node, with count 0, will get incremented later or will be assigned as 1 if last character
				node.children[c-'a'] = newNode;//<-- assign that node to appropriate place
				node = newNode;//<-- assign the current node to the new node
			}else{//<-- if the node for this char is not null
				node.count++;//<-- increment the count as there will one more node under this node
				node = node.children[c-'a'];//<-- assign the node to the appropriate child
			}
		}
		//If current node count is 0 set it as 1
		if(node.count==0){
			node.count = 1;
		}else{
			//This is a situation of duplicate name, ignore for the time being as it states no duplicate is possible
		}
	}
	
	//Find the number of contacts given a substring of name
	static int find(Node root, String value){
		Node node = root;//<-- start with root node
		for(int i=0; i<value.length(); i++){//<-- for all the character in the substring
			char c = value.charAt(i);//<-- get the char
			if(node.children[c-'a']==null){//<-- if the char points to null, there exists no contact, return 0
				return 0;
			}else{//<-- move current node to the found child
				node = node.children[c-'a'];
			}
		}
		return node.count;//<-- finally return the count of the child node, which will contain all counts of nodes underneath it
	}
	
	
    static int[] contacts(String[][] queries) {
    	if(queries==null){
    		return null;
    	}
    	List<Integer> res = new ArrayList<>();
    	//Initialize root node which does not correspond to any char but  placeholder with a count 0
    	Node root = new Node(0);
    	for(String[] query:queries){//<-- for all queries
    		String command = query[0];
    		String value = query[1];
    		if("add".equalsIgnoreCase(command)){//<-- if add 
    				add(root,value);//<-- add that contact
    		}else if("find".equalsIgnoreCase(command)){//<-- if find
    			res.add(find(root,value));//<-- add the find count in result list
    		}
    	}
		return res.stream()
				.mapToInt(i->i) //<-- convert the result list to array and return
				.toArray();
    }
	public static void main(String[] args){
		String[][] queries = {{"add", "hack"},{"add", "hackerrank"},{"find", "hac"},{"find", "hak"},{"add", "hakerrank"}, {"add", "hacker"}, {"find", "hak"},{"find", "hack"},{"find", "haker"}, {"find", "hacker"},{"find", "hackerrank"}};
		System.out.println(Arrays.toString(contacts(queries)));
	}
}
