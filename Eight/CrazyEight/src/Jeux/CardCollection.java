package Jeux;
import java.util.*;
public class CardCollection 
{
	protected ArrayList <Card> cardCollection;
	
	public CardCollection()
	{
		this.cardCollection = new <Card> ArrayList();	
	}
	public void newSet()
	{
		for(int rank=1;rank<14;rank++)
		{
			for(int suit=1;suit<5;suit++)
			{
				Card newCard = new Card(suit,rank);
//				System.out.println(newCard);
				this.cardCollection.add(newCard);
			}
		}
	}

	/*-------------------melanger les cartes--------------------*/
	public void shuffle()
	{
		Collections.shuffle(this.cardCollection);	
	}
	/*-------------------montrer toutes les cartes dans cette collection--------------------*/
	public void showCardCollection() 
	{	
		
		for(int i=0;i<this.cardCollection.size();i++)
		{
			System.out.println(i+"--->"+this.cardCollection.get(i).toString());
			
		}		
	}
	
	public void addCard(Card newCard)
	{
		this.cardCollection.add(newCard);
	}
	
	public void removeCard(int index)
	{
		this.cardCollection.remove(index);
	}
	
	
	public int findSuitLike(int suit)
	{
		int firstCardFound = -1;
		
		for(int i=0;i<this.cardCollection.size();i++)
		{
			if((this.cardCollection.get(i).getSuit()==suit)&&(firstCardFound == -1))
			{
				firstCardFound = i;
			}
		}
		
		
		return firstCardFound;
	}
	public int findRankLike(int rank)
	{
		int firstCardFound = -1;
		
		for(int i=0;i<this.cardCollection.size();i++)
		{
			if((this.cardCollection.get(i).getRank()==rank)&&(firstCardFound == -1))
			{
				firstCardFound = i;
			}
		}
		
		return firstCardFound;

	}
	
//	public static void main(String arg[])
//	{
//		CardCollection newCardCollection = new CardCollection();
//		CardCollection newCardCollection2 = new CardCollection();
//		
//		newCardCollection.newSet();
//		newCardCollection.shuffle();
//		System.out.println("first");
//		newCardCollection.showCardCollection();
//		newCardCollection2.cardCollection.addAll(newCardCollection.cardCollection);
//		System.out.println("seconde");
//		newCardCollection2.showCardCollection();
////		int firstCard = -1;
////		firstCard = newCardCollection.findRankLike(3);
////		System.out.println(firstCard);
////		int rank;
////		int suit;
////		suit = newCardCollection.cardCollection.get(0).getSuit();
////		rank = newCardCollection.cardCollection.get(0).getRank();
////		System.out.println(rank);
////		System.out.println(suit);
//		
//	}
}

