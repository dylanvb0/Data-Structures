package project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Map.Entry;

public class Main {
	final static String PATH = "/home/dylan/Documents/CMSC_315/textfiles/";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HashMap<String, Integer> words = new HashMap<String, Integer>();
		// harvest words from text files
		File dir = new File(PATH);
		File[] files = dir.listFiles();
		for(File file : files){
			try {
				Scanner s = new Scanner(file);
				while(s.hasNext()){
					String word = s.next().toLowerCase().replace(",", "").replace(".", "");
					Integer entry = words.get(word);
					if(entry == null){entry = 0;}
					words.put(word, entry + 1);
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/* Since we only need the top 20 elements, we use a modified quick sort
		 * We do a reverse sort so it is in descending order
		 * We stop sorting the right side of the pivot as soon as there are 20 elements on the left
		 */
		ArrayList<Entry<String,Integer>> sorted = QuickSort.sort(words);
		for(int i = 0; i < 20; i++){
			System.out.println("(" + (i + 1) + ") " + sorted.get(i).getKey() + ": " + sorted.get(i).getValue());
		}
		

	}

}
