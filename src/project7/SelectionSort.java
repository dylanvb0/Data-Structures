package project7;

import java.util.List;

public class SelectionSort<T extends Comparable<? super T>> implements MonitoredSort<T> {
	long swaps, comparisons, time;

	@Override
	public void sortList(List<T> a) {
		swaps = comparisons = 0;
		long start = System.currentTimeMillis();
		for(int i = 0; i < a.size(); i++) {
			int minI = i;
			for(int j = i; j < a.size(); j++) {
				comparisons++;
				if(a.get(j).compareTo(a.get(minI)) < 0) {
					minI = j;
				}
			}
			T temp = a.get(minI);
			a.set(minI, a.get(i));
			a.set(i, temp);
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
		return "Selection";
	}

}
