package fr.projet.java.gestionGraphique;

import fr.projet.java.gestionCarte.Carte;
import fr.projet.java.gestionCarte.Position;
import fr.projet.java.gestionUnite.Chemin;
import fr.projet.java.gestionUnite.Direction;
import fr.projet.java.gestionUnite.TypeUnite;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * @author Romain Affiche la carte passe en parametre dans un JPanel.
 */
public class AffichageCarte extends JPanel {

	private static final long serialVersionUID = 1L;

	private BoutonCarte[][] carteDeBouton;

	/**
	 * Affiche la carte graphiquement.
	 * 
	 * @param carte
	 *            La carte a afficher.
	 * @param auditeurBoutons
	 *            l'auditeur des boutons de la carte.
	 */
	public AffichageCarte(Carte carte, ActionListener auditeurBoutons) {
		super();

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
	 * @param carte La carte qu'il faut mettre ˆ jour.
	 */
	public void mettreAJour(Carte carte) {
		Position positionActuel;
		for (int caseCouranteX = 0; caseCouranteX < Carte.NB_CASES_X; caseCouranteX++)
			for (int caseCouranteY = 0; caseCouranteY < Carte.NB_CASES_Y; caseCouranteY++) {
				positionActuel = new Position(caseCouranteX, caseCouranteY);

				if (!carte.laCaseContientUneUnite(positionActuel)) {

					// La case ne contient rien.
					if (!carte.laCaseContientUneVille(positionActuel)) {
						this.carteDeBouton[caseCouranteX][caseCouranteY]
								.setIcon(new ImageIcon("Images/pelouse.jpeg"));
					}
					// La case contient une ville.
					else {
						this.carteDeBouton[caseCouranteX][caseCouranteY]
								.setIcon(new ImageIcon("Images/ville.jpg"));
					}
				}
				// La case contient une unite.
				else {
					this.carteDeBouton[caseCouranteX][caseCouranteY]
							.setIcon(obtenirLImageDeLUnite(positionActuel,
									carte));
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

	/**
	 * Affiche un chemin sur la carte.
	 * @param cheminAAfficher Le chemin a faire apparaitre sur la carte.
	 */
	public void afficherUnChemin(Chemin cheminAAfficher) {
		Position positionCourante;
		Position positionSuivante;
		Direction directionCaseSuivante, directionPrecedente;

		positionCourante = cheminAAfficher.getPosition(0);
		directionPrecedente = obtenirDirection(positionCourante,
				cheminAAfficher.getPosition(1));

		this.carteDeBouton[positionCourante.getX()][positionCourante.getY()]
				.setIcon(new ImageIcon("Images/chemin/fin/fin"
						+ directionPrecedente + ".png"));

		for (int i = 1; i < cheminAAfficher.getTaille() - 1; i++) {
			positionCourante = cheminAAfficher.getPosition(i);
			positionSuivante = cheminAAfficher.getPosition(i + 1);
			directionCaseSuivante = obtenirDirection(positionCourante,
					positionSuivante);

			switch (directionCaseSuivante) {
			case Bas:
				switch (directionPrecedente) {
				case Bas:
					this.carteDeBouton[positionCourante.getX()][positionCourante
							.getY()].setIcon(new ImageIcon(
							"Images/chemin/ligne/ligneDroiteVerticale.png"));
					break;
				case Gauche:
					this.carteDeBouton[positionCourante.getX()][positionCourante
							.getY()].setIcon(new ImageIcon(
							"Images/chemin/virage/virageBasGauche.png"));
					break;
				case Droite:
					this.carteDeBouton[positionCourante.getX()][positionCourante
							.getY()].setIcon(new ImageIcon(
							"Images/chemin/virage/virageBasDroite.png"));
					break;
				default:
					this.carteDeBouton[positionCourante.getX()][positionCourante
							.getY()].setIcon(new ImageIcon(
							"Images/chemin/erreur/erreurChemin.png"));
					break;
				}
				break;
			case Haut:
				switch (directionPrecedente) {
				case Haut:
					this.carteDeBouton[positionCourante.getX()][positionCourante
							.getY()].setIcon(new ImageIcon(
							"Images/chemin/ligne/ligneDroiteVerticale.png"));
					break;
				case Gauche:
					this.carteDeBouton[positionCourante.getX()][positionCourante
							.getY()].setIcon(new ImageIcon(
							"Images/chemin/virage/virageHautGauche.png"));
					break;
				case Droite:
					this.carteDeBouton[positionCourante.getX()][positionCourante
							.getY()].setIcon(new ImageIcon(
							"Images/chemin/virage/virageHaurDroite.png"));
					break;
				default:
					this.carteDeBouton[positionCourante.getX()][positionCourante
							.getY()].setIcon(new ImageIcon(
							"Images/chemin/erreur/erreurChemin.png"));
					break;
				}
				break;
			case Gauche:
				switch (directionPrecedente) {
				case Bas:
					this.carteDeBouton[positionCourante.getX()][positionCourante
							.getY()].setIcon(new ImageIcon(
							"Images/chemin/virage/virageBasGauche.png"));
					break;
				case Haut:
					this.carteDeBouton[positionCourante.getX()][positionCourante
							.getY()].setIcon(new ImageIcon(
							"Images/chemin/virage/virageHautGauche.png"));
					break;
				case Gauche:
					this.carteDeBouton[positionCourante.getX()][positionCourante
							.getY()].setIcon(new ImageIcon(
							"Images/chemin/ligne/ligneDroiteHorizontale.png"));
					break;
				default:
					this.carteDeBouton[positionCourante.getX()][positionCourante
							.getY()].setIcon(new ImageIcon(
							"Images/chemin/erreur/erreurChemin.png"));
					break;
				}
				break;
			case Droite:
				switch (directionPrecedente) {
				case Bas:
					this.carteDeBouton[positionCourante.getX()][positionCourante
							.getY()].setIcon(new ImageIcon(
							"Images/chemin/virage/virageHautDroite.png"));
					break;
				case Haut:
					this.carteDeBouton[positionCourante.getX()][positionCourante
							.getY()].setIcon(new ImageIcon(
							"Images/chemin/virage/virageBasDroite.png"));
					break;
				case Droite:
					this.carteDeBouton[positionCourante.getX()][positionCourante
							.getY()].setIcon(new ImageIcon(
							"Images/chemin/ligne/ligneDroiteHorizontale.png"));
					break;
				default:
					this.carteDeBouton[positionCourante.getX()][positionCourante
							.getY()].setIcon(new ImageIcon(
							"Images/chemin/erreur/erreurChemin.png"));
					break;
				}
				break;
			default:
				this.carteDeBouton[positionCourante.getX()][positionCourante
						.getY()].setIcon(new ImageIcon(
						"Images/chemin/erreur/erreurChemin.png"));
				break;
			}

			directionPrecedente = directionCaseSuivante;
		}
		switch (directionPrecedente) {
		case Bas:
			this.carteDeBouton[positionCourante.getX()][positionCourante.getY()]
					.setIcon(new ImageIcon("Images/chemin/fin/finHaut.png"));
			break;
		case Gauche:
			this.carteDeBouton[positionCourante.getX()][positionCourante.getY()]
					.setIcon(new ImageIcon("Images/chemin/fin/finDroite.png"));
			break;
		case Droite:
			this.carteDeBouton[positionCourante.getX()][positionCourante.getY()]
					.setIcon(new ImageIcon("Images/chemin/fin/finGauche.png"));
			break;
		case Haut:
			this.carteDeBouton[positionCourante.getX()][positionCourante.getY()]
					.setIcon(new ImageIcon("Images/chemin/fin/finBas.png"));
			break;
		default:
			this.carteDeBouton[positionCourante.getX()][positionCourante.getY()]
					.setIcon(new ImageIcon(
							"Images/chemin/erreur/erreurChemin.png"));
			break;
		}
	}

	private Direction obtenirDirection(Position positionCourante,
			Position positionSuivante) {
		Direction directionCaseSuivante = null;
		if (positionSuivante.equals(new Position(positionCourante.getX() + 1,
				positionCourante.getY())))
			directionCaseSuivante = Direction.Bas;
		if (positionSuivante.equals(new Position(positionCourante.getX() - 1,
				positionCourante.getY())))
			directionCaseSuivante = Direction.Haut;
		if (positionSuivante.equals(new Position(positionCourante.getX(),
				positionCourante.getY() + 1)))
			directionCaseSuivante = Direction.Droite;
		if (positionSuivante.equals(new Position(positionCourante.getX(),
				positionCourante.getY() - 1)))
			directionCaseSuivante = Direction.Gauche;
		return directionCaseSuivante;
	}

}
