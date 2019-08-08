package com.amit.alogs.hr.hard;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

@SuppressWarnings("unused")
public class HR4BinaryTreeSwapNode {

	static int res_i = 0;
    
	static void inOrder(int[][] indexes, int i, int[] res){
    	if(indexes[i-1][0]>0){inOrder(indexes,indexes[i-1][0], res);}
    	res[res_i++] = i;
    	if(indexes[i-1][1]>0){inOrder(indexes,indexes[i-1][1], res);}
    }
    
    static void calc_depth(int[][] indexes, int i, int depth, int[] depths){
    	depths[i-1] = depth;
    	if (indexes[i-1][0] > 0) calc_depth(indexes, indexes[i-1][0], depth + 1, depths);
    	if (indexes[i-1][1] > 0) calc_depth(indexes, indexes[i-1][1], depth + 1, depths);
    }
	
    static void swap(int[][] indexes, int j){
    	for(int i=j; i<indexes.length; i++){
    		int temp = indexes[i][0];
    		indexes[i][0] = indexes[i][1];
    		indexes[i][1] = temp;
    	}
    }
	
	static int[][] swapNodes(int[][] indexes, int[] queries) {
		res_i = 0;
		int[] begining = new int[indexes.length];
		inOrder(indexes, 1, begining);
    	System.out.println(Arrays.toString(begining));
		int[][] res = new int[queries.length][indexes.length];
    	int n = indexes.length;
    	int[] depth = new int[n];
    	calc_depth(indexes, 1, 1, depth);
    	System.out.println(Arrays.toString(depth));
    	for(int q=0; q<queries.length; q++){
    		int k = queries[q];
    		for(int j=0;j<n; j++){
    			if(depth[j]%k==0){
//    				swap(indexes, j);
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
    
	
	/*
     * Complete the minimumAverage function below.
     */
    public static void main(String[] args) throws IOException {
    	int[][] indexes = {{2, 3},{4, -1},{5, -1},{6, -1},{7, 8},{-1, 9},{-1, -1},{10, 11},{-1, -1},{-1, -1},{-1, -1}};
    	int[] queries = {2,4};
//    	int[][] indexes = {{2, 3},{-1, 4},{-1, 5},{-1, -1},{-1, -1}};
//    	int[] queries = {2};
    	int[][] res = new int[queries.length][indexes.length];
//    	inOrder(indexes, 1, res);
//    	System.out.println(Arrays.toString(res));
//    	int[] depths = new int[indexes.length];
//    	depth_i = 0;
//    	calc_depth(indexes, 1, 1, depths);
    	res = swapNodes(indexes, queries);
    	for(int i=0; i<res.length;i++){
    		System.out.println(Arrays.toString(res[i]));
    	}
    }
}

