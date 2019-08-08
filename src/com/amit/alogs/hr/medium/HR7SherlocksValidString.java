package com.amit.alogs.hr.medium;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class HR7SherlocksValidString {
	
    static String isValid(String s) {
    	if(s==null || s.length()==0 || s.length()==1){
    		return "YES";
    	}
    	int[] counts = new int[128];
    	for(int i=0; i<s.length(); i++){
    		char c = s.charAt(i);
    		counts[c] = counts[c]+1;
    	}
    	int count1 = 0;
    	int count1Occurances = 0;
    	int count2 = 0;
    	int count2Occurances = 0;
    	for(int i=0; i<128; i++){
    		if(counts[i]==0){
    			continue;
    		}else{
    			if(count1==0){
    				count1 = counts[i];
    				count1Occurances++;
    			}else{
    				if(counts[i]==count1){
    					count1Occurances++;
    				}else{
    					if(count2==0){
    	    				count2 = counts[i];
    	    				count2Occurances++;;
    					}else{
    						if(counts[i]==count2){
    							count2Occurances++;
    						}else{
    							//System.out.println("have more than 2 diff counts");
    							return "NO";
    						}
    					}
    				}
    			}
    		}
    	}//end-for
    	if(count1==1) {
    		if(count1Occurances==1) {
    			return "YES";
    		}else {
    			return "NO";
    		}
    	}else {
    		if(count2==0) {
    			return "YES";
    		}else {
    			if(count2==1) {
    				if(count2Occurances==1) {
    					return "YES";
    				}else {
    					return "NO";
    				}
    			}else {
    				if(count2Occurances==1) {
    					if(Math.abs(count1-count2)>1) {
    						return "NO";
    					}else {
    						return "YES";
    					}
    				}else {
    					return "NO";
    				}
    			}
    		}
    	}
    }


    public static void main(String[] args) throws IOException {
    	//String s = "ibfdgaeadiaefgbhbdghhhbgdfgeiccbiehhfcggchgghadhdhagfbahhddgghbdehidbibaeaagaeeigffcebfbaieggabcfbiiedcabfihchdfabifahcbhagccbdfifhghcadfiadeeaheeddddiecaicbgigccageicehfdhdgafaddhffadigfhhcaedcedecafeacbdacgfgfeeibgaiffdehigebhhehiaahfidibccdcdagifgaihacihadecgifihbebffebdfbchbgigeccahgihbcbcaggebaaafgfedbfgagfediddghdgbgehhhifhgcedechahidcbchebheihaadbbbiaiccededchdagfhccfdefigfibifabeiaccghcegfbcghaefifbachebaacbhbfgfddeceababbacgffbagidebeadfihaefefegbghgddbbgddeehgfbhafbccidebgehifafgbghafacgfdccgifdcbbbidfifhdaibgigebigaedeaaiadegfefbhacgddhchgcbgcaeaieiegiffchbgbebgbehbbfcebciiagacaiechdigbgbghefcahgbhfibhedaeeiffebdiabcifgccdefabccdghehfibfiifdaicfedagahhdcbhbicdgibgcedieihcichadgchgbdcdagaihebbabhibcihicadgadfcihdheefbhffiageddhgahaidfdhhdbgciiaciegchiiebfbcbhaeagccfhbfhaddagnfieihghfbaggiffbbfbecgaiiidccdceadbbdfgigibgcgchafccdchgifdeieicbaididhfcfdedbhaadedfageigfdehgcdaecaebebebfcieaecfagfdieaefdiedbcadchabhebgehiidfcgahcdhcdhgchhiiheffiifeegcfdgbdeffhgeghdfhbfbifgidcafbfcd";
    	//String s = "aabbccddeefghi";
    	String s = "abcdefghhgfedecba";
    	//System.out.println(isValid(s));
    	File file = new File("E:\\MyOwnProject\\data\\test7.txt");
    	Scanner scanner = new Scanner(file);
    	//String s = scanner.nextLine();
        String result = isValid(s);
        System.out.println(result);
        scanner.close();
    	
    	
    }
}
