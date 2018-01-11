package player;

import card.Card;
import card.CardCollection;
import game.GlobalInformation;
import strategy.Difficulty;
import strategy.Easy;


public class AIPlayer extends Player
{
	private boolean pass = false;
	private String name;
	private int playerID;
	private Difficulty difficulty;
	public AIPlayer()
	{
		super("ai");
		
	}
	
	public AIPlayer(Difficulty difficulty)
	{
		super("ai");
		this.difficulty = difficulty;
		
	}
	
	public void playGame(CardCollection drawPile, CardCollection discardPile, int currentRank, int currentSuit)
	{
		difficulty.playGame(this, drawPile, discardPile, currentRank, currentSuit);
		
	}

	public boolean isPass() {
		return pass;
	}

	public void setPass(boolean pass) {
		this.pass = pass;
	}
	
}
