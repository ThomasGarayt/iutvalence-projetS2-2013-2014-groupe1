package fr.projet.java.gestionGraphique;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import fr.projet.java.gestionCarte.Carte;
import fr.projet.java.gestionCarte.Position;
import fr.projet.java.gestionUnite.Chemin;
import fr.projet.java.gestionUnite.Direction;

/**
 * @author Romain Affiche les chemins d'une carte dans un JPanel.
 */
public class AffichageCarteChemin extends JPanel {

	private BoutonCarte[][] carteDeBouton;

	/**
	 * Creer une carte des chemins et l'affiche.
	 * 
	 * @param auditeurBoutons
	 *            L'actionListener des boutons de la carte.
	 */
	public AffichageCarteChemin(ActionListener auditeurBoutons) {
		carteDeBouton = new BoutonCarte[Carte.NB_CASES_X][Carte.NB_CASES_Y];

		for (int caseCouranteX = 0; caseCouranteX < Carte.NB_CASES_X; caseCouranteX++)
			for (int caseCouranteY = 0; caseCouranteY < Carte.NB_CASES_Y; caseCouranteY++)
				this.carteDeBouton[caseCouranteX][caseCouranteY] = new BoutonCarte(
						caseCouranteX, caseCouranteY, new ImageIcon(), auditeurBoutons);

		GridLayout gridLayout = new GridLayout(Carte.NB_CASES_X,
				Carte.NB_CASES_Y);

		this.setLayout(gridLayout);

		for (int caseCouranteX = 0; caseCouranteX < Carte.NB_CASES_X; caseCouranteX++)
			for (int caseCouranteY = 0; caseCouranteY < Carte.NB_CASES_Y; caseCouranteY++) {
				this.add(carteDeBouton[caseCouranteX][caseCouranteY]);
			}
	}

