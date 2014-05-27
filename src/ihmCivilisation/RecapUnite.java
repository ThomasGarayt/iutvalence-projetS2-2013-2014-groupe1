package ihmCivilisation;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import systemeCivilisation.Unite;
import systemeCivilisation.Ville;

public class RecapUnite extends JPanel {

	public RecapUnite() {
		super();

		this.setLayout(new GridLayout(3, 2));
		this.add(new JLabel(" Niveau  "));
		this.add(new JLabel("1"));

		this.add(new JLabel(" Vie  "));
		this.add(new JLabel(" "));

		this.add(new JLabel(" Deplacement  "));
		this.add(new JLabel(" "));
	}

	public RecapUnite(Unite unite) {
		super();

		this.setLayout(new GridLayout(3, 2));
		this.add(new JLabel(" Niveau  "));
		this.add(new JLabel(" "));

		this.add(new JLabel(" Vie  "));
		this.add(new JLabel(Integer.toString(unite.obtenirVie())));

		this.add(new JLabel(" Deplacement  "));
		this.add(new JLabel(Integer.toString(unite.obtenirPointDeMouvements())));
	}

	public RecapUnite(Ville ville) {
		super();

		this.setLayout(new GridLayout(3, 2));

		this.add(new JLabel(" Niveau  "));
		this.add(new JLabel(" " + Integer.toString(ville.obtenirNiveau())));

		if (ville.obtenirJoueurProprietaire() != null) {
			this.add(new JLabel(" Appartient à : "));
			this.add(new JLabel(" "
					+ ville.obtenirJoueurProprietaire().obtenirNom()));
		} else {
			this.add(new JLabel(" Ville libre         "));
			this.add(new JLabel("                        "));
		}

		this.add(new JLabel(" "));
		this.add(new JLabel(" "));
	}
}
