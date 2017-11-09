package project4;

public class CustomLinkedList {
	Node head;
	Node tail;
	
	public CustomLinkedList(){
		head = null;
		tail = null;
	}
	
	void addToBeginning(int digit){
		Node n = new Node(digit);
		n.next = head;
		if(head != null){
			head.prev = n;
		}
		head = n;
		if(tail == null){
			tail = n;
		}
	}
	
	void add(int digit){
		Node n = new Node(digit);
		if(tail != null){
			tail.next = n;
			n.prev = tail;
		}else{
			head = n;
		}
		tail = n;
	}
	
	class Node {
		int data;
		Node next;
		Node prev;
		
		Node(int data){
			this.data = data;
			this.next = null;
			this.prev = null;
		}
		
	}
}
