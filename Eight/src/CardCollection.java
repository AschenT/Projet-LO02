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
			System.out.println(this.cardCollection.get(i).toString());
			
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
	
//	public static void main(String arg[])
//	{
//		CardCollection newCardCollection = new CardCollection();
//		newCardCollection.newSet();
//		newCardCollection.shuffle();
////		newCardCollection.showCardCollection();
//		
//	}
}

