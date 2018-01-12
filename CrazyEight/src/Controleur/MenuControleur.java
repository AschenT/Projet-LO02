package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Vue.Interface;
import game.Eight;
import strategy.Difficulty;

public class MenuControleur {
	private Interface view;
	private Eight model;
	
	public MenuControleur() {
		this.model=new Eight();
		this.view=new Interface(this);
		view.getFrame().setVisible(true);
		}
		
	public void startGame() {
		int nombreJoueur, nombreOrdi, variante = 0;
		try {
			nombreJoueur = view.getNombreJoueur();
			model.setPlayerQuantitie(nombreJoueur);
			
			nombreOrdi = view.getNombreOrdi();
			model.setComputerQuantitie(nombreOrdi);
			
			Difficulty difficulty = view.getDifficulty();
			
			variante = view.getVariante();
			model.setSelectedVariation(variante);

			
			model.demarrerConsole(difficulty);
		}catch(NumberFormatException ex) {
			//view.displayErrorMessage("Entrez un entier!");
			System.out.println("hi");
		}
	}
	
	

}
