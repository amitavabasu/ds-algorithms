package com.amit.alogs.hr.easy;

public class HR6ElectronicsShop {

	public int getMoneySpent(int[] keyboards, int[] drives, int b) {
		int maxMoneyToSpend = -1;
		if(keyboards==null || drives==null || keyboards.length <= 0 || drives.length <=0 || b==0 ){
			return maxMoneyToSpend;
		}
		for(int i=0; i<keyboards.length; i++){
			int kprice = keyboards[i];
			if(kprice>=b){
				continue;
			}else{
				for(int j=0; j<drives.length; j++){
					int dprice = drives[j];
					if(dprice>=b){
						continue;
					}else if(kprice+dprice>b){
						continue;
					}else{
						//possible hit
						if(kprice+dprice>=maxMoneyToSpend){
							maxMoneyToSpend = kprice+dprice;
						}else{
							//possible better option exists
						}
					}
				}
			}
		}
		return maxMoneyToSpend;
	}
	
	public static void main(String[] args){
//		int[] keyboards = {40,50,60};
//		int[] drives = {5,8,12};
//		int b = 60;
		int[] keyboards = {5,1,1};
		int[] drives = {4};
		int b = 5;
		
		HR6ElectronicsShop sm = new HR6ElectronicsShop();
		System.out.println(sm.getMoneySpent(keyboards,drives,b));
	}
}
