package com.amit.alogs.hr.easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static class SinglyLinkedListNode {
        public int data;
        public SinglyLinkedListNode next;

        public SinglyLinkedListNode(int nodeData) {
            this.data = nodeData;
            this.next = null;
        }
    }

    static class SinglyLinkedList {
        public SinglyLinkedListNode head;
        public SinglyLinkedListNode tail;

        public SinglyLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void insertNode(int nodeData) {
            SinglyLinkedListNode node = new SinglyLinkedListNode(nodeData);

            if (this.head == null) {
                this.head = node;
            } else {
                this.tail.next = node;
            }

            this.tail = node;
        }
    }

    public static void printSinglyLinkedList(SinglyLinkedListNode node) throws IOException {
        while (node != null) {
            System.out.print(node+"->"+String.valueOf(node.data));
            node = node.next;
            if (node != null) {
            	System.out.print(" ");
            }
        }
        System.out.println();
    }

    // Complete the mergeLists function below.

    /*
     * For your reference:
     *
     * SinglyLinkedListNode {
     *     int data;
     *     SinglyLinkedListNode next;
     * }
     *
     */
    static void add(SinglyLinkedListNode head, 
    SinglyLinkedListNode last, 
    SinglyLinkedListNode node) {
        if(last==null){
            last = node;
            head = last;
        }else{
            last.next = node;
            last =last.next;
        }
    }

    static SinglyLinkedListNode mergeLists(SinglyLinkedListNode head1,
    SinglyLinkedListNode head2) {
        if(head1==null && head2==null){
            return null;
        }
        if(head1==null){
        	return head2;
        }
        if(head2==null){
        	return head1;
        }
        boolean done = false;
        SinglyLinkedListNode last = null;
        SinglyLinkedListNode head = null;
        SinglyLinkedListNode nodeTobeAdded = null;
        while(!done){
            if(head1==null){
                if(head2==null){
                    done = true;
                    nodeTobeAdded = null;
                }else{
                	nodeTobeAdded = head2;
                	done = true;
                }
            }else{
                if(head2==null){
                	nodeTobeAdded = head1;
                	done = true;
                }else{
                    if(head1.data<head2.data){
                    	nodeTobeAdded = head1;
                        head1 = head1.next;
                    }else{
                    	nodeTobeAdded = head2;
                        head2 = head2.next;
                    }
                }
            }
            if(nodeTobeAdded!=null){
            	if(last==null){
            		last=nodeTobeAdded;
            		head = last;
            	}else{
            		last.next = nodeTobeAdded;
            		last = last.next;
            	}
            }else{
            	last.next = null;
            }
        }
        return head;
    }

    static int getNode(SinglyLinkedListNode head, int positionFromTail) {
        if(head==null || positionFromTail < 0){
            return -1;
        }
        SinglyLinkedListNode node = head;
        SinglyLinkedListNode tracker = null;
        int i =0;
        while(i<positionFromTail && node.next!=null){
            node = node.next;
            i++;
        }
        tracker = head;
        while(node.next!=null){
           tracker = tracker.next;
           node=node.next;
        }
        if(tracker!=null){
            return tracker.data;
        }else{
            return -1;
        }
    }
    
    static SinglyLinkedListNode removeDuplicates(SinglyLinkedListNode head) {
        if(head==null || head.next==null){
            return head;
        }
        SinglyLinkedListNode checker = head;
        SinglyLinkedListNode node = head.next;
        while(node!=null){
            if(checker.data==node.data){
                node = node.next;
            }else{
                checker.next = node;
                checker = checker.next;
                node = node.next;
            }
        }
        checker.next = node;
        return head;
    }
    
    static int findMergeNode(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        if(head1==null || head2==null){
            return 0;
        }
        int count1 = 0;
        int count2 = 0;
        SinglyLinkedListNode node = head1;
        while(node!=null){
            node = node.next;
            count1++;
        }
        node = head2;
        while(node!=null){
            node = node.next;
            count2++;
        }
        int diff = Math.abs(count1-count2);
        SinglyLinkedListNode checker = null;
        if(count1==count2){
            node = head1;
            checker = head2;
        }else{
            if(count1>count2){
                node = head1;
                checker = head2;
            }else{
                node = head2;
                checker = head1;
            }
            for(int i=0; i<diff; i++){
                node = node.next;
            }
        }
        while(node!= null && checker!=null){
            if(node==checker){
                return node.data;
            }else{
                node = node.next;
                checker = checker.next;
            }
        }
        return 0;
    }
    //private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        File file = new File("E:\\MyOwnProject\\data\\test.txt");
        Scanner scanner = new Scanner(file);
    	
