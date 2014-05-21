/**
 * @author Romain Une ville qui à un niveau et une position.
 */
public class Ville {

	private int niveau;

	private final Position position;

	/**
	 * Crée une ville avec une position et un niveau.
	 * 
	 * @param positionDeLaVille
	 *            la position à laquelle on souhaite crée la ville.
	 */
	public Ville(Position positionDeLaVille) {
		this.position = positionDeLaVille;
		this.niveau = 1;
	}

	/**
	 * Augmenter le niveau de la ville de 1 point.
	 */
	public void ameliorer() {
		// Réduire la trésorerie ?
		this.niveau++;
	}

	/**
	 * Permet d'obtenir la position de la ville.
	 * 
	 * @return La position de la ville.
	 */
	public Position obtenirPosition() {
		return this.position;
	}

	/**
	 * Permet d'obtenir le niveau de la ville.
	 * 
	 * @return Le niveau de la ville.
	 */
	public int obtenirNiveau() {
		return this.niveau;
	}
}
