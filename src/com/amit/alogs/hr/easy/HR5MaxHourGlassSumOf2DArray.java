package com.amit.alogs.hr.easy;

public class HR5MaxHourGlassSumOf2DArray {

	public int hourglassSum(int[][] arr) {
		if(arr.length !=6 && arr[0].length !=6)
			return -1;
		int maxSum = Integer.MIN_VALUE;
		int[][] hourGlassSums = new int[4][4];
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				int sum = (arr[i][j]+arr[i][j+1]+arr[i][j+2])+(arr[i+1][j+1])+(arr[i+2][j]+arr[i+2][j+1]+arr[i+2][j+2]);
				hourGlassSums[i][j] = sum;
				if(sum>=maxSum){
					maxSum=sum;
				}
			}
		}
		return maxSum;
	}
	
	public static void main(String[] args){
//		int[][] arr = {{-9, -9, -9,  1, 1, 1},
//					   {0, -9,  0,  4, 3, 2},
//					   {-9, -9, -9,  1, 2, 3},
//					   {0,  0,  8,  6, 6, 0},
//					   {0,  0,  0, -2, 0, 0},
//					   {0,  0,  1,  2, 4, 0}};

		int[][] arr = {{-1,-1,0,-9,-2,-2},
					   {-2,-1,-6,-8,-2,-5},
					   {-1,-1,-1,-2,-3,-4},
					   {-1,-9,-2,-4,-4,-5},
					   {-7,-3,-3,-2,-9,-9},
					   {-1,-3,-1,-2,-4,-5}};
		
		HR5MaxHourGlassSumOf2DArray sm = new HR5MaxHourGlassSumOf2DArray();
		System.out.println(sm.hourglassSum(arr));
	}
}
