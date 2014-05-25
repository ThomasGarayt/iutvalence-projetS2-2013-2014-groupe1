package ihmCivilisation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import systemeCivilisation.Joueur;
import systemeCivilisation.PartieDeCivilisation;
import systemeCivilisation.Position;
import systemeCivilisation.TypeUnite;

public class InterfaceGraphique implements Runnable, ActionListener {

	
	private FenetreCivilisation fenetreDeJeu;
	private PartieDeCivilisation logiqueDuJeu;
	
	private Position positionDeLUniteSelectionner;
	private Joueur joueurCourant;

	public InterfaceGraphique() {
		this.fenetreDeJeu = new FenetreCivilisation(this);
		this.logiqueDuJeu = new PartieDeCivilisation();
		this.positionDeLUniteSelectionner = null;
		//TODO remplacer par le primier joueur du tableau de joueur de la logique.
		this.joueurCourant = new Joueur();
	}
	
	public void mettreAJourLaCarte() {
		this.fenetreDeJeu.mettreAJourLaCarte(this.logiqueDuJeu.obtenirCarte() );
	}
	
	@Override
	public void run() {
		this.fenetreDeJeu.initialiserFenetreCivilisation(this.logiqueDuJeu.obtenirCarte() );
		
		this.logiqueDuJeu.ajouterUneUnite(null, new Position(9,10), TypeUnite.Soldats);
		
		this.fenetreDeJeu.mettreAJourLaCarte(this.logiqueDuJeu.obtenirCarte());
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		JComponent source = (JComponent) event.getSource();
		Position positionDeLaSelection = new Position(((BoutonCarte) source).obtenirX(),((BoutonCarte) source).obtenirY());
		
		if ( (this.positionDeLUniteSelectionner == null) && ( this.logiqueDuJeu.obtenirCarte().laCaseNecontientPasDUnite(positionDeLaSelection)) )
				return;
		
		if (this.positionDeLUniteSelectionner == null) {
			this.fenetreDeJeu.mettreAJourLeMenu(this.logiqueDuJeu.obtenirCarte().obtenirLUniteDeLaCase(positionDeLaSelection));
			this.positionDeLUniteSelectionner = positionDeLaSelection;
			}
		else {
			if (this.logiqueDuJeu.obtenirCarte().laCaseNecontientPasDUnite(positionDeLaSelection) )
				this.logiqueDuJeu.deplacerUneUnite(joueurCourant, positionDeLUniteSelectionner, positionDeLaSelection);
			else
				this.logiqueDuJeu.attaquer(null, positionDeLUniteSelectionner, positionDeLaSelection);
			this.mettreAJourLaCarte();
			this.reinitialiserLeMenu();
			this.positionDeLUniteSelectionner = null;
		}
		
	}

	private void reinitialiserLeMenu() {
		this.fenetreDeJeu.mettreAJourLeMenu();
	}
	
}
