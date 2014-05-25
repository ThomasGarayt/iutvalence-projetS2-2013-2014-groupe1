package systemeCivilisation;
/**
 * @author Romain Un Joueur, il possède une trésorerie et une liste d'unité.
 */

public class Joueur {

	/**
	 * La trésorerie de départ d'un joueur.
	 */
	public final static int TRESORERIE_DE_DEPART = 1000;

	int tresorerie;

	/**
	 * Crée un joueur avec une trésorerie par défaut et une liste d'unité vide.
	 */
	public Joueur() {
		this.tresorerie = TRESORERIE_DE_DEPART;
	}
	
}