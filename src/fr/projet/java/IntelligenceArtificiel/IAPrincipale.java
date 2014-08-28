package fr.projet.java.IntelligenceArtificiel;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;

import fr.projet.java.exception.*;
import fr.projet.java.gestionCarte.*;
import fr.projet.java.gestionUnite.*;
import fr.projet.java.logiqueDuJeu.*;

//TODO unitesDeLaNation - Nouvel objet contenant l'unite et l'etat du test.

/**
 * @author Romain La classe principale de l'IA, elle rassemble les informations
 *         principale et prend les decisions.
 */
public class IAPrincipale implements Joueur {

	private static final int NOMBRE_UNITE_MAX = 5;

	private ActionIA actionEnCour;

	private Carte carte;
	private Nation nation;

	private Vector<Unite> unitesDeLaNation;
	private Vector<Ville> villesDeLaNation;

	private boolean[] unitesTeste;
	private boolean[] villesTeste;

	private boolean premiereActionDuTour;

	/**
	 * Creer une IA.
	 * 
	 * @param nation
	 *            La nation de l'IA
	 */
	public IAPrincipale(Nation nation) {
		this.nation = nation;

		this.unitesDeLaNation = new Vector<Unite>();
		this.villesDeLaNation = new Vector<Ville>();

		this.premiereActionDuTour = true;
	}

	/**
	 * Actualiser les ressources dont dispose l'IA. Les villes et les unites.
	 * Remet a zero les unites ayant deja ete deplacer et les villes dont l'IA
	 * c'est occupee.
	 */
	private void actualiserLesResources() {
		// Les unites et les villes de toutes la carte
		Vector<Unite> unitesRecuperer;
		Vector<Ville> villesRecuperer;

		villesRecuperer = carte.obtenirVillesDeLaCarte();
		unitesRecuperer = carte.obtenirUnitesDeLaCarte();

		Iterator<Ville> villes = villesRecuperer.iterator();
		Iterator<Unite> unites = unitesRecuperer.iterator();

		Ville villeCourante;
		Unite uniteCourante;

		// On vide les listes des unites de l'IA.
		this.unitesDeLaNation.removeAllElements();
		this.villesDeLaNation.removeAllElements();

		// On parcourt les villes de la carte et on garde celles dont on est le
		// proprietaire.
		while (villes.hasNext()) {
			villeCourante = villes.next();
			if (villeCourante.obtenirJoueurProprietaire() != null)
				if (villeCourante.obtenirJoueurProprietaire().equals(
						this.nation))
					this.villesDeLaNation.add(villeCourante);
		}

		// De meme pour les unites.
		while (unites.hasNext()) {
			uniteCourante = unites.next();
			if (uniteCourante.obtenirJoueur().equals(this.nation))
				this.unitesDeLaNation.add(uniteCourante);
		}

		// Nouveaux tableaux de booleens avec la bonne taille.
		this.unitesTeste = new boolean[this.unitesDeLaNation.size()];
		Arrays.fill(unitesTeste, false);
		this.villesTeste = new boolean[this.villesDeLaNation.size()];
		Arrays.fill(villesTeste, false);
	}

	/**
	 * Associer la carte a l'IA.
	 * 
	 * @param carte
	 *            La carte du jeu.
	 */
	public void associerLaCarte(Carte carte) {
		this.carte = carte;
	}

