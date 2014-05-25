package systemeCivilisation;

public class Case {
	
	private Unite unite;
	
	private Ville ville;
	
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
	
	public Unite obtenirUnite() {
		return this.unite;
	}
	
	public void ajouterUnite(Unite unite) {
		this.unite = unite;
	}
	
	public void supprimerUnite() {
		this.unite = null;
	}
	
	public Ville obtenirVille() {
		return this.ville;
	}
	
}
