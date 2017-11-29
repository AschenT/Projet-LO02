package Jeux;
import java.util.*;
public class Eight 
{
	private int playerQuantitie;
	private CardCollection drawPile;
	private CardCollection discardPile;

	private static int currentRank;
	private static int currentSuit;
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
			HumainPlayer newHumainPlayer = new HumainPlayer();
			this.playerList.add(newHumainPlayer);
		}
		//instancier un joueur AI
		AIPlayer aiPlayer = new AIPlayer();	
		this.playerList.add(aiPlayer);
		
		
		this.drawPile = new CardCollection(); 
		this.discardPile = new CardCollection();
		
		
	}
	/*
	 * Le debut du jeux. L'arbitre initialise les cartes, distribuer les cartes
	 */
	public void startGame()
	{
		//initialiser une nouvelle collection des 52 cartes et melanger 
		this.drawPile.newSet();
		this.drawPile.shuffle();
		
		//afficher drawPile
//		System.out.println("-------------------------------DrawPile:-------------------------------");
//		this.drawPile.showCardCollection();
		
		//distribution des cartes
	for(int id=0;id<this.playerQuantitie+1;id++)
		{
			  for(int nb_cards=0;nb_cards<8;nb_cards++)////////////////////////////////////////////////////////////////////////////////////////////////////////
			{
				Card newCard = new Card();
				//newCard = this.drawPile.cardCollection.get(nb_cards);//on distribue toujours la premiere carte de drawPile
				newCard = this.drawPile.cardCollection.get(0);
				this.playerList.get(id).hand.addCard(newCard);
				this.drawPile.removeCard(0);
			}
		}
		//drawPile apres distribution
//		System.out.println("-------------------------------DrawPile after dealing:-------------------------------");
//		this.drawPile.showCardCollection();
		
		//mettre la premiere carte du drawPile dans le discardPile qui vient d'etre jetee
		Card firstCard = new Card();
		firstCard = this.drawPile.cardCollection.get(0);
		this.drawPile.cardCollection.remove(0);
		this.discardPile.cardCollection.add(firstCard);
		this.currentRank = firstCard.getRank();
		this.currentSuit = firstCard.getSuit();
	}
	
	/*
	 * Afficher tous les joueurs et leurs cartes y compris AI
	 */
	public void showPlayersCards()
	{
		for(int i=0;i<this.playerQuantitie+1;i++)
		{
			System.out.println("----------------------------"+this.playerList.get(i).toString()+"----------------------------");
			this.playerList.get(i).hand.showCardCollection();
		}
		
	}
	
	public static void main(String arg[])
	{
		Eight crazyEight = new Eight();
		crazyEight.startGame();
		crazyEight.showPlayersCards();
		int turn = 0;
		int winner = -1;
		
		while(winner==-1)
		{
			for(turn=0;turn<crazyEight.playerList.size();turn++)
			{
				if(crazyEight.drawPile.cardCollection.size()>=5)
				{
					crazyEight.playerList.get(turn).playGame(crazyEight.drawPile, crazyEight.discardPile, crazyEight.currentRank, crazyEight.currentSuit);
					
					crazyEight.currentRank = crazyEight.discardPile.cardCollection.get(crazyEight.discardPile.cardCollection.size()-1).getRank();
					crazyEight.currentSuit = crazyEight.discardPile.cardCollection.get(crazyEight.discardPile.cardCollection.size()-1).getSuit();
					if(crazyEight.playerList.get(turn).hand.cardCollection.size()==0)
					{
						winner = turn;
						System.out.println("The game is over, winner is "+crazyEight.playerList.get(winner).toString()+"!");
						break;
					}
				}
				else 
				{
					System.out.println("----------------------------No more card----------------------------");
					crazyEight.drawPile.cardCollection.addAll(crazyEight.discardPile.cardCollection);
					crazyEight.discardPile.cardCollection.clear();
					Card newCard = new Card();
					newCard = crazyEight.drawPile.cardCollection.get(crazyEight.drawPile.cardCollection.size()-1);
					crazyEight.discardPile.cardCollection.add(newCard);
					crazyEight.drawPile.removeCard(crazyEight.drawPile.cardCollection.size()-1);
					turn--;
				}
				
			}
			turn = 0;
		}
		
		
		
		
		
//		crazyEight.playerList.get(1).playGame(crazyEight.drawPile, crazyEight.discardPile, crazyEight.currentRank, crazyEight.currentSuit);
//		System.out.println("-------------------------------DicardPile -------------------------------");
//		crazyEight.discardPile.showCardCollection();
		
		
//		while((crazyEight.playerList.get(1).hand.cardCollection.size()!=0)&&(crazyEight.playerList.get(1).getPass()==false))
//		{
//			
//		//	crazyEight.playerList.get(0).playGame(crazyEight.drawPile, crazyEight.discardPile, crazyEight.currentRank, crazyEight.currentSuit);
//			crazyEight.playerList.get(1).playGame(crazyEight.drawPile, crazyEight.discardPile, crazyEight.currentRank, crazyEight.currentSuit);
//			System.out.println("-------------------------------DicardPile -------------------------------");
//			crazyEight.discardPile.showCardCollection();
//			System.out.println("-------------------------------DrawPile -------------------------------");
//			crazyEight.drawPile.showCardCollection();
//			//Mettre a jour le currentSuit et currentRank
//			crazyEight.currentRank = crazyEight.discardPile.cardCollection.get(crazyEight.discardPile.cardCollection.size()-1).getRank();
//			crazyEight.currentSuit = crazyEight.discardPile.cardCollection.get(crazyEight.discardPile.cardCollection.size()-1).getSuit();
//		}
//		crazyEight.playerList.get(0).playGame(crazyEight.drawPile, crazyEight.discardPile, crazyEight.currentRank, crazyEight.currentSuit);
//		System.out.println("-------------------------------DicardPile -------------------------------");
//		crazyEight.discardPile.showCardCollection();
//		System.out.println("-------------------------------DrawPile -------------------------------");
//		crazyEight.drawPile.showCardCollection();
		
	}
}
