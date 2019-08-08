package com.amit.alogs.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TreeTraversal {

/* Traversal:
 * depth-first-search: pre-order(N-L-R), in-order(L-N-R), out-order(R-N-L), post-order(LRN)
 * breadth-first-search: level-order
 */
	
	public class Node {
		public Node left;
		public Node right;
		public char value;
		public Node(char value){
			this.value = value;
		}
	}
	
/*	
				F
			   / \
			  B   G
			 / \   \
			A   D   I
			   / \ / 
			  C   EH   
*/
	
	@SuppressWarnings("unused")
	public Node makeTree1Char(){
		Node F = new Node('F');
		Node B = F.left = new Node('B');
		Node G = F.right = new Node('G');
		Node A = B.left = new Node('A');
		Node D = B.right = new Node('D');
		Node C = D.left = new Node('C');
		Node E = D.right = new Node('E');
		Node I = G.right = new Node('I');
		Node H = I.left = new Node('H');
		return F;
	}


/* RECURSIVE depth-first tree traversals
 * Have similar pattern call same method in the same order as desired
 */
	
	//pre-order: Root-Left-Right
	public void preOrder(Node root){
		if(root==null){
			return;
		}else{
			System.out.println(root.value);
			preOrder(root.left);
			preOrder(root.right);
		}
	}
	//in-order: Left-Root-Right
	public void inOrder(Node root){
		if(root==null){
			return;
		}else{
			inOrder(root.left);
			System.out.println(root.value);
			inOrder(root.right);
		}
	}
	//out-order: Right-Root-Left
	public void outOrder(Node root){
		if(root==null){
			return;
		}else{
			outOrder(root.right);
			System.out.println(root.value);
			outOrder(root.left);
		}
	}
	//post-order: Left-Right-Root
	public void postOrder(Node root){
		if(root==null){
			return;
		}else{
			postOrder(root.left);
			postOrder(root.right);
			System.out.println(root.value);
		}
	}
	
/* NON-RECURSIVE depth-first tree traversal
 * Have variations in each type of traversal - mostly using stack to store nodes for future visit 
 * pre-order(NLR) - similar to non recursive breath first traversal, instead of queue use stack
 * in-order(LNR) - initialize node to root, use stack, needs two while loop, outer while none not null or stack not empty, inner while node not null, push node to move to left assign node to left, after inner while loop node is null, pop node from stack, display/visit node, assign node to right node
 * out-order(RNL) - same as in-order, right node first
 * post-order(LRN) - this one trickier, needs two stack, do same as pre-order but push on second stack, finally pop out all, i.e. reverse order of the pre-order
 */	
	
	/*pre-order(NLR) - similar to non recursive breath first traversal, instead of queue use stack
	 * initial check, 
	 * use stack, 
	 * push root to stack, 
	 * while stack not empty, 
	 * 	pop node from stack, 
	 * 	display/visit-node, 
	 * 	push left if not null
	 * 	then push right if not null
	 * end-while-loop
	 * end
	 */
	
	public void preOrderNonRecursive(Node root){
		if(root==null){
			return;
		}
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);
		while(!stack.empty()){
			Node node = stack.pop();
			System.out.println(node.value);
			if(node.left!=null)
				stack.push(node.left);
			if(node.right!=null)
				stack.push(node.right);
		}
	}
	
	/* in-order(LNR)
	 * initialize node to root 
	 * use stack 
	 * needs two while loop 
	 * outer while node not null or stack not empty 
	 * 	inner while node not null 
	 * 		push node
	 * 		to move to left assign node to left
	 * 	end-inner-while-loop 
	 * 	after inner while loop node is null 
	 *  pop node from stack 
	 *  display/visit node 
	 *  assign node to right node
	 * end-out-while-loop
	 * end
	*/
	public void inOrderNonRecursive(Node root){
		Stack<Node> stack = new Stack<Node>();
		Node node = root;
		while(node!=null || !stack.empty()){
			while(node!=null){
				stack.push(node);
				node = node.left;
			}
			node = stack.pop();
			System.out.println(node.value);
			node = node.right;
		}
	}
	
	public boolean checkBST(Node root){
		Stack<Node> stack = new Stack<Node>();
		Node node = root;
		Node prev = null;
		while(node!=null || !stack.empty()){
			while(node!=null){
				stack.push(node);
				if(node.left!=null && node.left.value > node.value ){
					return false;
				}
				node = node.left;
			}
			node = stack.pop();
			System.out.println("Previous Comparisn: "+(prev==null?"null":prev.value)+"-"+node.value);
			if(prev!=null && node.value <= prev.value){
				return false;
			}
			prev = node;
			System.out.println("Storing as previous: "+node.value);
			System.out.println("Comparing to right: "+node.value+"-"+(node.right==null?"null":node.right.value));
			if(node.right!=null && node.right.value < node.value ){
				return false;
			}
			node = node.right;
		}
		return true;
	}
	
	
	/*out-order(RNL) - similar to in-order with two while loop
	 * initialize node to root
	 * use stack
	 * use two while loop
	 * outer while until node is null OR stack is empty
	 * 	inner while until node is null
	 * 	 push node to stack
	 * 	 assign node to right
	 * 	end-inner-while-loop
	 *  at this point node is null
	 *  pop node from stack
	 *  display/visit-node
	 *  assign node to left
	 * end-outer-while-loop
	 * end
	 */
	public void outOrderNonRecursive(Node root){
		Node node = root;
		Stack<Node> stack = new Stack<Node>();
		while(node!=null || !stack.empty()){
			while(node!=null){
				stack.push(node);
				node = node.right;
			}
			node = stack.pop();
			System.out.println(node.value);
			node = node.left;
		}
	}
	
	/*post-order(LRN) - this one tricker needs two stack. Do pre-order traversal, instead of display/visit push the node in second stack and finally display in reverse order.
	 * initial check
	 * use stack
	 * push root to stack
	 * use another storage stack
	 * while first stack is not empty
	 * 	pop node from stack
	 *  push that into second storage stack
	 *  if left not null push it to first stack
	 *  if right not null push it to first stack
	 * end-while-loop
	 * display node in reverse order from second stack  
	 */
	public void postOrderNonRecursive(Node root){
		if(root==null){
			return;
		}
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);
		Stack<Node> storage = new Stack<Node>();
		while(!stack.empty()){
			Node node = stack.pop();
			storage.push(node);
			if(node.left!=null){
				stack.push(node.left);
			}
			if(node.right!=null){
				stack.push(node.right);
			}
		}
		while(!storage.empty()){
			Node node = storage.pop();
			System.out.println(node.value);
		}
	}
	
	

