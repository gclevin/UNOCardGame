/**
 * This class is for a singly linked node to be used in a singly linked list. The node has two fields, one for data and the other a reference to another node
 * @author Gabriel C. Levin
 * @email gclevin@brandeis.edu
 * @param <T> Receives data of type T, which allows this class to be used for various types of inputs
 */

public class SinglyLinkedNode<T> {
	private T data;
	private SinglyLinkedNode<T> next;
	
	/**
	 * Constructor for creating a Singly Linked Node
	 * @Runtime: O(1)
	 * @param data is the information that will be held within the node
	 */
	public SinglyLinkedNode (T data) {
		this.data = data;
	}
	
	/**
	 * @return Returns the information held in the node's data field
	 * @Runtime: O(1)
	 */
	public T getData() {
		return this.data;
	}
	
	/**
	 * @param Sets the the information in the "next" field with whatever is thrown in as a parameter
	 * @Runtime: O(1)
	 */
	public void setNext(SinglyLinkedNode<T> next) {
		this.next = next;
	}
	
	/**
	 * @return Returns the information held in the node's next field (will always be another node)
	 * @Runtime: O(1)
	 */
	public SinglyLinkedNode<T> getNext() {
		return this.next;
	}
	
	/**
	 * @return Returns the information held in the data field as a string
	 * @Runtime: O(1)
	 */
	public String toString() {
		return data.toString();
	}
}