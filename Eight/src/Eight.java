package Jeux;
import java.util.*;
public class Eight 
{
	private int playerQuantitie;
	private CardCollection drawPile;
	private CardCollection discardPile;
	
	protected static int currentRank;
	protected static int currentSuit; 
	
	private List <Player> playerList;
	
	public Eight()
	{
		//initialisation des joueurs, enregistres dans playerList afin de mieux controler le jeux
		this.playerList = new <Player> ArrayList();
		Scanner sc = new Scanner(System.in);
		//Instancier les joueurs humains
		System.out.println("How many people play against computer? Tape a number terminate with ; and press enter");
		sc.useDelimiter(";");
		this.playerQuantitie = sc.nextInt();
		for(int i=0;i<this.playerQuantitie;i++)
		{
			Player newPlayer = new Player();
			this.playerList.add(newPlayer);
		}
		//instancier un joueur AI
		Player AIPlayer = new Player("AI");
		this.playerList.add(AIPlayer);
		
		this.drawPile = new CardCollection(); 
		this.discardPile = new CardCollection();
	}
	
	public void startGame()
	{
		//initialiser une nouvelle collection des 52 cartes et melanger 
		this.drawPile.newSet();
		this.drawPile.shuffle();
		
		//afficher drawPile
		System.out.println("-------------------------------DrawPile:-------------------------------");
		this.drawPile.showCardCollection();
		
		//distribution des cartes
	for(int id=0;id<this.playerQuantitie;id++)
		{
			  for(int nb_cards=0;nb_cards<8;nb_cards++)
			{
				Card newCard = new Card();
				//newCard = this.drawPile.cardCollection.get(nb_cards);//on distribue toujours la premiere carte de drawPile
				newCard = this.drawPile.cardCollection.get(0);
				this.playerList.get(id).hand.addCard(newCard);
				this.drawPile.removeCard(0);
			}
		}
		//drawPile apres distribution
		System.out.println("-------------------------------DrawPile after dealing:-------------------------------");
		this.drawPile.showCardCollection();
		
		//mettre la premiere carte du drawPile dans le discardPile 
		Card firstCard = new Card();
		firstCard = this.drawPile.cardCollection.get(0);
		this.drawPile.cardCollection.remove(0);
		this.discardPile.cardCollection.add(firstCard);
		this.currentRank = firstCard.getRank();
		this.currentSuit = firstCard.getSuit();
	}
	
	public static void main(String arg[])
	{
		Eight crazyEight = new Eight();
		crazyEight.startGame();
		System.out.println("-------------------------------wenbin:-------------------------------");
		crazyEight.playerList.get(0).hand.showCardCollection();
		System.out.println("-------------------------------AI:-------------------------------");
		crazyEight.playerList.get(1).hand.showCardCollection();
		System.out.println(crazyEight.playerQuantitie);
	}
}
