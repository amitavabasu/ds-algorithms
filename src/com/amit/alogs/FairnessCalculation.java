package com.amit.alogs;
import java.io.*;
import java.util.*;

public class FairnessCalculation {
	
	
	
    static int maxMin(int k, int[] arr) {
        Arrays.sort(arr);
        int i=0;
        int j=k-1;
        int minFairness = Integer.MAX_VALUE;
        while(j<arr.length){
            int min = arr[i];
            int max = arr[j];
            int fairness = max-min;
            if(minFairness > fairness){
                minFairness = fairness;
            }
            i++;
            j++;
        }
        return minFairness;
    }

    static File f = new File("C:\\Users\\amita\\eclipse-workspace\\project-2\\test-dada\\1.txt");
    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    	final Scanner scanner = new Scanner(f);
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int k = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            int arrItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            arr[i] = arrItem;
        }

        int result = maxMin(k, arr);

        System.out.println(String.valueOf(result));

        scanner.close();
    }

}
