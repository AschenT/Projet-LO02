package Jeux;
import java.util.*;
public class Player 
{
	private String name;
	private int playerID;
	protected static int playerQuantitie;
	protected CardCollection hand;
	
	public Player()
	{
		playerQuantitie++;
		this.playerID = this.playerQuantitie;
		System.out.println("Type your name and end with ;");
		Scanner sc = new Scanner(System.in);
		sc.useDelimiter(";");
		this.name = sc.next();
		
		this.hand = new CardCollection();
	}
	public Player (String ai)
	{
		playerQuantitie++;
		this.name = ai;
		this.playerID = this.playerQuantitie;
		this.hand = new CardCollection();
	}
	public void drawCard(CardCollection drawPile)
	{
		Card newCard = new Card();
		newCard = drawPile.cardCollection.get(1);
		this.hand.addCard(newCard);
		drawPile.removeCard(1);
	}
	
	public void discardCard(int index, CardCollection discardPile)
	{
		Card newCard = new Card();
		newCard = this.hand.cardCollection.get(index);
		discardPile.addCard(newCard);
		this.hand.removeCard(index);
	}
	
	public int getPlayerQuantitie()
	{
		return this.playerQuantitie;
	}
	
	public int getPlayerID()
	{
		return this.playerID;
	}
	
	public String toString()
	{
		return this.name;
	}
	//tester la focntionnement de classe Player
//	public static void main(String arg[])
//	{
//		Player humainPlayer = new Player();
//		Player AIPlayer = new Player();
//		System.out.println(humainPlayer.getPlayerID());
//		System.out.println(AIPlayer.getPlayerID());
//		//System.out.println(humainPlayer.getPlayerQuantitie());
//	}
}
