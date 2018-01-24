/**
 * @author Gabriel C. Levin
 * @email gclevin@brandeis.edu
 * This class acts as the client code for the data structures and classes implemented in the other files. It allows users to play a modified version of the game Uno.
 * Up to five players can play at a time, but others may wait on deck by being added to the player queue. The class has three static variables, which are the player circle
 * (players who will be participating the current round), the player queue (players who are waiting to join a subsequent game, and the deck, which holds all the cards,
 * including the discard pile.
 */

import java.util.Arrays;
import java.util.Scanner;

public class UnoGame {
	static PlayerCircle playerCircle = new PlayerCircle();
	static UnoDeck deck;
	static Queue<Player> playerQueue;
	
	/**
	 * Initializes the game
	 * @Runtime O(1)
	 */
	public static void main(String[] args) {
		initializeGame();
	}
	
	/**
	 * Calls methods that prepare the game
	 * @Runtime O(1)
	 */
	public static void initializeGame () {
		initializePlayers();
		initializeCards();
		initializeRound();
	}
	
	/**
	 * Gets the number of players competing and their names. Then adds them to either the player circle or player queue
	 * @runtime O(n)
	 */
	public static void initializePlayers () {
		//get number of players participating
		System.out.println("How many players will be participating?");
		Scanner userInput = new Scanner(System.in);
		int numberOfPlayers = userInput.nextInt();
		
		//initialize queue
		if (numberOfPlayers > 5) {
			playerQueue = new Queue<Player>(numberOfPlayers - 4);
		} else {
			//if 5 players or less are competing
			playerQueue = new Queue<Player>(1);
		}
		
		//add players to the player circle by asking for each player's name
		System.out.println("Each Player Name Must Be Unique");
		for (int i = 0; i < numberOfPlayers; i++) {
			System.out.println("What is this player's name?");
			String name = userInput.next();
			if (i >= 5) {
				playerQueue.enqueue(new Player(name));
			} else {
				playerCircle.addToCircle(new Player(name));
			}
		}
	}
	
	/**
	 * Sets of the Uno deck that will be used, along with dealing each player the appropriate amount of cards
	 * @runtime O(n)
	 */
	public static void initializeCards () {
		System.out.println("NEW GAME");
		deck = new UnoDeck();
		
		//each player draws 7 cards and their hands are printed
		Player temp = playerCircle.getFirstPlayer();
		System.out.println("PLAYER HANDS:");
		for (int i = 0; i < playerCircle.getSize(); i++) {
			for (int j = 0; j < 7; j++) { 
				temp.addToHand(deck.drawCard());
			}
			//print each players hand
			System.out.println();
			System.out.println(temp.getName());
			System.out.println(temp.getHand().toStringForPlayer());
			System.out.println();
			
			temp = temp.getNextPlayer();
		}
		
		//discard the top card of the deck
		deck.discardCard(deck.drawCard());
		
		initializeRound();
	}
	
