package systemeCivilisation;

/**
 * @author Romain Un Joueur, il possede une tresorerie et une liste d'unite.
 */

public class Joueur {

	/**
	 * La tresorerie de depart d'un joueur.
	 */
	public final static int TRESORERIE_DE_DEPART = 1000;

	private int tresorerie;
	
	private String nom;
	
	private SetDImages setDimagesDuJoueur;

	/**
	 * Creer un joueur avec une tresorerie par defaut et une liste d'unite vide.
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