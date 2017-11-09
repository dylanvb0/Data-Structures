package project7;

import java.util.*;

public class BubbleSort<T extends Comparable<? super T>> implements MonitoredSort<T> {
	long swaps, comparisons, time;

	@Override
	public void sortList(List<T> a) {
		swaps = comparisons = 0;
		long start = System.currentTimeMillis();
		for(int i = 0; i < a.size(); i++) {
			for(int j = 0; j < a.size() - 1; j++) {
				comparisons++;
				if(a.get(j).compareTo(a.get(j + 1)) > 0) {
					swaps++;
					T temp = a.get(j);
					a.set(j, a.get(j+1));
					a.set(j+1, temp);
				}
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
		return "Bubble";
	}

}
