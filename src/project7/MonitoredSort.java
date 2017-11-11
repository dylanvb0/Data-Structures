package project7;

import java.util.*;

public interface MonitoredSort<T extends Comparable<?>> {
	public void sortList(List<T> a);
	public long getSwaps();
	public long getComparisons();
	public long getTime();
	public String getSortType();
	public MonitoredSort<T> copy();
}
