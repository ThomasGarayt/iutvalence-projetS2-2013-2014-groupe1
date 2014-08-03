package fr.projet.java.gestionUnite;

/**
 * @author Romain Les types d'unite disponible dans le jeu.
 */
public enum TypeUnite {

	/**
	 * Un type d'unite puissant.
	 */
	Chars(500, 400, 1, 4, 1, 1, 700),

	/**
	 * Un type d'unitee faible.
	 */
	Soldats(50, 20, 3, 2, 1, 2, 150);

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
	 * @return int pm Les pm d'une unité
	 */
	public int getPm() {
		return pm;
	}

	/**
	 * Obtenir la portee d'une unite.
	 * 
	 * @return La portee de l'unite.
	 */
	public int getPortee() {
		return portee;
	}
	
	/**
	 * Obtenir le nombre d'attaque que peux effectuer une unité par tour
	 * 
	 * @return Le nombre d'attaque par tour d'une unité
	 */
	public int getNombreAttaqueParTour() {
		return nombreAttaqueParTour;
	}

	/**
	 * Obtenir le niveau d'une unite.
	 * 
	 * @return Le niveau de l'unite.
	 */
	public int getNiveau() {
		return niveau;
	}
	
	/**
	 * Obtenier le cout de creation d'une unite 
	 *
	 * @return Le cout de creation de l'unite
	 */
	public int getCoutCreation()
	{
		return coutCreation;
	}

	private int vie;
	private int puissance;
	private int pm;
	private int portee;
	private int niveau;
	private int nombreAttaqueParTour;
	private int coutCreation;

	private TypeUnite(int vie, int puissance, int pm, int portee, int niveau,int nombreAttaqueParTour, int coutCreation) {
		this.vie = vie;
		this.puissance = puissance;
		this.pm = pm;
		this.portee = portee;
		this.niveau = niveau;
		this.nombreAttaqueParTour = nombreAttaqueParTour;
		this.coutCreation = coutCreation;
	}

}
