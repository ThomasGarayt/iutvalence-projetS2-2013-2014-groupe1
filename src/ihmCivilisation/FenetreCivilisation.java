package ihmCivilisation;

import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;

import systemeCivilisation.*;

public class FenetreCivilisation {

	private JSplitPane splitPane;
	private AffichageMenu menu;
	private ActionListener ecouteurDeBouton;

	public FenetreCivilisation(ActionListener ecouteurDeBouton) {
		this.ecouteurDeBouton = ecouteurDeBouton;
	}

	public void initialiserFenetreCivilisation(Carte carte, Joueur joueur, ActionListener ecouteurBouton) {
		JFrame fenetre = new JFrame("Civilisation II");
		fenetre.setSize(700, 600);
		fenetre.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		fenetre.setResizable(false);

		JPanel carteDuJeu = new AffichageCarte(carte, ecouteurDeBouton);

		menu = new AffichageMenu(joueur, ecouteurBouton);

		this.splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

		this.splitPane.add(menu);

		this.splitPane.add(carteDuJeu);

		this.splitPane.setEnabled(false);

		this.splitPane.setBorder(null);

		this.splitPane.setDividerSize(0);

		this.splitPane.setResizeWeight(0);

		fenetre.add(splitPane);
		fenetre.setVisible(true);

	}

	public void mettreAJourLaCarte(Carte carte, ActionListener ecouteurBouton) {
		AffichageCarte carteDuMonde = new AffichageCarte(carte, ecouteurDeBouton);
		this.splitPane.setRightComponent(carteDuMonde);
	}

	public void mettreAJourLeMenu(Unite unite, Joueur joueur, ActionListener ecouteurBouton) {
		this.menu = new AffichageMenu(unite, joueur,ecouteurBouton);
		this.splitPane.setLeftComponent(this.menu);
	}
	
	public void mettreAJourLeMenu(Ville ville, Joueur joueur, ActionListener ecouteurBouton) {
		this.menu = new AffichageMenu(ville, joueur,ecouteurBouton);
		this.splitPane.setLeftComponent(this.menu);
	}

	public void mettreAJourLeMenu(Joueur joueur, ActionListener ecouteurBouton) {
		this.menu = new AffichageMenu(joueur,ecouteurBouton);
		this.splitPane.setLeftComponent(menu);
	}

	public JSplitPane obtenirMenu() {
		return this.splitPane;
	}

}
