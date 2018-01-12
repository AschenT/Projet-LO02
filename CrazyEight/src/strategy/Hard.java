package strategy;

import card.Card;
import card.CardCollection;
import game.GlobalInformation;
import player.AIPlayer;

public class Hard implements Difficulty {
	public int couleurDominante(AIPlayer ai) {
		int c = 0;
		int s = 0;
		int d = 0;
		int h = 0;
		for(int i=0;i<ai.getHand().getCardCollection().size();i++) {
			if(ai.getHand().getCardCollection().get(i).getSuit()==1) {
				c=c+1;
			}else if(ai.getHand().getCardCollection().get(i).getSuit()==2) {
				s=s+1;
			}else if(ai.getHand().getCardCollection().get(i).getSuit()==3) {
				d=d+1;
			}else {
				h=h+1;
			}
		}
			int couleurs[] = {c, s, d, h};
			int valeurMax = couleurs[0];
			int couleurDominante = 1;
			    for (int i=0; i<couleurs.length; i++){
			            
			                if (couleurs[i] > valeurMax)
			                    {
			                        valeurMax = couleurs[i];
			                        couleurDominante = i;
			                    }
			            
		}
			    return couleurDominante;
	}
	
	public int nombreDominant(AIPlayer ai) {
		int nombreMax = 0;
		int nombreDominant = 0;
		for(int i=0;i<ai.getHand().getCardCollection().size();i++) {
			int somme = 1;
				for(int j=i+1;j <ai.getHand().getCardCollection().size();j++) {
					if(ai.getHand().getCardCollection().get(i)==ai.getHand().getCardCollection().get(j)) {
						somme = somme + 1;
					}
					if(somme>=nombreMax) {
						nombreMax = somme;
						nombreDominant = i;
					}
				}
		}
		return 	ai.getHand().getCardCollection().get(nombreDominant).getRank();
	}
	
	public int choixMethode(AIPlayer ai) {
		int nombreMax = 0;
		for(int i=0;i<ai.getHand().getCardCollection().size();i++) {
			int somme = 1;
				for(int j=i+1;j <ai.getHand().getCardCollection().size();j++) {
					if(ai.getHand().getCardCollection().get(i)==ai.getHand().getCardCollection().get(j)) {
						somme = somme + 1;
					}
					if(somme>=nombreMax) {
						nombreMax = somme;
					}
				}
		}
		int c = 0;
		int s = 0;
		int d = 0;
		int h = 0;
		for(int i=0;i<ai.getHand().getCardCollection().size();i++) {
			if(ai.getHand().getCardCollection().get(i).getSuit()==1) {
				c=c+1;
			}else if(ai.getHand().getCardCollection().get(i).getSuit()==2) {
				s=s+1;
			}else if(ai.getHand().getCardCollection().get(i).getSuit()==3) {
				d=d+1;
			}else {
				h=h+1;
			}
		}
			int couleurs[] = {c, s, d, h};
			int valeurMax = couleurs[0];
			    for (int i=0; i<couleurs.length; i++){
			            
			                if (couleurs[i] > valeurMax)
			                    {
			                        valeurMax = couleurs[i];
			                    }
			            
		}
			if(valeurMax<=nombreMax)  {
				return 0;
			}else {
				return 1;
			}
	}
	
	@Override
	public void playGame(AIPlayer ai, CardCollection drawPile, CardCollection discardPile, int currentRank, int currentSuit) {

		int n=1;
		int cardDrawn=0;
		int indexDiscardCard = -1;
		//afficher toutes les cartes jouables s'ils existent et leurs numeros correspondants dans le Handcard sinon piocher une carte
		System.out.println("----------------------------AI's turn----------------------------");
		while((ai.getPlayableCards().getCardCollection().size()==0)&&(n>0))
		{
			for(int i=0;i<ai.getHand().getCardCollection().size();i++)
			{
				//Afficher les cartes jouables
				if((ai.getHand().getCardCollection().get(i).getRank()==currentRank)||(ai.getHand().getCardCollection().get(i).getSuit()==currentSuit))
				{
					ai.getPlayableCards().addCard(ai.getHand().getCardCollection().get(i));
					//System.out.println(i+"|"+ai.getHand().getCardCollection().get(i).toString());
					indexDiscardCard = i;
				}
			}
			if(choixMethode(ai)==0) {
				for(int i=0;i<ai.getPlayableCards().getCardCollection().size();i++) {
					if(nombreDominant(ai)==ai.getPlayableCards().getCardCollection().get(i).getRank()) {
						indexDiscardCard = i;
					}
				}
					}else {
						for(int k=0;k<ai.getPlayableCards().getCardCollection().size();k++) {
							if(couleurDominante(ai)==ai.getPlayableCards().getCardCollection().get(k).getRank()) {
								indexDiscardCard = k;
					}
				}
			}
			//S'il y a pas de carte jouables, il faut piocher une carte 
			if((ai.getPlayableCards().getCardCollection().size()==0)&&(n>0))
			{
				System.out.println("----------------------------AI don't have any playable cards, AI drawed one card----------------------------");
				Card newCard = new Card();
				//piocher la premiere carte dans le drawPile
				newCard = drawPile.getCardCollection().get(0);
				ai.getHand().addCard(newCard);
				drawPile.removeCard(0);
				//System.out.println(newCard.toString());
				cardDrawn=1;
			}
			n--;
		}
		
		//Si le joueur n'a pas de carte jouables apres 3 fois de piocher,
		if(ai.getPlayableCards().getCardCollection().size()==0)
		{
			//System.out.println("----------------------------AI still don't have any playable cards----------------------------");
			System.out.println("----------------------------Pass----------------------------");
			ai.setPass(true);
		}
		else 
		{		
			
			//verifier la carte choisie par le joueur AI est compatible et jeter la carte si possible
			if((ai.getHand().getCardCollection().get(indexDiscardCard).getRank()==currentRank)||(ai.getHand().getCardCollection().get(indexDiscardCard).getSuit()==currentSuit))
			{
				discardPile.addCard(ai.getHand().getCardCollection().get(indexDiscardCard));
//				currentRank = this.hand.cardCollection.get(indexDiscardCard).getRank();
//				currentSuit = this.hand.cardCollection.get(indexDiscardCard).getSuit();
				System.out.println("AI discarded card:"+ai.getHand().getCardCollection().get(indexDiscardCard).toString());
				ai.getHand().getCardCollection().remove(ai.getHand().getCardCollection().get(indexDiscardCard));
				
			}
			
		}
		
	}
}




