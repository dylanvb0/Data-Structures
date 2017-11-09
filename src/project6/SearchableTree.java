package project6;

public abstract class SearchableTree<T extends Comparable<? super T>> {
	public abstract void insert (T x); 
	public abstract T remove (T x); 
	public abstract T find (T x); 
	public abstract void rebalance (T x); 
}
