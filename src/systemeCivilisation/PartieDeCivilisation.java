package systemeCivilisation;

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
	Carte carte = new Carte();

	public PartieDeCivilisation() {
		for (int joueurCourant = 0; joueurCourant < NOMBRE_DE_JOUEUR; joueurCourant++)
			joueurs[joueurCourant] = new Joueur();
		// Carte
		Carte carteDuMonde = new Carte();
		carteDuMonde.ajouterUneUniteALaCase(new Position(2, 3), new Unite(
				TypeUnite.Soldats, null));
		carteDuMonde.ajouterUneUniteALaCase(new Position(5, 7), new Unite(
				TypeUnite.Soldats, null));
		carteDuMonde.ajouterUneUniteALaCase(new Position(5, 1), new Unite(
				TypeUnite.Soldats, null));
		carteDuMonde.ajouterUneUniteALaCase(new Position(1, 5), new Unite(
				TypeUnite.Soldats, null));
		this.carte = carteDuMonde;
	}

	public void attaquer(Joueur joueurAttaquant, Position positionAttaquant,
			Position positionDefenseur) {
		if (this.carte.obtenirLUniteDeLaCase(positionAttaquant).obtenirJoueur() == joueurAttaquant) {
			this.carte.obtenirLUniteDeLaCase(positionAttaquant).attaquer(
					this.carte.obtenirLUniteDeLaCase(positionDefenseur));
			if (!(this.carte.obtenirLUniteDeLaCase(positionDefenseur)
					.estVivante()))
				this.carte.supprimerUneUnite(positionDefenseur);
		}

	}

	public void deplacerUneUnite(Joueur joueurCourant,
			Position positionDeLUnite, Position positionFutureDeLUnite) {
		if ((this.carte.obtenirLUniteDeLaCase(positionDeLUnite).obtenirJoueur() == joueurCourant)
				&& (this.carte
						.laCaseNecontientPasDUnite(positionFutureDeLUnite)))
			this.carte.deplacerUneUnite(positionDeLUnite,
					positionFutureDeLUnite);
	}

	public void prendreLaVille(Joueur joueurAttaquant, Position positionVille,
			Position positionDeLUnite) {
		if ((this.carte.obtenirLUniteDeLaCase(positionDeLUnite).obtenirJoueur() == joueurAttaquant)
				&& !(this.carte.laCaseNeContientPasDeVille(positionVille))
				&& (this.carte.obtenirLaVilleDeLaCase(positionVille)
						.obtenirJoueurProprietaire() == joueurAttaquant))
			this.carte.obtenirLaVilleDeLaCase(positionVille)
					.changerProprietaire(joueurAttaquant);
	}

	public void ajouterUneUnite(Joueur joueurConstructeur,
			Position positionDeLunite, TypeUnite typeDeLUnite) {
		if (this.carte.laCaseNecontientPasDUnite(positionDeLunite))
			this.carte.ajouterUneUniteALaCase(positionDeLunite, new Unite(
					typeDeLUnite, joueurConstructeur));
	}

	public Carte obtenirCarte() {
		return this.carte;
	}
}
