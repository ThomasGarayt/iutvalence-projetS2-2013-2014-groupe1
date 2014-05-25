package ihmCivilisation;

import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;

import systemeCivilisation.TypeUnite;
import systemeCivilisation.Unite;

public class AffichageMenu extends JPanel {
	
	public AffichageMenu() {
		super();
		
		this.setLayout(new GridLayout(3, 1));
		this.add(new JLabel(" Joueur X"));
		this.add(new JLabel(" Unité / Ville"));
		this.add(new RecapUnite());
	}
	
	public AffichageMenu(Unite unite) {
		super();
		
		this.setLayout(new GridLayout(3, 1));
		this.add(new JLabel(" Joueur X") );
		this.add(new JLabel(" Unité / Ville") );
		this.add(new RecapUnite(unite) );
	}
	
	public void miseAJourDuMenu() {
	}
}