	/**
	 * L'IA choisi la prochaine action a realiser.
	 * 
	 * @throws FinDuTourException
	 *             Fin du tour.
	 */
	private void choixDeLaProchaineAction() throws FinDuTourException {
		// On parcourt les villes et on voit si il y en a dont on ne s'est pas
		// occupe.
		int i = 0;
		if (this.villesTeste.length != 0) {
			while (this.villesTeste[i]) {
				i++;
				if (i >= this.villesTeste.length)
					break;
			}
			// Si il y en a on s'en occupe.
			if (i < this.villesTeste.length) {
				Ville ville = this.villesDeLaNation.get(i);
				Position positionVille = this.carte.trouverVille(ville);
				// Si la tresorerie le permet (et qu'on n'a pas encore atteint
				// le nombre maximum d'unite), on construit une unite.
				if (this.nation.obtenirTresorerie() > TypeUnite.Soldats
						.getCoutCreation()
						&& !(this.carte.laCaseContientUneUnite(positionVille))
						&& this.unitesDeLaNation.size() <= NOMBRE_UNITE_MAX) {
					this.actionEnCour = new ActionIA(TypeActionIA.construireUnite,
							positionVille, null);
					this.villesTeste[i] = true;
					return;
				}
				// Sinon on ne fait rien.
				else if (!this.carte.laCaseContientUneUnite(positionVille)) {
					this.actionEnCour = new ActionIA(TypeActionIA.neRienFaireVille,
							positionVille, null);
					this.villesTeste[i] = true;
					return;
				}
				// Gestion du cas ou une unite est sur la ville.
				else
					;
				this.villesTeste[i] = true;
			}
		}

		if (this.unitesTeste.length != 0) {
			// On parcourt le tableau des unites.
			i = 0;
			while (this.unitesTeste[i]) {
				i++;
				if (i >= this.unitesTeste.length)
					break;
			}
			// Et on s'en occupe.
			if (i < this.unitesTeste.length) {
				// On recupere la position de l'unite
				ComparaisonDistance type;
				Unite unite = this.unitesDeLaNation.get(i);
				Position positionUnite = this.carte.trouverUnite(unite);
				Position positionDeplacement = positionUnite;

				if (attaquerEstPossible(unite, positionUnite)) return;

				// On regarde les villes les plus proche.
				Vector<ComparaisonDistance> distanceVille = this.carte
						.distanceVille(positionUnite);
				Iterator<ComparaisonDistance> it = distanceVille.iterator();
				while (it.hasNext()) {
					type = (ComparaisonDistance) it.next();
					// On prend la ville la plus proche, si elle appartient a
					// l'IA
					// on regarde la suivante.
					if (this.carte.obtenirLaVilleDeLaCase(type.getPosition())
							.obtenirJoueurProprietaire() != this.nation) {
						try {
							// Si elle n'appartient pas a l'IA, on s'en
							// approche.
							Chemin chemin = this.carte.obtenirUnChemin(
									positionUnite, type.getPosition());

							int pm;
							if (chemin.getTaille() > unite
									.obtenirPointDeMouvements())
								pm = unite.obtenirPointDeMouvements();
							// Ou on essai de la prendre.
							else
								pm = chemin.getTaille() - 1;
							positionDeplacement = chemin.getPosition(pm);
						} catch (CheminImpossibleException e) {
						}
						break;
					}
				}
				// Sauvegarde de l'action choisi.

				// On ne fait rien, toutes les villes accessible sont a l'IA.
				if (positionUnite == positionDeplacement)
					this.actionEnCour = new ActionIA(TypeActionIA.neRienFaireUnite,
							positionUnite, null);
				// On se rapproche d'une ville.
				else
					this.actionEnCour = new ActionIA(TypeActionIA.deplacerUnite,
							positionUnite, positionDeplacement);

				// L'unite a ete teste.
				this.unitesTeste[i] = true;
				return;
			}
		}
		// Si on c'est occupee de toutes les villes et de toutes les unites, on
		// declare la fin du tour
		declarerLaFinDuTour();
	}

	private boolean attaquerEstPossible(Unite unite, Position positionUnite) {
		// On regarde les unites ennemis les plus proche.
		Vector<ComparaisonDistance> distanceUnite = this.carte
				.distanceUnite(positionUnite);
		Iterator<ComparaisonDistance> iterateurUnite = distanceUnite
				.iterator();
		while (iterateurUnite.hasNext()) {
			ComparaisonDistance comparaisonDistance = (ComparaisonDistance) iterateurUnite
					.next();
			if (comparaisonDistance.getDistance() > unite
					.obtenirPorte()
					|| (this.carte.obtenirLUniteDeLaCase(
							comparaisonDistance.getPosition())
							.obtenirJoueur().equals(this.nation)))
				;
			else if (unite.obtenirNombreAttaqueParTour() > 0) {
				this.actionEnCour = new ActionIA(TypeActionIA.attaquerUnite,
						positionUnite,
						comparaisonDistance.getPosition());
				return true;
			} else
				;
		}
		return false;
	}

	/**
	 * Fin du tour
	 * 
	 * @throws FinDuTourException
	 *             Fin du tour.
	 */
	private void declarerLaFinDuTour() throws FinDuTourException {
		this.premiereActionDuTour = true;
		throw new FinDuTourException();
	}

	@Override
	public ActionUnite selectionnerActionUnite() throws FinDuTourException {
		return (ActionUnite) this.actionEnCour.retourAction();
	}

	@Override
	public ActionVille selectionnerActionVille() throws FinDuTourException {
		return (ActionVille) this.actionEnCour.retourAction();
	}

	@Override
	public Position selectionnerPosition() throws FinDuTourException {
		if (this.premiereActionDuTour) {
			this.premiereActionDuTour = false;
			this.actualiserLesResources();
		}
		if (this.actionEnCour == null || this.actionEnCour.actionTermine())
			choixDeLaProchaineAction();
		return (Position) this.actionEnCour.retourAction();
	}

	@Override
	public TypeUnite obtenirLeTypeDUniteSelectionne() {
		return TypeUnite.Soldats;
	}
}
