package structures;

import java.util.List;

import customStructureExceptions.EmptyStructureException;
import customStructureExceptions.KeyDifferenceException;

public interface IMaxHeap<T extends Comparable<T>>{

	public void heapify(int i);
	
	public void buildMaxHeap();
	
	public void insert(T element) throws KeyDifferenceException;
	
	public T extract() throws EmptyStructureException;
	
	public T returnMaximum();
	
	public int parent(int i);
	
	public int left(int i);
	
	public int right(int i);
	
	public void increaseKey(int i,T key) throws KeyDifferenceException;
	
	public List<T> getList();
	
}
