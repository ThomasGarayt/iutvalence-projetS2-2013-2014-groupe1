public class Unite {

	int puissance;
	int vie;
	TypeUnite type;
	
	public Unite(TypeUnite type) {
		this.type = type;
		this.vie = type.getVie();
		this.puissance = type.getPuissance();
	}
	
	public boolean estVivante() {
		return (vie > 0);
	}
	
	public void changementDeVieRelatif(int vieAChanger){
		this.vie += vieAChanger;
	}
}
