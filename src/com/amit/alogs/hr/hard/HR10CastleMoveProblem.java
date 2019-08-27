package com.amit.alogs.hr.hard;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class HR10CastleMoveProblem {
	
	static int minimumMoves(String[] grid, int startX, int startY, int goalX, int goalY) {
		int[][] map, visitMap;
		int n = grid.length;
		map = new int[n][n];
		visitMap = new int[n][n];
		boolean blockExists = false;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(grid[i].charAt(j)=='.') {
					map[i][j] = 100;
				}else {
					blockExists = true;
					map[i][j] = -1;
				}
			}
		}
		if(!blockExists) {
			return 2;
		}
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[]{startX,startY});
		map[startX][startY] = 0;
		while(!queue.isEmpty()) {
			int[] currentPos = queue.remove();
			if(visitMap[currentPos[0]][currentPos[1]]==0) {
				visitMap[currentPos[0]][currentPos[1]] = 1;
				
				int x = currentPos[0];
			    int y = currentPos[1];
			    int currentMoveValue = map[x][y];
			    for(int i=x; i<n && map[i][y]!=-1;i++){
			        if(map[i][y] > currentMoveValue+1)
			        	map[i][y] = currentMoveValue+1;
			        queue.add(new int[] {i,y});
			    }
			    for(int i=x; i>=0 && map[i][y]!=-1;i--){
			        if(map[i][y] > currentMoveValue+1)
			        	map[i][y] = currentMoveValue+1;
			        queue.add(new int[] {i,y});
			    }
			    for(int i=y; i<n && map[x][i]!=-1;i++){
			        if(map[x][i] > currentMoveValue+1)
			        	map[x][i] = currentMoveValue+1;
			        queue.add(new int[] {x,i});
			    }
			    for(int i=y; i>=0 && map[x][i]!=-1;i--){
			        if(map[x][i] > currentMoveValue+1)
			        	map[x][i] = currentMoveValue+1;
			        queue.add(new int[] {x,i});
			    }				
			}
		}
		for(int i=0; i<n; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		return map[goalX][goalY];
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
