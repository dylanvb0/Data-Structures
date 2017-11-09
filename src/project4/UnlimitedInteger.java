package project4;

import java.util.*;
public class UnlimitedInteger
{
	CustomLinkedList list;

    public UnlimitedInteger() {
    	list = new CustomLinkedList();
    	list.addToBeginning(0);
    }
    
    public UnlimitedInteger(String y) {
    	this(y, false);
    }
    
    private UnlimitedInteger(String y, boolean tailFirst){
   		list = new CustomLinkedList();
   		for(int i = 0; i < y.length(); i++){
   			if(tailFirst){
   				list.add(intValue(y.charAt(i)));
   			}else{
   				list.addToBeginning(intValue(y.charAt(i)));
   			}
   		}
    }
    
    public UnlimitedInteger add(UnlimitedInteger y) {
    	CustomLinkedList.Node top = list.head;
    	CustomLinkedList.Node bottom = y.list.head;
    	String result = "";
    	int carry = 0;
    	while(top != null || bottom != null){
    		int res = carry;
    		carry = 0;
    		if(top != null){
    			res += top.data;
    			top = top.next;
    		}
    		if(bottom != null){
    			res += bottom.data;
    			bottom = bottom.next;
    		}
    		while(res >= 10){
    			carry++;
    			res -= 10;
    		}
    		result += res;
    	}
    	if(carry > 0){
    		result += carry;
    	}
    	return new UnlimitedInteger(result, true);
    }

    public UnlimitedInteger subtract(UnlimitedInteger y) {
    	CustomLinkedList.Node top = list.head;
    	CustomLinkedList.Node bottom = y.list.head;
    	StringBuilder result = new StringBuilder();
    	boolean carry = false;
    	while(top != null){
    		int res = 0;
    		if(carry){
    			res -= 1;
    		}
    		res += top.data;
    		top = top.next;
    		if(bottom != null){
    			res -= bottom.data;
    			bottom = bottom.next;
    		}
    		if(res < 0){
    			carry = true;
    			res += 10;
    		}else{
    			carry = false;
    		}
    		result.append(res);
    	}
    	// resulted in negative number
    	if(carry) throw new java.lang.IllegalArgumentException();
    	// remove leading zeroes
    	while(result.charAt(result.length() - 1) == '0'){
    		result.deleteCharAt(result.length() - 1);
    	}
    	return new UnlimitedInteger(result.toString(), true);
    }

    public UnlimitedInteger multiply(UnlimitedInteger y) {
    	CustomLinkedList.Node bottom = y.list.head;
    	UnlimitedInteger result = new UnlimitedInteger();
    	// assuming less than 2^32 digits
    	int offset = 0;
    	while(bottom != null){
    		CustomLinkedList.Node top = list.head;
    		String rowTotal = "";
    		for(int i = 0; i < offset; i++){
    			rowTotal += "0";
    		}
    		offset++;
    		int carry = 0;
    		while(top != null){
    			int currentMult = bottom.data * top.data + carry;
    			carry = 0;
    			while(currentMult >= 10){
    				carry++;
    				currentMult -= 10;
    			}
    			rowTotal += currentMult;
    			top = top.next;
    		}
    		if(carry > 0){
    			rowTotal += carry;
    		}
    		result = result.add(new UnlimitedInteger(rowTotal, true));
    		bottom = bottom.next;
    	}
    	return result;
    }
    
    public UnlimitedInteger pow(int y) {
    	if(y == 0) return new UnlimitedInteger("1");
    	if(y % 2 == 1) return this.multiply(this.pow(y-1));
    	UnlimitedInteger curr = this.pow(y/2);
    	return curr.multiply(curr);
    }
    
    public UnlimitedInteger factorial() {
    	UnlimitedInteger one = new UnlimitedInteger("1");
    	if (this.toString().equals("1") || this.toString().equals("0")) return one;
    	return this.multiply(this.subtract(one).factorial());
    }

    public String toString() {
    	String res = "";
    	CustomLinkedList.Node current = list.tail;
    	while(current != null){
    		res += current.data;
    		current = current.prev;
    	}
    	return res;
    }
    private int intValue(char c){
    	int val = c - 48;
    	if(val < 0 || val > 9) throw new java.lang.IllegalArgumentException();
    	return c - 48;
    }
}
