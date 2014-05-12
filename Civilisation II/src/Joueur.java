
public class Joueur {

	public final static int NOMBRE_D_ARMEES_MAX = 0;
	
	int tresorerie;
	Armee[] armees;
	
	
	Joueur() {
		this.tresorerie = 0;
		this.armees = new Armee[NOMBRE_D_ARMEES_MAX];
		for (int armeeCourante = 0; armeeCourante < NOMBRE_D_ARMEES_MAX; armeeCourante++)
			this.armees[armeeCourante] = null;
	}
}
