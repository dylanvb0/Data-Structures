package project7;

import java.util.List;

public class InsertionSort<T extends Comparable<? super T>> implements MonitoredSort<T> {
	long swaps, comparisons, time;

	public InsertionSort(long swaps, long comparisons, long time) {
		this.swaps = swaps;
		this.comparisons = comparisons;
		this.time = time;
	}
	
	public InsertionSort() {
		
	}

	@Override
	public void sortList(List<T> a) {
		swaps = comparisons = 0;
		long start = System.currentTimeMillis();
		for(int i = 0; i < a.size(); i++) {
			int insertion = i;
			for(int j = i - 1; j >= 0; --j) {
				comparisons++;
				if(a.get(i).compareTo(a.get(j)) >= 0) {
					break;
				}
				insertion = j;
			}
			if(insertion != i) {
				swaps++;
				a.add(insertion, a.remove(i));
			}
		}
		time = System.currentTimeMillis() - start;
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
		return "Insertion";
	}

	@Override
	public MonitoredSort<T> copy() {
		return new InsertionSort<T>(swaps, comparisons, time);
	}
}
