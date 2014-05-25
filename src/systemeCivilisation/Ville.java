package systemeCivilisation;
/**
 * @author Romain Une ville qui à un niveau et une position.
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
	public void ameliorer() {
		this.niveau++;
	}


	/**
	 * Permet d'obtenir le niveau de la ville.
	 * 
	 * @return Le niveau de la ville.
	 */
	public int obtenirNiveau() {
		return this.niveau;
	}
	
	public Joueur obtenirJoueurProprietaire() {
		return this.proprietaire;
	}
	
	public void changerProprietaire(Joueur joueurProprietaire) {
		this.proprietaire = joueurProprietaire;
	}
}
