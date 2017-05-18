import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class GoFish implements CardGame {
	Deck deck;
	int playerPairs;
	int dealerPairs;
	LinkedList<Card> hand = new LinkedList<Card>();
	LinkedList<Card> dealerHand = new LinkedList<Card>();
	
	public GoFish() {
		startGame();
		playerPairs += findPairs(hand);
		dealerPairs += findPairs(dealerHand);
		
		while(!deck.isEmpty() && hand.size() != 0 && dealerHand.size() != 0){
			printPairs();
			playerTurn();
			playerPairs += findPairs(hand);
			otherTurn();
			dealerPairs += findPairs(dealerHand);
		}
		determinWinner();
	}
	
	public static void main(String[] args){
		GoFish test = new GoFish();
	}
	
	public void printPairs(){
		System.out.printf("Your pairs: %d \t Dealer pairs: %d\n", playerPairs, dealerPairs);
	}
	
	public int findPairs(LinkedList<Card> hand){
		int pairs = 0;
//		System.out.printf("%d\n", hand.size());
		for(int i = 0; i < hand.size(); i++){
			for(int j = (i + 1); j < hand.size(); j++){
				if(hand.get(i).rank == hand.get(j).rank){
					pairs++;
					hand.remove(j);
					hand.remove(i);
					i--;
					break;
				}
			}
		}
		
		return pairs;
	}

	@Override
	public void startGame() {
		deck = new Deck();
		playerPairs = 0;
		dealerPairs = 0;
	
		dealCards(7);
	}

	@Override
	public void playerTurn() {
		System.out.printf("Your cards: ");
		for(int i = 0; i < hand.size(); i++){
			System.out.printf("%d ", hand.get(i).rank);
		}
		System.out.printf("\n");

		Scanner in = new Scanner(System.in);
		System.out.printf("Guess(1-13): ");
		int guess = in.nextInt();
		
		for(int i = 0; i < dealerHand.size(); i++){
			if(guess == dealerHand.get(i).rank){
				System.out.printf("You take: %d\n", guess);
				hand.add(dealerHand.get(i));
				dealerHand.remove(i);
				playerPairs += findPairs(hand);
				return;
			}
		}
		System.out.printf("Go Fish\n");
		hand.add(deck.takeCard());
		return;
	}

	@Override
	public void otherTurn() {
		Random r = new Random();
		int randomIndex = r.nextInt(dealerHand.size());
//		System.out.printf("Dealer index: %d\n", randomIndex);
		
		int guess = dealerHand.get(randomIndex).rank;
		System.out.printf("Dealer asks for: %d\n", guess);
		
		for(int i = 0; i < hand.size(); i++){
			if(guess == hand.get(i).rank){
				System.out.printf("Dealer takes: %d\n", guess);
				dealerHand.add(hand.get(i));
				hand.remove(i);
				return;
			}
		}
		dealerHand.add(deck.takeCard());
		return;
	}

	@Override
	public void determinWinner() {
		printPairs();
		if(dealerPairs > playerPairs){
			System.out.printf("Dealer wins\n");
		}else{
			System.out.printf("Plyaer wins\n");
		}
	}

	@Override
	public void dealCards(int num) {
		for(int i = 0; i < num; i++){
			hand.add(deck.takeCard());
			dealerHand.add(deck.takeCard());
		}
	}

}
