package ihmCivilisation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import systemeCivilisation.Joueur;
import systemeCivilisation.PartieDeCivilisation;
import systemeCivilisation.Position;

public class InterfaceGraphique implements Runnable, ActionListener {

	private FenetreCivilisation fenetreDeJeu;
	private PartieDeCivilisation logiqueDuJeu;

	private Position positionDeLUniteSelectionner;
	private Position positionDeLaVilleSelectionner;
	private Joueur joueurCourant;

	public InterfaceGraphique() {
		this.fenetreDeJeu = new FenetreCivilisation(this);
		this.logiqueDuJeu = new PartieDeCivilisation();
		this.positionDeLUniteSelectionner = null;
		this.positionDeLaVilleSelectionner = null;
		this.joueurCourant = this.logiqueDuJeu.obtenirJoueurDontCEstLeTour();
	}

	public void mettreAJourLaCarte() {
		this.fenetreDeJeu.mettreAJourLaCarte(this.logiqueDuJeu.obtenirCarte(),
				this);
	}

	@Override
	public void run() {
		this.fenetreDeJeu.initialiserFenetreCivilisation(
				this.logiqueDuJeu.obtenirCarte(), this.joueurCourant, this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		JComponent source = (JComponent) event.getSource();

		// Bouton FinirTour
		if (source.getName() == "FinirTour") {
			this.finirLeTour();
			this.joueurCourant = this.logiqueDuJeu
					.obtenirJoueurDontCEstLeTour();
			this.reinitialiserLeMenu();
			return;
		}

		// Bouton AmeliorerVille
		if (source.getName() == "AmeliorerVille") {
			this.logiqueDuJeu.obtenirCarte()
					.obtenirLaVilleDeLaCase(positionDeLaVilleSelectionner)
					.ameliorer();
			this.fenetreDeJeu.mettreAJourLeMenu(
					this.logiqueDuJeu.obtenirCarte().obtenirLaVilleDeLaCase(
							positionDeLaVilleSelectionner), this.joueurCourant,
					this);
			this.positionDeLaVilleSelectionner = null;
			this.reinitialiserLeMenu();
			return;
		}

		Position positionDeLaSelection = new Position(
				((BoutonCarte) source).obtenirX(),
				((BoutonCarte) source).obtenirY());

		// Aucune position n'a ete selectionne
		if ((this.positionDeLUniteSelectionner == null)
				&& (this.logiqueDuJeu.obtenirCarte().laCaseNecontientPasDUnite(
						positionDeLaSelection) && (this.logiqueDuJeu
						.obtenirCarte()
						.laCaseNeContientPasDeVille(positionDeLaSelection))))
			return;

		// Selection d'une ville
		if ((this.logiqueDuJeu.obtenirCarte()
				.laCaseContientUneVille(positionDeLaSelection))
				&& (this.positionDeLUniteSelectionner == null)
				&& (this.logiqueDuJeu.obtenirCarte()
						.laCaseNecontientPasDUnite(positionDeLaSelection))) {
			this.fenetreDeJeu.mettreAJourLeMenu(
					this.logiqueDuJeu.obtenirCarte().obtenirLaVilleDeLaCase(
							positionDeLaSelection), joueurCourant, this);
			this.positionDeLaVilleSelectionner = positionDeLaSelection;
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

		// Selection d'une ville
		if ((this.logiqueDuJeu.obtenirCarte()
				.laCaseContientUneVille(positionDeLaSelection))
				&& (this.positionDeLUniteSelectionner == null)) {
			this.fenetreDeJeu.mettreAJourLeMenu(
					this.logiqueDuJeu.obtenirCarte().obtenirLaVilleDeLaCase(
							positionDeLaSelection), joueurCourant, this);
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
	 * Fini le tour en cours
	 */
	private void finirLeTour() {
		this.logiqueDuJeu.finirLeTour();
	}

}