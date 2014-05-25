package systemeCivilisation;

public class Carte {

	public static final int NB_CASES_X = 30;
	public static final int NB_CASES_Y = 30;
	
	private Case[][] cases;
	
	public Carte() {
		cases = new Case[NB_CASES_X][NB_CASES_Y];
		for (int caseCouranteX = 0; caseCouranteX < NB_CASES_X; caseCouranteX++)
			for (int caseCouranteY = 0; caseCouranteY < NB_CASES_Y; caseCouranteY++)
				this.cases[caseCouranteX][caseCouranteY] = new Case();
	}
	
	public boolean laCaseNecontientPasDUnite(Position positionDeLaCase) {
		return !(this.cases[positionDeLaCase.positionX][positionDeLaCase.positionY].aUneUnite() );
	}
	
	public boolean laCaseNeContientPasDeVille(Position positionDeLaCase) {
		return !(this.cases[positionDeLaCase.positionX][positionDeLaCase.positionY].aUneVille() );
	}
	
	public Unite obtenirLUniteDeLaCase(Position positionDeLaCase) {
		return this.cases[positionDeLaCase.positionX][positionDeLaCase.positionY].obtenirUnite();
	}
	
	public void ajouterUneUniteALaCase(Position positionDeLUnite, Unite uniteAAjouter) {
		this.cases[positionDeLUnite.positionX][positionDeLUnite.positionY].ajouterUnite(uniteAAjouter);
	}
	
	public void supprimerUneUnite(Position positionDeLUnite) {
		this.cases[positionDeLUnite.positionX][positionDeLUnite.positionY].supprimerUnite();
	}
	
	public void deplacerUneUnite(Position positionDeDepart, Position positionDArriver ) {
		Unite uniteADeplacer = this.cases[positionDeDepart.positionX][positionDeDepart.positionY].obtenirUnite();
		
		this.cases[positionDArriver.positionX][positionDArriver.positionY].ajouterUnite(uniteADeplacer);
		this.cases[positionDeDepart.positionX][positionDeDepart.positionY].supprimerUnite();
	}
	
	public Ville obtenirLaVilleDeLaCase(Position positionDeLaVille) {
		return  this.cases[positionDeLaVille.positionX][positionDeLaVille.positionY].obtenirVille();
	}
}