import java.util.Random;

public class OverloadDemo {
	
	public static void main(String[] args) throws InterruptedException {
	
		StackUsingArray<Character> theStack = new StackUsingArray<Character>(Character.class);
		QueueUsingArray<Character> theQueue = new QueueUsingArray<Character>(Character.class);
		
		int operationSequenceLength;
		int operationCounter;
		
		System.out.println("Beginning the Stack Overload Test");
		operationSequenceLength = 1;
		operationCounter = 0;
		try {
			theStack.showContents("");
			while(true) { // but obviously change this during the demo
				for(int i = 0; i < operationSequenceLength; i++) {
					theStack.push('x');
					operationCounter++;
					theStack.showContents("push");
				}
				for(int i = 0; i < operationSequenceLength; i++) {
					theStack.pop();
					operationCounter++;
					theStack.showContents("pop");
				}
				operationSequenceLength++;
			}
		} catch (Exception e) {
			System.out.println("Your stack has died after " + operationCounter + " operations. My condolences.");
		}
		Thread.sleep(5000);		
		System.out.println();
		
		System.out.println("Beginning the Queue Overload Test");
		operationSequenceLength = 1;
		operationCounter = 0;
		try {
			theQueue.showContents("");
			while(true) {
				for(int i = 0; i < operationSequenceLength; i++) {
					theQueue.add('x');
					operationCounter++;
					theQueue.showContents("add");
				}
				for(int i = 0; i < operationSequenceLength; i++) {
					theQueue.remove();
					operationCounter++;
					theQueue.showContents("remove");
				}
				operationSequenceLength++;
			}
		} catch (Exception e) {
			System.out.println("Your queue has died after " + operationCounter + " operations. My condolences.");
		}
		
	}
	
}

class StackUsingArray<T> /* implements "Stack" */ { 

	/**
    * an instance of the "Factory" for allocating an array of generic type T
    */
	private Factory<T> factory;

	/**
    * the backing array that contains the data
    */
	private T[] data;

	/**
    * the number of elements (n.b., the index of top + 1, but not the capacity)
    */
	private int size;

	/**
	* this is the default (and only) constructor for StackUsingArray
	*
	* @param   t   the type parameter for the objects being collected
	*/
	public StackUsingArray(Class<T> t) {
		factory = new Factory<T>(t);
		data = factory.newArray(20);
		size = 0;
	}

	/**
	* this prints the contents (n.b., not actually part of the interface)
	*/
	public void showContents(String s) throws InterruptedException{
		System.out.print("Stack :: ");
		for (int i = 0; i < data.length; i++) {
			if (i < size) {
				System.out.print("["+data[i]+"]");
			} else {
				System.out.print("[ ]");
			}
		}
		System.out.println(" " + s);
		Thread.sleep(100);
		//for(int j = 0; j < 200; j++) {
		//	System.out.println("\b") ;
		//}
		//System.out.flush();
	}
	
	/**
	* this removes and returns the value at the top of the stack
	*
	* @return  the element at the top of the stack
	*/	
	public T pop() {
		size--;
		T element = data[size];
		return element;
	}

	/**
	* this adds a new element at the top of the stack
	*/
	public void push(T next) {
		data[size] = next;
		size++;
	}

}

class QueueUsingArray<T> /* implements "Queue" */ { 

	/**
    * an instance of the "Factory" for allocating an array of generic type T
    */
	private Factory<T> factory;

	/**
    * the backing array that contains the data
    */
	private T[] data;

	/**
    * the front and back of the queue; n.b., back > front
    */
	private int front;
	private int back;

	/**
	* this is the default (and only) constructor for StackUsingArray
	*
	* @param   t   the type parameter for the objects being collected
	*/
	public QueueUsingArray(Class<T> t) {
		factory = new Factory<T>(t);
		data = factory.newArray(20);
		front = 0;
		back = 0;
	}

	/**
	* this prints the contents (n.b., not actually part of the interface)
	*/
	public void showContents(String s) throws InterruptedException{
		System.out.print("Queue :: ");
		for (int i = 0; i < data.length; i++) {
			if (i >= front && i < back) {
				System.out.print("["+data[i]+"]");
			} else {
				System.out.print("[ ]");
			}
		}
		System.out.println(" " + s);
		Thread.sleep(100);
		//for(int j = 0; j < 200; j++) {
		//	System.out.println("\b") ;
		//}
		//System.out.flush();
	}

	/**
	* this removes and returns the value at the top of the stack
	*
	* @return  the element at the top of the stack
	*/	
	public T remove() {
		T element = data[front];
		front++;
		return element;
	}

	/**
	* this adds a new element at the top of the stack
	*/
	public void add(T next) {
		data[back] = next;
		back++;
	}
}