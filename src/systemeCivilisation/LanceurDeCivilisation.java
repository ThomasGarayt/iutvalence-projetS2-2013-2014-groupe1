package systemeCivilisation;

import ihmCivilisation.InterfaceGraphique;

import javax.swing.SwingUtilities;

/**
 * @author Romain La classe qui lance le jeu de Civilisation.
 */
public class LanceurDeCivilisation {

	/**
	 * Le "main" graphique de l'application
	 * 
	 * @param args
	 *            Les arguments en entree du programme. ( Ici aucun )
	 */
	public static void main(String[] args) {
		InterfaceGraphique interfaceGraphique = new InterfaceGraphique();
		SwingUtilities.invokeLater(interfaceGraphique);
	}

}
