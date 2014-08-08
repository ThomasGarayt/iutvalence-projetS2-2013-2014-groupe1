package fr.projet.java.gestionCarte;

import fr.projet.java.exception.CheminImpossibleException;
import fr.projet.java.gestionUnite.Chemin;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Romain IA qui utilise l'algorithme de detection de chemin par vague.
 *         On affecte un coefficient a chaque case puis on parcourt le chemin en
 *         sens inverse en trouvant les case adjacentes les moins coefficiente.
 */
public class DetectionDesChemins {

	private static final Integer OCCUPEE = -1;
	private Integer[][] carteCoefficient;

	/**
	 * Le constructeur initialise la carte des coefficient.
	 */
	public DetectionDesChemins() {
		this.carteCoefficient = new Integer[Carte.NB_CASES_X][Carte.NB_CASES_Y];
	}

	/**
	 * Obtenir un chemin d'une position a une autre.
	 * 
	 * @param carte
	 *            La carte ou l'on doit chercher les chemins.
	 * @param positionDepart
	 *            La position de depart du chemin.
	 * @param positionArrive
	 *            La position d'arrivee du chemin.
	 * @return Un chemin contenant toutes les positions par lesquelles on doit
	 *         passer pour arriver a la position defini en parametre, les
	 *         positions de depart et d'arrivee incluse.
	 * @throws CheminImpossibleException
	 *             Le chemin n'as pas ete trouver ou est impossible.
	 */
	public Chemin obtenirUnChemin(Carte carte, Position positionDepart,
			Position positionArrive) throws CheminImpossibleException {

		ArrayList<Position> positionDeCoeffActuel = new ArrayList<Position>();
		ArrayList<Position> positionACoefficiente = new ArrayList<Position>();

		ArrayList<Position> cheminDefinitif = new ArrayList<Position>();
		ArrayList<Position> positionAdjacente = new ArrayList<Position>();

		Position positionCheminActuel = positionArrive;
		Position positionATesterPourCoefficient;

		int coefficientActuel = 0;
		int coefficientDeReference = 1000;

		viderLaCarteDesCoefficient();
		placerObstacle(carte);

		carteCoefficient[positionDepart.positionX][positionDepart.getY()] = 0;
		if (carte.estOccupee(positionArrive))
			throw new CheminImpossibleException();

		// Creation de le carte des coefficients.
		while (!carteRemplie(coefficientActuel)) {

			// Mise a zero de positionACoefficiente
			positionACoefficiente = new ArrayList<Position>();

			// Les positions contenant le coefficient actuel.
			positionDeCoeffActuel = positionContenant(coefficientActuel);

			// Augmentation du coefficient.
			coefficientActuel++;

			// Les voisines de ces positions.
			Iterator<Position> positionActuel = positionDeCoeffActuel
					.iterator();
			while (positionActuel.hasNext()) {
				positionACoefficiente.addAll(carte
						.casesAdjacentes(positionActuel.next()));
			}

			// Affectation des coefficients.
			Iterator<Position> iteratorDePositionACoefficiente = positionACoefficiente
					.iterator();
			while (iteratorDePositionACoefficiente.hasNext()) {
				Position position = iteratorDePositionACoefficiente.next();
				remplirCase(position, coefficientActuel);
			}
		}

		boolean cheminModifier = true;
		// Creation du chemin.
		while (!positionCheminActuel.equals(positionDepart)) {
			// Ajout de la position au chemin
			cheminDefinitif.add(0, positionCheminActuel);

			// Determinisation de la position adjacente la moins coefficiente.
			positionAdjacente = carte.casesAdjacentes(positionCheminActuel);

			// Si il n'y a pas de coefficient dans les cases adjacentes le
			// chemin est impossible.
			if (positionAdjacente.size() == 0 || !cheminModifier)
				throw new CheminImpossibleException();

			cheminModifier = false;
			
			Iterator<Position> iteratorDePositionAdjacente = positionAdjacente
					.iterator();

			while (iteratorDePositionAdjacente.hasNext()) {
				positionATesterPourCoefficient = iteratorDePositionAdjacente
						.next();
				// Verifie si le coefficient est plus petit.
				Integer coefficientDeLaCarteATester = carteCoefficient[positionATesterPourCoefficient
						.getX()][positionATesterPourCoefficient.getY()];
				if (coefficientDeLaCarteATester != null
						&& coefficientDeLaCarteATester != OCCUPEE
						&& coefficientDeLaCarteATester < coefficientDeReference) {
					positionCheminActuel = new Position(
							positionATesterPourCoefficient.getX(),
							positionATesterPourCoefficient.getY());
					coefficientDeReference = carteCoefficient[positionATesterPourCoefficient
							.getX()][positionATesterPourCoefficient.getY()];
					cheminModifier = true;
				}

			}
		}
		cheminDefinitif.add(0, positionCheminActuel);

		// Affectation et retour du chemin
		Chemin chemin = new Chemin(cheminDefinitif);
		return chemin;
	}

	private boolean carteRemplie(int coefficientCourant) {
		for (int x = 0; x < Carte.NB_CASES_X; x++)
			for (int y = 0; y < Carte.NB_CASES_Y; y++)
				if ((carteCoefficient[x][y] != null)
						&& (carteCoefficient[x][y] >= coefficientCourant))
					return false;
		return true;
	}

	private ArrayList<Position> positionContenant(Integer coefficient) {
		ArrayList<Position> positionContenantLeCoeff = new ArrayList<Position>();
		for (int x = 0; x < Carte.NB_CASES_X; x++)
			for (int y = 0; y < Carte.NB_CASES_Y; y++) {
				if (carteCoefficient[x][y] == coefficient)
					positionContenantLeCoeff.add(new Position(x, y));
			}
		return positionContenantLeCoeff;
	}

	private void remplirCase(Position position, int coeff) {
		if (carteCoefficient[position.getX()][position.getY()] == null)
			carteCoefficient[position.getX()][position.getY()] = coeff;
	}

	private void viderLaCarteDesCoefficient() {
		for (int x = 0; x < Carte.NB_CASES_X; x++)
			for (int y = 0; y < Carte.NB_CASES_Y; y++)
				this.carteCoefficient[x][y] = null;
	}

	private void placerObstacle(Carte carte) {
		for (int x = 0; x < Carte.NB_CASES_X; x++)
			for (int y = 0; y < Carte.NB_CASES_Y; y++) {
				Position position = new Position(x, y);
				if (carte.estOccupee(position))
					this.carteCoefficient[x][y] = OCCUPEE;
			}

	}

}
