package systemeCivilisation;
/**
 * @author Romain Les types d'unite disponible dans le jeu.
 */
public enum TypeUnite {

	/**
	 * Un type d'unit� puissant.
	 */
	Chars(500, 400,2,6),
	
	
	/**
	 * Un type d'unitee faible.
	 */
	Soldats(40, 20,4,5);

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
	
	public int getPm() {
		return pm;
	}
	
	public int getPortee()
	{
		return portee;
	}

	private int vie;
	private int puissance;
	private int pm;
	private int portee;

	
	private TypeUnite(int vie, int puissance, int pm, int portee) {
		this.vie = vie;
		this.puissance = puissance;
		this.pm = pm;
		this.portee = portee;
	}
	
}
