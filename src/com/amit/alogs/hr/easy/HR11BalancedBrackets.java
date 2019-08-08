package com.amit.alogs.hr.easy;

import java.util.Stack;

public class HR11BalancedBrackets {

	public String isBalanced(String s) {
		if(s==null || s.length() < 2){
			return "NO";
		}
		Stack<Character> stack = new Stack<>();
		for(int i=0; i<s.length(); i++){
			Character c = s.charAt(i);
			if(c=='[' || c=='{' || c=='('){
				stack.push(c);
			}else{
				if(stack.isEmpty()){
					return "NO";
				}
				Character cbar = stack.pop();
				if((c==')' && cbar=='(')||(c=='}' && cbar=='{')||(c==']' && cbar=='[')){
					continue;
				}else{
					return "NO";
				}
			}
		}
		if(stack.isEmpty()){
			return "YES";
		}else{
			return "NO";
		}
	}
	
	public static void main(String[] args){
		String s = "{[()]}";
		HR11BalancedBrackets sm = new HR11BalancedBrackets();
		System.out.println(sm.isBalanced(s));
	}
}
