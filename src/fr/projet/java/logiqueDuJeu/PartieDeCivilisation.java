package fr.projet.java.logiqueDuJeu;

import java.io.File;
import java.util.Arrays;

import fr.projet.java.exception.CheminImpossibleException;
import fr.projet.java.exception.FinDuTourException;
import fr.projet.java.gestionCarte.Carte;
import fr.projet.java.gestionCarte.Position;
import fr.projet.java.gestionUnite.Nation;
import fr.projet.java.gestionUnite.TypeUnite;
import fr.projet.java.gestionUnite.Unite;
import fr.projet.java.gestionUnite.Ville;
import fr.projet.java.IntelligenceArtificiel.IAPrincipale;

/**
 * @author Romain Decrie la partie logique du deroulement de la partie.
 * 
 */
public class PartieDeCivilisation {

	private Joueur[] joueurs;
	private Affichage affichage;
	private Carte carte;

	private int tour = 0;
	private Nation[] nations;
	private boolean[] nationsDisparue;

	/**
	 * Creer une nouvelle partie de civilisation.
	 * 
	 * @param joueurs
	 *            Le joueur de la partie.
	 * @param nations
	 *            Les nations associer au joueur (dans le même ordre et le même
	 *            nombre).
	 * @param affichage
	 *            Les fonctions permettant l'affichage.
	 * @param fichierCarte
	 *            Le fichier de la carte.
	 */
	public PartieDeCivilisation(Joueur[] joueurs, Nation[] nations,
			Affichage affichage, File fichierCarte) {
		this.affichage = affichage;
		this.joueurs = joueurs;
		this.nations = nations;

		this.nationsDisparue = new boolean[nations.length];
		Arrays.fill(nationsDisparue, false);

		this.carte = creationDeLaCarte(fichierCarte);
		((IAPrincipale) this.joueurs[0]).associerLaCarte(this.carte);

		this.tour = 0;
	}

	/**
	 * Jouer la partie.
	 */
	public void jouer() {
		while (nombreDeNationSurLaCarte() > 1) {

			this.tour += 1;

			if (!nationsDisparue[this.tour % joueurs.length]) {
				affichage.mettreAJourLesUnites(carte);
				affichage.mettreAJourLeTerrain(carte);
				affichage
						.mettreAJourLeMenu(nations[this.tour % joueurs.length]);

				jouerUnTour(joueurs[this.tour % joueurs.length],
						nations[this.tour % joueurs.length]);
				finirLeTour(nations[this.tour % joueurs.length]);
				System.out.println("Fin du tour " + this.tour);
			}
		}
		System.out.println("Fin de la partie.");
	}

