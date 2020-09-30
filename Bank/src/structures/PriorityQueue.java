package structures;

import java.util.List;

import customStructureExceptions.EmptyStructureException;
import customStructureExceptions.KeyDifferenceException;

public class PriorityQueue<T extends Comparable<T>> implements IPriorityQueue<T>{	
	
	private MaxHeap elements;
	
	public PriorityQueue (int maxSize) {
		elements = new MaxHeap(maxSize);
		
	}

	@Override
	public void insert(T newElement) throws KeyDifferenceException {
		elements.insert(newElement);
	}

	@Override
	public T maximum() {
		return (T) elements.returnMaximum();
	}

	@Override
	public T extractMax() throws EmptyStructureException {
		return (T) elements.extract();
	}

	@Override
	public void IncreaseKey(int i, T key) throws KeyDifferenceException {
		elements.increaseKey(i, key);
	}

	@Override
	public List<T> getList() {
		return elements.getList();
	}
}
