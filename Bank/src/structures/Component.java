package structures;

public class Component<T extends Comparable <T>> {

	private T element;
	
	public Component(T element) {
		this.element = element;
	}

	public T getElement() {
		return element;
	}

	public void setElement(T element) {
		this.element = element;
	}
	
}
