package card;
import java.util.*;
public class Card 							  
{
	private int rank;
	private int suit;
	//0 pour le premier element
	private static String[] suits = {null,"clubs","spades","diamonds","hearts"};
	private static String[] ranks = {null,"ace","2","3","4","5","6","7","8","9","10","jack","queen","king"};
	
	public Card(int suit, int rank)
	{
        this.rank = rank;
        this.suit = suit;
   }
    
	public Card()
	{
		
	}
    public String toString()
   {
    return suits[suit] + "  "+ ranks[rank] ;
   }
    public int getRank()
    {
    		return this.rank;
    }
    public int getSuit()
    {
    		return this.suit;
    }
//fonction main pour afficher une carte    
//    public static void main(String arg[])
//    {
//    		Card testCard = new Card(0,1);
//    		System.out.println(testCard);
//    }

}

