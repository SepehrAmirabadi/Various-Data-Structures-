package comp2402a2;

import java.util.AbstractList;
import java.util.Collection;
import java.util.ArrayList; 

/**
 * @author morin
 *
 * @param <T> the type of objects stored in the List
 */
public class BlockedList<T> extends AbstractList<T> {
	/**
	 * keeps track of the class of objects we store
	 */
	Factory<T> f;



	ArrayDeque<T> list; 
	//number of blocks stored
	int numb; 
	/**
	 * The number of elements stored
	 */
	int n;

	/**
	 * The block size
	 */
	int b;

	/**
	 * Constructor
	 * @param t the type of objects that are stored in this list
	 * @param b the block size
	 */
	public BlockedList(Class<T> t, int b) {
		f = new Factory<T>(t);
		n = 0;
		this.b = b ;
		list = new ArrayDeque<T> (t);
	}

	public int size() {
		return n;
	}

	public T get(int i) {
		// TODO: Implement this
		if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
		return list.get(i); 
	}

	public T set(int i, T x) {
		// TODO: Implement this
		if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
		return list.set(i, x); 
	}

	public void add(int i, T x) {
		// TODO: Implement this
		if (i < 0 || i > n) throw new IndexOutOfBoundsException();
		list.add(i, x); 
		this.n = list.size();
	}

	public T remove(int i) {
		// TODO: Implement this
		if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
		this.n = list.size()-1;
		return list.remove(i); 
	}
	
}
