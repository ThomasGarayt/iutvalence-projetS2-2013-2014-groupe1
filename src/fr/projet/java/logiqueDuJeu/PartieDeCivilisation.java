package fr.projet.java.logiqueDuJeu;

import fr.projet.java.exception.CheminImpossibleException;
import fr.projet.java.gestionCarte.Carte;
import fr.projet.java.gestionCarte.Position;
import fr.projet.java.gestionGraphique.FinDuTourException;
import fr.projet.java.gestionUnite.Nation;
import fr.projet.java.gestionUnite.SetDImages;
import fr.projet.java.gestionUnite.TypeUnite;
import fr.projet.java.gestionUnite.Unite;
import fr.projet.java.gestionUnite.Ville;

/**
 * @author Romain Décrie la partie logique du déroulement de la partie.
 * 
 */
public class PartieDeCivilisation {

	private Joueur[] joueurs;
	private Affichage affichage;
	private Carte carte;

	private int tour = 0;
	private Nation[] nations;

	/**
	 * Creer une nouvelle partie de civilisation.
	 * 
	 * @param joueurs
	 *            Le joueur de la partie.
	 * @param affichage
	 *            Les fonctions permettant l'affichage.
	 */
	public PartieDeCivilisation(Joueur[] joueurs, Affichage affichage) {
		this.affichage = affichage;
		this.joueurs = joueurs;

		this.nations = new Nation[2];
		this.nations[0] = new Nation("Hello", SetDImages.imagesBleu);
		this.nations[1] = new Nation("Bonjour", SetDImages.imagesRouges);

		this.carte = creationDeLaCarte();

		this.tour = 0;
	}

	/**
	 * Juoer la partie.
	 */
	public void jouer() {
		while (true) {
			this.tour += 1;

			affichage.mettreAJourLesUnites(carte);
			affichage.mettreAJourLeTerrain(carte);
			affichage.mettreAJourLeMenu(nations[this.tour % joueurs.length]);

			jouerUnTour(joueurs[this.tour % joueurs.length], nations[this.tour
					% joueurs.length]);
			finirLeTour(nations[this.tour % joueurs.length]);
			System.out.println("Fin du tour " + this.tour);
		}
	}

	/**
	 * Un joueur joue un tour.
	 * 
	 * @param joueur
	 *            Le joueur.
	 * @param nation
	 *            La nation associer au joueur.
	 */
	public void jouerUnTour(Joueur joueur, Nation nation) {
		Position positionChoisi;
		Position deuxiemePositionChoisie;
		Ville ville;
		Unite unite;

		while (true) {
			try {
				
				affichage.afficherUnChemin(null, 0);
				positionChoisi = joueur.selectionnerPosition();

				System.out.println("Tu as choisi une position : "
						+ positionChoisi);
				if (carte.laCaseContientUneUnite(positionChoisi)) {
					System.out.println("C'est une unité.");
					unite = carte.obtenirLUniteDeLaCase(positionChoisi);

					affichage.mettreAJourLeMenu(unite, nation);

					switch (joueur.selectionnerActionUnite()) {
					case Ameliorer:
						if (unite.obtenirJoueur() != nation)
							break;
						System.out.println("Tu as amélioré l'unité.");
						unite.ameliorerUnite();
						break;
					case Deplacer:
						deuxiemePositionChoisie = joueur.selectionnerPosition();
						if (unite.obtenirJoueur() != nation)
							break;
						if (carte
								.laCaseContientUneUnite(deuxiemePositionChoisie)) {
							if (deuxiemePositionChoisie.equals(positionChoisi))
								break;
							System.out.println("Tu as attaqué une unité.");
							carte.attaquerDesUnite(positionChoisi,
									deuxiemePositionChoisie);
						}
						else {
							try {
								affichage.afficherUnChemin(carte
										.obtenirUnChemin(positionChoisi,
												deuxiemePositionChoisie), unite.obtenirPointDeMouvements());
							}
							catch (CheminImpossibleException e) {
							}
							
							if (joueur.selectionnerPosition().equals(
									deuxiemePositionChoisie)) {
								System.out.println("Tu as déplacé l'unité.");
								carte.deplacerUneUnite(positionChoisi,
										deuxiemePositionChoisie);
							}
							affichage.mettreAJourLeTerrain(carte);
						}
						break;
					case Deselectionner:
						System.out.println("Tu as déselectionné l'unité.");
					}
					affichage.mettreAJourLeMenu(nation);
				}
				else if (carte.laCaseContientUneVille(positionChoisi)) {
					System.out.println("C'est une ville.");
					ville = carte.obtenirLaVilleDeLaCase(positionChoisi);

					affichage.mettreAJourLeMenu(ville, nation);

					switch (joueur.selectionnerActionVille()) {
					case Ameliorer:
						System.out.println("Tu as amélioré la ville.");
						ville.ameliorer(nation);
						break;
					case CreerUnite:
						System.out.println("Tu as crée une unité.");
						carte.ajouterUneUniteALaCase(positionChoisi, new Unite(
								TypeUnite.Soldats, nation));
						break;
					case Deselectionner:
						affichage.mettreAJourLeMenu(nation);
						System.out.println("Tu as désélectionné la ville.");
					}
					affichage.mettreAJourLeMenu(nation);

				}
				else {
					affichage.mettreAJourLeMenu(nation);
				}
				affichage.mettreAJourLesUnites(carte);
			}
			catch (FinDuTourException e) {
				break;
			}
		}
	}

