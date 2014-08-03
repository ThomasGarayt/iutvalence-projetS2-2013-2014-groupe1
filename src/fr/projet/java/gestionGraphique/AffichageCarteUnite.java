package fr.projet.java.gestionGraphique;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import fr.projet.java.gestionCarte.Carte;
import fr.projet.java.gestionCarte.Position;
import fr.projet.java.gestionUnite.TypeUnite;

/**
 * @author Romain Affiche les unites d'une carte dans un JPanel.
 */
public class AffichageCarteUnite extends JPanel {

	private BoutonCarte[][] carteDeBouton;

	/**
	 * Creer et affiche une carte des unites.
	 * @param carte La carte a afficher.
	 * @param auditeurBoutons L'action listeneur des boutons de la carte.
	 */
	public AffichageCarteUnite(Carte carte, ActionListener auditeurBoutons) {
		carteDeBouton = new BoutonCarte[Carte.NB_CASES_X][Carte.NB_CASES_Y];

		for (int caseCouranteX = 0; caseCouranteX < Carte.NB_CASES_X; caseCouranteX++)
			for (int caseCouranteY = 0; caseCouranteY < Carte.NB_CASES_Y; caseCouranteY++)
				this.carteDeBouton[caseCouranteX][caseCouranteY] = new BoutonCarte(
						caseCouranteX, caseCouranteY, null, auditeurBoutons);

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
	 *            La carte qu'il faut mettre ˆ jour.
	 */
	public void mettreAJour(Carte carte) {
		Position positionActuel;
		for (int caseCouranteX = 0; caseCouranteX < Carte.NB_CASES_X; caseCouranteX++)
			for (int caseCouranteY = 0; caseCouranteY < Carte.NB_CASES_Y; caseCouranteY++) {
				positionActuel = new Position(caseCouranteX, caseCouranteY);
				// La case contient une unite.
				if (carte.laCaseContientUneUnite(positionActuel)) {
					this.carteDeBouton[caseCouranteX][caseCouranteY]
							.setIcon(obtenirLImageDeLUnite(positionActuel,
									carte));
				}
				else {
					this.carteDeBouton[caseCouranteX][caseCouranteY]
							.setIcon(new ImageIcon(""));
				}

			}
	}

	private ImageIcon obtenirLImageDeLUnite(Position position, Carte carte) {
		if (carte.obtenirLUniteDeLaCase(position).obtenirTypeUnite() == TypeUnite.Soldats)
			return new ImageIcon(carte.obtenirLUniteDeLaCase(position)
					.obtenirJoueur().obtenirLesetDimagesDeLaNation()
					.obtenirLImageDuSoldat());
		return new ImageIcon(carte.obtenirLUniteDeLaCase(position)
				.obtenirJoueur().obtenirLesetDimagesDeLaNation()
				.obtenirLImageDuChar());
	}
}
