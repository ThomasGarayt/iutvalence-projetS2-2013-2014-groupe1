package systemeCivilisation;

/**
 * @author Romain Un Joueur, il possède une trésorerie et une liste d'unité.
 */

public class Joueur {

	/**
	 * La trésorerie de départ d'un joueur.
	 */
	public final static int TRESORERIE_DE_DEPART = 1000;

	private int tresorerie;
	
	private String nom;
	
	private SetDImages setDimagesDuJoueur;

	/**
	 * Crée un joueur avec une trésorerie par défaut et une liste d'unité vide.
	 */
	public Joueur(String nom, SetDImages setDimagesDuJoueur) {
		this.nom = nom;
		this.tresorerie = TRESORERIE_DE_DEPART;
		this.setDimagesDuJoueur = setDimagesDuJoueur;
	}
	
	public String obtenirNom() {
		return this.nom;
	}
	
	public int obtenirTresorerie() {
		return this.tresorerie;
	}
	
	public SetDImages obtenirLesetDimagesDuJoueur() {
		return this.setDimagesDuJoueur;
	}
}