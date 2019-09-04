package com.amit.alogs.hr.medium;

public class HR15CrossWordPuzzle {
	
    static int superDigit(String n, int k) {
        n = n.chars().mapToLong(Character::getNumericValue).sum() * k
                + "";
        return (n.length() > 1) ? superDigit(n, 1) :
                                    Character.getNumericValue(n.charAt(0));
    }

	
	static boolean fillUp(String[] crossword, String word) {
		boolean res = false;
		int i=0;
		int row = 0;
		int col =0;
		while(i<word.length() && row<10){
			char c = word.charAt(i);
			String rowStr = crossword[row];
			while(col<10){
				char p = rowStr.charAt(col);
				if(c==p){
					if(col!=0 && i!=0 && rowStr.charAt(col-1)==word.charAt(i-1)){
						if(i==word.length()-1){
							if(col+1==10 || rowStr.charAt(col+1)=='+'){
								return true;
							}
						}
						i++;
						col++;
					}
				}else if(p=='-'){
					
				}else{
					col++;
				}
			}
		}
		
		
		
		return res;
	}
	
	
    static String[] crosswordPuzzle(String[] crossword, String words) {
    	
    	return crossword;
    }
	
	
	public static void main(String[] args){
		String[] crossword = {"+-++++++++","+-++++++++","+-------++","+-++++++++","+-++++++++","+------+++","+-+++-++++","+++++-++++","+++++-++++","++++++++++"};
		String words = "AGRA;NORWAY;ENGLAND;GWALIOR";
		for(String s:crosswordPuzzle(crossword, words)){
			System.out.println(s);
		}
	}
}
