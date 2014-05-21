/**
 * @author Romain Une unit� qui poss�de une vie, une puissance et une position
 *         sur une carte. Elle peut attaquer d'autre unit�s et se d�placer.
 */
public class Unite {

	int puissance;
	int vie;
	TypeUnite type;
	Position positionUnite;

	/**
	 * @param type
	 *            Le type d'unit� que l'on souhaite cr��, cela d�finira sa vie
	 *            et sa puissance.
	 */
	public Unite(TypeUnite type) {
		this.type = type;
		this.vie = type.getVie();
		this.puissance = type.getPuissance();
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

	/**
	 * Changer la position de l'unit�.
	 * 
	 * @param nouvellePosition
	 *            La nouvelle position de l'unit�.
	 */
	public void deplacerUnite(Position nouvellePosition) {
		// Test de la disponibilit� de la case fait dans joueur.
		this.positionUnite = nouvellePosition;
	}

	/**
	 * Teste un position et la compare � la position actuelle de l'unit�.
	 * 
	 * @param positionATester
	 *            La position � comparer avec celle de l'unit�.
	 * @return Vrai si les position sont �gale, faux sinon.
	 */
	public boolean occupeLaPosition(Position positionATester) {
		return (positionATester == this.positionUnite);
	}
}
