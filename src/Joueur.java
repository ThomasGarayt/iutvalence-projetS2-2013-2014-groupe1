/**
 * @author Romain Un Joueur, il poss�de une tr�sorerie et une liste d'unit�.
 */
public class Joueur {

	/**
	 * Le nombre d'unit� maximal que peut obtenir un joueur.
	 */
	public final static int NOMBRE_D_UNITE_MAX = 0;

	/**
	 * La tr�sorerie de d�part d'un joueur.
	 */
	public final static int TRESORERIE_DE_DEPART = 1000;

	int tresorerie;
	Unite[] unite;

	/**
	 * Cr�e un joueur avec une tr�sorerie par d�faut et une liste d'unit� vide.
	 */
	public Joueur() {
		this.tresorerie = TRESORERIE_DE_DEPART;
		this.unite = new Unite[NOMBRE_D_UNITE_MAX];
		for (int armeeCourante = 0; armeeCourante < NOMBRE_D_UNITE_MAX; armeeCourante++)
			this.unite[armeeCourante] = null;
	}

	/**
	 * Permet au joueur de d�placer une de ses unit�s. �crase les unit�s sur la
	 * case d'arriv�.
	 * 
	 * @param positionDeLuniteADeplacer
	 *            La position de l'unit� que le joueur souhaite d�placer.
	 * @param positionFutureDeLUnite
	 *            La position � laquelle le joueur souhaite d�placer son unit�.
	 */
	public void deplacerUnite(Position positionDeLuniteADeplacer,
			Position positionFutureDeLUnite) {
		unite[trouverLaPositionDUneUniteParUnePosition(positionDeLuniteADeplacer)]
				.deplacerUnite(positionFutureDeLUnite);
	}

	/**
	 * Test si le joueur � une unit� sur la case entr�e en param�tre.
	 * 
	 * @param positionATester
	 *            La position � laquelle ont souhaite savoir si le joueur
	 *            poss�de une unit�.
	 * @return Vrai si le joueur poss�de une unit� sur la position. Faux sinon.
	 */
	public boolean aUneUniteSurLaPosition(Position positionATester) {
		for (int uniteCourante = 0; uniteCourante < NOMBRE_D_UNITE_MAX; uniteCourante++)
			if (this.unite[uniteCourante].occupeLaPosition(positionATester))
				return true;
		return false;
	}

	/**
	 * Sert � trouver la position dans le tableau d'unit� du joueur gr�ce � une
	 * position.
	 * 
	 * @param positionDeLuniteATrouver
	 *            La position de l'unit� que l'on souhaite trouv�.
	 * @return la position de l'unit� dans le tableau d'unit�s du joueur.
	 */
	public int trouverLaPositionDUneUniteParUnePosition(
			Position positionDeLuniteATrouver) {
		for (int uniteCourante = 0; uniteCourante < NOMBRE_D_UNITE_MAX; uniteCourante++)
			if (this.unite[uniteCourante]
					.occupeLaPosition(positionDeLuniteATrouver))
				return uniteCourante;

		return 0; // TODO renvoyer une erreur plut�t ( et la g�rer tant qu'�
					// faire ).
	}

	/**
	 * Sert a donner la reference d'une unite appartenant au joueur gr�ce � sa
	 * position.
	 * 
	 * @param positionDeLUniteATrouver
	 *            Une position sur la grille.
	 * @return L'unit� qui occupe la position. Null si non trouv�.
	 */
	public Unite obtenirUneUniteAvecSaPosition(Position positionDeLUniteATrouver) {
		for (int uniteCourante = 0; uniteCourante < NOMBRE_D_UNITE_MAX; uniteCourante++)
			if (this.unite[uniteCourante]
					.occupeLaPosition(positionDeLUniteATrouver))
				return this.unite[uniteCourante];

		return null;
	}
}