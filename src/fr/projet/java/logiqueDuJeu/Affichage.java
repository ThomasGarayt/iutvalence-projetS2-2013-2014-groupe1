package fr.projet.java.logiqueDuJeu;

import fr.projet.java.gestionCarte.Carte;
import fr.projet.java.gestionUnite.Chemin;
import fr.projet.java.gestionUnite.Nation;
import fr.projet.java.gestionUnite.Unite;
import fr.projet.java.gestionUnite.Ville;

/**
 * @author Romain Regroupe les fonctions d'affichage des informations a l'ecran.
 * 
 */
public interface Affichage {

	/**
	 * Met a jour la carte
	 * 
	 * @param carte
	 *            La carte a afficher
	 */
	public void mettreAJourLaCarte(Carte carte);

	/**
	 * Met a jour le menu avec une unite.
	 * 
	 * @param unite
	 *            L'unite a afficher dans le menu
	 * @param joueur
	 *            Le joueur courant
	 */
	public void mettreAJourLeMenu(Unite unite, Nation joueur);

	/**
	 * Met a jour le menu avec une ville.
	 * 
	 * @param ville
	 *            La ville a afficher dans le menu
	 * @param joueur
	 *            Le joueur courant
	 */
	public void mettreAJourLeMenu(Ville ville, Nation joueur);

	/**
	 * Met a jour le menu sans que rien ne soit selectionner.
	 * 
	 * @param joueur
	 *            Le joueur courant
	 */
	public void mettreAJourLeMenu(Nation joueur);
	
	/**
	 * Met a jour la carte avec un chemin a afficher.
	 * @param cheminAAfficher Le chemin a afficher.
	 */
	public void afficherUnChemin(Chemin cheminAAfficher);
}
