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
	 *            Le type d'unit� que l'on souhaite cr��, cela d�finira sa vie
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
	 * L'unit� attaque une autre unit�.
	 * 
	 * @param uniteAAttaquer
	 *            L'unit� qui va subir les dommages.
	 */
	public void attaquer(Unite uniteAAttaquer) {
		uniteAAttaquer.changementDeVieRelatif(-puissance);
	}

	/**
	 * Si l'unit� a encore de la vie.
	 * 
	 * @return Vrai si l'unit� a encore de la vie, faux sinon.
	 */
	public boolean estVivante() {
		return (vie > 0);
	}

	/**
	 * Changer la vie de l'unit� par rapport � sa vie actuel.
	 * 
	 * @param vieAChanger
	 *            Le nombre de point de vie � changer, il sera n�gatif en cas de
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
