package com.amit.alogs.hr.medium;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HR8MaxArraySum {
	
	 
	static int getSubset(int i, int[] arr, int[] d) {
		if(d[i]!=0) {
			return d[i];
		}
		if(i==arr.length-1) {
			d[i] = arr[i];
		}else if(i==arr.length-2) {
			d[i] = Math.max(arr[i], arr[i+1]);
		}else {
			d[i] = Math.max(arr[i], Math.max(arr[i] + getSubset(i+2, arr, d), getSubset(i+1, arr, d)));
		}
		return d[i];
	}
	
	static void printNonConsicutiveSubsets(int[] arr){
		//int[] arr = {1,2,3,4,5,6,7,8,9};
		for(int i=0;i<arr.length-2; i++){
			StringBuffer s = new StringBuffer();
			s.append(arr[i]);
			//System.out.println(s);
			for(int j=i+2; j<arr.length; j++){
				StringBuffer s1 = new StringBuffer();
				s1.append(s).append(",").append(arr[j]);
				System.out.println(s1);
				int k = j+2;
				int p = k;//store initial k
				boolean done = false;
				if(k>=arr.length){
					done = true;
				}
				while(!done){
					StringBuffer s2 = new StringBuffer();
					s2.append(s1).append(",").append(arr[k]);
					System.out.println(s2);
					k +=2;
					if(k>=arr.length){
						if(p>=arr.length-1){
							done = true;
						}else{
							p++;
							k=p;
						}
					}
				}
//				for(int k=j+2; k<arr.length; k+=2){
//					s1.append(",").append(arr[k]);
//					System.out.println(s1);
//				}
			}
		}
	}
	
	static void printSubArrays(int[] arr){
		for(int i=0;i<arr.length; i++){
			StringBuffer s = new StringBuffer();
			s.append(arr[i]);
			System.out.println(s);
			for(int j=i+1; j<arr.length; j++){
				s.append(",").append(arr[j]);
				System.out.println(s);
			}
		}
	}
	
	
	static int maxSubsetSum(int[] arr) {
        int currentSum = arr[0];
        int previousSum = 0;
        int maxOldPreviousSum = 0;
        for(int i = 1; i < arr.length; i++){
        	System.out.println(currentSum+"-"+previousSum+"-"+maxOldPreviousSum);
            maxOldPreviousSum = Math.max(currentSum,previousSum);
            currentSum = previousSum + arr[i];
            previousSum = maxOldPreviousSum;
        }
        System.out.println(currentSum+"-"+previousSum+"-"+maxOldPreviousSum);
        return Math.max(currentSum, previousSum);
    }
	
    static String twoStrings(String s1, String s2) {
        if(s1==null || s2==null || s1.length()==0 || s2.length()==0){
            return "NO";
        }
        Map<Character, Integer> charCount = new HashMap<>();
        for(int i=0;i<s1.length(); i++){
            Character c = s1.charAt(i);
            Integer count = charCount.get(c);
            if(count==null || count==0){
                charCount.put(c,new Integer(1));
            }else{
                charCount.put(c, count++);
            }
        }
        for(int i=0; i<s2.length(); i++){
        	Character c = s2.charAt(i);
            Integer count = charCount.get(c);
            if(count==null||count==0){
                continue;
            }else{
                return "YES";
            }
        }
        return "NO";
    }
    
    public static long sbtrCount(int n, String s){
    	long count = n;
    	for(int i=0; i<n; i++){
    		//Check it is part of a palindrome like aabaa
    		int distance = 1;
    		while(i-distance >=0 && i+distance<n && s.charAt(i-distance)==s.charAt(i-1) && s.charAt(i+distance) == s.charAt(i-1)){
    			count++;
    			distance++;
    		}
    		int repeats = 0;
    		while(i+1<n && s.charAt(i)==s.charAt(i+1)){
    			repeats++;
    			i++;
    		}
    		count = count + repeats *(repeats+1)/2;
    		//Check if it is a sequence of repeating character
    	}
    	return count;
    }
    
    static int sherlockAndAnagrams(String s) {
        // go through a string and add every value to a hashmap
        HashMap<String, Integer> map = new HashMap<>();
            
        // total of anagrams
        int total = 0;
            
        // for each key, add one to value
        for(int i = 0; i < s.length(); i++) {
            for(int j = i+1; j <= s.length(); j++) {
                // get substring and sort it!
                String sub = s.substring(i,j);
                    
                // sorting the string
                char tempArray[] = sub.toCharArray();
                Arrays.sort(tempArray);
                sub = new String(tempArray);
                    
                if(map.containsKey(sub)){
                    // adds one to last value
                    int oldValue = map.get(sub);
                    // total++          WRONG
                    total+=oldValue; // RIGHT
                    map.put(sub, ++oldValue);
                } else {
                    // add to map if not seen
                    map.put(sub, 1);
                }
            }
        }
        return total;
    }    
    
    
    public static long substrCount3(int length, String s) {
    	long counter = 0;
    	for (int i = 0; i < length; i++) {
    		// if the current symbol is in the middle of palindrome, e.g. aba
    		int offset = 1;
    		while ( i - offset >= 0 
    				&& 
    				i + offset < length 
    				&& 
    				s.charAt(i - offset) == s.charAt(i - 1)
    				&& 
    				s.charAt(i + offset) == s.charAt(i - 1)) {
    			counter++;
    			offset++;
    		}
    		// if this is repeatable characters aa
    		int repeats = 0;
    		while ( i + 1 < length 
    				&& 
    				s.charAt(i) == s.charAt(i + 1)) {
    			repeats++;
    			i++;
    		}
    		counter += repeats * (repeats + 1) / 2;
    	}
    	return counter + length;//<-- length is added for single characters
    }    
    
    static long substrCount2(int n, String s) {
        long count = 0;
        for (int i = 0; i < s.length(); i++) {
            int innerCounter = 1;

            int counterDown = 0;
            int counterUp = 1;
            while ( i - innerCounter >= 0 && i + innerCounter < s.length()
                    && 
                    s.charAt(i - innerCounter) == s.charAt(i - 1) 
                    && 
                    s.charAt(i + innerCounter) == s.charAt(i - 1)) {
                count++;
                innerCounter++;
            }

            while (i - counterDown >= 0 
            		&& 
            		i + counterUp < s.length() 
            		&& 
            		s.charAt(i - counterDown) == s.charAt(i)
                    && s.charAt(i + counterUp) == s.charAt(i)) {
                count++;
                counterDown++;
                counterUp++;
            }
        }

        return count + s.length();
    }    

    static long substrCount(int n, String s) {
    	if(s==null || s.length()==0){
    		return 0;
    	}
    	long ssCount = n;
    	for(int sz=2;sz<=n; sz++){
    		int i =0;
    		int j = (i+sz)-1;
    		while(i<n & j<n){
//   				if(isSpecial(i,j,s)){
//   					ssCount++;
//   				}
    	    	char first = s.charAt(i);
    	    	boolean found = true;
    	    	if((j-i)%2==0){
    	    		int mid = ((j-i)/2)+i;
    	    		for(int k=i,l=j; k<=mid-1; k++,l--){
    	    			if(first==s.charAt(k) && first==s.charAt(l)){
    	    				continue;
    	    			}else{
    	    				found = false;
    	    				break;
    	    			}
    	    		}
    	    	}else{
    	    		int mid = ((j-i)/2)+i;
    	    		for(int k=i,l=j; k<=mid; k++,l--){
    	    			if(first==s.charAt(k) && first==s.charAt(l)){
    	    				continue;
    	    			}else{
    	    				found = false;
    	    				break;
    	    			}
    	    		}
    	    	}
    	    	if(found){
    	    		ssCount++;
    	    	}
   				i++;
   				j++;
    		}
    	}
    	//String s = "asasd";
    	return ssCount;
    }
    
    static boolean isSpecial(int st, int en, String s){
    	char first = s.charAt(st);
    	if((en-st)%2==0){
    		int mid = ((en-st)/2)+st;
    		for(int i=st+1;i<=en; i++){
    			if(first==s.charAt(i) || i==mid){
    				continue;
    			}else{
    				return false;
    			}
    		}
    		return true;
    	}else{
    		for(int i=st+1; i<=en; i++){
    			if(first==s.charAt(i)){
    				continue;
    			}else{
    				return false;
    			}
    		}
    		return true;
    	}
//    	char first = s.charAt(i);
//    	int count = 0;
//    	for(int i=0; i<mid; i++ ){
//    		if(first==s.charAt(i)){
//    			count++;
//    		}else{
//    			return false;
//    		}
//    	}
//    	for(int i=mid; i<n; i++ ){
//    		if(first==s.charAt(i)){
//    			count--;
//    		}else{
//    			return false;
//    		}
//    	}
//    	if(count==0){
//    		return true;
//    	}else{
//    		return false;
//    	}
    }
    
    
    
    
    
	public static void main(String[] args) {
		//int[] arr = {1,2,3,4,5,6,7,8,9};
//		int res = maxSubsetSum(arr);
//		System.out.println(res);
//		printSubArrays(arr);
//		System.out.println("-----");
//		printNonConsicutiveSubsets(arr);
//		String s1 = "hi"; 
//		String s2 = "world";
//		System.out.println(twoStrings(s1,s2));
		String s = "abcd";
		//String s = "aaaa";
		//System.out.println(sbtrCount(s.length(), s));
		System.out.println(sherlockAndAnagrams2(s));
		//System.out.println(isSpecial("aabbaa"));
		
	}
	
    static int sherlockAndAnagrams2(String s) {
    	int totalCount = 0;
    	Map<String,Integer> countMap = new HashMap<>();
    	for(int sz=1; sz<s.length(); sz++){
    		int i=0;
    		int j=i+sz;
    		while(i<s.length() && j<s.length()+1){
    	    	String substr = s.substring(i,j);
    	    	char[] temp = substr.toCharArray();
    	    	Arrays.sort(temp);
    	    	String sortedSubStr = new String(temp);
    	    	if(countMap.containsKey(sortedSubStr)){
    	    		int oldCount = countMap.get(sortedSubStr);
    	    		totalCount = totalCount + oldCount;
    	    		oldCount++;
    	    		countMap.put(sortedSubStr, oldCount);
    	    	}else{
    	    		countMap.put(sortedSubStr, 1);
    	    	}
    	    	i++;
    	    	j++;
    		}
    	}
    	return totalCount;
    }
    
	
	
	//static File f = new File("E:\\MyOwnProject\\data\\07-21-2019-1.txt");
	
//    public static void main(String[] args) throws IOException {
//        
//    	Scanner scanner = new Scanner(f);
//    	
////    	BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
//
//        int n = scanner.nextInt();
//        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//
//        int[] arr = new int[n];
//
//        String[] arrItems = scanner.nextLine().split(" ");
//        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//
//        for (int i = 0; i < n; i++) {
//            int arrItem = Integer.parseInt(arrItems[i]);
//            arr[i] = arrItem;
//        }
//
//        int res = maxSubsetSum(arr);
//
//        System.out.println(String.valueOf(res));
////        bufferedWriter.newLine();
////
////        bufferedWriter.close();
//
//        scanner.close();
//    }
///*
// * static int maxSubsetSum(int[] arr) {
//        int inc = arr[0];
//        int exc = 0;
//        int exc_new;
//        
//        for(int i = 1; i < arr.Length; i++){
//            exc_new = Math.Max(exc,inc);
//            inc = exc + arr[i];
//            exc = exc_new;
//        }
//
//        return Math.Max(exc, inc);
//    }
		
 /*     
 *     
 */
    
	
}

/*
3,7,4,6,5,8

3,4,5
3,4,8
3,6,8
3,4
3,6
3,5
3,8
7,6
7,5
4,5

3,7,4

3,4


*/