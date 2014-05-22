/**
 * @author Romain D�crie une partie de civilisation et les interaction entre les
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
	 * Une unit� en attaque une autre.
	 * 
	 * @param positionUniteAttaquante
	 *            La position de l'unit� attaquante.
	 * @param positionUniteDefenseur
	 *            La position de l'unit� d�fenseur.
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
	 * D�placer une unit� vers une autre position.
	 * 
	 * @param positionUniteADeplacer
	 *            La position de l'unit� � d�placer.
	 * @param positionFutureUnite
	 *            La position � laquelle on souhaite d�placer l'unit�.
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
