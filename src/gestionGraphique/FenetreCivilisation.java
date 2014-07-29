package gestionGraphique;

import gestionCarte.Carte;
import gestionCarte.Position;
import gestionUnite.Nation;
import gestionUnite.Unite;
import gestionUnite.Ville;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;

import logiqueDuJeu.ActionUnite;
import logiqueDuJeu.ActionVille;
import logiqueDuJeu.Affichage;
import logiqueDuJeu.Joueur;

/**
 * @author Romain Une fenetre de jeu.
 */
public class FenetreCivilisation implements Runnable, ActionListener,
		Affichage, Joueur {

	private int TAILLE_MENU = 200;

	private JSplitPane splitPane;
	private AffichageMenu menu;
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
		AffichageCarte carteDuMonde = new AffichageCarte(carte, this);
		this.splitPane.setRightComponent(carteDuMonde);
		this.splitPane.setDividerLocation(TAILLE_MENU);

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
	public void actionPerformed(ActionEvent event) {
		System.out.println("Event !");
		JComponent source = (JComponent) event.getSource();
		// Bouton fin du tour
		if (source.getName() == "FinirTour") {
			this.finDuTour = true;
		}
		// Bouton am�liorer la ville
		else if (source.getName() == "AmeliorerVille") {
			this.actionVilleChoisie = ActionVille.Ameliorer;
			this.lActionVilleEstChoisie = true;
		}
		// Bouton cr�e une unit�
		else if (source.getName() == "CreerUnite") {
			this.actionVilleChoisie = ActionVille.CreerUnite;
			this.lActionVilleEstChoisie = true;
		}
		// Bouton am�liorer une unit�
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