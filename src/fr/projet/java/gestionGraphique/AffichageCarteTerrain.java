package fr.projet.java.gestionGraphique;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import fr.projet.java.gestionCarte.Carte;
import fr.projet.java.gestionCarte.Position;

/**
 * @author Romain Affiche le terrain et les villes d'une carte dans un JPanel.
 */
public class AffichageCarteTerrain extends JPanel {

	private BoutonCarte[][] carteDeBouton;

	/**
	 * Creer et affiche la carte des terrain et des villes.
	 * 
	 * @param carte
	 *            La carte a afficher.
	 * @param auditeurBoutons
	 *            L'actionListener des boutons de la carte.
	 */
	public AffichageCarteTerrain(Carte carte, ActionListener auditeurBoutons) {

		carteDeBouton = new BoutonCarte[Carte.NB_CASES_X][Carte.NB_CASES_Y];

		for (int caseCouranteX = 0; caseCouranteX < Carte.NB_CASES_X; caseCouranteX++)
			for (int caseCouranteY = 0; caseCouranteY < Carte.NB_CASES_Y; caseCouranteY++) {
				carteDeBouton[caseCouranteX][caseCouranteY] = new BoutonCarte(
						caseCouranteX, caseCouranteX, new ImageIcon(),
						auditeurBoutons);
			}

		GridLayout gridLayout = new GridLayout(Carte.NB_CASES_X,
				Carte.NB_CASES_Y);

		this.setLayout(gridLayout);

		for (int caseCouranteX = 0; caseCouranteX < Carte.NB_CASES_X; caseCouranteX++)
			for (int caseCouranteY = 0; caseCouranteY < Carte.NB_CASES_Y; caseCouranteY++) {
				this.add(carteDeBouton[caseCouranteX][caseCouranteY]);
			}

		mettreAJour(carte);
	}

	/**
	 * Met a jour la carte.
	 * 
	 * @param carte
	 *            La carte.
	 */
	public void mettreAJour(Carte carte) {
		Position positionActuel;
		for (int caseCouranteX = 0; caseCouranteX < Carte.NB_CASES_X; caseCouranteX++)
			for (int caseCouranteY = 0; caseCouranteY < Carte.NB_CASES_Y; caseCouranteY++) {
				positionActuel = new Position(caseCouranteX, caseCouranteY);
				// La case contient une ville.
				if (carte.laCaseContientUneVille(positionActuel)) {
					if (carte.obtenirLaVilleDeLaCase(positionActuel)
							.obtenirJoueurProprietaire() != null)
						this.carteDeBouton[caseCouranteX][caseCouranteY]
								.setImage(new ImageIcon(carte
										.obtenirLaVilleDeLaCase(positionActuel)
										.obtenirJoueurProprietaire()
										.obtenirLesetDimagesDeLaNation()
										.obtenirLImageDeLaVille()));
					else
						this.carteDeBouton[caseCouranteX][caseCouranteY]
								.setImage(new ImageIcon(
										"Images/Ville/ville_libre.png"));
				} else
					this.carteDeBouton[caseCouranteX][caseCouranteY]
							.setImage(new ImageIcon("Images/"
									+ carte.obtenirTerrain(positionActuel)
									+ ".jpeg"));
			}
	}
}
