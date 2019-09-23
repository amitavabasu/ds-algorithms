package com.amit.tests;

import java.util.*;

public class Test2Solutions {

	int minimumTime(int numOfSubFiles, List<Integer> files)
    {	
		//Check for initial condition
        if(numOfSubFiles == 0 || files == null || files.size() == 0){
        	return 0;
        }
        //Initialize minTime
        int minTime = 0;
        //Use a heap or priority queue to get files with min sizes
		Queue<Integer> heap = new PriorityQueue<Integer>();
		//Store all file sizes into the heap
		for(int f:files){
			heap.offer(f);
		}
		//Repeat until heap contains just 1 file
		while(heap.size()>1){
			int first = heap.remove();
			int second = heap.remove();
			int timeToMerge = first+second;
			//calculate total time, and put it back to heap
			minTime += timeToMerge;
			heap.offer(timeToMerge);
		}
		return minTime;
    }
	
    int minimumDistance(int numRows, int numColumns, List<List<Integer>> area){
    	//Use two 2D array.
    	//One to hold the distance and the other to mark a cell is already visited or not 
    	int[][] map, visitedMap;
		map = new int[numRows][numColumns];
		visitedMap = new int[numRows][numColumns];
		//Set destination row and column to -1 (unknown)
		int destR = -1;
		int destC = -1;
		//For each cell in aread
		for(int i=0;i<numRows;i++) {
			for(int j=0;j<numColumns;j++) {
				if(area.get(i).get(j)==1 || area.get(i).get(j)==9){
					map[i][j] = 9999;//<-- if a cell is movable set the value to 9999 (just an imaginary max value)
				}else{
					map[i][j] = -1;//<-- otherwise mark the cell as -1 means not movable.
				}
				if(area.get(i).get(j)==9) {//<-- the destination cell found store the destination index.
					destR=i;
					destC=j;
				}
			}
		}
		int startR = 0;//<-- set start position at 0,0
		int startC = 0;
		Queue<int[]> queue = new LinkedList<>();//<-- use  a queue to generate and calculate distance 
		queue.add(new int[]{startR,startC});
		map[startR][startC] = 0;//<-- store start position in the queue
		while(!queue.isEmpty()) {//<-- until queue is empty
			int[] currentPos = queue.remove();//<-- get top position from queue
			if(visitedMap[currentPos[0]][currentPos[1]]==0) {//<-- if this position is not visited
				visitedMap[currentPos[0]][currentPos[1]] = 1;//<-- mark the position as visited
				
				int r = currentPos[0];//<-- get row and column value from position in the queue
			    int c = currentPos[1];
			    
			    int currentValue = map[r][c];//<-- get what is the move value of the current position
			    
			    if(r+1<numRows && map[r+1][c]!=-1){//<-- if the next right row is not -1
			        if(map[r+1][c] > currentValue+1)//<-- and the cell value of that is grater than current cell value, increment that value. 
			        	map[r+1][c] = currentValue+1;//<-- increment by 1 the next row value
			        queue.add(new int[] {r+1,c});//<-- store the index of this cell for further exploration
			    }
			    if(r-1>=0 && map[r-1][c]!=-1){//<-- do same for the cell left to the current cell
			        if(map[r-1][c] > currentValue+1)
			        	map[r-1][c] = currentValue+1;
			        queue.add(new int[] {r-1,c});
			    }
			    if(c+1<numColumns && map[r][c+1]!=-1){//<-- do the same for the cell just above the current cell
			        if(map[r][c+1] > currentValue+1)
			        	map[r][c+1] = currentValue+1;
			        queue.add(new int[] {r,c+1});
			    }
			    if(c-1>=0 && map[r][c-1]!=-1){//<-- do the same for the cell just below the current cell
			        if(map[r][c-1] > currentValue+1)
			        	map[r][c-1] = currentValue+1;
			        queue.add(new int[] {r,c-1});
			    }
			}
		}
		//Just for test to print entire map
		for(int i=0; i<numRows; i++){
			System.out.println(Arrays.toString(map[i]));
		}
		//If the maps destination cell value is still 9999 then the cell is not reachable so return -1, else return the cell value
		if(map[destR][destC]!=9999){
			return map[destR][destC];
		}else{
			return -1;
		}
    }
	
	
	
	public static void main(String[] args){
//		int numOfSubFiles = 4;
//		List<Integer> files = new ArrayList<>();
//		files.add(4);
//		files.add(8);
//		files.add(6);
//		files.add(12);
		Test2Solutions sol = new Test2Solutions();
//		System.out.println(sol.minimumTime(numOfSubFiles, files));
//		System.out.println("--finish--");
		
		int numRows = 3;
		int numColumns = 3;
		List<Integer> row = new ArrayList<>();
		row.add(1);
		row.add(0);
		row.add(0);
		List<List<Integer>> area = new ArrayList<>();
		area.add(row);
		row = new ArrayList<>();
		row.add(1);
		row.add(1);
		row.add(0);
		area.add(row);
		row = new ArrayList<>();
		row.add(1);
		row.add(1);
		row.add(9);
		area.add(row);
		System.out.println(sol.minimumDistance(numRows,numColumns,area));
		System.out.println("--finish--");
	}
}
