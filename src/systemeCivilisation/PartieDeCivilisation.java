package systemeCivilisation;

/**
 * @author Romain Decrie une partie de civilisation et les interaction entre les
 *         joueurs.
 */
public class PartieDeCivilisation {

	/**
	 * Le nombre de joueur dans la partie;
	 */
	public final int NOMBRE_DE_JOUEUR = 2;

	private Joueur[] joueurs = new Joueur[NOMBRE_DE_JOUEUR];
	private Carte carte = new Carte();
	private int joueurDontCEstLeTour;

	/**
	 * Une nouvelle partie de civilisation.
	 */
	public PartieDeCivilisation() {

		joueurs[0] = new Joueur("Joueur Bleu", SetDImages.imagesBleu);
		joueurs[1] = new Joueur("Joueur Rouge", SetDImages.imagesRouges);

		Carte carteDuMonde = creationDeLaCarte();
		this.carte = carteDuMonde;

		this.joueurDontCEstLeTour = 0;
	}

	/**
	 * Fonction permettant a un joueur d'attaquer un autre joueur
	 * 
	 * 
	 * @param joueurAttaquant
	 *            Le joueur qui attaque
	 * 
	 * @param positionAttaquant
	 *            La position de l'unite attaquante
	 * 
	 * @param positionDefenseur
	 *            La position de l'unite qui defend
	 */
	public void attaquer(Joueur joueurAttaquant, Position positionAttaquant,
			Position positionDefenseur) {
		if ((this.carte.obtenirLUniteDeLaCase(positionAttaquant)
				.obtenirJoueur() == joueurAttaquant)
				&& (this.carte.obtenirLUniteDeLaCase(positionDefenseur)
						.obtenirJoueur() != this.joueurs[this.joueurDontCEstLeTour])
				&& (this.carte.obtenirLUniteDeLaCase(positionAttaquant).portee >= (positionDefenseur
						.deltaX(positionAttaquant) + positionDefenseur
						.deltaY(positionAttaquant)))
				&& (this.carte.obtenirLUniteDeLaCase(positionAttaquant).nombreAttaqueParTour > 0)) {
			this.carte.obtenirLUniteDeLaCase(positionAttaquant).attaquer(
					this.carte.obtenirLUniteDeLaCase(positionDefenseur));
			if (!(this.carte.obtenirLUniteDeLaCase(positionDefenseur)
					.estVivante()))
				this.carte.supprimerUneUnite(positionDefenseur);
		}

	}

	/**
	 * Fonction permettant de deplacer une unite
	 * 
	 * 
	 * @param joueurCourant
	 *            Le joueur courant, qui est en train de jouer
	 * 
	 * @param positionDeLUnite
	 *            La position de l'unite qui va etre deplacer
	 * 
	 * @param positionFutureDeLUnite
	 *            La position voulue pour l'unite deplace
	 */
	public void deplacerUneUnite(Joueur joueurCourant,
			Position positionDeLUnite, Position positionFutureDeLUnite) {
		if ((this.carte.obtenirLUniteDeLaCase(positionDeLUnite).obtenirJoueur() == joueurCourant)
				&& (this.carte
						.laCaseNecontientPasDUnite(positionFutureDeLUnite))
				&& (this.carte.obtenirLUniteDeLaCase(positionDeLUnite)
						.obtenirPointDeMouvements() >= ((positionDeLUnite
						.deltaX(positionFutureDeLUnite) + positionDeLUnite
						.deltaY(positionFutureDeLUnite))))) {

			this.carte.obtenirLUniteDeLaCase(positionDeLUnite)
					.mettreAJourPointDeMouvements(
							positionDeLUnite.deltaX(positionFutureDeLUnite)
									+ positionDeLUnite
											.deltaY(positionFutureDeLUnite));

			this.carte.deplacerUneUnite(positionDeLUnite,
					positionFutureDeLUnite);
		}
	}

	/**
	 * Fonction permettant de prendre une ville
	 * 
	 * @param joueurAttaquant
	 *            Le joueur attaquant la ville
	 * 
	 * @param positionVille
	 *            La position de la ville attaquee
	 * 
	 * @param positionDeLUnite
	 *            La position de l'unite qui attaque
	 */
	public void prendreLaVille(Joueur joueurAttaquant, Position positionVille,
			Position positionDeLUnite) {
		if ((this.carte.obtenirLUniteDeLaCase(positionDeLUnite).obtenirJoueur() == joueurAttaquant)
				&& !(this.carte.laCaseNeContientPasDeVille(positionVille))
				&& (this.carte.obtenirLaVilleDeLaCase(positionVille)
						.obtenirJoueurProprietaire() != joueurAttaquant)) {
			this.carte.obtenirLaVilleDeLaCase(positionVille)
					.changerProprietaire(joueurAttaquant);
		}
	}

