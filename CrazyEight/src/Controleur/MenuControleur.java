package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Vue.Interface;
import game.Eight;

public class MenuControleur {
	private Interface view;
	private Eight model;
	
	public MenuControleur(Interface view, Eight model) {
		this.view=view;
		this.model=model;
		
		view.addValidationListener(new ValidateListener());
	}
	
	class ValidateListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int nombreJoueur, nombreOrdi = 0;
			try {
				nombreJoueur = view.getNombreJoueur();
				nombreOrdi = view.getNombreOrdi();
				model.setComputerQuantitie(nombreOrdi);
				model.setPlayerQuantitie(nombreJoueur);
			}catch(NumberFormatException ex) {
				view.displayErrorMessage("Entrez un entier!");
			}
			
		}
		
	}

}
