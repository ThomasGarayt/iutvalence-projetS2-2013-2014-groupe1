package fr.projet.java.gestionUnite;

/**
 * @author Romain Une unité qui est défini par un type, une puissance, une vie,
 *         des points de mouvements, un niveau, un nombre d'attaque par tour et
 *         un cout de création. Elle peut attaquer d'autre unité, se déplacer et
 *         prendre des villes
 */
public class Unite {

	private int puissance;
	private int vie;
	private int pointsDeMouvements;
	private int portee;
	private int niveau;
	private int nombreAttaqueParTour;
	private int coutCreation;
	private TypeUnite type;

	private Nation joueur;

	/**
	 * Fonction permettant de creer une unite de different type, ayant des
	 * attributs de base
	 * 
	 * @param type
	 *            Le type d'unite que l'on souhaite cree
	 * @param joueur
	 *            Le joueur a qui appartient l'unite.
	 */
	public Unite(TypeUnite type, Nation joueur) {
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
		if ((this.nombreAttaqueParTour > 0)
				&& (uniteAAttaquer.obtenirJoueur() != this.joueur)) {
			uniteAAttaquer.changementDeVieRelatif(-puissance);
			this.nombreAttaqueParTour = this.nombreAttaqueParTour - 1;
		}
	}

	/**
	 * Teste si l'unite a encore de la vie.
	 * 
	 * @return Vrai si l'unite a encore de la vie, faux sinon.
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
	 * Permet d'obtenir le joueur d'une unite
	 * 
	 * @return joueur Le joueur auquel appartient l'unite
	 */
	public Nation obtenirJoueur() {
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
	 * @return puissance La puissance de l'unite
	 */
	public int obtenirPuissance() {
		return this.puissance;
	}

	/**
	 * Permet d'obtenier le nombre de points de mouvement d'une unite
	 * 
	 * @return pointsDeMouvements Les Pm d'une unite
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
	public void reinitialiserNombreAttaqueParTour() {
		this.nombreAttaqueParTour = this.type.getNombreAttaqueParTour();
	}

	/**
	 * Permet d'obtenir le type de l'unite
	 * 
	 * @return type Le type de l'unite
	 */
	public TypeUnite obtenirTypeUnite() {
		return this.type;
	}

	/**
	 * Permet d'obtenir le niveau de l'unite
	 * 
	 * @return niveau Le niveau de l'unite
	 */
	public int obtenirNiveau() {
		return this.niveau;
	}

	/**
	 * Permet d'obtenir le nombre d'attaque par tour d'une unite
	 * 
	 * @return nombreAttaqueParTour Le nombre d'attaque qu'une unite peut
	 *         effectuer
	 */
	public int obtenirNombreAttaqueParTour() {
		return this.nombreAttaqueParTour;
	}

	/**
	 * Permet d'obtenir la porte d'attaque d'une unite
	 * 
	 * @return porte La porte d'attaque de l'unite
	 */
	public int obtenirPorte() {
		return this.portee;
	}

	/**
	 * Permet d'obtenir le cout de création d'une unite
	 * 
	 * @return coutCreation Le cout de creation de l'unite
	 */
	public int obtenirCoutCreation() {
		return this.coutCreation;
	}

	/**
	 * Fonction permettant de passer au niveau superieur une unite
	 * 
	 */
	public void upNiveau() {
		if (joueur.obtenirTresorerie() >= this.coutNiveauUp()) {
			joueur.modifierTresorie(-this.coutNiveauUp());
			this.niveau++;
		}
	}

	/**
	 * Fonction permettant d'ameliorer une unité, en augmentant sa vie et sa
	 * puissance
	 * 
	 */
	public void ameliorerUnite() {
		if (this.joueur.obtenirTresorerie() >= this.coutNiveauUp()) {
			if (this.type == TypeUnite.Soldats) {
				this.vie = this.vie + 50;
				this.puissance = this.puissance + 20;
			}
			if (this.type == TypeUnite.Chars) {
				this.vie = this.vie + 200;
				this.puissance = this.puissance + 50;
			}
		}
	}

	/**
	 * Fonction permettant d'obtenir le cout pour passe au niveau supérieur sur
	 * un soldat ou un char
	 * 
	 * @return int Le cout pour level up l'unite
	 */
	public int coutNiveauUp() {
		if (this.type == TypeUnite.Soldats) { return (this.niveau + 1) * 100; }

		if (this.type == TypeUnite.Chars) { return (this.niveau + 1) * 150; }

		return 0;

	}

}
