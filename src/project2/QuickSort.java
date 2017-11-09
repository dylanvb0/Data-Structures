package project2;
import java.util.*;
import java.util.Map.Entry;

public class QuickSort {
	static ArrayList<Entry<String, Integer>> list;

	public static ArrayList<Entry<String, Integer>> sort(HashMap<String, Integer> words) {
		list = new ArrayList<Entry<String, Integer>>(words.entrySet());
		quickSort(0, list.size() - 1);
		return list;
	}
	
	private static void quickSort(int left, int right){
		if(right - left <= 0){
			return;
		}else{
			int pivot = list.get(right).getValue();
			int partition = partitionFunc(left, right, pivot);
			quickSort(left, partition - 1);
			// since we only need the top 20, we stop sorting the right side after we have 20 on the left
			if(partition < 20){
				quickSort(partition + 1, right);
			}
		}
	}

	private static int partitionFunc(int left, int right, int pivot) {
		int leftPointer = left - 1;
		int rightPointer = right;
		while(true){
			while(list.get(++leftPointer).getValue() > pivot){
				
			}
			while(rightPointer > 0 && list.get(--rightPointer).getValue() < pivot){
				
			}
			if(leftPointer >= rightPointer){
				break;
			}else{
				Entry<String, Integer> temp = list.get(leftPointer);
				list.set(leftPointer, new AbstractMap.SimpleEntry<String, Integer>(list.get(rightPointer)));
				list.set(rightPointer, temp);
			}
		}
		Entry<String, Integer> temp = list.get(leftPointer);
		list.set(leftPointer, new AbstractMap.SimpleEntry<String, Integer>(list.get(right)));
		list.set(right, temp);
		return leftPointer;
	}
	
	

}