/* NON-RECURSIVE breadth-first/level-order tree traversals
 * It's not recursive and uses a queue, same as in-order traversal except using stack we use a queue
 */
		
	/*breadth-first traversal - use queue
	 * initial check
	 * use queue
	 * push root to queue
	 * while until queue is empty
	 * 	remove node from queue
	 * 	display/visit-node
	 * 	add left if not null
	 *  add right if not null 
	 * end-while-loop
	 * end
	 */
	public void breadthFirstSearchLevelOrderNonRecursive(Node root){
		if(root==null){
			return;
		}
		Queue<Node> queue = new LinkedList<Node>();
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

/* RECURSIVE breadth-first/level-order tree traversals
 * This one inefficient, needs to calculate height(recursively) of the tree first, then within a while loop for each level display/visit the node
 * The display/visit level function recursively traverse the entire tree and displays or visits the node at that level
 */

	/* Recursive tree height calculation
	 * set initial condition if node null return 0
	 * calculate left height
	 * calculate right height
	 * compare left and right height, choose the higher add one and return
	 * end 
	 */
	public int height(Node root){
		if(root==null){
			return 0;
		}
		int lheight = height(root.left);
		int rheight = height(root.right);
		if(lheight>rheight){
			return lheight+1;
		}else{
			return rheight+1;
		}
	}
	
	public int heightByedges(Node root){
		if(root==null || (root.left==null && root.right==null)){
			return 0;
		}
		int lheight = heightByedges(root.left);
		int rheight = heightByedges(root.right);
		if(lheight>rheight){
			return lheight+1;
		}else{
			return rheight+1;
		}
	}
	
	
	/* Recursively Visit/Display all nodes of a tree in a given level
	 * check initial condition if node is null return
	 * check level if level is 1
	 * 	display/visit node
	 * call display/visit-node of the left child with level-1 parameter
	 * call display/visit-node of the right child with level-1 parameter 
	 */
	public void visitAndDisplayLevel(Node root, int level){
		if(root==null){
			return;
		}
		if(level==1){
			System.out.println(root.value);
		}
		visitAndDisplayLevel(root.left, level-1);
		visitAndDisplayLevel(root.right, level-1);
	}
	
	/* RECURSIVE breadth-first/level-oder search using height & display/visit-node for a given level
	 * calculate height of the tree
	 * for i starting 1 to height
	 * 	call display/visit-node for level i
	 * end-for
	 * end
	 */
	public void breadthFirstLevelOrderRecursiveTraversal(Node root){
		int height = height(root);
		for(int i=1; i<=height; i++){
			visitAndDisplayLevel(root,i);
		}
	}
	
	/*
	 * MAIN METHOD
	 */
	public static void main(String[] args){
		
		TreeTraversal tree = new TreeTraversal();
		Node root = tree.makeTree1Char();
		
//		System.out.println("--");
//		tree.preOrder(root);
//		//F, B, A, D, C, E, G, I, H
//		System.out.println("--");
//		tree.preOrderNonRecursive(root);
//		//F, B, A, D, C, E, G, I, H

		
//		System.out.println("--");
//		tree.postOrder(root);
//		//A, C, E, D, B, H, I, G, F
//		System.out.println("--");
//		tree.postOrderNonRecursive1(root);
//		//A, C, E, D, B, H, I, G, F

		
//		System.out.println("--");
//		tree.inOrder(root);
//		//A, B, C, D, E, F, G, H, I
//		System.out.println("--");
//		tree.inOrderNonRecursive(root);
//		//A, B, C, D, E, F, G, H, I

		
//		System.out.println("--");
//		tree.outOrder(root);
//		//I, H, G, F, E, D, C, B, A
//		System.out.println("--");
//		tree.outOrderNonRecursive(root);
//		//A, B, C, D, E, F, G, H, I

		
//		System.out.println("--");
//		tree.breadthFirstSearchLevelOrderNonRecursive(root);
//		System.out.println("--");
//		//F, B, G, A, D, I, C, E, H
//		System.out.println("--");
//		tree.breadthFirstSearchLevelOrderRecursive(root);
//		F, B, G, A, D, I, C, E, H
		System.out.println(tree.checkBST(root));
//		System.out.println(tree.height(root));
//		System.out.println(tree.heightByedges(root));
	}
}
