package systemeCivilisation;
/**
 * @author Romain Une unité qui possède une vie, une puissance et une position
 *         sur une carte. Elle peut attaquer d'autre unités et se déplacer.
 */
public class Unite {

	int puissance;
	int vie;
	int pointsDeMouvements;
	TypeUnite type;
	
	Joueur joueur;

	/**
	 * @param type
	 *            Le type d'unité que l'on souhaite créé, cela définira sa vie
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
	 * L'unité attaque une autre unité.
	 * 
	 * @param uniteAAttaquer
	 *            L'unité qui va subir les dommages.
	 */
	public void attaquer(Unite uniteAAttaquer) {
		uniteAAttaquer.changementDeVieRelatif(-puissance);
	}

	/**
	 * Si l'unité a encore de la vie.
	 * 
	 * @return Vrai si l'unité a encore de la vie, faux sinon.
	 */
	public boolean estVivante() {
		return (vie > 0);
	}

	/**
	 * Changer la vie de l'unité par rapport à sa vie actuel.
	 * 
	 * @param vieAChanger
	 *            Le nombre de point de vie à changer, il sera négatif en cas de
	 *            dommage.
	 */
	public void changementDeVieRelatif(int vieAChanger) {
		this.vie += vieAChanger;
	}
	
	public Joueur obtenirJoueur() {
		return this.joueur;
	}
	
	public int obtenirVie() {
		return this.vie;
	}
	
	public int obtenirPointDeMouvements() {
		return this.pointsDeMouvements;
	}
}
