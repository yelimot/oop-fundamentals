
public class ID<T> {
	
	// Generic type of ID as hinted homework description. Id can be both string and integer type.
	private T value;
	
	public ID(T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	
	public static <T> ID<T> of(T value){
		return new ID<T>(value);
	}
	
}
