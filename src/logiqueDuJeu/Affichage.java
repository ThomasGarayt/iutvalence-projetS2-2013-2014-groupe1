package logiqueDuJeu;

import gestionCarte.Carte;
import gestionUnite.Nation;
import gestionUnite.Unite;
import gestionUnite.Ville;

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
}
