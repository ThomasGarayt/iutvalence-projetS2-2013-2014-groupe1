package fr.projet.java.menu;

import java.io.File;

/**
 * @author Romain Les informations permettant de creer une partie.
 * 
 */
public class InfoPreferencePartie {
	private String nomJ1, nomJ2, nationJ1, nationJ2, carte;

	/**
	 * Creation d'un objet rassemblant les informations de creations de partie.
	 * 
	 * @param nomJ1
	 *            Le nom du joueur 1
	 * @param nomJ2
	 *            Le nom du joueur 2
	 * @param nationJ1
	 *            La nation du joueur 1
	 * @param nationJ2
	 *            La nation du joueur 2
	 * @param carte
	 *            La carte.
	 */
	public InfoPreferencePartie(String nomJ1, String nomJ2, String nationJ1,
			String nationJ2, String carte) {
		this.nomJ1 = nomJ1;
		this.nomJ2 = nomJ2;
		this.nationJ1 = nationJ1;
		this.nationJ2 = nationJ2;
		this.carte = carte;
	}

	@Override
	public String toString() {
		String str;
		if (this.nomJ1 != null && this.nationJ1 != null && this.carte != null
				&& this.nationJ2 != null && this.nomJ2 != null) {
			str = "Description de l'objet InfoZDialog : \n";
			str += "Joueur 1 : " + this.nomJ1 + "\n";
			str += "\t Nation : " + this.nationJ1 + "\n";
			str += "Joueur 2 : " + this.nomJ2 + "\n";
			str += "\t Nation : " + this.nationJ2 + "\n";
			str += "Carte : " + this.carte + "\n";
		} else {
			str = "Information Manquante!";
		}
		return str;
	}

	/**
	 * @return Un tableau contenant le nom des joueurs.
	 */
	public String[] obtenirNomsDesJoueurs() {
		String[] nomDesJoueurs = new String[2];
		nomDesJoueurs[0] = this.nomJ1;
		nomDesJoueurs[1] = this.nomJ2;
		return nomDesJoueurs;
	}

	/**
	 * @return Un tableau contenant les couleurs des nations associer au
	 *         joueurs.
	 */
	public String[] obtenirNationsAssocierAuJoueur() {
		String[] nomDesJoueurs = new String[2];
		nomDesJoueurs[0] = this.nationJ1;
		nomDesJoueurs[1] = this.nationJ2;
		return nomDesJoueurs;
	}

	/**
	 * @return Le fichier de la carte choisi.
	 */
	public File obtenirFichierCarte() {
		return new File("Cartes/" + this.carte);
	}
}
