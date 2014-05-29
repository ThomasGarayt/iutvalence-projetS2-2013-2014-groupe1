package systemeCivilisation;

/**
 * @author Romain Une carte qui contient des cases.
 */
public class Carte {

	/**
	 * Le nombre de case en x de la carte.
	 */
	public static final int NB_CASES_X = 15;

	/**
	 * Le nombre de case en x de la carte.
	 */
	public static final int NB_CASES_Y = 15;

	private Case[][] cases;

	/**
	 * Une nouvelle carte vide.
	 */
	public Carte() {
		cases = new Case[NB_CASES_X][NB_CASES_Y];
		for (int caseCouranteX = 0; caseCouranteX < NB_CASES_X; caseCouranteX++)
			for (int caseCouranteY = 0; caseCouranteY < NB_CASES_Y; caseCouranteY++)
				this.cases[caseCouranteX][caseCouranteY] = new Case();
	}

	/**
	 * Permet de savoir si une case contient une unite a partir d'une position.
	 * 
	 * @param positionDeLaCase
	 *            La position de la case.
	 * @return Vrai si la case ne contient pas d'unite, faux sinon.
	 */
	public boolean laCaseNecontientPasDUnite(Position positionDeLaCase) {
		return !(this.cases[positionDeLaCase.positionX][positionDeLaCase.positionY]
				.aUneUnite());
	}

	/**
	 * Permet de savoir si une case contient une ville a partir d'une position.
	 * 
	 * @param positionDeLaCase
	 *            La position de la case.
	 * @return Vrai si la case ne contient pas de ville, faux sinon.
	 */
	public boolean laCaseNeContientPasDeVille(Position positionDeLaCase) {
		return !(this.cases[positionDeLaCase.positionX][positionDeLaCase.positionY]
				.aUneVille());
	}

	/**
	 * Permet de savoir si une case contient une ville a partir d'une position.
	 * 
	 * @param positionDeLaCase
	 *            La position de la case.
	 * @return Vrai si la case contient une ville, faux sinon.
	 */
	public boolean laCaseContientUneVille(Position positionDeLaCase) {
		return (this.cases[positionDeLaCase.positionX][positionDeLaCase.positionY]
				.aUneVille());
	}

	/**
	 * Permet d'obtenir l'unite qui occupe une case.
	 * 
	 * @param positionDeLaCase
	 *            La position de la case.
	 * @return L'unite qui occupe la case, null si il n'y a pas d'unite.
	 */
	public Unite obtenirLUniteDeLaCase(Position positionDeLaCase) {
		return this.cases[positionDeLaCase.positionX][positionDeLaCase.positionY]
				.obtenirUnite();
	}

	/**
	 * Permet d'ajouter une unite a la case.
	 * 
	 * @param positionDeLUnite
	 *            La position a laquelle on souhaite ajouter l'unite.
	 * @param uniteAAjouter
	 *            L'unite a ajouter sur la carte.
	 */
	public void ajouterUneUniteALaCase(Position positionDeLUnite,
			Unite uniteAAjouter) {
		this.cases[positionDeLUnite.positionX][positionDeLUnite.positionY]
				.ajouterUnite(uniteAAjouter);
	}

	/**
	 * Permet de supprimer une unite.
	 * 
	 * @param positionDeLUnite
	 *            La posiiton de l'unite que l'on souhaite supprimer.
	 */
	public void supprimerUneUnite(Position positionDeLUnite) {
		this.cases[positionDeLUnite.positionX][positionDeLUnite.positionY]
				.supprimerUnite();
	}

	/**
	 * Permet de deplacer une unite.
	 * 
	 * @param positionDeDepart
	 *            La case de depart de l'unite a deplacer.
	 * @param positionDArriver
	 *            La case d'arrive de l'unite a deplacer.
	 */
	public void deplacerUneUnite(Position positionDeDepart,
			Position positionDArriver) {
		Unite uniteADeplacer = this.cases[positionDeDepart.positionX][positionDeDepart.positionY]
				.obtenirUnite();

		this.cases[positionDArriver.positionX][positionDArriver.positionY]
				.ajouterUnite(uniteADeplacer);
		this.cases[positionDeDepart.positionX][positionDeDepart.positionY]
				.supprimerUnite();
	}

	/**
	 * Permet d'ajouter une ville sur la carte.
	 * 
	 * @param positionDeLaVille
	 *            La position a laquelle on souhaite ajouter la ville.
	 * @param villeAAjouter
	 *            La ville que l'on souaite ajouter.
	 */
	public void ajouterUneVille(Position positionDeLaVille, Ville villeAAjouter) {
		this.cases[positionDeLaVille.positionX][positionDeLaVille.positionY]
				.ajouterVille(villeAAjouter);
	}

	/**
	 * Permet d'obtenir une ville a partir d'une position.
	 * 
	 * @param positionDeLaVille
	 *            La position de la ville que l'on souhaite obtenir.
	 * @return La ville occupant la posiiton, null si il n'y en a pas.
	 */
	public Ville obtenirLaVilleDeLaCase(Position positionDeLaVille) {
		return this.cases[positionDeLaVille.positionX][positionDeLaVille.positionY]
				.obtenirVille();
	}
}
