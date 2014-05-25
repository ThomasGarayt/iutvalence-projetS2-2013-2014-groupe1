package systemeCivilisation;
/**
 * @author Romain Un Joueur, il poss�de une tr�sorerie et une liste d'unit�.
 */

public class Joueur {

	/**
	 * La tr�sorerie de d�part d'un joueur.
	 */
	public final static int TRESORERIE_DE_DEPART = 1000;

	private int tresorerie;
	
	private String nom;

	/**
	 * Cr�e un joueur avec une tr�sorerie par d�faut et une liste d'unit� vide.
	 */
	public Joueur(String nom) {
		this.nom = nom;
		this.tresorerie = TRESORERIE_DE_DEPART;
	}
	
	public String obtenirNom() {
		return this.nom;
	}
	
	public int obtenirTresorerie() {
		return this.tresorerie;
	}
}