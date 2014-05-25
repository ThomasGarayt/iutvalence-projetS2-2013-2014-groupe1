package ihmCivilisation;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import systemeCivilisation.Carte;
import systemeCivilisation.Position;

public class AffichageCarte extends JPanel {
	
	public AffichageCarte(Carte carte, ActionListener auditeurBoutons) {
		super();
		
		this.setLayout(new GridLayout(Carte.NB_CASES_X, Carte.NB_CASES_Y));
		
		for (int caseCouranteX = 0; caseCouranteX < Carte.NB_CASES_X; caseCouranteX ++)
			
			for (int caseCouranteY = 0; caseCouranteY < Carte.NB_CASES_Y; caseCouranteY ++){
				
				if (carte.laCaseNecontientPasDUnite(new Position(caseCouranteX,caseCouranteY)))
					
					if (carte.laCaseNeContientPasDeVille(new Position(caseCouranteX,caseCouranteY)))
						this.add(new BoutonCarte(caseCouranteX,caseCouranteY, new ImageIcon("Images/pelouse.jpeg"), auditeurBoutons));
					
					else this.add(new BoutonCarte(caseCouranteX,caseCouranteY, new ImageIcon("Images/ville.jpg"), auditeurBoutons));
				
				else this.add(new BoutonCarte(caseCouranteX,caseCouranteY, new ImageIcon("Images/soldat.png"), auditeurBoutons));
			}
	}
}
