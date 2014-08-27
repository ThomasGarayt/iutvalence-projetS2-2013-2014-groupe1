package fr.projet.java.gestionCarte;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

import fr.projet.java.exception.CheminImpossibleException;
import fr.projet.java.gestionUnite.Chemin;
import fr.projet.java.gestionUnite.Nation;
import fr.projet.java.gestionUnite.Unite;
import fr.projet.java.gestionUnite.Ville;

/**
 * @author Romain Une carte qui contient des cases.
 */
public class Carte {

	/**
	 * Le nombre de case en x de la carte.
	 */
	public static int NB_CASES_X;

	/**
	 * Le nombre de case en x de la carte.
	 */
	public static int NB_CASES_Y;

	private Case[][] cases;

	private DetectionDesChemins detectionDesChemins;

	private Vector<Unite> unitesDeLaCarte;

	/**
	 * Obtenir l'ensemble des unites presente sur la carte.
	 * 
	 * @return Un tableau d'unite.
	 */
	public Vector<Unite> obtenirUnitesDeLaCarte() {
		return this.unitesDeLaCarte;
	}

	/**
	 * Obtenir l'emsemble des villes presente sur la carte.
	 * 
	 * @return Un tableau de ville.
	 */
	public Vector<Ville> obtenirVillesDeLaCarte() {
		return this.villesDeLaCarte;
	}

	private Vector<Ville> villesDeLaCarte;