	private int nombreDeNationSurLaCarte() {
		int nombreDeNation = 0;
		for (int nationCourante = 0; nationCourante < joueurs.length; nationCourante++) {
			if (!this.nationsDisparue[nationCourante])
				nombreDeNation++;
		}
		return nombreDeNation;
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

		// Tant que le tour n'est pas terminer par le joueur.
		while (true) {
			try {
				// On reinitialise les chemins.
				affichage.afficherUnChemin(null, 0);
				// Le joueur choisie une position.
				positionChoisi = joueur.selectionnerPosition();

				System.out.println("Tu as choisi une position : "
						+ positionChoisi);
				// Si la position correspond a une unite.
				if (carte.laCaseContientUneUnite(positionChoisi)) {
					System.out.println("C'est une unite.");
					unite = carte.obtenirLUniteDeLaCase(positionChoisi);

					// On met a jour le menu.
					affichage.mettreAJourLeMenu(unite, nation);

					// Le joueur selectionne une action a realiser avec l'unite.
					switch (joueur.selectionnerActionUnite()) {
					// Le joueur ameliore l'unite.
					case Ameliorer:
						if (unite.obtenirJoueur() != nation)
							break;
						System.out.println("Tu as ameliore l'unite.");
						unite.ameliorerUnite();
						break;
					// Le joueur deplace l'unite ou attaque une autre unite.
					case Deplacer:
						// Le joueur selectionne une autre position.
						deuxiemePositionChoisie = joueur.selectionnerPosition();
						// Si l'unite n'appartient pas au joueur on interdit
						// toutes actions.
						if (unite.obtenirJoueur() != nation)
							break;
						// Si une deuxieme unite est selectionner, c'est une
						// attaque.
						if (carte
								.laCaseContientUneUnite(deuxiemePositionChoisie)) {
							// Si c'est la meme position c'est une deselection.
							if (deuxiemePositionChoisie.equals(positionChoisi))
								break;
							System.out.println("Tu as attaque une unite.");
							carte.attaquerDesUnite(positionChoisi,
									deuxiemePositionChoisie);
							// Sinon c'est un deplacement.
						} else {
							Position var2 = deuxiemePositionChoisie;
							Position var1;
							// Tant que la position n'est pas choisie
							// definitivement.
							while (true) {
								// On affiche le chemin.
								try {
									affichage.afficherUnChemin(null, 0);
									affichage.afficherUnChemin(carte
											.obtenirUnChemin(positionChoisi,
													var2), unite
											.obtenirPointDeMouvements());
								} catch (CheminImpossibleException e) {
								}
								// Le joueur selectionne une autre position.
								var1 = joueur.selectionnerPosition();
								// Si c'est la meme que la precedente c'est une
								// validation.
								if (var1.equals(var2)) {
									System.out
											.println("Tu as deplace l'unite.");
									carte.deplacerUneUnite(positionChoisi, var2);
									affichage.mettreAJourLeTerrain(carte);
									break;
									// Si c'est la position de l'unite c'est une
									// annulation.
								} else if (var1.equals(positionChoisi))
									break;
								var2 = var1;
							}
						}
						break;
					// Le joueur appuie sur une case ou un bouton menant a la
					// deselection.
					case Deselectionner:
						System.out.println("Tu as deselectionne l'unite.");
						break;
					default:
						break;
					}
					affichage.mettreAJourLeMenu(nation);
					// Si la case contient une ville.
				} else if (carte.laCaseContientUneVille(positionChoisi)) {
					System.out.println("C'est une ville.");
					ville = carte.obtenirLaVilleDeLaCase(positionChoisi);

					// On met a jour le menu.
					affichage.mettreAJourLeMenu(ville, nation);

					// Le joueur choisie une action.
					switch (joueur.selectionnerActionVille()) {
					// Si il ameliore la ville.
					case Ameliorer:
						System.out.println("Tu as ameliore la ville.");
						ville.ameliorer(nation);
						break;
					// S'il creer une unite.
					case CreerUnite:
						System.out.println("Tu as cree une unite.");
						carte.ajouterUneUniteALaCase(positionChoisi, new Unite(
								joueur.obtenirLeTypeDUniteSelectionne(),
								nation));
						break;
					// Si il deselectionne la ville.
					case Deselectionner:
						affichage.mettreAJourLeMenu(nation);
						System.out.println("Tu as deselectionne la ville.");
						break;
					default:
						break;
					}
					affichage.mettreAJourLeMenu(nation);
					// Si rien n'a ete selectionner.
				} else {
					affichage.mettreAJourLeMenu(nation);
				}
				affichage.mettreAJourLesUnites(carte);
				// En cas de fin du tour.
			} catch (FinDuTourException e) {
				break;
			}
		}
	}

	private void finirLeTour(Nation nation) {
		reinitialiserUnite(nation);
		miseAJourTresorerieFinTour(nation);
		miseAJourNationDisparue();
	}

	private void miseAJourNationDisparue() {
		for(int nationCourante = 0; nationCourante < joueurs.length; nationCourante++)
			if (!carte.nationExiste(nations[nationCourante]))
				this.nationsDisparue[nationCourante] = true;
		
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
	private Carte creationDeLaCarte(File fichierCarte) {
		Carte carteDuMonde = new Carte(fichierCarte);

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
