package project6;

import java.util.*;

public class LazyBST<T extends Comparable<? super T>> extends SearchableTree<T> {
	Entry<T> root;
	
	@Override
	public void insert(T x) {
		if (root == null) 
        {
            if (x == null)
                throw new NullPointerException();
            root = new Entry<T> (x, null);
            return;
        } // empty tree
        else 
        {
            Entry<T> temp = root;

            int comp;

            while (true) 
            {
            	try {
            		Entry<T> parent = temp.parent;
            		if(checkRebalance(temp)) {
            			temp = (parent != null) ? parent : root;
            		}
            	}catch(Exception e){
            		e.printStackTrace();
            	}
                comp =  x.compareTo (temp.value);
                if (comp == 0) {
                    return;
                }
                if (comp < 0) {
                    if (temp.left != null) {
                        temp = temp.left;
                    }else {
                        temp.left = new Entry<T> (x, temp);
                        temp.left.increaseNode();
                        temp.left.increaseHeight();
                        return;
                    }
                } else if (temp.right != null) {
                    temp = temp.right;
                }else {
                    temp.right = new Entry<T> (x, temp);
                    temp.right.increaseNode();
                    temp.right.increaseHeight();
                    return;
                } // temp.right == null
                	
            } // while
        } // root not null
	}

	@Override
	public T remove(T x) {
		Entry<T> p = getEntry(x);
		if(p == null) return null;
		// If p has two children, replace p's element with p's successor's
        // element, then make p reference that successor.
        if (p.left != null && p.right != null) 
        {
            Entry<T> s = successor (p);
            p.value = s.value;
            p = s;
        } // p had two children


        // At this point, p has either no children or one child.

        Entry<T> replacement;
         
        if (p.left != null)
            replacement = p.left;
        else
            replacement = p.right;

        // set height and nodes fields
        p.decreaseHeightUntil(replacement);
        p.decreaseNodeUntil(replacement);
        p.nodes = 1;
        if(p.left != null) {
        	p.height = p.left.height;
        	p.nodes += p.left.nodes;
        }
        if(p.right != null && p.right.height > p.height) {
        	p.height = p.right.height;
        	p.nodes += p.right.nodes;
        }
        
        // If p has at least one child, link replacement to p.parent.
        if (replacement != null) 
        {
            replacement.parent = p.parent;
            if (p.parent == null)
                root = replacement;
            else if (p == p.parent.left)
                p.parent.left  = replacement;
            else
                p.parent.right = replacement;
        } // p has at least one child  
        else if (p.parent == null)
            root = null;
        else 
        {
            if (p == p.parent.left)
                p.parent.left = null;
            else
                p.parent.right = null;        
        } // p has a parent but no children
        checkRebalance(p.parent);
        return p.value;
	}

	@Override
	public T find(T x) {
		int comp;
        if (x == null)
           throw new NullPointerException();
        Entry<T> e = root;
        while (e != null) 
        {
            comp = (x).compareTo (e.value);
            if (comp == 0)
                return x;
            else if (comp < 0)
                e = e.left;
            else
                e = e.right;
        } // while
		return null;
	}
	
	private Entry<T> getEntry(T x) {
		int comp;
        if (x == null)
           throw new NullPointerException();
        Entry<T> e = root;
        while (e != null) 
        {
            comp = (x).compareTo (e.value);
            if (comp == 0)
                return e;
            else if (comp < 0)
                e = e.left;
            else
                e = e.right;
        } // while
		return null;
	}
	
	protected Entry<T> successor (Entry<T> e) 
    {
        if (e == null)
            return null;
        else if (e.right != null) 
        {
            // successor is leftmost Entry in right subtree of e
            Entry<T> p = e.right;
            while (p.left != null)
                p = p.left;
            return p;

        } // e has a right child
        else 
        {

            // go up the tree to the left as far as possible, then go up
            // to the right.
            Entry<T> p = e.parent;
            Entry<T> ch = e;
            while (p != null && ch == p.right) 
            {
                ch = p;
                p = p.parent;
            } // while
            return p;
        } // e has no right child
    } // method successor

	private boolean checkRebalance(Entry<T> parent) {
		// I use 4 because I include the given element in the height
		if(parent.height <= 4) return false;  
		int left = 0;
		int right = 0;
		if(parent.left != null) {
			left = parent.left.nodes; 
		}
		if(parent.right != null) {
			right = parent.right.nodes; 
		}
		if(right == 0 || left == 0 || right / left >= 2 || left / right >= 2) {
			rebalance(parent.value);
			return true;
		}
		return false;
	}
	
