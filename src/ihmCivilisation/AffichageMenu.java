package ihmCivilisation;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import systemeCivilisation.Joueur;
import systemeCivilisation.Unite;
import systemeCivilisation.Ville;

public class AffichageMenu extends JPanel {
	

	public AffichageMenu(Joueur joueur, ActionListener ecouteurBouton) {
		super();
		
		this.setLayout(new GridLayout(4, 1));
		this.add(new JLabel(joueur.obtenirNom()) );
		this.add(new JLabel(" Unite / Ville"));
		this.add(new RecapUnite());
		
		JButton finirTour = new JButton("Finir le tour");
		finirTour.setName("FinirTour");
		finirTour.addActionListener(ecouteurBouton);
		
		this.add(finirTour);
	}
	
	public AffichageMenu(Unite unite, Joueur joueur, ActionListener ecouteurBouton) {
		super();
		
		this.setLayout(new GridLayout(4, 1));
		this.add(new JLabel(joueur.obtenirNom()) );
		this.add(new JLabel(" Unite") );
		this.add(new RecapUnite(unite));
		
		JButton finirTour = new JButton("Finir le tour");
		finirTour.setName("FinirTour");
		finirTour.addActionListener(ecouteurBouton);
		
		this.add(finirTour);
	}
	
	public AffichageMenu(Ville ville, Joueur joueur, ActionListener ecouteurBouton) {
		super();
		
		this.setLayout(new GridLayout(6, 1));
		this.add(new JLabel(joueur.obtenirNom()) );
		this.add(new JLabel("Ville") );
		this.add(new RecapUnite(ville));
		
		JButton ameliorerChar = new JButton("Améliorer char ");
		//ameliorerChar.setName("AmeliorerChar");
		//ameliorerChar.addActionListener(ecouteurBouton);
		
		JButton ameliorerVille = new JButton("Améliorer au niveau " + (ville.obtenirNiveau()+1));
		ameliorerVille.setName("AmeliorerVille");
		ameliorerVille.addActionListener(ecouteurBouton);
		
		JButton finirTour = new JButton("Finir le tour");
		finirTour.setName("FinirTour");
		finirTour.addActionListener(ecouteurBouton);
		
		this.add(ameliorerChar);
		this.add(ameliorerVille);
		this.add(finirTour);
	}
	
	
	public void miseAJourDuMenu() {
	}
}
