package game;

import java.util.*;

import card.Card;
import card.CardCollection;
import player.*;

public class Eight implements GlobalInformation {
	private int playerQuantitie;
	private int computerQuantitie;
	private CardCollection drawPile;
	private CardCollection discardPile;

	private static int currentRank;
	private static int currentSuit;
	private List<Player> playerList;
	private int turn;
	private int winner;
	private static int selectedVariation;

	public Eight() {
		// initialisation des joueurs, enregistres dans playerList afin de mieux
		// controler le jeux
		this.playerList = new <Player>ArrayList();
		selectedVariation = choixVariante();


		//TODO limiter 4 joueurs
		this.playerQuantitie = saisirNombreJoueur();

		for (int i = 0; i < this.playerQuantitie; i++) {
			HumainPlayer newHumainPlayer = new HumainPlayer();
			this.playerList.add(newHumainPlayer);
		}
		
		// instancier un joueur AI
		this.computerQuantitie = saisirNombreOrdi();

		for (int i = 0; i < this.computerQuantitie; i++) {
			AIPlayer newaiPlayer = new AIPlayer();
			this.playerList.add(newaiPlayer);
		}


		this.drawPile = new CardCollection();
		this.discardPile = new CardCollection();

	}
	
	public int choixVariante() {
		System.out.println("Quelle variante souhaitez-vous?");
		System.out.println("0------>Avec Effet");
		System.out.println("1------>Sans Effet");
		Scanner sc = new Scanner(System.in);
		int variante = sc.nextInt();
		return variante;
	}

	public int saisirNombreJoueur() {
		Scanner sc = new Scanner(System.in);
		// Instancier les joueurs humains
		System.out.println("How many people play against computer?");
		int nombre = 0;
		try {
			nombre = sc.nextInt();
		}
		
		catch(InputMismatchException exception) {
			System.out.println("Vous devez rentrez un nombre");
			saisirNombreJoueur();
		}
		
		return nombre;
	}
	
	public int saisirNombreOrdi() {
		Scanner sc = new Scanner(System.in);
		// Instancier les Ordi
		System.out.println("How many computers?");
		int nombre = 0;
		try {
			nombre = sc.nextInt();
		}
		
		catch(InputMismatchException exception) {
			System.out.println("Vous devez rentrez un nombre");
			saisirNombreOrdi();
		}
		
		return nombre;
	}

		/*
		 * Le debut du jeux. L'arbitre initialise les cartes, distribuer les cartes
		 */
		public void startGame() {
			// initialiser une nouvelle collection des 52 cartes et melanger
			this.drawPile.newSet();
			this.drawPile.shuffle();

			// afficher drawPile
			// System.out.println("-------------------------------DrawPile:-------------------------------");
			// this.drawPile.showCardCollection();

			// distribution des cartes
			for (int id = 0; id < this.playerQuantitie; id++) {
				for (int nb_cards = 0; nb_cards < 8; nb_cards++)////////////////////////////////////////////////////////////////////////////////////////////////////////
				{
					Card newCard = new Card();
					// newCard = this.drawPile.cardCollection.get(nb_cards);//on distribue toujours
					// la premiere carte de drawPile
					newCard = this.drawPile.getCardCollection().get(0);
					this.playerList.get(id).getHand().addCard(newCard);
					this.drawPile.removeCard(0);
				}
			}
			for (int id = this.playerQuantitie; id < this.playerQuantitie + this.computerQuantitie; id++) {
				for (int nb_cards = 0; nb_cards < 8; nb_cards++)////////////////////////////////////////////////////////////////////////////////////////////////////////
				{
					Card newCard = new Card();
					// newCard = this.drawPile.cardCollection.get(nb_cards);//on distribue toujours
					// la premiere carte de drawPile
					newCard = this.drawPile.getCardCollection().get(0);
					this.playerList.get(id).getHand().addCard(newCard);
					this.drawPile.removeCard(0);
				}
			}
			// drawPile apres distribution
			// System.out.println("-------------------------------DrawPile after
			// dealing:-------------------------------");
			// this.drawPile.showCardCollection();

			// mettre la premiere carte du drawPile dans le discardPile qui vient d'etre
			// jetee
			Card firstCard = new Card();
			firstCard = this.drawPile.getCardCollection().get(0);
			this.drawPile.getCardCollection().remove(0);
			this.discardPile.getCardCollection().add(firstCard);
			this.currentRank = firstCard.getRank();
			this.setCurrentSuit(firstCard.getSuit());
		}

