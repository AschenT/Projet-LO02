package strategy;

import card.Card;
import card.CardCollection;
import game.Eight;
import game.GlobalInformation;
import player.AIPlayer;

public class Easy implements Difficulty {

	@Override
	public void playGame(AIPlayer ai, CardCollection drawPile, CardCollection discardPile, int currentRank, int currentSuit) {
		int n=1;
		int cardDrawn=0;
		int indexDiscardCard = -1;
		System.out.println("----------------------------Here's ai's hand cards----------------------------");
		ai.getHand().showCardCollection();
		System.out.println("Current Rank:");
		System.out.println(GlobalInformation.ranks[currentRank]);
		System.out.println("Current Suit:");
		System.out.println(GlobalInformation.suits[currentSuit]);
		
		//afficher toutes les cartes jouables s'ils existent et leurs numeros correspondants dans le Handcard sinon piocher une carte
		System.out.println("----------------------------AI's turn----------------------------");
		while((ai.getPlayableCards().getCardCollection().size()==0)&&(n>0))
		{
			for(int i=0;i<ai.getHand().getCardCollection().size();i++)
			{
				//Afficher les cartes jouables
				if((ai.getHand().getCardCollection().get(i).getRank()==currentRank)||(ai.getHand().getCardCollection().get(i).getSuit()==currentSuit))
				{
					ai.getPlayableCards().addCard(ai.getHand().getCardCollection().get(i));
					System.out.println(i+"--->"+ai.getHand().getCardCollection().get(i).toString());
					indexDiscardCard = i;
				}
			}
			//S'il y a pas de carte jouables, il faut piocher une carte 
			if((ai.getPlayableCards().getCardCollection().size()==0)&&(n>0))
			{
				System.out.println("----------------------------AI don't have any playable cards, AI drawed one card----------------------------");
				Card newCard = new Card();
				//piocher la premiere carte dans le drawPile
				newCard = drawPile.getCardCollection().get(0);
				ai.getHand().addCard(newCard);
				drawPile.removeCard(0);
				System.out.println(newCard.toString());
				cardDrawn=1;
			}
			n--;
		}
		
		//Si le joueur n'a pas de carte jouables apres 3 fois de piocher,
		if(ai.getPlayableCards().getCardCollection().size()==0)
		{
			//System.out.println("----------------------------AI still don't have any playable cards----------------------------");
			System.out.println("----------------------------Pass----------------------------");
			ai.setPass(true);
		}
		else 
		{		
			
			//verifier la carte choisie par le joueur AI est compatible et jeter la carte si possible
			if((ai.getHand().getCardCollection().get(indexDiscardCard).getRank()==currentRank)||(ai.getHand().getCardCollection().get(indexDiscardCard).getSuit()==currentSuit))
			{
				discardPile.addCard(ai.getHand().getCardCollection().get(indexDiscardCard));
//				currentRank = this.hand.cardCollection.get(indexDiscardCard).getRank();
//				currentSuit = this.hand.cardCollection.get(indexDiscardCard).getSuit();
				System.out.println("AI discarded card:"+ai.getHand().getCardCollection().get(indexDiscardCard).toString());
				ai.getHand().getCardCollection().remove(ai.getHand().getCardCollection().get(indexDiscardCard));
				
			}
			
		}
		
	}

}
