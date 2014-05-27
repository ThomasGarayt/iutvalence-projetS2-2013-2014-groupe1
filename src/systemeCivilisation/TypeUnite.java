package systemeCivilisation;
/**
 * @author Romain Les types d'unite disponible dans le jeu.
 */
public enum TypeUnite {

	/**
	 * Un type d'unit� puissant.
	 */
	Chars(500, 400),
	
	
	/**
	 * Un type d'unitee faible.
	 */
	Soldats(40, 20);

	/**
	 * Permet d'obtenir la vie de depart de l'unite.
	 * 
	 * @return La vie de depart de l'unite.
	 */
	public int getVie() {
		return vie;
	}

	/**
	 * Permet d'obtenir la puissance de depart de l'unite.
	 * 
	 * @return La puissance de depart de l'unite.
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
