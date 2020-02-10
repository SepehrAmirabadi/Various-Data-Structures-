import java.util.Random;

public class ArrayStackDemo {
	
	public static void main(String[] args) throws InterruptedException {
	
		if (args.length < 3) {
			System.err.println();
			System.err.println("Error: Insufficient number of arguments");
			System.err.println("Usage: java W04A02LiveBackingArrayListDemo numOps numAdd lenSlp");
			System.err.println("  numOps:   the number of add/remove operations to demo");
			System.err.println("  numAdd:   the number of initial add operations");
			System.err.println("  lenSlp:   the duration (ms) of the pause between operations");
			System.exit(1);
		}
		
		int argNumOperations = Integer.parseInt(args[0]);
		int argDemoBiasToAdd = Integer.parseInt(args[1]);
		int argSleepDuration = Integer.parseInt(args[2]);
		
		Random prng = new Random();
		
		StackUsingArray<Character> theStack = new StackUsingArray<Character>(Character.class);
		
		char element;
		
		theStack.showContents();

		for (int i = 0; i < argNumOperations; i++) {
		
			if (prng.nextInt(2) == 0 || theStack.size() == 0 || argDemoBiasToAdd > 0) {
				element = "abcdefghijklmnopqrstuvwxyz".charAt(prng.nextInt(26));
				theStack.push(element);
				System.out.println("'"+element+"' was pushed");
				argDemoBiasToAdd--;
			} else {
				element = theStack.pop();
				System.out.println("'"+element+"' was popped");
			}

			theStack.showContents();
			
			Thread.sleep(argSleepDuration);
			
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
		data = factory.newArray(1);
		size = 0;
	}

	/**
	* this prints the contents (n.b., not actually part of the interface)
	*/
	public void showContents(){
		if (size == 0) {
			System.out.print("/");
		} else {
			for (int i = 0; i < data.length; i++) {
				if (i < size) {
					System.out.print("["+data[i]+"]");
				} else {
					System.out.print("[ ]");
				}
			}
		}
		System.out.println();
		System.out.println();
	}
		
	/**
	* this gets the value of the private variable size
	*
	* @return  the size of the array
	*/
	public int size() {
		return size;
	}
	
	/**
	* this returns the value at the top of the stack without removing it
	*
	* @return  the element at the top of the stack
	*/
	public T top() {
		if (size == 0) {
			throw new IndexOutOfBoundsException();
		}
		return data[size-1];
	}

	/**
	* this removes and returns the value at the top of the stack
	*
	* @return  the element at the top of the stack
	*/	
	public T pop() {
		
		if (size == 0) {
			throw new IndexOutOfBoundsException();
		}
		size--;

		T element = data[size];
		
		// check if the array capacity is thrice the stack size or more
		if (data.length >= 3*size) {
			// and if so, resize the backing array
			resize();
		}
	
		return element;
		
	}

	/**
	* this adds a new element at the top of the stack
	*/
	public void push(T next) {
		
		// check if the size of the stack is already the capacity of the array
		if (size == data.length) {
			// and if so, resize the backing array
			resize();
		}
		
		data[size] = next;
		size++;
		
	}
	
	/**
	* this resizes the backing array
	*/
	protected void resize() {
		T[] resized = factory.newArray(Math.max(size*2,1));
		for (int i = 0; i < size; i++) {
			resized[i] = data[i];
		}
		data = resized;
	}
}