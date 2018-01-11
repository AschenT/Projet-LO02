package strategy;

import card.CardCollection;
import player.AIPlayer;

public interface Difficulty {
	public void playGame(AIPlayer ai, CardCollection drawPile, CardCollection discardPile, int currentRank, int currentSuit);

}
