package fr.projet.java.logiqueDuJeu;

import fr.projet.java.gestionGraphique.FenetreCivilisation;

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
		FenetreCivilisation fenetreCivilisation = new FenetreCivilisation();
		FenetreCivilisation[] joueurs = new FenetreCivilisation[2];
		joueurs[0] = fenetreCivilisation;
		joueurs[1] = fenetreCivilisation;
		
		PartieDeCivilisation nouvellePartie = new PartieDeCivilisation(joueurs, fenetreCivilisation);
		SwingUtilities.invokeLater(fenetreCivilisation);
		
		try {
			Thread.sleep(1000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		nouvellePartie.jouer();
	}

}
