package game;
import java.util.*;

import card.Card;
import card.CardCollection;
import player.Player;
public class Variation {
	private int variation;
	// Active l'effet de la carte jouée.
	public void activateEffect(Eight crazyEight, Card playedCard, CardCollection drawPile,CardCollection discardPile, List <Player> playerList, int turn) {
		switch (playedCard.getRank())
		{
		  case 1:
			  for(int i=0; i<4; i++) 
			  {
				Card newCard = new Card();	  
			 	newCard = drawPile.getCardCollection().get(0);
			 	playerList.get(turn+1).getHand().addCard(newCard);
			 	drawPile.removeCard(0);
			  }
		    break;  
		  case 11:
			List<Player> temporaryPlayerList = playerList;
			int k;
			for(int i=temporaryPlayerList.size()-1; i>=0; i--) 
			{
					k = Math.abs(i-playerList.size());
				    playerList.set(k, temporaryPlayerList.get(i));
			}
			    break;
		  case 10:
			  playerList.get(turn).playGame(drawPile, discardPile, 10, playedCard.getSuit());
			    break;
		  case 8:
			  System.out.println("Type the number matching the suit you want");
			  System.out.println("1---------->clubs");
			  System.out.println("2---------->spades");
			  System.out.println("3---------->diamonds");
			  System.out.println("4---------->hearts");
				Scanner sc = new Scanner(System.in);
				Eight.setCurrentSuit(sc.nextInt());
			    break;
		  case 7:
			  crazyEight.skipNextPlayerTurn();
			    break;
		  case 5:
			  System.out.println("Type the number matching the player you want to draw a card");
			  for(int i=0; i<playerList.size(); i++) {
				  System.out.println(i+"---------->"+playerList.get(i).getName());
			  }
			  Scanner sc1 = new Scanner(System.in);
			  
			  Card newCard = new Card();	  
			  newCard = drawPile.getCardCollection().get(0);
			  playerList.get(sc1.nextInt()).getHand().addCard(newCard);
			  drawPile.removeCard(0);
			    break;
		  default:
		    break;             
		}
	}

}
