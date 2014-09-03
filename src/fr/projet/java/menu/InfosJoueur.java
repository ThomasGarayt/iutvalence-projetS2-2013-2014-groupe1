package fr.projet.java.menu;

import fr.projet.java.gestionGraphique.SetDImages;

public class InfosJoueur {

	private String nom;
	private SetDImages nation;
	private boolean estUneIA;

	public InfosJoueur(String nom, SetDImages nation, boolean estUneIA) {
		this.nom = nom;
		this.nation = nation;
		this.estUneIA = estUneIA;
	}

	public String getNom() {
		return this.nom;
	}

	public SetDImages getNation() {
		return this.nation;
	}

	public boolean getEstUneIA() {
		return this.estUneIA;
	}

}
