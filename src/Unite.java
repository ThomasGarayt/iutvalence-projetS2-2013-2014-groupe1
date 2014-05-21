/**
 * @author Romain Une unité qui possède une vie, une puissance et une position
 *         sur une carte. Elle peut attaquer d'autre unités et se déplacer.
 */
public class Unite {

	int puissance;
	int vie;
	TypeUnite type;
	Position positionUnite;

	/**
	 * @param type
	 *            Le type d'unité que l'on souhaite créé, cela définira sa vie
	 *            et sa puissance.
	 */
	public Unite(TypeUnite type) {
		this.type = type;
		this.vie = type.getVie();
		this.puissance = type.getPuissance();
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

	/**
	 * Changer la position de l'unité.
	 * 
	 * @param nouvellePosition
	 *            La nouvelle position de l'unité.
	 */
	public void deplacerUnite(Position nouvellePosition) {
		// Test de la disponibilité de la case fait dans joueur.
		this.positionUnite = nouvellePosition;
	}

	/**
	 * Teste un position et la compare à la position actuelle de l'unité.
	 * 
	 * @param positionATester
	 *            La position à comparer avec celle de l'unité.
	 * @return Vrai si les position sont égale, faux sinon.
	 */
	public boolean occupeLaPosition(Position positionATester) {
		return (positionATester == this.positionUnite);
	}
}
