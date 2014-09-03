package fr.projet.java.gestionCarte;

/**
 * @author Romain Outil de comparaison entre differentes distance. Contient une
 *         position et sa distance a l'origine.
 */
public class ComparaisonDistance implements Comparable<ComparaisonDistance> {

	private Position position;
	private Integer distance;

	/**
	 * Nouvelle distance
	 * 
	 * @param position
	 *            La position de la distance
	 * @param distance
	 *            La distance de la position TODO( Houla ... )
	 */
	public ComparaisonDistance(Position position, Integer distance) {
		this.position = position;
		this.distance = distance;
	}

	/**
	 * La position.
	 * 
	 * @return La position.
	 */
	public Position getPosition() {
		return this.position;
	}

	/**
	 * La distance.
	 * 
	 * @return La distance.
	 */
	public Integer getDistance() {
		return this.distance;
	}

	@Override
	public int compareTo(ComparaisonDistance o) {
		return this.distance - o.distance;
	}
}
