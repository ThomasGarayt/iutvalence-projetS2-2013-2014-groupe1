package fr.projet.java.gestionGraphique;

import fr.projet.java.gestionCarte.Carte;
import fr.projet.java.gestionCarte.Position;
import fr.projet.java.gestionUnite.Chemin;
import fr.projet.java.gestionUnite.Nation;
import fr.projet.java.gestionUnite.Unite;
import fr.projet.java.gestionUnite.Ville;
import fr.projet.java.logiqueDuJeu.ActionUnite;
import fr.projet.java.logiqueDuJeu.ActionVille;
import fr.projet.java.logiqueDuJeu.Affichage;
import fr.projet.java.logiqueDuJeu.Joueur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;

/**
 * @author Romain Une fenetre de jeu.
 */
public class FenetreCivilisation implements Runnable, ActionListener,
		Affichage, Joueur {

	private int TAILLE_MENU = 200;

	private JSplitPane splitPane;
	private AffichageMenu menu;
	private AffichageCarte carteDuMonde;
	private JFrame fenetre;

	private Position positionChoisie;
	private volatile boolean laPositionEstChoisie;
	private ActionVille actionVilleChoisie;
	private volatile boolean lActionVilleEstChoisie;
	private ActionUnite actionUniteChoisie;
	private volatile boolean lActionUniteEstChoisie;

	private volatile boolean finDuTour;

	/**
	 * Initialisation de la fenetre de jeu.
	 */
	public void initialiserFenetreCivilisation() {

		fenetre = new JFrame("Civilisation II");
		fenetre.setSize(700, 600);
		fenetre.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		fenetre.setResizable(false);

		this.splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

		this.splitPane.add(new JButton());

		this.splitPane.add(new JButton());

		this.splitPane.setEnabled(false);

		this.splitPane.setBorder(null);

		this.splitPane.setDividerSize(0);

		this.splitPane.setResizeWeight(0);

		fenetre.add(splitPane);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setVisible(true);

		this.splitPane.setDividerLocation(TAILLE_MENU);

	}

	@Override
	public Position selectionnerPosition() throws FinDuTourException {
		while ( !laPositionEstChoisie && !finDuTour)
			;
		if (finDuTour) {
			finDuTour = false;
			throw new FinDuTourException();
		}
		laPositionEstChoisie = false;
		return positionChoisie;
	}

	@Override
	public ActionVille selectionnerActionVille() throws FinDuTourException {
		while ( !lActionVilleEstChoisie && !laPositionEstChoisie && !finDuTour)
			;
		if (finDuTour) {
			finDuTour = false;
			throw new FinDuTourException();
		}
		if (laPositionEstChoisie) {
			laPositionEstChoisie = false;
			actionVilleChoisie = ActionVille.Deselectionner;
		}
		lActionVilleEstChoisie = false;
		return actionVilleChoisie;
	}

	@Override
	public ActionUnite selectionnerActionUnite() throws FinDuTourException {
		while ( !lActionUniteEstChoisie && !laPositionEstChoisie && !finDuTour)
			;
		if (finDuTour) {
			finDuTour = false;
			throw new FinDuTourException();
		}
		if (laPositionEstChoisie) {
			actionUniteChoisie = ActionUnite.Deplacer;
		}
		lActionUniteEstChoisie = false;
		return actionUniteChoisie;
	}

	@Override
	public void mettreAJourLaCarte(Carte carte) {
		this.carteDuMonde.mettreAJour(carte);
	}

	@Override
	public void mettreAJourLeMenu(Unite unite, Nation joueur) {
		this.menu = new AffichageMenu(unite, joueur, this);
		this.splitPane.setLeftComponent(this.menu);
		this.splitPane.setDividerLocation(TAILLE_MENU);

	}

	@Override
	public void mettreAJourLeMenu(Ville ville, Nation joueur) {
		this.menu = new AffichageMenu(ville, joueur, this);
		this.splitPane.setLeftComponent(this.menu);
		this.splitPane.setDividerLocation(TAILLE_MENU);

	}

	@Override
	public void mettreAJourLeMenu(Nation joueur) {
		this.menu = new AffichageMenu(joueur, this);
		this.splitPane.setLeftComponent(menu);
		this.splitPane.setDividerLocation(TAILLE_MENU);

	}

	@Override
	public void afficherUnChemin(Chemin cheminAAfficher) {
		this.carteDuMonde.afficherUnChemin(cheminAAfficher);
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		System.out.println("Event !");
		JComponent source = (JComponent) event.getSource();
		// Bouton fin du tour
		if (source.getName() == "FinirTour") {
			this.finDuTour = true;
		}
		// Bouton améliorer la ville
		else if (source.getName() == "AmeliorerVille") {
			this.actionVilleChoisie = ActionVille.Ameliorer;
			this.lActionVilleEstChoisie = true;
		}
		// Bouton crée une unité
		else if (source.getName() == "CreerSoldat") {
			this.actionVilleChoisie = ActionVille.CreerUnite;
			this.lActionVilleEstChoisie = true;
		}
		// Bouton améliorer une unité
		else if (source.getName() == "AmeliorerUnite") {
			this.actionUniteChoisie = ActionUnite.Ameliorer;
			this.lActionUniteEstChoisie = true;
		}
		// Un bouton de la carte
		else {
			System.out.println("Position");
			this.positionChoisie = new Position(
					((BoutonCarte) source).obtenirX(),
					((BoutonCarte) source).obtenirY());
			this.laPositionEstChoisie = true;
		}

	}
	
	@Override
	public void run() {
		lActionUniteEstChoisie = false;
		lActionVilleEstChoisie = false;
		laPositionEstChoisie = false;
		
		initialiserFenetreCivilisation();
	}

}