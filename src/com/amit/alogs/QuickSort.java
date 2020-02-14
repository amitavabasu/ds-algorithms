package com.amit.alogs;

import java.util.Arrays;

public class QuickSort {

	private int[] arr;

	private int lenght;

	public void sort(int[] arr) {
		if (arr == null || arr.length == 0)
			return;
		this.arr = arr;
		this.lenght = arr.length;
		this.quickSort(0, this.lenght - 1);
	}

	private void quickSort(int low, int high) {
		int i = low, j = high;
		int pivot = arr[low + (high - low) / 2];
		while (i <= j) {//<-- this is wrong - 1
			while (arr[i] < pivot) {
				i++;
			}
			while (arr[j] > pivot) {
				j--;
			}
			if (i <= j) {//This is wrongly done because of wrong @1
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				i++;
				j--;
			}
		}

		if (low < j)
			quickSort(low, j);
		
		if (i < high) {
			quickSort(i, high);
		}

	}

	public static void main(String[] args) {
		//int[] arr = { 1, 7, 4, 9, 0, 4, 2, 8, 2, 5, 6, 1, 0 };
		int[] arr = {5,4,8,0,2,7,5,6,9,1,3};
		System.out.println(Arrays.toString(arr));
		new QuickSort().sort(arr);
		System.out.println(Arrays.toString(arr));
	}

}