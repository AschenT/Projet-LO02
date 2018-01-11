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
	private int difficulty;
	public AIPlayer()
	{
		super("ai");
		
	}
	
	public AIPlayer(Difficulty difficulty)
	{
		super("ai");
		difficulty = new Easy(); //TODO choix de la difficulté
		
	}
	
	public void playGame(CardCollection drawPile, CardCollection discardPile, int currentRank, int currentSuit)
	{
		//difficulty.playGame();
		int n=1;
		int cardDrawn=0;
		int indexDiscardCard = -1;
		System.out.println("----------------------------Here's ai's hand cards----------------------------");
		this.hand.showCardCollection();
		System.out.println("Current Rank:");
		System.out.println(GlobalInformation.ranks[currentRank]);
		System.out.println("Current Suit:");
		System.out.println(GlobalInformation.suits[currentSuit]);
		
		//afficher toutes les cartes jouables s'ils existent et leurs numeros correspondants dans le Handcard sinon piocher une carte
		System.out.println("----------------------------AI's turn----------------------------");
		while((getPlayableCards().getCardCollection().size()==0)&&(n>0))
		{
			for(int i=0;i<this.hand.getCardCollection().size();i++)
			{
				//Afficher les cartes jouables
				if((this.hand.getCardCollection().get(i).getRank()==currentRank)||(this.hand.getCardCollection().get(i).getSuit()==currentSuit))
				{
					getPlayableCards().addCard(this.hand.getCardCollection().get(i));
					System.out.println(i+"--->"+this.hand.getCardCollection().get(i).toString());
					indexDiscardCard = i;
				}
			}
			//S'il y a pas de carte jouables, il faut piocher une carte 
			if((getPlayableCards().getCardCollection().size()==0)&&(n>0))
			{
				System.out.println("----------------------------AI don't have any playable cards, AI drawed one card----------------------------");
				Card newCard = new Card();
				//piocher la premiere carte dans le drawPile
				newCard = drawPile.getCardCollection().get(0);
				this.hand.addCard(newCard);
				drawPile.removeCard(0);
				System.out.println(newCard.toString());
				cardDrawn=1;
			}
			n--;
		}
		
		//Si le joueur n'a pas de carte jouables apres 3 fois de piocher,
		if(getPlayableCards().getCardCollection().size()==0)
		{
			//System.out.println("----------------------------AI still don't have any playable cards----------------------------");
			System.out.println("----------------------------Pass----------------------------");
			this.pass = true;
		}
		else 
		{		
			
			//verifier la carte choisie par le joueur AI est compatible et jeter la carte si possible
			if((this.hand.getCardCollection().get(indexDiscardCard).getRank()==currentRank)||(this.hand.getCardCollection().get(indexDiscardCard).getSuit()==currentSuit))
			{
				discardPile.addCard(this.hand.getCardCollection().get(indexDiscardCard));
//				currentRank = this.hand.cardCollection.get(indexDiscardCard).getRank();
//				currentSuit = this.hand.cardCollection.get(indexDiscardCard).getSuit();
				System.out.println("AI discarded card:"+this.hand.getCardCollection().get(indexDiscardCard).toString());
				this.hand.getCardCollection().remove(this.hand.getCardCollection().get(indexDiscardCard));
				
			}
			
		}
	}
	
}
