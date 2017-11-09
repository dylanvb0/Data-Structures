package project6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Pattern;

public class SpellChecker {
	static SearchableTree<String> words;
	static boolean isUsingTrie = false;
	/* I have included various dictionary files for testing: 
	 * words.txt - given dictionary, 480,000 words
	 * smaller.txt - smaller version, 179,000 words
	 * tiny.txt - most common google searches, 10,000 words
	 */
	static final String DICTIONARY_FILE = "words.txt";
	public static void main(String[] args) {
		System.out.println("Loading Dictionary using LazyBST");
		if(isUsingTrie) {
			words = new Trie();
		}else {
			words = new LazyBST<String>();
		}
		initWords();
		Scanner keyboard = new Scanner(System.in);
		while(true) {
			System.out.println("What would you like to do?");
			System.out.println("1) Add a word to the dictionary");
			System.out.println("2) Remove a word from the dictionary");
			System.out.println("3) Spell check a document");
			if(isUsingTrie) {
				System.out.println("4) Switch to standard mode (LazyBST)");
			}else {
				System.out.println("4) Switch to efficient mode (Trie)");
			}
			System.out.println("5) Quit");
			System.out.print("Enter 1, 2, 3, 4 or 5 >> ");
			String input = keyboard.nextLine();
			System.out.println();
			if(input.contains("1")) {
				// add a word to the dictionary
				System.out.print("Enter the word to add >> ");
				words.insert(sanitize(keyboard.nextLine()));
			}else if(input.contains("2")) {
				// remove a word from the dictionary
				System.out.print("Enter the word to remove >> ");
				if(words.remove(sanitize(keyboard.nextLine())) != null) {
					System.out.println("Word removed successfully");
				}else {
					System.out.println("Word not found");
				}
				
			}else if(input.contains("3")) {
				// spell check a document
				System.out.print("Enter the name of the document >> ");
				spellcheck(keyboard.nextLine().trim());
			}else if(input.contains("4")) {
				// switch tree type
				isUsingTrie = !isUsingTrie;
				if(isUsingTrie) {
					words = new Trie();
				}else {
					words = new LazyBST<String>();
				}
				initWords();
			}else if(input.contains("5")) {
				System.out.println("Goodbye");
				keyboard.close();
				System.exit(0);
			}else {
				System.out.println("Please enter a number");
			}
			System.out.println();
		}
	}
	
	private static void spellcheck(String str) {
		long start = System.currentTimeMillis();
		LinkedList<String> misspelledWords = new LinkedList<String>();
		Scanner s = null;
		try {
			s = new Scanner(new File(str));
			// split up words by spaces, newline, tab or 2 consecutive dashes
			s.useDelimiter(Pattern.compile("[ ]|[\n]|[\t]|[-]{2}"));
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			return;
		}
		Scanner keyboard = new Scanner(System.in);
		while(s.hasNext()) {
			String currentWord = sanitize(s.next());
			if(!currentWord.equals("") && words.find(currentWord) == null){
				System.out.println("\"" + currentWord + "\" is misspelled. Add to dictionary? (y/n)");
				if(keyboard.nextLine().contains("y")) {
					words.insert(currentWord);
				}else {
					misspelledWords.add(currentWord);
				}
			}
		}
		s.close();
		System.out.println("Misspelled Words: ");
		for(String word : misspelledWords) {
			System.out.println(word);
		}
		System.out.println("Time: " + (System.currentTimeMillis() - start));
	}
	
	// Assumption: case insensitive spell check
	// only allow letters, single quotes, and hyphens
	private static String sanitize(String s) {
		return s.toLowerCase().replaceAll("[^a-z'-]", "").trim();
	}

	private static void initWords() {
		long start = System.currentTimeMillis();
		Scanner s = null;
		try {
			s = new Scanner(new File(DICTIONARY_FILE));
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(1);
		}
		int i = 0;
		while(s.hasNextLine()) {
			i++;
			if(i % 1000 == 0) System.out.println(i);
			// Note: words.txt has major rebalancing around 409,000
			words.insert(sanitize(s.nextLine()));
		}
		s.close();
		System.out.println("Time: " + (System.currentTimeMillis() - start));
	}
}
