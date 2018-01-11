package Vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
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
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		nombreJoueur = new JTextField();
		nombreJoueur.setBounds(12, 42, 116, 22);
		frame.getContentPane().add(nombreJoueur);
		nombreJoueur.setColumns(10);
		
		nombreOrdi = new JTextField();
		nombreOrdi.setBounds(12, 123, 116, 22);
		frame.getContentPane().add(nombreOrdi);
		nombreOrdi.setColumns(10);
		
		btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnValider.setBounds(270, 188, 97, 25);
		frame.getContentPane().add(btnValider);
		
		lblNombreDeJoueurs = new JLabel("Nombre de Joueurs");
		lblNombreDeJoueurs.setBounds(12, 13, 116, 22);
		frame.getContentPane().add(lblNombreDeJoueurs);
		
		lblNombreDordinateur = new JLabel("Nombre d'ordinateur");
		lblNombreDordinateur.setBounds(12, 88, 143, 22);
		frame.getContentPane().add(lblNombreDordinateur);
		
		rdbtnMedium = new JRadioButton("Medium");
		buttonGroup_1.add(rdbtnMedium);
		rdbtnMedium.setBounds(12, 202, 127, 25);
		frame.getContentPane().add(rdbtnMedium);
		
		rdbtnEasy = new JRadioButton("Easy");
		buttonGroup_1.add(rdbtnEasy);
		rdbtnEasy.setBounds(12, 172, 127, 25);
		frame.getContentPane().add(rdbtnEasy);
		
		lblJoueursMaximum = new JLabel("4 Joueurs Maximum!");
		lblJoueursMaximum.setBounds(216, 18, 148, 52);
		frame.getContentPane().add(lblJoueursMaximum);
		
		lblVariante = new JLabel("Variante");
		lblVariante.setBounds(226, 83, 79, 22);
		frame.getContentPane().add(lblVariante);
		
		rdbtnAvecEffet = new JRadioButton("Avec effet");
		buttonGroup.add(rdbtnAvecEffet);
		rdbtnAvecEffet.setBounds(216, 114, 127, 25);
		frame.getContentPane().add(rdbtnAvecEffet);
		
		rdbtnSansEffet = new JRadioButton("Sans effet");
		buttonGroup.add(rdbtnSansEffet);
		rdbtnSansEffet.setBounds(216, 144, 127, 25);
		frame.getContentPane().add(rdbtnSansEffet);
	}
}
