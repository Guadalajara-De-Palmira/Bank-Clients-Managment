package structures;

public class Node<T> {

	private T value;
	private Node next;
	private Node previous;
	
	public Node(T value, Node next, Node previous) {
		this.value = value;
		this.next = next;
		this.previous = previous;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Node getPrevious() {
		return previous;
	}

	public void setPrevious(Node previous) {
		this.previous = previous;
	}
	
	
	
}
