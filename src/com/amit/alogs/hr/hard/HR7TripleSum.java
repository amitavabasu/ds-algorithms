package com.amit.alogs.hr.hard;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.stream.Collectors;

public class HR7TripleSum {
	/* 
	 * Found number of combinations possible satisfying the condition a[i]<=b[j]>=c[k]
	 * i, j & k are all the non-duplicate elements of a, b & c. 
	 * The theory to solve this problem is create two long array left & right both have equal size as of b or non duplicate of b which we are calling bbar 
	 * the left will contain the number of elements available in a or abar which are less than or equal to the corresponding element in bbar
	 * the right array will contain the number of elements in cbar which are less than or equal to corresponding element of bbar
	 * finally multiply left[i] * right[i] for all i and add all of them to get the final sum of combinations 
	 */
	
	static long triplets(int[] a, int[] b, int[] c) {
		//Get the array abar from a without duplicates using LinkedHashSet
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>(Arrays.stream(a).boxed().collect(Collectors.toList()));
        Integer[] abar = linkedHashSet.toArray(new Integer[] {});
        //Get back the array bbar without duplicates
        linkedHashSet = new LinkedHashSet<>(Arrays.stream(b).boxed().collect(Collectors.toList()));
        Integer[] bbar = linkedHashSet.toArray(new Integer[] {});
        //Get back the array bbar without duplicates        
        linkedHashSet = new LinkedHashSet<>(Arrays.stream(c).boxed().collect(Collectors.toList()));
        Integer[] cbar = linkedHashSet.toArray(new Integer[] {});        
        
        //Sort all 3 non duplicate arrays
        Arrays.sort(abar);Arrays.sort(bbar);Arrays.sort(cbar);
        //Until this point it is only preparation 
        
        //Define left and right array, they should be long and equal size of array bbar
        long[] left = new long[bbar.length];
        long[] right = new long[bbar.length];
        
        
        int i=0;
        int j=0;
        while(i<abar.length && j<bbar.length) {//<-- traverse all the element of abar & bbar
            if(abar[i]>bbar[j]) {//<-- when an abar element becomes grater than a bbar element 
                left[j] = i;//<-- i is the number of element in abar which less that or equal to the corresponding element in bbar
                j++;//<-- increment index of bbar which is j
            }else {//<-- if abar[i] is less than or equal to corresponding element of bbar[j] need to increment the abar index count i
                i++;//<-- increment abar index count (i)
            }
        }
        while(j<bbar.length) {//<-- if bbar has more elements for those all left[j] will be the length of abar
            left[j] = abar.length;
            j++;
        }
        //System.out.println(Arrays.toString(left));
        i=0;
        j=0;
        while(i<cbar.length && j<bbar.length) {//<-- traverse all ements of cbar & bbar
            if(cbar[i]>bbar[j]) {//<-- when an element in cbar is grater than the element in bbar the number of elements in the right the index value of cbar which is i
                right[j] = i;//<-- assign i to right[j]
                j++;//<-- increment index of bbar
            }else {//<-- if cbar element is smaller or equal increment the index of car which is i
                i++;
            }
        }
        while(j<bbar.length) {//<-- if bbar has more elements for those the right[j] will be the length of cbar
            right[j] = cbar.length;
            j++;
        }
        //System.out.println(Arrays.toString(right));
        long sum = 0;//<-- initialize sum to 0
        for(int k=0;k<left.length; k++) {//<-- for all the lement in left & right (size are same)
            sum += left[k]*right[k]; //<-- calculate sum by ading and multiplying left[k]*right[k]
        }
//		long correct = 17747701952583L;
//		System.out.println(sum-correct);
        return sum;//<-- return sum 
    }	
	
	public static void main(String[] args) throws IOException{
//		int[] a = {3,5,7};
//		int[] b = {3,6};
//		int[] c = {4,6,9};

//		int[] a = {1,3,5};
//		int[] b = {2,3};
//		int[] c = {1,2,3};
		
//		int[] a = {1,4,5};
//		int[] b = {2,3,3};
//		int[] c = {1,2,3};
		
//		int[] a = {1,3,5,7};
//		int[] b = {5,7,9};
//		int[] c = {7,9,11,13};
        //File file = new File("C:\\Users\\amita\\eclipse-workspace\\ds-algorithms\\test-dada\\TripleSum.txt");
		File file = new File("E:\\ds-algorithms\\test-dada\\TripleSum2.txt");
        Scanner scanner = new Scanner(file);
        String[] lenaLenbLenc = scanner.nextLine().split(" ");
        int lena = Integer.parseInt(lenaLenbLenc[0]);
        int lenb = Integer.parseInt(lenaLenbLenc[1]);
        int lenc = Integer.parseInt(lenaLenbLenc[2]);
        int[] arra = new int[lena];
        String[] arraItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        for (int i = 0; i < lena; i++) {
            int arraItem = Integer.parseInt(arraItems[i]);
            arra[i] = arraItem;
        }
        int[] arrb = new int[lenb];
        String[] arrbItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        for (int i = 0; i < lenb; i++) {
            int arrbItem = Integer.parseInt(arrbItems[i]);
            arrb[i] = arrbItem;
        }
        int[] arrc = new int[lenc];
        String[] arrcItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        for (int i = 0; i < lenc; i++) {
            int arrcItem = Integer.parseInt(arrcItems[i]);
            arrc[i] = arrcItem;
        }
        long ans = triplets(arra, arrb, arrc);
        System.out.println(ans);
        scanner.close();
	}
}
