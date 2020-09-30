package structures;

import customStructureExceptions.FullStructureException;
import customStructureExceptions.NotFoundException;

public interface IHashTable<K,E> {
	
	public void tableInsert(E element) throws FullStructureException;
	
	public E tableRetrieve(K key) throws NotFoundException;
	
	public E tableDelete(K key) throws NotFoundException;
	
	public boolean isEmpty();
	
	public int tableLength();
	
	public int hashFunction(int key);
	
	public Element<K,E>[] getTable();

	
}
