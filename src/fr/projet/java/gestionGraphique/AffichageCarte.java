package fr.projet.java.gestionGraphique;

import fr.projet.java.gestionCarte.Carte;
import fr.projet.java.gestionUnite.Chemin;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.OverlayLayout;

/**
 * @author Romain Affiche la carte passe en parametre dans un JPanel.
 */
public class AffichageCarte extends JPanel {

	private static final long serialVersionUID = 1L;
	private AffichageCarteChemin affichageCarteChemin;
	private AffichageCarteTerrain affichageCarteTerrain;
	private AffichageCarteUnite affichageCarteUnite;

	/**
	 * Affiche la carte graphiquement.
	 * 
	 * @param carte
	 *            La carte a afficher.
	 * @param auditeurBoutons
	 *            l'auditeur des boutons de la carte.
	 */
	public AffichageCarte(Carte carte, ActionListener auditeurBoutons) {
		super();

		this.setLayout(new OverlayLayout(this));
		affichageCarteTerrain = new AffichageCarteTerrain(carte,
				auditeurBoutons);
		affichageCarteTerrain.setOpaque(false);

		affichageCarteChemin = new AffichageCarteChemin(auditeurBoutons);
		affichageCarteChemin.setOpaque(false);

		affichageCarteUnite = new AffichageCarteUnite(carte, auditeurBoutons);
		affichageCarteUnite.setOpaque(false);

		this.add(affichageCarteUnite);
		this.add(affichageCarteChemin);
		this.add(affichageCarteTerrain);

	}

	/**
	 * Met a jour la carte des unites.
	 * 
	 * @param carte
	 *            La carte.
	 */
	public void mettreAJourUnite(Carte carte) {
		this.affichageCarteUnite.mettreAJour(carte);
		this.affichageCarteUnite.repaint();
	}

	/**
	 * Met a jour la carte du terrain.
	 * 
	 * @param carte
	 *            La Carte.
	 */
	public void mettreAJourVille(Carte carte) {
		this.affichageCarteTerrain.mettreAJour(carte);
		this.affichageCarteTerrain.repaint();
	}

	/**
	 * Met a jour la carte des chemins.
	 * 
	 * @param cheminAAfficher
	 *            Le chemin a afficher.
	 * @param nombreDePM
	 *            Le nombre de point de mouvement ( apres le chemin devient
	 *            rouge ).
	 */
	public void afficherUnChemin(Chemin cheminAAfficher, int nombreDePM) {
		this.affichageCarteChemin.afficherUnChemin(cheminAAfficher, nombreDePM);
		this.affichageCarteChemin.repaint();
	}
}