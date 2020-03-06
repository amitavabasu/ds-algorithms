package com.amit.alogs.hr.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoveIntToEnd {
	  public static List<Integer> moveElementToEnd(List<Integer> array, int toMove) {
		    // Write your code here.
				int exchangePosition = 0;
				int currentPosition = 0;
				boolean done = false;
				while(currentPosition<array.size()) {
					while(exchangePosition < array.size() && array.get(exchangePosition)!=toMove){
						exchangePosition++;
					}
					while(currentPosition < array.size() && array.get(currentPosition)==toMove){
						currentPosition++;
					}
					if(exchangePosition < array.size() && currentPosition < array.size() && exchangePosition < currentPosition){
						array.set(exchangePosition, array.get(currentPosition));
						array.set(currentPosition, toMove);
						exchangePosition++;
					}
					currentPosition++;
				}
		    return array;
		  }
	
	  public static void main(String[] args) {
//		  List<Integer> array = new ArrayList<Integer>(Arrays.asList(1, 2, 4, 5, 3));
//		  int toMove = 3;
		  List<Integer> array = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
		  int toMove = 3;		  
		  System.out.println("Result: " +Arrays.toString(moveElementToEnd(array, toMove).toArray()));
	  }
}
