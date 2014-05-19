public class Ville {

	private int niveau;
	
	private Position position;
	
	public void ameliorer() {
		// Réduire la trésorerie ?
		this.niveau ++;
	}
	
	public Position obtenirPosition(){
		return this.position;
	}
}