//    	int[] values = {1,2,2,3,4,5,5,5,6,7,7,7,8,9,9,9,9,10};
//    	SinglyLinkedList llist = new SinglyLinkedList();
//        for (int val:values){
//                llist.insertNode(val);
//        }
//        SinglyLinkedListNode result = removeDuplicates(llist.head);
//        printSinglyLinkedList(result);
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

//        int tests = scanner.nextInt();
//        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//
//        for (int testsItr = 0; testsItr < tests; testsItr++) {
//            int index = scanner.nextInt();
//            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//
//            SinglyLinkedList llist1 = new SinglyLinkedList();
//
//            int llist1Count = scanner.nextInt();
//            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//
//            for (int i = 0; i < llist1Count; i++) {
//                int llist1Item = scanner.nextInt();
//                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//
//                llist1.insertNode(llist1Item);
//            }
//          
//          	SinglyLinkedList llist2 = new SinglyLinkedList();
//
//            int llist2Count = scanner.nextInt();
//            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//
//            for (int i = 0; i < llist2Count; i++) {
//                int llist2Item = scanner.nextInt();
//                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//
//                llist2.insertNode(llist2Item);
//            }
//          
//          	SinglyLinkedListNode ptr1 = llist1.head;
//            SinglyLinkedListNode ptr2 = llist2.head;
//
//            for (int i = 0; i < llist1Count; i++) {
//                if (i < index) {
//                    ptr1 = ptr1.next;
//                }
//            }
//
//            for (int i = 0; i < llist2Count; i++) {
//                if (i != llist2Count-1) {
//                    ptr2 = ptr2.next;
//                }
//            }
//
//            ptr2.next = ptr1;
////            printSinglyLinkedList(llist1.head);
////            printSinglyLinkedList(llist2.head);
//            int result = findMergeNode(llist1.head, llist2.head);
//            System.out.println(String.valueOf(result));
//        }
//        scanner.close();
//        int[] arr = {3,-7,0};
//        System.out.println(minimumAbsoluteDifference(arr));
//        int money = 5;
//        int[] cost = {2, 1, 3, 5, 6};
//        whatFlavors(cost,money);
//          List<List<Integer>> queries = new ArrayList<>();
//          List<Integer> query = new ArrayList<>();
//          query.add(1);query.add(3);
//          queries.add(query);
//          query = new ArrayList<>();
//          query.add(2);query.add(3);
//          queries.add(query);
//          query = new ArrayList<>();
//          query.add(3);query.add(2);
//          queries.add(query);
//          query = new ArrayList<>();
//          query.add(1);query.add(4);
//          queries.add(query);          
//          query = new ArrayList<>();
//          query.add(1);query.add(5);
//          queries.add(query);          
//          query = new ArrayList<>();
//          query.add(1);query.add(5);
//          queries.add(query);
//          query = new ArrayList<>();
//          query.add(1);query.add(4);
//          queries.add(query);
//          query = new ArrayList<>();
//          query.add(3);query.add(2);
//          queries.add(query);
//          query = new ArrayList<>();
//          query.add(2);query.add(4);
//          queries.add(query);
//          query = new ArrayList<>();
//          query.add(3);query.add(2);
//          queries.add(query);
//          int[][] queries = {{1, 5},{1, 6},{3, 2},{1, 10},{1, 10},{1, 6},{2, 5},{3, 2}};
//          List<Integer> res = freqQuery(queries);
//          for(Integer i:res){
//        	  System.out.println(i);
//          }
        String s1 = "OUDFRMYMAW";
        String s2 = "AWHYFCCMQX";
        System.out.println(commonChild(s1,s2));
        
        
    }
    
    static int commonChild(String s1, String s2) {
    	List<Integer> counts = new ArrayList<>();
    	int count=0;
    	if(s1==null||s1.length()==0||s2==null||s2.length()==0){
    		return count;
    	}
    	Map<Character,List<Integer>> countMap = new HashMap<>();
    	for(int i=0; i<s1.length(); i++){
    		char c = s1.charAt(i);
    		List<Integer> indexes = countMap.get(c);
    		if(indexes==null){
    			indexes = new ArrayList<>();
    			indexes.add(i);
    			countMap.put(c, indexes);
    		}else{
    			indexes.add(i);
    			countMap.put(c, indexes);
    		}
    	}
    	for(int i=0; i<s2.length(); i++){
    		char c = s2.charAt(i);
    		List<Integer> indexes = countMap.get(c);
    		if(indexes==null||indexes.size()==0){
    			continue;
    		}else{
    			for(Integer index:indexes){
    				if(index>=i){
    					count++;
    					break;
    				}
    			}
    		}
    	}
    	return count;
    }
    
    
    
    static List<Integer> freqQuery(int[][] queries) {
    	List<Integer> res = new ArrayList<>();
    	if(queries==null || queries.length==0){
    		return null;
    	}
    	Map<Integer, Integer> elementFreeqMap = new HashMap<>();
    	Map<Integer, Integer> freqCountMap = new HashMap<>();
		int currentFreq = 0;
		int currFreqElemCount = 0;
    	for(int[] query:queries){
    		int command = query[0];
    		int param = query[1];
    		if(command==1){
    			currentFreq = elementFreeqMap.getOrDefault(param, 0);
    			currFreqElemCount = freqCountMap.getOrDefault(currentFreq, 0);
    			if(currFreqElemCount>0){
    				freqCountMap.put(currentFreq, currFreqElemCount-1);
    			}
    			currentFreq ++;
    			elementFreeqMap.put(param,currentFreq);
    			currFreqElemCount = freqCountMap.getOrDefault(currentFreq, 0);
   				freqCountMap.put(currentFreq, currFreqElemCount+1);
    		}else if(command==2){
				currentFreq = elementFreeqMap.getOrDefault(param, 0);
				if(currentFreq>0){
					currFreqElemCount = freqCountMap.getOrDefault(currentFreq, 0);
					if(currFreqElemCount>0){
						freqCountMap.put(currentFreq, currFreqElemCount-1);
					}
					currentFreq--;
					elementFreeqMap.put(param,currentFreq);
					currFreqElemCount = freqCountMap.getOrDefault(currentFreq, 0);
					freqCountMap.put(currentFreq, currFreqElemCount+1);
				}
    		}else if(command==3){
    			currFreqElemCount = freqCountMap.getOrDefault(param, 0);
    			if(currFreqElemCount==0){
    				res.add(0);
    			}else{
    				res.add(1);
    			}
    		}else{
    			//error
    		}
    	}
    	return res;
    }
    
    
    static long countTriplets(List<Long> arr, long r) {
    	//map which contains the count of elements
		Map<Long, Long> elementMapWithOccuranceCount = new HashMap<>();
		//map contains the triplet count for each element
		Map<Long, Long> elementMapWithTripletCount = new HashMap<>();
		long count = 0;
		for (int i = 0; i < arr.size(); i++) {
			long a = arr.get(i);
			long key = a / r;
			
			if (elementMapWithTripletCount.containsKey(key) && a % r == 0) {
				//Have previous count so increment the count
				count += elementMapWithTripletCount.get(key);
			}
			
			if (elementMapWithOccuranceCount.containsKey(key) && a % r == 0) {
				//Have a matching member in triplet, check it's count and put that in triplet count, 
				//that many triplet will be available in the array for this count, only if triplet count available, other wise 0 
				long elementCount = elementMapWithOccuranceCount.get(key);
				elementMapWithTripletCount.put(a, elementMapWithTripletCount.getOrDefault(a, 0L) + elementCount);
			}
			//By default put it every time we find a new element, this map also keeps count but only for elements
			elementMapWithOccuranceCount.put(a, elementMapWithOccuranceCount.getOrDefault(a, 0L) + 1); // Every number can be the start of a triplet.
		}
		return count;
	}    
    
    
    static int minimumAbsoluteDifference(int[] arr) {
    	if(arr==null || arr.length==0 || arr.length==1) {
    		return 0;
    	}
    	int minAbsDiff = Integer.MAX_VALUE;
    	Arrays.sort(arr);
    	for(int i=1; i<arr.length; i++) {
    		int absDiff = Math.abs(arr[i]-arr[i-1]);
    		System.out.println(absDiff);
    		if(minAbsDiff>absDiff) {
    			minAbsDiff = absDiff;
    		}
    	}
    	return minAbsDiff;
    }
    
    static void whatFlavors(int[] cost, int money) {
        if(cost==null || cost.length<2 || money==0) {
        	return;
        }
        for(int i=0;i<cost.length;i++) {
        	int flavourCost = cost[i];
        	int moneyLeft = money-flavourCost;
        	if(moneyLeft<=0) {
        		continue;
        	}
        	for(int j=i+1; j<cost.length; j++) {
        		int nextFlavourCost = cost[j];
        		if(nextFlavourCost==moneyLeft) {
        			System.out.println((i+1)+" "+(j+1));
        			return;
        		}
        	}
        }
    }
    
    static int luckBalance(int k, int[][] contests) {
        int impCCount = 0;
        int totalLBalance = 0;
        List<Integer> impCList = new ArrayList<>();
        for(int i=0; i<contests.length; i++){
            totalLBalance = totalLBalance + contests[i][0];
            if(contests[i][1]==1){
                impCCount ++;
                impCList.add(contests[i][0]);
            }
        }
        if(k<impCCount) {
        	Collections.sort(impCList);
        	int n = impCCount - k;
        	for(int i=0;i<n; i++){
        		totalLBalance = totalLBalance - impCList.get(i) * 2;
        	}
        }
        return totalLBalance;
    }
    
}