	/**
	 * Fonction permettant d'ajouter une unite
	 * 
	 * 
	 * @param joueurConstructeur
	 *            Le joueur qui souhaite ajouter une unitee
	 * 
	 * @param positionDeLunite
	 *            La position ou l'unite va etre ajouter
	 * 
	 * @param typeDeLUnite
	 *            Le type de l'unite ajoutee
	 *            
	 * @return true si l'unite a été ajoute, false sinon
	 */
	public boolean ajouterUneUnite(Joueur joueurConstructeur,Position positionDeLunite, TypeUnite typeDeLUnite) 
	{
		if (this.carte.laCaseNecontientPasDUnite(positionDeLunite))
		{
			this.carte.ajouterUneUniteALaCase(positionDeLunite, new Unite(
					typeDeLUnite, joueurConstructeur));
			return true;
		}
		return false;

	}

	/**
	 * Fonction crÃ©ant la carte de jeu et mettant en place des unitees
	 * 
	 * @return carteDuMonde La carte de jeu
	 */
	private Carte creationDeLaCarte() {
		Carte carteDuMonde = new Carte();
		carteDuMonde.ajouterUneUniteALaCase(new Position(2, 3), new Unite(
				TypeUnite.Soldats, this.joueurs[0]));
		carteDuMonde.ajouterUneUniteALaCase(new Position(5, 7), new Unite(
				TypeUnite.Chars, this.joueurs[0]));
		carteDuMonde.ajouterUneUniteALaCase(new Position(5, 1), new Unite(
				TypeUnite.Chars, this.joueurs[1]));
		carteDuMonde.ajouterUneUniteALaCase(new Position(1, 5), new Unite(
				TypeUnite.Soldats, this.joueurs[1]));
		carteDuMonde.ajouterUneVille(new Position(6, 2), new Ville());
		carteDuMonde.ajouterUneVille(new Position(3, 3), new Ville());
		return carteDuMonde;
	}

	/**
	 * Fonction permettant de finir le tour et de passer au joueur suivant
	 * 
	 */
	public void finirLeTour() {
		reinitialiserUnite();
		miseAJourTresorerieFinTour();

		if (this.joueurDontCEstLeTour < NOMBRE_DE_JOUEUR - 1)
			this.joueurDontCEstLeTour++;
		else
			this.joueurDontCEstLeTour = 0;
	}

	private void reinitialiserUnite() {
		for (int i = 0; i < Carte.NB_CASES_X; i++)
			for (int j = 0; j < Carte.NB_CASES_Y; j++) {
				Unite uniteCourante = this.carte
						.obtenirLUniteDeLaCase(new Position(i, j));
				if ((uniteCourante != null)
						&& (uniteCourante.obtenirJoueur() == this
								.obtenirJoueurDontCEstLeTour())) {
					uniteCourante.reinitialiserPm();
					uniteCourante.reinitialiserNombreAttaqueParTour();
				}

			}
	}

	private void miseAJourTresorerieFinTour() {
		int niveauVilleJoueur = 0;
		for (int i = 0; i < Carte.NB_CASES_X; i++)
			for (int j = 0; j < Carte.NB_CASES_Y; j++) {
				Ville villeCourante = this.carte
						.obtenirLaVilleDeLaCase(new Position(i, j));
				if ((villeCourante != null)
						&& (villeCourante.obtenirJoueurProprietaire() == this
								.obtenirJoueurDontCEstLeTour())) {
					niveauVilleJoueur = niveauVilleJoueur
							+ villeCourante.obtenirNiveau();
				}
			}
		this.obtenirJoueurDontCEstLeTour().modifierTresorie(
				niveauVilleJoueur * 50);
	}
	
	/**
	 * Teste si le joueur non courant a encore des unités et villes en vie
	 * 
	 * @return true si le joueur non courant n'a plus rien, false sinon
	 */
	public boolean testFinPartie()
	{
		for (int i = 0; i < Carte.NB_CASES_X; i++)
			for (int j = 0; j < Carte.NB_CASES_Y; j++) {
				if (this.carte.laCaseContientUneVille(new Position(i,j)))
					if (this.carte.obtenirLaVilleDeLaCase(new Position(i,j)).obtenirJoueurProprietaire() == this.obtenirJoueurDontCEstLeTour())
						return false;
				if (!this.carte.laCaseNecontientPasDUnite(new Position(i,j)))
					if (this.carte.obtenirLUniteDeLaCase(new Position(i,j)).obtenirJoueur() == this.obtenirJoueurDontCEstLeTour())
						return false;
		}
		return true;
	}

	/**
	 * Fonction permettant d'obtenir le joueur courant
	 * 
	 * @return joueurDontCEstLeTour Le joueur qui est en train de jouer
	 */
	public Joueur obtenirJoueurDontCEstLeTour() {
		return this.joueurs[this.joueurDontCEstLeTour];
	}
	
	/**
	 * Fonction permettant d'obtenir le joueur qui ne joue pas
	 * 
	 * @return joueursDontCeNEstPasLeTour Le joueur qui ne joue pas
	 */
	public Joueur obtenirJoueurDontCEstNEstPasLeTour() {
		if (joueurDontCEstLeTour == 1)
			return joueurs[0];
		else
			return joueurs[1];
	}
	
	/**
	 * Retourne la carte du jeu
	 * 
	 * @return carte La carte du jeu
	 */
	public Carte obtenirCarte() {
		return this.carte;
	}
	
	public void changerPseudoJoueur(String Pseudo1, String Pseudo2)
	{
		this.joueurs[0].setNom(Pseudo1);
		this.joueurs[1].setNom(Pseudo2);
	}
}
