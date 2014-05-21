/**
 * @author Romain Un Joueur, il possède une trésorerie et une liste d'unité.
 */
public class Joueur {

	/**
	 * Le nombre d'unité maximal que peut obtenir un joueur.
	 */
	public final static int NOMBRE_D_UNITE_MAX = 0;

	/**
	 * La trésorerie de départ d'un joueur.
	 */
	public final static int TRESORERIE_DE_DEPART = 1000;

	int tresorerie;
	Unite[] unite;

	/**
	 * Crée un joueur avec une trésorerie par défaut et une liste d'unité vide.
	 */
	public Joueur() {
		this.tresorerie = TRESORERIE_DE_DEPART;
		this.unite = new Unite[NOMBRE_D_UNITE_MAX];
		for (int armeeCourante = 0; armeeCourante < NOMBRE_D_UNITE_MAX; armeeCourante++)
			this.unite[armeeCourante] = null;
	}

	/**
	 * Permet au joueur de déplacer une de ses unités.
	 * 
	 * @param positionDeLuniteADeplacer
	 *            La position de l'unité que le joueur souhaite déplacer.
	 * @param positionFutureDeLUnite
	 *            La position à laquelle le joueur souhaite déplacer son unité.
	 */
	public void deplacerUnite(Position positionDeLuniteADeplacer,
			Position positionFutureDeLUnite) { // TODO Ne vérifie qu'un joueur !
		if (aUneUniteSurLaPosition(positionFutureDeLUnite))
			; // Faire remonter une erreur ?
		else {
			unite[TrouverUneUniteParLaPosition(positionDeLuniteADeplacer)]
					.deplacerUnite(positionFutureDeLUnite);
		}
	}

	/**
	 * Test si le joueur à une unité sur la case entrée en paramètre.
	 * 
	 * @param positionATester
	 *            La position à laquelle ont souhaite savoir si le joueur
	 *            possède une unité.
	 * @return Vrai si le joueur possède une unité sur la position. Faux sinon.
	 */
	public boolean aUneUniteSurLaPosition(Position positionATester) {
		for (int uniteCourante = 0; uniteCourante < NOMBRE_D_UNITE_MAX; uniteCourante++)
			if (this.unite[uniteCourante].occupeLaPosition(positionATester))
				return true;
		return false;
	}

	/**
	 * Sert à trouver la position dans le tableau d'unité du joueur grâce à une
	 * position.
	 * 
	 * @param positionDeLuniteATrouver
	 *            La position de l'unité que l'on souhaite trouvé.
	 * @return la position de l'unité dans le tableau d'unités du joueur.
	 */
	public int TrouverUneUniteParLaPosition(Position positionDeLuniteATrouver) {
		for (int uniteCourante = 0; uniteCourante < NOMBRE_D_UNITE_MAX; uniteCourante++)
			if (this.unite[uniteCourante]
					.occupeLaPosition(positionDeLuniteATrouver))
				return uniteCourante;

		return 0; // TODO renvoyer une erreur plutôt ( et la gérer tant qu'à
					// faire ).
	}
}