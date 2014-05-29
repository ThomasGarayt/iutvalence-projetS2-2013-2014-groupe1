package ihmCivilisation;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import systemeCivilisation.Joueur;
import systemeCivilisation.Unite;
import systemeCivilisation.Ville;

/**
 * @author Romain Affiche le menu passe en parametre dans un JPanel.
 */
public class AffichageMenu extends JPanel {

	/**
	 * Crée un menu.
	 * 
	 * @param joueur
	 *            Le joueur dont c'est le tour.
	 * @param ecouteurBouton
	 *            L'auditeur des boutons du menu.
	 */
	public AffichageMenu(Joueur joueur, ActionListener ecouteurBouton) {
		super();

		this.setLayout(new GridLayout(6, 1));

		this.add(new Recap(joueur));

		this.add(new JLabel(" Rien n'est selectionner"));
		this.add(new Recap());

		this.add(new JLabel(" "));
		this.add(new JLabel(" "));

		JButton finirTour = new JButton("Finir le tour");
		finirTour.setName("FinirTour");
		finirTour.addActionListener(ecouteurBouton);

		this.add(finirTour);
	}

	/**
	 * Crée un menu.
	 * 
	 * @param unite
	 *            L'unité qui est selectionne.
	 * @param joueur
	 *            Le joueur dont c'est le tour.
	 * @param ecouteurBouton
	 *            L'auditeur des boutons du menu.
	 */
	public AffichageMenu(Unite unite, Joueur joueur,
			ActionListener ecouteurBouton) {
		super();

		this.setLayout(new GridLayout(6, 1));

		this.add(new Recap(joueur));

		this.add(new JLabel(" Unite"));
		this.add(new Recap(unite));

		this.add(new JLabel(" "));
		this.add(new JLabel(" "));

		JButton finirTour = new JButton("Finir le tour");
		finirTour.setName("FinirTour");
		finirTour.addActionListener(ecouteurBouton);

		this.add(finirTour);
	}
	
	/**
	 * Crée un menu.
	 * 
	 * @param ville
	 *            La ville qui est selectionne.
	 * @param joueur
	 *            Le joueur dont c'est le tour.
	 * @param ecouteurBouton
	 *            L'auditeur des boutons du menu.
	 */
	public AffichageMenu(Ville ville, Joueur joueur,
			ActionListener ecouteurBouton) {
		super();

		this.setLayout(new GridLayout(6, 1));

		this.add(new Recap(joueur));

		this.add(new JLabel(new ImageIcon("Images/ville.jpg")));
		this.add(new Recap(ville));

		if (joueur == ville.obtenirJoueurProprietaire()) {
			JButton ameliorerChar = new JButton("Creer une Unite ");
			ameliorerChar.setName("CreerUnite");
			ameliorerChar.addActionListener(ecouteurBouton);

			JButton ameliorerVille = new JButton("Ameliorer au niveau : "
					+ (ville.obtenirNiveau() + 1) + " "
					+ Integer.toString(ville.coutNiveauUp()));
			ameliorerVille.setName("AmeliorerVille");
			ameliorerVille.addActionListener(ecouteurBouton);

			this.add(ameliorerChar);
			this.add(ameliorerVille);

		} else {
			this.add(new JLabel(""));
			this.add(new JLabel(""));
		}
		JButton finirTour = new JButton("Finir le tour");
		finirTour.setName("FinirTour");
		finirTour.addActionListener(ecouteurBouton);

		this.add(finirTour);
	}

}
