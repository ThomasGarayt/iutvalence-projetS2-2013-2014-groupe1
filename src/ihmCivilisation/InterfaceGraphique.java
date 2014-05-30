package ihmCivilisation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import systemeCivilisation.Joueur;
import systemeCivilisation.PartieDeCivilisation;
import systemeCivilisation.Position;
import systemeCivilisation.TypeUnite;

/**
 * @author Romain
 * La classe qui gere le lien entre l'interface graphique et le moteur du jeu.
 */
public class InterfaceGraphique implements Runnable, ActionListener {

	private FenetreCivilisation fenetreDeJeu;
	private PartieDeCivilisation logiqueDuJeu;

	private Position positionDeLUniteSelectionner;
	private Position positionDeLaVilleSelectionner;
	private Joueur joueurCourant;

	/**
	 * Constructeur qui initialise les priencipaux element du jeu.
	 */
	public InterfaceGraphique() {
		this.fenetreDeJeu = new FenetreCivilisation();
		this.logiqueDuJeu = new PartieDeCivilisation();
		this.positionDeLUniteSelectionner = null;
		this.positionDeLaVilleSelectionner = null;
		this.joueurCourant = this.logiqueDuJeu.obtenirJoueurDontCEstLeTour();
	}

	@Override
	public void run() {
		
		JOptionPane jPseudo1, jPseudo2;
		 
		//Boîtes des messages d'informations
		jPseudo1 = new JOptionPane();
		String pseudo1 = (String)jPseudo1.showInputDialog(null, "Pseudo du Joueur 1", "Joueur 1", JOptionPane.INFORMATION_MESSAGE);
		
		jPseudo2 = new JOptionPane();
		String pseudo2 = (String)jPseudo2.showInputDialog(null, "Pseudo du Joueur 2", "Joueur 2", JOptionPane.INFORMATION_MESSAGE);
		
		this.logiqueDuJeu.changerPseudoJoueur(pseudo1, pseudo2);
				

		this.fenetreDeJeu.initialiserFenetreCivilisation(
				this.logiqueDuJeu.obtenirCarte(), this.joueurCourant, this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		JComponent source = (JComponent) event.getSource();

		// Bouton FinirTour
		if (source.getName() == "FinirTour") {
			finirLeTour();
			joueurCourant = logiqueDuJeu.obtenirJoueurDontCEstLeTour();
			this.positionDeLaVilleSelectionner = null;
			this.positionDeLUniteSelectionner = null;
			reinitialiserLeMenu();
			return;
		}

		// Bouton AmeliorerVille
		if (source.getName() == "AmeliorerVille") {
			this.logiqueDuJeu.obtenirCarte()
					.obtenirLaVilleDeLaCase(positionDeLaVilleSelectionner)
					.ameliorer(this.joueurCourant);

			this.fenetreDeJeu.mettreAJourLeMenu(logiqueDuJeu.obtenirCarte()
					.obtenirLaVilleDeLaCase(positionDeLaVilleSelectionner),
					joueurCourant, this);

			this.positionDeLaVilleSelectionner = null;
			reinitialiserLeMenu();
			return;
		}

		// Bouton CreerUnite
		if (source.getName() == "CreerUnite") {
			this.logiqueDuJeu.ajouterUneUnite(joueurCourant, positionDeLaVilleSelectionner, TypeUnite.Soldats);
			this.logiqueDuJeu.obtenirJoueurDontCEstLeTour().modifierTresorie(-TypeUnite.Soldats.getCoutCreation());
			this.mettreAJourLaCarte();
			return;
		}
		
		
		// Bouton CreerChar
		if (source.getName() == "CreerChar") {
			this.logiqueDuJeu.ajouterUneUnite(joueurCourant, positionDeLaVilleSelectionner, TypeUnite.Chars);
			this.logiqueDuJeu.obtenirJoueurDontCEstLeTour().modifierTresorie(-TypeUnite.Chars.getCoutCreation());
			this.mettreAJourLaCarte();
			return;
		}
		
		
		
				
				
		Position positionDeLaSelection = new Position(
				((BoutonCarte) source).obtenirX(),
				((BoutonCarte) source).obtenirY());
		
		// Aucune position n'a ete selectionne
		if ((this.positionDeLUniteSelectionner == null)
				&& (this.logiqueDuJeu.obtenirCarte().laCaseNecontientPasDUnite(
						positionDeLaSelection)
						&& (this.logiqueDuJeu.obtenirCarte()
								.laCaseNeContientPasDeVille(positionDeLaSelection)) 
						&& (this.positionDeLaVilleSelectionner == null))) 
		{ return; }

		// Selection d'une ville
		if ((this.logiqueDuJeu.obtenirCarte()
				.laCaseContientUneVille(positionDeLaSelection))
				&& (this.positionDeLUniteSelectionner == null)
				&& (this.logiqueDuJeu.obtenirCarte()
						.laCaseNecontientPasDUnite(positionDeLaSelection))
				&& (this.positionDeLaVilleSelectionner == null)) {
			this.fenetreDeJeu.mettreAJourLeMenu(
					this.logiqueDuJeu.obtenirCarte().obtenirLaVilleDeLaCase(
							positionDeLaSelection), joueurCourant, this);
			this.positionDeLaVilleSelectionner = positionDeLaSelection;
			return;
		}

		// Deselection d'une ville
		if ((this.positionDeLaVilleSelectionner != null)
				|| (this.positionDeLaVilleSelectionner == positionDeLaSelection)) {
			this.positionDeLaVilleSelectionner = null;
			this.fenetreDeJeu.mettreAJourLeMenu(joueurCourant, this);
			return;
		}

		// Selection de la premiere unite.
		if ((this.positionDeLUniteSelectionner == null)) {
			this.fenetreDeJeu.mettreAJourLeMenu(this.logiqueDuJeu
					.obtenirCarte()
					.obtenirLUniteDeLaCase(positionDeLaSelection),
					joueurCourant, this);
			this.positionDeLUniteSelectionner = positionDeLaSelection;
			return;
		}

		// L'unite est selectionne et le joueur va effectuer une action.

		// Le joueur re-selectionne l'unite.
		if (this.positionDeLUniteSelectionner.equals(positionDeLaSelection)) {
			this.positionDeLUniteSelectionner = null;
			this.reinitialiserLeMenu();
			return;
		}

		// Le joueur prend une ville
		if (this.logiqueDuJeu.obtenirCarte().laCaseContientUneVille(
				positionDeLaSelection)
				&& this.logiqueDuJeu.obtenirCarte().laCaseNecontientPasDUnite(
						positionDeLaSelection))
			this.logiqueDuJeu.prendreLaVille(joueurCourant,
					positionDeLaSelection, positionDeLUniteSelectionner);

		// Le joueur deplace l'unite
		if (this.logiqueDuJeu.obtenirCarte().laCaseNecontientPasDUnite(
				positionDeLaSelection))
			this.logiqueDuJeu.deplacerUneUnite(joueurCourant,
					positionDeLUniteSelectionner, positionDeLaSelection);

		// Le joueur attaque une unite adverse.
		else
			this.logiqueDuJeu.attaquer(this.joueurCourant,
					positionDeLUniteSelectionner, positionDeLaSelection);
		this.mettreAJourLaCarte();
		this.reinitialiserLeMenu();
		this.positionDeLUniteSelectionner = null;

	}

	/**
	 * Permet de reinitialiser le menu de gauche
	 */
	private void reinitialiserLeMenu() {
		this.fenetreDeJeu.mettreAJourLeMenu(this.joueurCourant, this);
	}
	
	/**
	 * Permet de mettre a jour la carte.
	 */
	public void mettreAJourLaCarte() {
		this.fenetreDeJeu.mettreAJourLaCarte(this.logiqueDuJeu.obtenirCarte(),
				this);
	}

	/**
	 * Fini le tour en cours
	 */
	private void finirLeTour() {
		this.logiqueDuJeu.finirLeTour();
	}

}