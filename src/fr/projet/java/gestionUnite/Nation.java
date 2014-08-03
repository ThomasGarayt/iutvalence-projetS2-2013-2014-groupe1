package fr.projet.java.gestionUnite;

/**
 * @author Romain Une nation commandé par un joueur.
 * 
 */
public class Nation {

	/**
	 * La tresorerie de depart d'un joueur.
	 */
	public final static int TRESORERIE_DE_DEPART = 1000;

	private int tresorerie;

	/**
	 * Le nom de la nation.
	 */
	private String nom;

	/**
	 * Le set d'image associe a la nation (en fonction de sa couleur)
	 */
	private SetDImages setDimagesDeLaNation;

	/**
	 * Creer une nation avec une tresorerie par defaut et une liste d'unite vide.
	 * 
	 * @param nom
	 *            Le nom de la nation.
	 * @param setDimagesDeLaNation
	 *            Le set d'images qui definit l'images des unite de la nation.
	 */
	public Nation(String nom, SetDImages setDimagesDeLaNation) {
		this.nom = nom;
		this.tresorerie = TRESORERIE_DE_DEPART;
		this.setDimagesDeLaNation = setDimagesDeLaNation;
	}

	/**
	 * Fonction qui permet d'obtenir le nom de la nation.
	 * 
	 * @return nom Le nom de la nation.
	 */
	public String obtenirNom() {
		return this.nom;
	}

	/**
	 * Donner le nom de la nation.
	 * 
	 * @param nom
	 *            Le nom de la nation.
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Fonction qui permet d'obtenir la tresorerie de la nation.
	 * 
	 * @return tresorerie La tresorerie de la nation.
	 */
	public int obtenirTresorerie() {
		return this.tresorerie;
	}

	/**
	 * Permet de modifier la tresorerie de la nation.
	 * 
	 * @param diffTresorerie
	 *            Le montant a crediter ou a debiter sur la tresorerie de la nation.
	 */
	public void modifierTresorie(int diffTresorerie) {
		this.tresorerie += diffTresorerie;
	}

	/**
	 * Fonction qui permet d'obtenir le set d'image de la nation.
	 * 
	 * @return setDimagesDuJoueur Le set d'imae associe à la nation.
	 */
	public SetDImages obtenirLesetDimagesDeLaNation() {
		return this.setDimagesDeLaNation;
	}

}
