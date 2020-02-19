package com.amit.alogs.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.amit.alogs.tree.TreeTraversal.Node;

public class TreeTraversalDup1 {
	
	//PRE-ORDER root-left-right
	public void preOrderRecursive(Node root) {
		if(root==null) {
			return;
		}
		System.out.println(root.value);
		preOrderRecursive(root.left);
		preOrderRecursive(root.right);
	}
	public void preOrderNonRecursive(Node root) {
		if(root==null)
			return;
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);
		while(!stack.isEmpty()) {
			Node node = stack.pop();
			System.out.println(node.value);
			if(node.right!=null) {
				stack.add(node.right);
			}
			if(node.left!=null){
				stack.push(node.left);
			}
		}
	}
	
	//IN-ORDER left-root-right
	public void inOrderRecursive(Node root) {
		if(root==null)
			return;
		inOrderRecursive(root.left);
		System.out.println(root.value);
		inOrderRecursive(root.right);
	}
	public void inOrderNonRecursive(Node root) {
		Stack<Node> stack = new Stack<>();
		Node node = root; 
		while(node!=null || !stack.isEmpty()) {
			while(node!=null) {
				stack.add(node);
				node = node.left;
			}
			node = stack.pop();
			System.out.println(node.value);
			node = node.right;
		}
	}
	
	//OUT-ORDER right-root-left
	public void outOrderRecursive(Node root) {
		if(root==null)
			return;
		outOrderRecursive(root.right);
		System.out.println(root.value);
		outOrderRecursive(root.left);
	}
	public void outOrderNonRecursive(Node root) {
		Stack<Node> stack = new Stack<>();
		Node node = root;
		while(node!=null || !stack.isEmpty()) {
			while(node!=null) {
				stack.push(node);
				node = node.right;
			}
			node = stack.pop();
			System.out.println(node.value);
			node = node.left;
		}
	}
	
	//POST-ORDER left-right-root
	public void postOrderRecursive(Node node) {
		if(node==null)
			return;
		postOrderRecursive(node.left);
		postOrderRecursive(node.right);
		System.out.println(node.value);
	}
	public void postOrderNonRecursive(Node root) {
		if(root==null)
			return;
		Stack<Node> stack = new Stack<>();
		Stack<Node> result = new Stack<>();
		stack.push(root);
		while(!stack.isEmpty()) {
			Node node = stack.pop();
			result.push(node);
			if(node.left!=null) {
				stack.push(node.left);
			}
			if(node.right!=null) {
				stack.push(node.right);
			}
		}
		while(!result.isEmpty()) {
			System.out.println(result.pop().value);
		}
	}
	
	
	//LEVEL-ORDER
	public void levelOrderRecursive(Node root) {
		int height = height(root);
		for(int i=0; i<height; i++) {
			displayLevel(root, i+1);
		}
	}
	public int height(Node root) {
		if(root==null) {
			return 0;
		}else {
			int lheight = height(root.left);
			int rheight = height(root.right);
			if(lheight>rheight) {
				return lheight+1;
			}else {
				return rheight+1;
			}
		}
	}
	public int heightByEdges(Node root) {
		if(root==null || (root.left==null && root.right == null)) {
			return 0;
		}else {
			int lheight = heightByEdges(root.left);
			int rheight = heightByEdges(root.right);
			if(lheight>rheight) {
				return lheight+1;
			}else {
				return rheight+1;
			}
		}
	}
	
	public void displayLevel(Node root, int level) {
		if(root==null)
			return;
		if(level==1) {
			System.out.println(root.value);
		}else {
			displayLevel(root.left, level-1);
			displayLevel(root.right, level-1);
		}
	}
	public void levelOrderNonRecursive(Node root) {
		if(root==null)
			return;
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		while(!queue.isEmpty()) {
			Node node = queue.remove();
			System.out.println(node.value);
			if(node.left!=null)
				queue.add(node.left);
			if(node.right!=null)
				queue.add(node.right);
		}
	}
	

}
