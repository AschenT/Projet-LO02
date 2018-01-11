package Vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import Controleur.MenuControleur;
import game.Eight;
import strategy.Difficulty;
import strategy.Easy;
import strategy.Hard;

import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.InputMismatchException;
import java.awt.event.ActionEvent;

public class Interface {

	private JFrame frame;
	private JTextField nombreJoueur;
	private JTextField nombreOrdi;
	private JButton btnValider;
	private JLabel lblNombreDeJoueurs;
	private JLabel lblNombreDordinateur;
	private JRadioButton rdbtnMedium;
	private JRadioButton rdbtnEasy;
	private JLabel lblJoueursMaximum;
	private JLabel lblVariante;
	private JRadioButton rdbtnAvecEffet;
	private JRadioButton rdbtnSansEffet;
	private MenuControleur controleur;
	private int totalJoueur;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the application.
	 */
	public Interface(MenuControleur mc) {
		initialize();
		controleur = mc;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 450, 300);
		getFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);

		nombreJoueur = new JTextField();
		nombreJoueur.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent e) {
				checkSelection();
			}
		});
		nombreJoueur.setBounds(12, 42, 116, 22);
		getFrame().getContentPane().add(nombreJoueur);
		nombreJoueur.setColumns(10);

		nombreOrdi = new JTextField();
		nombreOrdi.addCaretListener(new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent e) {
				checkSelection();
			}
		});
		nombreOrdi.setBounds(12, 123, 116, 22);
		getFrame().getContentPane().add(nombreOrdi);
		nombreOrdi.setColumns(10);

		btnValider = new JButton("Valider");
		btnValider.setEnabled(false);
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getFrame().dispose();
				controleur.startGame();

			}
		});
		btnValider.setBounds(270, 188, 97, 25);
		getFrame().getContentPane().add(btnValider);

		lblNombreDeJoueurs = new JLabel("Nombre de Joueurs");
		lblNombreDeJoueurs.setBounds(12, 13, 116, 22);
		getFrame().getContentPane().add(lblNombreDeJoueurs);

		lblNombreDordinateur = new JLabel("Nombre d'ordinateur");
		lblNombreDordinateur.setBounds(12, 88, 143, 22);
		getFrame().getContentPane().add(lblNombreDordinateur);

		rdbtnMedium = new JRadioButton("Medium");
		buttonGroup_1.add(rdbtnMedium);
		rdbtnMedium.setBounds(12, 202, 127, 25);
		getFrame().getContentPane().add(rdbtnMedium);
		rdbtnMedium.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				checkSelection();

			}

		});

		rdbtnEasy = new JRadioButton("Easy");
		buttonGroup_1.add(rdbtnEasy);
		rdbtnEasy.setBounds(12, 172, 127, 25);
		getFrame().getContentPane().add(rdbtnEasy);
		rdbtnEasy.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				checkSelection();

			}

		});

		lblJoueursMaximum = new JLabel("4 Joueurs Maximum!");
		lblJoueursMaximum.setBounds(216, 18, 148, 52);
		getFrame().getContentPane().add(lblJoueursMaximum);

		lblVariante = new JLabel("Variante");
		lblVariante.setBounds(226, 83, 79, 22);
		getFrame().getContentPane().add(lblVariante);

		rdbtnAvecEffet = new JRadioButton("Avec effet");
		buttonGroup.add(rdbtnAvecEffet);
		rdbtnAvecEffet.setBounds(216, 114, 127, 25);
		getFrame().getContentPane().add(rdbtnAvecEffet);
		rdbtnAvecEffet.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				checkSelection();
			}
		});

		rdbtnSansEffet = new JRadioButton("Sans effet");
		buttonGroup.add(rdbtnSansEffet);
		rdbtnSansEffet.setBounds(216, 144, 127, 25);
		getFrame().getContentPane().add(rdbtnSansEffet);
		rdbtnSansEffet.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				checkSelection();

			}

		});
	}

	public int getNombreJoueur() {
		if (Integer.parseInt(nombreJoueur.getText()) >= 1 && Integer.parseInt(nombreJoueur.getText()) < 4) {
			return Integer.parseInt(nombreJoueur.getText());
		} else {
			return 0;
		}
	}

	public int getNombreOrdi() {
		if (Integer.parseInt(nombreOrdi.getText()) >= 0
				&& Integer.parseInt(nombreJoueur.getText()) + Integer.parseInt(nombreOrdi.getText()) < 4) {
			return Integer.parseInt(nombreOrdi.getText());
		} else {
			return 0;
		}
	}

	public int getVariante() {
		if (rdbtnSansEffet.isSelected()) {
			return 1;
		} else {
			return 0;
		}
	}

	public Difficulty getDifficulty() {
		if (rdbtnMedium.isSelected()) {
			return new Hard();
		} else {
			return new Easy();
		}
	}

	public boolean checkChampJoueur() {
		try {
			int a;
			if(nombreJoueur.getText().equals("")) 
			 return false;
			else
				a = Integer.parseInt(nombreJoueur.getText());
		}
		
		catch(NumberFormatException e) {
			System.out.println("Vous devez rentrez un nombre");
			return false;
		}
		return true;
	}

	public boolean checkChampOrdi() {
		try {
			int a;
			if(nombreOrdi.getText().equals("")) 
			 return false;
			else
				a = Integer.parseInt(nombreOrdi.getText());
		}
		
		catch(NumberFormatException e) {
			System.out.println("Vous devez rentrez un nombre");
			return false;
		}
		return true;
	}

	public boolean checkChampDifficulte() {
		if (rdbtnEasy.isSelected() || rdbtnMedium.isSelected()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkChampVariante() {
		if (rdbtnSansEffet.isSelected() || rdbtnAvecEffet.isSelected()) {
			return true;
		} else {
			return false;
		}

	}

	public void checkSelection() {
		// verifier les deux chaps de textes et les deux groupes
		if (checkChampDifficulte() && checkChampJoueur() && checkChampOrdi() && checkChampVariante()) {
			totalJoueur = getNombreJoueur() + getNombreOrdi();
			if(totalJoueur<=4 && totalJoueur>=2)
			btnValider.setEnabled(true);
		}else {
			btnValider.setEnabled(false);
		}
	}

	public void displayErrorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(getFrame(), this, errorMessage, JOptionPane.ERROR_MESSAGE);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

}
