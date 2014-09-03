package fr.projet.java.menu;

import java.io.File;

import fr.projet.java.gestionGraphique.SetDImages;

/**
 * @author Romain Les informations permettant de creer une partie.
 * 
 */
public class InfoPreferencePartie {
	
	private InfosJoueur[] infoJoueur;
	private File fichierCarte;

	/**
	 * Creation d'un objet rassemblant les informations de creations de partie.
	 */
	public InfoPreferencePartie(InfosJoueur[] infoJoueur, File fichierCarte) {
		this.fichierCarte = fichierCarte;
		this.infoJoueur = infoJoueur;
	}

	public InfosJoueur[] obtenirInfoJoueur() {
		return this.infoJoueur;
	}
	
	/**
	 * @return Le fichier de la carte choisi.
	 */
	public File obtenirFichierCarte() {
		return fichierCarte;
	}
}
