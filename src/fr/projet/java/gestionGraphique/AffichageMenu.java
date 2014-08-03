package fr.projet.java.gestionGraphique;

import fr.projet.java.gestionUnite.Nation;
import fr.projet.java.gestionUnite.TypeUnite;
import fr.projet.java.gestionUnite.Unite;
import fr.projet.java.gestionUnite.Ville;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Romain Affiche le menu passe en parametre dans un JPanel.
 */
public class AffichageMenu extends JPanel {

	private static final long serialVersionUID = 1L;

	private JComboBox<TypeUnite> choixDeLUnite;

	/**
	 * Cr�e un menu.
	 * 
	 * @param joueur
	 *            Le joueur dont c'est le tour.
	 * @param ecouteurBouton
	 *            L'auditeur des boutons du menu.
	 */
	public AffichageMenu(Nation joueur, ActionListener ecouteurBouton) {
		super();

		this.setLayout(new GridLayout(6, 1));

		this.add(new Recap(joueur));

		this.add(new JLabel(" Rien n'est selectionne"));
		this.add(new Recap());

		this.add(new JLabel(" "));
		this.add(new JLabel(" "));

		JButton finirTour = new JButton("Finir le tour");
		finirTour.setName("FinirTour");
		finirTour.addActionListener(ecouteurBouton);

		this.add(finirTour);
	}

	/**
	 * Cree un menu.
	 * 
	 * @param unite
	 *            L'unite qui est selectionne.
	 * @param joueur
	 *            Le joueur dont c'est le tour.
	 * @param ecouteurBouton
	 *            L'auditeur des boutons du menu.
	 */
	public AffichageMenu(Unite unite, Nation joueur,
			ActionListener ecouteurBouton) {
		super();

		this.setLayout(new GridLayout(7, 1));

		this.add(new Recap(joueur));

		this.add(new JLabel(" Unite"));
		this.add(new Recap(unite));

		this.add(new JLabel(" "));
		this.add(new JLabel(" "));

		if (joueur == unite.obtenirJoueur()) {
			JButton ameliorerUnite = new JButton("Ameliorer au niveau "
					+ (unite.obtenirNiveau() + 1) + " : "
					+ Integer.toString(unite.coutNiveauUp()));
			ameliorerUnite.setName("AmeliorerUnite");
			ameliorerUnite.addActionListener(ecouteurBouton);

			this.add(ameliorerUnite);
		}

		else {
			this.add(new JLabel(""));
		}
		JButton finirTour = new JButton("Finir le tour");
		finirTour.setName("FinirTour");
		finirTour.addActionListener(ecouteurBouton);
		this.add(finirTour);
	}

	/**
	 * Cree un menu.
	 * 
	 * @param ville
	 *            La ville qui est selectionne.
	 * @param joueur
	 *            Le joueur dont c'est le tour.
	 * @param ecouteurBouton
	 *            L'auditeur des boutons du menu.
	 */
	public AffichageMenu(Ville ville, Nation joueur,
			ActionListener ecouteurBouton) {
		super();

		this.setLayout(new GridLayout(7, 1));

		this.add(new Recap(joueur));

		this.add(new JLabel(new ImageIcon("Images/ville.jpg")));
		this.add(new Recap(ville));

		if (joueur == ville.obtenirJoueurProprietaire()) {
			JButton creerUnite = new JButton("Creer une unite");
			creerUnite.setName("CreerUnite");
			creerUnite.addActionListener(ecouteurBouton);

			JButton ameliorerVille = new JButton("Ameliorer au niveau "
					+ (ville.obtenirNiveau() + 1) + " : "
					+ Integer.toString(ville.coutNiveauUp()));
			ameliorerVille.setName("AmeliorerVille");
			ameliorerVille.addActionListener(ecouteurBouton);

			this.choixDeLUnite = new JComboBox<TypeUnite>(TypeUnite.values());
			
			this.add(this.choixDeLUnite);
			this.add(creerUnite);
			this.add(ameliorerVille);

		}

		else {
			this.add(new JLabel(""));
			this.add(new JLabel(""));
			this.add(new JLabel(""));
		}
		JButton finirTour = new JButton("Finir le tour");
		finirTour.setName("FinirTour");
		finirTour.addActionListener(ecouteurBouton);

		this.add(finirTour);
	}

	/**
	 * Renvoi le type d'unite choisie dans la comboBox.
	 * 
	 * @return Le type d'unite courant de la combo box.
	 */
	public TypeUnite obtenirTypeUnite() {
		return (TypeUnite) choixDeLUnite.getSelectedItem();
	}
}
