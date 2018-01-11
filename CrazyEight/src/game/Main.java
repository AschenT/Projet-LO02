package game;

import Controleur.MenuControleur;
import Vue.Interface;
import card.Card;
import card.CardCollection;

public class Main {
	public static void main(String arg[]) {
		MenuControleur controleur = new MenuControleur();

		//Eight crazyEight = new Eight();

		//view.crazyEight.startGame();
		// crazyEight.showPlayersCards();
		//view.crazyEight.setTurn(0);
		//view.crazyEight.setWinner(-1);

		/*while (view.crazyEight.getWinner() == -1) {
			for (view.crazyEight.setTurn(0); view.crazyEight.getTurn() < view.crazyEight.getPlayerList().size(); view.crazyEight.turn++) {
				if (view.crazyEight.getDrawPile().getCardCollection().size() >= 5) {
					view.crazyEight.getPlayerList().get(view.crazyEight.turn).playGame(view.crazyEight.getDrawPile(), view.crazyEight.getDiscardPile(),
							view.crazyEight.getCurrentRank(), view.crazyEight.getCurrentSuit());
					
					if (view.crazyEight.getPlayerList().get(view.crazyEight.turn).getPlayableCards().getCardCollection().size()!=0) {
						view.crazyEight.setCurrentRank(view.crazyEight.getDiscardPile().getCardCollection()
								.get(view.crazyEight.getDiscardPile().getCardCollection().size() - 1).getRank());
						view.crazyEight.setCurrentSuit(view.crazyEight.getDiscardPile().getCardCollection()
								.get(view.crazyEight.getDiscardPile().getCardCollection().size() - 1).getSuit());
						if (view.crazyEight.getPlayerList().get(view.crazyEight.turn).getHand().getCardCollection().size() == 0) {
							view.crazyEight.setWinner(view.crazyEight.turn);
							System.out.println("The game is over, winner is "
									+ view.crazyEight.getPlayerList().get(view.crazyEight.getWinner()).toString() + "!");
							break;
						} else {
							Card playedCard = new Card(view.crazyEight.getCurrentSuit(), view.crazyEight.getCurrentRank());
							view.crazyEight.getPlayerList().get(view.crazyEight.turn).setPlayableCards(new CardCollection());
							if(view.crazyEight.getSelectedVariation() == 0) {
								view.crazyEight.activateEffect(playedCard);
							//crazyEight.playerList.get(crazyEight.turn).setPlayableCards(new CardCollection());
							}
							

						}
					}
				} else {
					System.out.println("----------------------------No more card----------------------------");
					view.crazyEight.getDrawPile().getCardCollection().addAll(view.crazyEight.getDiscardPile().getCardCollection());
					view.crazyEight.getDiscardPile().getCardCollection().clear();
					Card newCard = new Card();
					newCard = view.crazyEight.getDrawPile().getCardCollection().get(view.crazyEight.getDrawPile().getCardCollection().size() - 1);
					view.crazyEight.getDiscardPile().getCardCollection().add(newCard);
					view.crazyEight.getDrawPile().removeCard(view.crazyEight.getDrawPile().getCardCollection().size() - 1);
					view.crazyEight.turn--;
				}
				//				System.out.println(crazyEight.playerList);
				//				System.out.println(crazyEight.turn);

			}
			view.crazyEight.turn = 0;
		}*/

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