	/**
	 * Initializes the round. The gameplay mechanics continue until a player has run out cards in their hand
	 * @runtime O(n)
	 */
	public static void initializeRound () {
		//holds the number of times around the circle the game has gone
		int timesAroundCircle = -1;
		
		//determines the order in which the circle will go
		boolean reverse = false;
		
		Player currentPlayer = playerCircle.getFirstPlayer();
		
		while (!(currentPlayer.getPrevPlayer().winner()) && !(currentPlayer.getNextPlayer().winner()) ) {
			
			//increments the number of times around the circle by 1 if it is the turn of the player who went first
			if (currentPlayer.getName().equals(playerCircle.getFirstPlayer().getName())) {
				timesAroundCircle++;
			}
			
			//informs player of the top card of the discard pile
			System.out.println("Top Of Discard Pile:");
			System.out.println(deck.getLastDiscarded().toString());
			System.out.println();
			
			System.out.println(currentPlayer.getName());
			
			//forces the player to draw a card if the previously placed card necessitates it
			if (deck.getLastDiscarded().isDrawTwo()) {
				currentPlayer.addToHand(deck.drawCard());
				currentPlayer.addToHand(deck.drawCard());
			} else if (deck.getLastDiscarded().isWildDrawFour()) {
				currentPlayer.addToHand(deck.drawCard());
				currentPlayer.addToHand(deck.drawCard());
				currentPlayer.addToHand(deck.drawCard());
				currentPlayer.addToHand(deck.drawCard());
			}
			
			//determines and prints the cards that can be placed
			System.out.println("Card(s) That Can Be Placed: ");
			int numCardsCanBePlcaed = 0;
			int cardPosition = 0;
			SinglyLinkedNode<UnoCard> currentCard = currentPlayer.getHand().getHead();
			for (int i = 0; i < currentPlayer.getHand().size(); i++) {
				int cardOptionPlace = 0;
				cardPosition++;
				if (currentCard.getData().canBePlacedOn(deck.getLastDiscarded())) {
					numCardsCanBePlcaed++;
					System.out.println("(" + cardPosition + ")" + " " + currentCard);
				}
				currentCard = currentCard.getNext();
			}
			
			//if the player cannot place any cards, they draw a card.
			//if they can place a card, the program gets the player selection from the player
			if (numCardsCanBePlcaed == 0) {
				System.out.println("No card can be placed. One card has been drawn.");
				System.out.println();
				currentPlayer.addToHand(deck.drawCard());
			} else {
				System.out.println("Which card would you like to place (select via number)?");
				Scanner userInput = new Scanner(System.in);
				int cardToPlace = userInput.nextInt();
				System.out.println();
				
				//locates and discards the card selected by the player, placing it on top of the discard pile
				SinglyLinkedNode<UnoCard> placedCard = currentPlayer.getHand().removeIndex(cardToPlace);
				deck.discardCard(placedCard.getData());
			}
			
			//determines in which direction the next player should be chosen
			if (reverse == false) {
				if (deck.getLastDiscarded().isReverse() && numCardsCanBePlcaed != 0) {
					reverse = true;
					currentPlayer = currentPlayer.getPrevPlayer();
				} else if (deck.getLastDiscarded().isSkip() && numCardsCanBePlcaed != 0) {
					currentPlayer = currentPlayer.getNextPlayer().getNextPlayer();
				} else {
					currentPlayer = currentPlayer.getNextPlayer();
				}
			} else {
				if (deck.getLastDiscarded().isReverse() && numCardsCanBePlcaed != 0) {
					reverse = false;
					currentPlayer = currentPlayer.getNextPlayer();
				} else if (deck.getLastDiscarded().isSkip() && numCardsCanBePlcaed != 0) {
					currentPlayer = currentPlayer.getPrevPlayer().getPrevPlayer();
				} else {
					currentPlayer = currentPlayer.getPrevPlayer();
				}
			}
			
		}
		
		results(currentPlayer, timesAroundCircle, reverse);
	}
	
	/**
	 * This method determines the results of the game (winner, loser, and times around the circle). Since the rules of Uno allow the direction
	 * in which the players take their turns to change, there is no obvious metric for determining the number of times around the circle. I determined that
	 * what made the most sense was to simply increment the number of times around the circle by one every time the first player took a turn (not counting the very first
	 * turn taken).
	 * @param currentPlayer
	 * @param timesAroundCircle
	 * @param reverse
	 * @runtime O(n)
	 */
	public static void results(Player currentPlayer, int timesAroundCircle, boolean reverse) {
		System.out.println("RESULTS");
		
		 //prints the number of times around the circle
		System.out.println("Number Of Time Around Circle: " + timesAroundCircle);
		
		//determines the winner based on the direction in which the game was going
		if (reverse == false) {
			System.out.println("Winner: " + currentPlayer.getPrevPlayer().getName());
		} else {
			System.out.println("Winner: " + currentPlayer.getNextPlayer().getName());
		}

		// holds the players who will continue on to the next round in an array
		Player winnerArray[] = new Player[4];
		int place = 0;

		//determines which player has the most cards and deems them the loser
		Player loser = playerCircle.getFirstPlayer();
		Player tempPlayer = playerCircle.getFirstPlayer().getNextPlayer();
		for (int i = 0; i < playerCircle.getSize() - 1; i++) {
			if (tempPlayer.getHand().size() > loser.getHand().size()) {
				winnerArray[place] = loser;
				place++;
				loser = tempPlayer;
			} else {
				winnerArray[place] = tempPlayer;
				place++;
			}
			tempPlayer = tempPlayer.getNextPlayer();
		}

		System.out.println("Loser: " + loser.getName());
		System.out.println();
		resetPlayers(winnerArray, loser);
	}
	
	/**
	 * This method sets up the roster for the next game, along with managing the queue
	 * @param winnerArray Contains all the players who get to play in the following round
	 * @param loser The player who does not get to participate in the following round
	 * @runtime O(n)
	 */
	public static void resetPlayers (Player[] winnerArray, Player loser) {
		playerCircle = new PlayerCircle();
		loser.resetHand();
		playerQueue.enqueue(loser);
		
		//adds the player at the front of the player queue to the player circle for the next game
		playerCircle.addToCircle(playerQueue.dequeue());	


		
		//iterates through all the players in the winnerArray resets their hands and adds them to a new player circle
		int i = winnerArray.length;
		int j = 0;
		while (j < i && winnerArray[j] != null) {
			winnerArray[j].resetHand();
			playerCircle.addToCircle(winnerArray[j]);;
			j++;
		}
		
		System.out.println("Type anything to begin the next round: ");
		Scanner userInput = new Scanner(System.in);
		userInput.next();
		initializeCards();	
	}
}
