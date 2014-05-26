package ihmCivilisation;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import systemeCivilisation.Joueur;
import systemeCivilisation.Unite;

public class AffichageMenu extends JPanel {
	
	public AffichageMenu(Joueur joueur) {
		super();
		
		this.setLayout(new GridLayout(4, 1));
		this.add(new JLabel(joueur.obtenirNom()) );
		this.add(new JLabel(" Unite / Ville"));
		this.add(new RecapUnite());
		this.add(new JButton("Finir le tour"));
	}
	
	public AffichageMenu(Unite unite, Joueur joueur) {
		super();
		
		this.setLayout(new GridLayout(4, 1));
		this.add(new JLabel(joueur.obtenirNom()) );
		this.add(new JLabel(" Unite / Ville") );
		this.add(new RecapUnite(unite) );
		this.add(new JButton("Finir le tour"));
	}
	
	public void miseAJourDuMenu() {
	}
}
