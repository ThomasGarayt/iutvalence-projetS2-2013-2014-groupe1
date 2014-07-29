package gestionGraphique;

import gestionCarte.Carte;
import gestionCarte.Position;
import gestionUnite.TypeUnite;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * @author Romain Affiche la carte passe en parametre dans un JPanel.
 */
public class AffichageCarte extends JPanel {


	private static final long serialVersionUID = 1L;

	/**
	 * Affiche la carte graphiquement.
	 * 
	 * @param carte
	 *            La carte à afficher.
	 * @param auditeurBoutons
	 *            l'auditeur des boutons de la carte.
	 */
	public AffichageCarte(Carte carte, ActionListener auditeurBoutons) {
		super();

		BoutonCarte boutonCourant;
		Position positionActuel;

		this.setLayout(new GridLayout(Carte.NB_CASES_X, Carte.NB_CASES_Y));

		for (int caseCouranteX = 0; caseCouranteX < Carte.NB_CASES_X; caseCouranteX++)

			for (int caseCouranteY = 0; caseCouranteY < Carte.NB_CASES_Y; caseCouranteY++) {

				positionActuel = new Position(caseCouranteX, caseCouranteY);

				if (!carte.laCaseContientUneUnite(positionActuel)) {

					// La case ne contient rien.
					if (!carte.laCaseContientUneVille(positionActuel)) {
						boutonCourant = new BoutonCarte(caseCouranteX,
								caseCouranteY, new ImageIcon(
										"Images/pelouse.jpeg"), auditeurBoutons);
						this.add(boutonCourant);
					}
					// La case contient une ville.
					else {
						boutonCourant = new BoutonCarte(caseCouranteX,
								caseCouranteY,
								new ImageIcon("Images/ville.jpg"),
								auditeurBoutons);
						this.add(boutonCourant);
					}
				}
				// La case contient une unite.
				else {
					boutonCourant = new BoutonCarte(caseCouranteX,
							caseCouranteY, obtenirLImageDeLUnite(
									positionActuel, carte), auditeurBoutons);
					this.add(boutonCourant);
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
