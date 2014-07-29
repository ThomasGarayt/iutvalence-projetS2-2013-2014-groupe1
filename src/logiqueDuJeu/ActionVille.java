package logiqueDuJeu;


/**
 * @author Romain
 * Les action possible d'un joueur lorsqu'il selectionne une ville 
 */
public enum ActionVille {
	/**
	 * Le joueur ameliore la ville.
	 */
	Ameliorer,
	/**
	 * Le joueur cree une unite dans la ville.
	 */
	CreerUnite,
	/**
	 * Le joueur deselectionne la ville.
	 */
	Deselectionner;
}
