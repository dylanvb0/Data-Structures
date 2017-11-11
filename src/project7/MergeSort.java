package project7;

import java.util.*;

public class MergeSort<T extends Comparable<? super T>> implements MonitoredSort<T> {
	long swaps, comparisons, time;

	public MergeSort(long swaps, long comparisons, long time) {
		this.swaps = swaps;
		this.comparisons = comparisons;
		this.time = time;
	}

	public MergeSort() {
		
	}

	@Override
	public void sortList(List<T> a) {
		swaps = comparisons = 0;
		long start = System.currentTimeMillis();
		a = doSort(a);
		time = System.currentTimeMillis() - start;
	}
	
	private List<T> doSort(List<T> a) {
		if(a.size() == 1) return a;
		List<T> l1 = new ArrayList<T>();
		for(int i = 0; i <= a.size() / 2 - 1; i++) {
			l1.add(a.get(i));
		}
		l1 = doSort(l1);
		List<T> l2 = new ArrayList<T>();
		for(int i = a.size() / 2; i < a.size(); i++) {
			l2.add(a.get(i));
		}
		l2 = doSort(l2);
		return merge(l1, l2);
	}
	
	private List<T> merge(List<T> l1, List<T> l2){
		List<T> ret = new ArrayList<T>();
		while(l1.size() > 0 && l2.size() > 0) {
			comparisons++;
			if(l1.get(0).compareTo(l2.get(0)) > 0) {
				ret.add(l2.remove(0));
			}else {
				ret.add(l1.remove(0));
			}
		}
		while(l1.size() > 0) ret.add(l1.remove(0));
		while(l2.size() > 0) ret.add(l2.remove(0));
		return ret;
	}

	@Override
	public long getSwaps() {
		return swaps;
	}

	@Override
	public long getComparisons() {
		return comparisons;
	}

	@Override
	public long getTime() {
		return time;
	}

	@Override
	public String getSortType() {
		return "Merge";
	}

	@Override
	public MonitoredSort<T> copy() {
		return new MergeSort<T>(swaps, comparisons, time);
	}

}
