package ihmCivilisation;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import systemeCivilisation.Joueur;
import systemeCivilisation.Unite;
import systemeCivilisation.Ville;

public class Recap extends JPanel {

	public Recap() {
		super();

		this.add(new JLabel(""));
	}

	public Recap(Joueur joueur)
	{
		super();

		this.setLayout(new GridLayout(2, 2));

		this.add(new JLabel(" Tour de :"));
		this.add(new JLabel(joueur.obtenirNom()));
		
		
		this.add(new JLabel(" Argent : "));
		this.add(new JLabel(Integer.toString(joueur.obtenirTresorerie())));
		
	}
	
	
	public Recap(Unite unite) {
		super();

		this.setLayout(new GridLayout(3, 2));
		this.add(new JLabel(" Niveau : "));
		this.add(new JLabel(Integer.toString(unite.obtenirNiveau())));

		this.add(new JLabel(" Vie : "));
		this.add(new JLabel(Integer.toString(unite.obtenirVie())));

		this.add(new JLabel(" Deplacement : "));
		this.add(new JLabel(Integer.toString(unite.obtenirPointDeMouvements())));
	}

	public Recap(Ville ville) {
		super();

		this.setLayout(new GridLayout(3, 2));

		this.add(new JLabel(" Niveau  "));
		this.add(new JLabel(" " + Integer.toString(ville.obtenirNiveau())));

		if (ville.obtenirJoueurProprietaire() != null) {
			this.add(new JLabel(" Propriétaire : "));
			this.add(new JLabel(" "
					+ ville.obtenirJoueurProprietaire().obtenirNom()));
		} 
		else 
		{
			this.add(new JLabel(" Ville libre         "));
			this.add(new JLabel("                        "));
		}

		this.add(new JLabel(" "));
		this.add(new JLabel(" "));
	}
}
