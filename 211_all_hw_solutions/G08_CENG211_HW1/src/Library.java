import java.util.Arrays;

public class Library {
	
	private Book[] bookArray;
	private int capacity;
	private static int defaultCapacity = 10;
	private int occupied = 0; // current number of elements in the list
	
	public Book[] getBookArray() {
		return bookArray;
	}

	public void setBookArray(Book[] bookArray) {
		this.bookArray = bookArray;
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
		Library.defaultCapacity = defaultCapacity;
	}

	public int getOccupied() {
		return occupied;
	}

	public void setOccupied(int occupied) {
		this.occupied = occupied;
	}
	
	public Library() {
		this(defaultCapacity);
	}
	
	public Library(int initialCapacity) {
		bookArray = (Book[]) new Book[initialCapacity];
	}
	
	// If the array capacity is full, this method doubles the capacity.
	private void ensureCapacity() {
		int capacity = bookArray.length-1;
		if(occupied >= capacity) {
			int newCapacity = 2*capacity;
			bookArray = Arrays.copyOf(bookArray, newCapacity+1);
		}
	}
	
	// Adding a new element by ensuring that there is room for it.
	public void add(Book newElement) {
		ensureCapacity();
		bookArray[occupied] = newElement;
		occupied++;
	}

}