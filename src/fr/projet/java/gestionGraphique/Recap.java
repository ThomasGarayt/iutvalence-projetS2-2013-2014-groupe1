package fr.projet.java.gestionGraphique;

import fr.projet.java.gestionUnite.Nation;
import fr.projet.java.gestionUnite.Unite;
import fr.projet.java.gestionUnite.Ville;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Romain
 * Le recapitulatif grahpique d'un joueur d'une unite ou d'une ville.
 */
public class Recap extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Recapitulatif vide.
	 */
	public Recap() {
		super();

		this.add(new JLabel(""));
	}

	/**
	 * Recapitulatif d'un joueur
	 * @param joueur Le joueur dont on veux obtenir le recapitulatif.
	 */
	public Recap(Nation joueur)
	{
		super();

		this.setLayout(new GridLayout(2, 2));

		this.add(new JLabel(" Tour de :"));
		this.add(new JLabel(joueur.obtenirNom()));
		
		
		this.add(new JLabel(" Argent : "));
		this.add(new JLabel(Integer.toString(joueur.obtenirTresorerie())));
		
	}
	
	
	/**
	 * Recapitulatif d'une unite.
	 * @param unite L'unite dont on souhaite obtenir le recapitulatif.
	 */
	public Recap(Unite unite) {
		super();

		this.setLayout(new GridLayout(6, 2));
		this.add(new JLabel(" Niveau : "));
		this.add(new JLabel(Integer.toString(unite.obtenirNiveau())));

		this.add(new JLabel(" Vie : "));
		this.add(new JLabel(Integer.toString(unite.obtenirVie())));
		
		this.add(new JLabel(" Puissance : "));
		this.add(new JLabel(Integer.toString(unite.obtenirPuissance())));
		
		this.add(new JLabel(" Deplacement : "));
		this.add(new JLabel(Integer.toString(unite.obtenirPointDeMouvements())));
		
		this.add(new JLabel(" Nb attaques : "));
		this.add(new JLabel(Integer.toString(unite.obtenirNombreAttaqueParTour())));
		
		this.add(new JLabel(" Portee : "));
		this.add(new JLabel(Integer.toString(unite.obtenirPorte())));
	}

	/**
	 * Recapitulatif d'une ville.
	 * @param ville La ville dont on souhaite obtenir le recapitulatif.
	 */
	public Recap(Ville ville) {
		super();

		this.setLayout(new GridLayout(3, 2));

		this.add(new JLabel(" Niveau  "));
		this.add(new JLabel(" " + Integer.toString(ville.obtenirNiveau())));

		if (ville.obtenirJoueurProprietaire() != null) {
			this.add(new JLabel(" Proprietaire : "));
			this.add(new JLabel(" "
					+ ville.obtenirJoueurProprietaire().obtenirNom()));
		} 
		else 
		{
			this.add(new JLabel(" Ville libre"));
			this.add(new JLabel(" "));
		}

		this.add(new JLabel(" "));
		this.add(new JLabel(" "));
	}
}
