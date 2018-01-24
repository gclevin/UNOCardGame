/**
 * Class represents a deck of Uno cards
 * @author Gabriel C. Levin and COSI 21A TAs
 * @email gclevin@brandeis.edu
 */

import java.util.*;
public class UnoDeck {
	private static final String[] REGULAR_COLORS = {"red", "yellow", "blue", "green"};
	private SinglyLinkedList<UnoCard> deck; 
	private SinglyLinkedList<UnoCard> discard; 
	private UnoCard lastDiscarded;
	
	//http://play-k.kaserver5.org/Uno.html
	// There are 108 cards in a Uno deck. 
	// There are four suits, Red, Green, Yellow and Blue, 
	// each consisting of one 0 card, two 1 cards, two 2s, 3s, 4s, 5s, 6s, 7s, 8s and 9s; 
	// two Draw Two cards; two Skip cards; and two Reverse cards. 
	// In addition there are four Wild cards and four Wild Draw Four cards.

	public UnoDeck(){
		deck = new SinglyLinkedList<UnoCard>();
		discard = new SinglyLinkedList<UnoCard>();
		lastDiscarded = null;
		
		// Initialized as having all 108 cards, as described above	
		for (String color : REGULAR_COLORS){
			deck.randomInsert(new UnoCard(color, 0)); // add one of your color in zero
			for (int i = 0; i<2; i++){
				// add numbers 1-9
				for (int cardNumber = 1; cardNumber<=9; cardNumber++){
					deck.randomInsert(new UnoCard(color, cardNumber)); // Add this to deck - NOTE this line has changed
				}
				// add 2 of each of the special card for that color
				deck.randomInsert(new UnoCard(color, true, false, false)); // add to deck
				deck.randomInsert(new UnoCard(color, false, true, false)); // add to deck
				deck.randomInsert(new UnoCard(color, false, false, true)); // add to deck
			}
			
		}
		// add 4 wild cards, and 4 draw 4 wild cards
		for (int i = 0; i<4; i++){
			deck.randomInsert(new UnoCard(false)); // add to deck
			deck.randomInsert(new UnoCard(true)); // add to deck
		}
	}
	
	/**
	 * @return Returns the card held in the lastDiscarded variable
	 * @Runtime: O(1)
	 */
	public UnoCard getLastDiscarded (){
		return lastDiscarded;
	}
	
	/**
	 * Removes then returns the top card on the deck
	 * @return Returns a UnoCard
	 * @Runtime: O(1)
	 */
	public UnoCard drawCard (){		
		if (deck.getHead() == null) {
			//if the deck is empty, the discard pile becomes the deck and the discard pile is set to empty
			//a card is then returned
			this.deck = this.discard;
			this.discard = new SinglyLinkedList<UnoCard>();
			return deck.removeIndex(1).getData();
		} else {
			return deck.removeIndex(1).getData();
		}
	}
	
	/**
	 * @param c Randomly places c in the discard pile
	 * @Runtime: O(1)
	 */
	public void discardCard(UnoCard c) {
		if (lastDiscarded == null) {
			lastDiscarded = c;
			discard.randomInsert(c);
		} else if (lastDiscarded.canBePlacedOn(c)) {
			lastDiscarded = c;
			discard.randomInsert(c);
		} else {
			System.out.println("Card cannot be placed");
		}
	}
	
	/**
	 * Returns a string representation of all the cards in the deck
	 * @Runtime: O(n)
	 */
	public String toString(){
		String toReturn = "";
		SinglyLinkedNode tempNode = deck.getHead();
		for (int i = 0; i < deck.size(); i++) {
			toReturn = toReturn + " " + tempNode.getData().toString();
			tempNode = tempNode.getNext();
		}	
		return toReturn;
	}
}