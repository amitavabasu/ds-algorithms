package com.amit.alogs.hr.medium;

public class HR1NewYearChaos {

	public void minimumBribes(int[] q){
		int bribeCount = 0;
		if(q==null || q.length < 2){
			System.out.println(bribeCount);
		}
		int qLength = q.length;
		//Map<Integer,Integer> shiftMap = new HashMap<Integer, Integer>();
		for(int i=qLength-1; i>=0; i--){
			//System.out.println(Arrays.toString(q));
			int element = q[i];
			int j=i;
			while(element<i+1 && j>=0){
				j--;
				element = q[j];
			}
			int expectedElementIndex = element-1;
			int shiftOfThisElement = expectedElementIndex-j;
			if(shiftOfThisElement>2){
				System.out.println("Too chaotic");
				return;
			}else{
				bribeCount = bribeCount+shiftOfThisElement;
				for(int k=0; k<shiftOfThisElement; k++){
					int temp = q[j];
					q[j] = q[j+1];
					q[j+1] = temp;
					j++;
				}
			}
		}
		System.out.println(bribeCount);
//		List<Integer> keys = new ArrayList<Integer>(shiftMap.keySet());
//		Collections.sort(keys);
//		int totalCount = 0;
//		for(Integer index:keys){
//			Integer shift = shiftMap.get(index);
//			if(shift>=0){
//				bribeCount = bribeCount+shift;
//			}
//			totalCount = totalCount+shift;
//			System.out.println("Element: "+index+"==>"+shift);
//		}
//		System.out.println("TotalCount: "+totalCount+"; BribeCount: "+bribeCount);
	}
	
	public static void main(String[] args){
		int[] q = {2,1,5,3,4};
		//int[] q = {2, 5, 1, 3, 4};
		//int[] q = {2, 1, 5, 4, 3};
		//int[] q = {1, 2, 5, 3, 7, 8, 6, 4};
		HR1NewYearChaos sm = new HR1NewYearChaos();
		sm.minimumBribes(q);
	}
}
