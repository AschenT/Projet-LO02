package player;
import java.util.*;

import card.Card;
import card.CardCollection;
import game.GlobalInformation;
public class Player implements GlobalInformation
{
	protected String name;
	private int playerID;
	private boolean pass = false;
	protected static int playerQuantitie;
	protected static int computerQuantitie;
	protected CardCollection hand;
	protected CardCollection playableCards;
	

	public Player()
	{
		playerQuantitie++;
		this.playerID = this.playerQuantitie;
		System.out.println("Type your name"/* and end with ;"*/);
		Scanner sc = new Scanner(System.in);
		//		sc.useDelimiter(";");
		this.name = sc.next();

		this.hand = new CardCollection();
		this.playableCards = new CardCollection();
	}
	public Player (String ai)
	{
		playerQuantitie++;
		this.name = ai;
		this.playerID = this.playerQuantitie+this.computerQuantitie;
		this.hand = new CardCollection();
		this.playableCards =new CardCollection();
	}
	public void drawCard(CardCollection drawPile)
	{
		Card newCard = new Card();
		newCard = drawPile.getCardCollection().get(1);
		this.hand.addCard(newCard);
		drawPile.removeCard(1);
	}

	public void discardCard(int index, CardCollection discardPile)
	{
		Card newCard = new Card();
		newCard = getHand().getCardCollection().get(index);
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
	public void playGame(CardCollection drawPile, CardCollection discardPile, int currentRank, int currentSuit)
	{

	}
	public String toString()
	{
		return this.name;
	}
	
	public CardCollection getHand() {
		return hand;
	}

	public boolean getPass()
	{
		return this.pass;
	}

	public void ackPass()
	{
		this.pass = false;
	}
	
	public String getName() {
		return name;
	}
	
	public CardCollection getPlayableCards() {
		return playableCards;
	}
	public void setPlayableCards(CardCollection playableCards) {
		this.playableCards = playableCards;
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
