package ihmCivilisation;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import systemeCivilisation.Joueur;
import systemeCivilisation.Unite;
import systemeCivilisation.Ville;

public class AffichageMenu extends JPanel {

	public AffichageMenu(Joueur joueur, ActionListener ecouteurBouton) {
		super();

		this.setLayout(new GridLayout(6, 1));

		this.add(new RecapUnite(joueur));
		
		this.add(new JLabel(" Unite / Ville"));
		this.add(new RecapUnite());
		
		this.add(new JLabel(" "));
		this.add(new JLabel(" "));

		JButton finirTour = new JButton("Finir le tour");
		finirTour.setName("FinirTour");
		finirTour.addActionListener(ecouteurBouton);

		this.add(finirTour);
	}

	public AffichageMenu(Unite unite, Joueur joueur,
			ActionListener ecouteurBouton) {
		super();
		
		this.setLayout(new GridLayout(6, 1));
		
		
		this.add(new RecapUnite(joueur));

		this.add(new JLabel(" Unite"));
		this.add(new RecapUnite(unite));
		
		this.add(new JLabel(" "));
		this.add(new JLabel(" "));

		JButton finirTour = new JButton("Finir le tour");
		finirTour.setName("FinirTour");
		finirTour.addActionListener(ecouteurBouton);

		this.add(finirTour);
	}

	public AffichageMenu(Ville ville, Joueur joueur,
			ActionListener ecouteurBouton) {
		super();

		this.setLayout(new GridLayout(6, 1));
		
		this.add(new RecapUnite(joueur));
		
		this.add(new JLabel(new ImageIcon("Images/ville.jpg")));
		this.add(new RecapUnite(ville));

		if (joueur == ville.obtenirJoueurProprietaire()) {
			JButton ameliorerChar = new JButton("Ameliorer char ");
			ameliorerChar.setName("AmeliorerChar");
			ameliorerChar.addActionListener(ecouteurBouton);

			JButton ameliorerVille = new JButton("Ameliorer au niveau : "
					+ (ville.obtenirNiveau() + 1)
					+ "   "
					+ Integer.toString(((ville.obtenirNiveau() + 1)*200)));
			ameliorerVille.setName("AmeliorerVille");
			ameliorerVille.addActionListener(ecouteurBouton);

			this.add(ameliorerChar);
			this.add(ameliorerVille);

		}
		else {
			this.add(new JLabel(""));
			this.add(new JLabel(""));
		}
		JButton finirTour = new JButton("Finir le tour");
		finirTour.setName("FinirTour");
		finirTour.addActionListener(ecouteurBouton);

		this.add(finirTour);
	}

	public void miseAJourDuMenu() {
	}
}
