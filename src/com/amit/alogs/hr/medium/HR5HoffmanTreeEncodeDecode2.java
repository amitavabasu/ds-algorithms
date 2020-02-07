package com.amit.alogs.hr.medium;

import java.util.*;

abstract class HNode implements Comparable<HNode> {
	int freq;
	char data;
	HNode l;
	HNode r;
	public HNode(int freq) {
		this.freq = freq;
	}

	@Override
	public int compareTo(HNode n) {
		return this.freq - n.freq;
	}
}

class HoffmanLeaf extends HNode{
	public HoffmanLeaf(int freq, char c) {
		super(freq);
		this.freq = freq;
		this.data = c;
	}
}

class HoffmanNode extends HNode{
	public HoffmanNode(HNode l, HNode r) {
		super(l.freq+r.freq);
		this.l = l;
		this.r = r;
	}
}



class HoffmanUtil {

	public HNode buildTree(String text) {
		if(text==null || text.length()==0) {
			return null;
		}else {
			int[] frequency = new int[256];
			for(char c:text.toCharArray()) {
				frequency[c]++;
			}
			PriorityQueue<HNode> heap = new PriorityQueue<HNode>();
			for(int i=0; i<frequency.length; i++) {
				if(frequency[i]>0) {
					HoffmanLeaf leaf = new HoffmanLeaf(frequency[i], (char)i);
					heap.offer(leaf);
				}
			}
			assert heap.size() > 0;
			while(heap.size() > 1) {
				HNode l = heap.poll();
				HNode r = heap.poll();
				heap.offer(new HoffmanNode(l, r));
			}
			return heap.poll();
		}
	}
	Map<Character, String> codeMap = new HashMap<Character, String>();
	public void buildCodeMap(HNode root, StringBuffer prefix) {
		assert root != null;
		if( root instanceof HoffmanLeaf) {
			HoffmanLeaf leaf = (HoffmanLeaf)root;
			codeMap.put(leaf.data, prefix.toString());
		}else if(root instanceof HoffmanNode) {
			HoffmanNode node = (HoffmanNode)root;
			
			prefix.append('0');
			buildCodeMap(node.l,prefix);
			prefix.deleteCharAt(prefix.length()-1);
			
			prefix.append('1');
			buildCodeMap(node.r,prefix);
			prefix.deleteCharAt(prefix.length()-1);
		}
	}

	public String encode(HNode root, String text) {
		buildCodeMap(root, new StringBuffer());
		StringBuffer encodded = new StringBuffer();
		for(char c:text.toCharArray()) {
			encodded.append(this.codeMap.get(c));
		}
		System.out.println(encodded.toString());
        Set<Character> keys = codeMap.keySet();
        for(Character key:keys){
        	System.out.println(key+"==>"+codeMap.get(key));
        }
		return encodded.toString();
	}

	

	public String decode(HNode root, String encodedText) {
		StringBuffer sb = new StringBuffer();
		if(root==null || encodedText==null){
			return sb.toString();
		}else if(encodedText.length() == 0) {
			if(root instanceof HoffmanLeaf) {
				sb.append((char)root.data);
			}else {
				return sb.toString();
			}
		}else {
			HNode node = root;
			int i=0;
			while(i<encodedText.length()) {
				if(node instanceof HoffmanNode) {
					char ec = encodedText.charAt(i);
					if(ec=='0') {
						node = node.l;
					}else if(ec=='1') {
						node = node.r;
					}
					i++;
					if(i==encodedText.length()) {
						sb.append((char)node.data);
					}
				} else {
					sb.append((char)node.data);
					node = root;
				}
			}
		}
		return sb.toString();
	}
}





public class HR5HoffmanTreeEncodeDecode2 {

	public static void main(String[] args) {

	    Scanner input = new Scanner(System.in);

	    String text= input.next();

	    HoffmanUtil util = new HoffmanUtil();

	    HNode root = util.buildTree(text);

	    String encodedText = util.encode(root, text);

	    System.out.println("Encoded: "+encodedText);

	    System.out.println("Decoded: "+util.decode(root, encodedText));

	    input.close();

	}

}