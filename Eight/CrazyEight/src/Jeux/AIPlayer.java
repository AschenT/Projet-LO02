package Jeux;

import java.util.Scanner;

public class AIPlayer extends Player
{
	private boolean pass = false;
	private String name;
	private int playerID;
	public AIPlayer()
	{
		super("ai");
	}
	
	
	
	
	public void playGame(CardCollection drawPile, CardCollection discardPile, int currentRank, int currentSuit)
	{
		CardCollection playableCards = new CardCollection();
		
		int n=4;
		int cardDrawn=0;
		int indexDiscardCard = -1;
		
		//afficher toutes les cartes jouables s'ils existent et leurs numeros correspondants dans le Handcard sinon piocher une carte
		System.out.println("----------------------------AI's turn----------------------------");
		while((playableCards.cardCollection.size()==0)&&(n>0))
		{
			for(int i=0;i<this.hand.cardCollection.size();i++)
			{
				//Afficher les cartes jouables
				if((this.hand.cardCollection.get(i).getRank()==currentRank)||(this.hand.cardCollection.get(i).getSuit()==currentSuit))
				{
					playableCards.addCard(this.hand.cardCollection.get(i));
					//System.out.println(i+"--->"+this.hand.cardCollection.get(i).toString());
					indexDiscardCard = i;
				}
			}
			//S'il y a pas de carte jouables, il faut piocher une carte 
			if((playableCards.cardCollection.size()==0)&&(n>1))
			{
				System.out.println("----------------------------AI don't have any playable cards, AI drawed one card----------------------------");
				Card newCard = new Card();
				//piocher la premiere carte dans le drawPile
				newCard = drawPile.cardCollection.get(0);
				this.hand.addCard(newCard);
				drawPile.removeCard(0);
				//System.out.println(newCard.toString());
				cardDrawn=1;
			}
			n--;
		}
		
		//Si le joueur n'a pas de carte jouables apres 3 fois de piocher,
		if(playableCards.cardCollection.size()==0)
		{
			System.out.println("----------------------------AI still don't have any playable cards----------------------------");
			System.out.println("----------------------------Pass----------------------------");
			this.pass = true;
		}
		else 
		{		
			
			//verifier la carte choisie par le joueur AI est compatible et jeter la carte si possible
			if((this.hand.cardCollection.get(indexDiscardCard).getRank()==currentRank)||(this.hand.cardCollection.get(indexDiscardCard).getSuit()==currentSuit))
			{
				Card cardToDiscard = new Card();
				cardToDiscard = this.hand.cardCollection.get(indexDiscardCard);
				discardPile.addCard(cardToDiscard);
//				currentRank = this.hand.cardCollection.get(indexDiscardCard).getRank();
//				currentSuit = this.hand.cardCollection.get(indexDiscardCard).getSuit();
				System.out.println("AI discarded card:"+this.hand.cardCollection.get(indexDiscardCard).toString());
				this.hand.cardCollection.remove(indexDiscardCard);
				
			}
			
		}
	}
	
}
