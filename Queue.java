/**
 * This is a from scratch implementation of a queue. It can be used with any data type.
 * @author Gabriel C. Levin
 * @email gclevin@brandeis.edu
 */

import java.util.Arrays;

public class Queue<T> {
	private Object[ ] queueArray; 
	private int front = 0;
	private int rear = 0;
	private int size = 0;
	
	/**
	 * THe constructor receives and int and creates a queue of that size
	 * @param size Determines what size the queue will be
	 * @Runtime: O(1)
	 */
	public Queue (int size) {
		queueArray = new Object[size];
	}
	
	/**
	 * Add an element to the cue
	 * @param data Data is the information that will be stored in the newly inserted slot
	 * @Runtime: O(1)
	 */
	public void enqueue (T data) {
		if (isFull() == true) {
			//if queue is full
			System.out.println("Queue Full");
		} else {
			this.size = this.size + 1;
			queueArray[this.rear] = data;
			this.rear = (this.rear + 1) % queueArray.length;
		}
		
	}
	
	/**
	 * @return Returns the element at the front of the queue
	 * @Runtime: O(1)
	 */
	public T dequeue () {
		if (isEmpty() == true) {
			System.out.println("Queue Empty");
			return null;
		} else {
			T toReturn = (T) queueArray[this.front];
			this.size = this.size - 1;
			this.front = (this.front + 1) % queueArray.length;
			return toReturn;
		}
	}
	
	/**
	 * Determines whether the queue is full
	 * @return Returns true if the queue is full
	 * @Runtime: O(1)
	 */
	public boolean isFull () {
		if (this.size == queueArray.length) {
			return true;
		} else{
			return false;
		}
	}
	
	/**
	 * Determines whether the queue is empty
	 * @return Returns true if the queue is empty
	 * @Runtime: O(1)
	 */
	public boolean isEmpty () {	
		if (this.size == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns the size of the queue/the number of elements it holds
	 * @return Returns the size of the queue
	 * @Runtime: O(1)
	 */
	public int getSize () {
		return this.size;
	}
	
	/**
	 * Prints the queue in string form, along with all relevant information
	 * @Runtime: O(1)
	 */
	public void asString() {
		System.out.println("");
		System.out.println("REPORT:");
		System.out.println(Arrays.toString(this.queueArray));
		System.out.println("Front: " + this.front);
		System.out.println("Rear: " + this.rear);
		System.out.println("Size: " + getSize());
		System.out.println("");
	}
	
}
