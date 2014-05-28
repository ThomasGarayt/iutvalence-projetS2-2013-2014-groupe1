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
	
	/**
	 * Le nom du joueur
	 */
	private String nom;
	
	
	/**
	 * Le set d'image associé au joueur (en fonction de sa couleur)
	 */
	private SetDImages setDimagesDuJoueur;

	/**
	 * Creer un joueur avec une tresorerie par defaut et une liste d'unite vide.
	 */
	public Joueur(String nom, SetDImages setDimagesDuJoueur) {
		this.nom = nom;
		this.tresorerie = TRESORERIE_DE_DEPART;
		this.setDimagesDuJoueur = setDimagesDuJoueur;
	}

	/**
	 * Fonction qui permet d'obtenir le nom d'un joueur
	 * 
	 * @return nom
	 * 			Le nom du joueur
	 */
	public String obtenirNom() {
		return this.nom;
	}
	
	
	/**
	 * Fonction qui permet d'obtenir la tresorerie d'un joueur
	 * 
	 * @return tresorerie
	 * 			La tresorerie du joueur
	 */
	public int obtenirTresorerie() {
		return this.tresorerie;
	}
	
	public void modifierTresorie(int diffTresorerie)
	{
		this.tresorerie = this.tresorerie + diffTresorerie;
	}
	
	/**
	 * Fonction qui permet d'obtenir le set d'image d'un joueur
	 * 
	 * @return setDimagesDuJoueur
	 * 			Le set d'imae associé au joueur
	 */
	public SetDImages obtenirLesetDimagesDuJoueur() {
		return this.setDimagesDuJoueur;
	}
}