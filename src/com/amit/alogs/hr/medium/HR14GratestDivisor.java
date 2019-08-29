package com.amit.alogs.hr.medium;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class HR14GratestDivisor {
	
	public static int[] findGratestTwoDivisors(int num){
		int n = (int)Math.sqrt(num);
		for(int i=n; i>1; i--){
			if(num%i==0){
				int[] res = new int[]{i,num/i};
				System.out.println(Arrays.toString(res));
				return res;
			}
		}
		return null;
	}
	
    static int downToZero(int n) {
    	if(n==0)
    		return 0;
    	if(n==1)
    		return 1;
    	int res = 0;
    	while(n>0){
    		System.out.println(n+"<-->"+res);
    		System.out.println();
    		int[] gtd = findGratestTwoDivisors(n);
    		if(gtd!=null){
    			n = Math.max(gtd[0], gtd[1]);
    		}else{
    			n -= 1;
    		}
    		res++;
    	}
    	return res;
    }
    
    static int[] minMoves = new int[1000001];
    public static int minMoveRecursive(int n) {
        if (n <= 3) return n;
        if (minMoves[n] > 0) return minMoves[n];
        int min = Integer.MAX_VALUE;
        for (int i=2; i<=Math.sqrt(n); i++) {
            if (n % i == 0) {
                int factor = n/i;
                min = Math.min(min, 1 + minMoveRecursive(factor));
            }
        }
        min = Math.min(min, 1 + minMoveRecursive(n-1));
        minMoves[n] = min;
        return min;
    }
    
    public static int max = 1000001;
    public static int[] nums = new int[max];

    
   public static int minMoveNonRecursive(int n) {
		if (nums[n] > 0) {
			return nums[n];
		}
		// Initialize array
		for (int i = 0; i < max; ++i)
			nums[i] = -1;
		nums[0] = 0;
		nums[1] = 1;
		nums[2] = 2;
		nums[3] = 3;

		// Precompute
		for (int i = 1; i < max; ++i) {
			if (nums[i] == -1 || nums[i] > (nums[i - 1] + 1))
				nums[i] = nums[i - 1] + 1;
			for (int j = 1; j <= i && j * i < max; ++j)
				if (nums[j * i] == -1 || (nums[i] + 1) < nums[j * i])
					nums[j * i] = nums[i] + 1;
		}
		return nums[n];
	}    
	
	public static void main(String[] args) throws IOException{
		int n = 812849;
		//int n=251;
		System.out.println(minMoveNonRecursive(n));
		
//        File file = new File("E:\\ds-algorithms\\test-dada\\GratestDivisorTest.txt");
//        Scanner scanner = new Scanner(file);
//
//        int q = Integer.parseInt(scanner.nextLine().trim());
//
//        for (int qItr = 0; qItr < q; qItr++) {
//            int n = Integer.parseInt(scanner.nextLine().trim());
//
//            int result = downToZero(n);
//            System.out.println(result);
//        }
//        
//        scanner.close();
	}
}


//int main() {
//    int max = 1000001;
//    int nums[max];
//    
//    //Initialize array
//    for(int i = 0; i < max; ++i) nums[i] = -1;
//    nums[0] = 0; nums[1] = 1; nums[2] = 2; nums[3] = 3;
//    
//    //Precompute
//    for(int i = 0; i < max; ++i){
//        if(nums[i] == -1 || nums[i] > (nums[i - 1] + 1))
//            nums[i] = nums[i - 1] + 1;
//        for(int j = 1; j <= i && j * i < max; ++j)
//            if(nums[j * i] == -1 || (nums[i] + 1) < nums[j * i])
//                nums[j * i] = nums[i] + 1;
//    }
//    
//    //Example for 1st 20
//    //for(int i = 0; i < 21; ++i)
//        //cout << "Min for " << i << " is " << nums[i] << endl;
//    
//    int q; cin >> q;
//    for(int i = 0; i < q; ++i){
//        int n; cin >> n;
//        cout << nums[n] << endl;
//    }
//    return 0;
//}