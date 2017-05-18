import java.util.LinkedList;
import java.util.Scanner;

public class BlackJack implements CardGame {
	Deck deck;
	LinkedList<Card> hand = new LinkedList<Card>();
	LinkedList<Card> dealerHand = new LinkedList<Card>();
	
	public BlackJack() {
		startGame();
		dealCards(2);
		playerTurn();
		otherTurn();
		determinWinner();
	}
	
	public static void main(String[] args){
		BlackJack test = new BlackJack();
	}

	@Override
	public void startGame() {
		deck = new Deck();
	}

	@Override
	public void playerTurn() {
		System.out.printf("Hand: ");
		for(int i = 0; i < hand.size(); i++){
			System.out.printf("%d ", hand.get(i).rank);
		}
		System.out.printf("\n");

		Scanner in = new Scanner(System.in);
		System.out.printf("Hit(1) or Stay(0): ");
		if(in.nextInt() == 1){
			dealCards(1);
			playerTurn();
		}else{
			return;
		}
	}

	@Override
	public void otherTurn() {
		int sum = 0;
		while(sum < 19){
			dealerHand.add(deck.takeCard());
			sum += dealerHand.getLast().rank;
		}
		return;
	}

	@Override
	public void determinWinner() {
		int playerSum = 0;
		int dealerSum = 0;
		
		for(int i = 0; i < hand.size(); i++){
			playerSum += hand.get(i).rank;
		}
		
		for(int i = 0; i < dealerHand.size(); i++){
			dealerSum += dealerHand.get(i).rank;
		}
		
		System.out.printf("Player sum: %d\n", playerSum);
		System.out.printf("Dealer sum: %d\n", dealerSum);


		if(playerSum > 21 || playerSum <= dealerSum){
			System.out.printf("Dealer wins\n");
		}else{
			System.out.printf("Player wins\n");
		}
	}
	
	public void dealCards(int num){
		for(int i = 0; i < num; i++){
			hand.add(deck.takeCard());
			dealerHand.add(deck.takeCard());
		}
	}

}
