package com.amit.alogs.hr.hard;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

@SuppressWarnings("unused")
public class HR4BinaryTreeSwapNode {
	
	/*
	 * Swapping node of a binary tree received as and indexes of array
	 *  
	 */

	static int res_i = 0;
    
	static void inOrder(int[][] indexes, int i, int[] res){ //<-- this method to do in-order traversal of the tree 
    	if(indexes[i-1][0]>0){inOrder(indexes,indexes[i-1][0], res);}
    	res[res_i++] = i;//<-- why the index not the value?
    	if(indexes[i-1][1]>0){inOrder(indexes,indexes[i-1][1], res);}
    }
    
    static void calc_depth(int[][] indexes, int i, int depth, int[] depths){//<-- calculates the depth of the tree for each node in an array called depths
    	depths[i-1] = depth;
    	if (indexes[i-1][0] > 0) calc_depth(indexes, indexes[i-1][0], depth + 1, depths);
    	if (indexes[i-1][1] > 0) calc_depth(indexes, indexes[i-1][1], depth + 1, depths);
    }
	
	static int[][] swapNodes(int[][] indexes, int[] queries) {
		res_i = 0;
		int[] begining = new int[indexes.length];
		inOrder(indexes, 1, begining);//<-- just to print the initial tree
    	System.out.println(Arrays.toString(begining));
		int[][] res = new int[queries.length][indexes.length];//<-- each result contains the in-order traversal after swap(s)
    	int n = indexes.length;
    	int[] depth = new int[n];
    	calc_depth(indexes, 1, 1, depth);//<-- depth array
    	System.out.println(Arrays.toString(depth));
    	for(int q=0; q<queries.length; q++){
    		int k = queries[q];
    		for(int j=0;j<n; j++){
    			if(depth[j]%k==0){//<-- if depth of the array is multiple of k then swap the nodes
    				int lnode = indexes[j][0];
    				int rnode = indexes[j][1];
    				indexes[j][0] = rnode;
    				indexes[j][1] = lnode;
    			}
    		}
    		res_i = 0;
    		int[] singleRes = new int[n];
    		inOrder(indexes, 1, singleRes);
    		res[q] = singleRes;
    	}
    	return res;
    }
    
	
    public static void main(String[] args) throws IOException {
    	int[][] indexes = {{2, 3},{4, -1},{5, -1},{6, -1},{7, 8},{-1, 9},{-1, -1},{10, 11},{-1, -1},{-1, -1},{-1, -1}};
    	int[] queries = {2,4};
    	int[][] res = new int[queries.length][indexes.length];
    	res = swapNodes(indexes, queries);
    	for(int i=0; i<res.length;i++){
    		System.out.println(Arrays.toString(res[i]));
    	}
    }
}