	private void finirLeTour(Nation nation) {
		reinitialiserUnite(nation);
		miseAJourTresorerieFinTour(nation);
	}

	private void reinitialiserUnite(Nation nation) {
		for (int i = 0; i < Carte.NB_CASES_X; i++)
			for (int j = 0; j < Carte.NB_CASES_Y; j++) {
				Unite uniteCourante = this.carte
						.obtenirLUniteDeLaCase(new Position(i, j));
				if ((uniteCourante != null)
						&& (uniteCourante.obtenirJoueur() == nation)) {
					uniteCourante.reinitialiserPm();
					uniteCourante.reinitialiserNombreAttaqueParTour();
				}

			}
	}

	/**
	 * Fonction mettant a jour la tresorerie du joueur qui vient de jouer en
	 * fonction du nombre de ville possedee
	 * 
	 */
	private void miseAJourTresorerieFinTour(Nation nation) {
		int niveauVilleJoueur = 0;
		for (int i = 0; i < Carte.NB_CASES_X; i++)
			for (int j = 0; j < Carte.NB_CASES_Y; j++) {
				Ville villeCourante = this.carte
						.obtenirLaVilleDeLaCase(new Position(i, j));
				if ((villeCourante != null)
						&& (villeCourante.obtenirJoueurProprietaire() == nation)) {
					niveauVilleJoueur = niveauVilleJoueur
							+ villeCourante.obtenirNiveau();
				}
			}
		nation.modifierTresorie(niveauVilleJoueur * 50);
	}

	/**
	 * Fonction creant la carte de jeu et mettant en place des unitees
	 * 
	 * @return carteDuMonde La carte de jeu
	 */
	private Carte creationDeLaCarte() {
		Carte carteDuMonde = new Carte();

		// Armee de depart du Joueur Bleu
		carteDuMonde.ajouterUneUniteALaCase(new Position(2, 3), new Unite(
				TypeUnite.Soldats, this.nations[0]));
		carteDuMonde.ajouterUneUniteALaCase(new Position(12, 13), new Unite(
				TypeUnite.Soldats, this.nations[0]));

		// Armee de depart du Joueur Rouge
		carteDuMonde.ajouterUneUniteALaCase(new Position(1, 2), new Unite(
				TypeUnite.Soldats, this.nations[1]));
		carteDuMonde.ajouterUneUniteALaCase(new Position(2, 1), new Unite(
				TypeUnite.Soldats, this.nations[1]));

		// Placement des villes
		carteDuMonde.ajouterUneVille(new Position(13, 13), new Ville());
		carteDuMonde.ajouterUneVille(new Position(1, 1), new Ville());

		carteDuMonde.ajouterUneVille(new Position(6, 5), new Ville());
		carteDuMonde.ajouterUneVille(new Position(8, 8), new Ville());

		carteDuMonde.ajouterUneVille(new Position(12, 1), new Ville());
		carteDuMonde.ajouterUneVille(new Position(2, 13), new Ville());

		return carteDuMonde;
	}
}
