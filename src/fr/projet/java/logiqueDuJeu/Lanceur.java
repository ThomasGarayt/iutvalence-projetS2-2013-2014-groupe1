package fr.projet.java.logiqueDuJeu;

import java.io.File;

import fr.projet.java.IntelligenceArtificiel.IAPrincipale;
import fr.projet.java.gestionGraphique.FenetreCivilisation;
import fr.projet.java.gestionGraphique.SetDImages;
import fr.projet.java.gestionUnite.Nation;
import fr.projet.java.menu.MenuGeneral;
import fr.projet.java.menu.InfoPreferencePartie;

import javax.swing.SwingUtilities;

/**
 * @author Romain La classe qui lance le jeu de Civilisation.
 */
public class Lanceur {

	/**
	 * Le main de l'application.
	 * 
	 * @param args
	 *            Les arguments en entree du programme. ( Ici aucun )
	 */
	public static void main(String[] args) {
		// Creation et lancement du menu, recuperation de informations sur les
		// parametre de la partie.
		MenuGeneral menu = new MenuGeneral();
		SwingUtilities.invokeLater(menu);
		InfoPreferencePartie preferencePartie = menu.obtenirInfoPartie();
		if (preferencePartie == null) return;
		String[] nomsDesJoueurs = preferencePartie.obtenirNomsDesJoueurs();
		SetDImages[] nationsDesJoueurs = preferencePartie
				.obtenirNationsAssocierAuJoueur();

		// Creation de la fenetre de Civilisation et association avec les
		// joueurs.
		FenetreCivilisation fenetreCivilisation = new FenetreCivilisation();
		Joueur[] joueurs = new Joueur[nomsDesJoueurs.length];
		for (int joueurCourant = 0; joueurCourant < nomsDesJoueurs.length; joueurCourant++)
			joueurs[joueurCourant] = fenetreCivilisation;

		// Association des nations avec les joueurs.
		Nation[] nations = new Nation[nomsDesJoueurs.length];
		for (int joueurCourant = 0; joueurCourant < nomsDesJoueurs.length; joueurCourant++)
				nations[joueurCourant] = new Nation(
						nomsDesJoueurs[joueurCourant], nationsDesJoueurs[joueurCourant]);
		
		// Creation d'un joueur IA.
		joueurs[0] = new IAPrincipale(nations[0]);

		// Recuperation du fichier de la carte.
		File fichierCarte = preferencePartie.obtenirFichierCarte();

		// Lancement de l'interface graphique.
		PartieDeCivilisation nouvellePartie = new PartieDeCivilisation(joueurs,
				nations, fenetreCivilisation, fichierCarte);
		SwingUtilities.invokeLater(fenetreCivilisation);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Lancement de la partie.
		nouvellePartie.jouer();
	}

}
