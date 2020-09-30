package structures;

import java.util.ArrayList;
import java.util.List;

import customStructureExceptions.EmptyStructureException;
import customStructureExceptions.KeyDifferenceException;

public class MaxHeap <T extends Comparable<T>> implements IMaxHeap<T>{

	private Component[] elements;
	private int heapSize;
	
	public MaxHeap(int maxSize) {
		elements = new Component[maxSize];
		heapSize = 0;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void heapify(int i) {
		int l = left(i);
		int r = right(i);
		int largest = i;
		
		int comparation = 0;
		
		if (elements[l] != null && elements[i] != null) {
			comparation = elements[l].getElement().compareTo(elements[i].getElement());
		}
		
		if(l < heapSize && comparation > 0) {
			largest = l;
			
		}
		
		if (elements[r] != null && elements[i] != null) {
			comparation = elements[r].getElement().compareTo(elements[largest].getElement());
		}
		
		if(r < heapSize && comparation > 0) {
			largest = r;
		}
		if(largest != i) {
			Component aux = elements[i];
			elements[i] = elements[largest];
			elements[largest] = aux;
			aux = null;
			heapify(largest);
		}
	}

	@Override
	public void buildMaxHeap() {
		for(int i=elements.length;i>=0;i--) {
			heapify(i);
		}
		
	}

	@Override
	public void insert(T element) throws KeyDifferenceException {
		increaseKey(heapSize, element);
		heapSize++;
	}

	@Override
	public T extract() throws EmptyStructureException {
		if(heapSize<1) {
			throw new EmptyStructureException("Cannot extract; the heap is empty");
		}
		
		Component max = elements[0];
		elements[0] = elements[heapSize - 1];
		elements[heapSize - 1] = null;
		heapSize--;
		heapify(0);
		
		return (T)max.getElement();
	}

	@Override		
	public T returnMaximum() {
		T toReturn = null;
		
		if (elements[0] != null) {
			toReturn = (T)elements[0].getElement();
		}
		
		return toReturn;
	}
	

	@Override
	public int parent(int i) {
		return i/2;
	}

	@Override
	public int left(int i) {
		return (2*i) + 1;
	}

	@Override
	public int right(int i) {
		// TODO Auto-generated method stub
		return (2*i) + 2;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void increaseKey(int i, T key) throws KeyDifferenceException {	
		if(elements[i] != null && key.compareTo((T)elements[i].getElement()) < 0) {
			throw new KeyDifferenceException("Cannot increase key; the new key is minor than the actual key.");
		}
		
		int comparation = 0;
		elements[i] = new Component(key);
		
		if (i > 0) {
			comparation = elements[parent(i)].getElement().compareTo(elements[i].getElement());
		}
		while(i > 0 && comparation < 0) {
			Component aux = elements[parent(i)];
			elements[parent(i)] = elements[i];
			elements[i] = aux;
			i = parent(i);
			comparation = elements[parent(i)].getElement().compareTo(elements[i].getElement());
		}
	}
	
	public int getHeapSize () {
		return heapSize;
	}

	@Override
	public List<T> getList() {
		List<T> list = new ArrayList<T>();
		
		for(int i=0;i<heapSize;i++) {
			if (elements[i] != null) {
				list.add((T)elements[i].getElement());
			}
		}
		
		return list;
	}

	
}
