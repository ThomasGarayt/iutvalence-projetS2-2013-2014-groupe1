package fr.projet.java.logiqueDuJeu;

import fr.projet.java.gestionCarte.Position;
import fr.projet.java.gestionGraphique.FinDuTourException;

/**
 * @author Romain Regroupe les fonction de saisie des joueurs.
 */
public interface Joueur {

	/**
	 * Les joueur selectionne une position
	 * 
	 * @return La postion selectionner.
	 * @throws FinDuTourException
	 *             Le joueur termine son tour.
	 */
	Position selectionnerPosition() throws FinDuTourException;

	/**
	 * Les joueur selectionne une action sur la ville selectionner.
	 * 
	 * @return L'action selectionner.
	 * @throws FinDuTourException
	 *             Le joueur termine son tour.
	 */
	ActionVille selectionnerActionVille() throws FinDuTourException;

	/**
	 * Le jouer selectionne une action sur l'unite selectionner.
	 * 
	 * @return L'action selectionner
	 * @throws FinDuTourException
	 *             Le joueur termine son tour.
	 */
	ActionUnite selectionnerActionUnite() throws FinDuTourException;

}