	/**
	 * Affiche un chemin sur la carte.
	 * 
	 * @param cheminAAfficher
	 *            Le chemin a faire apparaitre sur la carte.
	 * @param nombreDeCasePossible
	 *            Le nombre de case a afficher en bleu.
	 */
	public void afficherUnChemin(Chemin cheminAAfficher,
			int nombreDeCasePossible) {

		if (cheminAAfficher == null) {
			reinitialiserLaCarte();
			return;
		}

		String couleurChemin = "bleu";
		Position positionCourante;
		Position positionSuivante;
		Direction directionCaseSuivante, directionPrecedente;

		positionCourante = cheminAAfficher.getPosition(0);
		directionPrecedente = obtenirDirection(positionCourante,
				cheminAAfficher.getPosition(1));

		this.carteDeBouton[positionCourante.getX()][positionCourante.getY()]
				.setImage(new ImageIcon("Images/chemin/bleu/fin/fin"
						+ directionPrecedente + ".png"));

		for (int i = 1; i < cheminAAfficher.getTaille() - 1; i++) {
			if (nombreDeCasePossible >= i)
				couleurChemin = "bleu";
			else
				couleurChemin = "rouge";

			positionCourante = cheminAAfficher.getPosition(i);
			positionSuivante = cheminAAfficher.getPosition(i + 1);
			directionCaseSuivante = obtenirDirection(positionCourante,
					positionSuivante);

			switch (directionCaseSuivante) {
			case Bas:
				switch (directionPrecedente) {
				case Bas:
					this.carteDeBouton[positionCourante.getX()][positionCourante
							.getY()]
							.setImage(new ImageIcon("Images/chemin/"
									+ couleurChemin
									+ "/ligne/ligneDroiteVerticale.png"));
					break;
				case Gauche:
					this.carteDeBouton[positionCourante.getX()][positionCourante
							.getY()].setImage(new ImageIcon("Images/chemin/"
							+ couleurChemin + "/virage/virageBasDroite.png"));
					break;
				case Droite:
					this.carteDeBouton[positionCourante.getX()][positionCourante
							.getY()].setImage(new ImageIcon("Images/chemin/"
							+ couleurChemin + "/virage/virageBasGauche.png"));
					break;
				default:
					this.carteDeBouton[positionCourante.getX()][positionCourante
							.getY()].setImage(new ImageIcon("Images/chemin/"
							+ couleurChemin + "/erreur/erreurChemin.png"));
					break;
				}
				break;
			case Haut:
				switch (directionPrecedente) {
				case Haut:
					this.carteDeBouton[positionCourante.getX()][positionCourante
							.getY()]
							.setImage(new ImageIcon("Images/chemin/"
									+ couleurChemin
									+ "/ligne/ligneDroiteVerticale.png"));
					break;
				case Gauche:
					this.carteDeBouton[positionCourante.getX()][positionCourante
							.getY()].setImage(new ImageIcon("Images/chemin/"
							+ couleurChemin + "/virage/virageHautDroite"
							+ ".png"));
					break;
				case Droite:
					this.carteDeBouton[positionCourante.getX()][positionCourante
							.getY()].setImage(new ImageIcon("Images/chemin/"
							+ couleurChemin + "/virage/virageHautGauche.png"));
					break;
				default:
					this.carteDeBouton[positionCourante.getX()][positionCourante
							.getY()].setImage(new ImageIcon("Images/chemin/"
							+ couleurChemin + "/erreur/erreurChemin.png"));
					break;
				}
				break;
			case Gauche:
				switch (directionPrecedente) {
				case Bas:
					this.carteDeBouton[positionCourante.getX()][positionCourante
							.getY()].setImage(new ImageIcon("Images/chemin/"
							+ couleurChemin + "/virage/virageHautGauche.png"));
					break;
				case Haut:
					this.carteDeBouton[positionCourante.getX()][positionCourante
							.getY()].setImage(new ImageIcon("Images/chemin/"
							+ couleurChemin + "/virage/virageHautGauche.png"));
					break;
				case Gauche:
					this.carteDeBouton[positionCourante.getX()][positionCourante
							.getY()].setImage(new ImageIcon("Images/chemin/"
							+ couleurChemin
							+ "/ligne/ligneDroiteHorizontale.png"));
					break;
				default:
					this.carteDeBouton[positionCourante.getX()][positionCourante
							.getY()].setImage(new ImageIcon("Images/chemin/"
							+ couleurChemin + "/erreur/erreurChemin.png"));
					break;
				}
				break;
			case Droite:
				switch (directionPrecedente) {
				case Bas:
					this.carteDeBouton[positionCourante.getX()][positionCourante
							.getY()].setImage(new ImageIcon("Images/chemin/"
							+ couleurChemin + "/virage/virageHautDroite.png"));
					break;
				case Haut:
					this.carteDeBouton[positionCourante.getX()][positionCourante
							.getY()].setImage(new ImageIcon("Images/chemin/"
							+ couleurChemin + "/virage/virageBasDroite.png"));
					break;
				case Droite:
					this.carteDeBouton[positionCourante.getX()][positionCourante
							.getY()].setImage(new ImageIcon("Images/chemin/"
							+ couleurChemin
							+ "/ligne/ligneDroiteHorizontale.png"));
					break;
				default:
					this.carteDeBouton[positionCourante.getX()][positionCourante
							.getY()].setImage(new ImageIcon("Images/chemin/"
							+ couleurChemin + "/erreur/erreurChemin.png"));
					break;
				}
				break;
			default:
				this.carteDeBouton[positionCourante.getX()][positionCourante
						.getY()].setImage(new ImageIcon("Images/chemin/"
						+ couleurChemin + "/erreur/erreurChemin.png"));
				break;
			}

			directionPrecedente = directionCaseSuivante;
		}

		positionCourante = cheminAAfficher.getPosition(cheminAAfficher
				.getTaille() - 1);
		if (cheminAAfficher.getTaille() - 1 > nombreDeCasePossible)
			couleurChemin = "rouge";
		else
			couleurChemin = "bleu";

		switch (directionPrecedente) {
		case Bas:
			this.carteDeBouton[positionCourante.getX()][positionCourante.getY()]
					.setImage(new ImageIcon("Images/chemin/" + couleurChemin
							+ "/fin/finHaut.png"));
			break;
		case Gauche:
			this.carteDeBouton[positionCourante.getX()][positionCourante.getY()]
					.setImage(new ImageIcon("Images/chemin/" + couleurChemin
							+ "/fin/finDroite.png"));
			break;
		case Droite:
			this.carteDeBouton[positionCourante.getX()][positionCourante.getY()]
					.setImage(new ImageIcon("Images/chemin/" + couleurChemin
							+ "/fin/finGauche.png"));
			break;
		case Haut:
			this.carteDeBouton[positionCourante.getX()][positionCourante.getY()]
					.setImage(new ImageIcon("Images/chemin/" + couleurChemin
							+ "/fin/finBas.png"));
			break;
		default:
			this.carteDeBouton[positionCourante.getX()][positionCourante.getY()]
					.setImage(new ImageIcon("Images/chemin/" + couleurChemin
							+ "/erreur/erreurChemin.png"));
			break;
		}
	}

	private void reinitialiserLaCarte() {
		for (int caseCouranteX = 0; caseCouranteX < Carte.NB_CASES_X; caseCouranteX++)
			for (int caseCouranteY = 0; caseCouranteY < Carte.NB_CASES_Y; caseCouranteY++) {
				this.carteDeBouton[caseCouranteX][caseCouranteY]
						.setImage(new ImageIcon(""));
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
