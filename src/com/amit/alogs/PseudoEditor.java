package com.amit.alogs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class PseudoEditor {
    private static StringBuffer S = new StringBuffer();
    private static Stack<String> stack = new Stack<>();


//    public static void main(String[] args) throws Exception{
//    	File file = new File("E:\\ds-algorithms\\test-dada\\PseudoEditorTests.txt");
//		//Scanner scanner = new Scanner(System.in);
//    	Scanner scanner = new Scanner(file);
//		int tests = scanner.nextInt();
//		scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//		for (int testsItr = 0; testsItr < tests; testsItr++) {
//			int op = scanner.nextInt();
//			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//			if(op==1){
//				stack.push(S.toString());
//				String W = scanner.next();
//				S.append(W);
//				scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//			}else if(op==2){
//				stack.push(S.toString());
//				int no = scanner.nextInt();
//				if(S.length()-no>=0){
//					S.setLength(S.length()-no);
//				}else{
//					S.setLength(0);
//				}
//			}else if(op==3){
//				int no = scanner.nextInt();
//				if(S.length()>=no)
//					System.out.println(S.charAt(no-1));
//				scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//			}else if(op==4){
//				S = new StringBuffer(stack.pop());
//			}
//		}
//		scanner.close();
//	}
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		Stack<String> stack = new Stack<>();
		List<Character> result = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			String append = null;
			int show = 0;
			int del = 0;
			String inp = br.readLine();
			String arr[] = inp.split(" ");
			int opt = Integer.parseInt(arr[0]);
			switch (opt) {
			case 1:
				append = arr[1];
				sb.append(append);
				stack.push("+" + append.length()); // '+' is used to
													// differentiate between
													// append or delete
													// operation
				break;
			case 2:
				del = Integer.parseInt(arr[1]);
				String delStr = sb.substring(sb.length() - del, sb.length());
				sb.delete(sb.length() - del, sb.length());
				stack.push(delStr);
				break;
			case 3:
				show = Integer.parseInt(arr[1]);
				result.add(sb.charAt(show - 1));
				break;
			case 4:
				String lastOp = stack.pop();
				if (lastOp.charAt(0) == '+') { // undo addition
					sb.delete(sb.length() - Integer.parseInt(lastOp.substring(1)), sb.length()); // omit
																									// first
																									// character,
																									// since
																									// it
																									// is
																									// just
																									// append
																									// operation
																									// marker
																									// (+)
				} else { // undo deletion
					sb.append(lastOp);
				}
			}
		}
		try {
			for (Character j : result)
				System.out.println(j);
		} catch (Exception e) {
		}
	}
}
