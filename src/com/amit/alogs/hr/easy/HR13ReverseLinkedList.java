package com.amit.alogs.hr.easy;

public class HR13ReverseLinkedList {
	/*
	 * reverse a given singly linked list
	 */
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
        
        SinglyLinkedListNode refToHead = head;//<-- keep a reference to head as this will be set to null at the end, after reversal
        SinglyLinkedListNode node = head.next;//<-- point a note to the go to next of head 
        boolean done = false;
        while(!done){
            SinglyLinkedListNode nextRef = node.next;//<-- create a 3rd node reference to its next
            node.next = head;//<-- reverse the link of this node to head 
            head = node;//<-- move head to next
            if(nextRef==null){//<-- next reference is null it is done
                done = true;
            }else{
                node = nextRef;//<-- else move the node reference to next node reference
            }
        }
        refToHead.next = null;//<-- finally set the head reference to null as this is now the end of the list
        return head;//<-- return new head
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
