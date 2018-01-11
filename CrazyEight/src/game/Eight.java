package game;

import java.util.*;

import card.Card;
import card.CardCollection;
import player.*;

public class Eight implements GlobalInformation {
	private int playerQuantitie = 0;
	private static int computerQuantitie = 0;


	private CardCollection drawPile;
	private CardCollection discardPile;

	private static int currentRank;
	private static int currentSuit;
	private List<Player> playerList;
	int turn;
	

	private int winner;
	private static int selectedVariation;


	public Eight() {
		// initialisation des joueurs, enregistres dans playerList afin de mieux
		// controler le jeux
		this.setPlayerList(new <Player>ArrayList());
		selectedVariation = choixVariante();


		//TODO limiter 4 joueurs
		this.playerQuantitie = saisirNombreJoueur();

		for (int i = 0; i < this.playerQuantitie; i++) {
			HumainPlayer newHumainPlayer = new HumainPlayer();
			this.getPlayerList().add(newHumainPlayer);
		}
		
		// instancier un joueur AI
		this.computerQuantitie = saisirNombreOrdi();

		for (int i = 0; i < this.computerQuantitie; i++) {
			AIPlayer newaiPlayer = new AIPlayer();
			this.getPlayerList().add(newaiPlayer);
		}


		this.setDrawPile(new CardCollection());
		this.setDiscardPile(new CardCollection());

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
			this.getDrawPile().newSet();
			this.getDrawPile().shuffle();

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
					newCard = this.getDrawPile().getCardCollection().get(0);
					this.getPlayerList().get(id).getHand().addCard(newCard);
					this.getDrawPile().removeCard(0);
				}
			}
			for (int id = this.playerQuantitie; id < this.playerQuantitie + this.computerQuantitie; id++) {
				for (int nb_cards = 0; nb_cards < 8; nb_cards++)////////////////////////////////////////////////////////////////////////////////////////////////////////
				{
					Card newCard = new Card();
					// newCard = this.drawPile.cardCollection.get(nb_cards);//on distribue toujours
					// la premiere carte de drawPile
					newCard = this.getDrawPile().getCardCollection().get(0);
					this.getPlayerList().get(id).getHand().addCard(newCard);
					this.getDrawPile().removeCard(0);
				}
			}
			// drawPile apres distribution
			// System.out.println("-------------------------------DrawPile after
			// dealing:-------------------------------");
			// this.drawPile.showCardCollection();

			// mettre la premiere carte du drawPile dans le discardPile qui vient d'etre
			// jetee
			Card firstCard = new Card();
			firstCard = this.getDrawPile().getCardCollection().get(0);
			this.getDrawPile().getCardCollection().remove(0);
			this.getDiscardPile().getCardCollection().add(firstCard);
			this.setCurrentRank(firstCard.getRank());
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
				System.out.println("----------------------------" + this.getPlayerList().get(i).toString()
						+ "----------------------------");
				this.getPlayerList().get(i).getHand().showCardCollection();
			}

		}


		public int getIndexOfAI() {
			int k = 0;
			for (int i = 0; i < getPlayerList().size(); i++) {
				if (getPlayerList().get(i).getName() == "ai")
					k = i;
			}
			return k;
		}
		public static int getComputerQuantitie() {
			return computerQuantitie;
		}

		public void setComputerQuantitie(int computerQuantitie) {
			this.computerQuantitie = computerQuantitie;
		}

		public static int getCurrentSuit() {
			return currentSuit;
		}

		public static void setCurrentSuit(int currentSuit) {
			Eight.currentSuit = currentSuit;
		}
	public int getPlayerQuantitie() {
		return playerQuantitie;
	}

	public void setPlayerQuantitie(int playerQuantitie) {
		this.playerQuantitie = playerQuantitie;
	}
	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

		public void activateEffect(Card playedCard)  {
			switch (playedCard.getRank()) {
			case 1:
				if (turn == getPlayerList().size()-1) {
					for (int i = 0; i < 4; i++) {
						Card newCard = new Card();
						newCard = getDrawPile().getCardCollection().get(0);
						getPlayerList().get(0).getHand().addCard(newCard);
						getDrawPile().removeCard(0);
					}
					System.out.println("Player "+ getPlayerList().get(0) + " drawed four cards.");
				} else {
					for (int i = 0; i < 4; i++) {
						Card newCard = new Card();
						newCard = getDrawPile().getCardCollection().get(0);
						getPlayerList().get(turn + 1).getHand().addCard(newCard);
						getDrawPile().removeCard(0);
					}
					System.out.println("Player "+ getPlayerList().get(turn+1) + " drawed four cards.");

				}
				break;
			case 11:
				Collections.reverse(getPlayerList());
				turn=getPlayerList().size()-turn-1;
				System.out.println("The direction of play has been reverted.");
				break;
			case 10:
				System.out.println("Player "+ getPlayerList().get(turn) + " get another turn.");
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
				if (turn == getPlayerList().size()-1) {
					turn = 0;
					System.out.println(getPlayerList().get(0) + "'s turn has been skipped. It is the turn of player "+getPlayerList().get(1)+".");
				} else if(turn == getPlayerList().size()-2){
					System.out.println(getPlayerList().get(getPlayerList().size()-1) + "'s turn has been skipped. It is the turn of player "+getPlayerList().get(0)+".");
					turn = getPlayerList().size()+1;
				} else {
					System.out.println(getPlayerList().get(turn+1) + "'s turn has been skipped. It is the turn of player "+getPlayerList().get(turn+2)+".");
					turn++;
				}
				;
				break;
			case 5:
				if (turn == getIndexOfAI()) {
					Random r1 = new Random();
					int resultat = r1.nextInt(getPlayerList().size());
					Card newCard = new Card();
					newCard = getDrawPile().getCardCollection().get(0);
					getPlayerList().get(resultat).getHand().addCard(newCard);
					getDrawPile().removeCard(0);
					System.out.println("Player "+ getPlayerList().get(resultat) + " drawed one cards.");
					break;
				} else {
					System.out.println("Type the number matching the player you want to draw a card");
					for (int i = 0; i < getPlayerList().size(); i++) {
						System.out.println(i + "---------->" + getPlayerList().get(i).getName());
					}
					Scanner sc1 = new Scanner(System.in);
					int playerToDraw = sc1.nextInt();
					Card newCard = new Card();
					newCard = getDrawPile().getCardCollection().get(0);
					getPlayerList().get(playerToDraw).getHand().addCard(newCard);
					getDrawPile().removeCard(0);
					System.out.println("Player "+ getPlayerList().get(playerToDraw) + " drawed one cards.");
					break;
				}
			default:
				break;
			}
		}

		public int getWinner() {
			return winner;
		}

		public void setWinner(int winner) {
			this.winner = winner;
		}

		public List<Player> getPlayerList() {
			return playerList;
		}

		public void setPlayerList(List<Player> playerList) {
			this.playerList = playerList;
		}

		public static int getCurrentRank() {
			return currentRank;
		}

		public void setCurrentRank(int currentRank) {
			Eight.currentRank = currentRank;
		}

		public CardCollection getDrawPile() {
			return drawPile;
		}

		public void setDrawPile(CardCollection drawPile) {
			this.drawPile = drawPile;
		}

		public CardCollection getDiscardPile() {
			return discardPile;
		}

		public void setDiscardPile(CardCollection discardPile) {
			this.discardPile = discardPile;
		}
		public int getSelectedVariation() {
			return selectedVariation;
		}

		public static void setSelectedVariation(int selectedVariation) {
			Eight.selectedVariation = selectedVariation;
		}
	}
