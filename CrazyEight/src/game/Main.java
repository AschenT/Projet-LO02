package game;

import Controleur.MenuControleur;
import Vue.Interface;
import card.Card;
import card.CardCollection;

public class Main {
	public static void main(String arg[]) {
		Interface view = new Interface();
		view.getFrame().setVisible(true);
		Eight crazyEight = new Eight();
		MenuControleur controleur = new MenuControleur(view,crazyEight);
		crazyEight.startGame();
		// crazyEight.showPlayersCards();
		crazyEight.setTurn(0);
		crazyEight.setWinner(-1);
		int p;

		while (crazyEight.getWinner() == -1) {
			for (crazyEight.setTurn(0); crazyEight.getTurn() < crazyEight.getPlayerList().size(); crazyEight.turn++) {
				if (crazyEight.getDrawPile().getCardCollection().size() >= 5) {
					crazyEight.getPlayerList().get(crazyEight.turn).playGame(crazyEight.getDrawPile(), crazyEight.getDiscardPile(),
							crazyEight.getCurrentRank(), crazyEight.getCurrentSuit());
					
					if (crazyEight.getPlayerList().get(crazyEight.turn).getPlayableCards().getCardCollection().size()!=0) {
						crazyEight.setCurrentRank(crazyEight.getDiscardPile().getCardCollection()
								.get(crazyEight.getDiscardPile().getCardCollection().size() - 1).getRank());
						crazyEight.setCurrentSuit(crazyEight.getDiscardPile().getCardCollection()
								.get(crazyEight.getDiscardPile().getCardCollection().size() - 1).getSuit());
						if (crazyEight.getPlayerList().get(crazyEight.turn).getHand().getCardCollection().size() == 0) {
							crazyEight.setWinner(crazyEight.turn);
							System.out.println("The game is over, winner is "
									+ crazyEight.getPlayerList().get(crazyEight.getWinner()).toString() + "!");
							break;
						} else {
							Card playedCard = new Card(crazyEight.getCurrentSuit(), crazyEight.getCurrentRank());
							crazyEight.getPlayerList().get(crazyEight.turn).setPlayableCards(new CardCollection());
							if(crazyEight.getSelectedVariation() == 0) {
							crazyEight.activateEffect(playedCard);
							//crazyEight.playerList.get(crazyEight.turn).setPlayableCards(new CardCollection());
							}
							

						}
					}
				} else {
					System.out.println("----------------------------No more card----------------------------");
					crazyEight.getDrawPile().getCardCollection().addAll(crazyEight.getDiscardPile().getCardCollection());
					crazyEight.getDiscardPile().getCardCollection().clear();
					Card newCard = new Card();
					newCard = crazyEight.getDrawPile().getCardCollection().get(crazyEight.getDrawPile().getCardCollection().size() - 1);
					crazyEight.getDiscardPile().getCardCollection().add(newCard);
					crazyEight.getDrawPile().removeCard(crazyEight.getDrawPile().getCardCollection().size() - 1);
					crazyEight.turn--;
				}
				//				System.out.println(crazyEight.playerList);
				//				System.out.println(crazyEight.turn);

			}
			crazyEight.turn = 0;
		}

		// crazyEight.playerList.get(1).playGame(crazyEight.drawPile,
		// crazyEight.discardPile, crazyEight.currentRank, crazyEight.currentSuit);
		// System.out.println("-------------------------------DicardPile
		// -------------------------------");
		// crazyEight.discardPile.showCardCollection();

		// while((crazyEight.playerList.get(1).hand.cardCollection.size()!=0)&&(crazyEight.playerList.get(1).getPass()==false))
		// {
		//
		// // crazyEight.playerList.get(0).playGame(crazyEight.drawPile,
		// crazyEight.discardPile, crazyEight.currentRank, crazyEight.currentSuit);
		// crazyEight.playerList.get(1).playGame(crazyEight.drawPile,
		// crazyEight.discardPile, crazyEight.currentRank, crazyEight.currentSuit);
		// System.out.println("-------------------------------DicardPile
		// -------------------------------");
		// crazyEight.discardPile.showCardCollection();
		// System.out.println("-------------------------------DrawPile
		// -------------------------------");
		// crazyEight.drawPile.showCardCollection();
		// //Mettre a jour le currentSuit et currentRank
		// crazyEight.currentRank =
		// crazyEight.discardPile.cardCollection.get(crazyEight.discardPile.cardCollection.size()-1).getRank();
		// crazyEight.currentSuit =
		// crazyEight.discardPile.cardCollection.get(crazyEight.discardPile.cardCollection.size()-1).getSuit();
		// }
		// crazyEight.playerList.get(0).playGame(crazyEight.drawPile,
		// crazyEight.discardPile, crazyEight.currentRank, crazyEight.currentSuit);
		// System.out.println("-------------------------------DicardPile
		// -------------------------------");
		// crazyEight.discardPile.showCardCollection();
		// System.out.println("-------------------------------DrawPile
		// -------------------------------");
		// crazyEight.drawPile.showCardCollection();

	}

}
