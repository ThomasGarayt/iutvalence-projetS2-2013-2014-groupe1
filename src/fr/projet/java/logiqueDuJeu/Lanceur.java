package fr.projet.java.logiqueDuJeu;

import java.io.File;

import fr.projet.java.IntelligenceArtificiel.IAPrincipale;
import fr.projet.java.gestionGraphique.FenetreCivilisation;
import fr.projet.java.gestionGraphique.SetDImages;
import fr.projet.java.gestionUnite.Nation;
import fr.projet.java.menu.InfosJoueur;
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
		InfosJoueur[] infoJoueurs = preferencePartie.obtenirInfoJoueur();
		
		FenetreCivilisation fenetreCivilisation = new FenetreCivilisation();
		
		int x =0;
		while (infoJoueurs[x] != null) {
			x++;
		}
		Nation[] nations = new Nation[infoJoueurs.length];
		for (int i = 0; i < x; i++) {
			nations[i] = new Nation(infoJoueurs[i].getNom(), infoJoueurs[i].getNation());
		}
		
		Joueur[] joueurs = new Joueur[infoJoueurs.length];
		for (int i = 0; i < x; i++) {
			if (infoJoueurs[i].getEstUneIA())
				joueurs[i] = new IAPrincipale(nations[i]);
			else
				joueurs[i] = fenetreCivilisation; 
		}
		
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
