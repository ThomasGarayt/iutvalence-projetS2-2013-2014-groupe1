package systemeCivilisation;

/**
 * @author Romain Une unit� qui poss�de une vie, une puissance et une
 *         position sur une carte. Elle peut attaquer d'autre unit�s et se
 *         d�placer.
 */
public class Unite {

	int puissance;
	int vie;
	int pointsDeMouvements;
	int portee;
	int niveau;
	int nombreAttaqueParTour;
	int coutCreation;
	TypeUnite type;

	Joueur joueur;

	/**
	 * @param type
	 *            Le type d'unite que l'on souhaite cree, cela definira sa vie
	 *            et sa puissance.
	 * @param joueur
	 *            Le joueur a qui appartient l'unite.
	 */
	public Unite(TypeUnite type, Joueur joueur) {
		this.type = type;
		this.vie = type.getVie();
		this.puissance = type.getPuissance();
		this.pointsDeMouvements = type.getPm();
		this.portee = type.getPortee();
		this.niveau = type.getNiveau();
		this.nombreAttaqueParTour = type.getNombreAttaqueParTour();
		this.coutCreation = type.getCoutCreation();
		this.joueur = joueur;
	}


	/**
	 * L'unite attaque une autre unite.
	 * 
	 * @param uniteAAttaquer
	 *            L'unite qui va subir les dommages.
	 */
	public void attaquer(Unite uniteAAttaquer) {
		uniteAAttaquer.changementDeVieRelatif(-puissance);
		this.nombreAttaqueParTour = this.nombreAttaqueParTour - 1;
	}

	/**
	 * Teste si l'unite a encore de la vie.
	 * 
	 * @return Vrai si l'unit� a encore de la vie, faux sinon.
	 */
	public boolean estVivante() {
		return (vie > 0);
	}

	/**
	 * Changer la vie de l'unite par rapport a sa vie actuel.
	 * 
	 * @param vieAChanger
	 *            Le nombre de point de vie a changer, il sera negatif en cas de
	 *            dommage.
	 */
	public void changementDeVieRelatif(int vieAChanger) {
		this.vie += vieAChanger;
	}

	/**
	 * Permet d'obtenir le joueur d'une unité
	 * 
	 * @return joueur Le joueur auquel appartient l'unite
	 */
	public Joueur obtenirJoueur() {
		return this.joueur;
	}

	/**
	 * Permet d'obtenir la vie d'une unite
	 * 
	 * @return vie La vie actuelle de l'unite
	 */
	public int obtenirVie() {
		return this.vie;
	}
	
	/**
	 * Permet d'obtenir la puissance d'une unite
	 * 
	 * @retur puissance La puissance de l'unite
	 */
	public int obtenirPuissance() {
		return this.puissance;
	}
	
	

	/**
	 * Permet d'obtenier le nombre de points de mouvement d'une unité
	 * 
	 * @return pointsDeMouvements Les Pm d'une unité
	 */
	public int obtenirPointDeMouvements() {
		return this.pointsDeMouvements;
	}

	/**
	 * Permet d'enlever des Points de mouvement a une unite
	 * 
	 * @param PmEnMoins
	 *            Le nombre de Pm que l'unite doit perdre
	 */
	public void mettreAJourPointDeMouvements(int PmEnMoins) {
		this.pointsDeMouvements = this.pointsDeMouvements - PmEnMoins;
	}

	/**
	 * Re-initialise tous les pm d'une unite
	 */
	public void reinitialiserPm() {
		this.pointsDeMouvements = this.type.getPm();
	}
	
	/**
	 * Re-initialise le nombre d'attaque par tour d'une unite
	 */
	public void reinitialiserNombreAttaqueParTour()
	{
		this.nombreAttaqueParTour = this.type.getNombreAttaqueParTour();
	}

	/**
	 * Permet d'obtenir le type de l'unité
	 * 
	 * @return type Le type de l'unité
	 */
	public TypeUnite obtenirTypeUnite() {
		return this.type;
	}

	/**
	 * Permet d'obtenir le niveau de l'unité
	 * 
	 * @return niveau Le niveau de l'unité
	 */
	public int obtenirNiveau() {
		return this.niveau;
	}
	
	
	/**
	 * Permet d'obtenir le nombre d'attaque par tour d'une unit�
	 * 
	 * @return nombreAttaqueParTour Le nombre d'attaque qu'une unit� peut effectuer
	 */
	public int obtenirNombreAttaqueParTour()
	{
		return this.nombreAttaqueParTour;
	}
	
	
	/**
	 * Permet d'obtenir la porte d'attaque d'une unit�
	 * 
	 * @return porte La porte d'attaque de l'unit�
	 */
	public int obtenirPorte()
	{
		return this.portee;
	}
	
	
	/**
	 * Permet d'obtenir le cout de cr�ation d'une unit�
	 * 
	 * @return coutCreation Le cout de cr�ation de l'unit�
	 */
	public int obtenirCoutCreation()
	{
		return this.coutCreation;
	}
	
	public void upNiveau(Joueur joueur) {
		if (joueur.obtenirTresorerie() >= this.coutNiveauUp()) {
			joueur.modifierTresorie(-this.coutNiveauUp());
			this.niveau++;
		}
	}
	
	public void ameliorerUnite()
	{
		if (this.type == type.Soldats)
		{
			this.vie = this.vie + 50 ;
			this.puissance = this.puissance +20;
		}
		
		if(this.type == type.Chars)
		{
			this.vie = this.vie + 200 ;
			this.puissance = this.puissance +50;
		}
	}


	public int coutNiveauUp() {
		if (this.type == type.Soldats)
		{
			return (int)((this.niveau + 1) * 100);
		}
		
		if(this.type == type.Chars)
		{
			return (int)((this.niveau + 1) * 150);
		}
		else return 0;

	}


}
