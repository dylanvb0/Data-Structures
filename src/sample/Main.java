package sample;

import java.util.*;

public class Main {
	public static void main(String[] args){
		ArrayList<String> words = new ArrayList<String>();
		words.add("hello");
		words.add("hi");
		words.add("tank");
		words.add("past");
		words.add("truck");
		words.add("rain");
		System.out.println("Using index: ");
		for(int i = 0; i < words.size(); i++){
			if(words.get(i).length() == 4){
				System.out.println(words.get(i));
			}
		}
		System.out.println("Using iterator: ");
		Iterator<String> it = words.iterator();
		while(it.hasNext()){
			String word = it.next();
			if(word.length() == 4){
				System.out.println(word);
			}
		}
		System.out.println("Using enhanced for: ");
		for(String word : words){
			if(word.length() == 4){
				System.out.println(word);
			}
		}

	}
}
