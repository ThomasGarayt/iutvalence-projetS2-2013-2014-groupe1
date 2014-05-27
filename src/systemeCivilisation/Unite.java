package systemeCivilisation;
/**
 * @author Romain Une unit� qui poss�de une vie, une puissance et une position
 *         sur une carte. Elle peut attaquer d'autre unit�s et se d�placer.
 */
public class Unite {

	int puissance;
	int vie;
	int pointsDeMouvements;
	TypeUnite type;
	
	Joueur joueur;

	/**
	 * @param type
	 *            Le type d'unit� que l'on souhaite cree, cela definira sa vie
	 *            et sa puissance.
	 */
	public Unite(TypeUnite type, Joueur joueur) {
		this.type = type;
		this.vie = type.getVie();
		this.puissance = type.getPuissance();
		this.joueur = joueur;
		this.pointsDeMouvements = 2;
	}

	/**
	 * L'unit� attaque une autre unite.
	 * 
	 * @param uniteAAttaquer
	 *            L'unite qui va subir les dommages.
	 */
	public void attaquer(Unite uniteAAttaquer) {
		uniteAAttaquer.changementDeVieRelatif(-puissance);
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
	 *  Permet d'obtenir le joueur d'une unité
	 * 
	 * @return joueur
	 * 			Le joueur auquel appartient l'unite
	 */
	public Joueur obtenirJoueur() {
		return this.joueur;
	}
		
	/**
	 * Permet d'obtenir la vie d'une unite
	 * 
	 * @return vie
	 * 			La vie actuelle de l'unite
	 */
	public int obtenirVie() {
		return this.vie;
	}
	
	/**
	 * Permet d'obtenier le nombre de points de mouvement d'une unité
	 * 
	 * @return pointsDeMouvements
	 * 			Les Pm d'une unité
	 */
	public int obtenirPointDeMouvements() {
		return this.pointsDeMouvements;
	}
		
	/**
	 * Permet d'obtenier le type de l'unité
	 * 
	 * @return type
	 * 			Le type de l'unité
	 */
	public TypeUnite obtenirTypeUnite() {
		return this.type;
	}
}