		/*
		 * Saute le tour du prochain joueur
		 */
		public void skipNextPlayerTurn() {
			this.turn++;
		}

		/*
		 * Afficher tous les joueurs et leurs cartes y compris AI
		 */
		public void showPlayersCards() {
			for (int i = 0; i < this.playerQuantitie + 1; i++) {
				System.out.println("----------------------------" + this.playerList.get(i).toString()
						+ "----------------------------");
				this.playerList.get(i).getHand().showCardCollection();
			}

		}

		public static void main(String arg[]) {
			Eight crazyEight = new Eight();
			crazyEight.startGame();
			// crazyEight.showPlayersCards();
			crazyEight.turn = 0;
			crazyEight.winner = -1;
			int p;

			while (crazyEight.winner == -1) {
				for (crazyEight.turn = 0; crazyEight.turn < crazyEight.playerList.size(); crazyEight.turn++) {
					if (crazyEight.drawPile.getCardCollection().size() >= 5) {
						crazyEight.playerList.get(crazyEight.turn).playGame(crazyEight.drawPile, crazyEight.discardPile,
								crazyEight.currentRank, crazyEight.getCurrentSuit());
						
						if (crazyEight.playerList.get(crazyEight.turn).getPlayableCards().getCardCollection().size()!=0) {
							crazyEight.currentRank = crazyEight.discardPile.getCardCollection()
									.get(crazyEight.discardPile.getCardCollection().size() - 1).getRank();
							crazyEight.setCurrentSuit(crazyEight.discardPile.getCardCollection()
									.get(crazyEight.discardPile.getCardCollection().size() - 1).getSuit());
							if (crazyEight.playerList.get(crazyEight.turn).getHand().getCardCollection().size() == 0) {
								crazyEight.winner = crazyEight.turn;
								System.out.println("The game is over, winner is "
										+ crazyEight.playerList.get(crazyEight.winner).toString() + "!");
								break;
							} else {
								Card playedCard = new Card(getCurrentSuit(), currentRank);
								crazyEight.playerList.get(crazyEight.turn).setPlayableCards(new CardCollection());
								if(selectedVariation == 0) {
								crazyEight.activateEffect(playedCard);
								//crazyEight.playerList.get(crazyEight.turn).setPlayableCards(new CardCollection());
								}
								

							}
						}
					} else {
						System.out.println("----------------------------No more card----------------------------");
						crazyEight.drawPile.getCardCollection().addAll(crazyEight.discardPile.getCardCollection());
						crazyEight.discardPile.getCardCollection().clear();
						Card newCard = new Card();
						newCard = crazyEight.drawPile.getCardCollection().get(crazyEight.drawPile.getCardCollection().size() - 1);
						crazyEight.discardPile.getCardCollection().add(newCard);
						crazyEight.drawPile.removeCard(crazyEight.drawPile.getCardCollection().size() - 1);
						crazyEight.turn--;
					}
					//				System.out.println(crazyEight.playerList);
					//				System.out.println(crazyEight.turn);

				}
				crazyEight.turn = 0;
			}

			// crazyEight.playerList.get(1).playGame(crazyEight.drawPile,
			// crazyEight.discardPile, crazyEight.currentRank, crazyEight.currentSuit);
			// System.out.println("-------------------------------DicardPile
			// -------------------------------");
			// crazyEight.discardPile.showCardCollection();

			// while((crazyEight.playerList.get(1).hand.cardCollection.size()!=0)&&(crazyEight.playerList.get(1).getPass()==false))
			// {
			//
			// // crazyEight.playerList.get(0).playGame(crazyEight.drawPile,
			// crazyEight.discardPile, crazyEight.currentRank, crazyEight.currentSuit);
			// crazyEight.playerList.get(1).playGame(crazyEight.drawPile,
			// crazyEight.discardPile, crazyEight.currentRank, crazyEight.currentSuit);
			// System.out.println("-------------------------------DicardPile
			// -------------------------------");
			// crazyEight.discardPile.showCardCollection();
			// System.out.println("-------------------------------DrawPile
			// -------------------------------");
			// crazyEight.drawPile.showCardCollection();
			// //Mettre a jour le currentSuit et currentRank
			// crazyEight.currentRank =
			// crazyEight.discardPile.cardCollection.get(crazyEight.discardPile.cardCollection.size()-1).getRank();
			// crazyEight.currentSuit =
			// crazyEight.discardPile.cardCollection.get(crazyEight.discardPile.cardCollection.size()-1).getSuit();
			// }
			// crazyEight.playerList.get(0).playGame(crazyEight.drawPile,
			// crazyEight.discardPile, crazyEight.currentRank, crazyEight.currentSuit);
			// System.out.println("-------------------------------DicardPile
			// -------------------------------");
			// crazyEight.discardPile.showCardCollection();
			// System.out.println("-------------------------------DrawPile
			// -------------------------------");
			// crazyEight.drawPile.showCardCollection();

		}

