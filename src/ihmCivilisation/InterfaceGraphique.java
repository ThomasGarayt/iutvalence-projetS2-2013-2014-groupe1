package ihmCivilisation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import systemeCivilisation.Joueur;
import systemeCivilisation.PartieDeCivilisation;
import systemeCivilisation.Position;
import systemeCivilisation.TypeUnite;

/**
 * @author Romain La classe qui gere le lien entre l'interface graphique et le
 *         moteur du jeu.
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

		// Boîtes de dialogues pour obtenir les pseudos des joueurs
		jPseudo1 = new JOptionPane();
		String pseudo1 = (String) jPseudo1.showInputDialog(null,"Pseudo du Joueur 1", "Joueur 1",JOptionPane.INFORMATION_MESSAGE);

		jPseudo2 = new JOptionPane();
		String pseudo2 = (String) jPseudo2.showInputDialog(null,"Pseudo du Joueur 2", "Joueur 2",JOptionPane.INFORMATION_MESSAGE);

		// Changement des pseudos des 2 joueurs
		this.logiqueDuJeu.changerPseudoJoueur(pseudo1, pseudo2);

		this.fenetreDeJeu.initialiserFenetreCivilisation(
				this.logiqueDuJeu.obtenirCarte(), this.joueurCourant, this);
	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent event) {

		JComponent source = (JComponent) event.getSource();

		// Bouton FinirTour
		if (source.getName() == "FinirTour") {
			finirLeTour();
			joueurCourant = logiqueDuJeu.obtenirJoueurDontCEstLeTour();
			positionDeLaVilleSelectionner = null;
			positionDeLUniteSelectionner = null;
			reinitialiserLeMenu();
			return;
		}

		// Bouton AmeliorerVille
		if (source.getName() == "AmeliorerVille") {
			logiqueDuJeu.obtenirCarte()
					.obtenirLaVilleDeLaCase(positionDeLaVilleSelectionner)
					.ameliorer(joueurCourant);

			fenetreDeJeu.mettreAJourLeMenu(logiqueDuJeu.obtenirCarte()
					.obtenirLaVilleDeLaCase(positionDeLaVilleSelectionner),
					joueurCourant, this);

			positionDeLaVilleSelectionner = null;
			reinitialiserLeMenu();
			return;
		}

		// Bouton AmeliorerUnite
		if (source.getName() == "AmeliorerUnite") {
			logiqueDuJeu.obtenirCarte()
					.obtenirLUniteDeLaCase(positionDeLUniteSelectionner)
					.upNiveau(joueurCourant);

			logiqueDuJeu.obtenirCarte()
					.obtenirLUniteDeLaCase(positionDeLUniteSelectionner)
					.ameliorerUnite();

			fenetreDeJeu.mettreAJourLeMenu(logiqueDuJeu.obtenirCarte()
					.obtenirLUniteDeLaCase(positionDeLUniteSelectionner),
					joueurCourant, this);

			positionDeLUniteSelectionner = null;
			reinitialiserLeMenu();
			return;
		}

		// Bouton CreerUnite
		if (source.getName() == "CreerUnite") {
			if (logiqueDuJeu.ajouterUneUnite(joueurCourant,
					positionDeLaVilleSelectionner, TypeUnite.Soldats)) {
				logiqueDuJeu.obtenirJoueurDontCEstLeTour().modifierTresorie(
						-TypeUnite.Soldats.getCoutCreation());
				mettreAJourLaCarte();
				logiqueDuJeu.obtenirJoueurDontCEstLeTour()
						.modifierNombreArme(1);
			}
			return;
		}

		// Bouton CreerChar
		if (source.getName() == "CreerChar") {
			if (logiqueDuJeu.ajouterUneUnite(joueurCourant,
					positionDeLaVilleSelectionner, TypeUnite.Chars)) {
				logiqueDuJeu.obtenirJoueurDontCEstLeTour().modifierTresorie(
						-TypeUnite.Chars.getCoutCreation());
				mettreAJourLaCarte();
				logiqueDuJeu.obtenirJoueurDontCEstLeTour()
						.modifierNombreArme(1);
			}
			return;
		}

		Position positionDeLaSelection = new Position(
				((BoutonCarte) source).obtenirX(),
				((BoutonCarte) source).obtenirY());

		// Aucune position n'a ete selectionne
		if ((positionDeLUniteSelectionner == null)
				&& (logiqueDuJeu.obtenirCarte().laCaseNecontientPasDUnite(
						positionDeLaSelection)
						&& (logiqueDuJeu.obtenirCarte()
								.laCaseNeContientPasDeVille(positionDeLaSelection)) && (positionDeLaVilleSelectionner == null))) {
			return;
		}

		// Selection d'une ville
		if ((logiqueDuJeu.obtenirCarte()
				.laCaseContientUneVille(positionDeLaSelection))
				&& (positionDeLUniteSelectionner == null)
				&& (logiqueDuJeu.obtenirCarte()
						.laCaseNecontientPasDUnite(positionDeLaSelection))
				&& (positionDeLaVilleSelectionner == null)) {
			fenetreDeJeu.mettreAJourLeMenu(logiqueDuJeu.obtenirCarte()
					.obtenirLaVilleDeLaCase(positionDeLaSelection),
					joueurCourant, this);
			positionDeLaVilleSelectionner = positionDeLaSelection;
			return;
		}

		// Deselection d'une ville
		if ((positionDeLaVilleSelectionner != null)
				|| (positionDeLaVilleSelectionner == positionDeLaSelection)) {
			positionDeLaVilleSelectionner = null;
			fenetreDeJeu.mettreAJourLeMenu(joueurCourant, this);
			return;
		}

		// Selection de la premiere unite.
		if ((positionDeLUniteSelectionner == null)) {
			fenetreDeJeu.mettreAJourLeMenu(this.logiqueDuJeu.obtenirCarte()
					.obtenirLUniteDeLaCase(positionDeLaSelection),
					joueurCourant, this);
			positionDeLUniteSelectionner = positionDeLaSelection;
			return;
		}

		// L'unite est selectionne et le joueur va effectuer une action.

		// Le joueur re-selectionne l'unite.
		if (positionDeLUniteSelectionner.equals(positionDeLaSelection)) {
			positionDeLUniteSelectionner = null;
			reinitialiserLeMenu();
			return;
		}

		// Le joueur prend une ville
		if (logiqueDuJeu.obtenirCarte().laCaseContientUneVille(
				positionDeLaSelection)
				&& logiqueDuJeu.obtenirCarte().laCaseNecontientPasDUnite(
						positionDeLaSelection)) 
		{
			logiqueDuJeu.obtenirJoueurDontCEstLeTour().modifierNbVille(1);
			if (logiqueDuJeu.obtenirCarte()
					.obtenirLaVilleDeLaCase(positionDeLaSelection)
					.obtenirJoueurProprietaire() == logiqueDuJeu
					.obtenirJoueurDontCEstNEstPasLeTour()) 
			{
				logiqueDuJeu.obtenirJoueurDontCEstNEstPasLeTour()
						.modifierNbVille(-1);
			}
			logiqueDuJeu.prendreLaVille(joueurCourant, positionDeLaSelection,
					positionDeLUniteSelectionner);
		}

		// Le joueur deplace l'unite
		if (logiqueDuJeu.obtenirCarte().laCaseNecontientPasDUnite(
				positionDeLaSelection))
		{
					logiqueDuJeu.deplacerUneUnite(joueurCourant,
					positionDeLUniteSelectionner, positionDeLaSelection);
		}

		// Le joueur attaque une unite adverse.
		else
		{
			logiqueDuJeu.attaquer(joueurCourant, positionDeLUniteSelectionner,
					positionDeLaSelection);
		}
		mettreAJourLaCarte();
		reinitialiserLeMenu();
		positionDeLUniteSelectionner = null;

		/* if (logiqueDuJeu.testFinPartie()) 
		{
			JOptionPane jFinPartie;
			
			// Boite de dialogue de fin de partie
			jFinPartie = new JOptionPane();
			jFinPartie.showMessageDialog(null, "Bien joué à " + this.joueurCourant, "Fin de partie",JOptionPane.INFORMATION_MESSAGE);
		} */
	}

	/**
	 * Permet de reinitialiser le menu de gauche
	 */
	private void reinitialiserLeMenu() {
		fenetreDeJeu.mettreAJourLeMenu(joueurCourant, this);
	}

	/**
	 * Permet de mettre a jour la carte.
	 */
	public void mettreAJourLaCarte() {
		fenetreDeJeu.mettreAJourLaCarte(logiqueDuJeu.obtenirCarte(), this);
	}

	/**
	 * Fini le tour en cours
	 */
	private void finirLeTour() {
		logiqueDuJeu.finirLeTour();
	}

}