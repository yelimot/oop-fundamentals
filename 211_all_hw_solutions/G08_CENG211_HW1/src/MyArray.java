import java.util.Arrays;

public class MyArray {
	
	private Object[] myList;
	private int capacity;
	private static int defaultCapacity = 10;
	private int occupied = 0; // current number of elements in the list
	
	public Object[] getObjectArray() {
		return myList;
	}

	public void setObjectArray(Object[] myList) {
		this.myList = myList;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public static int getDefaultCapacity() {
		return defaultCapacity;
	}

	public static void setDefaultCapacity(int defaultCapacity) {
		MyArray.defaultCapacity = defaultCapacity;
	}

	public int getOccupied() {
		return occupied;
	}

	public void setOccupied(int occupied) {
		this.occupied = occupied;
	}
	
	public MyArray() {
		this(defaultCapacity);
	}
	
	public MyArray(int initialCapacity) {
		myList = (Object[]) new Object[initialCapacity];
	}
	
	// If the array capacity is full, this method doubles the capacity.
	private void ensureCapacity() {
		int capacity = myList.length-1;
		if(occupied >= capacity) {
			int newCapacity = 2*capacity;
			myList = Arrays.copyOf(myList, newCapacity+1);
		}
	}
	
	// Adding a new element by ensuring that there is room for it.
	public void add(Object newElement) {
		ensureCapacity();
		myList[occupied] = newElement;
		occupied++;
	}
	
}
