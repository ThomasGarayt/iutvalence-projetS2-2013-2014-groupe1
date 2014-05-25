package ihmCivilisation;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import systemeCivilisation.Carte;
import systemeCivilisation.Position;
import systemeCivilisation.TypeUnite;

public class AffichageCarte extends JPanel {

	public AffichageCarte(Carte carte, ActionListener auditeurBoutons) {
		super();

		BoutonCarte boutonCourant;
		Position positionActuel;

		this.setLayout(new GridLayout(Carte.NB_CASES_X, Carte.NB_CASES_Y));

		for (int caseCouranteX = 0; caseCouranteX < Carte.NB_CASES_X; caseCouranteX++)

			for (int caseCouranteY = 0; caseCouranteY < Carte.NB_CASES_Y; caseCouranteY++) {

				positionActuel = new Position(caseCouranteX, caseCouranteY);

				if (carte.laCaseNecontientPasDUnite(positionActuel)) {

					// La case ne contient rien.
					if (carte.laCaseNeContientPasDeVille(positionActuel)) {
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
				// La case contient une unitŽ.
				else {
					boutonCourant = new BoutonCarte(caseCouranteX,
								caseCouranteY, obtenirLImageDeLUnite(positionActuel, carte),auditeurBoutons);
					this.add(boutonCourant);
				}

			}
	}

	private ImageIcon obtenirLImageDeLUnite(Position position, Carte carte) {

		if (carte.obtenirLUniteDeLaCase(position).obtenirTypeUnite() == TypeUnite.Soldats)
			return new ImageIcon(carte.obtenirLUniteDeLaCase(position)
					.obtenirJoueur().obtenirLesetDimagesDuJoueur()
					.obtenirLImageDuSoldat());
		else
			return new ImageIcon(carte.obtenirLUniteDeLaCase(position)
					.obtenirJoueur().obtenirLesetDimagesDuJoueur()
					.obtenirLImageDuChar());
	}

}