	/**
	 * Une nouvelle carte vide.
	 * 
	 * @param fichierCarte
	 *            Le fichier de creation de la carte.
	 */
	public Carte(File fichierCarte) {
		unitesDeLaCarte = new Vector<Unite>();
		villesDeLaCarte = new Vector<Ville>();

		Scanner scanner;
		try {
			scanner = new Scanner(fichierCarte);

			// On recupere le nombre de ligne et de colonne.
			NB_CASES_X = scanner.nextInt();
			NB_CASES_Y = scanner.nextInt();
			System.out.println(NB_CASES_X + "::" + NB_CASES_Y);
			scanner.nextLine();

			cases = new Case[NB_CASES_X][NB_CASES_Y];

			// On creer la carte.
			for (int caseCouranteX = 0; caseCouranteX < NB_CASES_X; caseCouranteX++) {
				String line = scanner.nextLine();
				System.out.println(line);

				for (int caseCouranteY = 0; caseCouranteY < NB_CASES_Y; caseCouranteY++) {
					if (line.charAt(caseCouranteY) == 'p')
						this.cases[caseCouranteX][caseCouranteY] = new Case(
								Terrain.Plaine);
					else if (line.charAt(caseCouranteY) == 'o')
						this.cases[caseCouranteX][caseCouranteY] = new Case(
								Terrain.Ocean);
					else if (line.charAt(caseCouranteY) == 'm')
						this.cases[caseCouranteX][caseCouranteY] = new Case(
								Terrain.Montagne);
				}
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		this.detectionDesChemins = new DetectionDesChemins();
	}

	/**
	 * Permet de tester si les unite peuvent traverser la case.
	 * 
	 * @param positionDeLaCase
	 *            La position de la case a tester.
	 * @return Vrai si la case est accessible au unite.
	 */
	public boolean laCaseEstAccessible(Position positionDeLaCase) {
		return this.cases[positionDeLaCase.getX()][positionDeLaCase.getY()]
				.estAccessible();
	}

	/**
	 * Permet d'obtenir le type de terrain d'une case.
	 * 
	 * @param positionDeLaCase
	 *            La position de la case.
	 * @return Le type de terrain de la case.
	 */
	public Terrain obtenirTerrain(Position positionDeLaCase) {
		return this.cases[positionDeLaCase.getX()][positionDeLaCase.getY()]
				.obtenirTerrain();
	}

	/**
	 * Permet de savoir si une case contient une unite a partir d'une position.
	 * 
	 * @param positionDeLaCase
	 *            La position de la case.
	 * @return Vrai si la case ne contient pas d'unite, faux sinon.
	 */
	public boolean laCaseContientUneUnite(Position positionDeLaCase) {
		return (this.cases[positionDeLaCase.positionX][positionDeLaCase.positionY]
				.aUneUnite());
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
		if (uniteAAjouter.obtenirJoueur().obtenirTresorerie() >= uniteAAjouter
				.obtenirCoutCreation()) {
			uniteAAjouter.obtenirJoueur().modifierTresorie(
					-uniteAAjouter.obtenirCoutCreation());
			this.unitesDeLaCarte.add(uniteAAjouter);
			this.cases[positionDeLUnite.positionX][positionDeLUnite.positionY]
					.ajouterUnite(uniteAAjouter);
		}
	}

	/**
	 * Deux unites qui s'attaquent.
	 * 
	 * @param positionUniteAttaquante
	 *            la position de l'unite attaquante.
	 * @param positionUniteDefenseur
	 *            La position de l'unite defenseur.
	 */
	public void attaquerDesUnite(Position positionUniteAttaquante,
			Position positionUniteDefenseur) {
		if (positionUniteAttaquante.distance(positionUniteDefenseur) <= this
				.obtenirLUniteDeLaCase(positionUniteAttaquante).obtenirPorte()) {
			this.obtenirLUniteDeLaCase(positionUniteAttaquante).attaquer(
					this.obtenirLUniteDeLaCase(positionUniteDefenseur));
			if (!this.obtenirLUniteDeLaCase(positionUniteDefenseur)
					.estVivante())
				supprimerUneUnite(positionUniteDefenseur);
		}
	}

	/**
	 * Permet de supprimer une unite.
	 * 
	 * @param positionDeLUnite
	 *            La position de l'unite que l'on souhaite supprimer.
	 */
	public void supprimerUneUnite(Position positionDeLUnite) {
		this.unitesDeLaCarte
				.remove(this.cases[positionDeLUnite.positionX][positionDeLUnite.positionY]
						.obtenirUnite());
		this.cases[positionDeLUnite.positionX][positionDeLUnite.positionY]
				.supprimerUnite();
	}

	/**
	 * @param positionDeDepart
	 *            La position de depart.
	 * @param positionDArriver
	 *            La position d'arrivee.
	 * @return Le chemin menant a la case demandee.
	 * @throws CheminImpossibleException
	 *             Le chemin est impossible.
	 */
	public Chemin obtenirUnChemin(Position positionDeDepart,
			Position positionDArriver) throws CheminImpossibleException {
		return detectionDesChemins.obtenirUnChemin(this, positionDeDepart,
				positionDArriver);
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
		Unite uniteADeplacer = this.obtenirLUniteDeLaCase(positionDeDepart);

		try {
			System.out.println("Recherche chemin ...");

			Chemin cheminDeLUnite = detectionDesChemins.obtenirUnChemin(this,
					positionDeDepart, positionDArriver);

			int coutPointsDeMouvement = cheminDeLUnite.getTaille() - 1;

			System.out.println("Coute (pm) : " + coutPointsDeMouvement);

			if (uniteADeplacer.obtenirPointDeMouvements() >= coutPointsDeMouvement) {
				this.cases[positionDArriver.positionX][positionDArriver.positionY]
						.ajouterUnite(uniteADeplacer);
				this.cases[positionDeDepart.positionX][positionDeDepart.positionY]
						.supprimerUnite();
				uniteADeplacer
						.mettreAJourPointDeMouvements(coutPointsDeMouvement);
				if (this.laCaseContientUneVille(positionDArriver))
					this.obtenirLaVilleDeLaCase(positionDArriver)
							.changerProprietaire(uniteADeplacer.obtenirJoueur());
			}
		} catch (CheminImpossibleException e) {
			System.err.println("Erreur chemin.");
		}

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
		this.villesDeLaCarte.add(villeAAjouter);
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

	/**
	 * Permet de savoir si une case est accessible a une unite.
	 * 
	 * @param positionArrive
	 *            La position a tester
	 * @return Vrai si la position est inaccessible, faux sinon.
	 */
	public boolean estAccessible(Position positionArrive) {
		if (laCaseContientUneUnite(positionArrive))
			return true;
		if (!laCaseEstAccessible(positionArrive))
			return true;
		return false;
	}

	/**
	 * Obtenir les position adjacente a une position.
	 * 
	 * @param position
	 *            La position dont a va regarder les position autour.
	 * @return La liste des position adjacentes.
	 */
	public ArrayList<Position> casesAdjacentes(Position position) {
		ArrayList<Position> positionAdjacente = new ArrayList<Position>();

		if (position.getX() > 0)
			positionAdjacente.add(new Position(position.getX() - 1, position
					.getY()));
		if (position.getX() + 1 < NB_CASES_X)
			positionAdjacente.add(new Position(position.getX() + 1, position
					.getY()));
		if (position.getY() > 0)
			positionAdjacente.add(new Position(position.getX(),
					position.getY() - 1));
		if (position.getY() + 1 < NB_CASES_Y)
			positionAdjacente.add(new Position(position.getX(),
					position.getY() + 1));

		return positionAdjacente;
	}

	/**
	 * Si une unite ou une ville appartenant a une nation existe sur la carte
	 * 
	 * @param nation
	 *            La nation.
	 * @return Vrai si la nation possede une ville ou une unite.
	 */
	public boolean nationExiste(Nation nation) {
		Position position;
		for (int positionX = 0; positionX < NB_CASES_X; positionX++)
			for (int positionY = 0; positionY < NB_CASES_X; positionY++) {
				position = new Position(positionX, positionY);
				if (this.laCaseContientUneUnite(position))
					if (obtenirLUniteDeLaCase(position).obtenirJoueur().equals(
							nation))
						return true;
				if (this.laCaseContientUneVille(position))
					if (this.obtenirLaVilleDeLaCase(position)
							.obtenirJoueurProprietaire() == null)
						;
					else if (this.obtenirLaVilleDeLaCase(position)
							.obtenirJoueurProprietaire().equals(nation))
						return true;
			}
		return false;
	}

	/**
	 * Trouver la position d'une unite sur la carte.
	 * 
	 * @param unite
	 *            L'unite a chercher.
	 * @return La position de l'unite, null si non trouve.
	 */
	public Position trouverUnite(Unite unite) {
		for (int positionX = 0; positionX < NB_CASES_X; positionX++)
			for (int positionY = 0; positionY < NB_CASES_X; positionY++) {
				Position position = new Position(positionX, positionY);
				if (this.laCaseContientUneUnite(position))
					if (this.obtenirLUniteDeLaCase(position).equals(unite))
						return position;
			}
		return null;
	}

	/**
	 * Trouver la position d'une ville sur la carte.
	 * 
	 * @param ville
	 *            La ville a chercher.
	 * @return La position de la ville, null si non trouve.
	 */
	public Position trouverVille(Ville ville) {
		for (int positionX = 0; positionX < NB_CASES_X; positionX++)
			for (int positionY = 0; positionY < NB_CASES_X; positionY++) {
				Position position = new Position(positionX, positionY);
				if (this.laCaseContientUneVille(position))
					if (this.obtenirLaVilleDeLaCase(position).equals(ville))
						return position;
			}
		return null;
	}

	/**
	 * Compare la distance de toutes les villes sur la carte avec une position.
	 * 
	 * @param origine
	 *            La position de reference.
	 * @return Un tableau de position des villes trier de la plus faible
	 *         distance a la plus eleve.
	 */
	// TODO Trier Les villes.
	public Vector<ComparaisonDistance> distanceVille(Position origine) {
		Position position;
		Vector<ComparaisonDistance> distanceVille = new Vector<ComparaisonDistance>();
		for (int positionX = 0; positionX < NB_CASES_X; positionX++)
			for (int positionY = 0; positionY < NB_CASES_X; positionY++) {
				position = new Position(positionX, positionY);
				if (this.laCaseContientUneVille(position))
					try {
						distanceVille.add(new ComparaisonDistance(position,
								this.obtenirUnChemin(origine, position)
										.getTaille()));
						;
					} catch (CheminImpossibleException e) {
					}
			}
		Collections.sort(distanceVille);
		return distanceVille;
	}

	/**
	 * Compare la distance de toutes les unites sur la carte avec une position.
	 * 
	 * @param origine
	 *            La position de reference.
	 * @return Un tableau de position des unites trier de la plus faible
	 *         distance a la plus eleve.
	 */
	// TODO Les chemins sont ignore car on vise une unite !
	public Vector<ComparaisonDistance> distanceUnite(Position origine) {
		Position position;
		Vector<ComparaisonDistance> distanceUnite = new Vector<ComparaisonDistance>();
		for (int positionX = 0; positionX < NB_CASES_X; positionX++)
			for (int positionY = 0; positionY < NB_CASES_X; positionY++) {
				position = new Position(positionX, positionY);
				if (this.laCaseContientUneUnite(position))
					distanceUnite.add(new ComparaisonDistance(position,
							origine.distance(position)));
			}
		Collections.sort(distanceUnite);
		return distanceUnite;
	}

}
