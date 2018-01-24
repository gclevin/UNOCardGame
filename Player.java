/**
 * This class represents a player in a game of Uno. The player has a name, a list of cards (hand), and a reference to a previous and next player
 * @author Gabriel C. Levin
 * @Email gclevin@brandeis.edu
 */
public class Player {
	private String name;
	private Player nextPlayer=null;
	private Player prevPlayer=null;
	private SinglyLinkedList<UnoCard> hand; 
	
	/**
	 * Constructor sets the name of the player to the String passed in as a parameter
	 * @Runtime: O(1)
	 */
	public Player(String name){
		this.name = name;
		hand = new SinglyLinkedList<UnoCard>();
	}
	
	/**
	 * Resets the players hand to be empty
	 * @Runtime: O(1)
	 */
	public void resetHand () {
		hand = new SinglyLinkedList<UnoCard>();
	}
	
	/**
	 * Returns the player's hand
	 * @Runtime: O(1)
	 */
	public SinglyLinkedList<UnoCard> getHand () {
		return hand;
	}
	
	/**
	 * Returns the player's name
	 * @Runtime: O(1)
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Adds a card (UnoCard c) to the player's hand
	 * @Runtime: O(1) (.regularInsert(c) has a runtime of O(n))
	 */
	public void addToHand(UnoCard c){
		hand.regularInsert(c);
	}
	
	/**
	 * Removes a card from a given index
	 * @param index is the position at which a node is to be removed
	 * @Runtime: O(1) (.removeIndex(index) has a runtime of O(n))
	 */
	public void removeFromHand(int index){
		//treats the first element in the linked list as index 1, NOT 0
		hand.removeIndex(index);
	}
	
	/**
	 * @return Returns true if the player's hand is empty
	 * @Runtime O(1)
	 */
	public boolean winner(){
		if (hand.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @return Returns the player referenced in the nextPlayer field
	 * @Runtime: O(1)
	 */
	public Player getNextPlayer() {
		return nextPlayer;
	}
	
	/**
	 * @param nextPlayer is the player that will be referenced in the nextPlayer field
	 * @Runtime: O(1)
	 */
	public void setNextPlayer(Player nextPlayer) {
		this.nextPlayer = nextPlayer;
	}
	
	/**
	 * @return Returns the player referenced in the prevPlayer field
	 * @Runtime: O(1)
	 */
	public Player getPrevPlayer() {
		return prevPlayer;
	}
	
	/**
	 * @param prevPlayer is the player that will be referenced in the prevPlayer field
	 * @Runtime: O(1)
	 */
	public void setPrevPlayer(Player prevPlayer) {
		this.prevPlayer = prevPlayer;
	}

	/**
	 * Returns the name of the player as a string
	 * @Runtime: O(1)
	 */
	public String toString() {
		return "Player [name=" + name + "]";
	}
	
	
}
