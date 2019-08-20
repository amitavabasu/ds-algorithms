package com.amit.alogs.hr.easy;

import java.util.Stack;

public class HR11BalancedBrackets {
	/*
	 * find if the string s contains balanced brackets 'Ex: {{()()()}{()}]()' or not 
	 */
	public String isBalanced(String s) {
		/*
		 * simple algorithm using stack
		 */
		if(s==null || s.length() < 2){
			return "NO";
		}
		Stack<Character> stack = new Stack<>();//<-- use stack
		for(int i=0; i<s.length(); i++){//<-- iterate over all the characters
			Character c = s.charAt(i);//<-- for each starting brackets push it to stack 
			if(c=='[' || c=='{' || c=='('){
				stack.push(c);
			}else{//<-- otherwise
				if(stack.isEmpty()){//<-- check stack empty or not, if empty it's false
					return "NO";
				}
				Character cbar = stack.pop();//<-- pop character
				if((c==')' && cbar=='(')||(c=='}' && cbar=='{')||(c==']' && cbar=='[')){//<-- check if poped character is the 
																						//closing char of the corresponding character
																						//if so continue
					continue;
				}else{//<-- else return false
					return "NO";
				}
			}
		}
		if(stack.isEmpty()){//<-- check if stack is empty or not
			return "YES";//<-- if empty return true
		}else{
			return "NO";//<-- else return false
		}
	}
	
	public static void main(String[] args){
		String s = "{[()]}";
		HR11BalancedBrackets sm = new HR11BalancedBrackets();
		System.out.println(sm.isBalanced(s));
	}
}
