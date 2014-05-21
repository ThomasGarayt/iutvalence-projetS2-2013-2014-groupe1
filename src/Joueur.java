public class Joueur {

	public final static int NOMBRE_D_ARMEES_MAX = 0;

	int tresorerie;
	Unite[] armees;

	public Joueur() {
		this.tresorerie = 0;
		this.armees = new Unite[NOMBRE_D_ARMEES_MAX];
		for (int armeeCourante = 0; armeeCourante < NOMBRE_D_ARMEES_MAX; armeeCourante++)
			this.armees[armeeCourante] = null;
	}

	public void deplacerUnite(Position positionDeLuniteADeplacer,
			Position positionFutureDeLUnite) { //TODO Ne vérifie qu'un joueur !
		if (aUneUniteSurLaPosition(positionFutureDeLUnite))
			; // Faire remonter une erreur ?
		else {
			armees[TrouverUneUniteParLaPosition(positionDeLuniteADeplacer)]
					.deplacerUnite(positionFutureDeLUnite);
		}
	}

	
	public boolean aUneUniteSurLaPosition(Position positionATester) {
		for (int uniteCourante = 0; uniteCourante < NOMBRE_D_ARMEES_MAX; uniteCourante++)
			if (this.armees[uniteCourante].occupeLaPosition(positionATester))
				return true;
		return false;
	}
	
	public int TrouverUneUniteParLaPosition(Position positionDeLuniteADeplacer) {
		for (int uniteCourante = 0; uniteCourante < NOMBRE_D_ARMEES_MAX; uniteCourante++)
			if (this.armees[uniteCourante]
					.occupeLaPosition(positionDeLuniteADeplacer))
				return uniteCourante;

		return 0; // TODO renvoyer une erreur plutôt ( et la gérer tant qu'à faire ).
	}
}