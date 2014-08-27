package fr.projet.java.IntelligenceArtificiel;

import fr.projet.java.IntelligenceArtificiel.ActionIA;
import fr.projet.java.gestionCarte.Position;
import fr.projet.java.logiqueDuJeu.ActionUnite;
import fr.projet.java.logiqueDuJeu.ActionVille;

/**
 * @author Romain Une action de l'IA dans son detail.
 */
public class ChoixIA {

	/**
	 * @author Romain La liste des actions possible pour l'IA.
	 */
	private enum ListeChoix {
		selectionnerPosition, selectionnerDeplacement, deplacerUnite, ameliorerVille, construireUnite, ameliorerUnite, deselectionnerVille, deselectionnerUnite;
	}

	/**
	 * Liste des actions a realiser pour creer une action IA.
	 */
	private ListeChoix[] actionARealiser;
	private int actionCourante = 0;

	private boolean actionTermine;

	private Position positionDeLaSelection;
	private Position positionDeLArriver;

	/**
	 * @param action
	 *            L'action a realiser par l'IA.
	 * @param positionDepart
	 *            La position de l'objet sur lequel on effectue l'action.
	 * @param positionDeplacement
	 *            La position ou l'on deplace l'unite. ( Null si pas de
	 *            deplacement )
	 */
	public ChoixIA(ActionIA action, Position positionDepart,
			Position positionDeplacement) {
		this.actionTermine = false;

		this.positionDeLaSelection = positionDepart;
		this.positionDeLArriver = positionDeplacement;

		// Decoupage de l'action en plusieurs sous-actions realisable.
		switch (action) {
		case construireUnite:
			actionARealiser = new ListeChoix[2];
			actionARealiser[0] = ListeChoix.selectionnerPosition;
			actionARealiser[1] = ListeChoix.construireUnite;
			break;
		case deplacerUnite:
			actionARealiser = new ListeChoix[4];
			actionARealiser[0] = ListeChoix.selectionnerPosition;
			actionARealiser[1] = ListeChoix.deplacerUnite;
			actionARealiser[2] = ListeChoix.selectionnerDeplacement;
			actionARealiser[3] = ListeChoix.selectionnerDeplacement;
			break;
		case attaquerUnite:
			actionARealiser = new ListeChoix[3];
			actionARealiser[0] = ListeChoix.selectionnerPosition;
			actionARealiser[1] = ListeChoix.deplacerUnite;
			actionARealiser[2] = ListeChoix.selectionnerDeplacement;
			break;
		case ameliorerUnite:
			actionARealiser = new ListeChoix[2];
			actionARealiser[0] = ListeChoix.selectionnerPosition;
			actionARealiser[1] = ListeChoix.ameliorerUnite;
			break;
		case ameliorerVille:
			actionARealiser = new ListeChoix[2];
			actionARealiser[0] = ListeChoix.selectionnerPosition;
			actionARealiser[1] = ListeChoix.ameliorerVille;
			break;
		case neRienFaireVille:
			actionARealiser = new ListeChoix[2];
			actionARealiser[0] = ListeChoix.selectionnerPosition;
			actionARealiser[1] = ListeChoix.deselectionnerVille;
			break;
		case neRienFaireUnite:
			actionARealiser = new ListeChoix[2];
			actionARealiser[0] = ListeChoix.selectionnerPosition;
			actionARealiser[1] = ListeChoix.deselectionnerUnite;
			break;
		default:
			break;
		}
	}

	/**
	 * Renvoi l'action a realiser.
	 * 
	 * @return Un objet correspondant a l'action en cours.
	 */
	public Object retourAction() {
		Object actionAFaire = null;

		// Association des sous action avec les actions villes et les actions
		// unites.
		switch (this.actionARealiser[this.actionCourante]) {
		case selectionnerPosition:
			actionAFaire = this.positionDeLaSelection;
			break;
		case ameliorerVille:
			actionAFaire = ActionVille.Ameliorer;
			break;
		case ameliorerUnite:
			actionAFaire = ActionUnite.Ameliorer;
			break;
		case construireUnite:
			actionAFaire = ActionVille.CreerUnite;
			break;
		case deplacerUnite:
			actionAFaire = ActionUnite.Deplacer;
			break;
		case selectionnerDeplacement:
			actionAFaire = this.positionDeLArriver;
			break;
		case deselectionnerVille:
			actionAFaire = ActionVille.Deselectionner;
			break;
		case deselectionnerUnite:
			actionAFaire = ActionUnite.Deselectionner;
			break;
		default:
			break;
		}
		this.actionCourante++;

		if (this.actionCourante >= this.actionARealiser.length)
			this.actionTermine = true;

		return actionAFaire;
	}

	/**
	 * Si l'action entreprise et terminer ou non.
	 * 
	 * @return Vrai si l'action est terminer.
	 */
	public boolean actionTermine() {
		return this.actionTermine;
	}
}
