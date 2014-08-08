package fr.projet.java.gestionCarte;

/**
 * @author Romain Un type de terrain sur la carte.
 */
public enum Terrain {

	/**
	 * Une plaine dans laquelle toutes les unites peuvent marcher.
	 */
	Plaine(true),
	/**
	 * Montagne inaccessible.
	 */
	Montagne(false),
	/**
	 * Ocean inaccessible.
	 */
	Ocean(false);

	private boolean estAccessible;

	private Terrain(boolean accessibilite) {
		this.estAccessible = accessibilite;
	}

	/**
	 * L'accessibilite de la case au unite.
	 * 
	 * @return Vrai si les unite peuvent marcher sur cette carte.
	 */
	public boolean estAccessible() {
		return this.estAccessible;
	}

}
