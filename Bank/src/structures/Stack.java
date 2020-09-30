package structures;

import java.util.List;

public class Stack <T extends Comparable<T>> implements IStack<T>{
	
	private IQueue<T> queue;
	
	public Stack () {
		queue = new Queue<T>();
	}
	
	@Override
	public void push(T value) {
		queue.enqueue(value);
	}

	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}

	@Override
	public T top() {
		T top = null;
		IQueue<T> queueTwo = new Queue<T>();
		while(queue.size()>1) {
			queueTwo.enqueue(queue.dequeue());
		}
		
		top = queue.front();
		queueTwo.enqueue(queue.dequeue());
		
		while(!queueTwo.isEmpty()) {
			queue.enqueue(queueTwo.dequeue());
		}
		
		return top; 
	}

	@Override
	public T pop() {
		T eliminated = null;
		IQueue<T> queueTwo = new Queue<T>();
		while(queue.size()>1) {
			queueTwo.enqueue(queue.dequeue());
		}
		
		eliminated = queue.dequeue();
		while(queueTwo.size()>0) {
			queue.enqueue(queueTwo.dequeue());
		}
		
		return eliminated;
	}

	@Override
	public int size() {
		return queue.size();
	}

	@Override
	public List<T> getElementsList() {
		return queue.getElementsList();
	}
	
	

}
