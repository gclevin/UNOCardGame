/**
 * This class is for a singly linked list. It has one field, which is a reference to the first node in the list
 * @author Gabriel C. Levin
 * @email gclevin@brandeis.edu
 * @param <T> Receives data of type T, which allows this class to be used for various types of inputs
 */

import java.util.Random;

public class SinglyLinkedList<T> {
	private SinglyLinkedNode head;
	
	/**
	 *  @return Returns the node held in the "head" field
	 *  @Runtime O(1)
	 */
	public SinglyLinkedNode<T> getHead(){ 
		return head;
	} 
	
	/**
	 * Inserts a node at the end of the list
	 * @param data is the information hat will be held in the newly created node
	 * @Runtime O(n)
	 */
	public void regularInsert(T data) {
		if (this.head == null){
			//if the list is empty
			SinglyLinkedNode temp = new SinglyLinkedNode(data);
			this.head = temp;
		} else {
			SinglyLinkedNode tempNode = this.head;
			//runs through the list to get the final node
			for (int i = 1; i < this.size(); i++) {
				tempNode = tempNode.getNext();
			}
			SinglyLinkedNode temp = new SinglyLinkedNode(data);
			tempNode.setNext(temp);
		}
	}
	
	/**
	 * Inserts a node in a random place in the list
	 * @param data is the information hat will be held in the newly created node
	 * @Runtime O(n)
	 */
	public void randomInsert(T data) {
		if (this.head == null) {
			//if the list is empty, the new node is made the head of the list
			this.head = new SinglyLinkedNode(data);
		} else {
			Random rand = new Random();
			int n = rand.nextInt(this.size()); //random number determines the spot where the new node will be inserted
			if (n == 0) {
				//if the node is to be inserted at the head
				SinglyLinkedNode originalHeadTemp = this.head;
				this.head = new SinglyLinkedNode(data);
				this.head.setNext(originalHeadTemp);
			} else if (n == this.size()) {
				//if the node is to be inserted at the end
				SinglyLinkedNode tempNode = this.head;
				while (tempNode.getNext() != null) {
					tempNode = tempNode.getNext();
				}
				tempNode.setNext(new SinglyLinkedNode(data));
			} else {
				//if the node is to be inserted anywhere other than the head or the tail
				SinglyLinkedNode tempNode = this.head;
				for (int i = 0; i < n; i++) {
					tempNode = tempNode.getNext();
				}
				SinglyLinkedNode tempNext = tempNode.getNext();
				tempNode.setNext(new SinglyLinkedNode(data));
				tempNode.getNext().setNext(tempNext);
			}
		}
	}
	
	/**
	 * Removes the node in the list containing the data passed in as a parameter. If the data does not exist, nothing is deleted
	 * @param finds the node in the list that stores data and removes it
	 * @Runtime O(n)
	 */
	public void remove(T data) {
		SinglyLinkedNode tempNode = this.head;
		if (this.head == null) {
			System.out.println("List Already Empty");
		} else if (tempNode.getData().equals(data)){
			//if data is held at the first node of the list
			this.head = this.head.getNext();
		} else {
			//if the data is held somewhere other than the head
			SinglyLinkedNode nodeToDelete = this.head.getNext();
			for (int i = 0; i < this.size() - 1; i++) {
				if (nodeToDelete.getData().equals(data)) {
					tempNode.setNext(nodeToDelete.getNext());
					break;
				} else {
					tempNode = tempNode.getNext();
					nodeToDelete = nodeToDelete.getNext();
				}
			}
		}
	}
	
	/**
	 * Removes a node at a specified index
	 * @Runtime O(n)
	 * @param index the spot where a node should be removed
	 * @return the removed node
	 */
	public SinglyLinkedNode<T> removeIndex (int index) {
		if (index == 1) {
			//if the index is 1
			SinglyLinkedNode toReturn = this.head;
			this.head = this.head.getNext();
			return toReturn;
		} else if (index == size()) {
			//if the index corresponds to the last node in the list
			SinglyLinkedNode tempNode = this.head;
			for (int i = 0; i < this.size() - 2; i++) {
				tempNode = tempNode.getNext();
			}
			SinglyLinkedNode toReturn = tempNode.getNext();
			tempNode.setNext(null);
			return toReturn;
		} else {
			//if index corresponds to a node not at the beginning or end of the list
			SinglyLinkedNode tempNode = this.head;
			SinglyLinkedNode nodeToDelete = this.head.getNext();
			for (int i = 0; i < index - 2; i++) {
				tempNode = tempNode.getNext();
				nodeToDelete = nodeToDelete.getNext();
			}
			SinglyLinkedNode toReturn = nodeToDelete;
			tempNode.setNext(nodeToDelete.getNext());
			return toReturn;		
		}
	}	
	
	/**
	 * Iterates through all the nodes in the list to determine the list's size
	 * @return Returns the size of the list
	 */
	public int size() {
		if (head == null) {
			return 0;
		} else {
			SinglyLinkedNode tempNode = this.head;
			int count = 1;
			while (tempNode.getNext() != null) {
				tempNode = tempNode.getNext();
				count++;
			}
			return count;
		}
	}
	
	/**
	 * @return Returns a representation of the list
	 * @Runtime O(n)
	 */
	public String toString() {
		if (this.head == null){
			return "List Empty";
		} else {
			String listRepresentation = this.head.toString();
			SinglyLinkedNode tempNode = this.head.getNext();
			for (int i = 0; i < this.size() - 1; i++) {
				listRepresentation = listRepresentation + " --> " + tempNode.toString();
				tempNode = tempNode.getNext();
			}
			return listRepresentation;
		}	
	}
	
	/**
	 * @return Returns a representation of the list that is more suitable for the player to read
	 * @Runtime O(n)
	 */
	public String toStringForPlayer() {
		String listRepresentation = this.head.toString();
		SinglyLinkedNode tempNode = this.head.getNext();
		for (int i = 0; i < this.size() - 1; i++) {
			listRepresentation = listRepresentation + ", " + tempNode.toString();
			tempNode = tempNode.getNext();
		}
		return listRepresentation;
	}
}
