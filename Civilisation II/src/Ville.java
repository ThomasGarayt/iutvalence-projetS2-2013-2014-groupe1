public class Ville {

	private int niveau;
	
	private Position position;
	
	public void ameliorer() {
		// R�duire la tr�sorerie ?
		this.niveau ++;
	}
	
	public Position obtenirPosition(){
		return this.position;
	}
}
