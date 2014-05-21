/**
 * @author Romain Les types d'unité disponible dans le jeu.
 */
public enum TypeUnite {

	/**
	 * Un type d'unité puissant.
	 */
	Chars(50, 40),
	/**
	 * Un type d'unité faible.
	 */
	Soldats(40, 20);

	/**
	 * Permet d'obtenir la vie de départ de l'unité.
	 * 
	 * @return La vie de départ de l'unité.
	 */
	public int getVie() {
		return vie;
	}

	/**
	 * Permet d'obtenir la puissance de départ de l'unité.
	 * 
	 * @return La puissance de départ de l'unité.
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
