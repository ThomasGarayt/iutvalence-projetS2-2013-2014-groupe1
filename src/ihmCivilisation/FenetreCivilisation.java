package ihmCivilisation;

import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;

import systemeCivilisation.*;

/**
 * @author Romain Une fenetre de jeu Civilisation.
 */
public class FenetreCivilisation {

	private int TAILLE_MENU = 200;

	private JSplitPane splitPane;
	private AffichageMenu menu;

	/**
	 * Initialisation de la fenetre de jeu.
	 * 
	 * @param carte
	 *            La carte que l'on va afficher.
	 * @param joueur
	 *            Le joueur dont c'est le tour.
	 * @param ecouteurBouton
	 *            L'auditeur de bouton du jeu.
	 */
	public void initialiserFenetreCivilisation(Carte carte, Joueur joueur,
			ActionListener ecouteurBouton) {
		
		
		JFrame fenetre = new JFrame("Civilisation II");
		fenetre.setSize(700, 600);
		fenetre.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		fenetre.setResizable(false);

		JPanel carteDuJeu = new AffichageCarte(carte, ecouteurBouton);
		
		menu = new AffichageMenu(joueur, ecouteurBouton);

		this.splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

		this.splitPane.add(menu);

		this.splitPane.add(carteDuJeu);

		this.splitPane.setEnabled(false);

		this.splitPane.setBorder(null);

		this.splitPane.setDividerSize(0);

		this.splitPane.setResizeWeight(0);

		fenetre.add(splitPane);
		fenetre.setVisible(true);

		this.splitPane.setDividerLocation(TAILLE_MENU);

	}

	/**
	 * Met a jour la carte.
	 * 
	 * @param carte
	 *            La carte que l'on va afficher.
	 * @param ecouteurBouton
	 *            L'auditeur de bouton du jeu.
	 */
	public void mettreAJourLaCarte(Carte carte, ActionListener ecouteurBouton) {
		AffichageCarte carteDuMonde = new AffichageCarte(carte,
				ecouteurBouton);
		this.splitPane.setRightComponent(carteDuMonde);
		this.splitPane.setDividerLocation(TAILLE_MENU);
	}

	/**
	 * Met a jour le menu
	 * 
	 * @param unite
	 *            L'unite selectionner.
	 * @param joueur
	 *            Le joueur dont c'est le tour.
	 * @param ecouteurBouton
	 *            L'auditeur de bouton du jeu.
	 */
	public void mettreAJourLeMenu(Unite unite, Joueur joueur,
			ActionListener ecouteurBouton) {
		this.menu = new AffichageMenu(unite, joueur, ecouteurBouton);
		this.splitPane.setLeftComponent(this.menu);
		this.splitPane.setDividerLocation(TAILLE_MENU);
	}

	/**
	 * Met a jour le menu
	 * 
	 * @param ville
	 *            La ville selectionner.
	 * @param joueur
	 *            Le joueur dont c'est le tour.
	 * @param ecouteurBouton
	 *            L'auditeur de bouton du jeu.
	 */
	public void mettreAJourLeMenu(Ville ville, Joueur joueur,
			ActionListener ecouteurBouton) {
		this.menu = new AffichageMenu(ville, joueur, ecouteurBouton);
		this.splitPane.setLeftComponent(this.menu);
		this.splitPane.setDividerLocation(200);
	}

	/**
	 * Met a jour le menu.
	 * 
	 * @param joueur
	 *            Le joueur dont c'est le tour.
	 * @param ecouteurBouton
	 *            L'auditeur de bouton du jeu.
	 */
	public void mettreAJourLeMenu(Joueur joueur, ActionListener ecouteurBouton) {
		this.menu = new AffichageMenu(joueur, ecouteurBouton);
		this.splitPane.setLeftComponent(menu);
		this.splitPane.setDividerLocation(TAILLE_MENU);
	}

}
