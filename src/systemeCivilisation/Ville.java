package systemeCivilisation;
/**
 * @author Romain Une ville qui � un niveau et une position.
 */
public class Ville {

	private int niveau;
	
	private Joueur proprietaire;

	public Ville() {
		this.niveau = 1;
		this.proprietaire = null;
	}

	/**
	 * Augmenter le niveau de la ville de 1 point.
	 */
	public void ameliorer(Joueur joueur) {
		if (joueur.obtenirTresorerie() >= this.coutNiveauUp() ) {
			joueur.modifierTresorie(-this.coutNiveauUp());
			this.niveau++;
		}
	}


	/**
	 * Permet d'obtenir le niveau de la ville.
	 * 
	 * @return Le niveau de la ville.
	 */
	public int obtenirNiveau() {
		return this.niveau;
	}
	
	public int coutNiveauUp()
	{
		return ((this.niveau+1)*200);
	}
	
	
	/**
	 * Permet d'obtenir le joueur proprietaire de la ville
	 * 
	 * @return proprietaire
	 * 			Le joueur auquel la ville appartient
	 */
	public Joueur obtenirJoueurProprietaire() {
		return this.proprietaire;
	}
	
	/**
	 * Permet de changer la proprietaire d'une ville
	 * 
	 * @param joueurProprietaire
	 * 			Le nouveau joueur qui possede la ville
	 */
	public void changerProprietaire(Joueur joueurProprietaire) {
		this.proprietaire = joueurProprietaire;
	}
}
