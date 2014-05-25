public class Case {
	
	private Unite unite;
	
	//TODO Remettre en private.
	public Ville ville;
	
	public Case() {
		this.unite = null;
	}
	
	public Case(Ville ville) {
		this.unite = null;
		this.ville = ville;
	}
	
	public boolean aUneUnite() {
		return (this.unite != null);
	}
	
	public boolean aUneVille() {
		return (this.ville != null);
	}
	
	public Ville obtenirVille() {
		return this.ville;
	}
	
}