	@Override
	public void rebalance(T x) {
		Entry<T> entry = getEntry(x);
		Entry<T> parent = entry.parent;
		// put elements in list using in-order traversal
		LinkedList<T> values = new LinkedList<T>();
		entry.inOrderTraversal(values);
		// cut off elements from tree
		entry.parent = parent;
		if(parent != null) { // entry is not root of tree
			if(entry == entry.parent.left) {
				parent.left = null;
			}else {
				parent.right = null;
			}
			entry.parent = null;
			// now reattach the nodes
			parent.addInOrder(0, values.size() - 1, values);
			parent.fixNodes();
			parent.fixHeight();
		}else {
			// reattach the nodes starting at root
			root = new Entry("a", null);
			root.addInOrder(0, values.size() - 1, values);
			root = root.right;
			root.parent = null;
		}
		
	}
	
	protected class Entry<E extends Comparable<? super E>> {
		E value;
		Entry<E> left = null;
		Entry<E> right = null;
		Entry<E> parent;
		int nodes = 1;
		int height = 1;
		
		public Entry (E value, Entry<E> parent){
			this.value = value;
			this.parent = parent;
		}
		
		public void fixHeight() {
			Entry<E> cursor = this;
			while(cursor != null) {
				int maxHeight = 0;
				if(cursor.left != null) {
					maxHeight = cursor.left.height;
				}
				if(cursor.right != null && cursor.right.height > maxHeight) {
					maxHeight = cursor.right.height;
				}
				cursor.height = maxHeight + 1;
				cursor = cursor.parent;
			}
			
		}

		public void fixNodes() {
			nodes = 1;
			if(left != null) {
				nodes += left.nodes;
			}
			if(right != null) {
				nodes += right.nodes;
			}
		}

		void increaseHeight() {
			/*
			 * Only increase height of parent if both are true:
			 * 1) parent exists; and
			 * 2) this would increase its height (it's other child doesn't have greater height)
			 */
			Entry<E> cursor = this;
			while(cursor.parent != null && cursor.height == cursor.parent.height) {
				cursor.parent.height++;
				cursor = cursor.parent;
			}
		}
		
		void increaseNode() {
			Entry<E> cursor = this;
			while(cursor.parent != null)
			if(cursor.parent != null) {
				cursor.parent.nodes++;
				cursor = cursor.parent;
			}
		}
		
		void decreaseNodeUntil(Entry<E> elem) {
			Entry<E> cursor = this;
			if(cursor.parent != null && cursor.parent != elem) {
				cursor.parent.nodes--;
				cursor = cursor.parent;
			}
		}
		
		void decreaseHeightUntil(Entry<T> elem) {
			/*
			 * Only increase height of parent if both are true:
			 * 1) parent exists; and
			 * 2) this node's height is greater than its sibling
			 * 3) parent is not elem
			 */
			Entry<E> cursor = this;
			if(cursor.parent != null && cursor.height > siblingHeight() && cursor.parent != elem) {
				cursor.parent.height--;
				cursor = cursor.parent;
			}
		}

		private int siblingHeight() {
			if(parent == null) return 0;
			Entry<E> sibling;
			if(this == parent.left) {
				sibling = parent.right;
			}else {
				sibling = parent.left;
			}
			return sibling != null ? sibling.height : 0;
		}
		
		void inOrderTraversal(LinkedList<E> list) {
			if(left != null) left.inOrderTraversal(list);
			list.add(value);
			if(right != null) right.inOrderTraversal(list);
		}
		
		boolean addInOrder(int low, int high, LinkedList<E> list) {
			if(low > high) return false;
			int mid = (high + low) / 2;
			E val = list.get(mid);
			Entry<E> current = new Entry<E>(val, this); 
			if(val.compareTo(this.value) < 0) {
				this.left = current;
			}else {
				this.right = current;
			}
			boolean oneAdded = false;
			current.nodes = 1;
			current.height = 0;
			if(current.addInOrder(low, mid - 1, list)) {
//				nodes+=current.nodes;
				oneAdded = true;
			}
			if(current.addInOrder(mid + 1, high, list)) {
//				nodes+=current.nodes;
				oneAdded = true;
			}
			nodes += current.nodes;
//			if(oneAdded) {
				int maxHeight = 0;
				if(current.left != null) {
					maxHeight = current.left.height;
				}
				if(current.right != null) {
					maxHeight = current.right.height;
				}
				if(current.right != null && current.left != null && current.left.height > current.right.height) {
					maxHeight = current.left.height;
				}
				current.height += maxHeight + 1;
//			}
			return true;
		}
		public String toString() {
			return value.toString();
		}
	}

}
