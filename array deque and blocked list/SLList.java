package comp2402a2;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.Queue;
import java.util.NoSuchElementException;

/**
 * An implementation of a FIFO Queue as a singly-linked list.
 * This also includes the stack operations push and pop, which
 * operate on the head of the queue
 * @author morin
 *
 * @param <T> the class of objects stored in the queue
 */
public class SLList<T> extends AbstractList<T> implements Queue<T> {
	class Node {
		T x;
		Node next;
	}

	/**
	 * Front of the queue
	 */
	Node head;

	/**
	 * Tail of the queue
	 */
	Node tail;

	/**
	 * The number of elements in the queue
	 */
	int n;

	public T get(int i) {
		if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
		return getNode(i).x;
	}

	public T set(int i, T x) {
		if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
		Node temp = new Node();
		temp.x =getNode(i).x;
		getNode(i).x=x;
		return temp.x;
	}

	public void add(int i, T x) {
		if (i < 0 || i > n) throw new IndexOutOfBoundsException();
		Node temp = new Node();
        temp.x = x;
        if (n == 0) {
			head = temp;
			tail = temp;
		}
		else if(i==n){
			tail.next = temp;
			tail = temp;
		}
		else if(i==0 && n!=0){
			temp.next=head;
			head=temp;
			
		}
		else{
			Node prev =getNode(i-1);
			addAfter(prev, temp);
		}
        n++;
	}

	public T remove(int i) {
		if (i < 0 || i > n - 1) throw new IndexOutOfBoundsException();
		T output;
        if (head == null){
			return null; 
		} 
		Node temp = head; 
        if (i == 0) { 
			output=head.x;
			head = temp.next; 
			n--;
            return output; 
		}
		output = getNode(i).x;
		temp = getNode(i-1);
		deleteNext(temp);
		n--;
		return output;
	}

	public void reverse() {
		Node curr = head;
    	Node next = null;
		Node prev = null;


	while(curr!=null){
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
	   }
	   head = prev;
	}

	public Iterator<T> iterator() {
		class SLIterator implements Iterator<T> {
			protected Node p;

			public SLIterator() {
				p = head;
			}
			public boolean hasNext() {
				return p != null;
			}
			public T next() {
				T x = p.x;
				p = p.next;
				return x;
			}
			public void remove() {
				throw new UnsupportedOperationException();
			}
		}
		return new SLIterator();
	}

	public int size() {
		return n;
	}

	public boolean add(T x) {
		Node u = new Node();
		u.x = x;
		if (n == 0) {
			head = u;
		} else {
			tail.next = u;
		}
		tail = u;
		n++;
		return true;
	}

	public boolean offer(T x) {
		return add(x);
	}

	public T peek() {
		if (n == 0) return null;
		return head.x;
	}

	public T element() {
		if (n == 0) throw new NoSuchElementException();
		return head.x;
	}

	public T poll() {
		if (n == 0)
			return null;
		T x = head.x;
		head = head.next;
		if (--n == 0)
			tail = null;
		return x;
	}

	/**
	 * Stack push operation - push x onto the head of the list
	 * @param x the element to push onto the stack
	 * @return x
	 */
	public T push(T x) {
		Node u = new Node();
		u.x = x;
		u.next = head;
		head = u;
		if (n == 0)
			tail = u;
		n++;
		return x;
	}

	protected void deleteNext(Node u) {
		if (u.next == tail)
			tail = u;
		u.next = u.next.next;
	}

	protected void addAfter(Node u, Node v) {
		v.next = u.next;
		u.next = v;
		if (u == tail)
			tail = v;
	}

	protected Node getNode(int i) {
		Node u = head;
		for (int j = 0; j < i; j++)
			u = u.next;
		return u;
	}

	/**
	 * Stack pop operation - pop off the head of the list
	 * @return the element popped off
	 */
	public T remove() {
		if (n == 0)	return null;
		T x = head.x;
		head = head.next;
		if (--n == 0) tail = null;
		return x;
	}

	public T pop() {
		if (n == 0)	return null;
		T x = head.x;
		head = head.next;
		if (--n == 0) tail = null;
		return x;
	}


	public static void main(String[] args) {

	}
}
