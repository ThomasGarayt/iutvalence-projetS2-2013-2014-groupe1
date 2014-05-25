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
	private Joueur joueurCourant;

	public InterfaceGraphique() {
		this.fenetreDeJeu = new FenetreCivilisation(this);
		this.logiqueDuJeu = new PartieDeCivilisation();
		this.positionDeLUniteSelectionner = null;
		this.joueurCourant = this.logiqueDuJeu.obtenirJoueurDontCEstLeTour();
	}

	public void mettreAJourLaCarte() {
		this.fenetreDeJeu.mettreAJourLaCarte(this.logiqueDuJeu.obtenirCarte());
	}

	@Override
	public void run() {
		this.fenetreDeJeu.initialiserFenetreCivilisation(
				this.logiqueDuJeu.obtenirCarte(), this.joueurCourant);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		JComponent source = (JComponent) event.getSource();

		// TODO V�rifier si c'est le bouton "Finir le Tour" qui � �t� press�.
		// Par ce que �a �a marche pas :/
		if (source.getParent() == this.fenetreDeJeu.obtenirMenu()) {
			this.finirLeTour();
			this.joueurCourant = this.logiqueDuJeu
					.obtenirJoueurDontCEstLeTour();
			this.reinitialiserLeMenu();
			return;
		}

		Position positionDeLaSelection = new Position(
				((BoutonCarte) source).obtenirX(),
				((BoutonCarte) source).obtenirY());

		// Aucune unit� n'a �t� s�lectionn�.
		if ((this.positionDeLUniteSelectionner == null)
				&& (this.logiqueDuJeu.obtenirCarte()
						.laCaseNecontientPasDUnite(positionDeLaSelection)))
			return;

		// S�lection de la premiere unit�.
		if (this.positionDeLUniteSelectionner == null) {
			this.fenetreDeJeu.mettreAJourLeMenu(this.logiqueDuJeu
					.obtenirCarte()
					.obtenirLUniteDeLaCase(positionDeLaSelection),
					joueurCourant);
			this.positionDeLUniteSelectionner = positionDeLaSelection;
			return;
		}

		// L'unit� est selectionner et le joueur va effectuer une action.

		// Le joueur re-s�lectionne l'unit�.
		if (this.positionDeLUniteSelectionner.equals(positionDeLaSelection)) {
			this.positionDeLUniteSelectionner = null;
			this.reinitialiserLeMenu();
			return;
		}

		// Le joueur d�place l'unit�.
		if (this.logiqueDuJeu.obtenirCarte().laCaseNecontientPasDUnite(
				positionDeLaSelection))
			this.logiqueDuJeu.deplacerUneUnite(joueurCourant,
					positionDeLUniteSelectionner, positionDeLaSelection);

		// Le joueur attaque une unit� adverse.
		else
			this.logiqueDuJeu.attaquer(this.joueurCourant,
					positionDeLUniteSelectionner, positionDeLaSelection);
		this.mettreAJourLaCarte();
		this.reinitialiserLeMenu();
		this.positionDeLUniteSelectionner = null;

	}

	private void reinitialiserLeMenu() {
		this.fenetreDeJeu.mettreAJourLeMenu(this.joueurCourant);
	}

	private void finirLeTour() {
		this.logiqueDuJeu.finirLeTour();
	}

}