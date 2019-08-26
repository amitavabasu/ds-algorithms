package com.amit.alogs.hr.hard;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.stream.Collectors;

public class HR7TripleSum {
	
	static long triplets(int[] a, int[] b, int[] c) {
		LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>(Arrays.stream(a).boxed().collect(Collectors.toList()));
        //Get back the array without duplicates
        Integer[] abar = linkedHashSet.toArray(new Integer[] {});
        linkedHashSet = new LinkedHashSet<>(Arrays.stream(b).boxed().collect(Collectors.toList()));
        //Get back the array without duplicates
        Integer[] bbar = linkedHashSet.toArray(new Integer[] {});
        linkedHashSet = new LinkedHashSet<>(Arrays.stream(c).boxed().collect(Collectors.toList()));
        //Get back the array without duplicates
        Integer[] cbar = linkedHashSet.toArray(new Integer[] {});        
		
		Arrays.sort(abar);Arrays.sort(bbar);Arrays.sort(cbar);
		int[] left = new int[bbar.length];
		int[] right = new int[bbar.length];
		int i=0;
		int j=0;
		while(i<abar.length && j<bbar.length) {
			if(a[i]>b[j]) {
				left[j] = i;
				j++;
			}else {
				i++;
			}
		}
		while(j<bbar.length) {
			left[j] = abar.length;
			j++;
		}
		System.out.println(Arrays.toString(left));
		i=0;
		j=0;
		while(i<cbar.length && j<bbar.length) {
			if(c[i]>b[j]) {
				right[j] = i;
				j++;
			}else {
				i++;
			}
		}
		while(j<bbar.length) {
			right[j] = c.length;
			j++;
		}
		System.out.println(Arrays.toString(right));
		long sum = 0;
		for(int k=0;k<left.length; k++) {
			sum += left[k]*right[k];
		}
		long correct = 12603652660415L;
		System.out.println(sum-correct);
		return sum;
		//12603652660415
		
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
    	//Scanner scanner = new Scanner(System.in);
		
		//test-1 getting: , correct: 9593177511025
		//test-2 getting: , correct: 17747701952583
		//test-3 getting: 13172449832198, correct: 12603652660415
		
        File file = new File("C:\\Users\\amita\\eclipse-workspace\\ds-algorithms\\test-dada\\TripleSum3.txt");
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
		//System.out.println(triplets(a,b,c));
        scanner.close();
	}
}
