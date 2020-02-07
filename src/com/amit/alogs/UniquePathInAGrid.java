package com.amit.alogs;



import java.util.LinkedList;

import java.util.Queue;



public class UniquePathInAGrid {

	public static int uniquePaths(int[][] arr) {
		int pathCount = 0; 
		if(arr==null || arr.length==0 || arr[0].length==0) {
			return pathCount;
		}
		int m = arr.length;
		int n = arr[0].length;
		arr[m-1][n-1] = 9;//<-- mark the destination or goal
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {0,0});
		while(!queue.isEmpty()) {
			int [] pos = queue.remove();
			int row = pos[0];
			int col = pos[1];
			if(arr[row][col] == 9) {//<-- this cell is a destination
				pathCount++;//<-- reached destination so increment path count
			}else {
				arr[row][col] = 2; //<-- mark this cell as visited, not to revisit this cell
			}
			if(col+1 < m && row < n && (arr[row][col+1] == 0 || arr[row][col+1] == 9) ) {//It's a valid cell to visit
				queue.add(new int[] {row, col+1});//Add to queue to visit later
			}
			if(col < m && row+1 < n && (arr[row+1][col] ==0 || arr[row+1][col] == 9) ) {//It's a valid cell to visit
				queue.add(new int[] {row+1, col});//Add to queue to visit later
			}
		}
		return pathCount;
	}

	public static void main(String[] args) {
		int[][] arr = { 
		            {0,0,0,0,0},

		            {0,1,0,0,0},

		            {0,1,0,0,0},

		            {1,1,1,1,1},

		            {0,0,0,0,0}
		            };
		System.out.println(uniquePaths(arr));
	}
}