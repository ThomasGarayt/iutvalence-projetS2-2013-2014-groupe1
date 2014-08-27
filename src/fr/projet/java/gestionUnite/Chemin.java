package fr.projet.java.gestionUnite;

import fr.projet.java.gestionCarte.Position;

import java.util.ArrayList;

/**
 * @author Romain
 * 
 *         Un chemin sur la carte.
 */
public class Chemin {

	private final ArrayList<Position> positions;
	private int taille;

	/**
	 * Construction du chemin avec un ensemble de positions.
	 * 
	 * @param positions
	 *            L'ensemble de positions du chemin.
	 */
	public Chemin(ArrayList<Position> positions) {
		this.positions = positions;
		this.taille = this.positions.size();
	}

	/**
	 * Pour obtenir la taille du chemin.
	 * 
	 * @return La taille du chemin en nombre de positions.
	 */
	public int getTaille() {
		return this.taille;
	}

	/**
	 * Pour obtenir une position particuliere du chemin.
	 * 
	 * @param position
	 *            Le numero de la position a obtenir.
	 * @return La N ieme position du chemin.
	 */
	public Position getPosition(int position) {
		return this.positions.get(position);
	}

}
