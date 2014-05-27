package systemeCivilisation;

public class Case {
	
	private Unite unite;
	
	private Ville ville;
	
	public Case() {
		this.unite = null;
	}
	
	/**
	 * Contructeur de Case posant directement une Ville
	 * 
	 * @param ville
	 */
	public Case(Ville ville) {
		this.unite = null;
		this.ville = ville;
	}
	
	/**
	 * Fonction testant si une case a une unite
	 * 
	 * @return true si unite present
	 * 		   false si vide
	 */
	public boolean aUneUnite() {
		return (this.unite != null);
	}
	
	
	/**
	 * Fonction testan si une case a une ville
	 * 
	 * @return true si ville presente
	 * 		   false si vide
	 */
	public boolean aUneVille() {
		return (this.ville != null);
	}
	
	
	/**
	 * Fonction permettant d'obtenir l'unité présente sur une case
	 * 
	 * @return unite
	 * 			L'unite presente sur la case
	 */
	public Unite obtenirUnite() {
		return this.unite;
	}
	
	
	/**
	 * Fonction permettant d'ajouter une unite à une case
	 * 
	 * @param unite
	 * 			L'unite que l'on souhaite ajouter sur la case
	 */
	public void ajouterUnite(Unite unite) {
		this.unite = unite;
	}
	
	
	/**
	 * Fonction permettant de re-initialiser une case, en supprimant l'unite presente dessus
	 */
	public void supprimerUnite() {
		this.unite = null;
	}
	
	
	/**
	 * Fonction permettant d'ajouter une ville à une case
	 *  
	 * @param villeAAjouter
	 * 			La ville que l'on desire ajouter
	 */
	public void ajouterVille(Ville villeAAjouter) {
		this.ville = villeAAjouter;
	}
	
	
	/**
	 * Fonction permettant d'obtenir la ville presente sur une case
	 * 
	 * @return ville
	 * 			La ville presente sur la case
	 */
	public Ville obtenirVille() {
		return this.ville;
	}
	
}
