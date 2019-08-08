package com.amit.alogs.hr.easy;

public class HR8CatsAndAMouse {

	public String catAndMouse(int x, int y, int z){
        int catAcatchUnit = Math.abs(z-x);
        int catBCatchUnit = Math.abs(z-y);
        if(catAcatchUnit < catBCatchUnit){
            return "Cat A";
        }else {
            if(catBCatchUnit < catAcatchUnit){
                return "Cat B";
            }else {
                if(catAcatchUnit==catBCatchUnit){
                    return "Mouse C";
                }else{
                	return null;
                }
            }
        }
	}
	
	public static void main(String[] args){
//		int x = 1;
//		int y = 3;
//		int z = 2;
		int x = 2;
		int y = 5;
		int z = 4;
		HR8CatsAndAMouse sm = new HR8CatsAndAMouse();
		System.out.println(sm.catAndMouse(x, y, z));
	}
}
