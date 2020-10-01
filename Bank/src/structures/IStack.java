package structures;

import java.util.List;

public interface IStack <T> {

	
	public void push(T value);
	
	public boolean isEmpty();
	
	public T top();
	
	public T pop();
	
	public int size();
	
	public List<T> getElementsList();
}
