package com.amit.alogs.hr.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HR9DynamicArray {

	public List<Integer> dynamicArray(int n, List<List<Integer>> queries) {
		List<Integer> result = new ArrayList<Integer>();
		int lastAnswer = 0;
		if(n==0 || queries==null || queries.size()==0){
			return null;
		}
		List<List<Integer>> seqList = new ArrayList<List<Integer>>();
		for(int i=0; i<n; i++){
			List<Integer> seq = new ArrayList<Integer>();
			seqList.add(seq);
		}
		int q = queries.size();
		for(int i=0; i<q; i++){
			List<Integer> query = queries.get(i);
			Integer qind = query.get(0);
			Integer x = query.get(1);
			Integer y = query.get(2);
			if(qind==1){
				List<Integer> seq = seqList.get((x^lastAnswer)%n);
				seq.add(y);
			}else {
				if(qind==2){
					List<Integer> seq = seqList.get((x^lastAnswer)%n);
					Integer element = seq.get(y%seq.size());
					lastAnswer = element;
					result.add(lastAnswer);
				}
			}
		}
		return result;
	}
	
	public static void main(String[] args){
		int n = 2;
		List<List<Integer>> queries = new ArrayList<List<Integer>>();
		Integer[] data1 = {1, 0, 5};
		Integer[] data2 = {1, 1, 7};
		Integer[] data3 = {1, 0, 3};
		Integer[] data4 = {2, 1, 0};
		Integer[] data5 = {2, 1, 1};
		queries.add(Arrays.asList(data1));
		queries.add(Arrays.asList(data2));
		queries.add(Arrays.asList(data3));
		queries.add(Arrays.asList(data4));
		queries.add(Arrays.asList(data5));
		HR9DynamicArray sm = new HR9DynamicArray();
		System.out.println(sm.dynamicArray(n, queries));
	}
}
