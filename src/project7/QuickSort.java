package project7;

import java.util.*;

public class QuickSort<T extends Comparable<? super T>> implements MonitoredSort<T> {
	long swaps, comparisons, time;

	@Override
	public void sortList(List<T> a) {
		swaps = comparisons = 0;
		long start = System.currentTimeMillis();
		doSort(a, 0, a.size() - 1);
		time = System.currentTimeMillis() - start;
	}
	
	private void doSort(List<T> a, int left, int right) {
		if(right - left <= 0) return;
		T pivot = a.get((int)(Math.random() * (right - left) + left));
		int partition = partitionFunc(a, left, right, pivot);
		doSort(a, left, partition - 1);
		doSort(a, partition + 1, right);
		
	}
	
	private int partitionFunc(List<T> a, int left, int right, T pivot) {
		int leftPointer = left - 1;
		int rightPointer = right;
		while(true) {
			while(++comparisons > 0 && a.get(++leftPointer).compareTo(pivot) < 0) {} 
			while(++comparisons > 0 && rightPointer > 0 && a.get(--rightPointer).compareTo(pivot) > 0) {}
			comparisons++;
			if(leftPointer >= rightPointer)	break;
			swaps++;
			T temp = a.get(leftPointer);
			a.set(leftPointer, a.get(rightPointer));
			a.set(rightPointer, temp);
		}
		swaps++;
		a.set(right, a.get(leftPointer));
		a.set(leftPointer, pivot);
		return leftPointer;
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
		return "Quicksort";
	}

}
