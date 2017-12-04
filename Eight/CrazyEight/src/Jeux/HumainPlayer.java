package Jeux;
import java.util.*;
public class HumainPlayer extends Player 
{
	private boolean pass = false;
	public HumainPlayer()
	{
		super();
	}
	
	public void playGame(CardCollection drawPile, CardCollection discardPile, int currentRank, int currentSuit)
	{
		int indexDiscardCard;
		System.out.println("----------------------------Here's "+this.name+"'s hand cards----------------------------");
		this.hand.showCardCollection();
		System.out.println("Current Rank:");
		System.out.println(GlobalInformation.ranks[currentRank]);
		System.out.println("Current Suit:");
		System.out.println(GlobalInformation.suits[currentSuit]);
		
		//afficher toutes les cartes jouables s'ils existent et leurs numeros correspondants dans le Handcard sinon piocher une carte
		System.out.println("----------------------------Here are "+this.name+"'s playable cards----------------------------");
		CardCollection playableCards = new CardCollection();
		
		int n=1;
		int cardDrawn=0;
		while((playableCards.cardCollection.size()==0)&&(n>0))
		{
			for(int i=0;i<this.hand.cardCollection.size();i++)
			{
				//Afficher les cartes jouables
				if(((this.hand.cardCollection.get(i).getRank()==currentRank)||(this.hand.cardCollection.get(i).getSuit()==currentSuit))&&(playableCards.cardCollection.contains(this.hand.cardCollection.get(i))==false))
				{
					playableCards.addCard(this.hand.cardCollection.get(i));
					System.out.println(i+"--->"+this.hand.cardCollection.get(i).toString());
				}
			}
			//S'il y a pas de carte jouables, il faut piocher une carte 
			if(playableCards.cardCollection.size()==0)
			{
				System.out.println("Your don't have any playable cards, you drawed one card");
				Card newCard = new Card();
				//piocher la premiere carte dans le drawPile
				newCard = drawPile.cardCollection.get(0);
				this.hand.addCard(newCard);
				drawPile.removeCard(0);
				System.out.println(newCard.toString());
				cardDrawn=1;
			}
			n--;
		}
		
		//Si le joueur n'a pas de carte jouables apres 3 fois de piocher,
		if(playableCards.cardCollection.size()==0)
		{
			//System.out.println("You still don't have any playable cards");
			System.out.println("Pass");
			this.pass = true;
		}
		else 
		{		
			System.out.println("Type the number of the card that you want to discard in handcard list"/*, end by;"*/);
			Scanner sc =new Scanner(System.in);
//			sc.useDelimiter(";");
			indexDiscardCard = sc.nextInt();
			//verifier la carte choisie par le joueur humain est compatible et jeter la carte si possible
			if((this.hand.cardCollection.get(indexDiscardCard).getRank()==currentRank)||(this.hand.cardCollection.get(indexDiscardCard).getSuit()==currentSuit))
			{
				Card cardToDiscard = new Card();
				cardToDiscard = this.hand.cardCollection.get(indexDiscardCard);
				discardPile.addCard(cardToDiscard);
//				currentRank = this.hand.cardCollection.get(indexDiscardCard).getRank();
//				currentSuit = this.hand.cardCollection.get(indexDiscardCard).getSuit();
				this.hand.cardCollection.remove(indexDiscardCard);
				
			}
			else
			{
				System.out.println("You can't discard this card!");
			}
		}
	}
	
}
