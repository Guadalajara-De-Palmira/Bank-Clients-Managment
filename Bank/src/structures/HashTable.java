package structures;

import customStructureExceptions.FullStructureException;
import customStructureExceptions.NotFoundException;

public class HashTable<K,E> implements IHashTable<K, E> {

	private Element<K,E>[] elements;
	private int length;
	
	@SuppressWarnings("unchecked")
	public HashTable(int maxLength) {
		elements = new Element[maxLength];
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void tableInsert(E element) throws FullStructureException {
		boolean inserted = false;
		
		for(int i=0; i<elements.length && !inserted;i++) {
			int index = (this.hashFunction(element.hashCode())+i)%elements.length;
			
			if(elements[index]==null) {
				
				elements[index] = new Element(element.hashCode(),element);
				inserted = true;
				
			}else if((int)elements[index].getKey()==-1 && elements[index].getElement()==null) {
				
				elements[index] = new Element(element.hashCode(),element);
				inserted = true;	
			}
		}
		if(!inserted) {
			throw new FullStructureException("The hash table is full.");
		}
		length++;
	}

	@Override
	public E tableRetrieve(K key) throws NotFoundException {
		boolean found = false;
		Element element = null;
		
		for(int i=0; i<elements.length && !found;i++) {
			
			int index = (this.hashFunction((int)key)+i)%elements.length;
			
			if(elements[index]==null) {
				throw new NotFoundException("The element was not found in the hash table");
				
			}else if((int)elements[index].getKey()==(int)key) {
				found = true;
				element = elements[index];

			}
		}
		
		if(!found) {
			throw new NotFoundException("The element was not found in the hash table");
		}
		return (E) element.getElement();
	}

	@Override
	public E tableDelete(K key) throws NotFoundException {
		boolean found = false;
		Element element = null;
		
		for(int i=0; i<elements.length && !found;i++) {
			int index =  (this.hashFunction((int)key)+i)%elements.length;

			if(elements[index]==null) {
				throw new NotFoundException("The element was not found in the hash table");
				
			}else if((int)elements[index].getKey()==(int)key) {
				found = true;
				element = elements[index];
				elements[index] = new Element(-1,null);
				length--;

			}
		}
		
		if(!found) {
			throw new NotFoundException("The element was not found in the hash table");
		}

		return (E) element.getElement();
	}

	@Override
	public boolean isEmpty() {
		boolean empty = false;
		
		if(length==0) {
			empty = true;
		}
		
		return empty;
	}

	@Override
	public int tableLength() {
		return length;
	}

	@Override
	public int hashFunction(int key) {
		return key%elements.length;
	}

	@Override
	public Element<K, E>[] getTable() {
		return elements;
	}

	
}
