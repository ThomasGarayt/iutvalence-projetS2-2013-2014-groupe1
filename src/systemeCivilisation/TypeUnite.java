package systemeCivilisation;
/**
 * @author Romain Les types d'unit� disponible dans le jeu.
 */
public enum TypeUnite {

	/**
	 * Un type d'unit� puissant.
	 */
	Chars(500, 400),
	/**
	 * Un type d'unit� faible.
	 */
	Soldats(40, 20);

	/**
	 * Permet d'obtenir la vie de d�part de l'unit�.
	 * 
	 * @return La vie de d�part de l'unit�.
	 */
	public int getVie() {
		return vie;
	}

	/**
	 * Permet d'obtenir la puissance de d�part de l'unit�.
	 * 
	 * @return La puissance de d�part de l'unit�.
	 */
	public int getPuissance() {
		return puissance;
	}

	private int vie;
	private int puissance;

	
	private TypeUnite(int vie, int puissance) {
		this.vie = vie;
		this.puissance = puissance;
	}
	
}
