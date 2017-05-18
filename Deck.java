import java.util.Collections;
import java.util.Stack;

public class Deck {
	Stack<Card> deck = new Stack<Card>();
	
	public Deck() {
		for(int suit = 1; suit <= 4; suit++){
			for(int rank = 1; rank <= 13; rank++){
				deck.push(new Card(rank, suit));
			}
		}
		Collections.shuffle(deck);
	}

	public Card takeCard(){
		return deck.pop();
	}
	
	public boolean isEmpty(){
		return deck.isEmpty();
	}
}
