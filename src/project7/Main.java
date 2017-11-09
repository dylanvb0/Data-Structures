package project7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
	// lower the number in the following line if you get sick of waiting for bubble sort, total run time with 250,000 items > 90 mins on my computer
	static final int NUM_ITEMS = 250000 ;

	public static void main(String[] args) {
		List<MonitoredSort<Integer>> sortTypes = initSortTypes();
		printHeaders();
		printCellRowWithTitle();
		for(int i = 0; i < sortTypes.size(); i++) { // loop through sort types
			MonitoredSort<Integer> type = sortTypes.get(i);
			printCell(type.getSortType(), 15);
			printCell(getStableString(isStable(i)), 10);
			for(int j = 0; j < 5; j++) { // loop through lists to sort
				if(j != 0) printCell("", 15);
				if(j != 0) printCell("", 10);
				List<Integer> list = getList(j);
				type.sortList(list);
				printCell(getListName(j), 20);
				printCell(getTimeString(type.getTime()), 15);
				printCell(type.getComparisons(), 15);
				printCell(type.getSwaps(), 15);
				System.out.print("│");
				System.out.println();
				if(j == 4 && i == sortTypes.size() - 1) {
					printBottomCellRow();
				}else if(j == 4){
					printCellRowWithTitle();
				}else {
					printCellRow();
				}
			}
		}
	}
	



	static List<MonitoredSort<Integer>> initSortTypes() {
		List<MonitoredSort<Integer>> sortTypes = new ArrayList<MonitoredSort<Integer>>();
		sortTypes.add(new SelectionSort<Integer>());
		sortTypes.add(new InsertionSort<Integer>());
		sortTypes.add(new BubbleSort<Integer>());
		sortTypes.add(new MergeSort<Integer>());
		sortTypes.add(new QuickSort<Integer>());
		return sortTypes;
	}
	
	
	static String getListName(int index) {
		if(index == 0) return "In order";
		if(index == 1) return "Reverse order";
		if(index == 2) return "Random 1";
		if(index == 3) return "Random 2";
		if(index == 4) return "Partially Ordered";
		return "None";
	}
	
	
	static List<Integer> getList(int index){
		if(index == 0) return getListInOrder();
		if(index == 1) return getListReverseOrder();
		if(index == 2) return getListFromFile("random1.txt");
		if(index == 3) return getListFromFile("random2.txt");
		if(index == 4) return getListFromFile("partially_ordered.txt");
		return null;
	}
	
	static boolean isStable(int index) {
		List<CustomComparable> arr = new ArrayList<CustomComparable>();
		// populate array
		for(int i = 0; i < 20; i++) {
			for(int j = 0; j < 20; j++) {
				arr.add(new CustomComparable(j));
			}
		}
		// shuffle array
		List<CustomComparable> shuffled = new ArrayList<CustomComparable>();
		while(arr.size() > 0) {
			shuffled.add(arr.remove((int)(Math.random() * arr.size())));
			shuffled.get(shuffled.size() - 1).setId();
		}
		MonitoredSort<CustomComparable> stable;
		if(index == 0) stable = new SelectionSort<CustomComparable>();
		else if(index == 1) stable = new InsertionSort<CustomComparable>();
		else if(index == 2) stable = new BubbleSort<CustomComparable>();
		else if(index == 3) stable = new MergeSort<CustomComparable>();
		else stable = new QuickSort<CustomComparable>();
		stable.sortList(shuffled);
		// check order of sorted list
		for(int i = 0; i < shuffled.size() - 1; i++) {
			if(shuffled.get(i).equals(shuffled.get(i+1)) && !shuffled.get(i).isInOrder(shuffled.get(i+1))) {
				return false;
			}
		}
		return true;
	}
	
	/* BEGIN - Get Specific List Types */
	static List<Integer> getListInOrder(){
		List<Integer> l = new ArrayList<Integer>();
		for(int i = 1; i <= NUM_ITEMS; i++) {
			l.add(i);
		}
		return l;
	}
	
	static List<Integer> getListReverseOrder(){
		List<Integer> l = new ArrayList<Integer>();
		for(int i = NUM_ITEMS; i > 0; i--) {
			l.add(i);
		}
		return l;
	}
	
	static List<Integer> getListFromFile(String name){
		List<Integer> list = new ArrayList<Integer>();
		try {
			Scanner s = new Scanner(new File(name));
			int index = 0;
			while(s.hasNextInt() && index < NUM_ITEMS) {
				list.add(s.nextInt());
				index++;
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
	/* END - Get Specific List Types */
	
	/* BEGIN - Output Formatting */
	static void printCell(Object obj, int width) {
		String str = obj.toString();
		System.out.print("│");
		System.out.print(str);
		for(int i = 0; i < width - str.length(); i++) {
			System.out.print(" ");
		}
	}
	
	
	private static void printBottomCellRow() {
		System.out.println("└───────────────┴──────────┴────────────────────┴───────────────┴───────────────┴───────────────┘");
	}
	
	static void printCellRowWithTitle() {
		System.out.println("├───────────────┼──────────┼────────────────────┼───────────────┼───────────────┼───────────────┤");
	}

	static void printCellRow() {
		System.out.println("│               │          ├────────────────────┼───────────────┼───────────────┼───────────────┤");
	}
	
	private static void printHeaders() {
//		printCellRow();
		System.out.println("┌───────────────┬──────────┬────────────────────┬───────────────┬───────────────┬───────────────┐");
		printCell("Sort Method", 15);
		printCell("Stable?", 10);
		printCell("Array Type", 20);
		printCell("Time", 15);
		printCell("Compares", 15);
		printCell("Swaps", 15);
		System.out.print("│");
		System.out.println();
	}
	
	static String getStableString(boolean val) {
		return val ? "Stable" : "Unstable";
	}
	
	private static String getTimeString(long time) {
		return String.format("%.4f sec.", (double)time / 1000);
	}
	/* END - Output Formatting */

}
