public interface CardGame {

	public void startGame();
	
	public void playerTurn();
	
	public void otherTurn();
	
	public void determinWinner();
	
	public void dealCards(int num);
}
