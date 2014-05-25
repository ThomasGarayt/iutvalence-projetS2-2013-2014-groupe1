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

		// TODO Vérifier si c'est le bouton "Finir le Tour" qui à été pressé.
		// Par ce que ça ça marche pas :/
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

		// Aucune unité n'a été sélectionné.
		if ((this.positionDeLUniteSelectionner == null)
				&& (this.logiqueDuJeu.obtenirCarte()
						.laCaseNecontientPasDUnite(positionDeLaSelection)))
			return;

		// Sélection de la premiere unité.
		if (this.positionDeLUniteSelectionner == null) {
			this.fenetreDeJeu.mettreAJourLeMenu(this.logiqueDuJeu
					.obtenirCarte()
					.obtenirLUniteDeLaCase(positionDeLaSelection),
					joueurCourant);
			this.positionDeLUniteSelectionner = positionDeLaSelection;
			return;
		}

		// L'unité est selectionner et le joueur va effectuer une action.

		// Le joueur re-sélectionne l'unité.
		if (this.positionDeLUniteSelectionner.equals(positionDeLaSelection)) {
			this.positionDeLUniteSelectionner = null;
			this.reinitialiserLeMenu();
			return;
		}

		// Le joueur déplace l'unité.
		if (this.logiqueDuJeu.obtenirCarte().laCaseNecontientPasDUnite(
				positionDeLaSelection))
			this.logiqueDuJeu.deplacerUneUnite(joueurCourant,
					positionDeLUniteSelectionner, positionDeLaSelection);

		// Le joueur attaque une unité adverse.
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