		public int getIndexOfAI() {
			int k = 0;
			for (int i = 0; i < playerList.size(); i++) {
				if (playerList.get(i).getName() == "ai")
					k = i;
			}
			return k;
		}

		public static int getCurrentSuit() {
			return currentSuit;
		}

		public static void setCurrentSuit(int currentSuit) {
			Eight.currentSuit = currentSuit;
		}

		public void activateEffect(Card playedCard)  {
			switch (playedCard.getRank()) {
			case 1:
				if (turn == playerList.size()-1) {
					for (int i = 0; i < 4; i++) {
						Card newCard = new Card();
						newCard = drawPile.getCardCollection().get(0);
						playerList.get(0).getHand().addCard(newCard);
						drawPile.removeCard(0);
					}
					System.out.println("Player "+ playerList.get(0) + " drawed four cards.");
				} else {
					for (int i = 0; i < 4; i++) {
						Card newCard = new Card();
						newCard = drawPile.getCardCollection().get(0);
						playerList.get(turn + 1).getHand().addCard(newCard);
						drawPile.removeCard(0);
					}
					System.out.println("Player "+ playerList.get(turn+1) + " drawed four cards.");

				}
				break;
			case 11:
				Collections.reverse(playerList);
				turn=playerList.size()-turn-1;
				System.out.println("The direction of play has been reverted.");
				break;
			case 10:
				System.out.println("Player "+ playerList.get(turn) + " get another turn.");
				turn--;
				break;
			case 8:
				if (turn == getIndexOfAI()) {
					Random r = new Random();
					int valeur = 1 + r.nextInt(4);
					Eight.setCurrentSuit(valeur);
					System.out.println("The suit is now " + Eight.getCurrentSuit() + ".");
				} else {
					System.out.println("Type the number matching the suit you want");
					System.out.println("1---------->clubs");
					System.out.println("2---------->spades");
					System.out.println("3---------->diamonds");
					System.out.println("4---------->hearts");
					Scanner sc = new Scanner(System.in);
					Eight.setCurrentSuit(sc.nextInt());
					System.out.println("The suit is now " + GlobalInformation.suits[Eight.getCurrentSuit()] + ".");
					break;
				}
			case 7:
				if (turn == playerList.size()-1) {
					turn = 0;
					System.out.println(playerList.get(0) + "'s turn has been skipped. It is the turn of player "+playerList.get(1)+".");
				} else if(turn == playerList.size()-2){
					System.out.println(playerList.get(playerList.size()-1) + "'s turn has been skipped. It is the turn of player "+playerList.get(0)+".");
					turn = playerList.size()+1;
				} else {
					System.out.println(playerList.get(turn+1) + "'s turn has been skipped. It is the turn of player "+playerList.get(turn+2)+".");
					turn++;
				}
				;
				break;
			case 5:
				if (turn == getIndexOfAI()) {
					Random r1 = new Random();
					int resultat = r1.nextInt(playerList.size());
					Card newCard = new Card();
					newCard = drawPile.getCardCollection().get(0);
					playerList.get(resultat).getHand().addCard(newCard);
					drawPile.removeCard(0);
					System.out.println("Player "+ playerList.get(resultat) + " drawed one cards.");
					break;
				} else {
					System.out.println("Type the number matching the player you want to draw a card");
					for (int i = 0; i < playerList.size(); i++) {
						System.out.println(i + "---------->" + playerList.get(i).getName());
					}
					Scanner sc1 = new Scanner(System.in);
					int playerToDraw = sc1.nextInt();
					Card newCard = new Card();
					newCard = drawPile.getCardCollection().get(0);
					playerList.get(playerToDraw).getHand().addCard(newCard);
					drawPile.removeCard(0);
					System.out.println("Player "+ playerList.get(playerToDraw) + " drawed one cards.");
					break;
				}
			default:
				break;
			}
		}
	}
