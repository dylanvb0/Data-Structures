package project6;

import java.util.*;

public class Trie extends SearchableTree {
	Entry root;
	
	public Trie() {
		root = new Entry(' ', null);
	}

	@Override
	public void insert(Comparable x) {
		String s = (String)x;
		Entry cursor = root;
		for(int i = 0; i < s.length(); i++) {
			if(!cursor.children.containsKey(s.charAt(i))) {
				Entry child = new Entry(s.charAt(i), cursor);
				cursor.children.put(s.charAt(i), child);
			}
			cursor = cursor.children.get(s.charAt(i));
			cursor.numUsing++;
		}
		cursor.isEnd = true;
	}

	@Override
	public Comparable remove(Comparable x) {
		if(find(x) == null) return null;
		String s = (String)x;
		Entry cursor = root;
		int index = 0;
		for(; index < s.length(); index++) {
			cursor = cursor.children.get(s.charAt(index));
			if(cursor == null) return null;
			cursor.numUsing--;
			if(cursor.numUsing == 0) {
				cursor.parent.children.remove(s.charAt(index));
				return x;
			}
		}
		if(index == s.length() && cursor.isEnd) {
			cursor.isEnd = false;
			return x;
		}
		return null; // should never reach this
	}

	@Override
	public Comparable find(Comparable x) {
		String s = (String)x;
		Entry cursor = root;
		int index = 0;
		while(index < s.length() && cursor.children.containsKey(s.charAt(index))) {
			cursor = cursor.children.get(s.charAt(index));
			index++;
		}
		if(index == s.length() && cursor.isEnd) {
			return s;
		}else {
			return null;
		}
	}

	@Override
	public void rebalance(Comparable x) {
		// TODO Auto-generated method stub

	}
	
	protected class Entry {
		Map<Character, Entry> children = new HashMap<Character, Entry>();
		Entry parent;
		Character value;
		int numUsing = 0;
		boolean isEnd = false;
		
		public Entry(Character value, Entry parent) {
			this.value = value;
			this.parent = parent;
		}
		
		public String toString() {
			return value.toString();
		}
	}

}
