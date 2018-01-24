/**
 * This class represents a circle of players playing the game Uno. The only field references in the first player in the circle. Each player has a reference to the
 * previous and next player, making this a modified doubly linked list.
 * @author Gabriel C. Levin
 * @email gclevin@brandeis.edu
 */
public class PlayerCircle {
	private Player head;
	
	/**
	 * @param p Adds player p to the circle in alphabetical order
	 * @Runtime: O(n)
	 */
	public void addToCircle(Player p) {
		if (this.head == null) {
			//if the the player circle is empty
			this.head = p;
			p.setNextPlayer(p);
			p.setPrevPlayer(p);
		}  else if (this.getSize() == 1) { 
			//if there is only one person in the circle
			p.setNextPlayer(this.head);
			p.setPrevPlayer(this.head);
			
			this.head.setNextPlayer(p);
			this.head.setPrevPlayer(p);
			
			//checks to see if p comes first alphabetically
			//if so, p is made head
			if (p.getName().compareToIgnoreCase(this.head.getName()) < 0) {
				this.head = p;
			} 
		} else {
			if (p.getName().compareToIgnoreCase(this.head.getName()) < 0) {
				//if the new name should go first
				p.setNextPlayer(this.head);
				p.setPrevPlayer(this.head.getPrevPlayer());
				
				this.head.getPrevPlayer().setNextPlayer(p);
				this.head.setPrevPlayer(p);
				
				this.head = p;
			} else if (p.getName().compareToIgnoreCase(this.head.getPrevPlayer().getName()) > 0) {
				//if the new name should go last
				p.setNextPlayer(this.head);
				p.setPrevPlayer(this.head.getPrevPlayer());
				
				this.head.getPrevPlayer().setNextPlayer(p);
				this.head.setPrevPlayer(p);
			} else {
				//if the name belongs between two existing names
				Player temp1 = this.head;
				Player temp2 = this.head.getNextPlayer();
				while(!(p.getName().compareToIgnoreCase(temp1.getName()) > 0 && p.getName().compareToIgnoreCase(temp2.getName()) < 0)) {
					temp1 = temp1.getNextPlayer();
					temp2 = temp2.getNextPlayer();
				}

				p.setNextPlayer(temp2);
				p.setPrevPlayer(temp1);
				
				temp1.setNextPlayer(p);
				temp2.setPrevPlayer(p);	
			}					
		}
	}
	
	/**
	 * @return Returns the first player in the circle
	 * @Runtime: O(1)
	 */
	public Player getFirstPlayer() {
		return this.head;
	}
	
	/**
	 * @return Returns the number of players in the circle
	 * @Runtime: O(n)
	 */
	public int getSize() {
		if (this.head == null) {
			//if the circle is empty
			return 0;
		} else {
			int toReturn = 1;
			Player tempPlayer = this.head;
			//runs until it has reached the initial player in the circle
			while (!(tempPlayer.getNextPlayer().getName().equals(this.head.getName()))) {
				tempPlayer = tempPlayer.getNextPlayer();
				toReturn++;
			}
			return toReturn;
		}
	}
	
	/**
	 * Returns a string representation of all the players in the circle
	 * @Runtime: O(n)
	 */
	public String toString() {
		String toReturn = "--> ";
		Player tempPlayer = this.head;
		for (int i = 0; i < this.getSize(); i++) {
			toReturn = toReturn + tempPlayer.getName() + " --> ";
			tempPlayer = tempPlayer.getNextPlayer();
		}
		return toReturn;
	}
}
