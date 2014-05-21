public class Unite {

	int puissance;
	int vie;
	TypeUnite type;
	Position positionUnite;

	public Unite(TypeUnite type) {
		this.type = type;
		this.vie = type.getVie();
		this.puissance = type.getPuissance();
	}

	public boolean estVivante() {
		return (vie > 0);
	}

	public void changementDeVieRelatif(int vieAChanger) {
		this.vie += vieAChanger;
	}

	public void deplacerUnite(Position nouvellePosition) {
		// Test de la disponibilité de la case fait dans joueur.
		this.positionUnite = nouvellePosition;
	}

	public boolean occupeLaPosition(Position positionATester) {
		return (positionATester == this.positionUnite);
	}
}
