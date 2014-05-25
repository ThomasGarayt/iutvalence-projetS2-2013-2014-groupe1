package systemeCivilisation;
/**
 * @author Romain Une position sur une grille en deux dimensions.
 */
public class Position {

	/**
	 * La position sur l'axe des abscisses.
	 */
	public int positionX;

	/**
	 * La position sur l'axe des ordonnées.
	 */
	public int positionY;

	/**
	 * Création d'une position sur la grille.
	 * 
	 * @param x
	 *            La position sur l'axe des abscisses.
	 * @param y
	 *            La position sur l'axe des ordonnées.
	 */
	public Position(int x, int y) {
		this.positionX = x;
		this.positionY = y;
	}

	/**
	 * Obtenir la position X.
	 * 
	 * @return La position X.
	 */
	public int getX() {
		return this.positionX;
	}

	/**
	 * Obtenir la position Y.
	 * 
	 * @return La position Y
	 */
	public int getY() {
		return this.positionY;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Position))
			return false;
		Position other = (Position) obj;
		if (this.positionX != other.positionX)
			return false;
		if (this.positionY != other.positionY)
			return false;
		return true;
	}
	
	
}
