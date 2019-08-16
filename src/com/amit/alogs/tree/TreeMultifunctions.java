package com.amit.alogs.tree;

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
import java.util.concurrent.ConcurrentLinkedQueue;

public class TreeMultifunctions {

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
	
	@SuppressWarnings("unused")
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
	
	/* -----------------------------------------------------------------------------------------------------
	 * RESURSIVE Calculate sum of the nodes falls in the same vertical lines of a tree   
	 * have a function to keep and increment the sum keyed by negative, zero and positive numbers in a map
	 * traverse the tree updating vertical sum in appropriate index
	 * have a function to display the vertical sums from the maps 
	 */
	
	/* recursively calculate vertical sum
	 * check initial condition if node is null return
	 * if not calculate vertical sum for that node with current index
	 * call the same vertical sum method recursively for the left child node and index-1
	 * call the same vertical sum method recursively for right child node and index+1
	 * end 
	 */
	public void verticalSum(Node root, int index){
		if(root==null){
			return;
		}
		calculateSum(root.value,index);
		verticalSum(root.left,index-1);
		verticalSum(root.right, index+1);
	}
	/*
	 * Nothing great just increment sum with provided value in appropriate index
	 */
	Map<Integer,Double> sums = new HashMap<Integer, Double>();
	private void calculateSum(Double value, Integer index){
		Double currentSum = sums.get(index);
		if(currentSum == null){
			sums.put(index, value);
		}else{
			sums.put(index,currentSum+value);
		}
	}
	/*
	 * Nothing great just sort by key and display the map of sums 
	 */
	public void displayVerticalSum(Node root){
		verticalSum(root,0);
		List<Integer> keys = new ArrayList<Integer>(sums.keySet());
		Collections.sort(keys);
		for(Integer index:keys){
			Double sum = sums.get(index);
			System.out.println("Index: "+index+"==>"+sum);
		}
	}
	
	/* -----------------------------------------------------------------------------------------------------
	 * NON-RECURSIVE Calculating max width of a tree
	 * initialize width as 0
	 * Do non recursive breadth-first/level-order traversal of the tree using a queue
	 * while just before visiting each node if size of the queue is grater than width set width as the size of the queue
	 * At the end of visiting all node when queue is empty return width 
	 */
	public int width(Node root) {
		int width = 0;
		if(root==null)
			return width;
		Queue<Node> queue = new ConcurrentLinkedQueue<Node>();
		queue.add(root);
		while(!queue.isEmpty()) {
			if(width < queue.size()) {
				width = queue.size();
			}
			//printQueue(queue);
			Node node = queue.remove();
			//System.out.println(node.value);
			if(node.left!=null) {
				queue.add(node.left);
			}
			if(node.right!=null) {
				queue.add(node.right);
			}
		}
		return width;
	}
	
	/*
	 * helper method to check how the queue is growing and shrinking 
	 */
	public void printQueue(Queue<Node> queue) {
		Object[] elements = queue.toArray(); 
		for(Object o:elements) {
			Node n = (Node)o;
			System.out.print(n.value+" ");
		}
		System.out.println();
	}
	

	/* -----------------------------------------------------------------------------------------------------
	 * NON-RECURSIVE Calculating max width of a tree
	 * initialize width as 0
	 * Do non recursive breadth-first/level-order traversal of the tree using a queue
	 * while just before visiting each node if size of the queue is grater than width set width as the size of the queue
	 * At the end of visiting all node when queue is empty return width 
	 */
	
	public int height(Node root) {
		if(root==null)
			return 0;
		int lheight = height(root.left);
		int rheight = height(root.right);

		if(lheight > rheight) {
			return lheight+1;
		}else {
			return rheight+1;
		}
	}
	
	public void addLevel(Node root, int level, Node sumNode) {
		if(root==null || level<1)
			return;
		if(level==1) {
			System.out.print(root.value+" ");
			sumNode.value = sumNode.value+root.value;
		}
		addLevel(root.left, level-1, sumNode);
		addLevel(root.right, level-1, sumNode);
//		System.out.println("Sum: "+sum);		
	}
	
	public void printLevelSum(Node root) {
		int height = height(root);
		for(int i=1; i<=height; i++) {
			Node sum = new Node(0.0);
			addLevel(root, i, sum);
			System.out.println("--level: "+i+"--Sum: "+sum.value);
		}
	}
	
	public void printLevelSumNonRecursive(Node root){
		if(root==null){
			System.out.println(0);
			return;
		}
		Queue<Node> queue = new ConcurrentLinkedQueue<Node>();
		queue.add(root);
		while(!queue.isEmpty()){
			Node node = queue.remove();
			System.out.println(node.value);
			if(node.left!=null){
				queue.add(node.left);
			}
			if(node.right!=null){
				queue.add(node.right);
			}
		}
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
	public static void topView(TNode root) {
        findTop(root,0);
        List<Integer> keys = new ArrayList<Integer>(topMap.keySet());
        //Collections.sort(keys);
        for(Integer index:keys){
            System.out.print(topMap.get(index)+" ");
        }
    }

    public static void findTop(TNode node, int index){//<-- this one is wrong, we need breadth-first-traversal (level-order-travesal)
        if(node==null){
            return;
        }else{
            //Integer checkValue = topMap.get(index);
            if(!topMap.containsKey(index)){
                topMap.put(index,node.data);
            }else{
            	//topMap.put(index, node.data);
            }
            findTop(node.left, index-1);
            findTop(node.right, index+1);
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
	
	public static TNode lowestCommonAncestorNonRecursive(TNode root, int v1, int v2){//<-- port-order-traversal
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
		System.out.println(checkBSTRecursive(root));
		System.out.println(checkBSTNonRecursive(root));
	}
}
