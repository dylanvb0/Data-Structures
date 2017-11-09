package project7;

public class CustomComparable implements Comparable<CustomComparable> {
	int value;
	int id;
	static int currentId = 0;
	
	public CustomComparable(int value) {
		this.value = value;
		this.id = currentId;
		currentId++;
	}

	@Override
	public int compareTo(CustomComparable o) {
		return this.value - o.value;
	}
	
	public boolean isInOrder(CustomComparable o) {
		return this.id < o.id;
	}
	
	public boolean equals(CustomComparable o) {
		return this.value == o.value;
	}
	
	public String toString() {
		return this.id + ": " + this.value;
	}
	
	public void setId() {
		id = currentId;
		currentId++;
	}

}
