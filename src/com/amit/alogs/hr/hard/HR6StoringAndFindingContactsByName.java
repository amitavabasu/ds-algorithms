package com.amit.alogs.hr.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HR6StoringAndFindingContactsByName {
	
	public static class Node{
		public Node[] children = new Node[26];
		public int count = 0;
		public Node(int count){
			this.count = count;
		}
	}
	
	static void add(Node root, String value) {
		Node node = root;
		//Node newNode = new Node(1);
		for(int i=0; i<value.length(); i++){
			char c = value.charAt(i);
			if(node.children[c-'a']==null){
				node.count++;
				Node newNode = new Node(0);
				node.children[c-'a'] = newNode;
				node = newNode;
			}else{
				node.count++;
				node = node.children[c-'a'];						
			}
		}
		node.count = 1;
	}
	
	static int find(Node root, String value){
		Node node = root;
		for(int i=0; i<value.length(); i++){
			char c = value.charAt(i);
			if(node.children[c-'a']==null){
				return 0;
			}else{
				node = node.children[c-'a'];
			}
		}
		return node.count;
	}
	
    static int[] contacts(String[][] queries) {
    	if(queries==null){
    		return null;
    	}
    	List<Integer> res = new ArrayList<>();
    	Node root = new Node(0);
    	for(String[] query:queries){
    		String command = query[0];
    		String value = query[1];
    		if("add".equalsIgnoreCase(command)){
    				add(root,value);
    		}else if("find".equalsIgnoreCase(command)){
    			res.add(find(root,value));
    		}
    	}
		return res.stream()
				.mapToInt(i->i)
				.toArray();
    }
	public static void main(String[] args){
		String[][] queries = {{"add", "hack"},{"add", "hackerrank"},{"find", "hac"},{"find", "hak"},{"add", "hakerrank"}, {"add", "hacker"}, {"find", "hak"},{"find", "hack"},{"find", "haker"}, {"find", "hacker"},{"find", "hackerrank"}};
		System.out.println(Arrays.toString(contacts(queries)));
	}
}
