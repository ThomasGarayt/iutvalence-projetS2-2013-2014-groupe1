package systemeCivilisation;
/**
 * @author Romain Les types d'unite disponible dans le jeu.
 */
public enum TypeUnite {

	/**
	 * Un type d'unite puissant.
	 */
	Chars(500,400,2,6,1),
	
	
	/**
	 * Un type d'unitee faible.
	 */
	Soldats(40,20,4,5,1);

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
	
	/**
	 * Permet d'obtenir les Pm d'une unité
	 * 
	 * @return int pm
	 * 			Les pm d'une unité
	 */			
	public int getPm() {
		return pm;
	}
	
	public int getPortee()
	{
		return portee;
	}
	
	public int getNiveau() {
		return niveau;
	}
	

	private int vie;
	private int puissance;
	private int pm;
	private int portee;
	private int niveau;

	
	private TypeUnite(int vie, int puissance, int pm, int portee, int niveau) {
		this.vie = vie;
		this.puissance = puissance;
		this.pm = pm;
		this.portee = portee;
		this.niveau = niveau;
	}


}
