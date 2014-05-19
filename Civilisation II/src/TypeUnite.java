
public enum TypeUnite {

	Chars(50,40),
	Soldats(40,20);
	
	public int getVie() {
		return vie;
	}

	public int getPuissance() {
		return puissance;
	}

	private int vie;
	private int puissance;
	
	private TypeUnite(int vie,int puissance){
		this.vie = vie;
		this.puissance = puissance;
	}
}
