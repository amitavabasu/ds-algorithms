package com.amit.alogs.hr.easy;

public class HR13ReverseLinkedList {

    static class SinglyLinkedListNode {
        public int data;
        public SinglyLinkedListNode next;

        public SinglyLinkedListNode(int nodeData) {
            this.data = nodeData;
            this.next = null;
        }
    }
    static SinglyLinkedListNode reverse(SinglyLinkedListNode head) {
        if(head==null || head.next==null){
            return head;
        }
        
        SinglyLinkedListNode refToHead = head;
        SinglyLinkedListNode node = head.next;
        boolean done = false;
        while(!done){
            SinglyLinkedListNode nextRef = node.next;
            node.next = head;
            head = node;
            if(nextRef==null){
                done = true;
            }else{
                node = nextRef;
            }
        }
        refToHead.next = null;
        return head;
    }

    public static void printSinglyLinkedList(SinglyLinkedListNode node){
        while (node != null) {
            System.out.println(node.data);
            node = node.next;
        }
    }        
        
	public static void main(String[] args){
		SinglyLinkedListNode node = new SinglyLinkedListNode(1);
		SinglyLinkedListNode head = node;
		node.next = new SinglyLinkedListNode(2);
		node = node.next;
		node.next = new SinglyLinkedListNode(3);
		node = node.next;
		node.next = new SinglyLinkedListNode(4);
		node = node.next;
		node.next = new SinglyLinkedListNode(5);
		printSinglyLinkedList(head);
		System.out.println("-------------------------------");
		printSinglyLinkedList(reverse(head));
	}
}
