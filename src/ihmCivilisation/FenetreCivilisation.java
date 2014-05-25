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

	public void initialiserFenetreCivilisation(Carte carte) {
		JFrame fenetre = new JFrame("Civilisation II");
		fenetre.setSize(700, 600);
		fenetre.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		fenetre.setResizable(false);

		JPanel carteDuJeu = new AffichageCarte(carte, ecouteurDeBouton);

		menu = new AffichageMenu();

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

	public void mettreAJourLaCarte(Carte carte) {
		AffichageCarte carteDuMonde = new AffichageCarte(carte, ecouteurDeBouton);
		this.splitPane.setRightComponent(carteDuMonde);
	}

	public void mettreAJourLeMenu(Unite unite) {
		this.splitPane.setLeftComponent(new AffichageMenu(unite));
	}

	public void mettreAJourLeMenu() {
		this.splitPane.setLeftComponent(new AffichageMenu());
		
	}

}
