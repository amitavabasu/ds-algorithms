package com.amit.alogs.hr.hard;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class HR10CastleMoveProblem {
	/*
	 * Problem to solve - minimum move required by a castle to go to destination x y from  starting x y co-ordinate location.
	 * Input String array grid contains a . if the position is available and X or some other symbol if the position is occupied. The cell which is marked X or occupied needs to be bypassed.
	 */
	
	static int minimumMoves(String[] grid, int startX, int startY, int goalX, int goalY) {
		//A Map a 2D array of integers to store the moved required. This map have equal number of rows and columns of the grid 
		int[][] map;
		//A visit map required to store if a particular cell is visited or not, it is done by storing a 0 or 1 in a given cell. This 2D array is also equal size of the grid 
		int[][] visitMap;
		
		int n = grid.length;
		map = new int[n][n];
		visitMap = new int[n][n];
		
		boolean blockExists = false; //<-- this variable just to detect if there exists any occupied cell or not, if not the moves could be just 1 or 2
		
		for(int i=0;i<n;i++) {//<-- traverse over the entire grid, fill the cell of the map with 100 if that cell is available, if not set the move value as -1 (not reachable). This is initialization code it means if a cell is available we can reach max by 100 moves 
			for(int j=0;j<n;j++) {
				if(grid[i].charAt(j)=='.') {
					map[i][j] = 100;
				}else {
					blockExists = true;
					map[i][j] = -1;
				}
			}
		}
		//If no occupied cell exists to simplify the answer just check x & y coordinates and return appropriate move required. It could be either 0, 1, or 2
		if(!blockExists) {
			if(startX==goalX)
				return 1;
			if(startY==goalY)
				return 1;
			return 2;
		}
		Queue<int[]> queue = new LinkedList<>(); //<-- will use a queue to hold all possible moves from a given position. The queue stores an array of integer. The array size is 2 one for x and one for y
		queue.add(new int[]{startX,startY});//<-- put start position in the queue
		map[startX][startY] = 0;//Also set start location move as 0
		while(!queue.isEmpty()) {//<-- repeat until queue is empty 
			int[] currentPos = queue.remove();//<-- remove an element from the queue
			if(visitMap[currentPos[0]][currentPos[1]]==0) {//<-- check if this position just removed from queue is already visited or not, if visited just skip this position
				
				visitMap[currentPos[0]][currentPos[1]] = 1;//<-- mark this position as visited
				//get the current x & y position from the element (int array) from queue
				int x = currentPos[0];
			    int y = currentPos[1];
			    int currentMoveValue = map[x][y];//<-- get the current positions move number or value 
			    for(int i=x; i<n && map[i][y]!=-1;i++){//<-- for horizontal right/positive axis moves start index (i) with x until less than n and also check map[index][y] is not -1 (blocked) cell
			        if(map[i][y] > currentMoveValue+1)//<-- check that the current map cell value is grater than the currentMoveValue+1, because all available map cell initialized to 100 
			        								  //this number could be either 100 or a number grater than currentMoveValue+1 or a number less than or equal to currentMoveValue+1
			        								  //for first two cases we should set the value to currentMoveValue+1, i.e. reduced value because we can move to this cell with that many moves
			        								  //we can ignore the third scenario as already this cell is accessible by number of moves which or less than currentMoveValue+1 
			        	map[i][y] = currentMoveValue+1;
			        queue.add(new int[] {i,y});//<-- store this new cell coordinate into queue to explore later
			    }
			    for(int i=x; i>=0 && map[i][y]!=-1;i--){//<-- this is same for horizontal left/negative moves. Start index (i) with x and go until index >= 0 to the left  
			        if(map[i][y] > currentMoveValue+1)
			        	map[i][y] = currentMoveValue+1;
			        queue.add(new int[] {i,y});
			    }
			    for(int i=y; i<n && map[x][i]!=-1;i++){//<-- this is same for vertical up/positive moves. Start index (i) with y and go until index < n to the up
			        if(map[x][i] > currentMoveValue+1)
			        	map[x][i] = currentMoveValue+1;
			        queue.add(new int[] {x,i});
			    }
			    for(int i=y; i>=0 && map[x][i]!=-1;i--){//<-- this is same for vertical down/negative moves. Start index (i) with y and go until index >= 0 to the down
			        if(map[x][i] > currentMoveValue+1)
			        	map[x][i] = currentMoveValue+1;
			        queue.add(new int[] {x,i});
			    }				
			}
		}
		for(int i=0; i<n; i++) {
			System.out.println(Arrays.toString(map[i]));//<-- print map values for visualization purpose
		}
		
		if(map[goalX][goalY]!=100) {//Check that the cell value of map at the goalX & goalY is 100 or not, if it's still 100 the goal is not reachable due to blocks in it's path
			return map[goalX][goalY]; //<-- finally return the cell value at goalX & goalY
		}else {
			return -1;//<-- return -1 if the goalX & goalY is not reachable
		}
    }	    
	    
	    
	    public static void main(String[] args) throws IOException {
//	    	String[] grid = {".X.",".X.","..."};
//	    	int startX = 0;
//	    	int startY = 0;
//	    	int goalX = 0;
//	    	int goalY = 2;
//	    	System.out.println(minimumMoves(grid,startX,startY,goalX,goalY));
	    	
	    	
//	    	Scanner scanner = new Scanner(System.in);
	        File file = new File("C:\\Users\\amita\\eclipse-workspace\\ds-algorithms\\test-dada\\CastleMoveTest.txt");
	        Scanner scanner = new Scanner(file);
	        int n = scanner.nextInt();
	        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

	        String[] grid = new String[n];

	        for (int i = 0; i < n; i++) {
	            String gridItem = scanner.nextLine();
	            grid[i] = gridItem;
	        }

	        String[] startXStartY = scanner.nextLine().split(" ");

	        int startX = Integer.parseInt(startXStartY[0]);

	        int startY = Integer.parseInt(startXStartY[1]);

	        int goalX = Integer.parseInt(startXStartY[2]);

	        int goalY = Integer.parseInt(startXStartY[3]);

	        int result = minimumMoves(grid, startX, startY, goalX, goalY);
	        
	        System.out.println(result);
	        scanner.close();
	    	
	    	
	    	
	   }
}
