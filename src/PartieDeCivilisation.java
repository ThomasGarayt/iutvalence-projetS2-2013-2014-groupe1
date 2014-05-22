/**
 * @author Romain Décrie une partie de civilisation et les interaction entre les
 *         joueurs.
 */
public class PartieDeCivilisation {

	/**
	 * Le nombre de joueur dans la partie;
	 */
	public final int NOMBRE_DE_JOUEUR = 2;

	Joueur[] joueurs = new Joueur[NOMBRE_DE_JOUEUR];

	/**
	 * 
	 */
	public PartieDeCivilisation() {
		for (int joueurCourant = 0; joueurCourant < NOMBRE_DE_JOUEUR; joueurCourant++)
			joueurs[joueurCourant] = new Joueur();
	}

	/**
	 * Une unité en attaque une autre.
	 * 
	 * @param positionUniteAttaquante
	 *            La position de l'unité attaquante.
	 * @param positionUniteDefenseur
	 *            La position de l'unité défenseur.
	 */
	public void attaquer(Position positionUniteAttaquante,
			Position positionUniteDefenseur) {
		Unite uniteAttaquante = null, uniteDefenseur = null;

		for (int joueurCourant = 0; joueurCourant < NOMBRE_DE_JOUEUR; joueurCourant++) {
			if (joueurs[joueurCourant]
					.aUneUniteSurLaPosition(positionUniteDefenseur))
				uniteDefenseur = joueurs[joueurCourant]
						.obtenirUneUniteAvecSaPosition(positionUniteDefenseur);
			if (joueurs[joueurCourant]
					.aUneUniteSurLaPosition(positionUniteAttaquante))
				uniteAttaquante = joueurs[joueurCourant]
						.obtenirUneUniteAvecSaPosition(positionUniteAttaquante);
		}
		uniteAttaquante.attaquer(uniteDefenseur);
	}

	/**
	 * Déplacer une unité vers une autre position.
	 * 
	 * @param positionUniteADeplacer
	 *            La position de l'unité à déplacer.
	 * @param positionFutureUnite
	 *            La position à laquelle on souhaite déplacer l'unité.
	 */
	public void deplacer(Position positionUniteADeplacer,
			Position positionFutureUnite) {

		Unite uniteADeplacer = null;
		for (int joueurCourant = 0; joueurCourant < NOMBRE_DE_JOUEUR; joueurCourant++) {
			if (this.joueurs[joueurCourant]
					.aUneUniteSurLaPosition(positionUniteADeplacer))
				uniteADeplacer = this.joueurs[joueurCourant]
						.obtenirUneUniteAvecSaPosition(positionUniteADeplacer);
		}

		for (int joueurCourant = 0; joueurCourant < NOMBRE_DE_JOUEUR; joueurCourant++)
			if (this.joueurs[joueurCourant]
					.aUneUniteSurLaPosition(positionFutureUnite))
				return;

		uniteADeplacer.deplacerUnite(positionFutureUnite);
	}
}
