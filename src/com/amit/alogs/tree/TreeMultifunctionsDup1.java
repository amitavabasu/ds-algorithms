package com.amit.alogs.tree;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public class TreeMultifunctionsDup1 {

	public class Node {
		public Node left;
		public Node right;
		public Double value;
		public Node(Double value){
			this.value = value;
		}
	}
	
	public static class TNode {
		public TNode left;
		public TNode right;
		public int data;
		public TNode(int value){
			this.data = value;
		}
	}
	
	@SuppressWarnings({ "unused", "deprecation" })
	public Node makeTree1(){
		Node F = new Node(new Double(6));
		Node B = F.left = new Node(new Double(2));
		Node G = F.right = new Node(new Double(7));
		Node A = B.left = new Node(new Double(1));
		Node D = B.right = new Node(new Double(4));
		Node C = D.left = new Node(new Double(3));
		Node E = D.right = new Node(new Double(5));
		Node I = G.right = new Node(new Double(9));
		Node H = I.left = new Node(new Double(8));
		
		Node n1 = A.left = new Node(new Double(0));
		Node n2 = I.right = new Node(new Double(10));
		return F;
	}
	
	//Vertical Sum
	Map<Integer, Double> sums = new HashMap<Integer, Double>();
	public void verticalSum(Node root, int offset) {
		if(root==null)
			return;
		Double sum = sums.getOrDefault(offset,0d);
		sums.put(offset, sum+root.value);
		verticalSum(root.left, offset-1);
		verticalSum(root.left, offset+1);
	}
	
	//Tree width
	public int width(Node root) {
		if(root==null)
			return 0;
		int width = 0;
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		while(!queue.isEmpty()) {
			if(width<queue.size()) {
				width = queue.size();
			}
			Node node = queue.remove();
			if(node.left!=null) {
				queue.add(node.left);
			}
			if(node.right!=null) {
				queue.add(node.right);
			}
		}
		return width;
	}
	
	//Tree height
	public int height(Node root) {
		if(root==null)
			return 0;
		int lheight = height(root.left);
		int rheight = height(root.right);
		if(lheight>rheight) {
			return lheight+1;
		}else {
			return rheight+1;
		}
	}
	
	
	public TNode insertIntoBST(TNode root, int data) {
		if(root==null) {
			return new TNode(data);
		}
		TNode curr  = null;
		if(data<=root.data) {
			curr = insertIntoBST(root.left, data);
			root.left = curr;
		}else {
			curr = insertIntoBST(root.right, data);
			root.right = curr;
		}
		return root;
	}
	
	public static TNode insert(TNode root, int data) {
        if(root == null) {
            return new TNode(data);
        } else {
            TNode cur;
            if(data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }
	
	public static Map<Integer, Integer> topMap = new TreeMap<>();
	
	public static void topViewOfTree(TNode root){
		class NodeWithOffset{
			TNode node;
			int offset;
			public NodeWithOffset(TNode node, int offset){
				this.node = node;
				this.offset = offset;
			}
		}
		if(root==null){
			return;
		}else{
			Queue<NodeWithOffset> queue = new LinkedList<>();
			queue.offer(new NodeWithOffset(root, 0));
			while(!queue.isEmpty()){
				NodeWithOffset no = queue.remove();
				if(!topMap.containsKey(no.offset)){
					topMap.put(no.offset, no.node.data);
				}
				if(no.node.left!=null){
					queue.offer(new NodeWithOffset(no.node.left, no.offset-1));
				}
				if(no.node.right!=null){
					queue.offer(new NodeWithOffset(no.node.right, no.offset+1));
				}
			}
		}
        for (Entry<Integer, Integer> entry : topMap.entrySet()) { 
            System.out.print(entry.getValue()+" "); 
        } 		
	}
    
	public static void topView2(TNode root) {
		class QueueObj {
            TNode node; 
            int horizontalOffset; 
            QueueObj(TNode node, int horizontalOffset) { 
                this.node = node; 
                this.horizontalOffset = horizontalOffset; 
            } 
        } 
        Queue<QueueObj> q = new LinkedList<QueueObj>(); 
        Map<Integer, TNode> topViewMap = new TreeMap<Integer, TNode>(); 
  
        if (root == null) { 
            return; 
        } else { 
            q.add(new QueueObj(root, 0)); 
        } 
        // count function returns 1 if the container  
        // contains an element whose key is equivalent to height, or returns zero otherwise. 
        while (!q.isEmpty()) { 
            QueueObj tmpNode = q.poll();
            if (!topViewMap.containsKey(tmpNode.horizontalOffset)) {
                topViewMap.put(tmpNode.horizontalOffset, tmpNode.node);
            } 
            if (tmpNode.node.left != null) { 
                q.add(new QueueObj(tmpNode.node.left, tmpNode.horizontalOffset - 1)); //<-- going left so reduce offset towards negative infinity
            } 
            if (tmpNode.node.right != null) { 
                q.add(new QueueObj(tmpNode.node.right, tmpNode.horizontalOffset + 1)); //<-- going right so increase offset twords positive infenity
            } 
  
        } 
        for (Entry<Integer, TNode> entry : topViewMap.entrySet()) { 
            System.out.print(entry.getValue().data+" "); 
        } 		
    }
    
	public static TNode lowestCommonAncestorRecursive(TNode root, int v1, int v2){//<-- post-order traversal
		if(root==null){
			return root;
		}else{
			if(root.data > v1 && root.data > v2){
				return lowestCommonAncestorRecursive(root.left, v1, v2);
			}else if(root.data < v1 && root.data < v2){
				return lowestCommonAncestorRecursive(root.right, v1, v2);
			}else{
				return root;
			}
		}
	}
	
	public static TNode lowestCommonAncestorNonRecursive(TNode root, int v1, int v2){//<-- post-order-traversal
		while(root!=null){
			if(root.data > v1 && root.data > v2){
				root = root.left;
			}else if(root.data < v1 && root.data < v2){
				root = root.right;
			}else{
				return root;
			}
		}
		return root;
	}
	static TNode prev = null;
	public static boolean checkBSTRecursive(TNode root){//<-- very good algo, in-order-traversal
		if(root==null){
			return true;
		}else{
			boolean leftCheck;
			boolean rightCheck;
			if(root.left!=null && root.left.data > root.data){
				return false;
			}else{
				 leftCheck = checkBSTRecursive(root.left);
			}
			if(prev!=null && root.data <= prev.data){//<-- this is important, current root must be less than or equal to previous, 
													 //why? 
													 //because previous node always points to a successfully visited node, 
													 //prior to visiting current node. 
													 //As this is L-N-R traversal previous is either root or right node of current node  
				return false;
			}
			prev = root;
			if(root.right!=null && root.right.data < root.data){
				return false;
			}else{
				rightCheck = checkBSTRecursive(root.right);
			}
			return leftCheck && rightCheck;
		}
	}
	
	public static boolean checkBSTNonRecursive(TNode root){//<-- using in-order-traversal
		Stack<TNode> stack = new Stack<>();
		TNode node = root;
		TNode prev = null;
		while(node!=null || !stack.empty()){
			while(node!=null){
				stack.push(node);
				if(node.left!=null && node.left.data > node.data ){
					return false;
				}
				node = node.left;
			}
			node = stack.pop();
			if(prev!=null && node.data <= prev.data){//<-- current node must be less than or equal to previous, 
													 //because previous node always points to a successfully visited node, 
													 //prior to visiting current node. 
													 //As this is L-N-R traversal previous is either root or right node of current node  
				return false;
			}
			prev = node;
			if(node.right!=null && node.right.data < node.data ){
				return false;
			}
			node = node.right;
		}
		return true;
	}
	
	public static TNode buildTree(){
		TNode root = new TNode(3);
		root.left = new TNode(5);
		root.right = new TNode(2);
		root.left.left = new TNode(1);
		root.left.right = new TNode(4);
		root.right.left = new TNode(6);
		return root;
	}
	
	public static void main(String[] args){
		int[] array = {37,23,108,59,86,64,94,14,105,17,111,65,55,31,79,97,78,25,50,22,66,46,104,98,81,90,68,40,103,77,74,18,69,82,41,4,48,83,67,6,2,95,54,100,99,84,34,88,27,72,32,62,9,56,109,115,33,15,91,29,85,114,112,20,26,30,93,96,87,42,38,60,7,73,35,12,10,57,80,13,52,44,16,70,8,39,107,106,63,24,92,45,75,116,5,61,49,101,71,11,53,43,102,110,1,58,36,28,76,47,113,21,89,51,19,3};
		//int[] array = {1,2,5,3,6,4};
		TNode root = null;
		for(int i:array){
			root = insert(root,i);
		}
//		topView(root);
//		int[] arr = {4, 2, 3, 1, 7, 6};
//		TNode root = null;
//		for(int i:arr){
//			root = insert(root,i);
//		}
//		System.out.println(lca(root,1,7).data);
//		TNode root = buildTree();
		topView2(root);
		System.out.println();
		topViewOfTree(root);
	}
}
